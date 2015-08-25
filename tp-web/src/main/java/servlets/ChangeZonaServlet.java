package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import aplicationModel.BuscarPlantinesModel;
@SuppressWarnings("serial")
public class ChangeZonaServlet extends AbstractServlet {
	
	/*
	 *Cambia de zona ,delega esta responsabilidad al buscarPlantinesModel 
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
	try{	
	BuscarPlantinesModel buscarplantin = this.getBuscarPlantinesModel(req);
	buscarplantin.cambiarZona();
	req.getRequestDispatcher("jardinZen.jsp").forward(req, resp);
	}catch(UserException e){
		req.setAttribute("error", e.getMessage());
		req.getRequestDispatcher("error.jsp").forward(req, resp);
	}
	
	}
}
