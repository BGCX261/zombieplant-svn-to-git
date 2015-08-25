package arenaui;

import org.uqbar.arena.bindings.Transformer;

public class JardinAcuaticoTransformer implements Transformer<Boolean, String> {


	
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
		return esDeAgua ? "Jardin Terrestre": "Jardin Acuatico";
	}

	@Override
	public Boolean viewToModel(String nombre) {
		return nombre.equals("Jardin Terrestre");
	}
}

	

