<%@ page import="com.chichos_snack_project.model.Customer" %>
<%@ page import="com.chichos_snack_project.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Sale" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chichos Snack Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        /* Ocultar el overlay inicialmente */
        #reportModal, #editarModal,#ModalDetails {
            display: none;
        }

        /* Ajustar la lista de ventas con un scroll */
        .ventas-list {
            max-height: 300px;
            overflow-y: auto;
        }

        .no-scrollbar::-webkit-scrollbar {
            display: none;
        }

        .no-scrollbar {
            scrollbar-width: none;
        }
    </style>
</head>

<body class="bg-gray-100">

<%
    HttpSession session1 = request.getSession();
    if(session1.getAttribute("is_valid_user") !=null){
        if(request.getAttribute("customerList") !=null && request.getAttribute("employeeList") !=null && request.getAttribute("saleList") !=null){
            @SuppressWarnings("unchecked")
            List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
            @SuppressWarnings("unchecked")
            List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
            @SuppressWarnings("unchecked")
            List<Sale> saleList = (List<Sale>) request.getAttribute("saleList");

%>

<div class="flex">
    <!-- Sidebar Navigation -->
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
                <a href="Index" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                        <span>
                            <ion-icon name="bar-chart-outline"></ion-icon>
                        </span>
                    <span>Inicio</span>
                </a>
            </li>
            <li class="mb-6">
                <a href="sales.jsp" class="flex items-center space-x-4 p-2 rounded-lg bg-teal-500">
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
                <a href="Customer" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="people-outline" class="text-xl"></ion-icon>
                    </span>
                    <span>Clientes</span>
                </a>
            </li>
            <li class="mb-6">
                <a href="Employee" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
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
    <div class="flex-1 p-6 relative  mt-5">
        <!-- Header -->
        <div class="flex justify-between items-center mb-8">
            <div class="relative">
                <input type="text" placeholder="Search Here" class="p-2 border border-gray-300 rounded-lg focus:outline-none w-full lg:w-96">
                <span class="absolute right-2 top-2 text-gray-400">
                        <ion-icon name="search-outline"></ion-icon>
                    </span>
            </div>
            <div class="flex items-center space-x-4">
                <button class="bg-green-400 text-white p-2 rounded-lg hover:bg-grean-600" onclick="openReportModal()">Generar reporte</button>
                <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">
                    <a href="sale_register.jsp">Nueva Venta</a>
                </button>
                <div class="relative">
                    <a href="Notifications">
                        <ion-icon name="notifications-outline" class="text-2xl text-gray-600"></ion-icon>
                        <span class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">1</span>
                    </a>
                </div>
            </div>
        </div>

        <!-- Main Section -->
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-4 items-center mt-19">
            <!-- Filtros -->
            <div class="bg-white p-4 rounded-lg shadow-md h-full flex flex-col">
                <h2 class="text-lg font-semibold mb-4">Filtros de Ventas</h2>
                <label class="block mb-2">Trabajador:</label>
                <select class="w-full p-2 mb-4 border rounded-lg" name ="employee">
                    <%
                        for(Employee employee : employeeList){
                    %>
                    <option value="<%=employee.getId_employee()%>" ><%= employee.getName() %></option>
                    <%
                        }
                    %>
                </select>
                <label class="block mb-2">Fecha:</label>
                <input type="date" id="fechaFiltro" class="w-full p-2 mb-4 border rounded-lg">
                <label class="block mb-2">Cliente:</label>
                <select class="w-full p-2 mb-4 border rounded-lg">
                    <%
                        for(Customer customer : customerList){
                    %>
                    <option value="<%=customer.getId_customer()%>"><%= customer.getName() %></option>
                    <%
                        }
                    %>
                </select>
                <label class="block mb-2">Producto:</label>
                <input type="text" placeholder="Nombre del producto" class="w-full p-2 border rounded-lg">
                <button class="bg-teal-500 w-full text-white p-2 mt-4 rounded-lg hover:bg-teal-600">Aplicar Filtros</button>
            </div>

            <!-- Lista de Ventas -->
            <div class="bg-white p-4 no-scrollbar rounded-lg shadow-md col-span-2 ventas-list min-h-[500px] flex flex-col">
                <h2 class="text-lg font-semibold mb-4">Ventas Realizadas</h2>
                <div class="space-y-4">
                    <%
                        Gson gson = new Gson();
                        for(Sale sale : saleList){
                            String json = gson.toJson(sale.getProductList()).replace("\"","'");

                    %>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #<%=sale.getId_sale()%> - Cliente: <%=sale.getCustomer().getName()%></span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('<%=sale.getEmployee().getName()%>','<%=sale.getCustomer().getName()%>','<%=sale.getSale_date()%>','<%=sale.getAmount()%>',`<%=json%>`)">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal de Detalles de Venta -->
<div id="ModalDetails" class="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white w-3/4 lg:w-1/2 p-6 rounded-lg">
        <h3 class="text-xl font-semibold mb-4">Detalles de Venta</h3>
        <div class="space-y-2">
            <p id="employeeId" ><strong>Vendedor:</strong> </p>
            <p id="customerId"><strong>Cliente:</strong> </p>
            <p id="dateId"><strong>Fecha:</strong></p>
            <hr>
            <h4 class="font-semibold mt-4">Productos Vendidos:</h4>
            <ul class="list-disc ml-6" id="list">

            </ul>
            <p class="font-semibold mt-4" id="totalId"></p>
        </div>
        <button onclick="cerrarModal('ModalDetails')" class="mt-6 w-full bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Cerrar</button>
    </div>
</div>

<!-- Modal de Edición de Venta -->
<div id="editarModal" class="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white w-3/4 lg:w-1/2 p-6 rounded-lg">
        <h3 class="text-xl font-semibold mb-4">Editar Venta</h3>
        <div class="space-y-2">
            <label>Cliente:</label>
            <input type="text" class="w-full p-2 border rounded-lg mb-4">
            <label>Fecha:</label>
            <input type="date" class="w-full p-2 border rounded-lg mb-4">
            <label>Productos:</label>
            <textarea class="w-full p-2 border rounded-lg mb-4" rows="4"></textarea>
            <button onclick="cerrarModal('editarModal')" class="mt-6 w-full bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Guardar Cambios</button>
        </div>
    </div>
</div>

<!-- Modal de Reporte de Ventas-->
<div id="reportModal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
    <div class="bg-white w-10/12 rounded-lg shadow-lg p-6 relative">
        <div class="flex justify-between items-center mb-4">
            <h2 class="text-2xl font-semibold">Reporte de ventas</h2>
            <button class="text-black hover:text-red-500" onclick="closeReportModal()">
                <ion-icon name="close-outline" class="text-2xl"></ion-icon>
            </button>

        </div>

        <!-- Dropdown para seleccionar el periodo -->
        <div class="flex justify-start items-center mb-6">
            <form action="" class="flex justify-start items-center">
                <label for="filterStart" class="text-lg font-semibold">Desde:</label>
                <input type="date" id="filterStart" name="date_start" onchange="dateFilter()" class="p-1 bg-white border rounded-lg border-gray-300 m-3">
                <label for="filterEnd" class="text-lg font-semibold">Hasta:</label>
                <input type="date" id="filterEnd" name="date_end" onchange="dateEndFilter()" class="p-1 bg-white border rounded-lg border-gray-300 m-3">
                <label for="filterEmployee">Empleado</label>
                <select id="filterEmployee" name="filter_employee" onchange="" class="p-2 bg-white border border-gray-300 rounded-lg m-3">
                    <option value="0">Todos</option>
                    <%
                        for(Employee employee : employeeList){
                    %>
                    <option value="<%=employee.getId_employee()%>"><%=employee.getName()%></option>
                   <%
                       }
                   %>
                </select>
                <label for="filterCustomer" class="text-lg font-semibold">Cliente:</label>
                <select id="filterCustomer" onchange="" name="filter_customer" class="p-2 bg-white border border-gray-300 rounded-lg m-3">
                    <option value="0">Todos</option>
                    <%
                        for(Customer customer : customerList){
                    %>
                    <option value="<%=customer.getId_customer()%>"><%=customer.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <div class="flex justify-center ms-3">
                    <button class="bg-green-500 text-white p-2 rounded-lg hover:bg-green-600" type="button" onclick="ajax_report_sales()">Exportar</button>
                </div>
            </form>

        </div>

        <!-- Tabla del reporte -->
        <div class="overflow-y-auto max-h-96">
            <table class="table-auto w-full text-center border border-gray-200" id="reportTable">
                <thead class="bg-blue-200 sticky top-0">
                <tr>
                    <th class="px-4 py-2">Id venta</th>
                    <th class="px-4 py-2">Empleado</th>
                    <th class="px-4 py-2">Cliente</th>
                    <th class="px-4 py-2">Fecha</th>
                    <th class="px-4 py-2">Monto</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Sale sale : saleList) {
                %>
                <tr>
                    <td class="border px-4 py-2"><%= sale.getId_sale() %></td>
                    <td class="border px-4 py-2"><%= sale.getEmployee().getName() %></td>
                    <td class="border px-4 py-2"><%= sale.getCustomer().getName() %></td>
                    <td class="border px-4 py-2"><%= sale.getSale_date() %></td>
                    <td class="border px-4 py-2">S/<%= sale.getAmount() %></td>
                    <td class="hidden"><%=sale.getEmployee().getId_employee()%></td>
                    <td class="hidden"><%=sale.getCustomer().getId_customer()%></td>
                </tr>
                <%
                    }
                %>

                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Scripts -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="js/function_sale.js"></script>
<script src="js/ajax_report_sales.js"></script>

</body>
<%
        }else{
            response.sendRedirect("Sales");
        }
    }else{
        response.sendRedirect("login.jsp");
    }
%>
</html>

