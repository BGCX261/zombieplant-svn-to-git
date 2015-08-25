package elementos.jardines;

import java.io.Serializable;

import elementos.mejoras.Mejoras;
import elementos.plantas.Plantin;

public class JardinZen implements Serializable{
	private Zona acuatico;
	private Zona terrestre;

	public JardinZen(int ancho) {
		this.acuatico = new Zona(ancho, true);
		this.terrestre = new Zona(ancho, false);
		this.configuracionJardinZen();

	}

	public JardinZen() {
		this(20);
	}

	public Zona getAcuatico() {
		return acuatico;
	}

	public void setAcuatico(Zona acuatico) {
		this.acuatico = acuatico;
	}

	public Zona getTerrestre() {
		return terrestre;
	}

	public void setTerrestre(Zona terrestre) {
		this.terrestre = terrestre;
	}

	public void plantar(Plantin plantin) {
		if (plantin.getEsDeAgua()) {
			getAcuatico().getPlantines().add(plantin);
		} else {
			getTerrestre().getPlantines().add(plantin);
		}
	}
	
	public Plantin obtenerPlantinEnTodoJardin(String nombre){
		Plantin res=this.obtenerPlantinEnZona(this.getAcuatico(), nombre);
		if(res==null){
			res=this.obtenerPlantinEnZona(this.getTerrestre(), nombre);
		}
		return res;
	}
	
	public Plantin obtenerPlantinEnZona(Zona z,String nombre){
		return z.getPlantin(nombre);
	}

	public void configuracionJardinZen() {
		// Agregue una mejora a la lista de mejoras compradas de ete plantin
		Plantin a = new Plantin("Aguacatero", true, 10, 80);
		a.agregarMejora(Mejoras.getMejoras().get(1));

		this.plantar(a);

		this.plantar(new Plantin("Agrillos", true, 85, 35));
		this.plantar(new Plantin("Amapola", false, 55, 60));
		this.plantar(new Plantin("Balsamo", false, 40, 60));
		this.plantar(new Plantin("Brocoli", false, 90, 10));
		this.plantar(new Plantin("Gandul", true, 20, 40));
	}

}
