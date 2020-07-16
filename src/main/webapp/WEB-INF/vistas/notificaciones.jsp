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
            <h1 class="display-4 text-white mb-4 font-weight-bold">Notificaciones</h1>
        </div>
    </div>
    <div class="row"> 
    <c:forEach items="${notificaciones}" var="notificacion">
      
        <div class="card mb-3 col-sm-5 ml-5">
            <div class="card-header">
                <div class="row">
                    <div class="col-sm-6"><h5><i class="fa fa-futbol-o" aria-hidden="true"></i> ${notificacion.asunto}</h5></div>
                </div>
            </div>
            <div class="card-body">
                <div class="row d-flex align-items-center">
                    <div class="col-sm-8">
                        <h5 class="card-title"><i class="fa fa-user"></i> ${notificacion.cuerpo}</h5>
                        <p><i class="fa fa-calendar" aria-hidden="true"></i> <fmt:formatDate value="${notificacion.partido.fecha}" pattern="dd/MM HH:mm" /> hs.</p><br>
                        <i><i class="fa fa-users" aria-hidden="true"></i> Quedan ${notificacion.partido.cantidadJugadores} lugares disponibles </i>
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
	</div>
</section>

<jsp:include page="footer.jsp" />
