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
					<div class="col-sm-6">
						<h5>${partido.cancha.direccion.barrio.descripcion}</h5>
					</div>
					<div class="col-sm-6">
						<h5>
							<fmt:formatDate value="${partido.fecha}" pattern="dd/MM HH:mm" />
							hs.
						</h5>
					</div>
				</div>
			</div>
			<div class="card-body">
				<div class="row d-flex align-items-center">
					<div class="col-sm-6">
						<h5 class="card-title">Organiza: ${partido.organizador}</h5>
						<p class="card-text">Cancha: ${partido.cancha.nombre}</p>
						<p class="card-text">Tipo: ${partido.sexo}</p>
						<i>Disponibilidad: ${partido.cantidadJugadores}</i>
					</div>
					<div class="col-sm-6">
						<a class="btn btn-sm btn-success"
							href="/detalle-partido/${partido.id}"> <i
							class="fa fa-search" title="Ver detalle"></i> Ver detalle
						</a>
						<c:if test="${partido.organizador == cuenta.usuario.userName}">
							<a class="btn btn-sm btn-primary"
								href="/invitar-usuario-partido/${partido.id}"> <i
								class="fa fa-user"></i> Invitar
							</a>
						</c:if>
						<c:if test="${partido.organizador == cuenta.usuario.userName}">
							<button id="btn-modal" data-id="${partido.id}"
								class="btn btn-sm btn-danger">
								<i class="fa fa-trash"></i> Eliminar
							</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</section>
<div class="modal" tabindex="-1" role="dialog" id="confirmDelete">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Atencion!</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Seguro que desea eliminar todo el partido?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
				<a id="id_partido" type="button" class="btn btn-primary" href="/#">Confirmar</a>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp" />
<script>
	$(document).on("click", "#btn-modal", function(e) {
		e.preventDefault();
		const _self = $(this);
		const id = _self.data('id');
		$("#id_partido").attr("href", "/eliminar-partido/" + id);
		$("#confirmDelete").modal('show');
	});
</script>
