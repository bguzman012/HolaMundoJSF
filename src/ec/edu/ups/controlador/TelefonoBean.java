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
	private List<Telefono> telefonosCliente;
	private static HttpSession httpSession;
	private FaceletContext faceletContext;
	private String formId;
	private String cedula;
	private String numero;
	private String tipo;
	


	public TelefonoBean() {

	}

	@PostConstruct
	public void init() {
		
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		this.cliente = null;
		this.cliente = (Cliente) session.getAttribute("cliente");
		System.out.println("Men" + this.cliente);
		
		this.telefonos = ejbTelefonoFacade.findByUsuario(this.cliente);
		
	}
	
	public void cambiarCodigo() {
		
		


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
	
	public List<Telefono> telefonoCliente(){
		List<Telefono> telefonos = null;
		telefonos = this.cliente.getTelefonos();
		System.out.println(telefonos);
		return telefonos;
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
		t.setCliente(cliente);
		ejbTelefonoFacade.edit(t);
		t.setEditable(false);

		this.telefonos = ejbTelefonoFacade.findByUsuario(this.cliente);
		return null;
	}
	
	public String eliminar(Telefono t) {
		t.setCliente(cliente);
		ejbTelefonoFacade.remove(t);

		this.telefonos = ejbTelefonoFacade.findByUsuario(this.cliente);
		return null;
		
	}
	
	public String add() {
		Telefono t = new Telefono(this.numero, this.tipo);
		t.setCliente(this.cliente);
		ejbTelefonoFacade.create(t);
		this.telefonos = ejbTelefonoFacade.findByUsuario(this.cliente);
		return null;
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
