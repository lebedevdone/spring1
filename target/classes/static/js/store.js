import {addProductToUserCart, getAllProducts, getProductsByName, showModal} from "./export.js";

window.onload = () => {
    fillProductTable('name')
}

const selectSort = document.querySelector('.input-select');
const urlParams = new URLSearchParams(window.location.search);
const paging = document.getElementById('paginationContainer')
const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
let page = urlParams.get('page');

if (page === null) {
    page = 1;
}

function fillProductTable(sort) {
    getAllProducts(sort, page)
        .then(data => {
            fill(data)
        })
}
function fill(response) {
    const productTable = document.getElementById('productTable');
    while (productTable.firstChild) {
        productTable.removeChild(productTable.firstChild)
    }
    $('#productTable').empty();
    nextPage(response.totalPages)
    let columnContent = '';
    response.content.forEach(element => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('col-md-4', 'col-xs-6');

        const product = document.createElement('div');
        product.classList.add('product');

        const productImg = document.createElement('div');
        productImg.classList.add('product-img');
        const img = document.createElement('img');
        img.src = element.imageUrl;
        img.alt = element.name;
        productImg.appendChild(img);

        const productBody = document.createElement('div');
        productBody.classList.add('product-body');
        const productName = document.createElement('h3');
        productName.classList.add('product-name');
        const productLink = document.createElement('span');
        productLink.textContent = element.name;
        productLink.style.cursor = 'pointer'; // Добавляем указатель мыши, чтобы подчеркнуть возможность клика
        productName.appendChild(productLink);

        const productPrice = document.createElement('h4');
        productPrice.classList.add('product-price');
        productPrice.textContent = element.price;
        productBody.appendChild(productName);
        productBody.appendChild(productPrice);

        const addToCart = document.createElement('div');
        addToCart.classList.add('add-to-cart');
        const addToCartBtn = document.createElement('button');
        addToCartBtn.classList.add('add-to-cart-btn');
        addToCartBtn.innerHTML = '<i class="fa fa-shopping-cart"></i>В Корзину';
        addToCart.appendChild(addToCartBtn);

        product.appendChild(productImg);
        product.appendChild(productBody);
        product.appendChild(addToCart);
        productDiv.appendChild(product);


        columnContent += productDiv.outerHTML;
    })
    productTable.innerHTML = columnContent;


    const productLinks = document.querySelectorAll('.product-name span');
    productLinks.forEach((productLink, index) => {
        const productId = response.content[index].id;
        productLink.addEventListener('click', () => {
            const currentUrl = new URL('/product', window.location.href);
            currentUrl.searchParams.set('id', productId);
            window.location.href = currentUrl.href;
        });
    });


    const productButton = document.querySelectorAll('.add-to-cart-btn');
    productButton.forEach((productButton, index) => {
        const productId = response.content[index].id;
        productButton.addEventListener('click', () => {
            addProductToUserCart(productId)
                .then(data => {
                    if (!data) {
                        showModal()
                    }
                })

        })
    })
}
function nextPage(totalPages) {
    while (paging.firstChild) {
        paging.removeChild(paging.firstChild);
    }
    for (let i = 1; i <= totalPages; i++) {
        const li = document.createElement("li");
        const a = document.createElement("a");
        a.href = `/?page=${i}`;
        a.textContent = i;
        li.appendChild(a);
        paging.appendChild(li);
        // Добавьте обработчик события на нажатие кнопки
        a.addEventListener("click", function (event) {
            event.preventDefault(); // Предотвратите переход по ссылке
            window.location.href = this.getAttribute("href");
        });


    }
}
document.getElementById('closeButton').addEventListener("click", () => {
    document.getElementById('modal').style.display = 'none';
})
selectSort.addEventListener('change', function () {
    const selectedOption = this.value;

    switch (selectedOption) {
        case '0':
            fillProductTable('name')
            break;
        case '1':
            fillProductTable('price')
            break;
        default:
            break;
    }
});
searchForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const searchQuery = searchInput.value;
    getProductsByName(searchQuery)
        .then(data => {
            fill(data)
        })
})
searchForm.addEventListener('input', () => {
    if (searchInput.value === '') {
        fillProductTable('name');
    }
});


