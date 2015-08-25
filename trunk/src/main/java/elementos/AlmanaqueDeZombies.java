package elementos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class AlmanaqueDeZombies implements Serializable{

	private static List<Zombie> zombiesPrototipos = new ArrayList<Zombie>();
	static {
		zombiesPrototipos.add(new Zombie("Jisus",
				"El primer zombie de la historia", 50, 50));
		zombiesPrototipos.add(new Zombie("Michael Jackson",
				"Te ataca bailando la coreo de thriller", 50, 75));
		zombiesPrototipos.add(new Zombie("Charly Garcia",
				"El no lo sabe, pero esta no-muerto", 50, 100));
		zombiesPrototipos.add(new Zombie("Cerati",
				"El no lo sabe, pero esta no-vivo", 50, 25));

	}

	private List<Zombie> zombies = new ArrayList<Zombie>();

	public AlmanaqueDeZombies() {
		for (Zombie zombieAClonar : zombiesPrototipos) {
			this.zombies.add(zombieAClonar.clonar());
		}

	}

	public List<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}

	public static List<Zombie> getZombiesPrototipos() {
		return zombiesPrototipos;
	}

	public static void setZombiesPrototipos(List<Zombie> zombies) {
		AlmanaqueDeZombies.zombiesPrototipos = zombies;
	}

	
}
