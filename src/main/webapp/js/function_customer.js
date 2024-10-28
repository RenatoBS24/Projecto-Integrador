// Función para abrir el modal de edición del cliente
function openModal1(name,phone,amount_total,amount_available,amount_used,id_customer) {
    document.getElementById('name').value = name;
    document.getElementById('phone').value = phone;
    document.getElementById('amount_total').value = amount_total;
    document.getElementById('amount_available').value = amount_available;
    document.getElementById('amount_used').value = amount_used;
    document.getElementById('id_customer').value = id_customer;
    document.getElementById('modal').classList.remove('hidden');
}

// Función para cerrar el modal de edición del cliente
function closeModal() {
    document.getElementById('modal').classList.add('hidden');
}

// Función para abrir el modal del reporte del cliente
function openWorkerReport1(nombre, apellidos, edad, dni, telefono, sueldo) {
    document.getElementById('reportNombre').innerText = nombre;
    document.getElementById('reportApellidos').innerText = apellidos;
    document.getElementById('reportEdad').innerText = edad;
    document.getElementById('reportDni').innerText = dni;
    document.getElementById('reportTelefono').innerText = telefono;
    document.getElementById('reportSueldo').innerText = sueldo;
    document.getElementById('workerReportModal').classList.remove('hidden');
}

// Función para cerrar el modal del reporte del cliente
function closeWorkerReport() {
    document.getElementById('workerReportModal').classList.add('hidden');
}

// Funciones para el modal delete
function openModalDelete(id_customer) {
    document.getElementById('id').value = id_customer;
    document.getElementById('deleteClientModal').classList.remove('hidden');
}

function closeModalDelete() {
    document.getElementById('deleteClientModal').classList.add('hidden');
}
// Funciones para el modal create
function openModalCustomer() {
    document.getElementById('clientModal').classList.remove('hidden');
}

function closeModalCustomer() {
    document.getElementById('clientModal').classList.add('hidden');
}



