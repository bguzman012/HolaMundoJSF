package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


import javax.persistence.PersistenceContext;

import ec.edu.ups.modelo.Telefono;

@Stateless
public class TelefonoFacade extends AbstractFacade<Telefono>{
	
	@PersistenceContext(unitName = "Repaso")
	private EntityManager em;

	public TelefonoFacade() {
		super(Telefono.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
