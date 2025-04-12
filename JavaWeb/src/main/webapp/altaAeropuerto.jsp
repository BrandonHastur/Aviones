<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Formulario Aeropuerto</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/formulario.css">

</head>
<body>
  <h1>Alta Aeropuerto</h1>
    <!-- Formulario para agregar registros -->
    <form method="POST" action="<%=request.getContextPath()%>/aeropuertos/alta" class="form-aeropuerto">
      <fieldset>
        <legend>Agregar nuevo aeropuerto</legend>
  
        <label for="nombre">NOMBRE:</label>
        <input type="text" id="nombre" name="nombre" value="${param.nombre}" required>

        <label for="nombre">CODIGO:</label>
        <input type="text" id="nombre" name="codigo" max="3" value="${param.codigo}" required>
  
        <label for="latitud">LATITUD:</label>
        <input type="number" step="any" id="latitud" name="latitud" value="${param.latitud} required>
  
        <label for="longitud">LONGITUD:</label>
        <input type="number" step="any" id="longitud" name="longitud" value="${param.longitud} required>
  
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
  
        <button type="submit">Agregar Aeropuerto</button>
      </fieldset>
    </form>
</body>
</html>