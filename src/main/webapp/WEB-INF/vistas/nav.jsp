<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-light bg-secondary justify-content-between pt-0 pb-1">
    
    
    <c:if test="${empty cuenta}"><!-----SI NO ESTA LOGUEADO QUE LOGO VUELVA A HOME-->
	    <a class="navbar-brand" href="home">
		        <img src="../../img/logo.png" style="height: 3em">
		</a>
	</c:if>
    <c:if test="${not empty cuenta}"> <!-----SI ESTA LOGUEADO QUE LOGO VUELVA A MOSTRAR PARTIDOS-->
	    <a class="navbar-brand" href="/mostrar-partidos">
	        <img src="../../img/logo.png" style="height: 3em">
	    </a>
	</c:if>
	
	
    <ul class="navbar-brand list-unstyled d-flex m-0">
        <c:if test="${not empty cuenta}">
            <c:if test="${not empty cantNotificaciones}">
                <li class="nav-item d-flex">
                    <a class="nav-link pr-0" href="/mostrar-notificaciones"><i class="fa fa-envelope"></i></a>
                    <span class="badge badge-pill badge-danger font-12" style="align-self: end;">${cantNotificaciones}</span>
                </li>
            </c:if>
            <li class="nav-item d-flex align-items-center flex-column">
                <i class="fa fa-user-circle"></i>
                <small class="text-white font-14">${cuenta.usuario.userName}</small>
            </li>
            <a class="p-0 text-green" href="/logout" title="Cerrar sesion">
                <i class="fa fa-times-circle font-14"></i>
            </a>
        </c:if>
        <c:if test="${empty cuenta}">
            <li class="nav-item">
                <a class="nav-link" href="/login"><small class="font-14">Login</small></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/show-form-usuario"><small class="font-14">Registrarse</small></a>
            </li>
        </c:if>
    </ul>
</nav>
