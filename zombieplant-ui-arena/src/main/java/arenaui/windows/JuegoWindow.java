package arenaui.windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.TextFilter;
import org.uqbar.arena.widgets.TextInputEvent;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import aplicationModel.JuegoModel;
import arenaui.DeseasReiniciar;
import arenaui.Identificacion;
import arenaui.TransformadorGetPlanta;
import arenaui.TransformadorTipoSurco;

import com.uqbar.commons.StringUtils;

import elementos.Zombie;
import elementos.jardines.Surco;
import elementos.plantas.Plantin;

public class JuegoWindow extends SimpleWindow<JuegoModel> {

	public JuegoWindow(WindowOwner parent, JuegoModel model) {
		super(parent, model);

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Zombies vs Plantas");
		this.setTaskDescription("Seleccione una fila y agrege una planta o ataque con un zombie");

		super.createMainTemplate(mainPanel);

		// en el panel principal con los botones de reiniciar ,jardinZen
		// almanaque y
		// el no mbre del jardin
		// this.crearPanelAltoIzq(mainPanel);

		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		// crea la tabla(jardin) en el panel actionsPanel
		this.crearPanelDeTabla(actionsPanel);

		// en el mismo panel de la tabla crea el menu para agregar plantas
		this.crearPanelDePlantines(actionsPanel);

		// en el panel principal crea el menu para atacar
		this.crearPanelDeZombies(mainPanel);

	}

	/**
	 * crea un panel horizontal dentro de mainPanel,con el boton de
	 * atacar,labels y un selector de zombie.Si no hay un zombie seleccionado se
	 * bloquea el boton y si no se selecciona una fila de la tabla se bloquea el
	 * selector
	 * 
	 * @param mainPanel
	 */
	private void crearPanelDeZombies(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button atacar = new Button(actionsPanel)
				//
				.setCaption("Atacar")
				.onClick(new MessageSend(this.getModelObject(), JuegoModel.ATACAR))
				.setAsDefault().disableOnError();

		new Label(actionsPanel).setText("  Zombies:  ").setForeground(
				Color.BLUE);
		new Label(actionsPanel).setText("Elige un zombie y ataque  ")
				.setForeground(Color.black);

		// selector de zombies
		Selector<Zombie> selector = new Selector<Zombie>(actionsPanel) //
				.allowNull(false);
		selector.bindValueToProperty("zombieAAtacar");

		Binding<ListBuilder<Zombie>> itemsBinding = selector.bindItems( //
				new ObservableProperty(this.getModelObject(), "zombies"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la
		// grilla.
		NotNullObservable elementSelected = new NotNullObservable(
				"zombieAAtacar");
		atacar.bindEnabled(elementSelected);
		elementSelected = new NotNullObservable("surcoSelect");
		selector.bindEnabled(elementSelected);

	}

	/**
	 * crea un panel horizontal dentro de mainPanel,con el boton de
	 * agregar,labels , checkBox con binding a la columna del jardin y un
	 * selector de plantines.Si no hay un plantin seleccionado se bloquea el
	 * boton y si no se selecciona una fila de la tabla se bloquea el selector
	 * 
	 * @param mainPanel
	 */
	private void crearPanelDePlantines(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new ColumnLayout(2));

		new Label(actionsPanel).setText("Plantines:").setForeground(Color.BLUE);
		new Label(actionsPanel).setText("Elige un plantin y su columna")
				.setForeground(Color.BLACK);

		new Label(actionsPanel).setText("Columna:").setForeground(Color.BLUE);
		TextBox column = new TextBox(actionsPanel);
		column.withFilter(new TextFilter() {
			public boolean accept(TextInputEvent event) {

				return StringUtils.isNumeric(event.getPotentialTextResult())
						&& event.getPotentialTextResult().length() <= 10;

			}
		});
		column.bindValueToProperty("posX");

		new Label(actionsPanel).setText("Auto inc.").setForeground(Color.BLUE);
		new CheckBox(actionsPanel).bindValueToProperty("autoIncrement");

		Button agregar = new Button(actionsPanel)
				//
				.setCaption("Agregar Planta")
				.onClick(new MessageSend(this.getModelObject(), "agregar"))
				.setAsDefault().disableOnError();

		// Este panel es para poder settear el tamaño del select
		// se que esta mal pero sino tira error
		Panel panelHorA = new Panel(actionsPanel);
		panelHorA.setLayout(new HorizontalLayout());

		// selector de plantines
		Selector<Plantin> selector = new Selector<Plantin>(panelHorA) //
				.allowNull(false);
		selector.bindValueToProperty("plantinAPlantar");
		selector.setWidth(100);

		Binding<ListBuilder<Plantin>> itemsBinding = selector.bindItems( //
				new ObservableProperty(this.getModelObject(), "plantines"));

		itemsBinding.setAdapter( //
				new PropertyAdapter(Plantin.class, "nombre"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado del
		// jardin.
		NotNullObservable elementSelected = new NotNullObservable(
				"plantinAPlantar");
		agregar.bindEnabled(elementSelected);
		elementSelected = new NotNullObservable("surcoSelect");
		selector.bindEnabled(elementSelected);

		new Label(actionsPanel).setText("Acciones del juego:").setForeground(
				Color.RED);

		// panel para el listado de acciones del juego
		// HorizontalLayout() para que funciones
		Panel panelHor = new Panel(actionsPanel);
		panelHor.setLayout(new HorizontalLayout());

		List<String> listado = new List<String>(panelHor);
		listado.bindItemsToProperty("acciones");
		listado.setHeigth(70);
		listado.setWidth(220);
	}

	/**
	 * Crea una table que representa al jardin en binding con con una lista de
	 * surcos del modelo ConfiguracionJuego
	 * 
	 * @param mainPanel
	 */
	private void crearPanelDeTabla(Panel mainPanel) {

		Table<Surco> table = new Table<Surco>(mainPanel, Surco.class);
		table.setHeigth(160);
		table.setWidth(640);
		table.bindItemsToProperty("jardin");
		table.bindValueToProperty("surcoSelect");

		this.agregarColumnas(table);

	}

	/**
	 * Agrega las columnas a la table
	 * 
	 * @param table
	 */
	private void agregarColumnas(Table<Surco> table) {

		new Column<Surco>(table)
				//
				.setTitle("Fila").setFixedSize(70)
				.bindContentsToProperty("posicion");

		new Column<Surco>(table)
				//
				.setTitle("Terreno").setFixedSize(70)
				.bindContentsToTransformer(new TransformadorTipoSurco());

		for (int i = 0; i < 5; i++) {

			new Column<Surco>(table)
					//
					.setTitle(Integer.toString(i)).setFixedSize(100)
					.bindContentsToTransformer(new TransformadorGetPlanta(i));

		}
	}

	public void identificacion() {
		this.openDialog(new Identificacion(this, this.getModelObject()),
				"agregarNombreAJugador");
	}

	/**
	 * abre una nueva ventana indicando si desea reiniciar
	 */
	public void reiniciar() {
		DeseasReiniciar dr = new DeseasReiniciar(this, this.getModelObject());
		dr.onAccept(new MessageSend(this, "reiniciate"));
		dr.open();

	}

	protected void openDialog(Dialog<?> dialog, String var) {
		dialog.onAccept(new MessageSend(this.getModelObject(), var));
		dialog.open();
	}

	public void irAlJardinZen() {
		new JardinZenWindow(this, this.getModelObject().getJardinZen(), this
				.getModelObject().getJugador()).open();
	}

	public void irAlAlmanaque() {
		new AlmanaqueWindow(this, this.getModelObject().getJuego()).open();
	}

	public void reiniciate() {

		this.close();
		JuegoModel jm = new JuegoModel();
		jm.setNombre(this.getModelObject().getNombre());
		new JuegoWindow(getOwner(), jm).open();

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new ColumnLayout(7));

		new Button(actionsPanel) //
				.setCaption("Reiniciar").onClick(
						new MessageSend(this, "reiniciar"));

		new Button(actionsPanel) //
				.setCaption("Jardin Zen").onClick(
						new MessageSend(this, "irAlJardinZen"));

		new Button(actionsPanel)//
				.setCaption("Almanaque De Zombies").onClick(
						new MessageSend(this, "irAlAlmanaque"));

		new Label(actionsPanel).setText("Nombre del Jugador: ").setForeground(
				Color.BLUE);
		new Label(actionsPanel).setBackground(Color.GREEN).bindValueToProperty(
				"nombre");

		new Label(actionsPanel).setText(" PUNTAJE: ").setForeground(Color.BLUE);

		// Este panel es para poder settear el tamaño del select
		// se que esta mal pero sino tira error
		Panel panelHorA = new Panel(actionsPanel);
		panelHorA.setLayout(new HorizontalLayout());

		Label labelPuntos = new Label(panelHorA); //
		labelPuntos.setBackground(Color.ORANGE).bindValueToProperty(
				"juego.jugador.puntos");
		labelPuntos.setWidth(50);

		new Label(actionsPanel).setText("El Jardin").setForeground(Color.BLUE)
				.setForeground(Color.GREEN).setFontSize(20);

	}

	public void createPanelSuperior(Panel mainPanel) {

		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		new Label(actionsPanel).setText("Columna: ").setForeground(Color.BLUE);
		new TextBox(actionsPanel).bindValueToProperty("posX");

		new Label(actionsPanel).setText("  Plantines:  ").setForeground(Color.BLUE);

		// selector de plantines
		Selector<Plantin> selector = new Selector<Plantin>(actionsPanel) //
				.allowNull(false);
		selector.bindValueToProperty("plantinAPlantar");

		Binding<ListBuilder<Plantin>> itemsBinding = selector.bindItems( //
				new ObservableProperty(this.getModelObject(), "plantines"));

		itemsBinding.setAdapter( //
				new PropertyAdapter(Plantin.class, "nombre"));

		Button agregar = new Button(actionsPanel)
				//
				.setCaption("Agregar Planta")
				.onClick(new MessageSend(this.getModelObject(), "agregar"))
				.setAsDefault().disableOnError();

		// Deshabilitar los botones si no hay ningún elemento seleccionado del
		// jardin.
		NotNullObservable elementSelected = new NotNullObservable(
				"plantinAPlantar");
		agregar.bindEnabled(elementSelected);

	}

	@Override
	protected void addActions(Panel actionsPanel) {
	}

}
