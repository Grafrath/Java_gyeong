export const productListBody = document.querySelector("#product-list-body");
export const productNameInput = document.querySelector("#product-name");
export const productPriceInput = document.querySelector("#product-price");
export const productStockInput = document.querySelector("#product-stock");
export const addProductBtn = document.querySelector("#add-product-btn");
export const searchInput = document.querySelector("#search-input");

// 목록 렌더링 함수
export function renderProductList(products, deleteProductCallback, startEditCallback, searchInputValue) {
    productListBody.innerHTML = "";

    if (!products || products.length === 0) {
        const tr = productListBody.insertRow();
        const td = tr.insertCell();
        td.colSpan = 7;
        td.textContent = searchInputValue ? "검색 결과가 없습니다." : "등록된 상품이 없습니다.";
        td.style.textAlign = 'center';
        return;
    }

    products.forEach(product => {
        const tr = productListBody.insertRow();
        tr.dataset.id = product.id;

        tr.insertCell().textContent = product.id;

        // 상품명
        const nameCell = tr.insertCell();
        nameCell.className = 'editable-cell';
        nameCell.textContent = product.name;
        nameCell.addEventListener("dblclick", () => startEditCallback(nameCell, product, 'name'));

        // 가격
        const priceCell = tr.insertCell();
        priceCell.className = 'editable-cell price-cell';
        priceCell.textContent = product.price.toLocaleString() + "원";
        priceCell.dataset.value = product.price;
        priceCell.addEventListener("dblclick", () => startEditCallback(priceCell, product, 'price'));

        // 재고
        const stockCell = tr.insertCell();
        stockCell.className = 'editable-cell stock-cell';
        stockCell.textContent = product.stock.toLocaleString();
        stockCell.dataset.value = product.stock;
        stockCell.addEventListener("dblclick", () => startEditCallback(stockCell, product, 'stock'));

        // 등록 날짜
        const createDate = new Date(product.createTimeStamp);
        tr.insertCell().textContent = createDate.toLocaleDateString();

        // 수정 날짜
        const updateDateCell = tr.insertCell();
        const updateDate = product.updateTimeStamp
            ? new Date(product.updateTimeStamp).toLocaleDateString()
            : '';
        updateDateCell.textContent = updateDate;

        // 삭제 버튼
        const actionCell = tr.insertCell();
        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "삭제";
        deleteBtn.className = "delete-btn";
        deleteBtn.addEventListener("click", () => {
            if (confirm(`[${product.name}] 상품을 정말 삭제하시겠습니까?`)) {
                deleteProductCallback(product.id);
            }
        });
        actionCell.appendChild(deleteBtn);
    });
}

// 상품 수정 시작 및 완료
export function startEdit(cell, product, fieldName, updateProductCallback, loadProductsCallback) {
    if (cell.querySelector('.edit-input')) return;

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
        let reloadNeeded = false;

        if (fieldName !== 'name') {
            newValue = parseInt(newValue);
            if (isNaN(newValue) || newValue < 0) {
                alert(`${fieldName}은(는) 0 이상의 숫자여야 합니다.`);
                editInput.remove();
                loadProductsCallback(searchInput.value);
                return;
            }
        }

        if (newValue.toString() === originalValue.toString() || newValue === "") {
            cell.textContent = (fieldName === 'name')
                ? originalValue
                : originalValue.toLocaleString() + (fieldName === 'price' ? "원" : "");
        } else {
            await updateProductCallback(product.id, fieldName, newValue, product);
            reloadNeeded = true;
        }

        editInput.remove();
        if (reloadNeeded) {
            loadProductsCallback(searchInput.value.trim());
        }
    };

    editInput.addEventListener('blur', finishEdit);
    editInput.addEventListener('keydown', finishEdit);
}

// 로딩 메시지를 표시하는 함수
export function showLoadingStatus() {
    productListBody.innerHTML = "";
    const tr = productListBody.insertRow();
    const td = tr.insertCell();
    td.colSpan = 7;
    td.textContent = "로딩 중...";
    td.style.textAlign = 'center';
}

// 에러 메시지를 표시하는 함수
export function showErrorStatus(errorMessage, searchItem) {
    productListBody.innerHTML = "";
    const tr = productListBody.insertRow();
    const td = tr.insertCell();
    td.colSpan = 7;

    // API 통신 실패 (5xx 또는 일반적인 네트워크 오류)
    let displayMessage = `데이터를 불러올 수 없습니다, 네트워크 상태를 점검하세요.`;

    console.log(errorMessage);

    td.textContent = displayMessage;
    td.style.textAlign = 'center';
    td.style.color = 'red';
}