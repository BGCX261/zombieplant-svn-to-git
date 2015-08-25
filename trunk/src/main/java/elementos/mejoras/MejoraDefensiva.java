package elementos.mejoras;
import org.uqbar.commons.utils.Observable;

import elementos.plantas.Plantin;


@Observable
public class MejoraDefensiva extends Mejora{

	
	
	
	
	public MejoraDefensiva(int mejora, String descripcion, int costo) {
		super(mejora, descripcion, costo);
		// TODO Auto-generated constructor stub
	}

	/**
	 * mejora la planta en defensa segun mejora
	 */
	@Override
	public void mejorar(Plantin plantin) {
		plantin.setDefensa(plantin.getDefensa()+this.getMejora());
	
		
	}

}
