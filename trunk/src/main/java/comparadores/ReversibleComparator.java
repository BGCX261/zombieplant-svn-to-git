package comparadores;

import java.io.Serializable;
import java.util.Comparator;

public abstract class ReversibleComparator<T> implements Comparator<T> , Serializable{
		private boolean invertido = false;

		@Override
		public int compare(T o1, T o2) {
			return invertido ? comparar(o2, o1) : comparar(o1, o2);
		}
		
		/*
		 * Retorna this con la direccion invertida si this es igual a rc
		 * en caso contraro retorna rc
		 */
		public ReversibleComparator<T> getSiguienteComparator(ReversibleComparator<T> rc){
			if (this.getNombre().equals(rc.getNombre())){
				this.invertido=!this.invertido;
				return this;
			}
			return rc;   
		}
		
		public  String getDireccion(){
			return this.invertido ? "arriba" : "abajo" ;
		}
		
		public String visible(String nombreComparator) {
			return this.getNombre().equals(nombreComparator) ? "visible" : "hidden";
		}
		
		protected abstract int comparar(T o1, T o2);
		
		public abstract String getNombre();

		
	}
