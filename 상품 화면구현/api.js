const BASE_URL = "http://localhost:8080";
const API_URL = BASE_URL + "/product";

async function apiFetch(url, options = {}) {
    const response = await fetch(url, options);
    const result = await response.json();

    if (response.ok) {
        console.log("서버 연결상태: " + response.ok);
    }

    if (!response.ok) {
        const errorMessage = result.error || `HTTP Error: ${response.status}`;
        const error = new Error(errorMessage);
        error.status = response.status;
        error.errorResult = result;
        throw error;
    }

    return result;
}

//상품 목록 전체 조회 또는 검색
export const productApi = {
    
    loadProducts: async (searchItem = '') => {
        let url = API_URL;

        if (searchItem) {
            url += "/listName?name=" + encodeURIComponent(searchItem);
        } else {
            url += "/listAll";
        }

        const result = await apiFetch(url);
        return result.data;
    },

    /** 상품 추가 (POST) */
    addProduct: async (newProduct) => {
        const url = API_URL + "/create";
        const options = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newProduct)
        };
        const result = await apiFetch(url, options);
        return result.data
    },

    /** 상품 수정 (PUT) */
    updateProduct: async (updatedProduct) => {
        const url = API_URL + "/update";
        const options = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedProduct)
        };
        const result = await apiFetch(url, options);
        return result.data;
    },

    /** 상품 삭제 (DELETE) */
    deleteProduct: async (id) => {
        const url = `${API_URL}/delete/${id}`;
        const options = {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        };
        const result = await apiFetch(url, options);
        return result.data;
    }
    
};