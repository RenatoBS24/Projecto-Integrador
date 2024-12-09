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
function openReportModal(){
    document.getElementById('reportModal').style.display = 'flex';
}

function closeReportModal(){
    document.getElementById('reportModal').style.display = 'none';
}
document.addEventListener('DOMContentLoaded', function() {
    function productFilter(){
        document.getElementById('filterProduct').addEventListener('change', function() {
            applyFilters();
        });
    }
    productFilter();
});document.addEventListener('DOMContentLoaded', function() {
    function dateFilter(){
        document.getElementById('filterStart').addEventListener('change', function() {
            applyFilters();
        });
    }
    dateFilter();
});document.addEventListener('DOMContentLoaded', function() {
    function dateEndFilter(){
        document.getElementById('filterEnd').addEventListener('change', function() {
            applyFilters();
        });
    }
    dateEndFilter();
});
function applyFilters() {
    let productFilter = document.getElementById('filterProduct').value;
    let startDateFilter = new Date(document.getElementById('filterStart').value);
    let endDateFilter = new Date(document.getElementById('filterEnd').value);
    let tabla = document.getElementsByTagName('table')[0];
    let rows = tabla.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        let productSelected = rows[i].getElementsByTagName('td')[6].textContent.trim();
        let dateSelected = new Date(rows[i].getElementsByTagName('td')[3].textContent.trim());
        let productMatch = (productFilter === '0' || productSelected === productFilter);
        let startDateMatch = (isNaN(startDateFilter.getTime()) || dateSelected >= startDateFilter);
        let endDateMatch = (isNaN(endDateFilter.getTime()) || dateSelected <= endDateFilter);

        if (productMatch && startDateMatch && endDateMatch) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }
}
