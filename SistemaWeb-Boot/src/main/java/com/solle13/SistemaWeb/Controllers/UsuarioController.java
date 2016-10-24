package com.solle13.SistemaWeb.Controllers;

import com.solle13.SistemaWeb.Models.Usuarios;
import com.solle13.SistemaWeb.Models.UsuariosDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UsuarioController {
	@Autowired
	  private UsuariosDao _usuariosDao;
	  
	  @RequestMapping(value="/delete")
	  @ResponseBody
	  public String delete(int id) {
	    try {
	      Usuarios usuarios = new Usuarios(id);
	      _usuariosDao.delete(usuarios);
	      return "Se ha eliminado el usuario";
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
	  }
	  
	  @RequestMapping(value="/get-by-user")
	  @ResponseBody
	  public String getByUsuario(String usuario) {
	    String u;
	    try {
	      Usuarios user = _usuariosDao.getByUsuario(usuario);
	      u = String.valueOf(user.getIdUsuario());
	      return u;
	    }
	    catch(Exception ex) {
	      return "0";
	    }
	  }
	  
	  @RequestMapping(value="/login", method=RequestMethod.POST)
	  public String login(Model model, @RequestParam("usuarios") String usuario, @RequestParam("pass")String pass) {
	    String res;
	    try {
	      Usuarios user = _usuariosDao.login(usuario,pass);
	      res = String.valueOf(user.getIdUsuario());
	    }
	    catch(Exception ex) {
	    	System.out.println("error "+ex);
	    	return "redirect:/";
	    }
	    if(res.equals("1")){
	    	model.addAttribute("lider", usuario);
	    	return "Admin";
	    	
	    }else{
	    	model.addAttribute("lider", usuario);
	    	return "User";
	    }
	  }

	  @RequestMapping(value="/save", method=RequestMethod.POST)
	  @ResponseBody
	  public String create(String usuarios, String pass) {
	    try {
	      Usuarios usuario = new Usuarios(usuarios, pass);
	      _usuariosDao.save(usuario);
	      return "El usuario se ha guardado";
	    }
	    catch(Exception ex) {
	    	System.out.println("error save "+ex);
	      return ex.getMessage();
	    }
	  }
	  
	  @RequestMapping(value="/save_ajax", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Usuarios> create(@RequestBody Usuarios usuario) {
		  try {
			  _usuariosDao.save(usuario);
			  System.out.println("Se ha guardado el usuario");
			  return new ResponseEntity<Usuarios>(usuario, HttpStatus.OK);
		  }
		  catch(Exception ex) {
			  System.out.println("Error "+ ex);
			  return new ResponseEntity<Usuarios>(usuario, HttpStatus.BAD_REQUEST);
		  }
	  }
	  
	  @RequestMapping(value="/update", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Usuarios> update(@RequestBody Usuarios usuario) {
	    try {
	      _usuariosDao.update(usuario);
	      System.out.println("Se ha actualizado el usuario");
		  return new ResponseEntity<Usuarios>(usuario, HttpStatus.OK);
	    }
	    catch(Exception ex) {
	    	System.out.println("Error "+ ex);
	    	return new ResponseEntity<Usuarios>(usuario, HttpStatus.BAD_REQUEST);
	    }
	  }

}
