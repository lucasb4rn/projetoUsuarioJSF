package br.com.lucas.usuarios.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DAO<T> {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(t);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public void remove(T t) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		
		em.close();
	}

	public void atualiza(T t) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		
		em.close();
	}
	

	public List<T> listaTodos() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		
		return lista;
		
	}

	public T buscaPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public List<T> buscaPorNome(String nome) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		Root<T> root = query.from(classe); 
		
		  List<Predicate> predicates = new ArrayList<Predicate>();
		
		  Path<String> nomePath = root.<String> get("nome");  
		  
		 if (!nome.isEmpty()) {
		        Predicate nomeIgual = criteriaBuilder.like(nomePath, "%" + nome + "%");
		        predicates.add(nomeIgual);
		}
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0])); 
		 
		
		TypedQuery<T> typedQuery = em.createQuery(query);
		
		List<T> lista = em.createQuery(query).getResultList();
		
		em.close();
		
		return typedQuery.getResultList();

	}
	
	
	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from usuarios n")
				.getSingleResult();
		em.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}

}
