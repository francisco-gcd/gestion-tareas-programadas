package es.pongo.liferay.tareasprogramadas.portlet.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import es.pongo.liferay.tareasprogramadas.portlet.dto.TareaProgramadaDTO;

/**
 *	Clase JavaBean con ambito de sesion.
 *	Contiene la infromacion para la vista de vista donde se mostrara las tareas programadas  
 */
@SessionScoped
@ManagedBean(name = TareasProgramadasView.BEAN_NAME)
public class TareasProgramadasView implements Serializable{

	private static final long serialVersionUID = 4137706902669085029L;
	public static final String BEAN_NAME = "TareasProgramadasView";
	
	// Tareas programadas del sistema
	private List<TareaProgramadaDTO> tareasProgramadas = Collections.emptyList();
	
	// Tarea seleccionada de todas de las del sistema
	private TareaProgramadaDTO tareaProgamada = new TareaProgramadaDTO();
	
	/*
	 * Getter | Setter
	 */
	public List<TareaProgramadaDTO> getTareasProgramadas() {
		return tareasProgramadas;
	}
	public void setTareasProgramadas(List<TareaProgramadaDTO> tareasProgramadas) {
		this.tareasProgramadas = tareasProgramadas;
	}
	public TareaProgramadaDTO getTareaProgamada() {
		return tareaProgamada;
	}
	public void setTareaProgamada(TareaProgramadaDTO tareaProgamada) {
		this.tareaProgamada = tareaProgamada;
	}
}
