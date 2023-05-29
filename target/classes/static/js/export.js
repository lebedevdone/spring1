const url = "http://localhost:8081/v2/api"

export function showNotification(message, duration, div) {
    const notificationElement = document.getElementById(div);
    notificationElement.innerText = message;
    notificationElement.style.display = 'block';

    setTimeout(() => {
        notificationElement.style.display = 'none';
    }, duration);
}
export function showModal() {
    const modal = document.getElementById('modal');
    const modalMessage = document.getElementById('modal-message');
    modalMessage.textContent = 'Необходимо авторизоваться или зарегистрироваться';
    modal.style.display = 'block';
}

export function getUserData() {
    return fetch(url + '/user/info', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(response => response.json())
}

export function getUserBaseData() {
    return fetch(url + '/user/base/info', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(response => response.json())
}

export function createUserOrder() {
    return fetch(url + '/user/order/create', {
        method: 'POST'
    }).then(response => {
        return response.ok;
    })
}

export function removeItemFromUserCart(productId) {
    return fetch(url + '/user/cart/delete/' + productId, {
        method: 'DELETE'
    }).then(response => {
        return response.ok;
    })
}

export function getCurrentUserOrders() {
    return fetch(url + '/user/order/find/all',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
}

export function getProductData(id) {
    return fetch(url + `/products/` + id,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
}

export function addProductToUserCart(productId) {
    return fetch(url + '/user/common/cart/add/' + productId, {
        method: 'PUT'
    })
        .then(response => {
            if (response.ok) {
                showNotification('Товар добавлен в корзину', 2000, 'notification');
                return response.ok;
            } else {
                return false;
            }
        });
}

export function getAllProducts(sort, page) {
    return fetch(url + '/products/all?page=' + page + '&sort=' + sort,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
}

export function getProductsByName(searchQuery) {
    return fetch(url + '/products/find/' + searchQuery, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    }).then(response => response.json())
}

export function registerUser(username, password, name, email, address) {
    return fetch(url + '/user/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            name: name,
            email: email,
            address: address
        })
    }).then(response => response.json())
}

export function updateUserInfo(name, email, address) {
    return fetch(url + '/user/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            email: email,
            address: address
        })
    }).then(response => response.json())
}