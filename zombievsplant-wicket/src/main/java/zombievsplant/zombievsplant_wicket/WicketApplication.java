package zombievsplant.zombievsplant_wicket;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;
import org.uqbar.commons.model.UserException;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see zombievsplant.zombievsplant_wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return JuegoPage.class;
	//	return JardinZenPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		this.getRequestCycleListeners().add(new PageRequestHandlerTracker());
		this.getRequestCycleListeners().add(new AbstractRequestCycleListener() {  
	          @Override  
	          public IRequestHandler onException(RequestCycle cycle, Exception e) {
	        	  
	        	  Page lastPage = (Page) PageRequestHandlerTracker.getLastHandler(RequestCycle.get()).getPage();
 
	        	  
	        	  if(esUserException(e)) {
	        		  return new RenderPageRequestHandler(new PageProvider(new ErrorPage(getUserException(e), lastPage)));  
	        	  }
	        	  else {
	        		  return super.onException(cycle, e);
	        	  }
	          }

			protected boolean esUserException(Exception e) {
				return getUserException(e) != null;
			}

			private UserException getUserException(Throwable e) {
				if(e == null || (e.getCause() == e)) {
					return null;
				}
				else if(e instanceof UserException) {
					return (UserException)e;
				}
				else {
					return getUserException(e.getCause());
				}
			}  
	      });  
	}
}
