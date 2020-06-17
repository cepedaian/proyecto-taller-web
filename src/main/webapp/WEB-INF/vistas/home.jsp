<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<c:if test="${not empty msj}">
	<div class="bg-success p-2 text-white">${msj}</div>
</c:if>

<section class="container-fluid home">
	<div class="text-center d-flex flex-column align-items-center p-5">
		<img class="mb-4" src="../img/logo.png" alt="imagen del logo">
		<a class="mb-2 btn btn-md btn-success" href="login">Login</a>
		<a class="mb-2 btn btn-md btn-success" href="show-form-usuario">Registrarse</a>
		<a class="mb-2 btn btn-md btn-success" href="mostrar-partidos">Invitado</a>
	</div>
</section>

<jsp:include page="footer.jsp" />