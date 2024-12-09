<%@ page import="com.chichos_snack_project.model.Inventory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Category" %>
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
        .no-scrollbar::-webkit-scrollbar {
            display: none;
        }
        .no-scrollbar {
            scrollbar-width: none;
        }
        #reportModal{
            display: none;
        }
    </style>
</head>
<%
    HttpSession session1  = request.getSession();
    if(session1.getAttribute("is_valid_user") != null){
        if(request.getAttribute("inventoryList") !=null && request.getAttribute("categoryList") != null && request.getAttribute("productList") != null){
            @SuppressWarnings("unchecked")
            List<Inventory> inventoryList = (List<Inventory>) request.getAttribute("inventoryList");
            @SuppressWarnings("unchecked")
            List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
            @SuppressWarnings("unchecked")
            List<Product> productList = (List<Product>)request.getAttribute("productList");

%>
<body class="bg-gray-100">
<div class="flex">
    <!-- Sidebar Navigation -->
    <div class="bg-teal-600 w-64 fixed h-screen text-white p-6 fixed top-0 left-0">
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
                <a href="Products" class="flex items-center space-x-4 p-2 rounded-lg bg-teal-500">
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
    <div class="flex-1 p-6 ml-64">
        <!-- Header -->
        <div class="flex justify-between items-center mb-8 sticky top-0 bg-gray-100 z-10 p-4 shadow">
            <div class="relative">
                <input type="text" placeholder="Search Here" class="p-2 border border-gray-300 rounded-lg focus:outline-none w-full lg:w-96">
                <span class="absolute right-2 top-2 text-gray-400">
                        <ion-icon name="search-outline"></ion-icon>
                    </span>
            </div>
            <div class="flex items-center space-x-4">
                <!-- Button to Open Modal -->
                <button class="bg-green-400 text-white p-2 rounded-lg hover:bg-grean-600" onclick="openReportModal()">Generar reporte</button>
                <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600" onclick="openModal()">+ Agregar Inventario</button>
                <div class="relative">
                    <a href="Notifications">
                        <ion-icon name="notifications-outline" class="text-2xl text-gray-600"></ion-icon>
                        <span class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">1</span>
                    </a>
                </div>
            </div>
        </div>

        <!-- Category Menu -->
        <div class="w-[80%] max-w-[1000px] overflow-x-auto no-scrollbar whitespace-nowrap text-teal-600 font-bold text-lg mb-6 sticky top-16 bg-gray-100 z-10 p-4 shadow">
            <div class="flex space-x-4">
                <button id="todos-btn" class="border-b-4 border-teal-500" onclick="showCategory('todos')">Todos</button>
                <%
                    for(Category category: categoryList){

                %>
                <button id="<%=category.getName_category()+"-btn"%>" class="hover:border-b-4 hover:border-teal-500" onclick="showCategory('<%=category.getName_category()%>')"><%=category.getName_category()%></button>
                <%
                    }
                %>
            </div>
        </div>

        <!-- Inventory Section -->
        <div class="overflow-y-auto h-[calc(100vh-14rem)]  no-scrollbar ">
            <div id="todos" class="category grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                <!-- Product Item -->
                <%
                    for(Inventory inventory:inventoryList){
                %>
                <div class="bg-white p-4 rounded-lg shadow">
                    <img src="https://via.placeholder.com/200" class="w-full h-40 object-cover rounded-lg mb-4">
                    <div class="flex justify-between items-center">
                        <div>
                            <h3 class="text-lg font-bold">Lote de <%=inventory.getProduct().getName()%></h3>
                            <p class="text-gray-600">Stock: <%=inventory.getStock()%></p>
                            <p class="text-red-600 font-bold">Expiracion: <%=inventory.getExpiration_date()%></p>
                        </div>
                        <button class="bg-teal-600 text-white px-3 py-1 rounded-lg hover:bg-teal-800 ml-4" onclick="openEditModal('<%=inventory.getLot()%>','<%=inventory.getProduct().getName()%>','<%=inventory.getExpiration_date()%>','<%=inventory.getPurchase_date()%>','<%=inventory.getPurchase_price()%>','<%=inventory.getStock()%>','<%=inventory.getId_inventory()%>')">Editar</button>
                        <button class="boton bg-red-600 text-white px-3 py-1 rounded-lg hover:bg-red-800 ml-4" onclick="openDeleteModal('<%=inventory.getId_inventory()%>')">Eliminar</button>
                    </div>
                </div>
                <%
                    }
                %>
            </div>

            <%
                for(Category category:categoryList){


            %>
            <div id="<%=category.getName_category()%>" class="category grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 hidden">
                <%
                    for(Inventory inventory:inventoryList){
                        if(inventory.getProduct().getCategory().getName_category().equalsIgnoreCase(category.getName_category())){
                %>
                <div class="bg-white p-4 rounded-lg shadow">
                    <img src="https://via.placeholder.com/200" class="w-full h-40 object-cover rounded-lg mb-4">
                    <div class="flex justify-between items-center">
                        <div>
                            <h3 class="text-lg font-bold">Lote de <%=inventory.getProduct().getName()%></h3>
                            <p class="text-gray-600">Stock: <%=inventory.getStock()%></p>
                            <p class="text-red-600 font-bold">Expiracion: <%=inventory.getExpiration_date()%></p>
                        </div>
                        <button class="bg-teal-600 text-white px-3 py-1 rounded-lg hover:bg-teal-800 ml-4" onclick="openEditModal('<%=inventory.getLot()%>','<%=inventory.getProduct().getName()%>','<%=inventory.getExpiration_date()%>','<%=inventory.getPurchase_date()%>','<%=inventory.getPurchase_price()%>','<%=inventory.getStock()%>','<%=inventory.getId_inventory()%>')">Editar</button>
                        <button class="boton bg-red-600 text-white px-3 py-1 rounded-lg hover:bg-red-800 ml-4" onclick="openDeleteModal('<%=inventory.getId_inventory()%>')">Eliminar</button>
                    </div>
                </div>
                <%
                        }
                    }
                %>

            </div>
            <%
                }
            %>
        </div>


    </div>
</div>

<!-- Modal for Adding Inventory -->
<div id="productModal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
    <div class="bg-white w-1/3 p-6 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-4">Agregar Información del Inventario</h2>
        <form action="createInventory" method="POST">
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700" for="product">Nombre del Producto</label>
                <select name="product" id="product" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
                    <%
                        for(Product product:productList){
                    %>
                    <option value="<%=product.getId_product()%>"><%=product.getName()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700" for="lot">Lote:</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="lot" maxlength="20" id="lot" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700" for="stock">Stock:</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="stock" id="stock" oninput="validateNumber(this)" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700" for="date_buy">Fecha de compra:</label>
                <input type="date" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="date_buy" id="date_buy" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700" for="date_end">Fecha de Vencimiento:</label>
                <input type="date" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="date_end" id="date_end" required>
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700" for="price_buy">Precio</label>
                <input type="number" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="price_buy" id="price_buy" required>
            </div>
            <!-- Modal Actions -->
            <div class="flex justify-end space-x-4">
                <button type="button" class="bg-gray-300 p-2 rounded-lg hover:bg-gray-400" onclick="closeModal()">Cancelar</button>
                <button type="submit" class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Guardar</button>
            </div>
        </form>
    </div>
</div>

<!-- Modal for editing Inventory -->
<div id="editProductModal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
    <div class="bg-white w-full max-w-3xl p-6 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-4">Editar Información del Inventario</h2>
        <form action="UpdateInventory" method="post">
            <input type="hidden" name="id_inventory" id="id_inventory">
            <!-- Basic Product Information -->
            <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700" for="name_product">Producto</label>
                    <p class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" id="name_product"></p>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700" for="name_lot">Lote</label>
                    <p  class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" id="name_lot" ></p>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Stock del Inventario</label>
                    <input type="text" id="editProductStock"  name="stock" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" oninput="validateNumber(this)" required>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Fecha de Compra</label>
                    <input id="editBuyDate" type="date" name="purchase" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" required>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700" for="editExpiryDate">Fecha de Caducidad</label>
                    <input id="editExpiryDate" name="expired" type="date" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" required>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Precio de compra</label>
                    <input id="editPurchasePrice" type="number" name="buy_purchase" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" required>
                </div>
            </div>
            <!-- Modal Actions -->
            <div class="flex justify-end space-x-4">
                <button type="button" class="bg-gray-300 p-2 rounded-lg hover:bg-gray-400" onclick="closeEditModal()">Cancelar</button>
                <button type="submit" class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Actualizar</button>
            </div>
        </form>
    </div>
</div>
<!-- Modal for delete inventory -->
<div id="deleteInventoryModal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
    <div class="bg-white w-1/3 p-6 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-4">Eliminar Inventario</h2>
        <form action="DeleteInventory" method="POST">
            <input type="hidden" id="id" name="id_inventory">
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Código: </label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" name="code_entered" required oninput="validateNumber(this)">
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
                <button type="button" class="bg-gray-300 p-2 rounded-lg hover:bg-gray-400" onclick="closeDeleteModal()">Cancelar</button>
                <button type="submit" class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Eliminar</button>
            </div>
        </form>
    </div>
</div>
<!-- Modal for report -->
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
                <label for="filterProduct">Producto</label>
                <select id="filterProduct" name="filter_product" onchange="" class="p-2 bg-white border border-gray-300 rounded-lg m-3">
                    <option value="0">Todos</option>
                    <%
                        for(Product product:productList){
                    %>
                    <option value="<%=product.getId_product()%>"><%=product.getName()%></option>
                    <%
                        }
                    %>
                </select>
                <div class="flex justify-center ms-3">
                    <button class="bg-green-500 text-white p-2 rounded-lg hover:bg-green-600" type="button" onclick="ajax_report_inventory()" >Exportar</button>
                </div>
            </form>

        </div>

        <!-- Tabla del reporte -->
        <div class="overflow-y-auto max-h-96">
            <table class="table-auto w-full text-center border border-gray-200" id="reportTable">
                <thead class="bg-blue-200 sticky top-0">
                <tr>
                    <th class="px-4 py-2">Lote</th>
                    <th class="px-4 py-2">Producto</th>
                    <th class="px-4 py-2">Stock</th>
                    <th class="px-4 py-2">Fecha de compra</th>
                    <th class="px-4 py-2">Fecha de vencimiento</th>
                    <th class="px-4 py-2">Precio de Compra</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Inventory inventory : inventoryList) {
                %>
                <tr>
                    <td class="border px-4 py-2"><%= inventory.getLot() %></td>
                    <td class="border px-4 py-2"><%= inventory.getProduct().getName()%></td>
                    <td class="border px-4 py-2"><%= inventory.getStock() %></td>
                    <td class="border px-4 py-2"><%= inventory.getPurchase_date() %></td>
                    <td class="border px-4 py-2"><%= inventory.getExpiration_date()%></td>
                    <td class="border px-4 py-2">S/<%=inventory.getPurchase_price()%></td>
                    <td class="hidden"><%=inventory.getProduct().getId_product()%></td>
                </tr>
                <%
                    }
                %>

                </tbody>
            </table>
        </div>
    </div>
</div>

<%
    if(request.getSession().getAttribute("error") !=null){
        String error = (String) request.getSession().getAttribute("error");
        request.getSession().removeAttribute("error");

%>
<script>
    alert("<%=error%>");
</script>
<%
    }
%>

<script src="js/function_inventory.js"></script>
<script src="js/code_email_ajax.js"></script>
<script src="js/ajax_report_inventory.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
<%
        }else{
            response.sendRedirect("Inventory");
        }

    }else{
        response.sendRedirect("login.jsp");
    }
%>
</html>x
