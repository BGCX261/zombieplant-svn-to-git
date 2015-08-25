package zombievsplant.zombievsplant_wicket;


import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

public class ErrorPage<Exception> extends BackPage {

	public ErrorPage(Exception e, Page lastPage) {
		super(new CompoundPropertyModel<Exception>(e), lastPage);
		this.add(new Label("message"));
		Form form = new Form("form");
		this.add(form);
		this.agregarBackButton("backButton", form);
	}

}
