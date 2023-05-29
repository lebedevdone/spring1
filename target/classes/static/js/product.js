import {addProductToUserCart, getProductData, showModal} from "./export.js";

window.onload = () => {
    setData()
}

const productButton = document.getElementById('addToCartButton');
const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('id');

function setData() {
    getProductData(productId)
        .then(data => {
            let img = document.getElementById('productImage');
            document.title = data.name;
            document.getElementById('productName').textContent = data.name;
            img.src = data.imageUrl;
            img.alt = data.name;
            document.getElementById('productDescription').textContent = data.description;
            document.getElementById('productPrice').textContent = data.price;
        })

}

productButton.addEventListener('click', function () {
    addProductToUserCart(productId)
        .then(data => {
            if (!data) {
                showModal()
            }
        })
})
document.getElementById('closeButton').addEventListener("click", () => {
    document.getElementById('modal').style.display = 'none';
})

