package elementos.jardines;

import java.io.Serializable;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import elementos.plantas.Planta;
import elementos.plantas.Plantin;

@Observable
public class Maceta implements Serializable{

	private Planta planta;

	
	//*****************
	//getters y setters
	//*****************
	
	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	
	public String getNombrePlanta(){
		if(this.getPlanta() == null){
			return "Sin planta";
		}
		return this.getPlanta().descripcion();
	}
	
	//*****************
	//implementacion
	//*****************
	
	public boolean estaVacio(){
		return this.getPlanta()==null;
	}

	public void plantar(Plantin plantinAPlantar) {
		this.setPlanta(plantinAPlantar.crearPlanta());
	}
}
