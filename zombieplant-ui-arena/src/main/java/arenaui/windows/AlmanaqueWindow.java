package arenaui.windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import aplicationModel.AlmanaqueModel;

import elementos.Juego;
import elementos.Zombie;

public class AlmanaqueWindow extends SimpleWindow<AlmanaqueModel> {

	public AlmanaqueWindow(WindowOwner parent, Juego juego) {
		super(parent, new AlmanaqueModel(juego));
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Plantas Vs Zombies");
		this.createFormPanel(mainPanel);
		this.createResultsGrid(mainPanel);
		this.createActionsPanel(mainPanel);
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel zombieSelec = new Panel(mainPanel);
		zombieSelec.setLayout(new HorizontalLayout());
		new Label(zombieSelec).setText("Zombie seleccionado:").setBackground(Color.yellow);
		new Label(zombieSelec).setWidth(120).bindValueToProperty(
				"zombieSeleccionado.nombre");
		Panel zombieSelec2 = new Panel(mainPanel);
		zombieSelec2.setLayout(new HorizontalLayout());

		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

		new Label(searchFormPanel).setText("Zombie a buscar");
		new TextBox(searchFormPanel).bindValueToProperty("busqueda");
		new Button(searchFormPanel).setCaption("Buscar").onClick(
				new MessageSend(this.getModelObject(), "buscar"));

	}

	private void createResultsGrid(Panel mainPanel) {

		Panel dosPaneles = new Panel(mainPanel);
		dosPaneles.setLayout(new HorizontalLayout());

		Panel zombiesPanel = new Panel(dosPaneles);
		zombiesPanel.setLayout(new VerticalLayout());
		new Label(zombiesPanel).setText("Zombies");
		Table<Zombie> tabla = new Table<Zombie>(zombiesPanel, Zombie.class);
		tabla.setHeigth(160);
		tabla.setWidth(250);
		tabla.bindItemsToProperty("zombies");
		tabla.bindSelection("zombieSeleccionado");

		// }
		//
		//

		Panel infoPanel = new Panel(dosPaneles);
		infoPanel.setLayout(new VerticalLayout());
		new Label(infoPanel).setText("Datos del Zombie").setBackground(Color.GRAY);

		Panel nombrePanel = new Panel(infoPanel);
		nombrePanel.setLayout(new HorizontalLayout());
		new Label(nombrePanel).setText("Nombre: ").setBackground(Color.GREEN);
		new Label(nombrePanel).setWidth(150).bindValueToProperty(
				"zombieSeleccionado.nombre");

		Panel ataquePanel = new Panel(infoPanel);
		ataquePanel.setLayout(new HorizontalLayout());
		new Label(ataquePanel).setText("Ataque: ").setBackground(Color.GREEN);
		new Label(ataquePanel).setWidth(75).bindValueToProperty(
				"zombieSeleccionado.ataque");

		Panel defPanel = new Panel(infoPanel);
		defPanel.setLayout(new HorizontalLayout());
		new Label(defPanel).setText("Defensa: ").setBackground(Color.GREEN);
		new Label(defPanel).setWidth(75).bindValueToProperty(
				"zombieSeleccionado.defensa");

		Panel desPanel = new Panel(infoPanel);
		desPanel.setLayout(new HorizontalLayout());
		new Label(desPanel).setText("Descripcion: ").setBackground(Color.GREEN);
		new Label(desPanel).setWidth(250).bindValueToProperty(
				"zombieSeleccionado.descripcion");

		
		
		this.describeResultsGrid(tabla);
	}

	//
	//
	//
	//
	private void describeResultsGrid(Table<Zombie> tabla) {
		new Column<Zombie>(tabla).setTitle("Zombies").setFixedSize(200)
				.bindContentsToProperty("nombre");

	}

	//

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Jugar").onClick(
				new MessageSend(this, "close"));

		new Button(actionsPanel).setCaption("Jardin Zen").onClick(
				new MessageSend(this, "irAlJardinZen"));

	}

	public void irAlJardinZen() {
		new JardinZenWindow(this, this.getModelObject().getJuego().getJugador()
				.getJardinZen(), this.getModelObject().getJuego().getJugador())
				.open();
	}

}