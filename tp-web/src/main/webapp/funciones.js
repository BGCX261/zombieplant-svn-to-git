function resaltar(elemento){
	elemento.className='mouseOver';
}

function noResaltar(elemento){
	elemento.className='mouseOut';
}

function noResaltarEncabezado(elemento){
	elemento.className='encabezado';
}

function verFlecha(idImagen){
	document.getElementById(idImagen).style.visibility="visible"; 
}

function ocultarFlecha(idImagen,visible){
	
		document.getElementById(idImagen).style.visibility="hidden"; 

}


function cambiarZona(documento){
	documento.location.href="changezona";
}



