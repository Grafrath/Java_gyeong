const BASE_URL = "http://localhost:8080";
const API_PRODUCT_URL = BASE_URL + "/product";
const API_ORDER_URL = BASE_URL + "/order";

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

// 상품 목록기능
export const productApi = {

    loadProducts: async (searchItem = '') => {
        let url = API_PRODUCT_URL;

        if (searchItem) {
            url += "/listName?name=" + encodeURIComponent(searchItem);
        } else {
            url += "/listAll";
        }

        const result = await apiFetch(url);
        return result.data;
    },

    addProduct: async (newProduct) => {
        const url = API_PRODUCT_URL + "/create";
        const options = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newProduct)
        };
        const result = await apiFetch(url, options);
        return result.data
    },

    updateProduct: async (updatedProduct) => {
        const url = API_PRODUCT_URL + "/update";
        const options = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedProduct)
        };
        const result = await apiFetch(url, options);
        return result.data;
    },

    deleteProduct: async (id) => {
        const url = `${API_PRODUCT_URL}/delete/${id}`;
        const options = {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        };
        const result = await apiFetch(url, options);
        return result.data;
    }
};

// 주문 관련기능
export const orderApi = {

    createOrder: async (productId, quantity) => {
        const url = API_ORDER_URL + "/create";

        const orderRequest = {
            productId: productId,
            quantity: quantity
        };

        const options = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(orderRequest)
        };

        const result = await apiFetch(url, options);
        return result.data[0];
    },

    loadOrders: async () => {
        const url = API_ORDER_URL + "/listAll";
        const result = await apiFetch(url);
        return result.data;
    }
};