<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<section class="container">
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
</section>
		

<jsp:include page="footer.jsp" />