<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Vuelo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%
List<Vuelo> vuelos = (List<Vuelo>) request.getAttribute("vuelos");
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Vuelo</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">

</head>
<body>
  <div class="table-responsive">

  <h1>Vuelo</h1>
  <table border="1">
    <thead>
      <tr>
        <th>ID_VUELO</th>
        <th>CODIGO_VUELO</th>
        <th>ID_AVION</th>
        <th>ID_ORIGEN</th>
        <th>ID_DESTINO</th>
        <th>FECHA_SALIDA</th>
        <th>ID_ESTATUS</th>
        <th>Editar/Eliminar</th>
      </tr>
    </thead>
    <tbody id="vueloBody">
      <% 
        int contador = 1; 
        for (Vuelo a: vuelos) {
        LocalDate fechaSalida = a.getFechaPrimerVuelo();
        String fechaFormateada = fechaSalida.format(dateFormatter);
      %>
      <tr>
        <td><%= contador %></td>
        <td style="display: none;"><%= a.getId() %></td>
        <td><%= a.getCodigoVuelo() %></td>
        <td><%= a.getAvion().getId() %></td>
        <td><%= a.getOrigen().getId() %></td>
        <td><%= a.getDestino().getId() %></td>
        <td><%= fechaFormateada %></td>
        <td><%= a.getEstatus() %></td>
       <td>
        <form action="<%= request.getContextPath() %>/vuelos/actualizar" method="GET" style="display:inline">
          <input type="hidden" name="id" value="<%= a.getId() %>" />
          <button type="submit">
            <i></i> Editar
          </button>
        </form> 
        <form action="<%= request.getContextPath() %>/vuelos/eliminar" method="GET" style="display:inline">
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