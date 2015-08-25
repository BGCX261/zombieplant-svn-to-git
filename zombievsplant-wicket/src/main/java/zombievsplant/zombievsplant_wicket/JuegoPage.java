package zombievsplant.zombievsplant_wicket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


import aplicationModel.JuegoModel;
import elementos.Imprimible;
import elementos.Zombie;
import elementos.plantas.Plantin;

public class JuegoPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public JuegoPage(final PageParameters parameters) {
		super(parameters);
		this.setApplicationModelJuego();
		this.addControlesAtacar();
		this.addControlesPlantar();
        this.addTablaJardin();
        this.addBotones();
        this.setOutputMarkupId(true);
        this.addResultados();
        this.addRecursos();
    }
	
	private void addRecursos(){
		Label recursosLabel=new Label("juego.jugador.puntos");
		recursosLabel.setOutputMarkupId(true);
		this.add(recursosLabel);
	}

	private void addControlesAtacar() {
		DropDownChoice selectorZombies = this.crearSelector("atacarModel.zombieAAtacar", ReflectionUtils.getMetodoDe(JuegoModel.class,"getZombies"));
		
		//se agrega evento onchange
		selectorZombies.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				JuegoPage.this.getJuegoModel().setZombieAAtacar( (Zombie) this.getComponent().getDefaultModelObject());
			    target.add(JuegoPage.this.get("atacar"));
			}
		});
		add(selectorZombies);
		
		AjaxLink atacarBoton =this.crearBoton("atacar",ReflectionUtils.getMetodoDe(JuegoModel.class,"atacar"));
		add(atacarBoton);
		bindEnabled(atacarBoton,selectorZombies);
	}

	
	
	private void addControlesPlantar() {
		DropDownChoice selectorPlantines = this.crearSelector("plantarModel.plantinAPlantar", ReflectionUtils.getMetodoDe(JuegoModel.class,"getPlantines"));
		
		selectorPlantines.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				JuegoPage.this.getJuegoModel().setPlantinAPlantar((Plantin) this.getComponent().getDefaultModelObject());
			    target.add(JuegoPage.this.get("plantar"));
			}
		});
		
		add(selectorPlantines);
		AjaxLink plantarBoton = this.crearBoton("plantar",ReflectionUtils.getMetodoDe(JuegoModel.class,"plantar"));
		add(plantarBoton);
		bindEnabled(plantarBoton, selectorPlantines);
	}

	
	protected void bindEnabled(final Component component, final DropDownChoice dependency) {
		component.add(new Behavior() {
			@Override
			public void onConfigure(Component component) {
				super.onConfigure(component);
				component.setEnabled(dependency.getModelObject() != null);
			}
		});
	}
	
	
	public DropDownChoice<Imprimible> crearSelector(String id,final Method listGetter) {
		DropDownChoice<Imprimible> selector=new DropDownChoice<Imprimible>(id,new LoadableDetachableModel<List<Imprimible>>() {
			@Override
			protected List<Imprimible> load() {
					try {
						return (List<Imprimible>) listGetter.invoke(JuegoPage.this.getJuegoModel());
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return null;
			}
		},
		
		new ChoiceRenderer<Imprimible>() {
			@Override
			public Object getDisplayValue(Imprimible object) {
				return object.descripcion();
			}
		}
	    );
		selector.setOutputMarkupId(true);
		selector.setEnabled(false);
		return selector;
	}
	
	public AjaxLink crearBoton(String id,final Method accion){
		AjaxLink b=new AjaxLink(id){

			@Override
			public void onClick(AjaxRequestTarget target) {
				try {
					
					accion.invoke(JuegoPage.this.getJuegoModel());
					JuegoPage.this.desabilitarSelectores();
					JuegoPage.this.refrescarTodosLosComponentes(target);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		return b;
	}


	private void addBotones(){
		Form form = new Form("jardinZenForm");
		add(form);
		form.add(new Link("jardinZen") {
			@Override
			public void onClick() {
				this.setResponsePage(new JardinZenPage(JuegoPage.this.getJuegoModel(),JuegoPage.this));
			}
		});
		
		Form form0 = new Form("almanaqueForm");
		add(form0);
		form0.add(new Link("almanaque") {
			@Override
			public void onClick() {
				this.setResponsePage(new AlmanaquePage(JuegoPage.this.getJuegoModel(), JuegoPage.this));
			}
		});
		
		
		Form form1 = new Form("reiniciarForm");
		add(form1);
		form1.add(new Link("reiniciar") {
			@Override
			public void onClick() {
				JuegoPage.this.setApplicationModelJuego();
				//porque no pude hacer el binding con el defaultModelObject
				JuegoPage.this.remove(JuegoPage.this.get("acciones"));
				JuegoPage.this.addResultados();
				JuegoPage.this.desabilitarSelectores();
			}
		});
	}
	
	

	private void setApplicationModelJuego() {
		this.setDefaultModel(new CompoundPropertyModel<JuegoModel>(new JuegoModel()));
	}
	
	public JuegoModel getJuegoModel(){
		return (JuegoModel)this.getDefaultModel().getObject();
	}
	
	@SuppressWarnings("serial")
	protected void addTablaJardin() {
		add(new TablaDeJardin("surcos",this));
		
	}
	
	private void addResultados() {
		//no pude hacer el binding con de defaultModelObject
		DropDownChoice<String> acciones=new DropDownChoice<String>("acciones",new LoadableDetachableModel<List<String>>() {
			@Override
			protected List<String> load() {
				return getJuegoModel().getAcciones();
			}
		});
		acciones.setOutputMarkupId(true);
		this.add(acciones);
	}
	
	public void refrescarTodosLosComponentes(AjaxRequestTarget target){
		this.refrescarComponente("atacarModel.zombieAAtacar", target);
		this.refrescarComponente("plantarModel.plantinAPlantar", target);
		this.refrescarComponente("atacar", target);
		this.refrescarComponente("plantar", target);
		this.refrescarComponente("juego.jugador.puntos", target);
		this.refrescarComponente("acciones", target);
		target.add(this);
	}
	
	public void habilitarSelectores(){
		this.habilitarComponente("atacarModel.zombieAAtacar", true);
		this.habilitarComponente("plantarModel.plantinAPlantar", true);
	}
	
	public void refrescarComponente(String nombre,AjaxRequestTarget target){
		target.add(this.get(nombre));
	}
	
	public void habilitarComponente(String nombre,boolean habilitado){
		this.get(nombre).setEnabled(habilitado);
	}
	
	public void desabilitarSelectores() {
		((DropDownChoice)this.get("plantarModel.plantinAPlantar")).setModelObject(null);
		((DropDownChoice)this.get("atacarModel.zombieAAtacar")).setModelObject(null);
		this.habilitarComponente("atacarModel.zombieAAtacar",false);
		this.habilitarComponente("plantarModel.plantinAPlantar",false);
	}
	
	public void refrescarBotones(AjaxRequestTarget target){
		this.refrescarComponente("atacar",target);
		this.refrescarComponente("plantar",target);
	}

}