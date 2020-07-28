package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;


import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.edu.ups.modelo.Cliente;
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

	public List<Telefono> findByUsuario(Cliente cliente) {

		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Telefono> criteriaQuery = criteriaBuilder.createQuery(Telefono.class);
		// Se establece la clausula FROM
		Root<Telefono> root = criteriaQuery.from(Telefono.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cliente"), cliente)); // criteriaQuery.multiselect(root.get(atr))
		// // Se configuran los predicados,
		// combinados por AND
		System.out.println(em.createQuery(criteriaQuery).getResultList());
		
		return em.createQuery(criteriaQuery).getResultList();
		
	}

}
