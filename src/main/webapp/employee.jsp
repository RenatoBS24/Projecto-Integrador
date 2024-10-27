<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chichos Snack</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex min-h-screen">

<%
    if(request.getAttribute("employeeList") != null){
        List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
%>

<!-- Sidebar -->
<div class="bg-teal-600 w-64 h-screen text-white p-6">
    <h1 class="text-2xl font-bold mb-10">CHICHOS SNACK</h1>
    <ul>
        <li class="mb-6">
            <a class="flex items-center space-x-4 p-2 rounded-lg bg-gradient-to-r from-green-400 to-teal-500">
                    <span>
                        <ion-icon name="menu-outline" class="text-xl"></ion-icon>
                    </span>
                <span class="text-lg font-semibold">Menú</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="../ChichosSnack/Inicio.html" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="cash-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Ventas</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="../ChichosSnack/productos.html" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="pricetag-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Productos</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="../ChichosSnack/empleados.html" class="flex items-center space-x-4 p-2 rounded-lg bg-teal-500">
                    <span>
                        <ion-icon name="people-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Empleados</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="../ChichosSnack/ajustes.html" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="settings-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Ajustes</span>
            </a>
        </li>
        <li class="mt-16">
            <a href="../ChichosSnack/IniciarSesion.html" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="log-out-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Salir</span>
            </a>
        </li>
    </ul>
</div>

<!-- Main Content -->
<main class="flex-1 p-6">
    <!-- Header -->
    <div class="flex justify-between items-center mb-8">
        <div class="relative">
            <input type="text" placeholder="Search Here" class="p-2 border border-gray-300 rounded-lg focus:outline-none w-full lg:w-96">
            <span class="absolute right-2 top-2 text-gray-400">
                    <ion-icon name="search-outline"></ion-icon>
                </span>
        </div>
        <div class="flex items-center space-x-4">
            <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">+ Agregar productos</button>
            <div class="relative">
                <a href="notificaciones.html">
                    <ion-icon name="notifications-outline" class="text-2xl text-gray-600"></ion-icon>
                    <span class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">1</span>
                </a>
            </div>
        </div>
    </div>

    <!-- Trabajadores -->
    <section class="mt-10">
        <h1 class="text-3xl font-bold mb-6">Trabajadores</h1>
        <div class="space-y-6">
            <!-- Trabajador -->
            <%
                for(Employee employee:employeeList){

            %>
            <div class="bg-teal-100 p-4 rounded-lg flex items-center justify-between">
                <div class="flex items-center space-x-4 cursor-pointer" onclick="openWorkerReport('Juan', 'Pérez', 35, 87654321, 912345678, 1200)">
                    <img src="https://via.placeholder.com/100" alt="Empleado" class="rounded-full w-16 h-16 object-cover">
                    <div>
                        <p class="text-lg font-semibold">Nombre: <%=employee.getName()%></p>
                        <p class="text-sm text-gray-600">DNI: <%=employee.getDni()%></p>
                        <p class="text-sm text-gray-600">Sueldo: <%=employee.getSalary()%></p>
                    </div>
                </div>
                <div class="flex space-x-2">
                    <button class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600">Eliminar</button>
                    <button onclick="openModal('<%=employee.getName()%>','<%=employee.getLastname()%>','<%=employee.getDni()%>','<%=employee.getPhone()%>','<%=employee.getSalary()%>','<%=employee.getId_employee()%>')" class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600">Editar</button>
                </div>
            </div>
            <%
                }
            %>
            <!-- Repetir estructura para más trabajadores -->
        </div>
    </section>
</main>

<!-- Modal (Ventana Emergente para Editar) -->
<div id="modal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden">
    <div class="bg-blue-100 w-1/3 rounded-lg shadow-lg p-5 relative">
        <h2 class="text-xl font-bold mb-4 text-center">Ingresar información de trabajador</h2>
        <div class="flex justify-center mb-4">
            <div class="relative">
                <img src="https://via.placeholder.com/100" alt="Empleado" class="rounded-full w-24 h-24 object-cover">
                <span class="absolute bottom-0 right-0 bg-gray-300 p-2 rounded-full cursor-pointer">
                        <ion-icon name="camera-outline"></ion-icon>
                    </span>
            </div>
        </div>
        <form action="UpdateEmployee" method="POST">
            <input type="hidden" name="id" id="id_employee">
            <div class="mb-4">
                <label class="block text-gray-700">Nombre:</label>
                <input type="text" class="w-full p-2 border rounded-lg focus:outline-none"  id="name" name="name">
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Apellidos:</label>
                <input type="text" class="w-full p-2 border rounded-lg focus:outline-none"  id="lastname" name="lastname">
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">DNI:</label>
                <input type="number" class="w-full p-2 border rounded-lg focus:outline-none"  id="dni" name="dni">
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Telefono:</label>
                <input type="text" class="w-full p-2 border rounded-lg focus:outline-none"  id="phone" name="phone">
            </div>
            <div class="mb-4">
                <label class="block text-gray-700">Sueldo:</label>
                <input type="number" class="w-full p-2 border rounded-lg focus:outline-none" id="salary" name="salary">
            </div>
            <div class="flex justify-between">
                <button type="button" onclick="closeModal()" class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600">Cancelar</button>
                <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600">Guardar</button>
            </div>
        </form>
    </div>
</div>

<!-- Modal Reporte -->
<div id="workerReportModal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden">
    <div class="bg-white w-1/3 rounded-lg shadow-lg p-5">
        <h2 class="text-xl font-bold mb-4 text-center">Reporte de Trabajador</h2>
        <div class="flex justify-center mb-4">
            <img src="https://via.placeholder.com/100" alt="Empleado" class="rounded-full w-24 h-24 object-cover">
        </div>
        <div>
            <p class="text-lg"><strong>Nombre:</strong> <span id="reportNombre"></span></p>
            <p class="text-lg"><strong>Apellidos:</strong> <span id="reportApellidos"></span></p>
            <p class="text-lg"><strong>Edad:</strong> <span id="reportEdad"></span></p>
            <p class="text-lg"><strong>DNI:</strong> <span id="reportDni"></span></p>
            <p class="text-lg"><strong>Teléfono:</strong> <span id="reportTelefono"></span></p>
            <p class="text-lg"><strong>Sueldo:</strong> $<span id="reportSueldo"></span></p>
        </div>
        <div class="flex justify-end mt-4">
            <button onclick="closeWorkerReport()" class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600">Cerrar</button>
        </div>
    </div>
</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script>
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
    function openWorkerReport(nombre, apellidos, edad, dni, phone, sueldo) {
        document.getElementById('reportNombre').innerText = nombre;
        document.getElementById('reportApellidos').innerText = apellidos;
        document.getElementById('reportEdad').innerText = edad;
        document.getElementById('reportDni').innerText = dni;
        document.getElementById('reportTelefono').innerText = phone;
        document.getElementById('reportSueldo').innerText = sueldo;
        document.getElementById('workerReportModal').classList.remove('hidden');
    }

    // Función para cerrar el modal del reporte del trabajador
    function closeWorkerReport() {
        document.getElementById('workerReportModal').classList.add('hidden');
    }
</script>
</body>
</html>

<%
    }else{
        response.sendRedirect("Employee");
    }
%>