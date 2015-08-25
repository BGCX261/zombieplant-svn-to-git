package comparadores;

import elementos.plantas.Plantin;

public class Nombre extends ReversibleComparator<Plantin> {

	@Override
	protected int comparar(Plantin p1,Plantin p2) {
		return p2.getNombre().compareToIgnoreCase(p1.getNombre());
	}

	@Override
	public String getNombre() {
		return "nombre";
	}
}
