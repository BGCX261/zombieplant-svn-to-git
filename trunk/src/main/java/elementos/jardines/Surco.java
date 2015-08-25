package elementos.jardines;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import elementos.Juego;
import elementos.Resultado;
import elementos.Zombie;
import elementos.plantas.Planta;

@Observable
public class Surco extends Entity implements Serializable{
	private boolean esDeAgua;
	private ArrayList<Maceta> macetas;
	private int posicion;

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Surco(boolean esDeAgua, int ancho) {
		this.configurarSurco(ancho);
		this.setEsDeAgua(esDeAgua);
	}

	// *****************
	// getters y setters
	// *****************

	public void agregarMaceta(Maceta m) {
		this.getMacetas().add(m);
	}

	public boolean getEsDeAgua() {
		return esDeAgua;
	}

	public void setEsDeAgua(boolean esDeAgua) {
		this.esDeAgua = esDeAgua;
	}

	public List<Maceta> getMacetas() {
		return macetas;
	}

	public void setMacetas(ArrayList<Maceta> macetas) {
		this.macetas = macetas;
	}

	// *****************
	// implementacion
	// *****************

	/**
	 * planta una planta en la posicion x.Lanza una exception si el tipo de
	 * planta es incompatible con el del surco o si la maceta esta ocupada.
	 * 
	 * @param p
	 * @param x
	 * @param m
	 * @throws UserException
	 * 
	 */
	public void plantar(Planta p, int x) {
		Maceta maceta = this.getMacetas().get(x);

		if (maceta.estaVacio()) {
			if (this.getEsDeAgua() == p.getEsDeAgua()) {
				maceta.setPlanta(p);
				p.setSurco(this);
			} else {
				throw new UserException("La planta " + p.getNombre()
						+ " es incompatible con el tipo de terreno.");
			}
		} else {
			throw new UserException("La maceta esta ocupada.");
		}

	}

	/**
	 * retorna si hay plantas en este surco
	 * 
	 * @return boolean
	 */
	public boolean hayPlantas() {
		boolean res = false;
		for (Maceta m : this.getMacetas()) {
			res = !m.estaVacio() || res;
		}
		return res;
	}

	/**
	 * retorna la planta mas cercana al zombie atacante si no hay planta retorna
	 * null
	 * 
	 * @return
	 */
	public Planta plantaMasCerca() {
		for (int i = this.getMacetas().size() - 1; i >= 0; i--) {
			if (!this.getMacetas().get(i).estaVacio()) {
				return this.getMacetas().get(i).getPlanta();
			}
		}
		return null;
	}

	/**
	 * dado una planta p la retira del surco
	 * 
	 * @param p
	 */
	public void sacarPlanta(Planta p) {
		for (Maceta m : this.getMacetas()) {
			if (!m.estaVacio() && m.getPlanta().equals(p)) {
				m.setPlanta(null);
			}
		}
	}

	public void configurarSurco(int ancho) {
		this.setEsDeAgua(esDeAgua);
		this.setMacetas(new ArrayList<Maceta>());
		for (int i = 0; i < ancho; i++) {
			this.getMacetas().add(new Maceta());
		}
	}

	public void atacar(Zombie zombie, Juego juego,Resultado resultado) {
		for (Maceta m: this.getMacetas()) {
			if (!m.estaVacio() && zombie.estaVivo()){
				m.getPlanta().atacarA(zombie,juego,resultado);	
			}
		}
	}
	
	public String getTipo(){
		return this.getEsDeAgua() ? "Agua" : "Tierra";
	}
}
