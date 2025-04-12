<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aeropuerto" %>
<%
Aeropuerto aeropuerto = (Aeropuerto) request.getAttribute("aeropuerto");
Long id = aeropuerto.getId();
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Aeropuertos</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/aeropuertos/actualizar" class="form-aeropuerto">
  <fieldset>
    <legend>Editar un aeropuerto</legend>

    <label>ID_AEROPUERTO:  <%= id %></label>
    <input type="hidden" name="id" value="<%= id %>">

    <label for="nombre">NOMBRE:</label>
    <input type="text" id="nombre" name="nombre" value="${param.nombre}" required>

    <label for="nombre">CODIGO:</label>
    <input type="text" id="codigo" name="codigo" value="${param.codigo}" required>

    <label for="nombre">LATITUD:</label>
    <input type="text" id="latitud" name="latitud" value="${param.latitud}" required>

    <label for="nombre">LONGITUD:</label>
    <input type="text" id="longitud" name="longitud" value="${param.longitud}" required>

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

    <label for="estatus">ID_ESTATUS:</label>
    <select id="estatus" name="estatus" required>
      <option value="" select disabled>Selecciones</option>
      <option value="1">Disponible</option>
      <option value="2">No Disponible</option>
    </select>

    <button type="submit">Editar Aeropuerto</button>

  </fieldset>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.1/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>