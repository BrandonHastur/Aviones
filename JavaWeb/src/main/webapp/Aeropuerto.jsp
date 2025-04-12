<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.brandon.webapp.proyecto.models.entities.Aeropuerto" %>
<%
List<Aeropuerto> aeropuertos = (List<Aeropuerto>) request.getAttribute("aeropuertos");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aeropuertos</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos.css">
</head>
<h1>Aeropuerto</h1>
<body>
  <div class="table-responsive">
  <table border="1">
    <thead>
      <tr>
        <th>ID_AEROPUERTO</th>
        <th>NOMBRE</th>
        <th>CODIGO</th>
        <th>LATITUD</th>
        <th>LONGITUD</th>
        <th>PAIS</th>
        <th>ID_ESTATUS</th>
        <th>Editar/Eliminar</th>
      </tr>
    </thead>
    <tbody id="aeropuertoBody">
      <% 
        int contador = 1; // Inicia el contador en 1
        for (Aeropuerto a: aeropuertos) {
      %>
      <tr>
        <td><%= contador %></td>
        <td style="display: none;"><%= a.getId() %></td>
        <td><%= a.getNombre() %></td>
        <td><%= a.getCodigo() %></td>
        <td><%= a.getLatitud() %></td>
        <td><%= a.getLongitud() %></td>
        <td><%= a.getPais() %></td>
        <td><%= a.getEstatus() %></td>
        <td>
          <form action="<%= request.getContextPath() %>/aeropuertos/actualizar" method="GET" style="display:inline">
            <input type="hidden" name="id" value="<%= a.getId() %>" />
            <button type="submit">
              <i></i> Editar
            </button>
          </form> 
          <form action="<%= request.getContextPath() %>/aeropuertos/eliminar" method="GET" style="display:inline">
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