package zombievsplant.zombievsplant_wicket;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.uqbar.commons.model.UserException;

import aplicationModel.JuegoModel;
import elementos.jardines.Maceta;
import elementos.jardines.Surco;

public class TablaDeJardin extends PropertyListView<Surco>{

	private JuegoPage juegoPage;
	public ListItem<Maceta> ultimoSeleccionado;
    
	public TablaDeJardin(String id,JuegoPage juegoPage) {
		super(id);
		this.juegoPage=juegoPage;
		this.setOutputMarkupId(true);
	}

	@SuppressWarnings({ "rawtypes", "serial" })
	@Override
	protected void populateItem(final ListItem<Surco> item) {
		final JuegoPage jm = (JuegoPage) this.getParent();
		item.add(new Label("posicion"));
		item.add(new Label("tipo"));
		
		item.add(new PropertyListView<Maceta>("macetas") {
			@Override
			protected void populateItem(final ListItem<Maceta> itemMaceta) {
				populateItemMaceta(itemMaceta, item.getModelObject());
			}
		});
	}

	protected JuegoModel getJuegoModel() {
		return TablaDeJardin.this.juegoPage.getJuegoModel();
	}

	protected void populateItemMaceta(final ListItem<Maceta> item, final Surco surco) {
		final Maceta maceta = (Maceta) item.getDefaultModelObject();
		item.setOutputMarkupId(true);
		item.add(new AjaxEventBehavior("onclick") {
		     protected void onEvent(AjaxRequestTarget target) {
		    	 JuegoModel j = getJuegoModel();
		    	 try{
		 			
		    		 j.setMacetaSeleccionada(maceta);
		 			
		 		 }catch(UserException e){
		 			target.appendJavaScript("alert('"+e.getMessage()+"')");
		 		 }
		    	 j.setSurcoSeleccionado(surco);
		    	 
		         TablaDeJardin.this.juegoPage.habilitarSelectores();
		         TablaDeJardin.this.juegoPage.refrescarTodosLosComponentes(target);
		         target.add(item);
		         
		         //Debe desabilitar el ultimo seleccionado,para queno queden todos seleccionados
		         if(ultimoSeleccionado != null)
		         target.add(ultimoSeleccionado);
		     }
		 });
		
		item.add(new Label("nombrePlanta"));
		item.add(this.createSeleccionadaAttributeModifier(item));
	}

	protected AttributeModifier createSeleccionadaAttributeModifier(final ListItem<Maceta> item) {
		return new AttributeModifier("class", new AbstractReadOnlyModel() {
			@Override
			public Object getObject() {
				if(getJuegoModel().esMacetaSeleccionada(item.getModelObject())){
					ultimoSeleccionado=item;
					return "seleccionada";
				}else{
					return "noSeleccionada";
				}
				
			}
		});
	}

}

