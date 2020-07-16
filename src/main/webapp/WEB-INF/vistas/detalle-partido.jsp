<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<section class="container-fluid section">
	<h1 class="display-4 text-white mb-4 pt-2 font-weight-bold">Detalle
		partido</h1>
	<div class="row m-3">
		<div class="col-sm-12 col-md-6">
			<h4 class="display-5 text-white font-weight-bold">
				<fmt:formatDate value="${partido.fecha}" pattern="dd-MM-yyyy HH:mm" />
			</h4>
		</div>
		<div class="col-sm-12 col-md-6">
			<h4 class="display-5 text-white font-weight-bold">Organiza:
				${partido.organizador}</h4>
		</div>
	</div>
	<div class="row m-3">
		<div class="col-sm-12 col-md-6">
			<h4 class="display-5 text-white font-weight-bold">Cancha:
				${partido.cancha.nombre}</h4><br>
			<h4 class="display-5 text-white font-weight-bold">Tipo: ${partido.sexo}</h4>
		</div>
		<div class="col-sm-12 col-md-6">
			<h4 class="display-5 text-white font-weight-bold">

				Direcci&oacute;n: ${partido.cancha.direccion.calle}
				${partido.cancha.direccion.altura},
				${partido.cancha.direccion.barrio.descripcion}</h4>
		</div>
	</div>
	<div class="row">
		${partido.cancha.mapa}
	</div>
	<div class="row m-3">
		<div class="col-sm-12">
			<h4 class="display-5 text-white font-weight-bold">
				Participantes:</h4>
			<ul class="list-unstyled col">
				<c:forEach items="${usuarios}" var="usuario">
					<li class="text-white mb-1 row">
						<h5 class="display-5 mr-2">${usuario.userName}</h5> <c:if
							test="${partido.organizador == cuenta.usuario.userName && usuario.userName != partido.organizador}">
							<button class="btn btn-sm btn-danger" id="btn-modal"
								data-id="${usuario.id}/${partido.id}">
								<i class="fa fa-close"></i>Eliminar
							</button>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<h5 class="display-5 text-white">

				Cupos disponibles: ${partido.cantidadJugadores}
				<!-- VER RESTA UNO DE M�S -->

			</h5>
			<a class="btn btn-md btn-secondary mt-3" href="/mostrar-partidos">Volver</a>
			<c:if test="${btnUnirse}">
				<a class="btn btn-md btn-primary pull-right mt-3"
					href="/unirse-partido/${partido.id}"> <i class="fa fa-user"></i>
					Unirse
				</a>
			</c:if>
			<c:if test="${btnBajarse}">
				<a class="btn btn-md btn-danger pull-right mt-3"
					href="/baja-partido/${partido.id}"> <i class="fa fa-user"></i>
					Baja
				</a>
			</c:if>

		</div>
	</div>
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
				<p>Seguro que desea eliminar el participante?</p>
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
		$("#id_partido").attr("href", "/eliminar-participante/" + id);
		$("#confirmDelete").modal('show');
	});
</script>

