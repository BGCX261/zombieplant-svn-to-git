package zombievsplant.zombievsplant_wicket;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import aplicationModel.MejorasModel;
import elementos.plantas.Plantin;

public class BackPage extends WebPage {

	private Page paginaAnterior;

	public BackPage(Page paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public BackPage(IModel<?> model, Page paginaAnterior) {
		super(model);
		this.paginaAnterior = paginaAnterior;
	}

	public BackPage(PageParameters parameters, Page paginaAnterior) {
		super(parameters);
		this.paginaAnterior = paginaAnterior;
	}

	protected void agregarBackButton(String wicketId, Form form) {
		form.add(new Link(wicketId) {
			@Override
			public void onClick() {
				this.setResponsePage(BackPage.this.paginaAnterior);
			}
		});
	}

}
