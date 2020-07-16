<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<section class="container-fluid section p-4">
	<h1 class="display-4 text-white mb-4 font-weight-bold">Crear cancha</h1>
	<form:form action="crear-cancha" method="POST" modelAttribute="cancha">
		<div class="form-group row">
			<label for="nombre" class="col-sm-2 col-form-label text-white font-weight-bold">Nombre:</label>
			<div class="col-sm-10">
			  <form:input path="nombre" type="text" class="form-control" id="nombre" placeholder="Ingrese el nombre de la cancha"/>
			</div>
		</div>
		<div class="form-group row">
			<label for="tipo_cancha" class="col-sm-2 col-form-label text-white font-weight-bold">Tipo de cancha:</label>
			<div class="col-sm-10">
			  <form:select path="tipo" class="form-control" id="tipo_cancha">
				  <option value="5">Futbol 5</option>
				  <option value="8">Futbol 8</option>
				  <option value="11">Futbol 11</option>
			  </form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="suelo" class="col-sm-2 col-form-label text-white font-weight-bold">Tipo de suelo:</label>
			<div class="col-sm-10">
			  <form:select path="suelo" class="form-control" id="suelo">
				  <option value="cemento">Cemento</option>
				  <option value="sintetico">Sintetico</option>
				  <option value="cesped">Cesped</option>
				  <option value="tierra">Tierra</option>
			  </form:select>
			</div>
		</div>
		<hr>
		<h4 class="text-white">Direcci&oacute;n</h4>
		<div class="form-row">
			<div class="form-group col-md-4 col-sm-12" >
				<label for="calle" class="col-form-label text-white font-weight-bold">Calle</label>
				<form:input path="direccion.calle" type="text" class="form-control" id="calle"/>
			</div>
			<div class="form-group col-md-4 col-sm-12">
				<label for="altura" class="col-form-label text-white font-weight-bold">Altura</label>
				<form:input path="direccion.altura" type="number" class="form-control" id="altura"/>
			</div>
			<div class="form-group col-md-4 col-sm-12">
			  	<label for="barrio" class="col-form-label text-white font-weight-bold">Barrio</label>
			  	<form:select path="direccion.barrio.id" class="form-control" id="barrio">
				  	<c:forEach items="${barrios}" var="barrio">
						<option value="${barrio.id}">${barrio.descripcion}</option>
				  	</c:forEach>
			  	</form:select>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-sm-12">
				<label for="mapa" class="col-form-label text-white font-weight-bold">Insertar mapa:</label>
				<form:input path="mapa" type="text" class="form-control" placeholder="Coloque el iframe de google maps"/>
			</div>
		</div>

		<button class="btn btn-lg btn-primary float-right mt-2" type="submit"/>Crear</button>
	</form:form>


</section>

<jsp:include page="footer.jsp" />