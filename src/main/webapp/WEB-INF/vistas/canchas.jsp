<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
	<h1 class="display-4 text-center text-white mb-2">Canchas</h1>
	<table class="table">
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Tipo</td>
			<td>Tipo de suelo</td>
			<td></td>
		</tr>
		<c:forEach items="${canchas}" var="cancha">
			<tr>
				<form:form action="eliminar-cancha/${cancha.id}" method="POST">
					<td>
							${cancha.id}
					</td>
					<td>
							${cancha.nombre}
					</td>
					<td>
							${cancha.tipo}
					</td>
					<td>
							${cancha.tipoSuelo}
					</td>
					<td>
						<button class="btn btn-sm btn-danger" type="submit"/>Eliminar</button>
					</td>
				</form:form>
			</tr>
		</c:forEach>
	</table>
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
<jsp:include page="footer.jsp" />