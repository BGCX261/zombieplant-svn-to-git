package arenaui.windows;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import aplicationModel.MejorasModel;

import elementos.mejoras.Mejora;

public class MejorasWindow extends SimpleWindow<MejorasModel> {

	public MejorasWindow(WindowOwner parent, MejorasModel model) {
		super(parent, model);
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
		Panel mejoraPanel = new Panel(mainPanel);
		mejoraPanel.setLayout(new HorizontalLayout());
		new Label(mejoraPanel).setText("Estas Mejorando:");//
		new Label(mejoraPanel)
				.bindValueToProperty("plantinSeleccionado.nombre");

		Panel puntosPanel = new Panel(mainPanel);
		puntosPanel.setLayout(new HorizontalLayout());
		new Label(puntosPanel).setText("Recursos:");
		new Label(puntosPanel).bindValueToProperty("jugador.puntos");

	}

	protected void createResultsGrid(Panel mainPanel) {

		Panel dosTablasPanel = new Panel(mainPanel);
		dosTablasPanel.setLayout(new HorizontalLayout());

		Panel grillaMejoraPanel = new Panel(dosTablasPanel);

		grillaMejoraPanel.setLayout(new VerticalLayout());
		new Label(grillaMejoraPanel).setText("Mejoras Compradas");
		Table<Mejora> table = new Table<Mejora>(grillaMejoraPanel, Mejora.class);
		table.setHeigth(160);
		table.setWidth(200);
		table.bindItemsToProperty("plantinSeleccionado.mejorasCompradas");

		Panel grillaMejorasDisPanel = new Panel(dosTablasPanel);
		grillaMejorasDisPanel.setLayout(new VerticalLayout());
		new Label(grillaMejorasDisPanel).setText("Mejoras Disponibles");
		Table<Mejora> tabla = new Table<Mejora>(grillaMejorasDisPanel,
				Mejora.class);
		tabla.setHeigth(160);
		tabla.setWidth(250);
		tabla.bindItemsToProperty("mejorasDisponibles");
		tabla.bindSelection("mejoraSeleccionada");

		this.describeResultsGrid(table);
		this.describeResultsMejoras(tabla);

	}

	protected void describeResultsGrid(Table<Mejora> table) {
		new Column<Mejora>(table).setTitle("Mejora").setFixedSize(200)
				.bindContentsToProperty("descripcion");
	}

	private void describeResultsMejoras(Table<Mejora> tabla) {
		new Column<Mejora>(tabla).setTitle("Mejora").setFixedSize(200)
				.bindContentsToProperty("descripcion");

		new Column<Mejora>(tabla).setTitle("Costo").setFixedSize(200)
				.bindContentsToProperty("costo");

	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button comprar=new Button(actionsPanel);
		comprar.setCaption("Comprar").onClick(
				new MessageSend(this.getModelObject(), "comprarMejora"));

		new Button(actionsPanel).setCaption("Cerrar").onClick(
				new MessageSend(this, "close"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
	    NotNullObservable elementSelected = new NotNullObservable("mejoraSeleccionada");
	    comprar.bindEnabled(elementSelected);
				
	}

}
