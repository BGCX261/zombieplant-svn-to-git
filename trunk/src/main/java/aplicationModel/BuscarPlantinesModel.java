package aplicationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparadores.Comparadores;
import comparadores.ReversibleComparator;

import elementos.Jugador;
import elementos.jardines.JardinZen;
import elementos.jardines.Zona;
import elementos.plantas.Plantin;

public class BuscarPlantinesModel extends JardinZenModel implements Serializable{

	private List<Plantin> plantinesFiltrados = new ArrayList<Plantin>();
	private String criterioBusqueda;
	private MejorasModel mejorasModel;
    private ReversibleComparator<Plantin> comparator=Comparadores.get("ataque");

	public BuscarPlantinesModel(JardinZen jardinZen, Jugador jugador) {
		super(jardinZen, jugador);
		this.mejorasModel= new MejorasModel(this.getJugador());
		configurarPlantines();

		
	}
	
	public BuscarPlantinesModel(Jugador jugador) {
		this(jugador.getJardinZen(), jugador);

	}
	
	/*Implementacion*/
	
	/*
	 * buscar todos los plantines con el criterioDeBusqueda sobre
	 * la zona visible,luego los ordena segun el comparador
	 */
	public void buscar() {
		this.plantinesFiltrados=this.getZonaVisible().filtrarPlantinesPor(this.getCriterioBusqueda());
		Collections.sort(this.getPlantinesFiltrados(),this.getComparator());		
		
	}

	
	@Override
	public void cambiarZona() {
		super.cambiarZona();
		this.configurarPlantines();		
	}
	
	protected void configurarPlantines() {
		getPlantinesFiltrados().clear();
		getPlantinesFiltrados().addAll(this.getZonaVisible().getPlantines());
		Collections.sort(this.getPlantinesFiltrados(),this.getComparator());
		setCriterioBusqueda("");
	}
	
	/*
	 * setega un nuevo comparator
	 */
	public void setComparator(String nombre){
		this.comparator=this.getComparator().getSiguienteComparator(Comparadores.get(nombre));
	}
	
	public String getZonaVisibleNombre(){
		return this.getZonaVisible().getEsDeAgua() ? "Acuatico" : "Terrestre";
	}
	
	/*
	 * retorna la visibilidad del comparador de nombreComparator
	 */
	public String getVisibilidad(String nombreComparator){
		return this.getComparator().visible(nombreComparator);
	}
	
	/*
	 * retorna la direccion del comparador actual
	 */
	public String getDireccion(String nombreComparator){
		return Comparadores.get(nombreComparator).getDireccion();
	}
	

	/*getters y setters*/
	
	public ReversibleComparator<Plantin> getComparator() {
		return comparator;
	}

	public void setComparator(ReversibleComparator<Plantin> comparator) {
		this.comparator = comparator;
	}

	
	
	public List<Plantin> getPlantinesFiltrados() {
		return plantinesFiltrados;
	}

	public void setPlantinesFiltrados(List<Plantin> plantinesFiltrados) {
		this.plantinesFiltrados = plantinesFiltrados;
	}

	public String getCriterioBusqueda() {
		return criterioBusqueda != null ? criterioBusqueda : "";
//		if(this.criterioBusqueda == null) {
//		   criterioBusqueda = "";}
//		return criterioBusqueda;
	}

	public void setCriterioBusqueda(String buscar) {
		this.criterioBusqueda = buscar;
	}


	public MejorasModel getMejorasModel() {
		
		return this.mejorasModel;
	}

	public void setMejorasModel(MejorasModel mejorasModel) {
		this.mejorasModel = mejorasModel;
	}
	
	
}
