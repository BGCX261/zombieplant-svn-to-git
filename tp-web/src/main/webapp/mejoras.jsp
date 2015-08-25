<%@ page isELIgnored ="false" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Mejoras</title>
        <script type="text/javascript" src="funciones.js" >
	    </script>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		<c:set var="buscarPlantinModel" value="${sessionScope.buscarPlantinesModel}" />
		<c:set var="mejorasModel" value="${sessionScope.buscarPlantinesModel.mejorasModel}" />
   </head>
   <body>  
        <h1>Zombies vs Plantas - Mejoras</h1>
        <table>    
           <tr>
	        <div id="izquierda">
	        
	            <h2 class="titulo">Mejoras disponibles</h2>
	            <h5> Recursos: ${buscarPlantinModel.jugador.puntos}  planta:${mejorasModel.plantinSeleccionado.nombre}  </h5>
	            <table border="5">
					<tr>
						<th>Seleccion</th>
						<th>Descripcion</th>
						<th>Precio</th>
						<th>Mejora</th>
					</tr>
				   <form action="comprar" method="get" name="formComprar">
			       <c:forEach items="${mejorasModel.mejorasDisponibles}" var="mejora" varStatus="status">
			 			<tr onmouseover=onmouseover="resaltar(this)" onmouseout="noResaltar(this)">
							<td ><input type="radio" name="mejora" value="${status.index}" onclick="document.formComprar.botonComprar.disabled=false;"></td>
					    	<td>${mejora.descripcion}</td>
					    	<td>${mejora.costo}</td>
					    	<td>${mejora.mejora}</td>
					    </tr>
				   </c:forEach>
					    <tr>
					    <td><input type="submit" value="comprar" disabled="disabled" name="botonComprar"/></td>
					    <td colspan="3">Seleccione una planta y compre </td>
					    </tr>
				   </form>
			    </table>	
				
			</div>  	
				
			<div id="derecha">	
				
				<h2 class="titulo">Mejoras Compradas</h2>
				<ol>
				<c:forEach items="${mejorasModel.plantinSeleccionado.mejorasCompradas}" var="mejora" varStatus="status">
					<li id="itemLista" onmouseover="resaltar(this)" onmouseout="noResaltar(this)"> ${mejora.descripcion} </li>
				</c:forEach>
				</ol>
	        </div>
	    </table> 
			<form action="jardinzen" method="post">
				<input type="submit" value="ir al jardin zen"/>
			</form>
		<c:if test="${requestScope.pudoComprar != null}">
		     <script >
				   alert("no tienes puntos suficientes");
	         </script>
	    </c:if>
</body>
</html>