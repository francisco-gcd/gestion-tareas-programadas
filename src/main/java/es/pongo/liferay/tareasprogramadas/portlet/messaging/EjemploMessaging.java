package es.pongo.liferay.tareasprogramadas.portlet.messaging;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import org.apache.log4j.Logger;

/**
 *	Clase de ejemplo para programar una tarea programada
 */
public class EjemploMessaging implements MessageListener{
	
	private static transient Logger LOG = Logger.getLogger(EjemploMessaging.class);

	@Override
	public void receive(Message message) throws MessageListenerException {
		LOG.info("Recibida peticion con el mensaje : " + message);
	}
}
