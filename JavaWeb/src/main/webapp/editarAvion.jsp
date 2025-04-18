<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Avion" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aerolinea" %>
<%@ page import="java.util.List" %>

<%
Avion avion = (Avion) request.getAttribute("avion");
List<Aerolinea> aerolineas = (List<Aerolinea>) request.getAttribute("aerolineas");
%>

<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Avion</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/aviones/actualizar" class="form-avion">
  <fieldset>
    <legend>Editar un avion</legend>

    <label>ID_AVION: <%= avion.getId() %></label>
    <input type="hidden" name="id" value="<%= avion.getId() %>">

    <label for="numRegistro">Num Registro:</label>
    <input type="text" id="numRegistro" name="numRegistro" value="<%= avion.getNumRegistro() %>" required>

    <label for="tipo">Tipo:</label>
    <input type="text" id="tipo" name="tipo" value="<%= avion.getTipo() %>" required>

    <label for="codigoModelo">Codigo Modelo:</label>
    <input type="text" id="codigoModelo" name="codigoModelo" value="<%= avion.getCodigoModelo() %>" required>

    <label for="capacidad">Capacidad:</label>
    <input type="number" id="capacidad" name="capacidad" value="<%= avion.getCapacidad() %>" required>

    <label for="fechaPrimerVuelo">Fecha del primer vuelo:</label>
    <input type="date" id="fechaPrimerVuelo" name="fechaPrimerVuelo" value="<%= avion.getFechaPrimerVuelo() %>" required>

    <label for="aerolinea">Aerolinea:</label>
    <select id="aerolinea" name="aerolinea" required>
      <option value="" disabled>Seleccione una aerolinea</option>
      <% for (Aerolinea a : aerolineas) { %>
        <option value="<%= a.getId() %>" <%= avion.getAerolinea().getId().equals(a.getId()) ? "selected" : ""%>>
          <%= a.getNombre() %>
        </option>
      <% } %>
    </select>

    <label for="estatus">Estatus:</label>
    <select id="estatus" name="estatus" required>
      <option value="1" <%= avion.getEstatus().name().equals("DISPONIBLE") ? "selected" : "" %>>Disponible</option>
      <option value="2" <%= avion.getEstatus().name().equals("NO_DISPONIBLE") ? "selected" : "" %>>No Disponible</option>
    </select>

    <button type="submit">Editar Avion</button>
  </fieldset>
</form>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.1/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>



