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
    document.getElementById('ModalDetails').style.display = 'flex';
}

function cerrarModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

function openReportModal(){
    document.getElementById('reportModal').style.display = 'flex';
}

function closeReportModal(){
    document.getElementById('reportModal').style.display = 'none';
}
/*
document.addEventListener('DOMContentLoaded', function() {
    function employeeFilter(){
        document.getElementById('filterEmployee').addEventListener('change', function() {
            let filter = this.value;
            let tabla = document.getElementsByTagName('table')[0];
            let rows = tabla.getElementsByTagName('tr');
            console.log(filter)
            for (let i = 1; i < rows.length; i++) {
                let categorySelected = rows[i].getElementsByTagName('td')[5].textContent.trim();

                if (filter === '0' || categorySelected === filter)  {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        });
    }
    employeeFilter();
});


document.addEventListener('DOMContentLoaded', function() {
    function customerFilter(){
        document.getElementById('filterCustomer').addEventListener('change', function() {
            let filter = this.value;
            let tabla = document.getElementsByTagName('table')[0];
            let rows = tabla.getElementsByTagName('tr');
            console.log(filter)
            for (let i = 1; i < rows.length; i++) {
                let categorySelected = rows[i].getElementsByTagName('td')[6].textContent.trim();

                if (filter === '0' || categorySelected === filter)  {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        });
    }
    customerFilter();
});

document.addEventListener('DOMContentLoaded', function() {
    function dateFilter(){
        document.getElementById('filterStart').addEventListener('change', function() {
            let filter = new Date(this.value);
            let tabla = document.getElementsByTagName('table')[0];
            let rows = tabla.getElementsByTagName('tr');
            console.log(filter)
            for (let i = 1; i < rows.length; i++) {
                let dateSelected = new Date(rows[i].getElementsByTagName('td')[3].textContent.trim());

                if (isNaN(filter.getTime()) || dateSelected >= filter) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        });
    }
    dateFilter();
});

document.addEventListener('DOMContentLoaded', function() {
    function dateEndFilter(){
        document.getElementById('filterEnd').addEventListener('change', function() {
            let filter = new Date(this.value);
            let tabla = document.getElementsByTagName('table')[0];
            let rows = tabla.getElementsByTagName('tr');
            console.log(filter)
            for (let i = 1; i < rows.length; i++) {
                let dateSelected = new Date(rows[i].getElementsByTagName('td')[3].textContent.trim());

                if (isNaN(filter.getTime()) || dateSelected <= filter) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        });
    }
    dateEndFilter();
});
*/document.addEventListener('DOMContentLoaded', function() {
    function employeeFilter(){
        document.getElementById('filterEmployee').addEventListener('change', function() {
            applyFilters();
        });
    }
    employeeFilter();
});document.addEventListener('DOMContentLoaded', function() {
    function customerFilter(){
        document.getElementById('filterCustomer').addEventListener('change', function() {
            applyFilters();
        });
    }
    customerFilter();
});document.addEventListener('DOMContentLoaded', function() {
    function dateFilter(){
        document.getElementById('filterStart').addEventListener('change', function() {
            applyFilters();
        });
    }
    dateFilter();
});document.addEventListener('DOMContentLoaded', function() {
    function dateEndFilter(){
        document.getElementById('filterEnd').addEventListener('change', function() {
            applyFilters();
        });
    }
    dateEndFilter();
});
function applyFilters() {
    let employeeFilter = document.getElementById('filterEmployee').value;
    let customerFilter = document.getElementById('filterCustomer').value;
    let startDateFilter = new Date(document.getElementById('filterStart').value);
    let endDateFilter = new Date(document.getElementById('filterEnd').value);
    let tabla = document.getElementsByTagName('table')[0];
    let rows = tabla.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        let employeeSelected = rows[i].getElementsByTagName('td')[5].textContent.trim();
        let customerSelected = rows[i].getElementsByTagName('td')[6].textContent.trim();
        let dateSelected = new Date(rows[i].getElementsByTagName('td')[3].textContent.trim());

        let employeeMatch = (employeeFilter === '0' || employeeSelected === employeeFilter);
        let customerMatch = (customerFilter === '0' || customerSelected === customerFilter);
        let startDateMatch = (isNaN(startDateFilter.getTime()) || dateSelected >= startDateFilter);
        let endDateMatch = (isNaN(endDateFilter.getTime()) || dateSelected <= endDateFilter);

        if (employeeMatch && customerMatch && startDateMatch && endDateMatch) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }
}



function openModalDelete(id){
    document.getElementById('deleteSaleModal').classList.remove('hidden');
    document.getElementById('id_sale').value = id;
}
function closeDeleteModal(){
    document.getElementById('deleteSaleModal').classList.add('hidden');
}