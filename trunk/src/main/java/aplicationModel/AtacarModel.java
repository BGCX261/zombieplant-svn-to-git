package aplicationModel;

import java.io.Serializable;
import java.util.List;

import elementos.Juego;
import elementos.Zombie;
import elementos.jardines.Surco;

public class AtacarModel implements Serializable{
	
	private List<Zombie> zombies;
	private Zombie zombieAAtacar;
	private ResultadoDePelea resultado;
	
	public AtacarModel(JuegoModel juegoModel){
		this.resultado=new ResultadoDePelea(juegoModel);
	}
	
	public List<Zombie> getZombies() {
		return zombies;
	}
	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}
	public Zombie getZombieAAtacar() {
		return zombieAAtacar;
	}
	public void setZombieAAtacar(Zombie zombieAAtacar) {
		this.zombieAAtacar = zombieAAtacar;
	}
	
	public void atacar(Surco surcoSeleccionado,Juego juego){
		juego.atacar(this.zombieAAtacar,surcoSeleccionado, resultado);
		//surcoSeleccionado.atacar(this.zombieAAtacar, juego, resultado);
	}

}
