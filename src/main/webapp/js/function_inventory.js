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
// Function to open the modal
function openModal() {
    document.getElementById('productModal').classList.remove('hidden');
}

// Function to close the modal
function closeModal() {
    document.getElementById('productModal').classList.add('hidden');
}

function openEditModal(lot,product,expiration_date,buy_date,price,stock,id){
    document.getElementById('editProductModal').classList.remove('hidden');
    document.getElementById('name_lot').textContent = lot;
    document.getElementById('name_product').textContent = product;
    document.getElementById('editExpiryDate').value = expiration_date;
    document.getElementById('editBuyDate').value = buy_date;
    document.getElementById('editPurchasePrice').value = price;
    document.getElementById('id_inventory').value = id;
    document.getElementById('editProductStock').value = stock;

}

function closeEditModal() {
    document.getElementById('editProductModal').classList.add('hidden');
}
document.getElementById('lot').addEventListener('input', function (e) {
    this.value = this.value.replace(/[^a-zA-Z0-9]/g, '');
});
function validateNumber(input) {
    input.value = input.value.replace(/[^0-9.]/g, '');
}
function validateAlphaNumeric(input) {
    input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
}

function openDeleteModal(id) {
    document.getElementById('deleteInventoryModal').classList.remove('hidden');
    document.getElementById('id').value = id;
}

function  closeDeleteModal(){
    document.getElementById('deleteProductModal').classList.add('hidden');
}