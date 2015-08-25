package arenaui;

import com.uqbar.commons.collections.Transformer;

import elementos.jardines.Surco;


public class TransformadorTipoSurco implements Transformer<Surco,String>{

   
	
	@Override
	public String transform(Surco s) {
		return s.getEsDeAgua() ? "Agua" : "Tierra";
	}

	

}
