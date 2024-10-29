<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
    if(request.getSession().getAttribute("code") != null){


%>
<body style="background-image: url('../img/Chicos\ Snack.png'); background-size: cover; background-position: center;">
<section class="d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg" style="width: 400px;">
        <div class="card-body">
            <form action="register" method="POST">
                <h2 class="text-center mb-4">Registro</h2>
                <div class="form-group">
                    <label for="nameUser">Nombre de Usuario</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="person-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="text" class="form-control" name="username"  id="nameUser" required>
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
                <div class="form-group">
                    <label for="passwordRepeat">Confirmar Contraseña</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="password" class="form-control" name="passwordRepeat" id="passwordRepeat" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rol">Rol</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                </span>
                        </div>
                        <select id="rol" class="form-control" name ="rol">
                            <option value="1">Administrador</option>
                            <option value="2">Vendedor</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="code_entered">Codigo dinamico(Este codigo ha sido enviado al correo del administrador)</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                </span>
                        </div>
                        <input type="text" class="form-control" name="code_entered" id="code_entered" maxlength="8" required>
                    </div>
                </div>
                <%
                    String error = (String)request.getAttribute("error");
                    if(error != null){


                %>
                <p style="color: red"><%=error%></p>
                <%
                    }
                %>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block">Registrarse</button>
                </div>
                <div class="text-center mt-3">
                    <p>Ya tengo una cuenta <a href="login.jsp">Iniciar Sesión</a></p>
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
<%
    }else{
        response.sendRedirect("register");
    }
%>