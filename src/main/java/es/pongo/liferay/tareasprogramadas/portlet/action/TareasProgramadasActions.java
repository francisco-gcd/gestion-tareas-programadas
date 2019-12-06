package es.pongo.liferay.tareasprogramadas.portlet.action;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.TriggerType;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;
import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.apache.log4j.Logger;

import es.pongo.liferay.tareasprogramadas.portlet.dto.TareaProgramadaDTO;
import es.pongo.liferay.tareasprogramadas.portlet.util.MapeadorDTOUtil;
import es.pongo.liferay.tareasprogramadas.portlet.view.TareasProgramadasEdit;
import es.pongo.liferay.tareasprogramadas.portlet.view.TareasProgramadasView;

/**
 *	Clase JavaBean con ambito de vista.
 *	Tiene las acciones para las vistas relacionadas con el portlet de Tareas Programadas  
 */
@ViewScoped
@ManagedBean(name = TareasProgramadasActions.BEAN_NAME)
public class TareasProgramadasActions implements Serializable{
	private static transient Logger LOG = Logger.getLogger(TareasProgramadasActions.class);

	private static final long serialVersionUID = -6428548657082352732L;
	public static final String BEAN_NAME = "TareasProgramadasActions";

	@ManagedProperty(value = "#{" + TareasProgramadasView.BEAN_NAME + "}")
	private TareasProgramadasView tareasProgramadasView;

	@ManagedProperty(value = "#{" + TareasProgramadasEdit.BEAN_NAME + "}")
	private TareasProgramadasEdit tareasProgramadasEdit;

	@ManagedProperty(value = "#{" + MapeadorDTOUtil.BEAN_NAME + "}")
	private MapeadorDTOUtil mapeadorDTOUtil;

	/*
	 * Getters || Setters
	 */
	public TareasProgramadasView getTareasProgramadasView() {
		return tareasProgramadasView;
	}
	public void setTareasProgramadasView(TareasProgramadasView tareasProgramadasView) {
		this.tareasProgramadasView = tareasProgramadasView;
	}
	public TareasProgramadasEdit getTareasProgramadasEdit() {
		return tareasProgramadasEdit;
	}
	public void setTareasProgramadasEdit(TareasProgramadasEdit tareasProgramadasEdit) {
		this.tareasProgramadasEdit = tareasProgramadasEdit;
	}
	public MapeadorDTOUtil getMapeadorDTOUtil() {
		return mapeadorDTOUtil;
	}
	public void setMapeadorDTOUtil(MapeadorDTOUtil mapeadorDTOUtil) {
		this.mapeadorDTOUtil = mapeadorDTOUtil;
	}

	/**
	 * Carga la lista de tareas programadas de la vista
	 * @param event
	 */
	public void cargar(ActionEvent event) {
		LOG.debug("Cargado las tareas programadas del sistema");
		
		try{
			tareasProgramadasView.setTareasProgramadas(new LinkedList<TareaProgramadaDTO>());
			for(SchedulerResponse schedulerResponse : SchedulerEngineHelperUtil.getScheduledJobs()){
				if( schedulerResponse.getGroupName().startsWith("com.liferay.portlet") ||
					schedulerResponse.getGroupName().startsWith("com.liferay.portal")
				){
					continue;
				}
				
				TareaProgramadaDTO tareaProgramadaDTO = mapeadorDTOUtil.toTareaProgramadaDTO(schedulerResponse);
				tareasProgramadasView.getTareasProgramadas().add(tareaProgramadaDTO);
			}
		}catch (SchedulerException oops) {
			LOG.error("Ocurrio un error al obtener las tareas programadas", oops);
		}
	}

	/**
	 * Pausa una tarea programada
	 */
	public void pausar(){
		try{
			final String nombre = tareasProgramadasView.getTareaProgamada().getNombre();
			final String grupo = tareasProgramadasView.getTareaProgamada().getGrupo();
			final String tipoAlmacenamiento = tareasProgramadasView.getTareaProgamada().getTipoAlmacenamiento();
			
			SchedulerEngineHelperUtil.pause(nombre, grupo, StorageType.valueOf(tipoAlmacenamiento));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención!", "Actualice la vista para asegurar que se pauso la Tarea Programada"));
		}catch (SchedulerException oops) {
			LOG.error("Ocurrio un error al pausar las tarea programada", oops);
		}
	}

	/**
	 * Reanuda una tarea programada
	 */
	public void reanudar(){
		try{
			final String nombre = tareasProgramadasView.getTareaProgamada().getNombre();
			final String grupo = tareasProgramadasView.getTareaProgamada().getGrupo();
			final String tipoAlmacenamiento = tareasProgramadasView.getTareaProgamada().getTipoAlmacenamiento();
			
			SchedulerEngineHelperUtil.resume(nombre, grupo, StorageType.valueOf(tipoAlmacenamiento));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención!", "Actualice la vista para asegurar que se reanudo la Tarea Programada"));
		}catch (SchedulerException oops) {
			LOG.error("Ocurrio un error al reanudar las tarea programada", oops);
		}
	}
	
	/**
	 * Borra una tarea programada
	 */
	public void borrar(){
		try{
			final String nombre = tareasProgramadasView.getTareaProgamada().getNombre();
			final String grupo = tareasProgramadasView.getTareaProgamada().getGrupo();
			final String tipoAlmacenamiento = tareasProgramadasView.getTareaProgamada().getTipoAlmacenamiento();
			
			SchedulerEngineHelperUtil.delete(nombre, grupo, StorageType.valueOf(tipoAlmacenamiento));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención!", "Actualice la vista para asegurar que se a borrado la Tarea Programada"));
		}catch (SchedulerException oops) {
			LOG.error("Ocurrio un error al borrar las tarea programada", oops);
		}
	}

	/**
	 * Inicia una nueva Tarea Programada
	 */
	public String nueva(){
		tareasProgramadasEdit.setTareaProgamada(new TareaProgramadaDTO());
		tareasProgramadasEdit.getTareaProgamada().setEsNueva(Boolean.TRUE);
		
		return "NAVIGATION_TO_TAREA_PROGRAMADA";
	}

	/**
	 * Guardar una nueva Tarea Programada
	 */
	public String guardar(){
		final Boolean esNueva = tareasProgramadasEdit.getTareaProgamada().getEsNueva();
		final String nombre = tareasProgramadasEdit.getTareaProgamada().getNombre();
		final String grupo = tareasProgramadasEdit.getTareaProgamada().getGrupo();
		final String descripcion = tareasProgramadasEdit.getTareaProgamada().getDescripcion();
		final StorageType tipoAlmacenamiento = StorageType.valueOf(tareasProgramadasEdit.getTareaProgamada().getTipoAlmacenamiento());

		final String nombreClase = tareasProgramadasEdit.getTareaProgamada().getNombreClase();
		final String portletId = tareasProgramadasEdit.getTareaProgamada().getPortletId();

		final TriggerType tipoTrigger = TriggerType.valueOf(tareasProgramadasEdit.getTareaProgamada().getTipoTrigger());
		final Object expresion = tareasProgramadasEdit.getTareaProgamada().getExpresion();
		
		String proximaPagina = StringPool.BLANK;
		
		// Si los valores son validos se grabara la tarea programada
		if(isValid(nombre, grupo, descripcion, tipoAlmacenamiento, nombreClase, portletId, tipoTrigger, expresion)){
			try{
				Trigger trigger = null;
				if(tipoTrigger == TriggerType.CRON){
					trigger = TriggerFactoryUtil.buildTrigger(tipoTrigger, nombre, grupo, null, null, String.valueOf(expresion.toString()));
				}else if(tipoTrigger == TriggerType.SIMPLE){
					trigger = TriggerFactoryUtil.buildTrigger(tipoTrigger, nombre, grupo, null, null, Long.valueOf(expresion.toString()));
				}

				if(esNueva){
					Message message = new Message();
					message.put(SchedulerEngine.MESSAGE_LISTENER_CLASS_NAME, nombreClase);
					message.put(SchedulerEngine.PORTLET_ID, portletId);
					
					SchedulerEngineHelperUtil.schedule(trigger, tipoAlmacenamiento, descripcion, DestinationNames.SCHEDULER_DISPATCH, message, 0);
				}else{
					SchedulerEngineHelperUtil.update(trigger, tipoAlmacenamiento);
				}

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención!", "Actualice la vista para asegurar que se agrego la Tarea Programada"));
				proximaPagina = "NAVIGATION_TO_LISTA_TAREAS_PROGRAMADAS";
			}catch (SchedulerException oops) {
				LOG.error("No se pudo crear la tarea programada", oops);
			}
		}

		return proximaPagina;
	}
	
	/*
	 * Permite validar los datos del formulario de una tarea programada
	 */
	private boolean isValid(String nombre, String grupo, String descripcion, StorageType tipoAlmacenamiento, String nombreClase, String portletId, TriggerType tipoTrigger, Object expresion){
		ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(portletId);
		if(Validator.isNull(classLoader)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontro el portlet Id");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}
		
		try{
			classLoader.loadClass(nombreClase);
		}catch (NullPointerException | ClassNotFoundException oops) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontro el nombre de clase");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}

		try{
			if(tipoTrigger == TriggerType.CRON){
				expresion = String.valueOf(expresion.toString());
			}else if(tipoTrigger == TriggerType.SIMPLE){
				expresion = Long.valueOf(expresion.toString());
			}
		}catch (NumberFormatException oops) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo pasar a unidades de tiempo la expresion");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}
		
		return true;
	}
}
