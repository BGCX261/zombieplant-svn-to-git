package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import aplicationModel.BuscarPlantinesModel;
import elementos.Jugador;
import elementos.jardines.JardinZen;

@SuppressWarnings("serial")
public class JardinZenServlet extends AbstractServlet{
	
	/*
	 *Guarda en sesion el buscarPlantinModel si esto no existe 
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			this.getBuscarPlantinesModel(req);
		}catch(UserException e){
			BuscarPlantinesModel buscarPlantin = new BuscarPlantinesModel(crearJugador());
			req.getSession().setAttribute("buscarPlantinesModel", buscarPlantin);
			
		}
	
		req.getRequestDispatcher("jardinZen.jsp").forward(req, resp);
	}


	
	protected Jugador crearJugador() {
		Jugador jugador = new Jugador();
		jugador.setJardinZen(new JardinZen());
		jugador.setPuntos(500);
		jugador.setNombre("Jardinero");
		return jugador;
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
}
