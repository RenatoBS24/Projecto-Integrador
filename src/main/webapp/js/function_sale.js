function abrirModal(employee,customer,date,total,products) {
    total = 'Total : S/.' + total;
    document.getElementById('employeeId').innerHTML = '<strong>Vendedor: </strong>'+ employee;
    document.getElementById('customerId').innerHTML = '<strong>Cliente: </strong>'+ customer;
    document.getElementById('dateId').innerHTML = '<strong>Fecha: </strong>'+ date;
    document.getElementById('totalId').textContent = total;
    const data = products.replace(/'/g, '"');
    const json = JSON.parse(data);
    console.log(json)
    let ul = document.getElementById('list');
    ul.innerHTML = "";
    if(json.length >0){
        json.forEach((item) => {
            let li = document.createElement("li");
            li.textContent = item.name + ' - ' + item.stock + ' x S/.' + item.price;
            ul.appendChild(li);
        });
    }
    document.getElementById('reporteModal').style.display = 'flex';
}

function cerrarModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}