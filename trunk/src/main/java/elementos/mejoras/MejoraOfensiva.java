package elementos.mejoras;
import org.uqbar.commons.utils.Observable;

import elementos.plantas.Plantin;


@Observable
public class MejoraOfensiva extends Mejora{

	private int mejora;
	private String descripcion;
	private int costo;
	
	
	
	public MejoraOfensiva(int mejora, String descripcion, int costo) {
		super(mejora, descripcion, costo);
		// TODO Auto-generated constructor stub
	}
	
	public MejoraOfensiva(){
		super();
	}


	/**
	 * mejora la planta en ataque segun mejora
	 */
	public void mejorar(Plantin plantin) {

		plantin.setAtaque(plantin.getAtaque()+this.getMejora());
		
	}


}
