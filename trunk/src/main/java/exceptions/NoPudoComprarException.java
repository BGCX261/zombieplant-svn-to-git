package exceptions;

import java.io.Serializable;

import org.uqbar.commons.model.UserException;

@SuppressWarnings("serial")
public class NoPudoComprarException extends UserException implements Serializable{

	public NoPudoComprarException(String message) {
		super(message);
	}


}
