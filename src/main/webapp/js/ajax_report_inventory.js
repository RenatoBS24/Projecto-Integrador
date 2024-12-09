function ajax_report_inventory(){
    const date_start = document.getElementById('filterStart');
    const selectDate = date_start.value;
    const date_end = document.getElementById('filterEnd');
    const selectDateEnd = date_end.value;
    const Product = document.getElementById('filterProduct');
    const selectProduct = Product.value;
    console.log(selectDate);
    console.log(selectDateEnd);
    console.log(selectProduct);
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/reportInventory?id_product=${selectProduct}&date_start=${selectDate}&date_end=${selectDateEnd}`)
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'ReporteInventario.xlsx';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Error al generar el archivo:', error));
}