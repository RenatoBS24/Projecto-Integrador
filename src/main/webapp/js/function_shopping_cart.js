const cartItems = [];
const cartTotalElement = document.getElementById('cart-total');
const cartItemsElement = document.getElementById('cart-items');
const originalPriceElement = document.getElementById('original-price');
const discountedPriceElement = document.getElementById('discounted-price');
const initialCreditElement = document.getElementById('initial-credit');
const amountSpentElement = document.getElementById('amount-spent');
const remainingCreditElement = document.getElementById('remaining-credit');
let discount = 0;
let initialCredit = 100.00;
let amountSpent = 0.00;
function initializeCartItems(productHashMap) {
    for (const product of Object.values(productHashMap)) {
        cartItems.push({
            name: product.name,
            price: product.price,
            quantity: product.stock,
            id: product.id_product
        });
    }
    renderCart();
}

function addToCart(name, price,id) {
    const item = cartItems.find(item => item.name === name);
    console.log(id)
    if (item) {
        item.quantity++;
    } else {
        cartItems.push({ name, price, quantity: 1 ,id});
    }
    renderCart();
    add(id,price,name);
}

function renderCart() {
    const existingRows = cartItemsElement.querySelectorAll('.js-cart-item');
    existingRows.forEach(row => row.remove());
    cartItemsElement.innerHTML += cartItems.map(item => `
            <tr class="js-cart-item">
                <td class="py-2">${item.name}</td>
                <td class="py-2 flex items-center">
                    <button class="text-gray-500 hover:text-gray-700" onclick="updateQuantity('${item.name}', -1,${item.id})">-</button>
                    <span class="mx-2">${item.quantity}</span>
                    <button class="text-gray-500 hover:text-gray-700" onclick="updateQuantity('${item.name}', 1,${item.id})">+</button>
                </td>
                <td class="py-2">$${item.price}</td>
                <td class="py-2">$${(item.price * item.quantity)}</td>
                <td class="py-2">
                    <button class="text-red-500 hover:underline" onclick="removeFromCart('${item.name}',${item.id})">Eliminar</button>
                </td>
            </tr>
        `).join('');
    const total = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
    const discountedTotal = total - (total * discount / 100).toFixed(2);
    cartTotalElement.textContent = `$${discountedTotal}`;
}

function updateQuantity(name, change,id) {
    console.log('start');
    console.log(name);
    const item = cartItems.find(item => item.id === id);
    console.log(item);
    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) {
            removeFromCart(name);
            updateHash(0,id)
        } else {
            console.log('render')
            renderCart();
            updateHash(item.quantity,id)
        }
    }else{
        console.log('no item');
    }
}

function removeFromCart(name,id) {
    const index = cartItems.findIndex(item => item.name === name);
    if (index > -1) cartItems.splice(index, 1)
    updateHash(0,id);
    renderCart();
}

function checkout() {
    register();
    // Add your checkout logic here
}

function toggleCart() {
    const cartModal = document.getElementById('cart-modal');
    cartModal.classList.toggle('hidden');
}

function showCredit() {
    const check = document.getElementById('check');
    const value = document.getElementById('customer').value;
    const nameID = 'credit-info-'+value;

    if (check.checked) {
        document.getElementById(nameID).classList.remove('hidden');
    }else{
        document.getElementById(nameID).classList.add('hidden');
    }
}
function add(id,price,name){
    console.log(id);
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/addToCart?id_product=${id}&price=${price}&quantity=${1}&name=${name}`,{
        method: "GET",
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => response.text())
        .then(data => {
            console.log("Respuesta del servidor: ", data);
        })
        .catch(error => console.error("Error:", error));
}
function updateHash(quantity, id){
    console.log(quantity);
    console.log(id);
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/UpdateQuantity?id=${id}&quantity=${quantity}`,{
        method: "GET",
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => response.text())
        .then(data => {
            console.log("Respuesta del servidor: ", data);
        })
        .catch(error => console.error("Error:", error));
}

function register(){
    const id_employee = document.getElementById('employee').value;
    const id_customer = document.getElementById('customer').value;
    const amount = document.getElementById('cart-total').textContent;
    const check = document.getElementById('check');
    let confirm = false;
    if(check.checked){
        confirm = true;
    }
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/CreateSale?id_customer=${id_customer}&id_employee=${id_employee}&amount=${amount}&confirm=${confirm}`,{
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud al servidor');
            }
            return response.json();
        })
        .then(data => {
            console.log("Respuesta del servidor: ", data);
            if (data.redirect) {
                window.location.href = data.redirect;
            }
        })
        .catch(error => console.error("Error:", error));

}
// Function to switch categories
function showCategory(category) {
    // Ocultar todas las categorías
    document.querySelectorAll('.category').forEach(function (el) {
        el.classList.add('hidden');
    });

    // Mostrar solo la categoría seleccionada
    document.getElementById(category).classList.remove('hidden');

    // Cambiar la clase de borde en los botones
    document.querySelectorAll('.flex.space-x-4 button').forEach(function (btn) {
        btn.classList.remove('border-b-4', 'border-teal-500');
    });

    // Añadir la clase de borde al botón seleccionado
    document.getElementById(category + '-btn').classList.add('border-b-4', 'border-teal-500');
}