package elementos;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;

import elementos.plantas.Planta;

@Observable
public class Zombie implements Serializable ,Imprimible{

	
	private String nombre;
	private String descripcion;
	private int defensa;
	private int ataque;
	private int resistenciaInicia;
	
	
	public Zombie(String nombre, String descripcion, int defensa, int ataque) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.defensa = defensa;
		this.ataque = ataque;
		this.resistenciaInicia = defensa;
	}

	
	public Zombie(){
		
	}
	
	//*****************
	//getters y setters
	//*****************
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	
	public int getResistenciaInicial() {
		return resistenciaInicia;
	}

	
	
	//*****************
    //implementacion
    //*****************
	
	@Override
	public String toString(){
		return this.getNombre()+"(A:"+this.getAtaque()+","+"D:"+this.getDefensa()+")";
		
	}
	
	public void atacarA(Planta p,Resultado resultado){
		p.teAtaca(this,resultado);
	}
	
	public boolean estaVivo(){
		return this.getDefensa()>0;
	}
	
	public void teAtaca(Planta p,Juego juego,Resultado resultado) {
		this.setDefensa(this.getDefensa()-p.getAtaque());
	}
	
	
	public void revivir() {
		this.defensa=this.resistenciaInicia;
	}


	public Zombie clonar() {
		return new Zombie(this.nombre, this.descripcion, this.defensa, this.ataque);
	}


	@Override
	public String descripcion() {
		// TODO Auto-generated method stub
		return this.getNombre()+"("+this.getAtaque()+","+this.getDefensa()+")";
	}
	
}
