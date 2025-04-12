<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Vuelo"%>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aeropuerto"%>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Avion"%>
<%@ page import="java.util.List" %>
<%
    List<Avion> aviones = (List<Avion>) request.getAttribute("aviones");
    List<Aeropuerto> aeropuertos = (List<Aeropuerto>) request.getAttribute("aeropuertos");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Formulario Vuelo</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">

</head>
<body>
  <h1>Formulario Vuelo</h1>
<!-- Formulario para agregar vuelo -->
<form method="post" action="${pageContext.request.contextPath}/vuelos/alta">
  <label for="codigo">Codigo de vuelo:</label>
  <input type="text" id="codigo" name="codigo" required><br>

  <label for="idAvion">Avion:</label>
  <select name="idAvion" id="idAvion" required>
      <% for (Avion avion : aviones) { %>
          <option value="<%= avion.getId() %>"><%= avion.getNumRegistro() %></option>
      <% } %>
  </select><br>

  <label for="idOrigen">Aeropuerto Origen:</label>
  <select name="idOrigen" id="idOrigen" required>
      <% for (Aeropuerto a : aeropuertos) { %>
          <option value="<%= a.getId() %>"><%= a.getNombre() %> - <%= a.getCodigo() %></option>
      <% } %>
  </select><br>

  <label for="idDestino">Aeropuerto Destino:</label>
  <select name="idDestino" id="idDestino" required>
      <% for (Aeropuerto a : aeropuertos) { %>
          <option value="<%= a.getId() %>"><%= a.getNombre() %> - <%= a.getCodigo() %></option>
      <% } %>
  </select><br>

  <label for="fechaSalida">Fecha de salida:</label>
  <input type="date" id="fechaSalida" name="fechaSalida" required><br>

  <label for="estatus">Estatus:</label>
  <select name="estatus" id="estatus" required>
      <option value="1">Disponible</option>
      <option value="2">No Disponible</option>
  </select><br>

  <button type="submit">Registrar Vuelo</button>
</form>
</body>
</html>