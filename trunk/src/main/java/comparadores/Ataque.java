package comparadores;

import java.io.Serializable;

import elementos.plantas.Plantin;


public class Ataque extends ReversibleComparator<Plantin> {

	@Override
	protected int comparar(Plantin p1,Plantin p2) {
		return p1.getAtaque()-p2.getAtaque();
	}

	@Override
	public String getNombre() {
		return "ataque";
	}

}
