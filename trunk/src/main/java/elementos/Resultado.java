package elementos;


import elementos.plantas.Planta;
import elementos.plantas.Plantin;

public interface Resultado {

	public void plantaMuerta(Planta p);
	
	public void zombieMuerto(Zombie z);
	
	public void noDarPremio();
	
	public void darPremioPlantin(Plantin p);
	
	public void darPremioPuntos(double puntos);
	
	public void atacaSinPlantas();
	
}
