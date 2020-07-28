package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.modelo.Cliente;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped

public class ClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteFacade ejbClienteFacade;
	private static HttpSession httpSession;

	private List<Cliente> listarClientes;
	private String cedula;
	private String nombre;
	private Date fecha;
	private int edad;
	private String direccion;

	private FaceletContext faceletContext;
	private String formId;

	public ClienteBean() {

	}

	@PostConstruct
	public void init() {

		this.listarClientes = ejbClienteFacade.findAll();
		System.out.println(listarClientes);

	}

	public ClienteFacade getEjbClienteFacade() {
		return ejbClienteFacade;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}


	public void setEjbClienteFacade(ClienteFacade ejbClienteFacade) {
		this.ejbClienteFacade = ejbClienteFacade;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Cliente> getListarClientes() {
		return listarClientes;
	}

	public void setListarClientes(List<Cliente> listarClientes) {
		this.listarClientes = listarClientes;
	}

	public String edit(Cliente c) {
		c.setEditable(true);
		return null;
	}

	public String save(Cliente c) {
		ejbClienteFacade.edit(c);
		c.setEditable(false);
		listarClientes = ejbClienteFacade.findAll();
		return null;
	}

	public String eliminar(Cliente c) {
		ejbClienteFacade.remove(c);
		listarClientes = ejbClienteFacade.findAll();
		return null;

	}

	public void cambiarCodigo() {
		faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes()
				.get(FaceletContext.FACELET_CONTEXT_KEY);
		formId = (String) faceletContext.getAttribute("formId");
	}

	public Cliente buscarCliente() {
		Cliente cliente = null;
		String cedula = formId;
		cliente = ejbClienteFacade.find(cedula);
		return cliente;
	}

	public String add() {
		Cliente cliente = new Cliente(this.cedula, this.nombre, this.fecha, this.edad, this.direccion);
		ejbClienteFacade.create(cliente);
		System.out.println("cliente creado con exito!");
		this.listarClientes = ejbClienteFacade.findAll();
		this.cedula="";
		this.nombre="";
		this.fecha=null;
		this.edad=0;
		this.direccion="";

		return null;
	}
	public String redirigirFactura(Cliente c) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("cliente", c);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("cliente", c);
		return "gestionTelefono";
	}

}
