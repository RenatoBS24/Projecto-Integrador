<%@ page import="com.chichos_snack_project.model.Inventory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Category" %>
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
    </style>
</head>
<%
    HttpSession session1  = request.getSession();
    if(session1.getAttribute("is_valid_user") != null){
        if(request.getAttribute("inventoryList") !=null && request.getAttribute("categoryList") != null){
            @SuppressWarnings("unchecked")
            List<Inventory> inventoryList = (List<Inventory>) request.getAttribute("inventoryList");
            @SuppressWarnings("unchecked")
            List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");

%>
<body class="bg-gray-100">

<div class="flex">
    <!-- Sidebar Navigation -->
    <div class="bg-teal-600 w-64 h-screen text-white p-6 fixed top-0 left-0">
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
                        <button class="boton bg-red-600 text-white px-3 py-1 rounded-lg hover:bg-red-800 ml-4" onclick="openDeleteModal('')">Eliminar</button>
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
                        <button class="boton bg-red-600 text-white px-3 py-1 rounded-lg hover:bg-red-800 ml-4" onclick="openDeleteModal('')">Eliminar</button>
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

<!-- Modal for Adding Product -->
<div id="productModal" class="fixed inset-0 bg-gray-900 bg-opacity-80 flex items-center justify-center hidden z-50">
    <div class="bg-white w-1/3 p-6 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-4">Agregar Información del Producto</h2>
        <form>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Nombre del Producto</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Lote</label>
                <input type="text" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Precio</label>
                <input type="number" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
            </div>
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">Subir Archivo (Excel)</label>
                <input type="file" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
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
        <form action="updateProduct" method="post">
            <input type="hidden" name="id_product" id="id_product">
            <input type="hidden" name="id_inventory" id="id_inventory">
            <!-- Basic Product Information -->
            <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700" for="name_lot">Lote</label>
                    <input type="text" name="name_lot" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" id="name_lot">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700" for="name_product">Producto</label>
                    <p class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none" id="name_product"></p>
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700" for="editExpiryDate">Fecha de Caducidad</label>
                    <input id="editExpiryDate" name="expired" type="date" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Fecha de Compra</label>
                    <input id="editBuyDate" type="date" name="purchase" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Precio de compra</label>
                    <input id="editPurchasePrice" type="text" name="buy_purchase" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Stock del Inventario</label>
                    <input type="text" id="editProductStock"  name="stock" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
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


<script>
    // Function to open the modal
    function openModal() {
        document.getElementById('productModal').classList.remove('hidden');
    }

    // Function to close the modal
    function closeModal() {
        document.getElementById('productModal').classList.add('hidden');
    }
</script>
<script src="js/function_inventory.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
<%
        }else{
            response.sendRedirect("inventory");
        }

    }else{
        response.sendRedirect("login.jsp");
    }
%>
</html>x
