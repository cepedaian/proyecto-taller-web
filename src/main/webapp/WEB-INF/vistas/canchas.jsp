<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<h1>Canchas</h1>
			<ul>
				<c:forEach items="${canchas}" var="cancha">
					<form:form action="eliminar-cancha/${cancha.id}" method="POST">
					<li>IdCancha = ${cancha.id} - Nombre = ${cancha.nombre} - TipoPartido ${cancha.tipo} - SueloCancha ${cancha.tipoSuelo}</li>
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Eliminar</button>
					</form:form>
				</c:forEach>
			</ul>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		
		<script>
		function removerCuit(id) {
			window.location.replace("/proyecto-limpio-spring/eliminar-partido/ + id");
		}</script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>