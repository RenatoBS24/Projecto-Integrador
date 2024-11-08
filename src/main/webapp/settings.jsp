
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajustes - Chichos Snack</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <%
        HttpSession session1  = request.getSession();
        boolean is_valid_user = false;
        if(session1.getAttribute("is_valid_user") != null){
    %>
</head>
<body class="bg-gray-100 flex min-h-screen">
<%
    HttpSession session2 = request.getSession();
    String username = (String)session2.getAttribute("username");
    if(username !=null){

%>
<!-- Sidebar -->
<div class="bg-teal-600 w-64 fixed top-0 left-0 h-screen text-white p-6 overflow-y-auto">
    <h1 class="text-2xl font-bold mb-10">CHICHOS SNACK</h1>
    <ul>
        <li class="mb-6">
            <a class="flex items-center space-x-4 p-2 rounded-lg bg-gradient-to-r from-green-400 to-teal-500">
                    <span>
                        <ion-icon name="menu-outline" class="text-xl"></ion-icon>
                    </span>
                <span class="text-lg font-semibold">Menú</span>
            </a>
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
            <a href="settings.jsp" class="flex items-center space-x-4 p-2 rounded-lg bg-teal-500">
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
    <!-- Profile Settings -->
    <section class="mb-8">
        <h2 class="text-xl font-bold text-teal-600 mb-4">Perfil de Usuario</h2>
        <div class="bg-white shadow-lg rounded-lg p-6">
            <div class="mb-4">
                <label class="block text-gray-700 font-semibold">Nombre:</label>
                <input type="text" class="w-full p-3 border rounded-lg focus:outline-none" placeholder="Nombre del usuario" value=<%=username%>>
            </div>
            <div class="mb-4">
                <label class="block text-gray-700 font-semibold">Correo Electrónico de la empresa:</label>
                <p class="w-full p-3 border rounded-lg focus:outline-none">renatoballena2405@gmail.com</p>
            </div>
            <%
                }
            %>
            <div class="flex justify-end mt-4">
                <button class="bg-teal-500 text-white px-6 py-3 rounded-lg hover:bg-teal-600">Guardar Cambios</button>
            </div>
        </div>
    </section>

    <section class="mb-8">
        <h2 class="text-xl font-bold text-teal-600 mb-4">Contraseña</h2>
        <div class="flex justify-between space-x-4">
            <div class="flex justify-end mt-4">
                <button type="submit" class="bg-teal-500 text-white px-6 py-3 rounded-lg hover:bg-teal-600">Cambiar contraseña</button>
            </div>
        </div>
    </section>


    <!-- Security Settings -->
    <section class="mb-8">
        <h2 class="text-xl font-bold text-teal-600 mb-4">Seguridad</h2>
        <div class="bg-white shadow-lg rounded-lg p-6">
            <div class="flex items-center justify-between mb-4">
                <div>
                    <p class="text-gray-700 font-semibold">Verificación en dos pasos (2FA)</p>
                    <p class="text-gray-500 text-sm">Aumenta la seguridad de tu cuenta con autenticación de dos pasos.</p>
                </div>
                <button class="bg-teal-500 text-white px-4 py-2 rounded-lg hover:bg-teal-600">Configurar</button>
            </div>
        </div>
    </section>

    <!-- Preferences Settings -->
    <section class="mb-8">
        <h2 class="text-xl font-bold text-teal-600 mb-4">Preferencias de la Aplicación</h2>
        <div class="bg-white shadow-lg rounded-lg p-6">
            <div class="mb-4">
                <label class="block text-gray-700 font-semibold">Idioma:</label>
                <select class="w-full p-3 border rounded-lg focus:outline-none">
                    <option value="es">Español</option>
                    <option value="en">Inglés</option>
                </select>
            </div>
            <div class="flex justify-between">
                <div>
                    <p class="text-gray-700 font-semibold">Tema:</p>
                    <p class="text-gray-500 text-sm">Selecciona entre el tema oscuro o claro.</p>
                </div>
                <div class="flex items-center space-x-2">
                    <span>Tema Claro</span>
                    <label class="relative inline-flex items-center cursor-pointer">
                        <input type="checkbox" class="sr-only peer">
                        <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-teal-300 rounded-full peer dark:bg-gray-700 peer-checked:bg-teal-500"></div>
                        <span class="ml-3 text-sm text-gray-700 peer-checked:text-teal-500">Tema Oscuro</span>
                    </label>
                </div>
            </div>
        </div>
    </section>
</main>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
<%
    }else{
            response.sendRedirect("login.jsp");
    }
%>
