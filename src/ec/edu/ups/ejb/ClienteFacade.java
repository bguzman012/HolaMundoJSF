package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.modelo.Cliente;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente>{
	
	@PersistenceContext(unitName = "Repaso")
	private EntityManager em;

	public ClienteFacade() {
		super(Cliente.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
