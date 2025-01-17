<%@ page import="com.chichos_snack_project.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Sale" %>
<%@ page import="com.chichos_snack_project.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chichos Snack Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        /* Inicialmente ocultamos el overlay */
        #reporteOverlay {
            display: none;
        }
    </style>
    <%
        HttpSession session1  = request.getSession();
        boolean is_valid_user = false;
        if(session1.getAttribute("is_valid_user") != null){
            if(request.getAttribute("total") !=null && request.getAttribute("count") !=null && request.getAttribute("employeeList") !=null && request.getAttribute("saleList") !=null && request.getAttribute("mostSoldProducts") !=null){
                double total = (double)request.getAttribute("total");
                int count = (int)request.getAttribute("count");
                @SuppressWarnings("unchecked")
                List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
                @SuppressWarnings("unchecked")
                List<Sale> saleList = (List<Sale>) request.getAttribute("saleList");
                @SuppressWarnings("unchecked")
                List<Product> mostSoldProducts = (List<Product>) request.getAttribute("mostSoldProducts");

    %>
</head>

<body class="bg-gray-100">

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
                <a href="index.jsp" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                    <span>
                        <ion-icon name="cash-outline" class="text-xl"></ion-icon>
                    </span>
                    <span>Inicio</span>
                </a>
            </li>
            <li class="mb-6">
                <a href="Sales" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
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
    <div class="flex-1 p-6 relative">
        <!-- Header  change-->
        <div class="flex justify-between items-center mb-8">
            <div class="relative">
                <input type="text" placeholder="Search Here" class="p-2 border border-gray-300 rounded-lg focus:outline-none w-full lg:w-96">
                <span class="absolute right-2 top-2 text-gray-400">
                        <ion-icon name="search-outline"></ion-icon>
                    </span>
            </div>
            <div class="flex items-center space-x-4">
                <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600"><a href="Products">+ Agregar Producto</a></button>
                <div class="relative">
                    <a href="Notifications">
                        <ion-icon name="notifications-outline" class="text-2xl text-gray-600"></ion-icon>
                        <span class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">1</span>
                    </a>
                </div>
            </div>
        </div>

        <!-- Main Section -->
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-4">
            <!-- Overview Section -->
            <div class="col-span-2 bg-white p-6 rounded-lg shadow-md">
                <h2 class="text-xl font-semibold mb-4">Principal</h2>
                <div class="grid grid-cols-2 gap-4 mb-4">
                    <div class="bg-green-100 p-4 rounded-lg">
                        <h3 class="text-sm font-medium text-green-700">Ingresos del mes</h3>
                        <p class="text-2xl font-bold">S/ <%=total%></p>
                    </div>
                    <div class="bg-red-100 p-4 rounded-lg">
                        <h3 class="text-sm font-medium text-red-700">N° de Clientes</h3>
                        <p class="text-2xl font-bold"><%=count%></p>
                    </div>
                </div>
                <!-- Popular Products -->
                <h3 class="text-lg font-semibold mb-2">Productos mas vendidos del mes</h3>
                <div class="flex space-x-4">
                    <%
                        for(Product product: mostSoldProducts){
                    %>
                    <div>
                        <img src="https://via.placeholder.com/50" class="w-12 h-12 rounded-full">
                        <p><%=product.getName()%></p>
                    </div>
                    <%
                        }
                    %>

                </div>
            </div>

            <!-- Reports Section -->
            <div class="bg-white p-6 rounded-lg shadow-md">
                <h2 class="text-xl font-semibold mb-4">Ultimas Ventas</h2>
                <div class="flex flex-col space-y-2">
                    <%
                      for(int i = 0;i<4;i++){
                        Sale sale = saleList.get(i);
                    %>
                    <div class="flex justify-between">
                        <span>Venta #00<%=sale.getId_sale()%></span>
                        <span class="text-green-600">S/<%=sale.getAmount()%></span>
                    </div>
                    <%
                        }
                    %>
                </div>
                <button class="bg-teal-500 text-white mt-4 p-2 rounded-lg hover:bg-teal-600" onclick="mostrarReporte()">Ver reporte detallado</button>
            </div>
        </div>

        <!-- Employee Section -->
        <div class="bg-white p-6 rounded-lg shadow-md mt-8">
            <div class="flex">
                <h2 class="text-xl font-semibold mb-4">Trabajadores</h2>
                <a class="bg-green-500 text-white py-1 px-4 rounded-lg ml-5 h-8" href="Employee">Ver mas</a>
            </div>

            <%
                for(int i =0; i<2;i++){
            %>
            <div class="flex items-center space-x-4 mb-2.5 mt-2.5">
                <img src="https://via.placeholder.com/50" class="w-12 h-12 rounded-full">
                <div>
                    <p class="font-bold"><%=employeeList.get(i).getName()%> <%=employeeList.get(i).getLastname()%></p>
                    <p class="text-gray-500">Telefono: <%=employeeList.get(i).getPhone()%></p>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>

<!-- Reporte de Ventas (overlay) -->
<div id="reporteOverlay" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
    <div class="bg-blue-100 w-2/3 rounded-lg shadow-lg p-6 relative">
        <div class="flex justify-between items-center mb-4">
            <h2 class="text-2xl font-semibold">Reporte de ventas</h2>
            <button class="text-black hover:text-red-500" onclick="cerrarReporte()">
                <ion-icon name="close-outline" class="text-2xl"></ion-icon>
            </button>
        </div>

        <!-- Dropdown para seleccionar el periodo -->
        <div class="flex justify-between items-center mb-6">
            <label for="periodo" class="text-lg font-semibold">Periodo:</label>
            <select id="periodo" class="p-2 bg-white border border-gray-300 rounded-lg">
                <option value="diario">Diario</option>
                <option value="semanal">Semanal</option>
                <option value="quincenal">Quincenal</option>
                <option value="mensual" selected>Mensual</option>
            </select>
        </div>

        <!-- Tabla del reporte -->
        <table class="table-auto w-full text-center border border-gray-200" >
            <thead class="bg-blue-200">
            <tr>
                <th class="px-4 py-2">Producto</th>
                <th class="px-4 py-2">Cantidad</th>
                <th class="px-4 py-2">Precio</th>
                <th class="px-4 py-2">Stock Disponible</th>
            </tr>
            </thead>
            <tbody>
            <tr class="bg-white">
                <td class="border px-4 py-2">Producto 1</td>
                <td class="border px-4 py-2">10</td>
                <td class="border px-4 py-2">S/. 15.00</td>
                <td class="border px-4 py-2">100</td>
            </tr>
            <tr class="bg-gray-50">
                <td class="border px-4 py-2">Producto 2</td>
                <td class="border px-4 py-2">20</td>
                <td class="border px-4 py-2">S/. 25.00</td>
                <td class="border px-4 py-2">200</td>
            </tr>
            </tbody>
        </table>

        <!-- Botón de Exportar -->
        <div class="flex justify-center mt-6">
            <button class="bg-green-500 text-white p-2 rounded-lg hover:bg-green-600">Exportar</button>
        </div>
    </div>
</div>

<!-- Scripts -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script>
    // Función para mostrar el reporte
    function mostrarReporte() {
        document.getElementById('reporteOverlay').style.display = 'flex';
    }

    // Función para cerrar el reporte
    function cerrarReporte() {
        document.getElementById('reporteOverlay').style.display = 'none';
    }
</script>
</body>
</html>

<%

            }else{
                response.sendRedirect("Index");
            }
    }else{
            response.sendRedirect("login.jsp");
    }
%>