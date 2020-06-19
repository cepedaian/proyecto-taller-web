<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<section class="container-fluid section">
	<h1 class="display-4 text-center text-white mb-2">Crear partido</h1>
	<form:form action="/insertar-partido" method="POST" modelAttribute="partido">
		<div class="form-group col-md-12 col-sm-12">
			  	<label for="cancha" class="col-form-label col-form-label-sm font-weight-bold">Cancha:</label>
			  	<form:select path="cancha.id" class="form-control form-control-sm" id="cancha">
				  	<c:forEach items="${canchas}" var="cancha">
						<option value="${cancha.id}">${cancha.nombre} - ${cancha.direccion.barrio.descripcion} </option>
				  	</c:forEach>
			  	</form:select>
			<button><a class="btn btn-sm btn-primary mt-1" href="/show-form-cancha">Nueva Cancha</a></button>
			</div>
		<div>
		</div>
		<div class="form-group col-md-12 col-sm-12">
			<label class="col-form-label col-form-label-sm font-weight-bold">Cantidad de Jugadores:</label>
			<form:input path="cantidadJugadores" type="text" id="cantidadJugadores" class="form-control mb-2"/>
		</div>
		<div class="form-group row">
			<label for="fecha" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Fecha:</label>
			<div class="col-sm-10">
				<form:input path="fecha" type="date" class="form-control form-control-sm" id="fecha" />
			</div>
		</div>
		<div class="form-group row">
			<label for="sexo" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Tipo de partido:</label>
			<div class="col-sm-10">
			  <form:select path="sexo" class="form-control form-control-sm" id="sexo">
				  <option value="M">Masculino</option>
				  <option value="F">Femenino</option>
			  </form:select>
			</div>
		</div>
		<div class="form-group col-md-12 col-sm-12">
			<label class="col-form-label col-form-label-sm font-weight-bold">Organizador:</label>
			<form:input path="organizador" type="text" id="organizador" class="form-control mb-2" value="${cuenta.usuario.userName}" disabled=""/>
		</div>
		<button class="btn btn-sm btn-primary" type="submit"/>Crear</button>

	</form:form>
</section>

<jsp:include page="footer.jsp" />
