// Funcion para obtener el codigo de email
document.querySelectorAll('.boton').forEach(function (boton){
    boton.addEventListener("click",function (){
        fetch("/chichos_snack_project-1.0-SNAPSHOT/GenerateCode",{
            method: "POST",
            headers:{
                "Content-Type": "application/json"
            }
        })
            .then(response => response.text())
            .then(data => {
                console.log("Respuesta del servidor: ", data);
            })
            .catch(error => console.error("Error:", error));
    })
})