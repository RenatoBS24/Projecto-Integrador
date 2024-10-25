
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-image: url('../img/Chicos\ Snack.png'); background-size: cover; background-position: center;">
<section class="d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg" style="width: 400px;">
        <div class="card-body">
            <form action="SingIn" method="POST" id="login-form">
                <h2 class="text-center mb-4">Iniciar Sesión</h2>
                <div class="form-group">
                    <label for="nameUser">Usuario</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="person-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="text" class="form-control" name="username" id="nameUser" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="password" class="form-control" name="password" id="password" required>
                    </div>
                </div>
                <div>
                    <%
                        String message = (String)request.getAttribute("errorMessage");
                        if(message !=null){
                    %>
                    <p style="color: red"><%=message%></p>
                    <%
                        }
                    %>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
                </div>
                <div class="text-center mt-3">
                    <a href="ForgotPassword">¿Olvidaste tu contraseña?</a>
                </div>
                <div class="text-center mt-3">
                    <p>No tengo una cuenta <a href="register_user.jsp">Registrarse</a></p>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
