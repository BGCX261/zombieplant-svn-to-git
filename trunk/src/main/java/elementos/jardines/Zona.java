package elementos.jardines;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import elementos.plantas.Plantin;


@Observable
public class Zona implements Serializable{

	private int ancho;
	private List<Plantin> plantines = new ArrayList<Plantin>();
	private boolean esDeAgua;
	

	public void setPlantines(List<Plantin> plantines) {
		this.plantines = plantines;
	}

	public Zona() {
		
	}

	public Zona(int ancho, boolean esDeAgua) {
		this.setEsDeAgua(esDeAgua);
		this.setAncho(ancho);
		
	}

	public List<Plantin> getPlantines() {
		return plantines;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void agregarPlantin(Plantin p) {
		validarLugar();
		this.plantines.add(p);

	}

	protected void validarLugar() {
		if (!hayLugar()) {
			throw new UserException(
					"No hay lugar en este Jardin. El Jardin tiene ocupado los "
							+ this.getAncho() + " lugares");
		}
	}

	public boolean hayLugar() {
		return this.plantines.size() < this.ancho;
	}

	public int getLugaresDisponibles() {
		return this.getAncho() - this.plantines.size();
	}

	public boolean getEsDeAgua() {
		return esDeAgua;
	}
		
	public void setEsDeAgua(boolean esDeAgua) {
		this.esDeAgua = esDeAgua;
	}

	
	public Plantin getPlantin(String nombre) {
		for(Plantin p:this.getPlantines()){
			if(p.getNombre().equals(nombre)){
				return p;
			}
		}

		return null;
	}

	public List<Plantin> filtrar(String nombreBusqueda) {
		List<Plantin> resultado=new LinkedList<Plantin>();
		if(nombreBusqueda == null){
			return resultado;
		}
		
		for(Plantin p:this.getPlantines()){
			if(p.getNombre().startsWith(nombreBusqueda) || p.getNombre().contains(nombreBusqueda)){
				resultado.add(p);
			}
		}
		return resultado;
	}

	public List<Plantin> filtrarPlantinesPor(String criterioBusqueda) {
		List<Plantin> plantinesFiltrados=new ArrayList<Plantin>();
		for (Plantin p : this.getPlantines()) {
			if (p.getNombre().toLowerCase().contains(criterioBusqueda.toLowerCase()) ) {
				plantinesFiltrados.add(p);
			}

		}
		return plantinesFiltrados;
	}

}
