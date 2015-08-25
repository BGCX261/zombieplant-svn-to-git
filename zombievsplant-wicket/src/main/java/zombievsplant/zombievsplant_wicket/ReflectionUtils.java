/**
 * 
 */
package zombievsplant.zombievsplant_wicket;

import java.lang.reflect.Method;

import com.uqbar.commons.exceptions.ProgramException;

/**
 * @author usuario
 *
 */
public class ReflectionUtils {

	public static Method getMetodoDe(Class clase, String nombre){
		try {
			return clase.getMethod(nombre);
		} catch (NoSuchMethodException e) {
			throw new ProgramException("No existe el metodo con nombre " + nombre + " en la clase " + clase);
		} catch (SecurityException e) {
			throw new ProgramException("Error al obtener el metodo con nombre " + nombre + " en la clase " + clase);
		}
	}

}
