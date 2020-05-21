<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" >
		<style>
			@import url('https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap');

			body {
				height: 100vh;
				background: #56ab2f;  /* fallback for old browsers */
				background: -webkit-linear-gradient(to top, #a8e063, #56ab2f);  /* Chrome 10-25, Safari 5.1-6 */
				background: linear-gradient(to top, #a8e063, #56ab2f); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
			}

			h1 {
				font-family: 'Dancing Script', cursive;
			}

			.nav-link {
				color: white !important;
			}
		</style>
	</head>
	<body>
		<header>
			<nav class="navbar navbar-expand-lg navbar-light">
				<a class="navbar-brand" href="#">Linea de 4</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarText">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active">
							<a class="nav-link" href="mostrar-partidos">Mostrar partidos</a>
					  	</li>
						<li class="nav-item active">
							<a class="nav-link" href="crear-partido">Crear partido</a>
					  	</li>
						<li class="nav-item active">
							<a class="nav-link" href="mostrar-canchas">Mostrar canchas</a>
					  	</li>
						<li class="nav-item active">
							<a class="nav-link" href="crear-cancha">Crear cancha</a>
					  	</li>
					</ul>
				  </div>
			</nav>
		</header>
