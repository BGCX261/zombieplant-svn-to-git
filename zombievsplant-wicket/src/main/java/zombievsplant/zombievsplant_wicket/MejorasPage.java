package zombievsplant.zombievsplant_wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

import aplicationModel.MejorasModel;
import elementos.Jugador;
import elementos.mejoras.Mejora;
import elementos.plantas.Plantin;

public class MejorasPage extends BackPage {
	private static final long serialVersionUID = 1L;

	public MejorasPage(MejorasModel mejorasModel,WebPage paginaAnterior){
		super(paginaAnterior);
		setDefaultModel(new CompoundPropertyModel<MejorasModel>(mejorasModel));
		this.agregarTitulos();
		Form form = new Form("mejorasForm");
		this.add(form);
		this.agregarMejorasCompradas(form);
		this.agregarMejorasDisponibles(form);
		this.agregarBackButton("paginaAnterior",form);
	}
	
	
	protected void agregarTitulos(){
		this.add(new Label("plantinSeleccionado.nombre"));
		this.add(new Label("jugador.puntos"));
	}
	
	
	protected void agregarMejorasCompradas(Form form){
		form.add(new PropertyListView<Mejora>("plantinSeleccionado.mejorasCompradas") {
				@Override
				protected void populateItem(final ListItem<Mejora> item) {
					item.add(new Label("descripcion"));
				}
		});
		
	}
	
	protected void agregarMejorasDisponibles(Form form){
		form.add(new PropertyListView<Mejora>("mejorasDisponibles") {
				@Override
				protected void populateItem(final ListItem<Mejora> item) {
					item.add(new Label("descripcion"));
					item.add(new Label("costo"));
					item.add(new Link("comprar") {
						@Override
						public void onClick() {
							 MejorasPage.this.getMejorasModel().setMejoraSeleccionada(item.getModelObject());
							 MejorasPage.this.getMejorasModel().comprarMejora();
						}
					});
				}
		});
		
	}
	
	
	protected MejorasModel getMejorasModel(){
		return (MejorasModel)this.getDefaultModelObject();
	}
	
}
