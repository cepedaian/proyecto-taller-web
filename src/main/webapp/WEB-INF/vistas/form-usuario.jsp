<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<section class="container p-3">
	<h1 class="display-4 text-white mb-4 font-weight-bold">Crear Usuario</h1>
	<form:form action="insertar-usuario" method="POST" modelAttribute="usuario">
		
		<div class="form-group row">
			<label for="userName" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Username:</label>
			<div class="col-sm-10">
			  <form:input path="userName" type="text" class="form-control form-control-sm" id="userName" placeholder="Ingrese su Username"/>
			</div>
		</div>
		<div class="form-group row">
			<label for="email" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Email:</label>
			<div class="col-sm-10">
				<form:input path="email" type="email" class="form-control form-control-sm" id="email" placeholder="Ingrese su Email"/>
			</div>
		</div>
		<div class="form-group row">
			<label for="password" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Contraseña:</label>
			<div class="col-sm-10">
				<form:input path="password" type="password" class="form-control form-control-sm" id="password" placeholder="Ingrese su Contraseña"/>
			</div>
		</div>
		<div class="form-group row">
			<label for="posicion" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Posicion:</label>
			<div class="col-sm-10">
			  <form:select path="posicion" class="form-control form-control-sm" id="posicion">
				  <option value="arquero">Arquero</option>
				  <option value="defensor">Defensor</option>
				  <option value="mediocampista">Mediocampista</option>
				  <option value="delantero">Delantero</option>
			  </form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="barrio" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Barrio</label>
			<div class="col-sm-10">
			  	<form:select path="barrio.id" class="form-control form-control-sm" id="barrio">
				  	<c:forEach items="${barrios}" var="barrioUsuario">
						<option value="${barrioUsuario.id}">${barrioUsuario.descripcion}</option>
				  	</c:forEach>
			  	</form:select>
			</div>
		</div>

		<button class="btn btn-sm btn-primary" type="submit"/>Crear</button>
	</form:form>


</section>
<jsp:include page="footer.jsp" />