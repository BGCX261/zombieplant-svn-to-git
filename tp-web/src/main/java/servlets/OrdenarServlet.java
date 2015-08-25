package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import aplicationModel.BuscarPlantinesModel;

@SuppressWarnings("serial")
public class OrdenarServlet extends AbstractServlet{
	
	    /*
	     * Recibe el criterio de ordenamiento por get,y realiza la busqueda
	     * 
	     */
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse resp)
				throws ServletException, IOException {
	
		try {

			BuscarPlantinesModel buscarPlantin = this.getBuscarPlantinesModel(request);
			buscarPlantin.setComparator(request.getParameter("criterioComparador"));
			buscarPlantin.buscar();
			request.getRequestDispatcher("jardinZen.jsp").forward(request, resp);
		} catch (UserException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, resp);
		}
		
		}
			

}
