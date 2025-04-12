<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Vuelo"%>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aeropuerto"%>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Avion"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    Vuelo vuelo = (Vuelo) request.getAttribute("vuelo");
    List<Avion> aviones = (List<Avion>) request.getAttribute("aviones");
    List<Aeropuerto> aeropuertos = (List<Aeropuerto>) request.getAttribute("aeropuertos");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
%>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Vuelo</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">
</head>
<body>

<form method="POST" action="${pageContext.request.contextPath}/vuelos/actualizar" class="form-vuelo">
  <fieldset>
    <legend>Editar un vuelo</legend>

    <label>ID_VUELO: <%= vuelo.getId() %></label>
    <input type="hidden" name="id" value="<%= vuelo.getId() %>">

    <label for="codigoVuelo">Código de vuelo:</label>
    <input type="text" id="codigoVuelo" name="codigoVuelo" value="<%= vuelo.getCodigoVuelo() %>" required>

    <label for="idAvion">Avion:</label>
    <select name="idAvion" id="idAvion" required>
        <% for (Avion a : aviones) { %>
            <option value="<%= a.getId() %>" <%= vuelo.getAvion().getId().equals(a.getId()) ? "selected" : "" %>>
                <%= a.getNumRegistro() %> - <%= a.getTipo() %> <%= a.getCodigoModelo() %>
            </option>
        <% } %>
    </select>

    <label for="idOrigen">Aeropuerto Origen:</label>
    <select name="idOrigen" id="idOrigen" required>
        <% for (Aeropuerto a : aeropuertos) { %>
            <option value="<%= a.getId() %>" <%= vuelo.getOrigen().getId().equals(a.getId()) ? "selected" : "" %>>
                <%= a.getNombre() %> - <%= a.getCodigo() %>
            </option>
        <% } %>
    </select>

    <label for="idDestino">Aeropuerto Destino:</label>
    <select name="idDestino" id="idDestino" required>
        <% for (Aeropuerto a : aeropuertos) { %>
            <option value="<%= a.getId() %>" <%= vuelo.getDestino().getId().equals(a.getId()) ? "selected" : "" %>>
                <%= a.getNombre() %> - <%= a.getCodigo() %>
            </option>
        <% } %>
    </select>

    <label for="fechaSalida">Fecha de salida:</label>
    <input type="date" id="fechaSalida" name="fechaSalida" value="<%= vuelo.getFechaPrimerVuelo().format(dateFormatter) %>" required>

    <label for="estatus">Estatus:</label>
    <select id="estatus" name="estatus" required>
      <option value="1" <%= vuelo.getEstatus().name().equals("DISPONIBLE") ? "selected" : "" %>>Disponible</option>
      <option value="2" <%= vuelo.getEstatus().name().equals("NO_DISPONIBLE") ? "selected" : "" %>>No Disponible</option>
    </select>

    <button type="submit">Editar Vuelo</button>
  </fieldset>
</form>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.1/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>