import {addProductToUserCart, showModal, showNotification} from "./export.js";

const url = 'http://localhost:8081/v1/api'

window.onload = () => {
    fillUsersTable()
    fillProductsTable()
    fillRoles()
    fetchOrderData()
}

const deleteOrderButton = document.getElementById('buttonDeleteOrder')
const newUserForm = document.getElementById('formNewUser')
const createProductButton = document.getElementById('createProduct')
const newUserButton = document.getElementById('createNewUser')
const updateUserButton = document.getElementById('editUser')
const closeModalCreateProduct = document.getElementById('closeModalCreateProduct')
const deleteUserButton = document.getElementById('deleteUserButton')
const closeDeleteModalButton = document.getElementById('closeModalDeleteProduct')
const closeEditModalButton = document.getElementById('closeModalUpdateProduct')
const updateProductButton = document.getElementById('updateProduct')
const deleteProductButton = document.getElementById('deleteProduct')
const updateOrderButton = document.getElementById('updateOrder')
const modalUpdateForm = document.getElementById('modalEditForm')
const modalCreateProductForm = document.getElementById('modalCreateProductForm')
const modalDeleteProductForm = document.getElementById('modalDeleteProductForm')
const modalUpdateProductForm = document.getElementById('modalUpdateProductForm')
const modalUpdateOrderForm = document.getElementById('modalUpdateOrderForm')
const orderTableBody = document.getElementById('currentOrderTableBody')
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');


function fillUsersTable() {
    const allUsersTableBody = document.getElementById('allUsersTableBody')
    $('#allUsersTableBody').empty()
    fetch(url + '/admin/all',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
        .then(data => {
            let columnContent = ''
            data.forEach(element => {
                columnContent += `<tr>
                    <td>${element.id}</td>
                    <td>${element.name}</td>
                    <td>${element.email}</td>
                    <td>${element.username}</td>
                    <td>${element.roles.map(role => role.name.substring(5))}</td>
                    <td>
                      <button type="button" id="buttonEdit" class="btn btn-info" data-bs-toggle="modal"
                       data-index="${element.id}"  data-bs-target="#modalEdit">Редактировать</button>
                    </td>
                    
                    <td>
                     <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-index="${element.id}"
                        data-bs-target="#modalDelete" id="buttonDelete">Удалить</button>
                    </td>
                    <td>
                    </td>
                </tr>
                `
            })
            allUsersTableBody.innerHTML = columnContent;

        })
}

function fillProductsTable() {
    const allUsersTableBody = document.getElementById('currentUserTableBody')
    $('#currentUserTableBody').empty()
    fetch(url + '/admin/find/product/all',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
        .then(data => {
            let columnContent = ''
            data.forEach(element => {
                columnContent += `<tr>
                    <td>${element.id}</td>
                   <td><img src="${element.imageUrl}" style=" height: 50px; width: 50px;"></td>
                    <td>${element.name}</td>
                    <td>${element.price}</td>
                    <td>
                      <button type="button" id="buttonEditProduct" class="btn btn-info" data-bs-toggle="modal"
                       data-index="${element.id}"  data-bs-target="#modalUpdateProduct">Редактировать</button>
                    </td>
                    <td>
                     <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-index="${element.id}"
                        data-bs-target="#modalDeleteProduct" id="buttonDeleteProduct">Удалить</button>
                    </td>
                    <td>
                    </td>
                </tr>
                `
            })
            allUsersTableBody.innerHTML = columnContent;

        })
}

function fillOrderTable(data) {
    let columnContent = ''
    data.forEach(element => {
        columnContent += `<tr>
                    <td>${element.id}</td>
                    <td>${element.name}</td>
                    <td>${element.address}</td>
                    <td>${element.price}</td>
                    <td>${element.status}</td>
                   <td>${element.productName.join('<br>')}</td>
                    <td>
                      <button type="button" id="buttonEdit" class="btn btn-info" data-bs-toggle="modal"
                       data-index="${element.id}"  data-bs-target="#modalUpdateOrder">Редактировать</button>
                    </td>
                    <td>
                     <button type="button" class="btn btn-danger orderDeleteButton" data-index="${element.id}"
                         id="buttonDeleteOrder">Удалить</button>
                    </td>
                    <td>
                    </td>
                </tr>
                `
    })
    orderTableBody.innerHTML = columnContent;
    const deleteOrdersButtons = document.querySelectorAll('.orderDeleteButton');
    deleteOrdersButtons.forEach((deleteButton, index) => {
        const id = deleteButton.dataset.index;
        deleteButton.addEventListener('click', () => {
            deleteOrder(id)
        })
    })

}

function fetchOrderData() {
    fetch(url + '/admin/order/find/all',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
        .then(data => {
            fillOrderTable(data)
        })
}

function searchOrder(char) {
    fetch(url + '/admin/order/find?characters=' + char ,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
        .then(data => {
            fillOrderTable(data)
        })
}

function deleteOrder(id) {
    fetch(url + '/admin/order/delete/' + id,{
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            showNotification('Товар удален', 2000, 'notification-auth')
            fetchOrderData()
        }
    })
}

function createNewUser() {
    let name = newUserForm.name.value;
    let email = newUserForm.name.value;
    let username = newUserForm.name.value;
    let password = newUserForm.password.value;
    let address = newUserForm.name.value;
    let roles = []
    for (let option of document.getElementById('newUserRoles').options) {
        if (option.selected) {
            roles.push({
                id: option.value, name: option.innerText
            })
        }
    }

    fetch(url + '/admin/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            email: email,
            address: address,
            password: password,
            username: username,
            roles: roles
        })
    })
        .then(result => {
            if (result.ok) {
                newUserForm.reset()
                fillUsersTable()
                $('.nav-tabs a[href="#UserTable"]').tab('show')
            }
        })
}

function getAllRoles() {
    return fetch(url + '/admin/role/find',{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
}

function fillRoles() {
    let roles = document.getElementById('newUserRoles');
    getAllRoles()
        .then(data => {
            let rolesData = '';
            data.forEach(el => {
                rolesData += `
                    <option value='${el.id}' selected>
                    ${el.name.substring(5)}
                    </option>
                    `
            })
            roles.innerHTML = rolesData;
        })
}

function fillUserForm(id, formName, method) {
    fetch(url + '/admin/find/user/' + id,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
        .then(data => {
            let roles = document.getElementById('roles' + method)
            let rolesList = [];
            formName.id.value = data.id;
            formName.name.value = data.name;
            formName.email.value = data.email;
            formName.address.value = data.address;
            data.roles.forEach(role => {
                rolesList.push(role.id)
            })
            getAllRoles()
                .then(data => {
                    const selected = rolesList.includes(data.id) ? "selected" : "";
                    let resRoles;
                    data.forEach(el => {
                        resRoles += `
                            <option value='${el.id}' ${selected}>
                            ${el.name.substring(5)}
                            </option>
                                `;
                    })
                    roles.innerHTML = resRoles;
                })
        })
}

function updateCurrentUser() {
    let editUserRoles = []
    for (let option of document.getElementById('rolesEdit').options) {
        if (option.selected) {
            editUserRoles.push({
                id: option.value, name: option.innerText
            })
        }
    }
    fetch(url + '/admin/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: modalUpdateForm.id.value,
            name: modalUpdateForm.name.value,
            email: modalUpdateForm.email.value,
            address: modalUpdateForm.address.value,
            roles: editUserRoles
        })
    }).then(response => {
        if (response.ok) {
            fillUsersTable()
            document.getElementById('closeEditModalWindow').click()
            $('.nav-tabs a[href="#UserTable"]').tab('show')
        }
    })
}

function deleteCurrentUser(id) {
    fetch(url + '/admin/delete/' + id, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            fillUsersTable()
            document.getElementById('close').click()
            $('.nav-tabs a[href="#UserTable"]').tab('show')
        }
    })
}

function fillEditProductModal(id) {
    fetchProductById(id)
        .then(data => {
            modalUpdateProductForm.id.value = data.id;
            modalUpdateProductForm.name.value = data.name;
            modalUpdateProductForm.price.value = data.price;
            modalUpdateProductForm.description.value = data.description;
            modalUpdateProductForm.imageUrl.value = data.imageUrl;
        })
}

function fillDeleteProductModal(id) {
    fetchProductById(id)
        .then(data => {
            modalDeleteProductForm.name.value = data.name;
            modalDeleteProductForm.price.value = data.price;
            modalDeleteProductForm.description.value = data.description;
            modalDeleteProductForm.imageUrl.value = data.imageUrl;
        })
}

function fetchProductById(id) {
    return fetch(url + '/admin/product/find/' + id,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }}).then(response => response.json())
}

function updateProduct() {
    fetch(url + '/admin/product/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
                id: modalUpdateProductForm.id.value,
                name: modalUpdateProductForm.name.value,
                price: modalUpdateProductForm.price.value,
                description: modalUpdateProductForm.description.value,
                imageUrl: modalUpdateProductForm.imageUrl.value
            })
    }).then(response => {
        if (response.ok) {
            closeEditModalButton.click()
            fillProductsTable()
            history.replaceState(null, '', window.location.origin + window.location.pathname);
        }
    })
}

function deleteProduct(id) {
    fetch(url + '/admin/product/delete/' + id, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            closeDeleteModalButton.click()
            fillProductsTable()
        } else {
            showNotification('Невозможно удалить товар, товар в заказе', 2000, 'notification-auth')
        }
    })
}

function createProduct() {
    fetch(url + '/admin/product/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: modalCreateProductForm.name.value,
            description: modalCreateProductForm.description.value,
            price: modalCreateProductForm.price.value,
            imageUrl: modalCreateProductForm.imageUrl.value
        })
    })
        .then(response => {
            if (response.ok) {
                fillProductsTable();
                closeModalCreateProduct.click()
            }
        })
}

function fillUpdateOrderModal(id) {
    fetch(url + '/admin/order/finds/' + id,{
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(response => response.json())
        .then(data => {
            let resRoles = '';
            resRoles +=
                `
         <option value="0">Новый</option>
         <option value="1">Обрабатывается</option>
         <option value="2">Отправлен</option>
         <option value="3">Выполнен</option>
         <option value="4">Отменен</option>
        `
            modalUpdateOrderForm.id.value = data.id;
            modalUpdateOrderForm.name.value = data.name;
            modalUpdateOrderForm.price.value = data.price;
            modalUpdateOrderForm.address.value = data.address;
            modalUpdateOrderForm.status.innerHTML = resRoles;

        })
}

function updateOrder() {
    let currentOption = '';
    for (let option of document.getElementById('status').options) {
        if (option.selected) {
            currentOption = option.getAttribute('value')
        }
    }
    fetch(url + '/admin/order/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: modalUpdateOrderForm.id.value,
            orderStatus: currentOption
        })
    })
        .then(response => {
            if (response.ok) {
                document.getElementById('closeModalUpdateOrder').click()
                fetchOrderData()
            }
        })
}


$(document).ready(() => {
    $('#modalEdit').off().on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        fillUserForm(id, document.forms['modalEditForm'], 'Edit')
    })

    $('#modalDelete').on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        fillUserForm(id, document.forms['modalDeleteForm'], 'Delete')
        deleteUserButton.addEventListener('click', function (event) {
            event.preventDefault()
            deleteCurrentUser(id)
        })
    })

    $('#modalUpdateProduct').off().on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        console.log(id)
        fillEditProductModal(id)
        updateProductButton.addEventListener('click', function (event) {
            event.preventDefault()
            updateProduct(id)
        })
    })

    $('#modalUpdateOrder').off().on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        fillUpdateOrderModal(id)
        updateOrderButton.addEventListener('click', function (event) {
            event.preventDefault()
            updateOrder()
        })
    })

    $('#modalDeleteProduct').off().on('show.bs.modal', event => {
        let id = $(event.relatedTarget).attr("data-index")
        fillDeleteProductModal(id)
        deleteProductButton.addEventListener('click', function (event) {
            event.preventDefault()
            deleteProduct(id)
        })
    })
})
newUserButton.addEventListener('click', function (event) {
    event.preventDefault();
    createNewUser();
})
updateUserButton.addEventListener('click', function (event) {
    updateCurrentUser()
})

createProductButton.addEventListener('click', function (event) {
    event.preventDefault()
    createProduct()
})




searchForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const searchQuery = searchInput.value;
    searchOrder(searchQuery)
})
searchForm.addEventListener('input', () => {
    if (searchInput.value === '') {
        fetchOrderData()
    }
})









