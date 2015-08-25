package arenaui;

import org.uqbar.arena.bindings.Transformer;


public class AcuaticoTerrestreTransformer implements Transformer<Boolean, String> {
	
	@Override
	public Class<Boolean> getModelType() {
		return Boolean.class;
	}

	@Override
	public Class<String> getViewType() {
		return String.class;
	}

	@Override
	public String modelToView(Boolean esDeAgua) {
		return esDeAgua ? "Acuatico" : "Terrestre";
	}

	@Override
	public Boolean viewToModel(String nombre) {
		return nombre.equals("Acuatico");
	}
}
