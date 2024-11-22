function ajax_report_sales(){
    const date_start = document.getElementById('filterStart');
    const selectDate = date_start.value;
    const date_end = document.getElementById('filterEnd');
    const selectDateEnd = date_end.value;
    const Employee = document.getElementById('filterEmployee');
    const selectEmployee = Employee.value;
    const Customer = document.getElementById('filterCustomer');
    const selectCustomer = Customer.value;
    console.log(selectDate);
    console.log(selectDateEnd);
    console.log(selectEmployee);
    console.log(selectCustomer);
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/ReportSales?id_employee=${selectEmployee}&id_customer=${selectCustomer}&date_start=${selectDate}&date_end=${selectDateEnd}`)
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'ReporteVentas.xlsx';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Error al generar el archivo:', error));
}