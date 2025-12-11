import { productApi } from './api.js';
import * as UI from './ui.js';

// 전체 목록 조회 또는 상품명 검색
async function loadProducts(searchItem = '') {
    UI.showLoadingStatus();
    try {
        const products = await productApi.loadProducts(searchItem);
        UI.renderProductList(products, deleteProduct, startEditHandler, searchItem);
    } catch (error) {
        UI.showErrorStatus(error.message, searchItem);

        if (error.status === 400 && error.message.includes("상품을 찾을 수 없습니다.")) {
            UI.renderProductList([], deleteProduct, startEditHandler, searchItem);
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

        UI.productNameInput.value = "";
        UI.productPriceInput.value = "";
        UI.productStockInput.value = "";

        console.log("새로 등록된 상품 정보:", savedProduct);
        await loadProducts();
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
        const remainingProducts = await productApi.deleteProduct(id);
        alert("상품이 삭제되었습니다.");
        UI.renderProductList(remainingProducts, deleteProduct, startEditHandler, UI.searchInput.value.trim());
    } catch (error) {
        console.error("상품 삭제 중 오류:", error);
        alert("상품 삭제에 실패했습니다: " + error.message);
        loadProducts();
    }
}

function startEditHandler(cell, product, fieldName) {
    UI.startEdit(cell, product, fieldName, updateProduct, loadProducts);
}

// 초기화
document.addEventListener("DOMContentLoaded", async () => {
    UI.addProductBtn.addEventListener("click", addProduct);
    UI.searchInput.addEventListener("keydown", (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            loadProducts(UI.searchInput.value.trim());
            UI.searchInput.blur();
        }
    });

    await loadProducts();
});