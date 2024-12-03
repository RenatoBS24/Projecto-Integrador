<%@ page import="java.util.List" %>
<%@ page import="com.chichos_snack_project.model.Product" %>
<%@ page import="com.chichos_snack_project.model.Category" %>
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
        if(request.getAttribute("productList") !=null && request.getAttribute("categoryList") !=null){
            @SuppressWarnings("unchecked")
            List<Product> productList = (List<Product>) request.getAttribute("productList");
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
                    <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600" onclick="openModal()">+ Agregar productos</button>
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
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <%
                for(Product product: productList){


            %>
            <div class="bg-white rounded-lg shadow p-4">
                <img src="https://via.placeholder.com/150" alt="Producto" class="w-full h-40 object-cover rounded">
                <h3 class="mt-4 font-bold text-gray-700"><%=product.getName()%></h3>
                <p class="text-sm text-gray-500">Stock: <%=product.getStock()%>  </p>
                <p class="text-lg font-bold text-teal-600">S/<%=product.getPrice()%></p>
                <button onclick="addToCart('<%=product.getName()%>','<%=product.getPrice()%>')" class="bg-teal-500 text-white mt-4 w-full py-2 rounded hover:bg-teal-600">
                    Agregar al carrito
                </button>
            </div>
            <%
                }
            %>
            <!-- Repeat this structure for other products -->
        </div>

        <!-- Cart Section -->
        <div class="mt-10">
            <h2 class="text-2xl font-bold mb-4">Carrito de Compras</h2>
            <div class="bg-white rounded-lg shadow p-4">
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
                    <tr>
                        <td colspan="5" class="text-center py-4 text-gray-500">El carrito está vacío</td>
                    </tr>
                    </tbody>
                </table>
                <div class="flex justify-between items-center mt-6">
                    <span class="text-xl font-bold">Total: <span id="cart-total" class="text-teal-600">$0.00</span></span>
                    <button class="bg-teal-500 text-white px-6 py-2 rounded-lg hover:bg-teal-600">Finalizar Compra</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const cartItems = [];
    const cartTotalElement = document.getElementById('cart-total');
    const cartItemsElement = document.getElementById('cart-items');

    function addToCart(name, price) {
        const item = cartItems.find(item => item.name === name);
        if (item) {
            item.quantity++;
        } else {
            cartItems.push({ name, price, quantity: 1 });
        }
        renderCart();
    }

    function renderCart() {
        cartItemsElement.innerHTML = cartItems.map(item => `
                <tr>
                    <td class="py-2">${item.name}</td>
                    <td class="py-2">${item.quantity}</td>
                    <td class="py-2">$${item.price.toFixed(2)}</td>
                    <td class="py-2">$${(item.price * item.quantity)}</td>
                    <td class="py-2">
                        <button class="text-red-500 hover:underline" onclick="removeFromCart('${item.name}')">Eliminar</button>
                    </td>
                </tr>
            `).join('');
        const total = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
        cartTotalElement.textContent = `$${total.toFixed(2)}`;
    }

    function removeFromCart(name) {
        const index = cartItems.findIndex(item => item.name === name);
        if (index > -1) cartItems.splice(index, 1);
        renderCart();
    }
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
