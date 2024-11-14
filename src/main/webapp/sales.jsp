
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
        #reporteModal, #editarModal {
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
                <a href="LogOut" class="flex items-center space-x-4 p-2 rounded-lg hover:bg-teal-500">
                        <span>
                            <ion-icon name="log-out-outline" class="text-xl"></ion-icon>
                        </span>
                    <span>Salir</span>
                </a>
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
                <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">+ Agregar productos</button>
                <div class="relative">
                    <a href="notificaciones.html">
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
                <select class="w-full p-2 mb-4 border rounded-lg">
                    <option>Todos</option>
                    <option>Trabajador 1</option>
                    <option>Trabajador 2</option>
                </select>
                <label class="block mb-2">Fecha:</label>
                <input type="date" id="fechaFiltro" class="w-full p-2 mb-4 border rounded-lg">
                <label class="block mb-2">Cliente:</label>
                <select class="w-full p-2 mb-4 border rounded-lg">
                    <option>Todos</option>
                    <option>Cliente 1</option>
                    <option>Cliente 2</option>
                </select>
                <label class="block mb-2">Producto:</label>
                <input type="text" placeholder="Nombre del producto" class="w-full p-2 border rounded-lg">
                <button class="bg-teal-500 w-full text-white p-2 mt-4 rounded-lg hover:bg-teal-600">Aplicar Filtros</button>
            </div>

            <!-- Lista de Ventas -->
            <div class="bg-white p-4 no-scrollbar rounded-lg shadow-md col-span-2 ventas-list min-h-[500px] flex flex-col">
                <h2 class="text-lg font-semibold mb-4">Ventas Realizadas</h2>
                <div class="space-y-4">
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <!-- Más registros de ventas aquí -->
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                    <div class="flex justify-between items-center bg-gray-100 p-3 rounded-lg">
                        <span>Venta #001 - Cliente: Juan Pérez</span>
                        <div>
                            <button class="text-blue-500" onclick="abrirModal('reporteModal')">Ver Detalles</button>
                            <button class="text-green-500 ml-4" onclick="abrirModal('editarModal')">Editar</button>
                            <button class="text-red-500 ml-4">Eliminar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal de Detalles de Venta -->
<div id="reporteModal" class="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white w-3/4 lg:w-1/2 p-6 rounded-lg">
        <h3 class="text-xl font-semibold mb-4">Detalles de Venta</h3>
        <div class="space-y-2">
            <p><strong>Vendedor:</strong> María López</p>
            <p><strong>Cliente:</strong> Juan Pérez</p>
            <p><strong>Fecha:</strong> 12/11/2024</p>
            <hr>
            <h4 class="font-semibold mt-4">Productos Vendidos:</h4>
            <ul class="list-disc ml-6">
                <li>Producto 1 - Cantidad: 2 - Precio: $10.00</li>
                <li>Producto 2 - Cantidad: 1 - Precio: $5.00</li>
            </ul>
            <p class="font-semibold mt-4">Total: $25.00</p>
        </div>
        <button onclick="cerrarModal('reporteModal')" class="mt-6 w-full bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">Cerrar</button>
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

<!-- Scripts -->
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script>
    function abrirModal(modalId) {
        document.getElementById(modalId).style.display = 'flex';
    }

    function cerrarModal(modalId) {
        document.getElementById(modalId).style.display = 'none';
    }
</script>

</body>
</html>

