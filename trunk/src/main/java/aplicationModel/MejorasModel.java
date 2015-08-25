package aplicationModel;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import elementos.Jugador;
import elementos.mejoras.Mejora;
import elementos.mejoras.Mejoras;
import elementos.plantas.Plantin;

@Observable
public class MejorasModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Jugador jugador;
	private Plantin plantinSeleccionado;
	private Mejora mejoraSeleccionada;
	private List<Mejora> mejorasDisponibles;

	
	public MejorasModel(Plantin plantinSeleccionado, Jugador jugador) {
		this.plantinSeleccionado = plantinSeleccionado;
		this.jugador = jugador;
		this.actualizarMejorasDisponibles();

	}
	
	/*inmplementacion*/
	
	/*
	 * compra la mejora si tiene recursos,de lo contrario
	 * lansa una userException
	 */
	public void comprarMejora() {
		// cambiar los puntos por la mejora
		this.jugador.comprarMejora(this.mejoraSeleccionada.getCosto());
		this.plantinSeleccionado.mejorar(this.mejoraSeleccionada);
		this.actualizarMejorasDisponibles();
	}

	/*
	 * resta a las mejoras globales las mejoras de la planta 
	 */
	protected void actualizarMejorasDisponibles() {
		this.mejorasDisponibles=Mejoras.getMejorasExcepto(plantinSeleccionado.getMejorasCompradas());
	}

	public void configurar(String nombrePlanta) {
		this.plantinSeleccionado =jugador.obtenerPlantin(nombrePlanta);
		this.actualizarMejorasDisponibles();
		this.mejoraSeleccionada=null;
	}
	
	public void setMejoraDisponible(int mejoraIndex){
		this.mejoraSeleccionada=this.getMejorasDisponibles().get(mejoraIndex);
	}
	
	/*
	 * busca la mejora de ideMejora y la setea en mejoraSeleccionada
	 * 
	 */
	public void setMejoraSeleccionadaDeIde(String ideMejora){
		this.validarEntero(ideMejora);
		Integer indice=Integer.parseInt(ideMejora);;		
		this.validarSiExisteMejoraCon(indice);
		this.mejoraSeleccionada=this.getMejorasDisponibles().get(Integer.parseInt(ideMejora));
	}
	
	/*
	 * valida que el ide no sea mayor al tamaño de lista de
	 * mejoras disponibles y que sea menor a 0
	 */
	private void validarSiExisteMejoraCon(Integer indice){
		if( indice > this.getMejorasDisponibles().size()
				|| indice < 0){
			
			throw new UserException("La mejora de indice "+indice+" no esta diponible"); 
		}
	}
	
	private void validarEntero(String numero){
		try{
			Integer.parseInt(numero);
		}catch(NumberFormatException e){
			throw new UserException("El ide debe ser un entero");
		}
	}
	
	/*
	 * busca y settea el plantin seleccionado de nombre nombre
	 * si no existe el plantin lanz auna exception
	 */
	public void buscarYSeleccionarPlantin(String nombre){
		Plantin plantin=this.getJugador().obtenerPlantin(nombre);
		this.setPlantinSeleccionado(plantin);
		this.actualizarMejorasDisponibles();
	}
	
	/*getters y setters*/
	
	public MejorasModel(Jugador jugador) {
		this.jugador=jugador;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Plantin getPlantinSeleccionado() {
		return plantinSeleccionado;
	}

	public void setPlantinSeleccionado(Plantin plantinSeleccionado) {
		this.plantinSeleccionado = plantinSeleccionado;
	}

	public Mejora getMejoraSeleccionada() {
		return mejoraSeleccionada;
	}

	
	
	
	public void setMejoraSeleccionada(Mejora mejoraSeleccionada) {
		this.mejoraSeleccionada = mejoraSeleccionada;
	}

	public List<Mejora> getMejorasDisponibles() {
		return this.mejorasDisponibles;
	}

	public void setMejorasDisponibles(List<Mejora> mejorasDisponibles) {
		this.mejorasDisponibles = mejorasDisponibles;
	}

	

	

}
