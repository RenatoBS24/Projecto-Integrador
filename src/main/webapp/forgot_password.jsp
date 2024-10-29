
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambiar Contraseña</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-image: url('../img/Chicos\ Snack.png'); background-size: cover; background-position: center;">

<section class="d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg" style="width: 400px;">
        <div class="card-body">
            <form action="ForgotPassword" method="post" id="password-form">
                <h2 class="text-center mb-4">Cambiar Contraseña</h2>
                <div class="form-group">
                    <label for="username">Usuario: </label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="person-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="text" class="form-control" name="username" id="username"  required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">Nueva Contraseña</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="key-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="password" class="form-control" name="password" id="password" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordRepeat">Confirmar Nueva Contraseña</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="key-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="password" class="form-control" name="passwordRepeat" id="passwordRepeat" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="code">Codigo dinamico(Este codigo ha sido enviado al correo del administrador)</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="key-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="text" class="form-control" name="code" id="code" required>
                    </div>
                </div>
                <div>
                    <%
                        String message = (String)request.getAttribute("error");
                        if(message != null){
                    %>
                    <p STYLE="color: red"><%=message%></p>
                </div>
                <%
                    }
                %>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block">Cambiar Contraseña</button>
                </div>
                <div class="text-center mt-3">
                    <a href="login.jsp">Volver al inicio de sesión</a>
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

