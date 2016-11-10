package com.solle13.SistemaWeb.Models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solle13.SistemaWeb.Models.Ventas;

@Repository
@Transactional
public class VentasDao {

	@Autowired
	  private SessionFactory _sessionFactory;
	  
	  private Session getSession() {
	    return _sessionFactory.getCurrentSession();
	  }

	  public void save(Ventas venta) {
	    getSession().save(venta);
	    return;
	  }
	  
	  public void delete(Ventas venta) {
	    getSession().delete(venta);
	    return;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Ventas> getAll() {
	    return getSession().createQuery("from Ventas").list();
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Ventas> getbyId(int id) {
	    return getSession().createQuery("from Ventas where IdFabrica = :id")
	    		.setParameter("id", id)
	    		.list();
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Ventas> getbyDate(String fecha1, String fecha2) {
	    return getSession().createQuery("from Ventas where Fecha >= :fecha1 and Fecha <= :fecha2")
	    		.setParameter("fecha1", fecha1)
	    		.setParameter("fecha2", fecha2)
	    		.list();
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Ventas> getbyDateAndId(String fecha1, String fecha2, int id) {
	    return getSession().createQuery("from Ventas where Fecha >= :fecha1 and Fecha <= :fecha2 and IdFabrica = :id")
	    		.setParameter("fecha1", fecha1)
	    		.setParameter("fecha2", fecha2)
	    		.setParameter("id", id)
	    		.list();
	  }
	  
	  public Ventas getById(int id) {
	    return (Ventas) getSession().load(Ventas.class, id);
	  }

	  public void update(Ventas venta) {
	    getSession().update(venta);
	    return;
	  }
	  
	  public void deleteFabrica(int id) {
		  getSession().createQuery(
			"delete from Ventas where IdFabrica = :id")
			.setParameter("id", id)
			.executeUpdate();
		    return; 
		  }
	  
	  public void deleteSucursal(int id) {
		  getSession().createQuery(
			"delete from Ventas where IdPunto = :id")
			.setParameter("id", id)
			.executeUpdate();
		    return; 
		  }
}
