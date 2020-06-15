<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<div class="container-fluid section d-flex justify-content-center">
	<div class="col-sm-6 p-4 text-white">
		<form:form action="validar-login" method="POST" modelAttribute="cuenta" class="d-flex flex-column aling-items-start">
			<img class="mb-3" src="../../img/logo.png" style="align-self: center;">
			<label for="email">Email:</label>
			<form:input path="email" id="email" type="email" class="form-control mb-3" />
			<label for="password">Password:</label>
			<form:input path="password" type="password" id="password" class="form-control mb-3"/>

			<div style="align-self: center;">
				<a class="btn btn-md btn-secondary mr-2" href="home">Cancelar</a>
				<button class="btn btn-md btn-success" type="submit"/>Login</button>
			</div>
		</form:form>

		<c:if test="${not empty error}">
			<small class="text-danger">${error}</small>
		</c:if>
	</div>
</div>

<jsp:include page="footer.jsp" />
		
