import {getCurrentUserOrders, getUserBaseData, showNotification, updateUserInfo} from "./export.js";

window.onload = () => {
    getOrders();
    getInfo();
}

const saveButton = document.getElementById('saveButton')
const logoutButton = document.getElementById('logoutButton')
const updateForm = document.getElementById('updateForm')

function getOrders() {
    const orderContainer = document.getElementById('orderHistory');
    getCurrentUserOrders()
        .then(data => {
            data.forEach(order => {
                const orderItem = document.createElement('div');
                orderItem.classList.add('order-item');
                orderItem.innerHTML = `
          <strong>Номер заказа:</strong> ${order.id}<br>
          <strong>Наименование:</strong> ${order.name}<br>
          <strong>Сумма:</strong> ${order.price}<br>
          <strong>Статус:</strong> ${order.status}
        `;
                orderContainer.appendChild(orderItem);
            })
        })
}

function getInfo() {
    getUserBaseData()
        .then(data => {
            document.getElementById("userName").value = data.name;
            document.getElementById("userEmail").value = data.email;
            document.getElementById("userAddress").value = data.address;
        })
}

function updateInfo() {
    let name = updateForm.name.value;
    let email = updateForm.email.value;
    let address = updateForm.address.value;
    updateUserInfo(name, email, address)
        .then(data => {
            if (data.success) {
                showNotification(data.message, 2000, 'notification')
                return;
            }
            showNotification(data.message, 2000, 'notification-auth')
        })
}

saveButton.addEventListener('click', function (event) {
    event.preventDefault();
    updateInfo();
})

logoutButton.addEventListener('click', function (event) {
    event.preventDefault();
    window.location.assign("/logout")
})
