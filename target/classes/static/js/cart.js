import {createUserOrder, getUserData, removeItemFromUserCart, showNotification} from "./export.js";

window.onload = () => {
    fetchUserData()
}
document.title = 'Корзина';
const orderProductsElement = document.getElementById('orderProducts');
const totalPriceElement = document.getElementById('totalPrice');

function fetchUserData() {
    while (orderProductsElement.firstChild) {
        orderProductsElement.removeChild(orderProductsElement.firstChild);
    }
    getUserData().then(data => {
        document.getElementById("userName").value = data.name;
        document.getElementById("userEmail").value = data.email;
        document.getElementById("userAddress").value = data.address;
        let totalPrice = 0;
        data.cart.forEach(product => {
            const itemElement = document.createElement("div");
            itemElement.className = "cart-item";
            itemElement.style.display = "flex";
            itemElement.style.flexDirection = "row";
            itemElement.style.alignItems = "flex-end";
            itemElement.style.justifyContent = "space-between";
            itemElement.style.aligContent = "space-between";
            itemElement.dataset.productId = product.id;
            itemElement.innerHTML = `
        <div style="display: flex" class="product-info">
          <a style="margin-right: 5px" data-index="${product.id}" class="cartDeleteButton"><b>X</b></a>
          <div>${product.name}</div>
        </div>
        <div align="right" style="width: 35%" >${product.price}</div>
      `;

            totalPrice += product.price;

            orderProductsElement.appendChild(itemElement);

            const deleteButton = itemElement.querySelector(".cartDeleteButton");
            deleteButton.addEventListener("click", function(event) {
                const id = deleteButton.dataset.index;
                removeItemFromCart(id);
            });
        });
        totalPriceElement.textContent = totalPrice.toFixed(2);
    });
}


function createOrder() {
     createUserOrder()
         .then(data => {
             if (data) {
                 showNotification('Заказ успешно создан', 2000, 'notification')
                 fetchUserData();
             }
         })
}

function removeItemFromCart(id) {
        removeItemFromUserCart(id)
            .then(data => {
                if (data) {
                    fetchUserData()
                    showNotification('Заказ успешно удален', 2000, 'notification');
                }
            })
}
document.getElementById('createOrder').addEventListener('click', createOrder);