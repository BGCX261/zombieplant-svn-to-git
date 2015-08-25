package elementos.jardines;

import java.io.Serializable;
import java.util.ArrayList;

import org.uqbar.commons.model.UserException;

import elementos.plantas.Planta;

public class Jardin implements Serializable {

	private int alto;
	private int ancho;
	private ArrayList<Surco> surcos;
	
	public Jardin(){
		this.setAlto(6);
		this.setAncho(5);
		this.configurarJardin();
	}
	
	//*****************
	//getters y setters
	//*****************
	
	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public ArrayList<Surco> getSurcos() {
		return surcos;
	}

	public void setSurcos(ArrayList<Surco> surcos) {
		this.surcos = surcos;
	}

	
	//*****************
	//implementacion
	//*****************
	
	public Surco dameSurco(int n){
		return this.getSurcos().get(n);
	}
	
	/**
	 * Comprueba que la posicion no salga del tablero(jardin)
	 * de lo contrario lanza una exception
	 * @param x
	 * @param y
	 */
	public void validarPosicion(int x,int y){
		if(this.getAlto()<=y || this.getAncho()<=x ||
				x<0 || y<0){
			throw new UserException("El alto o el ancho cae fuera del jardin.");
		}
	}
	
	/**
	 * planta una planta en la coordenada x,y si las cordinadas
	 * son validas
	 * @param p
	 * @param x
	 * @param y
	 */
	public void plantar(Planta p,int x,int y){
		this.validarPosicion(x, y);
		
		this.getSurcos().get(y).plantar(p, x);
		
	}
	
	public void  configurarJardin(){
		//true=es de agua
		//false=es de tierra
		this.setSurcos(new ArrayList<Surco>());
		
		//por el momento el tipo de surco no es configurable
		boolean[] tipoSurcos={false,true,true,false,false,false};
		
		for(int y=0;y<this.getAlto();y++){
			Surco s=new Surco(tipoSurcos[y],this.getAncho());
			s.setPosicion(y);
			this.getSurcos().add(s);
		}
	}
}
