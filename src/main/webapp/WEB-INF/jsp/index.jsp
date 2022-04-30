<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./style.css" rel="stylesheet">

<title>CAMPEONATO DE FUTEBOL</title>
</head>
<body>

	<section class="main">

		<h1>CAMPEONATO DE FUTEBOLA</h1>

		<h1>
			<c:out value="${action }" />
		</h1>

		<div>
			<form action="campeonatoData" method="post">
				<label>SELECIONE UMA DATA AQUI</label> <input id="date" type="date"
					name="dataRodada"> <input type="submit" value="Buscar"
					id="button">
			</form>

			<br> <a class="btn" href="campeonato?action=getTimes">mostrar
				grupos</a> <br> <a class="btn"
				href="campeonato?action=getTimesResultado">resultado geral</a>

		</div>


		<div>

			<c:if test="${not empty times }">
				<table border="1px" cellpadding="5px" cellspacing="0">

					<tr>
						<th>TIMES</th>
						<th>GRUPOS</th>
					</tr>

					<c:forEach var="time" items="${times }">
						<tr>
							<td>${time.nome}</td>
							<td>${time.grupo}</td>
						</tr>
					</c:forEach>
				</table>

			</c:if>

			<c:if test="${not empty jogos }">
				<h1>Edite o resultado dos jogos</h1>

				<table border="1px" cellpadding="5px" cellspacing="0">

					<tr>
						<th>TIME CASA</th>
						<th>GRUPO CASA</th>
						<th>GOLS CASA</th>
						<th>TIME FORA</th>
						<th>GRUPO FORA</th>
						<th>GOLS FORA</th>
						<th>DATA</th>
					</tr>
					<form>
						<c:forEach var="jogo" items="${jogos }">
							<tr>
								<td>${jogo.timeCasa}</td>
								<td>${jogo.grupoCasa}</td>

								<td><input type="text" value=${jogo.golsCasa } /></td>

								<td>${jogo.timeFora}</td>
								<td>${jogo.grupoFora}</td>

								<td><input type="text" value=${jogo.golsFora } /></td>

								<td>${jogo.data}</td>
							</tr>
						</c:forEach>

						<input type="button" value="update resultado das partidas">

					</form>
				</table>

			</c:if>


			<c:if test="${not empty timesResultados }">
				<h1>Resultado Geral Do Campeonato</h1>

				<table border="1px" cellpadding="5px" cellspacing="0">

					<tr>
						<th>TIME</th>
						<th>GRUPO</th>
						<th>JOGOS DISPUTADOS</th>
						<th>VITORIAS</th>
						<th>EMPATES</th>
						<th>DERROTAS</th>
						<th>GOLS MARCADOS</th>
						<th>GOLS SOFRIDOS</th>
						<th>SALDO GOLS</th>
						<th>PONTOS</th>
						<th>SITUAÇÃO</th>
					</tr>

					<c:forEach var="timeResultado" items="${ timesResultados}">
						<tr>
							<td>${timeResultado.nome}</td>
							<td>${timeResultado.grupo}</td>
							<td>${timeResultado.jogosDisputados}</td>
							<td>${timeResultado.vitorias}</td>
							<td>${timeResultado.empates}</td>
							<td>${timeResultado.derrotas}</td>
							<td>${timeResultado.golsMarcados}</td>
							<td>${timeResultado.golsSofridos}</td>
							<td>${timeResultado.saldoGols}</td>
							<td>${timeResultado.pontos}</td>
							<td>${timeResultado.situacao}</td>
						</tr>
					</c:forEach>


				</table>

			</c:if>


		</div>

	</section>

</body>

</html>