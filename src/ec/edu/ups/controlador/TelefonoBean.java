package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.TelefonoFacade;
import ec.edu.ups.modelo.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped

public class TelefonoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private TelefonoFacade ejbTelefonoFacade;
	@EJB
	private ClienteFacade ejbClienteFacade;
	private Cliente cliente;
	private List<Telefono> telefonos;
	
	private FaceletContext faceletContext;
	private String formId;
	private String cedula;

	public TelefonoBean() {

	}

	@PostConstruct
	public void init() {
		System.out.println("Entrar");
	}
	
	public void cambiarCodigo() {
		faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes()
				.get(FaceletContext.FACELET_CONTEXT_KEY);
		formId = (String) faceletContext.getAttribute("formId");
		System.out.println(formId + "Mija");
		this.cliente = ejbClienteFacade.find(formId);
		System.out.println("Soy el cliente chch al fin mmv" + this.cliente);
		this.telefonos = this.cliente.getTelefonos();
	}

	public TelefonoFacade getEjbTelefonoFacade() {
		return ejbTelefonoFacade;
	}

	public void setEjbTelefonoFacade(TelefonoFacade ejbTelefonoFacade) {
		this.ejbTelefonoFacade = ejbTelefonoFacade;
	}

	public ClienteFacade getEjbClienteFacade() {
		return ejbClienteFacade;
	}

	public void setEjbClienteFacade(ClienteFacade ejbClienteFacade) {
		this.ejbClienteFacade = ejbClienteFacade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	public FaceletContext getFaceletContext() {
		return faceletContext;
	}

	public void setFaceletContext(FaceletContext faceletContext) {
		this.faceletContext = faceletContext;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	public List<Telefono> findTelefonos(){
		Cliente cli = ejbClienteFacade.find(this.formId);
		List<Telefono> telefonos = null;
		this.cedula = this.formId;
		telefonos = cli.getTelefonos();
		return telefonos;
	}
	
	public String edit(Telefono t) {
		t.setEditable(true);
		return null;
	}
	
	public String save(Telefono t) {
		ejbTelefonoFacade.edit(t);
		t.setEditable(false);
		
		this.cliente = ejbClienteFacade.find(formId);
		System.out.println("Soy el cliente chch al fin mmv" + cliente);
		this.telefonos = cliente.getTelefonos();
		return null;
	}
	
	public String eliminar(Telefono t) {
		ejbTelefonoFacade.remove(t);
		this.cliente = ejbClienteFacade.find(formId);
		System.out.println("Soy el cliente chch al fin mmv" + cliente);
		this.telefonos = cliente.getTelefonos();
		return null;
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


}
