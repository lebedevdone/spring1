import {registerUser, showNotification} from "./export.js";

const registerBtn = document.getElementById('registerButton');
const registerForm = document.getElementById('registerForm')

registerBtn.addEventListener('click', function (event) {
    event.preventDefault()
    let username = registerForm.username.value;
    let password = registerForm.password.value;
    let name = registerForm.name.value;
    let email = registerForm.email.value;
    let address = registerForm.address.value;
    registerUser(username, password, name, email, address)
        .then(data => {
            if (!data.success) {
                showNotification(data.message, 2000, 'notification-auth')
                return;
            }
            window.location.assign("/auth")
        })
});