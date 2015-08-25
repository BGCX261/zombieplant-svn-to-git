package elementos.mejoras;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

import elementos.plantas.Plantin;


@Observable
public abstract class Mejora implements Serializable{

	private int mejora;
	private String descripcion;
	private int costo;

	// *****************
	// getters y setters
	// *****************

	public Mejora(int mejora, String descripcion, int costo) {
		super();
		this.mejora = mejora;
		this.descripcion = descripcion;
		this.costo = costo;
	}

	public Mejora() {
	}

	public int getMejora() {
		return mejora;
	}

	public void setMejora(int mejora) {
		this.mejora = mejora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	// *****************
	// implementacion
	// *****************

	/**
	 * debe ser implementado por las subclases
	 * 
	 * @param planta
	 */
	public abstract void mejorar(Plantin plantin);
}
