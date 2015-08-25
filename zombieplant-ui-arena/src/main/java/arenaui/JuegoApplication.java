package arenaui;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import aplicationModel.JuegoModel;



public class JuegoApplication extends Application{

	@Override
	protected Window<?> createMainWindow() {
		return new Identificacion(this, new JuegoModel());
	}
	
	public static void main(String[] args){
		new JuegoApplication().start();
	}



}
