const API_URL = "http://localhost:8080/product";

// DOM 요소 참조 (검색 필드 추가)
const productListBody = document.querySelector("#product-list-body");
const productNameInput = document.querySelector("#product-name");
const productPriceInput = document.querySelector("#product-price");
const productStockInput = document.querySelector("#product-stock");
const addProductBtn = document.querySelector("#add-product-btn");
// ⭐️ 검색 입력 필드 참조 추가
const searchInput = document.querySelector("#search-input");

// ----------------------------------------------------
// 1. 목록 렌더링 함수 (수정 이벤트 리스너 확장)
// ----------------------------------------------------
function renderProductList(products) {
    productListBody.innerHTML = "";

    if (!products || products.length === 0) {
        // ... 등록된 상품이 없습니다 로직 ...
        const tr = productListBody.insertRow();
        const td = tr.insertCell();
        td.colSpan = 6;
        td.textContent = searchInput.value ? "검색 결과가 없습니다." : "등록된 상품이 없습니다.";
        td.style.textAlign = 'center';
        return;
    }

    products.forEach(product => {
        const tr = productListBody.insertRow();
        tr.dataset.id = product.id;

        tr.insertCell().textContent = product.id;

        // 2. 상품명 셀 (수정 가능)
        const nameCell = tr.insertCell();
        nameCell.className = 'editable-cell';
        nameCell.textContent = product.name;
        nameCell.addEventListener("dblclick", () => startEdit(nameCell, product, 'name'));

        // ⭐️ 3. 가격 셀 (수정 가능)
        const priceCell = tr.insertCell();
        priceCell.className = 'editable-cell price-cell';
        priceCell.textContent = product.price.toLocaleString() + "원";
        // 원본 값을 저장하여 수정 로직에 사용
        priceCell.dataset.value = product.price;
        priceCell.addEventListener("dblclick", () => startEdit(priceCell, product, 'price'));

        // ⭐️ 4. 재고 셀 (수정 가능)
        const stockCell = tr.insertCell();
        stockCell.className = 'editable-cell stock-cell';
        stockCell.textContent = product.stock.toLocaleString();
        // 원본 값을 저장하여 수정 로직에 사용
        stockCell.dataset.value = product.stock;
        stockCell.addEventListener("dblclick", () => startEdit(stockCell, product, 'stock'));

        const date = new Date(product.createTimeStamp);
        tr.insertCell().textContent = date.toLocaleDateString();

        // 6. 버튼 컬럼 (삭제 버튼 로직 유지)
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

// ----------------------------------------------------
// 2. 전체 상품 목록 조회 또는 검색 (Read All / listByName)
// ----------------------------------------------------
async function loadProducts(searchTerm = '') {
    let url = API_URL;

    if (searchTerm) {
        url += "/listName?name=" + encodeURIComponent(searchTerm);
    } else {
        url += "/listAll";
    }

    try {
        const response = await fetch(url);
        // ⭐️ 200 OK가 아니더라도 응답 본문(Body)을 먼저 읽습니다.
        const result = await response.json();

        if (!response.ok) {
            // ⭐️ 400 Bad Request이면서 "상품을 찾을 수 없습니다." 에러인 경우를 구분합니다.
            if (response.status === 400 && result.error && result.error.includes("상품을 찾을 수 없습니다.")) {
                // 검색 결과가 없을 뿐, 통신 오류는 아니므로 빈 목록을 렌더링하고 종료합니다.
                renderProductList([]);
                return;
            }

            // 그 외의 모든 서버 에러 (500 Internal, 다른 종류의 400 Bad Request 등)
            console.error("서버 오류:", result.error);
            // 오류 메시지를 포함하여 throw 하여 catch 블록에서 사용자에게 보여줍니다.
            throw new Error(result.error || "서버에서 알 수 없는 오류 발생");
        }

        // 200 OK인 경우
        renderProductList(result.data);

    } catch (error) {
        console.error("네트워크 오류:", error);
        // 구체적인 에러 메시지를 alert에 포함하여 사용자에게 보여줍니다.
        alert("API 통신 중 오류가 발생했습니다. 서버 상태를 확인해주세요: " + error.message);
    }
}

// ----------------------------------------------------
// 3. 상품 추가 함수 (기존 로직 유지)
// ----------------------------------------------------
async function addProduct() {
    // ... (기존 로직)
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

        // 검색 중이더라도 새 항목 추가 후에는 전체 목록을 로드하는 것이 일반적
        // 또는 검색 상태를 유지하며 로드: loadProducts(searchInput.value.trim());
        loadProducts();

    } catch (error) {
        console.error("상품 추가 중 오류:", error);
        alert("상품 추가에 실패했습니다.");
    }
}


// ----------------------------------------------------
// 4. 상품 수정 시작 및 완료 (일반화)
// ----------------------------------------------------
function startEdit(cell, product, fieldName) {
    if (cell.querySelector('.edit-input')) return;

    // 현재 셀의 원본 값 가져오기
    let originalValue;
    if (fieldName === 'name') {
        originalValue = product.name;
    } else {
        // price나 stock일 경우 dataset.value에 저장된 숫자 값을 사용
        originalValue = parseInt(cell.dataset.value);
    }

    const editInput = document.createElement('input');
    editInput.className = 'edit-input';
    editInput.value = originalValue;

    // ⭐️ 필드 타입에 따라 입력 타입 설정
    if (fieldName === 'name') {
        editInput.type = 'text';
    } else { // price or stock
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
            // 숫자 유효성 검사
            if (isNaN(newValue) || newValue < 0) {
                alert(`${fieldName}은(는) 0 이상의 숫자여야 합니다.`);
                // 복구 후 재로드
                loadProducts(searchInput.value);
                return;
            }
        }

        // 값이 변경되지 않았거나 입력이 비어있다면 복원
        if (newValue.toString() === originalValue.toString() || newValue === "") {
            cell.textContent = (fieldName === 'name')
                ? originalValue
                : originalValue.toLocaleString() + (fieldName === 'price' ? "원" : "");
        } else {
            // ⭐️ 업데이트 함수 호출
            await updateProduct(product.id, fieldName, newValue, product);
        }

        editInput.remove();
        // 변경 사항 반영을 위해 목록 재로드 (서버에서 최신 데이터 가져옴)
        loadProducts(searchInput.value.trim());
    };

    editInput.addEventListener('blur', finishEdit);
    editInput.addEventListener('keydown', finishEdit);
}

// ----------------------------------------------------
// 5. 상품 정보 업데이트 API 호출 (PUT /product/update)
// ----------------------------------------------------
async function updateProduct(id, fieldToUpdate, newValue, originalProduct) {
    const updatedProduct = {
        id: id,
        name: originalProduct.name,
        price: originalProduct.price,
        stock: originalProduct.stock
    };

    // 변경된 필드만 새로운 값으로 업데이트
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

        // 성공적으로 수정되면 목록을 다시 로드 (loadProducts에서 이미 처리됨)

    } catch (error) {
        console.error("상품 수정 중 오류 발생:", error);
        alert(`상품 수정에 실패했습니다: ${error.message}`);
        // 오류 시 원래 상태로 복원하기 위해 목록 재로드
        loadProducts(searchInput.value.trim());
    }
}


// ----------------------------------------------------
// 6. 상품 삭제 함수 (기존 로직 유지)
// ----------------------------------------------------
async function deleteProduct(id) {
    // ... (기존 로직)
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


// ----------------------------------------------------
// 7. 초기화 및 이벤트 리스너 등록
// ----------------------------------------------------
document.addEventListener("DOMContentLoaded", () => {
    addProductBtn.addEventListener("click", addProduct);

    // ⭐️ 수정된 부분: input 이벤트를 keydown 이벤트로 변경하고 Enter 키를 확인합니다.
    searchInput.addEventListener("keydown", (e) => {
        // 1. 눌린 키가 'Enter'인지 확인합니다.
        if (e.key === 'Enter') {
            e.preventDefault(); // 기본 동작(폼 제출 등)을 방지합니다.

            // 2. 검색 함수를 호출합니다.
            loadProducts(searchInput.value.trim());

            // 3. 엔터 입력 후 포커스 이동 (선택 사항)
            searchInput.blur();
        }
    });
    // (선택 사항: 기존의 'input' 이벤트 리스너가 남아 있다면 제거해주세요.)
    /*
    searchInput.addEventListener("input", () => {
        loadProducts(searchInput.value.trim()); // 이 코드는 이제 제거됩니다.
    });
    */

    // 페이지 로드 시 상품 목록 조회
    loadProducts();
});