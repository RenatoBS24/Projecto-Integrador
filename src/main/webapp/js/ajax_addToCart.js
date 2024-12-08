function add(){
    const id = document.getElementById('id').value;
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/addToCart?id_product=${id}`,{
        method: "GET",
        headers:{
            "Content-Type": "application/json"
        }
    })
        .then(response => response.text())
        .then(data => {
            console.log("Respuesta del servidor: ", data);
        })
        .catch(error => console.error("Error:", error));
}
