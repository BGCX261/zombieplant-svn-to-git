package aplicationModel;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.model.UserException;

import elementos.Juego;
import elementos.jardines.Maceta;
import elementos.jardines.Surco;
import elementos.plantas.Plantin;

public class PlantarModel implements Serializable{
	
		private List<Plantin> plantines;
		private Plantin plantinAPlantar;
		private Maceta macetaSeleccionada;
		
		
		public List<Plantin> getPlantines() {
			return plantines;
		}
		public Maceta getMacetaSeleccionada() {
			return macetaSeleccionada;
		}
		public void setMacetaSeleccionada(Maceta macetaSeleccionada) {
			this.macetaSeleccionada = macetaSeleccionada;
			if(!macetaSeleccionada.estaVacio()){
				throw new UserException("Cuidado!:La maceta esta ocupada,se perderá la planta "+ this.getMacetaSeleccionada().getNombrePlanta());
			}
		}
		public void setPlantines(List<Plantin> plantines) {
			this.plantines = plantines;
		}
		public Plantin getPlantinAPlantar() {
			return plantinAPlantar;
		}
		public void setPlantinAPlantar(Plantin plantinAPlantar) {
			this.plantinAPlantar = plantinAPlantar;
		}
		
		public void plantar(Surco surcoSeleccionado){
			this.macetaSeleccionada.plantar(this.plantinAPlantar);
			this.macetaSeleccionada.getPlanta().setSurco(surcoSeleccionado);
			this.macetaSeleccionada = null;
		}
		
		public void elegirPlantines(Juego juego,Surco surcoSeleccionado){
			this.plantines = juego.getPlantinesPara(surcoSeleccionado);
		}

}
