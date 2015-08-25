package comparadores;

import java.io.Serializable;
import java.util.Hashtable;

import org.uqbar.commons.model.UserException;

import elementos.plantas.Plantin;

public class Comparadores  implements Serializable{

	private static Hashtable<String,ReversibleComparator<Plantin>> comparadores;
	
	static{
		Comparadores.comparadores=new Hashtable<String,ReversibleComparator<Plantin>>();
		comparadores.put("ataque", new Ataque());
		comparadores.put("defensa", new Defensa());
		comparadores.put("nombre", new Nombre());
	
	}
	
	/*
	 * retorna el comparador mapeado con la clave tipo
	 * 
	 */
	public static ReversibleComparator<Plantin> get(String tipo){
		ReversibleComparator<Plantin> r=Comparadores.comparadores.get(tipo);
		if(r==null){
			throw new UserException("El comparador no existe");
		}
		return r;
	}
	
	
}
