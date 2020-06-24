<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<c:if test="${not empty msj}">
	<div class="bg-success p-2 text-white">${msj}</div>
</c:if>

<section class="container-fluid p-3 section">
	<div class="row d-flex align-items-center">
		<div class="col-sm-6">
			<h1 class="display-4 text-white mb-4 font-weight-bold">Partidos</h1>
		</div>
		<div class="col-sm-6 text-right">
			<c:if test="${not empty cuenta}">
				<a class="btn btn-primary" href="/crear-partido">Crear partido</a>
			</c:if>
		</div>
	</div>

	<c:forEach items="${partidos}" var="partido">
		<div class="card mb-3">
			<div class="card-header">
				<div class="row">
					<div class="col-sm-6"><h5>${partido.cancha.direccion.barrio.descripcion}</h5></div>
					<div class="col-sm-6"><h5><fmt:formatDate value="${partido.fecha}" pattern="dd/MM HH:mm" />hs.</h5></div>
				</div>
			</div>
		  	<div class="card-body">
				<div class="row d-flex align-items-center">
					<div class="col-sm-6">
						<h5 class="card-title">Organiza: ${partido.organizador}</h5>
						<p class="card-text">Cancha: ${partido.cancha.nombre}</p>
						<i>Disponibilidad: ${partido.cantidadJugadores}</i>
					</div>
					<div class="col-sm-6">
						<a class="btn btn-sm btn-success" href="/detalle-partido/${partido.id}">
							<i class="fa fa-search" title="Ver detalle"></i> Ver detalle
						</a>
						<c:if
							test="${partido.organizador == cuenta.usuario.userName}">
							<a class="btn btn-sm btn-primary" href="/invitar-usuario">
								<i class="fa fa-user"></i> Invitar
							</a>
						</c:if>
						<c:if
							test="${partido.organizador == cuenta.usuario.userName}">
							<a class="btn btn-sm btn-danger" href="/eliminar-partido/${partido.id}">
								<i class="fa fa-trash"></i> Eliminar
							</a>
						</c:if>
					</div>
				</div>
		  	</div>
		</div>
	</c:forEach>
</section>

<jsp:include page="footer.jsp" />