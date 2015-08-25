package arenaui;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import aplicationModel.JuegoModel;
import arenaui.windows.JuegoWindow;

public class Identificacion extends Dialog<JuegoModel>{

	public Identificacion(WindowOwner owner, JuegoModel model) {
		super(owner, model);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		new Label(mainPanel).setText("Ingresa tu nombre").setForeground(Color.BLUE);
		TextBox nombre=new TextBox(mainPanel);
		nombre.bindValueToProperty("nombre");		
		new Button(mainPanel)
		.setCaption("Aceptar")
		.onClick(new MessageSend(this, "accept"))
		.setAsDefault()
		.disableOnError();
		
	}

	@Override
	public void accept() {
		this.close();
		new JuegoWindow(getOwner(), this.getModelObject()).open();
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Identificacion");
		this.setTaskDescription("Por favor ingresá tu nombre");
		super.createMainTemplate(mainPanel);
	}

}
