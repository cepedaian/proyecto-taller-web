<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="navbar navbar-expand-lg bg-secondary d-flex justify-content-between">
    <a class="navbar-brand" href="home">
        <img src="../../img/logo.png" style="height: 3em">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <c:if test="${not empty cuenta}">
                
                <li class="nav-item text-white">
                    Hola ${cuenta.usuario.userName}
                </li>
            	<li class="nav-item">
                    <a class="nav-link" href="/mostrar-partidos">Partidos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/crear-partido">Crear Partido</a>
                </li>
            		
            </c:if>
            <c:if test="${empty cuenta}">
                <li class="nav-item">
                    <a class="nav-link" href="login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registrarse">Registrarse</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
