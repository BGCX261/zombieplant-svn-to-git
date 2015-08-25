package elementos.plantas;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.TransactionalAndObservable;

import elementos.Imprimible;
import elementos.mejoras.Mejora;

@SuppressWarnings("serial")
@TransactionalAndObservable
public class Plantin extends Plantable implements Imprimible{

	private List<Mejora> mejorasCompradas = new ArrayList<Mejora>();
	
	
	public Plantin(boolean esDeAgua){
		this.setEsDeAgua(esDeAgua);
	}
	
	public Plantin() {
		super();
	}
	

	public Plantin(String nombre, boolean esDeAgua, int defensa, int ataque) {
		super(nombre, esDeAgua, defensa, ataque);
	}

	
	public List<Mejora> getMejorasCompradas() {
		return mejorasCompradas;
	}

	public void setMejorasCompradas(List<Mejora> mejorasCompradas) {
		this.mejorasCompradas = mejorasCompradas;
	}
	
	
	public void mejorar(Mejora m) {
		m.mejorar(this);
		this.agregarMejora(m);
	}

    public void agregarMejora(Mejora m){
    	
    	List<Mejora> mejoras = this.mejorasCompradas;
		mejoras.add(m);
		mejorasCompradas = new ArrayList<Mejora>(mejoras);

    }
	
	public Planta crearPlanta() {
		return new Planta(this.getNombre(), this.getEsDeAgua(),
				this.getAtaque(), this.getDefensa());

	}

	@Override
	public String descripcion() {
		// TODO Auto-generated method stub
		return this.getNombre();
	}

	

}
