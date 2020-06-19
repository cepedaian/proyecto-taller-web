<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />
<c:if test="${not empty msj1}">
	<div class="bg-success p-2 text-white">${msj1}${id}${msj2}</div>
</c:if>
<section class="container-fluid p-3 section text-center">
	<h1 class="display-4 text-white mb-4 font-weight-bold">Partidos</h1>
	<div id="div1" style="overflow-y: scroll; height: 450px; width: 100%;">
		<table class="table text-white">
			<tr>
				<td>Id</td>
				<td>Cancha</td>
				<td>Organiza</td>
				<td>Cantidad de jugadores</td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${partidos}" var="partido">
				<tr>
					<form:form action="eliminar-partido/${partido.id}" method="POST">
						<td>${partido.id}</td>
						<td>${partido.cancha.nombre}</td>
						<td>${partido.organizador}</td>
						<td>${partido.cantidadJugadores}</td>
						<td><c:if
								test="${partido.organizador == cuenta.usuario.userName}">
								<button class="btn btn-sm btn-danger" type="submit" />Eliminar</button>
							</c:if></td>
						<td><c:if
								test="${partido.organizador == cuenta.usuario.userName}">
								<a class="btn btn-primary" href="invitar-usuario">Invitar
									Jugador</a>
							</c:if></td>
					</form:form>
				</tr>
			</c:forEach>
		</table>
	</div>
</section>

<jsp:include page="footer.jsp" />