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
    HttpSession session1  = request.getSession();
    boolean is_valid_user = false;
    if(session1.getAttribute("is_valid_user") != null){
    if(request.getAttribute("employeeList") != null){
        List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
%>

<!-- Sidebar -->
<div class="bg-teal-600 w-64 fixed top-0 left-0 h-screen text-white p-6">
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
            <a href="index.jsp" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="cash-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Ventas</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="Products" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="pricetag-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Productos</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="Customer" class="flex items-center space-x-4 p-2 rounded-lg bg-teal-500">
                    <span>
                        <ion-icon name="people-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Clientes</span>
            </a>
        </li>

        <li class="mb-6">
            <a href="Employee" class="flex items-center space-x-4 p-2 rounded-lg bg-teal-500">
                    <span>
                        <ion-icon name="people-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Empleados</span>
            </a>
        </li>
        <li class="mb-6">
            <a href="settings.jsp" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="settings-outline" class="text-xl"></ion-icon>
                    </span>
                <span>Ajustes</span>
            </a>
        </li>
        <li class="mt-16">
            <form action="LogOut" method="POST">
                <button class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                        <span>
                            <ion-icon name="log-out-outline" class="text-xl"></ion-icon>
                        </span>
                    <span>Salir</span>
                </button>
            </form>
        </li>
    </ul>
</div>

<!-- Main Content -->
<main class="flex-1 p-6 ml-64">
    <!-- Header -->
    <div class="flex justify-between items-center mb-8 sticky top-0 bg-gray-100 z-10 p-4 shadow">
        <div class="relative">
            <input type="text" placeholder="Search Here" class="p-2 border border-gray-300 rounded-lg focus:outline-none w-full lg:w-96">
            <span class="absolute right-2 top-2 text-gray-400">
                    <ion-icon name="search-outline"></ion-icon>
                </span>
        </div>
        <div class="flex items-center space-x-4">
            <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600" onclick="openModalEmployee()">+ Agregar Trabajador</button>
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
                <input type="hidden" name="id" value=<%=employee.getId_employee()%>>
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
                        <button onclick="openModalDelete('<%=employee.getId_employee()%>')"  class=" boton bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600">Eliminar</button>
                        <button onclick="openModal('<%=employee.getName()%>','<%=employee.getLastname()%>','<%=employee.getDni()%>','<%=employee.getPhone()%>','<%=employee.getSalary()%>','<%=employee.getId_employee()%>')" class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600" type="button">Editar</button>
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
<div id="modal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
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
<!-- Modal for Adding Client -->
<div id="clientModal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
    <div class="bg-white w-1/3 p-6 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-4">Agregar Trabajador</h2>
        <form action="CreateEmployee" method="POST" enctype="multipart/form-data">
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Nombre: </label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="name" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Apellidos:</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="lastname" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">DNI:</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="dni" maxlength="8" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">N° de telefono:</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="phone" maxlength="9" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Salario:</label>
                <input type="number" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="salary" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Subir Archivo (Excel)</label>
                <input type="file" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="file" accept=".xlsx">
            </div>
            <!-- Modal Actions -->
            <div class="flex justify-end space-x-4">
                <button type="button" class="bg-gray-300 p-2 rounded-lg hover:bg-gray-400" onclick="closeModalEmployee()">Cancelar</button>
                <button type="submit" class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Guardar</button>
            </div>
        </form>
    </div>
</div>

<!-- Modal for delete worker -->
<div id="deleteClientModal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
    <div class="bg-white w-1/3 p-6 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-4">Eliminar Trabajador</h2>
        <form action="DeleteEmployee" method="POST">
            <input type="hidden" id="id" name="id">
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Código: </label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="code_entered">
                <%
                    if(request.getAttribute("Error") != null){
                        String error = (String)request.getAttribute("Error");

                %>
                <p style="color: red"><%=error%></p>
                <%
                    }
                %>

            </div>
            <div class="flex justify-end space-x-4">
                <button type="button" class="bg-gray-300 p-2 rounded-lg hover:bg-gray-400" onclick="closeModalDelete()">Cancelar</button>
                <button type="submit" class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Eliminar</button>
            </div>
        </form>
    </div>
</div>



<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="js/function_employee.js"></script>
<script src="js/code_email_ajax.js"></script>
</body>
</html>

<%
        }else{
            response.sendRedirect("Employee");
        }
    }else{
        response.sendRedirect("login.jsp");
    }
%>