package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import exceptions.NoPudoComprarException;

import aplicationModel.BuscarPlantinesModel;
import aplicationModel.MejorasModel;

@SuppressWarnings("serial")
public class ComprarServlet extends AbstractServlet{
	
	/*
	 * Recibe por parametro get el indice de la mejora y la compra
	 * se la mejora no existe o o el indice no se puede castear
	 * se dirige a una pagina de error
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
	        BuscarPlantinesModel buscarPlantinesModel=this.getBuscarPlantinesModel(request);
	        MejorasModel mejorasModel= buscarPlantinesModel.getMejorasModel();
	        mejorasModel.setMejoraSeleccionadaDeIde(request.getParameter("mejora"));
	        mejorasModel.comprarMejora();
	        request.getRequestDispatcher("mejoras.jsp").forward(request, response);		
	        
		}catch(NoPudoComprarException e){
			request.setAttribute("pudoComprar", false);
			request.getRequestDispatcher("mejoras.jsp").forward(request, response);	
		}
		catch(UserException e){
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
      
	}
	
	
}
