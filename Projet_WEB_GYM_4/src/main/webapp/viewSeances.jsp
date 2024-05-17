<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Gestion de GYM</title>
    <link href="<%=request.getContextPath()%>/template/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/template/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body id="page-top">
    <div id="wrapper">
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <img src="${pageContext.request.contextPath}/images/logo.png">
            <hr class="sidebar-divider my-0">
            <li class="nav-item"><a class="nav-link" href="index">
                <i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span></a></li>
            <hr class="sidebar-divider">
            <li class="nav-item"><a class="nav-link collapsed" href="Clients"><i class="fas fa-fw fa-folder"></i><span>Gestion des Clients</span></a></li>
            <hr class="sidebar-divider">
            <li class="nav-item"><a class="nav-link collapsed" href="Coachs"><i class="fas fa-fw fa-folder"></i><span>Gestion des Coachs</span></a></li>
            <hr class="sidebar-divider">
            <li class="nav-item active"><a class="nav-link collapsed" href="Seances"><i class="fas fa-fw fa-folder"></i><span>Gestion des Séances</span></a></li>
            <hr class="sidebar-divider">
            <li class="nav-item"><a class="nav-link" href="Paiements"><i class="fas fa-fw fa-folder"></i><span>Gestion des Paiements</span></a></li>
            <hr class="sidebar-divider">
            <li class="nav-item"><a class="nav-link" href="Cours"><i class="fas fa-fw fa-folder"></i><span>Gestion des Cours</span></a></li>
            <hr class="sidebar-divider">
            <li class="nav-item"><a class="nav-link" href="Equipement"><i class="fas fa-fw fa-folder"></i><span>Gestion des Equipements</span></a></li>
            <hr class="sidebar-divider d-none d-md-block">
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
        </ul>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>
                    <h1 class="h3 mb-2 text-gray-800">Gestion des Séances</h1>
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Mon Compte</span>
                                <img class="img-profile rounded-circle" src="${pageContext.request.contextPath}/images/user.png">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </nav>
                <div class="container-fluid">
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between">
                            <h6 class="m-0 font-weight-bold text-primary">Liste des Séances</h6>
                            <div>
                                <a href="SeanceServlet?action=generate" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                    <i class="fas fa-download fa-sm text-white-50"></i> Generate Report
                                </a>
                                <a <a href="SeanceServlet?action=add"  class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm ml-auto">
                                    <i><img src="${pageContext.request.contextPath}/images/icons8-ajouter-24.png"></i> Ajouter
                                </a>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Coach</th>
                                            <th>Horaire</th>
                                            <th>Cours</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${Seances}" var="seance">
                                            <tr>
                                                <td><c:out value="${seance.id}"/></td>
                                                <td><c:out value="${seance.coachName}"/></td>
                                                <td><c:out value="${seance.horaire}"/></td>
                                                <td><c:out value="${seance.courseLabel}"/></td>
                                                <td>
                                                    <a href="?action=delete&id=<c:out value="${seance.id}"/>">Supprimer</a>
                                                    <a href="?action=edit&id=<c:out value="${seance.id}"/>">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.jsp">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/template/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/vendor/datatables/dataTables.bootstrap4.min.js"></script>
    <script src="<%=request.getContextPath()%>/template/js/demo/datatables-demo.js"></script>
</body>
</html>
