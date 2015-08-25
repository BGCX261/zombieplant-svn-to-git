package arenaui.windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import aplicationModel.JardinZenModel;
import aplicationModel.MejorasModel;
import arenaui.AcuaticoTerrestreTransformer;

import elementos.Jugador;
import elementos.jardines.JardinZen;
import elementos.plantas.Plantin;

public class JardinZenWindow extends SimpleWindow<JardinZenModel> {

	public JardinZenWindow(WindowOwner parent, JardinZen jardinZen, Jugador jugador) {
		super(parent, new JardinZenModel(jardinZen, jugador));
	}

	@Override
	protected void createMainTemplate(Panel mainPanel)  {
		this.setTitle("Plantas Vs Zombies");
		this.createFormPanel(mainPanel);
		this.createResultsGrid(mainPanel);
		this.createActionsPanel(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel tipoZonaPanel = new Panel(mainPanel);
		tipoZonaPanel.setLayout(new HorizontalLayout());
		new Label(tipoZonaPanel).setText("Jardin Zen").setBackground(Color.yellow);

		new Label(tipoZonaPanel).setWidth(75)
				.bindValueToProperty("zonaVisible.esDeAgua")
				.setTransformer(new AcuaticoTerrestreTransformer());

		Panel lugarDisponiblesPanel = new Panel(mainPanel);
		lugarDisponiblesPanel.setLayout(new HorizontalLayout());
		new Label(lugarDisponiblesPanel).setText("Lugares Disponibles");
		new Label(lugarDisponiblesPanel)
				.bindValueToProperty("zonaVisible.lugaresDisponibles");

	}

	protected void createResultsGrid(Panel mainPanel) {
		Table<Plantin> table = new Table<Plantin>(mainPanel, Plantin.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("zonaVisible.plantines");
		table.bindSelection("plantinSeleccionado");
		this.describeResultsGrid(table);
		this.plantinSeleccionadoPanel(mainPanel);
	}

	public void plantinSeleccionadoPanel(Panel mainPanel){
		Panel plantinPanel = new Panel(mainPanel);
		plantinPanel.setLayout(new HorizontalLayout());
		new Label(plantinPanel).setText("Seleccionado:");
		new Label(plantinPanel).setWidth(100)
				.bindValueToProperty("plantinSeleccionado.nombre");
		
		Button mejorar = new Button(plantinPanel);
		mejorar.setCaption("Mejorar")
		.onClick(new MessageSend(this, "irAMejoras"));
		
		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("plantinSeleccionado");
		mejorar.bindEnabled(elementSelected);
		
	}
	
	public void irAMejoras(){
		new MejorasWindow(this,new MejorasModel(getModelObject().getPlantinSeleccionado(), getModelObject().getJugador())).open();// falta pasar el jugador!
	}
	
	
	protected void describeResultsGrid(Table<Plantin> table) {
		new Column<Plantin>(table)
				//
				.setTitle("Nombre").setFixedSize(150)
				.bindContentsToProperty("nombre");

		new Column<Plantin>(table)
				//
				.setTitle("Ataque").setFixedSize(100)
				.bindContentsToProperty("ataque");

		new Column<Plantin>(table)
				//
				.setTitle("Defensa").setFixedSize(100)
				.bindContentsToProperty("defensa");

	}

	@Override
	protected void addActions(Panel actionsPanel) {
		 new Button(actionsPanel) 
		 .setCaption("cambiar de zona")
		.onClick(new MessageSend(this.getModelObject(),"cambiarZona"));

		 new Button(actionsPanel)
		 	.setCaption("Jugar")
			.onClick(new MessageSend(this,"close"));
		
	}

}
