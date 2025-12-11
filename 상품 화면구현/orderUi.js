export const orderListBody = document.querySelector("#order-list-body");

export function renderOrderList(orders) {
    orderListBody.innerHTML = "";

    if (!orders || orders.length === 0) {
        const tr = orderListBody.insertRow();
        const td = tr.insertCell();
        td.colSpan = 6;
        td.textContent = "등록된 주문이 없습니다.";
        td.style.textAlign = 'center';
        return;
    }

    orders.forEach(order => {
        const tr = orderListBody.insertRow();
        tr.dataset.id = order.orderId;

        tr.insertCell().textContent = order.orderId;
        tr.insertCell().textContent = order.productId;
        tr.insertCell().textContent = order.productName;
        tr.insertCell().textContent = order.quantity.toLocaleString();

        const total = order.quantity * order.orderedPrice;
        tr.insertCell().textContent = total.toLocaleString() + "원";

        const orderDate = new Date(order.orderTime);
        tr.insertCell().textContent = orderDate.toLocaleDateString() + " " + orderDate.toLocaleTimeString();
    });
    
}