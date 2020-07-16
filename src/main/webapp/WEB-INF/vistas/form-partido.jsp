<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<section class="container-fluid section">
	<h1 class="display-4 text-white mb-4 font-weight-bold">Crear partido</h1>
	<form:form action="/insertar-partido" method="POST" modelAttribute="partido" class="ml-3 mr-3">
		<div class="row">
			<div class="col-sm-12 col-md-6">
				<div class="form-group">
					<label class="col-form-label col-form-label-sm font-weight-bold text-white">Cancha:</label>
					<form:select path="cancha.id" class="form-control form-control-sm">
						<c:forEach items="${canchas}" var="cancha">
							<option value="${cancha.id}">${cancha.nombre} - ${cancha.direccion.barrio.descripcion} </option>
						</c:forEach>
					</form:select>
					<a class="btn btn-sm btn-primary mt-1" href="/show-form-cancha">Nueva cancha</a>
				</div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="form-group">
					<label class="col-form-label col-form-label-sm font-weight-bold text-white">Cantidad de jugadores:</label>
					<form:select path="cantidadJugadores" class="form-control form-control-sm">
						<option value="10">10 (Futbol 5)</option>
						<option value="16">16 (Futbol 8)</option>
						<option value="22">22 (Futbol 11)</option>
					</form:select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 col-md-6">
				<div class="form-group">
					<label for="fecha" class="col-form-label col-form-label-sm font-weight-bold text-white">Fecha:</label>
						<form:input path="fecha" type="datetime-local" class="form-control form-control-sm"/>
				</div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="form-group">
					<label for="sexo" class="col-form-label col-form-label-sm font-weight-bold text-white">Tipo de partido:</label>
					  <form:select path="sexo" class="form-control form-control-sm">

						  <option value="${cuenta.usuario.getSexo()}">${cuenta.usuario.getSexo()}</option>
					  </form:select>
				</div>
			</div>
		</div>

		<form:input path="organizador" type="hidden" value="${cuenta.usuario.userName}" />
		<button class="btn btn-sm btn-primary" type="submit"/>Crear</button>
	</form:form>
</section>

<jsp:include page="footer.jsp" />
