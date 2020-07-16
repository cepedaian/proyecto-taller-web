<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<c:if test="${not empty msj}">
	<div class="bg-success p-2 text-white">${msj}</div>
</c:if>

<section class="container-fluid p-3 section">
	<div class="row d-flex align-items-center">
		<div class="col-sm-6">
			<h1 class="display-4 text-white mb-4 font-weight-bold">Resultado de busqueda</h1>
		</div>
	</div>

	<c:if test="${usuarios.size() > 0}">
		<c:forEach items="${usuarios}" var="usuario">
			<div class="card mb-3">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-6">
							<h4 class="display-4">${usuario.userName}</h4>
						</div>
						<div class="col-sm-6">

						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row d-flex align-items-center">
						<div class="col-sm-6">
							<h5 class="card-title">Posici&oacute;n preferida: ${usuario.posicion}</h5>
						</div>
						<div class="col-sm-6">
							<h5>
								Fecha nacimiento:
								<fmt:formatDate value="${usuario.fecha_nac}" pattern="dd/MM/yyyy" />
							</h5>
						</div>
					</div>
					<div class="text-right">
						<a class="btn btn-lg btn-primary" href="/invitar-usuario/${id-partido}/${usuario.id}">Invitar</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${usuarios.size() == 0}">
		<h4 class="display-5 text-white mb-4">No hay usuarios encontrados para los filtros ingresados</h4>
	</c:if>

	<a class="btn btn-md mr-2 btn-secondary"
		href="/show-invitar-usuario-partido/${id-partido}">Volver
	</a>
</section>


<jsp:include page="footer.jsp" />