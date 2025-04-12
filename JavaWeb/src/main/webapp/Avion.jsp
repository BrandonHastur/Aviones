<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Avion" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    List<Avion> aviones = (List<Avion>) request.getAttribute("aviones");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Avion</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">
</head>
<body>
  <div class="table-responsive">
  <h1>Avion</h1>
  <table border="1">
    <thead>
      <tr>
        <th>ID_AVION</th>
        <th>NUM_REGISTRO</th>
        <th>TIPO</th>
        <th>CODIGO_MODELO</th>
        <th>CAPACIDAD</th>
        <th>FECHA_PRIMER_VUELO</th>
        <th>ID_ESTATUS</th>
        <th>ID_AEROLINEA</th>
      </tr>
    </thead>
    <tbody id="avionBody">
      <% 
        int contador = 1;
        for (Avion a: aviones) {
        LocalDate fechaPrimerVuelo = a.getFechaPrimerVuelo();
        String fechaFormateada = fechaPrimerVuelo.format(dateFormatter);
      %>
      <tr>
        <td><%= contador %></td>
        <td style="display: none;"><%= a.getId() %></td>
        <td><%= a.getNumRegistro() %></td>
        <td><%= a.getTipo() %></td>
        <td><%= a.getCodigoModelo() %></td>
        <td><%= a.getCapacidad() %></td>
        <td><%= fechaFormateada %></td>
        <td><%= a.getEstatus() %></td>
        <td><%= a.getAerolinea().getId() %></td>
        <td>
          <form action="<%= request.getContextPath() %>/aviones/actualizar" method="GET" style="display:inline">
            <input type="hidden" name="id" value="<%= a.getId() %>" />
            <button type="submit">
              <i></i> Editar
            </button>
          </form> 
          <form action="<%= request.getContextPath() %>/aviones/eliminar" method="GET" style="display:inline">
              <input type="hidden" name="id" value="<%= a.getId() %>" />
              <button type="button" class="delete-btn">Eliminar</button>
          </form>
        </td>
      </tr>
      <% contador++;} %>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.1/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/script.js"></script>
</body>
</html>