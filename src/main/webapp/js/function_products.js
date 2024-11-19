// Function to open the modal
function openModal() {
    document.getElementById('productModal').classList.remove('hidden');
}

// Function to close the modal
function closeModal() {
    document.getElementById('productModal').classList.add('hidden');
}
function openEditModal(id,name,price,stocks,category,unit,data) {

    document.getElementById('editProductName').value = name;
    document.getElementById('editProductPrice').value = price;
    document.getElementById('ProductStock').textContent = stocks;
    document.getElementById('id_product').value = id;
    const select  =document.getElementById('editLotNumber');
    select.innerHTML = "";
    //-------------------------------------------
    const text = data.replace(/'/g, '"');
    const json = JSON.parse(text);
    console.log(json);
    if (json.length > 0) {
        document.getElementById('categories').value = json[0]["product"].category.id_category;
        document.getElementById('units').value = json[0]["product"].unitOfMeasurement.id_unit_of_measurement;
        document.getElementById('editExpiryDate').value = formatDate(json[0].expiration_date);
        console.log(formatDate(json[0].expiration_date))
        document.getElementById('editBuyDate').value = formatDate(json[0].purchase_date);
        document.getElementById('editPurchasePrice').value = json[0].purchase_price;
        document.getElementById('editProductStock').value = json[0].stock;
        json.forEach((item) => {
            const option = document.createElement("option")
            option.value = item.id_inventory;
            option.text = item.lot;
            option.setAttribute("data-expire", formatDate(item.expiration_date));
            option.setAttribute("data-purchase", formatDate(item.purchase_date));
            option.setAttribute("data-price", item.purchase_price);
            option.setAttribute("data-stock", item.stock);
            select.appendChild(option);
        });
    } else {
        document.getElementById('categories').value = category;
        document.getElementById('units').value = unit;
        document.getElementById('editExpiryDate').value = ""
        document.getElementById('editBuyDate').value = ""
        document.getElementById('editPurchasePrice').value = ""
        document.getElementById('editProductStock').value = ""
    }
    document.getElementById('editProductModal').classList.remove('hidden');

}
function formatDate(dateString) {
    const months = {
        'ene': 'Jan', 'feb': 'Feb', 'mar': 'Mar', 'abr': 'Apr', 'may': 'May', 'jun': 'Jun',
        'jul': 'Jul', 'ago': 'Aug', 'sep': 'Sep', 'oct': 'Oct', 'nov': 'Nov', 'dic': 'Dec'
    };
    const [month, day, year] = dateString.split(' ');
    const englishDateString = `${months[month.toLowerCase()]} ${day.replace(',', '')}, ${year}`;
    const date = new Date(englishDateString);
    const formattedYear = date.getFullYear();
    const formattedMonth = String(date.getMonth() + 1).padStart(2, '0');
    const formattedDay = String(date.getDate()).padStart(2, '0');
    return `${formattedYear}-${formattedMonth}-${formattedDay}`;
}


function change(){
    const select  =document.getElementById('editLotNumber');
    const selectedOption = select.options[select.selectedIndex];
    const expire = selectedOption.getAttribute('data-expire');
    const purchase = selectedOption.getAttribute('data-purchase');
    const price = selectedOption.getAttribute('data-price');
    const stock = selectedOption.getAttribute("data-stock");
    const id = selectedOption.getAttribute("data_id");
    document.getElementById('editExpiryDate').value = expire;
    document.getElementById('editBuyDate').value = purchase;
    document.getElementById('editPurchasePrice').value = price;
    document.getElementById('id_inventory').value = id;
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


// open modal delete

function openDeleteModal(id) {
    document.getElementById('deleteProductModal').classList.remove('hidden');
    document.getElementById('id').value = id;
}

function  closeDeleteModal(){
    document.getElementById('deleteProductModal').classList.add('hidden');
}

function openReportModal(){
    let modal = document.getElementById('reportModal');
    modal.style.display = 'flex';
    document.body.classList.add('no-scroll','dimmed');
}
function closeReportModal(){
    let modal = document.getElementById('reportModal');
    modal.style.display = 'none';
    document.body.classList.remove('no-scroll','dimmed');
}
function categoryFilter(){
    document.getElementById('filter').addEventListener('change', function() {
        let filter = this.value;
        let tabla = document.getElementsByTagName('table')[0];
        let rows = tabla.getElementsByTagName('tr');
        console.log(filter)
        for (let i = 1; i < rows.length; i++) {
            let categorySelected = rows[i].getElementsByTagName('td')[4].textContent.trim();

            if (filter === '0' || categorySelected === filter)  {
                rows[i].style.display = '';
            } else {
                rows[i].style.display = 'none';
            }
        }
    });
}