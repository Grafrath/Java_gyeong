export const productListBody = document.querySelector("#product-list-body");
export const productNameInput = document.querySelector("#product-name");
export const productPriceInput = document.querySelector("#product-price");
export const productStockInput = document.querySelector("#product-stock");
export const addProductBtn = document.querySelector("#add-product-btn");
export const searchInput = document.querySelector("#search-input");
export const buyListBody = document.querySelector("#Buy-list-body");
export const orderListBody = document.querySelector("#order-list-body");

// 로딩 및 에러 상태 메시지 영역 (HTML에 ID가 있다고 가정)
export const loadingStatus = document.querySelector("#loading-status-message");
export const errorStatus = document.querySelector("#error-status-message");


// 로딩 상태 표시 함수
export function showLoadingStatus() {
    if (loadingStatus) {
        loadingStatus.textContent = "로딩 중...";
        loadingStatus.style.display = 'block';
    }
    if (errorStatus) errorStatus.style.display = 'none';
    if (productListBody) productListBody.innerHTML = "";
    if (buyListBody) buyListBody.innerHTML = "";
}

// 에러 상태 표시 함수
export function showErrorStatus(message, searchItem = '') {
    if (loadingStatus) loadingStatus.style.display = 'none';
    if (errorStatus) {
        errorStatus.textContent = searchItem
            ? `"${searchItem}" 검색 결과: ${message}`
            : `오류 발생: ${message}`;
        errorStatus.style.display = 'block';
    }
}

// 상품 목록 렌더링 (Read)
export function renderProductList(products, deleteProductCallback, startEditCallback, searchInputValue) {
    if (loadingStatus) loadingStatus.style.display = 'none';
    if (errorStatus) errorStatus.style.display = 'none';
    if (!productListBody) return;

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

        const nameCell = tr.insertCell();
        nameCell.className = 'editable-cell';
        nameCell.textContent = product.name;
        nameCell.dataset.field = 'name';
        nameCell.addEventListener("dblclick", () => startEditCallback(nameCell, product, 'name'));

        const priceCell = tr.insertCell();
        priceCell.className = 'editable-cell price-cell';
        priceCell.textContent = product.price.toLocaleString() + "원";
        priceCell.dataset.value = product.price;
        priceCell.dataset.field = 'price';
        priceCell.addEventListener("dblclick", () => startEditCallback(priceCell, product, 'price'));

        const stockCell = tr.insertCell();
        stockCell.className = 'editable-cell stock-cell';
        stockCell.textContent = product.stock.toLocaleString();
        stockCell.dataset.value = product.stock;
        stockCell.dataset.field = 'stock';
        stockCell.addEventListener("dblclick", () => startEditCallback(stockCell, product, 'stock'));

        const createDate = new Date(product.createTimeStamp);
        tr.insertCell().textContent = createDate.toLocaleDateString();

        const updateDateCell = tr.insertCell();
        const updateDate = product.updateTimeStamp
            ? new Date(product.updateTimeStamp).toLocaleDateString()
            : '';
        updateDateCell.textContent = updateDate;

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

// 상품 수정 시작
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
                loadProductsCallback(searchInput.value);
                return;
            }
        }

        if (newValue.toString() === originalValue.toString() || newValue === "") {
            cell.textContent = (fieldName === 'price')
                ? originalValue.toLocaleString() + "원"
                : originalValue.toLocaleString();
            if (fieldName === 'name') cell.textContent = originalValue;
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

// 단일 상품 행 렌더링 (즉시 갱신용)
export function renderSingleProductRow(product, deleteProductCallback, startEditCallback) {
    const tableBody = productListBody;

    if (!tableBody) {
        console.error("Critical Error: '#product-list-body' 요소를 찾을 수 없습니다.");
        return;
    }

    // 폼 입력 필드 초기화
    if (productNameInput) productNameInput.value = "";
    if (productPriceInput) productPriceInput.value = "";
    if (productStockInput) productStockInput.value = "";

    const newRow = tableBody.insertRow(0); // 가장 위에 행 삽입
    newRow.dataset.id = product.id;

    // ID
    newRow.insertCell().textContent = product.id;

    // 상품명 (Editable)
    const nameCell = newRow.insertCell();
    nameCell.className = 'editable-cell';
    nameCell.textContent = product.name;
    nameCell.dataset.field = 'name';
    nameCell.addEventListener("dblclick", () => startEditCallback(nameCell, product, 'name'));

    // 가격 (Editable)
    const priceCell = newRow.insertCell();
    priceCell.className = 'editable-cell price-cell';
    priceCell.textContent = product.price.toLocaleString() + "원";
    priceCell.dataset.value = product.price;
    priceCell.dataset.field = 'price';
    priceCell.addEventListener("dblclick", () => startEditCallback(priceCell, product, 'price'));

    // 재고 (Editable)
    const stockCell = newRow.insertCell();
    stockCell.className = 'editable-cell stock-cell';
    stockCell.textContent = product.stock.toLocaleString();
    stockCell.dataset.value = product.stock;
    stockCell.dataset.field = 'stock';
    stockCell.addEventListener("dblclick", () => startEditCallback(stockCell, product, 'stock'));

    // 생성 날짜
    const createDate = new Date(product.createTimeStamp);
    newRow.insertCell().textContent = createDate.toLocaleDateString();

    // 수정 날짜
    const updateDateCell = newRow.insertCell();
    const updateDate = product.updateTimeStamp
        ? new Date(product.updateTimeStamp).toLocaleDateString()
        : '';
    updateDateCell.textContent = updateDate;

    // 삭제 버튼
    const actionCell = newRow.insertCell();
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "삭제";
    deleteBtn.className = "delete-btn";

    deleteBtn.addEventListener("click", () => {
        if (confirm(`[${product.name}] 상품을 정말 삭제하시겠습니까?`)) {
            deleteProductCallback(product.id);
        }
    });
    actionCell.appendChild(deleteBtn);
}


// ⭐️ 주문 가능 상품 목록 렌더링 (7개 칼럼 구조에 맞춤)
export function renderBuyList(products, createOrderCallback) {
    if (!buyListBody) return;

    buyListBody.innerHTML = '';

    products.forEach(product => {
        // 재고가 0 이상인 상품만 렌더링
        if (product.stock > 0) {
            const tr = buyListBody.insertRow();
            tr.insertCell().textContent = product.id; // 1. 상품 번호
            tr.insertCell().textContent = product.name; // 2. 상품명
            tr.insertCell().textContent = product.price.toLocaleString() + "원"; // 3. 가격
            tr.insertCell().textContent = product.stock.toLocaleString(); // 4. 재고

            // 5. ⭐️ 구매 수량 칼럼 생성
            const quantityCell = tr.insertCell();
            const input = document.createElement('input');
            input.type = 'number';
            input.min = '1';
            input.max = product.stock;
            input.value = '1';
            input.className = 'buy-quantity-input';
            quantityCell.appendChild(input);

            // 6. ⭐️ 주문 가격 칼럼 생성 (자동 계산 로직 추가)
            const totalPriceCell = tr.insertCell();
            totalPriceCell.className = 'buy-total-price';
            totalPriceCell.textContent = (product.price * 1).toLocaleString() + "원"; // 초기값

            // 수량 변경 시 주문 가격 자동 업데이트 로직
            input.addEventListener('input', (e) => {
                let quantity = parseInt(e.target.value);

                // 입력값 유효성 검사 (음수/NaN 방지, 재고 초과 방지)
                if (isNaN(quantity) || quantity < 1) quantity = 1;
                if (quantity > product.stock) {
                    quantity = product.stock;
                    e.target.value = quantity;
                }

                const price = quantity * product.price;
                totalPriceCell.textContent = price.toLocaleString() + "원";
            });

            // 7. ⭐️ 구매 버튼 칼럼 생성
            const buyActionCell = tr.insertCell();
            const button = document.createElement("button");
            button.textContent = '구매';
            button.className = 'buy-btn';

            button.addEventListener('click', () => createOrderCallback(product.id, input));
            buyActionCell.appendChild(button);
        }
    });
}

// ⭐️ 주문 목록 렌더링 (6개 칼럼 구조에 맞춤)
export function renderOrderList(orders) {
    if (!orderListBody) return;

    orderListBody.innerHTML = '';

    if (!orders || orders.length === 0) {
        const tr = orderListBody.insertRow();
        const td = tr.insertCell();
        td.colSpan = 6; // index.html 헤더 (6개)에 맞춤
        td.textContent = "주문 내역이 없습니다.";
        td.style.textAlign = 'center';
        return;
    }

    orders.forEach(order => {
        const tr = orderListBody.insertRow();

        // 1. 주문 번호
        tr.insertCell().textContent = order.orderId;

        // 2. 상품 번호
        tr.insertCell().textContent = order.productId || 'N/A';

        // 3. 상품명
        tr.insertCell().textContent = order.productName;

        // 4. 주문 수량
        tr.insertCell().textContent = order.quantity.toLocaleString() + "개";

        // 5. 주문 가격
        tr.insertCell().textContent = order.orderTotalPrices.toLocaleString() + "원";

        // 6. 주문 시간
        tr.insertCell().textContent = new Date(order.orderTime).toLocaleDateString();
    });
}