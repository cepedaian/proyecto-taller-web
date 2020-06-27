<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<c:if test="${not empty msj}">
    <div class="bg-success p-2 text-white">${msj}</div>
</c:if>

<section class="container-fluid p-3 section">
    <div class="row d-flex align-items-center">
        <div class="col-sm-6">
            <h1 class="display-4 text-white mb-4 font-weight-bold">Notificaciones</h1>
        </div>
    </div>
    <c:forEach items="${notificaciones}" var="notificacion">
        <div class="card mb-3">
            <div class="card-header">
                <div class="row">
                    <div class="col-sm-6"><h5>Nuevo jugador!</h5></div>
                    <div class="col-sm-6"><h5>${notificacion.partido.fecha}>hs.</h5></div>
                </div>
            </div>
            <div class="card-body">
                <div class="row d-flex align-items-center">
                    <div class="col-sm-6">
                        <h5 class="card-title">${notificacion.remitente}</h5>
                        <p class="card-text">Se unió al partido</p>
                        <i>Disponibilidad: ${notificacion.partido.cantidadJugadores}</i>
                    </div>
                    <div class="col-sm-6">
                        <c:if
                                test="${partido.organizador == cuenta.usuario.userName}">
                            <a class="btn btn-sm btn-danger" href="/eliminar-usuario/${notificacion.usuario.userName}">
                                <i class="fa fa-user"></i> Eliminar
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</section>

<jsp:include page="footer.jsp" />
