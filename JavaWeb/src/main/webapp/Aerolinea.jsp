<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aerolinea" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%
List<Aerolinea> aerolineas = (List<Aerolinea>) request.getAttribute("aerolineas");
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aerolineas</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">
</head>
<body>
  <div class="table-responsive">

  <h1>Aerolinea</h1>
  <table border="1">
    <thead>
      <tr>
        <th>ID_AEROLINEA</th>
        <th>NOMBRE</th>
        <th>IATA</th>
        <th>PAIS</th>
        <th>FECHA_FUNDACION</th>
        <th>ID_ESTATUS</th>
        <th>Editar/Eliminar</th>
      </tr>
    </thead>
    <tbody >
    <% 
      int contador = 1; // Inicia el contador en 1 o el valor que prefieras
      for (Aerolinea a: aerolineas) {
      LocalDate fechaFundacion = a.getFechaFundacion();
      String fechaFormateada = fechaFundacion.format(dateFormatter);
    %>
    <tr>
      <td><%= contador %></td>
      <td><%= a.getNombre() %></td>
      <td><%= a.getIata() %></td>
      <td><%= a.getEstatus() %></td>
      <td><%= a.getPais() %></td>
      <td><%= fechaFormateada %></td>
      <td>
        <form action="<%= request.getContextPath() %>/aerolineas/actualizar" method="GET" style="display:inline">
          <input type="hidden" name="id" value="<%= a.getId() %>" />
          <button type="submit">
            <i></i> Editar
          </button>
        </form> 
        <form action="<%= request.getContextPath() %>/aerolineas/eliminar" method="GET" style="display:inline">
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