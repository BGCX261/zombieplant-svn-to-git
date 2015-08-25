<%@ page isELIgnored ="false" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Jardin Zen</title>
        <script type="text/javascript" src="funciones.js" >
	    </script>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<c:set var="buscarPlantinModel" value="${sessionScope.buscarPlantinesModel}" />
    </head>
	<body>  
	    <h1>Zombies vs Plantas - Jardin Zen</h1>
	    <h3> ${buscarPlantinModel.zonaVisibleNombre}</h3>
		<form action="buscarPlantin" method= "post">
			<input type="text" name="criterio" value="${buscarPlantinModel.criterioBusqueda}" />
			<input type="submit" value="Buscar" />
		</form>
		<form action="mejoras" method="get" name="formMejoras">
		<table border="5">
			<tr>
				<th>Seleccion</th>
				<th  onmouseover="resaltar(this) ; verFlecha('nombre')" onmouseout="noResaltarEncabezado(this); ocultarFlecha('nombre',${buscarPlantinModel.getVisibilidad('nombre')})"> Nombre<a href="ordenar?criterioComparador=nombre"><IMG id="nombre"  src="${buscarPlantinModel.getDireccion('nombre')}.png"  style="visibility:${buscarPlantinModel.getVisibilidad('nombre')}"> </IMG></a></th>
				<th  onmouseover="resaltar(this) ; verFlecha ('ataque')" onmouseout="noResaltarEncabezado(this); ocultarFlecha('ataque',${buscarPlantinModel.getVisibilidad('ataque')})"> Poder Ofensivo<a href="ordenar?criterioComparador=ataque"><IMG id="ataque" src="${buscarPlantinModel.getDireccion('ataque')}.png" style="visibility:${buscarPlantinModel.getVisibilidad('ataque')}"></a></IMG></th>
				<th  onmouseover="resaltar(this) ; verFlecha('defensa')" onmouseout="noResaltarEncabezado(this); ocultarFlecha('defensa',${buscarPlantinModel.getVisibilidad('defensa')})"> Poder Defensivo<a href="ordenar?criterioComparador=defensa"><IMG id="defensa" src="${buscarPlantinModel.getDireccion('defensa')}.png" style="visibility:${buscarPlantinModel.getVisibilidad('defensa')}"></IMG></a></th>
			</tr>
			<c:forEach items="${buscarPlantinModel.plantinesFiltrados}"
				var="plantin" varStatus="status">
				<tr  onmouseover="resaltar(this)" onmouseout="noResaltar(this)">
					<td><input type="radio" name="nombrePlanta" value="${plantin.nombre}" onclick="document.formMejoras.botonMejoras.disabled=false;" /> </td>
					<td>${plantin.nombre}</td>
					<td>${plantin.ataque}</td>
					<td>${plantin.defensa}</td>
				</tr>
			</c:forEach>
		</table>
	<input type="submit" value="Mejorar Planta" name="botonMejoras" disabled="disabled"/>
	</form>
		<button onclick= "cambiarZona(window.document)" > Cambiar Zona  </button>	
	</body>
</html>

