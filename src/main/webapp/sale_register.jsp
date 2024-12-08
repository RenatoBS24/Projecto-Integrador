<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Product" %>
<%@ page import="com.chichos_snack_project.model.Category" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.chichos_snack_project.model.Employee" %>
<%@ page import="com.chichos_snack_project.model.Customer" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chichos Snack Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>

</head>
<style>
    .no-scrollbar::-webkit-scrollbar {
        display: none;
    }

    .no-scrollbar {
        scrollbar-width: none;
    }

    .scale-up {
        animation: scaleUp 0.3s ease-in-out;
    }

    @keyframes scaleUp {
        0% {
            transform: scale(1);
        }

        50% {
            transform: scale(1.2);
        }

        100% {
            transform: scale(1);
        }
    }

    .fade-in {
        animation: fadeIn 0.5s ease-in-out;
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
        }

        to {
            opacity: 1;
        }
    }
</style>

<%
    HttpSession session1 = request.getSession();
    if(session1.getAttribute("is_valid_user") !=null){
        if(request.getAttribute("productList") !=null && request.getAttribute("categoryList") !=null && request.getAttribute("employeeList") !=null && request.getAttribute("customerList") !=null){
            @SuppressWarnings("unchecked")
            List<Product> productList = (List<Product>) request.getAttribute("productList");
            @SuppressWarnings("unchecked")
            List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
            @SuppressWarnings("unchecked")
            List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
            @SuppressWarnings("unchecked")
            List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");

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
                    <span class="text-lg font-semibold">Menu</span>
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
    <div class="flex-1 p-6 ml-64">
        <div class="max-w-[1300px] mx-auto">
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
                    <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600" onclick="toggleCart()">
                        <ion-icon name="cart-outline" class="text-2xl"></ion-icon>
                        Carrito de Compras
                    </button>
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
        </div>
        <!-- Product Grid -->
        <div class="overflow-y-auto h-[calc(100vh-14rem)]  no-scrollbar ">
            <div id="todos" class="category grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                <!-- Product Item -->
                <%
                    for(Product product:productList){
                %>
                <div class="bg-white p-4 rounded-lg shadow">
                    <img src="https://via.placeholder.com/200" class="w-full h-40 object-cover rounded-lg mb-4">
                    <div class="flex justify-between items-center">
                        <div>
                            <h3 class="text-lg font-bold"><%=product.getName()%></h3>
                            <p class="text-gray-600">Stock: <%=product.getStock()%> <%=product.getUnitOfMeasurement().getName_unit_of_measurement()%></p>
                            <p class="text-teal-600 font-bold">Precio: <%=product.getPrice()%></p>
                            <input type="hidden" value="<%=product.getId_product()%>" id="id" >
                        </div>
                    </div>
                    <%
                        if(product.getStock() > 0){
                    %>
                    <button onclick="addToCart('<%=product.getName()%>','<%=product.getPrice()%>',<%=product.getId_product()%>)" class="bg-teal-500 text-white mt-4 w-full py-2 rounded hover:bg-teal-600">
                        Agregar al carrito
                    </button>
                    <%
                    }else{
                    %>
                    <p class="text-red-600 text-lg">El producto esta Agotado</p>
                    <%
                        }
                    %>
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
                    for(Product product:productList){
                        if(product.getCategory().getName_category().equalsIgnoreCase(category.getName_category())){
                %>
                <div class="bg-white p-4 rounded-lg shadow">
                    <img src="https://via.placeholder.com/200" class="w-full h-40 object-cover rounded-lg mb-4">
                    <div class="flex justify-between items-center">
                        <div>
                            <h3 class="text-lg font-bold"><%=product.getName()%></h3>
                            <p class="text-gray-600">Stock: <%=product.getStock()%></p>
                            <p class="text-teal-600 font-bold">Precio: <%=product.getPrice()%></p>
                        </div>
                    </div>
                    <%
                        if(product.getStock() > 0){
                    %>
                    <button onclick="addToCart('<%=product.getName()%>','<%=product.getPrice()%>',<%=product.getId_product()%>)" class="bg-teal-500 text-white mt-4 w-full py-2 rounded hover:bg-teal-600">
                        Agregar al carrito
                    </button>
                    <%
                        }else{
                    %>
                    <p class="text-red-600 text-lg">El producto esta Agotado</p>
                    <%
                        }
                    %>
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

<div id="cart-modal" class="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-end items-start z-50 hidden">
    <div class="bg-white w-full max-w-2xl h-full p-6 rounded-l-lg">
        <div class="flex justify-between items-center mb-4">
            <h2 class="text-2xl font-bold">Carrito de Compras</h2>
            <button class="text-red-500" onclick="toggleCart()">
                <ion-icon name="close-outline" class="text-2xl"></ion-icon>
            </button>
        </div>
        <div>
            <table class="w-full text-left">
                <thead>
                <tr>
                    <th class="border-b py-2">Producto</th>
                    <th class="border-b py-2">Cantidad</th>
                    <th class="border-b py-2">Precio</th>
                    <th class="border-b py-2">Total</th>
                    <th class="border-b py-2"></th>
                </tr>
                </thead>
                <tbody id="cart-items">
                <%
                    if(session1.getAttribute("cart") == null){
                %>
                <p>El carrito esta vacio</p>
                <%
                    }else{
                        @SuppressWarnings("unchecked")
                        HashMap<Integer,Product> productHashMap = (HashMap<Integer, Product>) session1.getAttribute("cart");
                        for(Product product:productHashMap.values()){


                %>
                <tr class="js-cart-item">
                    <td class="py-2"><%=product.getName()%></td>
                    <td class="py-2 flex items-center">
                        <button class="text-gray-500 hover:text-gray-700" onclick="updateQuantity('<%=product.getName()%>', -1,<%=product.getId_product()%>)">-</button>
                        <span class="mx-2"><%=product.getStock()%></span>
                        <button class="text-gray-500 hover:text-gray-700" onclick="updateQuantity('<%=product.getName()%>', 1,<%=product.getId_product()%>)">+</button>
                    </td>
                    <td class="py-2">S/<%=product.getPrice()%></td>
                    <td class="py-2">S/<%=product.getPrice()*product.getStock()%></td>
                    <td class="py-2">
                        <button class="text-red-500 hover:underline" onclick="removeFromCart('<%=product.getName()%>',<%=product.getId_product()%>)">Eliminar</button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
            <div class="flex justify-between items-center mt-6">
                <span class="text-xl font-bold">Total:
                    <%
                        if(session1.getAttribute("cart") == null){
                    %>
                    <span id="cart-total" class="text-teal-600">$0.00</span>
                    <%
                        }else{
                            @SuppressWarnings("unchecked")
                            HashMap<Integer,Product> productHashMap = (HashMap<Integer, Product>) session1.getAttribute("cart");
                            double total = 0;
                            for(Product product:productHashMap.values()){
                                total += product.getPrice()*product.getStock();
                            }
                    %>
                    <span id="cart-total" class="text-teal-600">$<%=total%></span>
                    <%
                        }
                    %>
                </span>
            </div>
            <div class="mt-4 flex space-x-4">
                <div class="w-1/2">
                    <label for="employee">Empleado</label>
                    <select name="Employee" id="employee" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
                        <%
                            for(Employee employee:employeeList){
                        %>
                        <option value="<%=employee.getId_employee()%>"><%=employee.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="w-1/2">
                    <label for="customer">Cliente</label>
                    <select name="Customer" id="customer" class="w-full p-2 border border-gray-300 rounded-lg focus:outline-none">
                        <%
                            for(Customer customer:customerList){
                        %>
                        <option value="<%=customer.getId_customer()%>"><%=customer.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="mt-4">
                <label for="check">Usar Credito</label>
                <input type="checkbox" onchange="showCredit()" id="check">
            </div>
            <%
                for(Customer customer:customerList){
            %>
            <div class="mt-4 hidden" id="credit-info-<%=customer.getId_customer()%>">
                <p class="text-gray-700">Credito Disponible: <span id="initial-credit">S/<%=customer.getAmount_available()%></span></p>
                <p class="text-gray-700">Monto Gastado: <span id="amount-spent">S/<%=customer.getAmount_used()%></span></p>
                <p class="text-gray-700">Credito Total: <span id="remaining-credit">S/<%=customer.getAmount_total()%></span></p>
            </div>
           <%
               }
           %>
            <button class="bg-teal-500 text-white px-6 py-2 rounded-lg hover:bg-teal-600 mt-4 w-full" onclick="checkout()">Finalizar Compra</button>
        </div>
    </div>
</div>
<script src = "js/function_shopping_cart.js"></script>
<script>
    <%
        if(session1.getAttribute("cart") != null){
    %>
    const productMap = <%=new ObjectMapper().writeValueAsString(session1.getAttribute("cart"))%>
    initializeCartItems(productMap);
<%
}
%>

</script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
<%
        }else{
            response.sendRedirect("SaleRegister");
        }
    }else{
        response.sendRedirect("login.jsp");
    }
%>
</html>
