// Function to open the modal
function openModal() {
    document.getElementById('productModal').classList.remove('hidden');
}

// Function to close the modal
function closeModal() {
    document.getElementById('productModal').classList.add('hidden');
}
function openEditModal(name,price,stocks,data) {

    document.getElementById('editProductName').value = name;
    document.getElementById('editProductPrice').value = price;
    document.getElementById('ProductStock').textContent = stocks;
    const select  =document.getElementById('editLotNumber');
    select.innerHTML = "";
    //-------------------------------------------
    const text = data.replace(/'/g, '"');
    const json = JSON.parse(text);
    console.log(json);
    document.getElementById('categories').value = json[0]["product"].category.id_category;
    document.getElementById('editExpiryDate').value = formatDate(json[0].expiration_date);
    document.getElementById('editBuyDate').value = formatDate(json[0].purchase_date);
    document.getElementById('editPurchasePrice').value =json[0].purchase_price;
    document.getElementById('editProductStock').value = json[0].stock;
    json.forEach((item) => {
        const option = document.createElement("option")
        option.value = item.id_inventory;
        option.text = item.lot;
        option.setAttribute("data-expire",formatDate(item.expiration_date));
        option.setAttribute("data-purchase",formatDate(item.purchase_date));
        option.setAttribute("data-price",item.purchase_price);
        option.setAttribute("data-stock",item.stock);
        select.appendChild(option);
    })
    document.getElementById('editProductModal').classList.remove('hidden');

}
function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}


function change(){
    const select  =document.getElementById('editLotNumber');
    const selectedOption = select.options[select.selectedIndex];
    const expire = selectedOption.getAttribute('data-expire');
    const purchase = selectedOption.getAttribute('data-purchase');
    const price = selectedOption.getAttribute('data-price');
    const stock = selectedOption.getAttribute("data-stock");
    document.getElementById('editExpiryDate').value = expire;
    document.getElementById('editBuyDate').value = purchase;
    document.getElementById('editPurchasePrice').value = price;
    console.log(stock)
    document.getElementById('editProductStock').value = stock;
}

function closeEditModal() {
    document.getElementById('editProductModal').classList.add('hidden');
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