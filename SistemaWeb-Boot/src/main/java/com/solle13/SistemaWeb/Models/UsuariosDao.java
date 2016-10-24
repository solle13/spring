package com.solle13.SistemaWeb.Models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solle13.SistemaWeb.Models.Usuarios;

@Repository
@Transactional
public class UsuariosDao {
	@Autowired
	  private SessionFactory _sessionFactory;
	  
	  private Session getSession() {
	    return _sessionFactory.getCurrentSession();
	  }

	  public void save(Usuarios usuarios) {
	    getSession().save(usuarios);
	    return;
	  }
	  
	  public void delete(Usuarios usuarios) {
	    getSession().delete(usuarios);
	    return;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Usuarios> getAll() {
	    return getSession().createQuery("from Usuarios").list();
	  }
	  
	  public Usuarios getByUsuario(String usuario) {
		    return (Usuarios) getSession().createQuery(
		        "from Usuarios where Usuario = :usuario")
		        .setParameter("usuario", usuario)
		        .uniqueResult();
		  }
	  
	  public Usuarios login(String usuario, String pass) {
		    return (Usuarios) getSession().createQuery(
		        "from Usuarios where Usuario = :usuario and Pass = :pass")
		        .setParameter("usuario", usuario)
		        .setParameter("pass",pass)
		        .uniqueResult();
		  }
	  
	  public Usuarios getById(int id) {
	    return (Usuarios) getSession().load(Usuarios.class, id);
	  }

	  public void update(Usuarios usuarios) {
	    getSession().update(usuarios);
	    return;
	  }

}
