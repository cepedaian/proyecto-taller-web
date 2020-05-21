<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<section class="container">
		<h1 class="display-4 text-center text-white mb-2">Partidos</h1>
		<table class="table">
				<tr>
					<td>Id</td>
					<td>Cancha</td>
					<td>Organiza</td>
					<td>Cantidad de jugadores</td>
					<td></td>
				</tr>
				<c:forEach items="${partidos}" var="partido">
				<tr>
					<form:form action="eliminar-partido/${partido.id}" method="POST">
					<td>
						${partido.id}
					</td>
					<td>
						${partido.cancha}
					</td>
					<td>
						${partido.organizador}
					</td>
					<td>
						${partido.cantidadJugadores}
					</td>
					<td>
						<button class="btn btn-sm btn-danger" type="submit"/>Eliminar</button>
					</td>
					</form:form>
				</tr>
				</c:forEach>
		</table>
</section>

<jsp:include page="footer.jsp" />