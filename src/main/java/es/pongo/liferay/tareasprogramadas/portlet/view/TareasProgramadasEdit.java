package es.pongo.liferay.tareasprogramadas.portlet.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import es.pongo.liferay.tareasprogramadas.portlet.dto.TareaProgramadaDTO;

/**
 *	Clase JavaBean con ambito de sesion.
 *	Contiene la infromacion para la vista de edicion de una tarea programada  
 */
@SessionScoped
@ManagedBean(name = TareasProgramadasEdit.BEAN_NAME)
public class TareasProgramadasEdit implements Serializable{

	private static final long serialVersionUID = 6490327664592830284L;
	public static final String BEAN_NAME = "TareasProgramadasEdit";

	// Tarea seleccionada para la edicion
	private TareaProgramadaDTO tareaProgamada = new TareaProgramadaDTO();
	
	/*
	 * Getter | Setter
	 */

	public TareaProgramadaDTO getTareaProgamada() {
		return tareaProgamada;
	}
	public void setTareaProgamada(TareaProgramadaDTO tareaProgamada) {
		this.tareaProgamada = tareaProgamada;
	}
}
