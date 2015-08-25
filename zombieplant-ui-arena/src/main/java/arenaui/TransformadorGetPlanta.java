package arenaui;

import com.uqbar.commons.collections.Transformer;
import elementos.jardines.Surco;

public class TransformadorGetPlanta implements Transformer<Surco,String>{

	private int fila;
	
	public TransformadorGetPlanta(int i){
		this.fila=i;
	}
	
	

	@Override
	public String transform(Surco s) {
		if(!s.getMacetas().get(fila).estaVacio()){
		return s.getMacetas().get(fila).getPlanta().descripcion();
		}
		return "VACIA";
	}

	

}
