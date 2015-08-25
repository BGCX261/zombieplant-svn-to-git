package elementos.mejoras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class Mejoras implements Serializable{

	public static List<Mejora> mejoras = new ArrayList<Mejora>();
	static {
		mejoras.add(new MejoraDefensiva(20, "Mejora defensa chica", 20));
		mejoras.add(new MejoraDefensiva(50, "Mejora defensa media", 100));
		mejoras.add(new MejoraDefensiva(100, "Mejora defensa grande", 200));
		mejoras.add(new MejoraOfensiva(20, "Mejora ataque chico",25));
		mejoras.add(new MejoraOfensiva(50, "Mejora ataque media", 100));
		mejoras.add(new MejoraOfensiva(100, "Mejora ataque grande", 200));

	}

	public static List<Mejora> getMejoras() {
		return mejoras;
	}
	
	public static void setMejoras(List<Mejora> mejoras) {
		Mejoras.mejoras = mejoras;
	}
	
	public static List<Mejora> getMejorasExcepto(List<Mejora> excepto) {
		return (List<Mejora>) CollectionUtils.subtract(getMejoras(), excepto);
	}
	
}
