const BASE_URL = "http://localhost:8080";
const API_URL = BASE_URL +"/product";

const productListBody = document.querySelector("#product-list-body");
const productNameInput = document.querySelector("#product-name");
const productPriceInput = document.querySelector("#product-price");
const productStockInput = document.querySelector("#product-stock");
const addProductBtn = document.querySelector("#add-product-btn");
const searchInput = document.querySelector("#search-input");

// 목록 렌더링 함수
function renderProductList(products) {
    productListBody.innerHTML = "";

    if (!products || products.length === 0) {
        const tr = productListBody.insertRow();
        const td = tr.insertCell();
        td.colSpan = 7;
        td.textContent = searchInput.value ? "검색 결과가 없습니다." : "등록된 상품이 없습니다.";
        td.style.textAlign = 'center';
        return;
    }

    products.forEach(product => {
        const tr = productListBody.insertRow();
        tr.dataset.id = product.id;

        tr.insertCell().textContent = product.id;

        // 상품명 셀
        const nameCell = tr.insertCell();
        nameCell.className = 'editable-cell';
        nameCell.textContent = product.name;
        nameCell.addEventListener("dblclick", () => startEdit(nameCell, product, 'name'));

        // 가격
        const priceCell = tr.insertCell();
        priceCell.className = 'editable-cell price-cell';
        priceCell.textContent = product.price.toLocaleString() + "원";
        priceCell.dataset.value = product.price;
        priceCell.addEventListener("dblclick", () => startEdit(priceCell, product, 'price'));

        // 재고
        const stockCell = tr.insertCell();
        stockCell.className = 'editable-cell stock-cell';
        stockCell.textContent = product.stock.toLocaleString();
        stockCell.dataset.value = product.stock;
        stockCell.addEventListener("dblclick", () => startEdit(stockCell, product, 'stock'));

        const date = new Date(product.createTimeStamp);
        tr.insertCell().textContent = date.toLocaleDateString();

        // 삭제 버튼
        const actionCell = tr.insertCell();
        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "삭제";
        deleteBtn.className = "delete-btn";
        deleteBtn.addEventListener("click", () => {
            if (confirm(`[${product.name}] 상품을 정말 삭제하시겠습니까?`)) {
                deleteProduct(product.id);
            }
        });
        actionCell.appendChild(deleteBtn);
    });
}

// 전체 목록 조회 또는 상품명 검색
async function loadProducts(searchItem = '') {
    let url = API_URL;

    if (searchItem) {
        url += "/listName?name=" + encodeURIComponent(searchItem);
    } else {
        url += "/listAll";
    }

    try {
        const response = await fetch(url);
        const result = await response.json();

        if (!response.ok) {
            if (response.status === 400 && result.error && result.error.includes("상품을 찾을 수 없습니다.")) {
                renderProductList([]);
                return;
            }

            console.error("서버 오류:", result.error);
            throw new Error(result.error || "서버에서 알 수 없는 오류 발생");
        }

        renderProductList(result.data);

    } catch (error) {
        console.error("네트워크 오류:", error);
        alert("API 통신 중 오류가 발생했습니다. 서버 상태를 확인해주세요: " + error.message);
    }
}

// 상품 추가 함수
async function addProduct() {
    const name = productNameInput.value.trim();
    const price = parseInt(productPriceInput.value);
    const stock = parseInt(productStockInput.value);

    if (!name || isNaN(price) || isNaN(stock) || price < 0 || stock < 0) {
        alert("모든 필드를 정확하게 입력해주세요 (가격/수량은 0 이상).");
        return;
    }

    const newProduct = { name, price, stock };

    try {
        const response = await fetch(API_URL + "/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newProduct)
        });

        if (!response.ok) throw new Error("상품 추가 실패");

        productNameInput.value = "";
        productPriceInput.value = "";
        productStockInput.value = "";

        loadProducts();

    } catch (error) {
        console.error("상품 추가 중 오류:", error);
        alert("상품 추가에 실패했습니다.");
    }
}


// 상품 수정 시작 및 완료
function startEdit(cell, product, fieldName) {
    if (cell.querySelector('.edit-input')) return;

    // 현재 항목의 원본 값 가져오기
    let originalValue;
    if (fieldName === 'name') {
        originalValue = product.name;
    } else {
        originalValue = parseInt(cell.dataset.value);
    }

    const editInput = document.createElement('input');
    editInput.className = 'edit-input';
    editInput.value = originalValue;

    if (fieldName === 'name') {
        editInput.type = 'text';
    } else {
        editInput.type = 'number';
        editInput.min = '0';
        editInput.style.textAlign = 'right';
    }

    cell.innerHTML = '';
    cell.appendChild(editInput);
    editInput.focus();

    const finishEdit = async (e) => {
        if (e && e.type === 'keydown' && e.key !== 'Enter') return;
        if (e && e.type === 'blur' && e.key) return;

        let newValue = editInput.value.trim();

        if (fieldName !== 'name') {
            newValue = parseInt(newValue);
            if (isNaN(newValue) || newValue < 0) {
                alert(`${fieldName}은(는) 0 이상의 숫자여야 합니다.`);
               loadProducts(searchInput.value);
                return;
            }
        }

        if (newValue.toString() === originalValue.toString() || newValue === "") {
            cell.textContent = (fieldName === 'name')
                ? originalValue
                : originalValue.toLocaleString() + (fieldName === 'price' ? "원" : "");
        } else {
           await updateProduct(product.id, fieldName, newValue, product);
        }

        editInput.remove();
        loadProducts(searchInput.value.trim());
    };

    editInput.addEventListener('blur', finishEdit);
    editInput.addEventListener('keydown', finishEdit);
}

// 상품 정보 업데이트
async function updateProduct(id, fieldToUpdate, newValue, originalProduct) {
    const updatedProduct = {
        id: id,
        name: originalProduct.name,
        price: originalProduct.price,
        stock: originalProduct.stock
    };

    updatedProduct[fieldToUpdate] = newValue;

    try {
        const response = await fetch(API_URL + "/update", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedProduct)
        });

        if (!response.ok) {
            const errorResult = await response.json();
            throw new Error(errorResult.error || "상품 수정 실패");
        }
    } catch (error) {
        console.error("상품 수정 중 오류 발생:", error);
        alert(`상품 수정에 실패했습니다: ${error.message}`);
        loadProducts(searchInput.value.trim());
    }
}


// 상품 삭제
async function deleteProduct(id) {
    try {
        const response = await fetch(`${API_URL}/delete/${id}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        });

        if (!response.ok) throw new Error('상품 삭제 실패');

        const result = await response.json();

        if (result.data) {
            alert("상품이 삭제되었습니다.");
            renderProductList(result.data);
        } else {
            throw new Error(result.error || "알 수 없는 오류로 삭제 실패");
        }
    } catch (error) {
        console.error("상품 삭제 중 오류:", error);
        alert("상품 삭제에 실패했습니다: " + error.message);
        loadProducts();
    }
}


// 초기화
document.addEventListener("DOMContentLoaded", () => {
    addProductBtn.addEventListener("click", addProduct);
    searchInput.addEventListener("keydown", (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            
            loadProducts(searchInput.value.trim());
            
            searchInput.blur();
        }
    });

    loadProducts();

});