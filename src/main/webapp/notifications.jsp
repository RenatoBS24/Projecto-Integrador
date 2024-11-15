<%@ page import="com.chichos_snack_project.model.Notification" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chichos Snack Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<%
    HttpSession session1  = request.getSession();
    if(session1.getAttribute("is_valid_user") != null){

        if(request.getAttribute("notificationList") !=null){
            @SuppressWarnings("unchecked")
            List<Notification> notificationList = (List<Notification>) request.getAttribute("notificationList");

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
        <!-- Header -->
        <div class="flex justify-between items-center mb-8 sticky top-0 bg-gray-100 z-10 p-4 shadow">
            <div class="relative">
                <input type="text" placeholder="Search Here" class="p-2 border border-gray-300 rounded-lg focus:outline-none w-full lg:w-96">
                <span class="absolute right-2 top-2 text-gray-400">
                        <ion-icon name="search-outline"></ion-icon>
                    </span>
            </div>
            <div class="flex items-center space-x-4">
                <button class="bg-teal-500 text-white p-2 rounded-lg hover:bg-teal-600">+ Agregar productos</button>
                <div class="relative">
                    <ion-icon name="notifications-outline" class="text-2xl text-gray-600"></ion-icon>
                    <span class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">1</span>
                </div>
            </div>
        </div>

        <!-- Notifications Section -->
        <div>
            <h2 class="text-2xl font-bold mb-4">Notificaciones</h2>
            <div class="space-y-4">
                <%
                    if(!notificationList.isEmpty()){

                        for(Notification notification:notificationList){


                %>
                <div class="bg-gray-200 p-4 rounded-lg">
                    <div class="flex justify-between items-center mb-2">
                        <span class="text-gray-700 font-bold">Fecha: <%=notification.getDate()%></span>
                        <span class="text-gray-700 font-bold">Asunto: <%=notification.getAffair()%></span>
                    </div>
                    <p><%=notification.getMessage()%></p>
                </div>
                <%
                        }
                    }else{


                %>
                <h1>No hay Notificaciones</h1>
                <%
                    }
                %>
            </div>
        </div>

    </div>
</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</body>
<%
        }else{
            response.sendRedirect("Notifications");
        }
    }else{
        response.sendRedirect("login.jsp");
        }
%>

</html>

