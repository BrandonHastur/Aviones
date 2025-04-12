<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aerolinea" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%
Aerolinea aerolinea = (Aerolinea) request.getAttribute("aerolinea");
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
Long id = aerolinea.getId();
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aerolineas</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/aerolineas/actualizar" class="form-aerolinea">
  <fieldset>
    <legend>Editar una aerolinea</legend>

    <label>ID_AEROLINEA:  <%= id %></label>
    <input type="hidden" name="id" value="<%= id %>">

    <label for="nombre">NOMBRE:</label>
    <input type="text" id="nombre" name="nombre" value="${param.nombre}" required>

    <label for="iata">IATA:</label>
    <input type="text" id="iata" name="iata" value="${param.iata}" maxlength="3" required>

    <label for="pais">PAIS:</label>
    <select id="pais" name="pais" required>
      <option value="Mexico">Mexico</option>
      <option value="Estados Unidos">Estados Unidos</option>
      <option value="Canada">Canada</option>
      <option value="Brasil">Brasil</option>
      <option value="Argentina">Argentina</option>
      <option value="España">Espana</option>
      <option value="Francia">Francia</option>
      <option value="Alemania">Alemania</option>
      <option value="Reino Unido">Reino Unido</option>
      <option value="Japon">Japon</option>
      <option value="China">China</option>
      <option value="India">India</option>
      <option value="Australia">Australia</option>
    </select>

    <label for="fundacion">FECHA_FUNDACION:</label>
    <input type="date" id="fundacion" name="fundacion" value="${param.fundacion}" required>

    <label for="estatus">ID_ESTATUS:</label>
    <select id="estatus" name="estatus" required>
      <option value="" select disabled>Selecciones</option>
      <option value="1">Disponible</option>
      <option value="2">No Disponible</option>
    </select>

    <button type="submit">Editar Aerolinea</button>

  </fieldset>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.1/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>