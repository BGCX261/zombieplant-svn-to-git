package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import aplicationModel.BuscarPlantinesModel;

@SuppressWarnings("serial")
public class BuscarPlantinServlet extends AbstractServlet{
	
	/*
	 *Busca la planta segun el criterio recibido por parametro
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{	
			BuscarPlantinesModel buscarPlantin =this.getBuscarPlantinesModel(req);
			buscarPlantin.setCriterioBusqueda(req.getParameter("criterio"));
			buscarPlantin.buscar();
			req.getRequestDispatcher("jardinZen.jsp").forward(req, resp);
		}catch(UserException e){
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
		

}
