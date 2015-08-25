package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.uqbar.commons.model.UserException;

import aplicationModel.BuscarPlantinesModel;

@SuppressWarnings("serial")
public class AbstractServlet extends HttpServlet {
	
    public BuscarPlantinesModel getBuscarPlantinesModel(HttpServletRequest request) throws UserException{
    	Object model=request.getSession().getAttribute("buscarPlantinesModel");
    	if(model==null){
    		throw new UserException("ApplicationModel no encotrada en la session");
    	}
    	return (BuscarPlantinesModel)model;
	}
	
	
	
	
}