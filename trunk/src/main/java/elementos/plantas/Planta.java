package elementos.plantas;

import org.uqbar.commons.utils.Observable;

import elementos.Juego;
import elementos.Resultado;
import elementos.Zombie;
import elementos.jardines.Surco;

@Observable
public class Planta extends Plantable {

	private Surco surco;
	
	public Surco getSurco() {
		return surco;
	}

	public void setSurco(Surco surco) {
		this.surco = surco;
	}

	public Planta(String nombre, boolean esDeAgua, int defensa, int ataque) {
		super(nombre, esDeAgua, defensa, ataque);
	}

	public Planta(){}
	
	public Planta(boolean b) {
		this.setEsDeAgua(b);
	}
	
	//*****************
    //implementacion
    //*****************
	
	
	public String descripcion(){
		return this.getNombre()+"(A:"+this.getAtaque()+",D:"+this.getDefensa()+")";
	}

	public boolean estaVivo(){
		return this.getDefensa()>0;
	}
	
	public void atacarA(Zombie z,Juego juego,Resultado resultado){
		z.teAtaca(this,juego,resultado);
	}
	
	
	public void teAtaca(Zombie z,Resultado resultado){
		this.setDefensa(this.getDefensa()-z.getAtaque());
		
		if (!this.estaVivo()){
			this.getSurco().sacarPlanta(this);
			resultado.plantaMuerta(this);
		}
	}

	
	
	
	

}
