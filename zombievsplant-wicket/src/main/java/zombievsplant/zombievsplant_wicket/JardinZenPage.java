package zombievsplant.zombievsplant_wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

import elementos.Jugador;
import elementos.jardines.JardinZen;
import elementos.plantas.Plantin;

import aplicationModel.BuscarPlantinesModel;
import aplicationModel.JardinZenModel;
import aplicationModel.JuegoModel;
import aplicationModel.MejorasModel;

public class JardinZenPage extends BackPage {
	private static final long serialVersionUID = 1L;

	public JardinZenPage(JardinZenModel jzm, WebPage paginaAnterior) {
		super(paginaAnterior);
		this.setDefaultModel(new CompoundPropertyModel<JardinZenModel>(jzm));
		this.addTituloZona();
		@SuppressWarnings("rawtypes")
		Form form = new Form("jardinZenForm");
		this.add(form);
		this.addTabla(form);
		this.addButtons(form);
		this.agregarBackButton("paginaAnterior", form);
		this.addBusqueda(form);
	}

	public JardinZenPage(JuegoModel jm, WebPage paginaAnterior) {
		this(new BuscarPlantinesModel(jm.getJardinZen(), jm.getJugador()),
				paginaAnterior);
	}

	protected BuscarPlantinesModel getJardinZenModel() {
		return (BuscarPlantinesModel) this.getDefaultModelObject();
	}

	// codigo robado
	protected void addBusqueda(Form form) {
		Form form2 = new Form("buscarForm");
		this.generarCampoBusqueda(form2);
		this.generarBotonBusqueda(form2);
		this.add(form2);
		// Al abrir el formulario disparo la búsqueda
		this.buscarPlantines();
	}

	public void buscarPlantines() {
		this.getJardinZenModel().buscar();
	}

	private void generarCampoBusqueda(Form form) {
		form.add(new TextField<String>("criterioBusqueda"));
	}

	private void generarBotonBusqueda(Form form) {
		form.add(new Button("buscar") {
			@Override
			public void onSubmit() {
				JardinZenPage.this.buscarPlantines();
			}

		});

	}

	// fin codigo robado

	private void addTituloZona() {
		this.add(new Label("zonaVisibleNombre"));
	}

	@SuppressWarnings("serial")
	protected void addTabla(final Form form) {
		addColumnComponent(form, "ataque");
		addColumnComponent(form, "defensa");
		addColumnComponent(form, "nombre");
		
		form.add(new PropertyListView<Plantin>("plantinesFiltrados") {
			@Override
			protected void populateItem(final ListItem<Plantin> item) {
				item.add(new Label("nombre"));
				item.add(new Label("ataque"));
				item.add(new Label("defensa"));
				item.add(new Link("mejorar") {
					@Override
					public void onClick() {
						this.setResponsePage(new MejorasPage(new MejorasModel(
								(Plantin) item.getDefaultModelObject(),
								JardinZenPage.this.getJardinZenModel()
										.getJugador()), JardinZenPage.this));
					}
				});
			}
		});
	}

	protected void addColumnComponent(final Form form, final String property) {
		AjaxLink headerLink = new AjaxLink(property + "Label") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				JardinZenPage.this.getJardinZenModel().setComparator(property);
				JardinZenPage.this.getJardinZenModel().buscar();
				target.add(form);
			}
		};
		form.add(headerLink);
	}

	@SuppressWarnings("serial")
	protected void addButtons(Form form) {
		form.add(new Button("cambiarZona") {
			@Override
			public void onSubmit() {
				getJardinZenModel().cambiarZona();
			}
		});

	}

}
