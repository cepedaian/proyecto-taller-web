<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<section class="container">
	<h1 class="display-4 text-center text-white mb-2">Crear partido</h1>
	<form:form action="insertar-cancha" method="POST" modelAttribute="cancha">
		<label>NOMBRE:</label>
		<form:input path="nombre" id="nombre" type="text" class="form-control mb-2" />
		<label>TIPO DE CANCHA (5-8-11 JUGADORES):</label>
		<form:input path="tipo" type="text" id="tipo" class="form-control mb-2"/>
		<label>TIPO DE SUELO:</label>
		<form:input path="tipoSuelo" type="text" id="tipoSuelo" class="form-control mb-2"/>

		<button class="btn btn-lg btn-primary" type="submit"/>Confirmar</button>
	</form:form>
</section>

<jsp:include page="footer.jsp" />