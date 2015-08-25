package aplicationModel;

import java.io.Serializable;
import java.util.LinkedList;

import elementos.Resultado;
import elementos.Zombie;
import elementos.plantas.Planta;
import elementos.plantas.Plantin;

public class ResultadoDePelea implements Resultado, Serializable{

	private JuegoModel juegoModel;
	
	public ResultadoDePelea(JuegoModel mdj){
		juegoModel=mdj;
	}
	
	
	public JuegoModel getJuegoModel() {
		return juegoModel;
	}

	public void setJuegoModel(JuegoModel juegoModel) {
		this.juegoModel = juegoModel;
	}

	@Override
	public void plantaMuerta(Planta p) {
		settear("La planta "+p.getNombre()+" ha muerto");
	
		
	}

	@Override
	public void zombieMuerto(Zombie z) {
		
		settear("El zombie "+ z.getNombre() + " ha muerto");
		
	}


	@Override
	public void noDarPremio() {
		
		settear("No recibiste premio");
	}


	@Override
	public void darPremioPlantin(Plantin p) {
		// TODO Auto-generated method stub
	
		settear("Ganaste plantin "+p.getNombre());
	}


	@Override
	public void darPremioPuntos(double puntos) {
	
		settear("Ganaste "+puntos+" puntos");
		
	}


	@Override
	public void atacaSinPlantas() {
		// TODO Auto-generated method stub
		settear("Te atacaron sin plantas,agrega una");
	}
	
	public void settear(String accion){
		//casteo a LinkedList para obtener el metodo addFirst!!!!!!!!!!
		//para mostrarlo mejor!!!!!!!!!
		LinkedList<String> aux=(LinkedList<String>)this.getJuegoModel().getAcciones();
		aux.addFirst(accion);
		
		this.getJuegoModel().setAcciones(null);
		this.getJuegoModel().setAcciones(aux);
	}

}
