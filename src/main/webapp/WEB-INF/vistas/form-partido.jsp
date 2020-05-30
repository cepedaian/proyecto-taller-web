<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<section class="container">
	<h1 class="display-4 text-center text-white mb-2">Crear partido</h1>
	<form:form action="insertar-partido" method="POST" modelAttribute="partido">
		<div class="form-group col-md-12 col-sm-12">
			  	<label for="cancha" class="col-form-label col-form-label-sm font-weight-bold">Cancha:</label>
			  	<form:select path="cancha.id" class="form-control form-control-sm" id="cancha">
				  	<c:forEach items="${canchas}" var="cancha">
						<option value="${cancha.id}">${cancha.nombre}</option>
				  	</c:forEach>
			  	</form:select>
			</div>
		<div class="form-group col-md-12 col-sm-12">
			<label class="col-form-label col-form-label-sm font-weight-bold">Cantidad de Jugadores:</label>
			<form:input path="cantidadJugadores" type="text" id="cantidadJugadores" class="form-control mb-2"/>
		</div>
		<div class="form-group col-md-12 col-sm-12">
			<label class="col-form-label col-form-label-sm font-weight-bold">Organizador:</label>
			<form:input path="organizador" type="text" id="organizador" class="form-control mb-2"/>
		</div>
		<button class="btn btn-sm btn-primary" type="submit"/>Crear</button>

	</form:form>
</section>

<jsp:include page="footer.jsp" />
