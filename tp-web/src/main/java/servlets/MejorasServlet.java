package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import aplicationModel.BuscarPlantinesModel;

@SuppressWarnings("serial")
public class MejorasServlet extends AbstractServlet {
	
	/*
	 * Recibe por parametro el nombre de la planta y la setea en mejorasModel
	 * que se encuentra en buscarPlantinModel.
	 * Si la planta no existe o el applicationModel no se encuentra en session
	 * se redirige a una pagina de error
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombrePlanta = request.getParameter("nombrePlanta");
		    
		
			try{
			this.setPlantaEnMejorasModel(nombrePlanta,request);
			
			request.getRequestDispatcher("mejoras.jsp").forward(request, response);
			
			}catch(UserException e){
				request.setAttribute("error",e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	
	
	}
	
	
	private void setPlantaEnMejorasModel(String nombrePlanta,HttpServletRequest request) throws UserException {
		BuscarPlantinesModel buscarPlantinModel=this.getBuscarPlantinesModel(request);
		buscarPlantinModel.getMejorasModel().buscarYSeleccionarPlantin(nombrePlanta); 
	}


	
	
}
