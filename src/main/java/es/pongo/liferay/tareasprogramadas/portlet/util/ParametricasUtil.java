package es.pongo.liferay.tareasprogramadas.portlet.util;

import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TriggerType;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *	Clase JavaBean con ambito de aplicacion.
 *	Contiene los metodo para obtener las listas de valores de parametricas del sistema   
 */
@ApplicationScoped
@ManagedBean(name = ParametricasUtil.BEAN_NAME)
public class ParametricasUtil implements Serializable {

	private static final long serialVersionUID = 4101037794355140518L;

	public static final String BEAN_NAME = "ParametricasUtil";

	// Tipos de Alamacenamientos para una Tarea Programada
	private List<String> tiposAlmacenamientos = Collections.emptyList();
	
	// Tipos de Trigger posibles
	private List<String> tiposTrigger = Collections.emptyList();

	/**
	 * Metodo para la carga de listas de valores que se ejectura despues de la constructor
	 */
	@PostConstruct
	public void init() {
		tiposAlmacenamientos = new LinkedList<String>();
		for(int i = 0; i < StorageType.values().length; i++){
			tiposAlmacenamientos.add(StorageType.values()[i].name());
		}
		
		tiposTrigger = new LinkedList<String>();
		for(int i = 0; i < TriggerType.values().length; i++){
			tiposTrigger.add(TriggerType.values()[i].name());
		}
	}

	/*
	 * Getter | Setter
	 */

	public List<String> getTiposAlmacenamientos() {
		return tiposAlmacenamientos;
	}
	public void setTiposAlmacenamientos(List<String> tiposAlmacenamientos) {
		this.tiposAlmacenamientos = tiposAlmacenamientos;
	}
	public List<String> getTiposTrigger() {
		return tiposTrigger;
	}
	public void setTiposTrigger(List<String> tiposTrigger) {
		this.tiposTrigger = tiposTrigger;
	}
}