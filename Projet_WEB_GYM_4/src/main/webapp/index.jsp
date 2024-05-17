<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Gestion de GYM</title>

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/template/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/template/css/sb-admin-2.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">


			<img src="${pageContext.request.contextPath}/images/logo.png">
			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link" href="index">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item "><a class="nav-link collapsed"
				href="Clients"> <i class="fas fa-fw fa-folder"></i> <span>Gestion
						des Clients</span>
			</a></li>
			<hr class="sidebar-divider">

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="Coachs">
					<i class="fas fa-fw fa-folder"></i> <span>Gestion des Coachs</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed"
				href="Seances"> <i class="fas fa-fw fa-folder"></i> <span>Gestion
						des Séances</span>
			</a></li>
			<hr class="sidebar-divider">

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="Paiements"> <i
					class="fas fa-fw fa-folder"></i> <span>Gestion des Paiements</span>
			</a></li>
			<hr class="sidebar-divider">
			<!-- Nav Item - Tables -->
			<li class="nav-item "><a class="nav-link" href="Cours"> <i
					class="fas fa-fw fa-folder"></i> <span>Gestion des Cours</span>
			</a></li>
			<hr class="sidebar-divider">
			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="Equipement">
					<i class="fas fa-fw fa-folder"></i> <span>Gestion des
						Equipements</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->
				
					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						




						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">Mon
									Compte </span> <img class="img-profile rounded-circle"
								src="${pageContext.request.contextPath}/images/user.png">

						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">

								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>

					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">
												Membres Inscrits</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:out value="${nombreClients }" />
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												Total des Gains</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:out value="${gains }" />
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												Total des Séances</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:out value="${nombreSeances }" />
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Pending Requests Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-success text-uppercase mb-1">
												Coachs</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:out value="${nombreCoachs }" />
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<!-- Content Row -->

					<div class="row">

						<!-- Area Chart -->
						<!-- Area Chart -->
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4" style="height: 630px;">
								<!-- Définissez la hauteur de la carte en fonction de la taille du graphique -->
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">Total des
										Gains en fonction des années</h6>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-area">
										<div class="d-flex justify-content-center">
											<!-- Définissez la hauteur à 100% pour centrer verticalement le contenu -->
											<canvas id="gainsChart"></canvas>
										</div>
									</div>

									<script>
										var yearGainsList = [ 2018, 600, 2019,
												600, 2020, 600, 2022, 4800,
												2023, 5720, 2024, 5400 ]; // Récupérer les données depuis la requête

										// Créer un tableau pour les années et pour les gains
										var years = [];
										var gains = [];

										// Parcourir les données et remplir les tableaux
										for (var i = 0; i < yearGainsList.length; i += 2) {
											years.push(yearGainsList[i]);
											gains.push(yearGainsList[i + 1]);
										}
										// Créer un graphique avec Chart.js
										var ctx = document.getElementById(
												'gainsChart').getContext('2d');
										var gainsChart = new Chart(
												ctx,
												{
													type : 'line',
													data : {
														labels : years,
														datasets : [ {
															label : 'Gains par année',
															data : gains,
															backgroundColor : 'rgba(54, 162, 235, 0.2)',
															borderColor : 'rgba(54, 162, 235, 1)',
															borderWidth : 1
														} ]
													},
													options : {
														scales : {
															y : {
																beginAtZero : true
															}
														}
													}
												});
									</script>

								</div>
							</div>
						</div>

						<!-- Pie Chart -->
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">Equipements</h6>

								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-pie">
										<canvas id="equipmentChart"></canvas>
									</div>

								</div>
								<script>
									var equipmentList = [ {
										id : 1,
										nom : "Treadmill",
										quantite : 9
									}, {
										id : 2,
										nom : "Elliptical Machine",
										quantite : 3
									}, {
										id : 3,
										nom : "Stationary Bike",
										quantite : 7
									}, {
										id : 4,
										nom : "Dumbbell Set",
										quantite : 10
									}, {
										id : 5,
										nom : "Barbell Set",
										quantite : 5
									}, {
										id : 6,
										nom : "Weight Bench",
										quantite : 3
									}, {
										id : 7,
										nom : "Pull-up Bar",
										quantite : 2
									}, {
										id : 8,
										nom : "Medicine Ball",
										quantite : 8
									}, {
										id : 9,
										nom : "Kettlebell Set",
										quantite : 6
									}, {
										id : 10,
										nom : "Resistance Band Set",
										quantite : 12
									} ];
									// Create an array of equipment names and quantities
									var labels = [];
									var data = [];
									for (var i = 0; i < equipmentList.length; i++) {
										labels.push(equipmentList[i].nom);
										data.push(equipmentList[i].quantite);
									}

									// Create a pie chart
									// Check if data is not null or undefined
									if (data !== null && data !== undefined
											&& data.length > 0) {
										// Create chart data
										var chartData = {
											labels : labels,
											datasets : [ {
												data : data,
												backgroundColor : [ '#4e73df',
														'#1cc88a', '#36b9cc',
														'#f6c23e', '#e74a3b',
														'#858796', '#f8b9e5',
														'#b9f7e5', '#d4b9e5',
														'#f9e5b9' ],
												hoverBackgroundColor : [
														'#2e59d9', '#17a673',
														'#2c9faf', '#dda20a',
														'#c0392b', '#666666',
														'#d18cd2', '#83d4d2',
														'#b183d2', '#d2b183' ],
											} ]
										};

										// Create chart
										var ctx = document.getElementById(
												'equipmentChart').getContext(
												'2d');
										var myPieChart = new Chart(ctx, {
											type : 'pie',
											data : chartData,
											options : {
												responsive : true,
												legend : {
													display : false
												// Hide legend
												}
											}
										});
									} else {
										console
												.log("No data available to create chart");
									}
								</script>
							</div>
						</div>
					</div>

					<!-- Content Row -->
					<!-- Area Chart -->
					<div class="col-xl-8 col-lg-7">
						<div class="card shadow mb-4" style="height: 600px;">
							<!-- Définissez la hauteur de la carte en fonction de la taille du graphique -->
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Total des
									Clients en fonction des années</h6>
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div class="chart-area">
									<div class="d-flex justify-content-center">
										<!-- Définissez la hauteur à 100% pour centrer verticalement le contenu -->
										<canvas id="clientsChart"></canvas>
									</div>
								</div>




								<script>
									var yearClientsList = [ 2018, 12, 2019, 12,
											2020, 12, 2021, 12, 2022, 2, 2024, 2 ]; // Récupérer les données depuis la requête

									// Créer un tableau pour les années et pour le nombre de clients
									var yeara = [];
									var totalClients = [];
									// Parcourir les données et remplir les tableaux
									for (var i = 0; i < yearClientsList.length; i += 2) {
										yeara.push(yearClientsList[i]);
										totalClients
												.push(yearClientsList[i + 1]);
									}
									// Parcourir les données et remplir les tableaux

									// Créer un graphique avec Chart.js
									var ctx = document.getElementById(
											'clientsChart').getContext('2d');
									var clientsChart = new Chart(
											ctx,
											{
												type : 'bar',
												data : {
													labels : years,
													datasets : [ {
														label : 'Nombre de clients par année',
														data : totalClients,
														backgroundColor : 'rgba(54, 162, 235, 0.2)',
														borderColor : 'rgba(54, 162, 235, 1)',
														borderWidth : 1
													} ]
												},
												options : {
													scales : {
														y : {
															beginAtZero : true
														}
													}
												}
											});
								</script>
							</div>
						</div>





					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->

			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.jsp">Logout</a>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath()%>/template/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/template/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/template/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath()%>/template/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="<%=request.getContextPath()%>/template/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="<%=request.getContextPath()%>/template/js/demo/chart-area-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/template/js/demo/chart-pie-demo.js"></script>

</body>

</html>