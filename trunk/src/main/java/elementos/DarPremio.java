package elementos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elementos.plantas.Plantin;

public class DarPremio implements Serializable{
	public static List<Plantin> plantinesParaPremios = new ArrayList<Plantin>();
	static {
		plantinesParaPremios.add(new Plantin("Salvia Divinorum", false, 100, 50));
		plantinesParaPremios.add(new Plantin("Vainilla", false, 100, 50));
		plantinesParaPremios.add(new Plantin("Muerdago", false, 80, 40));
		plantinesParaPremios.add(new Plantin("Estragón", false, 60, 30));
		plantinesParaPremios.add(new Plantin("Sesamo", false, 50, 20));
		plantinesParaPremios.add(new Plantin("Adelfa", true, 90, 50));
		plantinesParaPremios.add(new Plantin("Maranta", true, 70, 40));
		plantinesParaPremios.add(new Plantin("Alga", true, 50, 30));
		plantinesParaPremios.add(new Plantin("Irupe", true, 50, 20));
		plantinesParaPremios.add(new Plantin("castus", true, 45, 20));
	}

	public static List<Plantin> getPlantinesParaPremios() {
		return plantinesParaPremios;
	}

	public static void setPlantinesParaPremios(
			List<Plantin> plantinesParaPremios) {
		DarPremio.plantinesParaPremios = plantinesParaPremios;
	}

	// *****************
	// implementacion
	// *****************
	public void calcularYDarPremio(Jugador j, Zombie z,Resultado resultado) {

		// %50 de posibilidades de entregarle un premio
		if (0.5 <= Math.random()) {

			// ahora %25 de que sea una planta ,si es que uedan para entregar
			if (0.25 <= Math.random() && !this.plantinesParaPremios.isEmpty()) {
				Plantin p=obtenerPlantinRandom();
				j.getJardinZen().plantar(p);
				resultado.darPremioPlantin(p);
			} else {
				// de lo contrario gana puntos
				double t =Math.floor( (Math.random()*z.getResistenciaInicial()));
				t++;
				j.setPuntos(j.getPuntos() + t);
				System.out.println(z.getResistenciaInicial());
				resultado.darPremioPuntos(t);
			}

		}else{
			resultado.noDarPremio();
		}

	}

	/**
	 * Retorna un plantin aleatoreamente y lo saca de la lista
	 * 
	 * @return plantin
	 */
	protected Plantin obtenerPlantinRandom() {
		return this.plantinesParaPremios.remove(new Random()
				.nextInt(this.plantinesParaPremios.size()));
	}

}
