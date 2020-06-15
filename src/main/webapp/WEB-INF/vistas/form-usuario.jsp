<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />

<section class="container p-3">
	<h1 class="display-4 text-white mb-4 font-weight-bold">Crear
		Cuenta</h1>
	<form:form action="insertar-usuario" method="POST"
		modelAttribute="cuenta">

		<div class="form-group row">
			<label for="email"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Email:</label>
			<div class="col-sm-10">
				<form:input path="email" type="email"
					class="form-control form-control-sm" id="email"
					placeholder="Ingrese su Email" />
			</div>
		</div>
		<div class="form-group row">
			<label for="password"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Password:</label>
			<div class="col-sm-10">
				<form:input path="password" type="text"
					class="form-control form-control-sm" id="password"
					placeholder="Ingrese su password" />
			</div>
		</div>
		<div class="form-group row">
			<label for="userName"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">UserName:</label>
			<div class="col-sm-10">
				<form:input path="usuario.userName" type="text"
					class="form-control form-control-sm" id="userName"
					placeholder="Ingrese su UserName" />
			</div>
		</div>
		<div class="form-group row">
			<label for="posiscion"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Posicion:</label>
			<div class="col-sm-10">
				<form:select path="usuario.posicion"
					class="form-control form-control-sm" id="posicion">
					<option value="arquero">Arquero</option>
					<option value="defensor">Defensor</option>
					<option value="mediocampista">Mediocampista</option>
					<option value="delantero">Delantero</option>
				</form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="barrio"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Barrio</label>
			<div class="col-sm-10">
				<form:select path="usuario.barrio.id"
					class="form-control form-control-sm" id="barrio">
					<c:forEach items="${barrios}" var="barrioUsuario">
						<option value="${barrioUsuario.id}">${barrioUsuario.descripcion}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="fecha_nac"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Fecha
				de nacimiento:</label>
			<div class="col-sm-10">
				<form:input path="usuario.fecha_nac" type="date"
					class="form-control form-control-sm" id="fecha_nac" />
			</div>
		</div>
		<div class="form-group row">
			<label for="sexo"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Sexo:</label>
			<div class="col-sm-10">
				<form:select path="usuario.sexo"
					class="form-control form-control-sm" id="sexo">
					<option value="M">Masculino</option>
					<option value="F">Femenino</option>
				</form:select>
			</div>
		</div>

		<button class="btn btn-sm btn-primary" type="submit" />Crear</button>
	</form:form>
	<c:if test="${not empty error}">
		<small class="text-danger">${error}</small>
	</c:if>

</section>
<jsp:include page="footer.jsp" />