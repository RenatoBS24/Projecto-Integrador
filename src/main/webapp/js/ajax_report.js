function ajax_report(){
    const categorySelect = document.getElementById('filter');
    const selectedCategory = categorySelect.value;
    fetch(`/chichos_snack_project-1.0-SNAPSHOT/reportProduct?id_category=${selectedCategory}`)
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'Prueba.xlsx';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Error al generar el archivo:', error));
}