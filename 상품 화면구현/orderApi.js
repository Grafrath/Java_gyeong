const BASE_URL = "http://localhost:8080";
const API_URL = BASE_URL + "/order";

export const orderApi = {
    
    createOrder: async (orderRequest) => {
        const url = API_URL + "/create";
        const options = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(orderRequest)
        };
        const result = await apiFetch(url, options);
        return result.data;
    },
    
    loadOrders: async () => {
        const url = API_URL + "/listAll";
        const result = await apiFetch(url);
        return result.data;
    }

};