<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<section class="container">
	<h1 class="display-4 text-center text-white mb-2">Crear partido</h1>
	<form:form action="insertar-partido" method="POST" modelAttribute="partido">
		<label>CANCHA:</label>
		<form:input path="cancha" id="cancha" type="text" class="form-control mb-2" />
		<label>CANTIDAD DE JUGADORES:</label>
		<form:input path="cantidadJugadores" type="text" id="cantidadJugadores" class="form-control mb-2"/>
		<label>ORGANIZADOR:</label>
		<form:input path="organizador" type="text" id="organizador" class="form-control mb-2"/>

		<button class="btn btn-lg btn-primary" type="submit">Confirmar</button>
	</form:form>
</section>

<jsp:include page="footer.jsp" />
