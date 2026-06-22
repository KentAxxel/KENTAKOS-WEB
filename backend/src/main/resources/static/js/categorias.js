document.addEventListener("DOMContentLoaded", function () {
    cargarCategorias();
});

function cargarCategorias() {

    fetch("/kentakitos/categorias")
        .then(res => res.json())
        .then(data => {

            const tabla = document.getElementById("tablaCategorias");

            tabla.innerHTML = "";

            data.forEach(cat => {

                tabla.innerHTML += `
                    <tr>
                        <td>${cat.idcategoria}</td>
                        <td>${cat.nombrecategoria}</td>
                        <td>${cat.deleted == 1 ? "Activo" : "Eliminado"}</td>
                    </tr>
                `;
            });

        })
        .catch(err => {
            console.error("Error cargando categorías:", err);
        });
}