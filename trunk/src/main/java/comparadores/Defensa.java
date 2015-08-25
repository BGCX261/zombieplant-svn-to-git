package comparadores;

import elementos.plantas.Plantin;

public class Defensa extends ReversibleComparator<Plantin> {

	@Override
	protected int comparar(Plantin p1,Plantin p2) {
		return p1.getDefensa()-p2.getDefensa();
	}

	@Override
	public String getNombre() {
		return "defensa";
	}
}
