package zombievsplant.zombievsplant_wicket;

import org.apache.wicket.ajax.AjaxEventBehavior;
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
import org.uqbar.commons.model.UserException;


import elementos.Jugador;
import elementos.Zombie;
import elementos.jardines.JardinZen;
import elementos.jardines.Maceta;
import elementos.mejoras.Mejora;
import elementos.plantas.Plantin;

import aplicationModel.AlmanaqueModel;
import aplicationModel.BuscarPlantinesModel;
import aplicationModel.JardinZenModel;
import aplicationModel.JuegoModel;
import aplicationModel.MejorasModel;

public class AlmanaquePage extends BackPage {
	private static final long serialVersionUID = 1L;

	public AlmanaquePage(AlmanaqueModel almanaque, WebPage paginaAnterior) {
		super(paginaAnterior);
		this.setDefaultModel(new CompoundPropertyModel<AlmanaqueModel>(almanaque));
		this.addTituloZombie();
		@SuppressWarnings("rawtypes")
		Form form = new Form("almanaqueForm");
		this.add(form);
		this.addTabla(form);
		this.addBotonJardinZen(form);
		this.agregarBackButton("paginaAnterior", form);
		this.addBusqueda(form);
		// this.addzona();

	}

	public AlmanaquePage(JuegoModel jm, WebPage paginaAnterior) {
		this(new AlmanaqueModel(jm.getJuego()), paginaAnterior);
	}

	
	public void agregarDescripcion(){
		//add(new Label("zombieSeleccionado.descripcion"));
		add(new Label("zombieSeleccionado.nombre"));
	}
	
//	public JardinZenPage() {
//		this(new JardinZenModel(new JardinZen(), new Jugador()));
//	}

	protected AlmanaqueModel getAlmanaqueModel() {
		return (AlmanaqueModel) this.getDefaultModelObject();
	}

	
	protected void addBusqueda(Form form ){
	Form form2 =new Form("buscarForm");
	this.generarCampoBusqueda(form2);
	this.generarBotonBusqueda(form2);
	this.add(form2);
	// Al abrir el formulario disparo la búsqueda
	this.buscarZombies();
}

public void buscarZombies() {
	this.getAlmanaqueModel().buscar();
}

private void generarCampoBusqueda(Form form) {
	form.add(new TextField<String>("busqueda"));
}

private void generarBotonBusqueda(Form form) {
	form.add(new Button("buscar") {
		@Override
		public void onSubmit() {
			AlmanaquePage.this.buscarZombies();
		}

	});
	
}

	
private void addTituloZombie(){
	Label label1=new Label("zombieSeleccionado.nombre");
	label1.setOutputMarkupId(true);
	this.add(label1); 
	
	Label label2=new Label("zombieSeleccionado.descripcion");
	label2.setOutputMarkupId(true);
	this.add(label2);
	
	Label label3=new Label("zombieSeleccionado.ataque");
	label3.setOutputMarkupId(true);
	this.add(label3);
	
	Label label4=new Label("zombieSeleccionado.defensa");
	label4.setOutputMarkupId(true);
	this.add(label4);
}



	@SuppressWarnings("serial")
	protected void addTabla(final Form form) {
			form.add(new PropertyListView<Zombie>("zombies") {
					@Override
					protected void populateItem(final ListItem<Zombie> item) {
						item.add(new Label("nombre"));
						final Zombie zombie= (Zombie) item.getDefaultModelObject();
						item.add(new AjaxEventBehavior("onclick") {
						     protected void onEvent(AjaxRequestTarget target) {
						    	 AlmanaquePage.this.getAlmanaqueModel().setZombieSeleccionado(zombie);
						    	 target.add(AlmanaquePage.this.get("zombieSeleccionado.nombre"));
						    	 target.add(AlmanaquePage.this.get("zombieSeleccionado.descripcion"));
						    	 target.add(AlmanaquePage.this.get("zombieSeleccionado.ataque"));
						    	 target.add(AlmanaquePage.this.get("zombieSeleccionado.defensa"));
						     }
						 });
						
					}
			
			});
			
		}
		


	@SuppressWarnings("serial")
	protected void addBotonJardinZen(Form form) {
		form.add(new Button("jardinZen") {
			@Override
			public void onSubmit() {
				this.setResponsePage(new JardinZenPage(new BuscarPlantinesModel(AlmanaquePage.this.getAlmanaqueModel().getJuego().getJugador().getJardinZen(),AlmanaquePage.this.getAlmanaqueModel().getJuego().getJugador()), AlmanaquePage.this));
			}
		});

	}
	
	
	

}
