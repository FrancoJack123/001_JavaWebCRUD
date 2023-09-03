<%-- 
    Document   : index.jsp
    Created on : 5 ago. 2023, 12:35:29
    Author     : Moreno Jack
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="entidad.Products"%>
<%@ page import="dao.daoProducto"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Styles Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <!-- Styles Datatable -->
        <link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
        <!-- Styles Alertify -->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>
        
        <title>CRUD</title>
    </head>
    <body>
        <div class="container mt-5">
            <div class="card mb-4">
                <h2>Hola</h2>
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    DataTable Example
                </div>  
                
                <div class="card-body">
                    <table id="datatablesSimple" class="table table-bordered">
                        <thead class="table-light">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Categoria</th>
                                <th>Stock</th>
                                <th>Precio</th>
                                <th>Fecha de Vencimiento</th>
                                <th> </th>
                            </tr>
                        </thead>
                        <tfoot class="table-light">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Categoria</th>
                                <th>Stock</th>
                                <th>Precio</th>
                                <th>Fecha de Vencimiento</th>
                                <th> </th>
                            </tr>
                        </tfoot>
                        <tbody id="tablaContainer">
                        </tbody>
                    </table>
                </div>
            </div>
            
            <hr />
            <footer>
                <p>&copy; 2023 - Mi aplicación Java</p>
            </footer>
            
        </div>        

        <!-- Modal Agregar -->
        <div class="modal fade" id="agregarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="row g-3" id="formAgregar">
                            <div class="col-md-12">
                                <label for="inputEmail4" class="form-label">Codigo</label>
                                <input type="number" class="form-control" id="CodigoAgregar" placeholder="Autogenerado" disabled="">
                            </div>
                            <div class="col-md-12">
                                <label for="inputPassword4" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="NombreAgregar" placeholder="Escriba el nombre del producto">
                            </div>
                            <div class="col-md-12">
                                <label for="inputPassword4" class="form-label">Categoria</label>
                                <select class="form-select" aria-label="Default select example" id="cboAgregar">
                                    <option selected disabled="">Seleccione...</option>
                                </select>
                            </div>
                            <div class="col-md-12">
                                <label for="inputCity" class="form-label">Fecha de vencimiento</label>
                                <input type="date" class="form-control" id="FechaAgregar">
                            </div>
                            <div class="col-8">
                                <label for="inputAddress2" class="form-label">Precio</label>
                                <input type="text" class="form-control" id="PrecioAgregar" placeholder="Escriba el precio del producto">
                            </div>
                            <div class="col-4 mb-2">
                                <label for="inputAddress" class="form-label">Stock</label>
                                <input type="number" class="form-control" id="StockAgregar" placeholder="Escriba el stock">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn" data-bs-dismiss="modal" style="background-color: #e9ecef; border: 1px solid #ced4da;">Cerrar</button>
                        <button type="button" onclick="agregarProduct()" class="btn" style="background-color: #e9ecef; border: 1px solid #ced4da;">Guardar</button>
                    </div>
                </div>
            </div>
        </div>
        
        
        
        <!-- Modal Editar -->
        <div class="modal fade" id="editarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Editar</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="row g-3">
                            <div class="col-md-12">
                                <label for="inputEmail4" class="form-label">Codigo</label>
                                <input type="number" class="form-control" id="CodigoEditar" placeholder="" disabled>
                            </div>
                            <div class="col-md-12">
                                <label for="inputPassword4" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="NombreEditar" placeholder="Escriba el nombre del producto">
                            </div>
                            <div class="col-md-12">
                                <label for="inputPassword4" class="form-label">Categoria</label>
                                <select class="form-select" aria-label="Default select example" id="cboEditar">
                                    <option selected disabled="">Seleccione...</option>
                                </select>
                            </div>
                            <div class="col-md-12">
                                <label for="inputCity" class="form-label">Fecha de vencimiento</label>
                                <input type="date" class="form-control" id="FechaEditar">
                            </div>
                            <div class="col-8">
                                <label for="inputAddress2" class="form-label">Precio</label>
                                <input type="text" class="form-control" id="PrecioEditar" placeholder="Escriba el precio del producto">
                            </div>
                            <div class="col-4 mb-2">
                                <label for="inputAddress" class="form-label">Stock</label>
                                <input type="number" class="form-control" id="StockEditar">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn" data-bs-dismiss="modal" style="background-color: #e9ecef; border: 1px solid #ced4da;">Cerrar</button>
                        <button type="button" class="btn" onclick="EditarProducto()" style="background-color: #e9ecef; border: 1px solid #ced4da;">Guardar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Desactivar -->
        <div class="modal fade" id="desactivarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Desactivar</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="codigoDesactivar">
                        <p>Estas seguro de desactivar este producto?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn" data-bs-dismiss="modal" style="background-color: #e9ecef; border: 1px solid #ced4da;">Cerrar</button>
                        <button type="button" onclick="DesactivarProducto()" class="btn" style="background-color: #e9ecef; border: 1px solid #ced4da;">Desactivar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Script Jquery -->
        <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
        <!-- Script Datatable -->
        <script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
        <!-- Script font-awesome -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <!-- Script Bootstrape -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <!-- Script Jquery Extensiones -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/locale/es.min.js"></script>
        <!-- Script Alertifyjs -->
        <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
        <!-- Script -->
        <script src="codigo.js"></script>
    </body>
</html>
