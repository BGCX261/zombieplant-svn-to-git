package elementos.plantas;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

@Observable
public abstract class Plantable implements Serializable{

	private String nombre;
	private boolean esDeAgua;
	private int defensa;
	private int ataque;

	public Plantable(String nombre, boolean esDeAgua, int defensa, int ataque) {
		this.nombre = nombre;
		this.esDeAgua = esDeAgua;
		this.defensa = defensa;
		this.ataque = ataque;
	}
	
	public Plantable(){
	}
	

	// *****************
	// getters y setters
	// *****************

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getEsDeAgua() {
		return esDeAgua;
	}

	public void setEsDeAgua(boolean esDeAgua) {
		this.esDeAgua = esDeAgua;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

}
