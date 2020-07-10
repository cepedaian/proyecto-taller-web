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
				${partido.cancha.nombre}</h4>
		</div>
		<div class="col-sm-12 col-md-6">
			<h4 class="display-5 text-white font-weight-bold">
				Direcci&oacute;n: ${partido.cancha.direccion.calle}
				${partido.cancha.direccion.altura},
				${partido.cancha.direccion.barrio.descripcion}</h4>
		</div>
	</div>
	<div class="row m-3">
		<div class="col-sm-12">
			<h4 class="display-5 text-white font-weight-bold">
				Participantes:</h4>
			<ul class="list-unstyled">
				<c:forEach items="${usuarios}" var="usuario">
					<li class="text-white mb-1">
						<h5 class="display-5">${usuario.userName}</h5> <c:if
							test="${partido.organizador == cuenta.usuario.userName}">
							<a class="btn btn-sm btn-danger"
								href="/eliminar-participante/${usuario.id}/${partido.id}"> <i
								class="fa fa-trash"></i> Eliminar
							</a>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<h5 class="display-5 text-white">

				Cupos disponibles: ${partido.cantidadJugadores}
				<!-- VER RESTA UNO DE MÁS -->

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

<jsp:include page="footer.jsp" />

