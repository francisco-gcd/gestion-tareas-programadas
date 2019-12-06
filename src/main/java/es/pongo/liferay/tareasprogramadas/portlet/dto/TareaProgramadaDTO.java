package es.pongo.liferay.tareasprogramadas.portlet.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase con la informacion que se mostrara a un usuario por la interfaz 
 */
public class TareaProgramadaDTO implements Serializable{

	private static final long serialVersionUID = 6698692704060301275L;

	private Boolean esNueva;
	private String nombre, grupo, descripcion, tipoAlmacenamiento;
	private String tipoTrigger, estado;
	private String portletId, nombreClase;
	private Date proximoLanzamiento, ultimoLanzamiento;
	private Object expresion;
	
	/*
	 * Getter | Setter
	 */

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoAlmacenamiento() {
		return tipoAlmacenamiento;
	}
	public void setTipoAlmacenamiento(String tipoAlmacenamiento) {
		this.tipoAlmacenamiento = tipoAlmacenamiento;
	}
	public String getTipoTrigger() {
		return tipoTrigger;
	}
	public void setTipoTrigger(String tipoTrigger) {
		this.tipoTrigger = tipoTrigger;
	}
	public Object getExpresion() {
		return expresion;
	}
	public void setExpresion(Object expresion) {
		this.expresion = expresion;
	}
	public Date getProximoLanzamiento() {
		return proximoLanzamiento;
	}
	public void setProximoLanzamiento(Date proximoLanzamiento) {
		this.proximoLanzamiento = proximoLanzamiento;
	}
	public Date getUltimoLanzamiento() {
		return ultimoLanzamiento;
	}
	public void setUltimoLanzamiento(Date ultimoLanzamiento) {
		this.ultimoLanzamiento = ultimoLanzamiento;
	}
	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public String getPortletId() {
		return portletId;
	}
	public void setPortletId(String portletId) {
		this.portletId = portletId;
	}
	public Boolean getEsNueva() {
		return esNueva;
	}
	public void setEsNueva(Boolean esNueva) {
		this.esNueva = esNueva;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TareaProgramadaDTO other = (TareaProgramadaDTO) obj;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		}
		else if (!grupo.equals(other.grupo))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		}
		else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}