<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Avion" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aerolinea" %>
<%@ page import="java.util.List" %>

<%
List<Avion> aviones = (List<Avion>) request.getAttribute("aviones");
List<Aerolinea> aerolineas = (List<Aerolinea>) request.getAttribute("aerolineas");
%>

<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Alta Avion</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/aviones/alta" class="form-avion">
  <fieldset>
    <legend>Agregar un avion</legend>

    <label>ID_AVION:</label>
    <input type="number" name="id" id="id" value="0">

    <label for="numRegistro">Num Registro:</label>
    <input type="text" id="numRegistro" name="numRegistro" required>

    <label for="tipo">Tipo:</label>
    <input type="text" id="tipo" name="tipo" required>

    <label for="codigoModelo">Codigo Modelo:</label>
    <input type="text" id="codigoModelo" name="codigoModelo" required>

    <label for="capacidad">Capacidad:</label>
    <input type="number" id="capacidad" name="capacidad" required>

    <label for="fechaPrimerVuelo">Fecha del primer vuelo:</label>
    <input type="date" id="fechaPrimerVuelo" name="fechaPrimerVuelo" required>

    <label for="aerolinea">Aerolinea:</label>
    <select id="aerolinea" name="aerolinea" required>
      <option value="" disabled selected>Seleccione una aerolinea</option>
      <% for (Aerolinea a : aerolineas) { %>
        <option value="<%= a.getId() %>">
          <%= a.getNombre() %>
        </option>
      <% } %>
    </select>

    <label for="estatus">Estatus:</label>
    <select id="estatus" name="estatus" required>
      <option value="1">Disponible</option>
      <option value="2">No Disponible</option>
    </select>

    <button type="submit">Agregar Avion</button>
  </fieldset>
</form>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.1/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>