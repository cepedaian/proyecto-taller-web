<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<jsp:include page="nav.jsp" />

<section class="container">
		<h1 class="display-4 text-center text-white mb-2">Usuarios</h1>
		<table class="table">
				<tr>
					<td>Id</td>
					<td>UserName</td>
					<td></td>
				</tr>
				<c:forEach items="${usuarios}" var="usuario">
				<tr>
					
					<td>
						${usuario.id}
					</td>
					<td>
						${usuario.userName}
					</td>
					<td>
						
					</td>
					<td>
					
					</td>
					<td>
						<button class="btn btn-sm btn-primary" type="submit"/>Invitar</button>
					</td>
					
				</tr>
				</c:forEach>
		</table>
</section>

<jsp:include page="footer.jsp" />