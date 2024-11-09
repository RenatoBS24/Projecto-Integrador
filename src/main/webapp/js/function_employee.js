// Función para abrir el modal de edición del trabajador
function closeModal() {
    document.getElementById('modal').classList.add('hidden');
}


// Función para cerrar el modal de edición del trabajador
function openModal(nombre,apellidos,dni,phone,salario,id) {
    document.getElementById('name').value = nombre;
    document.getElementById('lastname').value = apellidos;
    document.getElementById('dni').value = dni;
    document.getElementById('phone').value = phone;
    document.getElementById('salary').value = salario;
    document.getElementById('id_employee').value = id;
    document.getElementById('modal').classList.remove('hidden');
}

// Función para abrir el modal del reporte del trabajador
function openWorkerReport(name, lastname, dni, phone, salary) {
    document.getElementById('reportNombre').innerText = name;
    document.getElementById('reportApellidos').innerText = lastname;
    document.getElementById('reportDni').innerText = dni;
    document.getElementById('reportTelefono').innerText = phone;
    document.getElementById('reportSueldo').innerText = salary;
    document.getElementById('workerReportModal').classList.remove('hidden');
}

// Función para cerrar el modal del reporte del trabajador
function closeWorkerReport() {
    document.getElementById('workerReportModal').classList.add('hidden');
}
// Funciones para el modal create
function openModalEmployee() {
    document.getElementById('clientModal').classList.remove('hidden');
}

function closeModalEmployee() {
    document.getElementById('clientModal').classList.add('hidden');
}


// Funciones para el modal delete
function openModalDelete(id_employee) {
    document.getElementById('id').value = id_employee;
    document.getElementById('deleteClientModal').classList.remove('hidden');
}

function closeModalDelete() {
    document.getElementById('deleteClientModal').classList.add('hidden');
}

