package com.solle13.SistemaWeb.Models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solle13.SistemaWeb.Models.Fabricas;

@Repository
@Transactional
public class FabricasDao {
	@Autowired
	  private SessionFactory _sessionFactory;
	  
	  private Session getSession() {
	    return _sessionFactory.getCurrentSession();
	  }

	  public void save(Fabricas fabricas) {
	    getSession().save(fabricas);
	    return;
	  }
	  
	  public void delete(Fabricas fabricas) {
	    getSession().delete(fabricas);
	    return;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Fabricas> getAll() {
	    return getSession().createQuery("from Fabricas").list();
	  }
	 
	  public Fabricas getById(int id) {
	    return (Fabricas) getSession().load(Fabricas.class, id);
	  }

	  public void update(Fabricas fabricas) {
	    getSession().update(fabricas);
	    return;
	  }
	  
	  public Fabricas getByLider(String lider) {
		    return (Fabricas) getSession().createQuery(
		        "from Fabricas where Lider = :lider")
		        .setParameter("lider", lider)
		        .uniqueResult();
		  }
	  
	  
}
