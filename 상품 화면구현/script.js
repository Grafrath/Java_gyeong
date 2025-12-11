import { productApi } from './api.js';
import * as UI from './ui.js';
import { createOrder, loadOrders } from './orderScript.js';

// 전체 목록 조회 또는 상품명 검색
async function loadProducts(searchItem = '') {
    UI.showLoadingStatus();
    try {
        const products = await productApi.loadProducts(searchItem);

        // 상품 목록
        UI.renderProductList(products, deleteProduct, startEditHandler, searchItem);

        // 주문 가능 상품 목록
        UI.renderBuyList(products, createOrderHandler);

    } catch (error) {
        UI.showErrorStatus(error.message, searchItem);

        if (error.status === 400 && error.message.includes("상품을 찾을 수 없습니다.")) {
            UI.renderProductList([], deleteProduct, startEditHandler, searchItem);
            UI.renderBuyList([], createOrderHandler);
            return;
        }
        console.error("API 통신 오류:", error);
        alert("API 통신 중 오류가 발생했습니다. 서버 상태를 확인해주세요: " + error.message);
    }
}

async function addProduct() {
    const name = UI.productNameInput.value.trim();
    const price = parseInt(UI.productPriceInput.value);
    const stock = parseInt(UI.productStockInput.value);

    if (!name || isNaN(price) || isNaN(stock) || price < 0 || stock < 0) {
        alert("모든 필드를 정확하게 입력해주세요 (가격/수량은 0 이상).");
        return;
    }

    const newProduct = { name, price, stock };

    try {
        const resultData = await productApi.addProduct(newProduct);
        const savedProduct = resultData[0];

        if (!savedProduct) {
            console.error("상품 등록은 성공했으나 응답 데이터가 비어있습니다.");
            await loadProducts(); // 데이터 문제 시 전체 재로딩
            return;
        }

        console.log("새로 등록된 상품 정보:", savedProduct);

        // 1. 상품 목록 갱신 (즉시 렌더링)
        UI.renderSingleProductRow(savedProduct, deleteProduct, startEditHandler);

        // 2. 주문 가능 상품 목록 갱신 (전체 상품 목록을 다시 불러와 갱신)
        const allProducts = await productApi.loadProducts(UI.searchInput.value.trim());
        UI.renderBuyList(allProducts, createOrderHandler);

    } catch (error) {
        console.error("상품 추가 중 오류:", error);
        alert("상품 추가에 실패했습니다: " + error.message);
    }
}

async function updateProduct(id, fieldToUpdate, newValue, originalProduct) {
    const updatedProduct = {
        id: id,
        name: originalProduct.name,
        price: originalProduct.price,
        stock: originalProduct.stock
    };

    updatedProduct[fieldToUpdate] = newValue;

    try {
        await productApi.updateProduct(updatedProduct);
    } catch (error) {
        console.error("상품 수정 중 오류 발생:", error);
        alert(`상품 수정에 실패했습니다: ${error.message}`);
        loadProducts(UI.searchInput.value.trim());
    }
}

async function deleteProduct(id) {
    try {
        await productApi.deleteProduct(id);
        alert("상품이 삭제되었습니다.");
        // 삭제 후, 상품 목록과 주문 가능 목록을 모두 갱신
        await loadProducts(UI.searchInput.value.trim());
    } catch (error) {
        console.error("상품 삭제 중 오류:", error);
        alert("상품 삭제에 실패했습니다: " + error.message);
        loadProducts();
    }
}

function startEditHandler(cell, product, fieldName) {
    UI.startEdit(cell, product, fieldName, updateProduct, loadProducts);
}

// 주문 생성
function createOrderHandler(productId, quantityInput) {
    const quantity = parseInt(quantityInput.value);

    if (isNaN(quantity) || quantity <= 0) {
        alert("구매 수량은 1개 이상으로 정확하게 입력해주세요.");
        return;
    }

    createOrder(productId, quantity)
        .then(() => {
            loadProducts(UI.searchInput.value.trim());
            loadOrders();
        })
        .catch(error => {
            alert(`주문 실패: ${error.message}`);
        });
}

// 초기화
document.addEventListener("DOMContentLoaded", async () => {
    // ⭐️ Live Preview 문제 회피를 위해 'load' 이벤트로 변경 가능 ⭐️
    // window.addEventListener("load", async () => { ... } );

    UI.addProductBtn.addEventListener("click", addProduct);
    UI.searchInput.addEventListener("keydown", (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            loadProducts(UI.searchInput.value.trim());
            UI.searchInput.blur();
        }
    });

    await loadProducts();
    await loadOrders();
});