package com.solle13.SistemaWeb.Models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solle13.SistemaWeb.Models.Sucursales;

@Repository
@Transactional
public class SucursalesDao {
	@Autowired
	  private SessionFactory _sessionFactory;
	  
	  private Session getSession() {
	    return _sessionFactory.getCurrentSession();
	  }

	  public void save(Sucursales sucursal) {
	    getSession().save(sucursal);
	    return;
	  }
	  
	  public void delete(Sucursales sucursal) {
	    getSession().delete(sucursal);
	    return;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Sucursales> getAll() {
	    return getSession().createQuery("from Sucursales").list();
	  }
	 
	  public Sucursales getById(int id) {
	    return (Sucursales) getSession().load(Sucursales.class, id);
	  }

	  public void update(Sucursales sucursal) {
	    getSession().update(sucursal);
	    return;
	  }
	  
	  public void deleteFabrica(int id) {
		  getSession().createQuery(
			"delete from Sucursales where IdFabrica = :id")
			.setParameter("id", id)
			.executeUpdate();
		    return; 
		  }
}
