package arenaui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import aplicationModel.JuegoModel;

public class DeseasReiniciar extends TransactionalDialog<JuegoModel>{

	public DeseasReiniciar(WindowOwner owner, JuegoModel model) {
		super(owner, model);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		new Button(mainPanel)
		.setCaption("Aceptar")
		.onClick(new MessageSend(this, "accept"))
		.setAsDefault()
		.disableOnError();

		new Button(mainPanel) //
			.setCaption("Cancelar")
			.onClick(new MessageSend(this, "cancel"));
		
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Reiniciar Juego");
		this.setTaskDescription("¿Esta seguro de reiniciar el juego?");
		super.createMainTemplate(mainPanel);
			
			
	}
	

	@Override
	public void accept() {
		this.close();
		super.accept();
	}
	

	
      
}
