/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var tablaProductos;

$(document).ready(function () {
    tablaProductos = $('#datatablesSimple').DataTable({
        "ajax": {
            "url": "ServletProduct",
            "type": "POST",
            "data": { action: "list" },
            "dataType": "json",
            "dataSrc": ""
        },
        "deferRender": true,
        "columns": [
            {"data": "cod_producto"},
            {"data": "descrip_prod"},
            {"data": "descrip_categ"},
            {"data": "pventa"},
            {"data": "stock"},
            {"data": "fecha_venc"},
            {
                data: "cod_producto",
                render: function (data, type, row) {
                    // Aquí puedes construir el contenido de la columna con los botones, igual que en el código original
                    return `<div class="d-flex gap-3 justify-content-center">` +
                            `<a id="miBoton" class="btn" onclick="abriModalEditar(` + data + `)" data-bs-toggle="modal" data-bs-target="#editarModal" style="background-color: #e9ecef; border: 1px solid #ced4da;">` +
                            `<i class="fas fa-pen text-secondary"></i>` +
                            `</a>` +
                            `<a class="btn" onclick="abriModalDesactivar(` + data + `)" data-bs-toggle="modal" data-bs-target="#desactivarModal" style="background-color: #e9ecef; border: 1px solid #ced4da;">` +
                            `<i class="fas fa-exclamation-circle text-secondary"></i>` +
                            `</a></div>`;
                },
                "orderable": false,
                "searchable": false
            }
        ],
        pageLength: 10,
        lengthChange: false,
        responsive: true
    });
    
     $.ajax({
       url : "ServletProduct",
       type: "POST",
       data: { action: "listCateg" },
       dataType: "json",
       dataSrc: "",
       success: function (data){
           var cboAgregar = $('#cboAgregar');
           $.each(data, function(index, item){
              cboAgregar.append($('<option>',{
                    value: item.cod_categoria,
                    text: item.descrip_categ
                }));
           });
           
           var cboEditar = $('#cboEditar');
           $.each(data, function(index, item){
              cboEditar.append($('<option>',{
                    value: item.cod_categoria,
                    text: item.descrip_categ
                }));
           });
       },
       error: function (xhr, status, error) {
            console.error(error);
       }
    });
    

    /* CODIGO DE PERSONALIZACION DE DATATABLE */

    let divDataTAable = document.querySelector('.col-sm-12');
    let divCrado = document.createElement('div');
    divCrado.setAttribute('class', 'dataTables_length');
    divCrado.setAttribute('id', 'datatablesSimple_length');

    divDataTAable.appendChild(divCrado);

    let rowContainer = document.querySelector('.dataTables_length');

    let customButton = document.createElement('button');
    customButton.innerHTML = 'Agregar';
    customButton.setAttribute('type', 'button');
    customButton.setAttribute('class', 'btn mb-2');
    customButton.setAttribute('style', 'background-color: #e9ecef; border: 1px solid #ced4da;');
    customButton.setAttribute('data-bs-toggle', 'modal');
    customButton.setAttribute('data-bs-target', '#agregarModal');

    rowContainer.appendChild(customButton);

    // Reemplazamos el diseño del buscador
    const datatablesSimpleFilter = document.getElementById('datatablesSimple_filter');

    const customSearch = `
                <div class="input-group mb-3" style="flex-wrap: initial;">
                    <span class="input-group-text" id="basic-addon1">Search</span>
                    <input type="text" class="form-control" id="custom-search-input">
                    <span class="input-group-text" id="basic-addon1"><i class="fas fa-search" style="color: #6c757d;"></i></span>
                </div>
                `;

    // Asiganamos la funcion de buscar al diseño del buscador modificado 
    datatablesSimpleFilter.innerHTML = customSearch;

    const customSearchInput = document.getElementById('custom-search-input');

    customSearchInput.addEventListener('keyup', function () {
        const table = $('#datatablesSimple').DataTable();

        const searchValue = this.value;

        table.search(searchValue).draw();
    });

    /* FIN DEL CODIGO DE PERSONALIZACION DE DATATABLE*/

});

function abriModalEditar(codigo1) {
    moment.locale('es');
    $.ajax({
        url: "ServletProduct", // Cambia la URL al servlet que buscará el producto
        type: "POST", // Utiliza POST para enviar el código al servlet
        data: {
            action: "search",
            codigo: codigo1
        },
        dataType: "json",
        success: function (data) {
            // Mostrar el resultado en la página web
            if (data) {
                let codigoProducto = document.getElementById('CodigoEditar');
                let nombreProducto = document.getElementById('NombreEditar');
                let stockProducto = document.getElementById('StockEditar');
                let precioProducto = document.getElementById('PrecioEditar');
                let fechaProducto = document.getElementById('FechaEditar');
                codigoProducto.value = data.cod_producto;
                nombreProducto.value = data.descrip_prod;
                stockProducto.value = data.stock;
                $('cboEditar').val(data.cod_categoria);
                precioProducto.value = data.pventa;
                let fechaFormateada = moment(data.fecha_venc, "MMM. DD, YYYY").format("YYYY-MM-DD");
                fechaProducto.value = fechaFormateada;
            }
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
};

function abriModalDesactivar(codigo) {
    let codigoProductod = document.getElementById('codigoDesactivar');
    codigoProductod.value = codigo;
};

function agregarProduct() {
    let nombreProducto = document.getElementById('NombreAgregar').value;
    let stockProducto = document.getElementById('StockAgregar').value;
    let precioProducto = document.getElementById('PrecioAgregar').value;
    let fechaProducto = document.getElementById('FechaAgregar').value;

    $.ajax({
        url: "ServletProduct",
        type: "POST",
        data: {
            action: "add",
            nombre: nombreProducto,
            stock: stockProducto,
            precio: precioProducto,
            fecha: fechaProducto
        },
        dataType: "text",
        success: function (data) {
            tablaProductos.ajax.reload();
            alertify.success(data);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
    $("#agregarModal").modal('hide');
    $("#CodigoAgregar").val('');
    $("#NombreAgregar").val('');
    $("#StockAgregar").val('');
    $("#PrecioAgregar").val('');
    $("#FechaAgregar").val('');
};

function EditarProducto() {
    let codigoProductos = document.getElementById('CodigoEditar').value; // Obtén el valor
    let nombreProductos = document.getElementById('NombreEditar').value; // Obtén el valor
    let stockProductos = document.getElementById('StockEditar').value; // Obtén el valor
    let precioProductos = document.getElementById('PrecioEditar').value; // Obtén el valor
    let fechaProductos = document.getElementById('FechaEditar').value;

    $.ajax({
        url: "ServletProduct",
        type: "POST",
        data: {
            action: "update",
            codigo: codigoProductos,
            nombre: nombreProductos,
            stock: stockProductos,
            precio: precioProductos,
            fecha: fechaProductos
        },
        dataType: "text",
        success: function (data) {
            tablaProductos.ajax.reload();
            alertify.success(data);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });

    $("#editarModal").modal('hide');
    $("#CodigoEditar").val('');
    $("#NombreEditar").val('');
    $("#StockEditar").val('');
    $("#PrecioEditar").val('');
    $("#FechaEditar").val('');
};

function DesactivarProducto() {
    let codigoProductod = document.getElementById('codigoDesactivar').value;
    $.ajax({
        url: "ServletProduct",
        type: "POST",
        data: {
            action: "deactivate",
            codigo: codigoProductod
        },
        dataType: "text",
        success: function (data) {
            tablaProductos.ajax.reload();
            alertify.error(data);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
    $("#desactivarModal").modal('hide');
    $("#codigoDesactivar").val('');
};


/*dasdasdsa*/

