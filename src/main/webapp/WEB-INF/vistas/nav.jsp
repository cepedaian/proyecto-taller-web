<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-light bg-secondary justify-content-between pt-0 pb-1">
    <a class="navbar-brand" href="home">
        <img src="../../img/logo.png" style="height: 3em">
    </a>

    <ul class="navbar-brand list-unstyled d-flex m-0">
        <%--<li class="nav-item">
            <a class="nav-link" href="/mostrar-partidos">Partidos</a>
        </li>--%>
        <c:if test="${not empty cuenta}">
            <li class="nav-item">
                <a class="nav-link" href="/mostrar-notificaciones"><i class="fa fa-envelope"></i></a>
            </li>
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
