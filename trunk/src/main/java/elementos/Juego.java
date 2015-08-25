package elementos;


import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import elementos.jardines.Jardin;
import elementos.jardines.Surco;
import elementos.mejoras.Mejoras;
import elementos.plantas.Planta;
import elementos.plantas.Plantin;

@Observable
public class Juego implements Serializable{
	private Jardin jardin;
	private AlmanaqueDeZombies almanaque;
	private Mejoras mejoras;
	private DarPremio premio;
	private Jugador jugador;
	
	public Juego(){
		this.configurarJuego();
	}
	
	public Juego(Jugador jugador) {
		this();
		this.setJugador(jugador);
	}

	public Jardin getJardin() {
		return jardin;
	}
	public void setJardin(Jardin jardin) {
		this.jardin = jardin;
	}
	public AlmanaqueDeZombies getAlmanaque() {
		return almanaque;
	}
	public void setAlmanaque(AlmanaqueDeZombies almanaque) {
		this.almanaque = almanaque;
	}
	public Mejoras getMejoras() {
		return mejoras;
	}
	public void setMejoras(Mejoras mejoras) {
		this.mejoras = mejoras;
	}
	public DarPremio getPremio() {
		return premio;
	}
	public void setPremio(DarPremio premio) {
		this.premio = premio;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	
	//******************
	//implementacion
	//******************
	
	/**
	 * la logica del ataque entre las plantas y el zombi
	 * @param zombie
	 * @param numeroSurco
	 */
	public void atacar(Zombie zombie,Surco surco,Resultado resultado){
		
		
		if(!surco.hayPlantas()){
			resultado.atacaSinPlantas();
		}else{
			while(surco.hayPlantas() && zombie.estaVivo()) {		
				Planta p = surco.plantaMasCerca();
				peleense(zombie, p,resultado);
			}
			if(!zombie.estaVivo()){;
			  //la entrega de premio
			    this.getPremio().calcularYDarPremio(this.getJugador(), zombie, resultado);
			    //saco al zombie del almanaque
			    zombie.revivir();
			    resultado.zombieMuerto(zombie);
			}
		}
		

	}

	protected void peleense(Zombie zombie, Planta p,Resultado resultado) {
		
	      zombie.atacarA(p,resultado);
		  p.getSurco().atacar(zombie, this,resultado);
	}
	
	public void plantar(Plantin platin, int x, Surco surco) {
		this.plantar(platin.crearPlanta(), x, surco.getPosicion());
	}
	
	//nota:p podria ser un plantin que genere una planta
	//en este metodo
	public void plantar(Planta p, int x, int y) {
		this.getJardin().plantar(p, x, y);
	}
	
	public void configurarJuego(){
		this.setAlmanaque(new AlmanaqueDeZombies());
		this.setJardin(new Jardin());
		this.setMejoras(new Mejoras());
	    this.setPremio(new DarPremio());
	}

	public List<Plantin> getPlantinesTerrestres() {
		return this.getJugador().getJardinZen().getTerrestre().getPlantines();
	}
	
	public List<Plantin> getPlantinesAcuaticos() {
		return this.getJugador().getJardinZen().getAcuatico().getPlantines();
	}

	public List<Plantin> getPlantinesPara(Surco surco) {
		//TODO: pasar la decision a JardinZen.getPlantinPara(Surco s)
		return surco.getEsDeAgua() ? this.getPlantinesAcuaticos() : this.getPlantinesTerrestres();  
	}

}
