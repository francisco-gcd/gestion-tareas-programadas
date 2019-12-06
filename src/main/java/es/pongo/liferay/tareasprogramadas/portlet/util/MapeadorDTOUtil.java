package es.pongo.liferay.tareasprogramadas.portlet.util;

import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerState;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

import es.pongo.liferay.tareasprogramadas.portlet.dto.TareaProgramadaDTO;

/**
 *	Clase JavaBean con ambito de aplicacion.
 *	Contiene los metodo para transformar los objetos a DTO.  
 */
@ApplicationScoped
@ManagedBean(name = MapeadorDTOUtil.BEAN_NAME)
public class MapeadorDTOUtil implements Serializable{
	private static final long serialVersionUID = -5961258683698636179L;

	private static transient Logger LOG = Logger.getLogger(MapeadorDTOUtil.class);
	public static final String BEAN_NAME = "MapeadorDTOUtil";

	public TareaProgramadaDTO toTareaProgramadaDTO(SchedulerResponse schedulerResponse){
		TareaProgramadaDTO tareaProgramadaDTO = new TareaProgramadaDTO();

		try{
			tareaProgramadaDTO.setNombre(schedulerResponse.getJobName());
			tareaProgramadaDTO.setGrupo(schedulerResponse.getGroupName());
			tareaProgramadaDTO.setDescripcion(schedulerResponse.getDescription());
			tareaProgramadaDTO.setTipoAlmacenamiento(schedulerResponse.getStorageType().name());
		
			tareaProgramadaDTO.setNombreClase((String) schedulerResponse.getMessage().get(SchedulerEngine.MESSAGE_LISTENER_CLASS_NAME));
			tareaProgramadaDTO.setPortletId((String) schedulerResponse.getMessage().get(SchedulerEngine.PORTLET_ID));
			
			Trigger trigger = schedulerResponse.getTrigger();
			tareaProgramadaDTO.setTipoTrigger(trigger.getTriggerType().name());
			tareaProgramadaDTO.setExpresion(String.valueOf(trigger.getTriggerContent()));
			
			TriggerState state = SchedulerEngineHelperUtil.getJobState(schedulerResponse);
			tareaProgramadaDTO.setEstado(state.name());
			
			Date nextFire = SchedulerEngineHelperUtil.getNextFireTime(tareaProgramadaDTO.getNombre(), tareaProgramadaDTO.getGrupo(), StorageType.valueOf(tareaProgramadaDTO.getTipoAlmacenamiento()));
			Date previousFire = SchedulerEngineHelperUtil.getPreviousFireTime(tareaProgramadaDTO.getNombre(), tareaProgramadaDTO.getGrupo(), StorageType.valueOf(tareaProgramadaDTO.getTipoAlmacenamiento()));
	
			tareaProgramadaDTO.setProximoLanzamiento(nextFire);
			tareaProgramadaDTO.setUltimoLanzamiento(previousFire);
			
			tareaProgramadaDTO.setEsNueva(Boolean.FALSE);
		}catch (SchedulerException oops) {
			LOG.error("Ocurrio un error al obtener las tarea programada", oops);
		}
		
		return tareaProgramadaDTO;
	}
}