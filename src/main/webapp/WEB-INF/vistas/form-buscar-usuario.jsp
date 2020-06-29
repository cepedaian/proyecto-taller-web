<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<section class="container-fluid section p-3">
	<h1 class="display-5 text-white mb-4 font-weight-bold">Busc&aacute; los usuarios que quieras invitar al partido ${partido.id}</h1>
	<form:form action="buscar-usuario" method="POST" modelAttribute="usuario">
		
		<div class="form-group row">
			<label for="barrio" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Barrio:</label>
			<div class="col-sm-10">
			  	<form:select path="barrio.id" class="form-control form-control-sm" id="barrio">
					<option value=""></option>
				  	<c:forEach items="${barrios}" var="barrioUsuario">
						<option value="${barrioUsuario.id}">${barrioUsuario.descripcion}</option>
				  	</c:forEach>
			  	</form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="posicion" class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">Posicion:</label>
			<div class="col-sm-10">
			  <form:select path="posicion" class="form-control form-control-sm" id="posicion">
				  <option value=""></option>
				  <option value="arquero">Arquero</option>
				  <option value="defensor">Defensor</option>
				  <option value="mediocampista">Mediocampista</option>
				  <option value="delantero">Delantero</option>
			  </form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="userName"
				class="col-sm-2 col-form-label col-form-label-sm font-weight-bold">UserName:</label>
			<div class="col-sm-10">
				<form:input path="userName" type="text"
					class="form-control form-control-sm" id="userName"
					placeholder="Ingrese su UserName" onkeyup="changeUserName(this.value)" />
			</div>
		</div>	
		<button class="btn btn-sm btn-primary" type="submit"/>Buscar</button>
	</form:form>


</section>
<jsp:include page="footer.jsp" />

<script>
	(function() {
	   	document.getElementById("posicion").selectedIndex = 0;
	   	document.getElementById("barrio").selectedIndex = 0;
		document.getElementById("userName").value = "";
	})();

	function changeUserName(value) {
		if(value != "") {
			document.getElementById("posicion").disabled = true;
			document.getElementById("barrio").disabled = true;
			document.getElementById("posicion").selectedIndex = 0;
			document.getElementById("barrio").selectedIndex = 0;
		} else {
			document.getElementById("posicion").disabled = false;
			document.getElementById("barrio").disabled = false;
		}
	}
</script>