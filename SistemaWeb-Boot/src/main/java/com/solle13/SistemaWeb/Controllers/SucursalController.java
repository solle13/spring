package com.solle13.SistemaWeb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solle13.SistemaWeb.Models.Sucursales;
import com.solle13.SistemaWeb.Models.SucursalesDao;

@Controller
@RequestMapping(value="/Sucursal")
public class SucursalController {
	
	@Autowired
	  private SucursalesDao _sucursalesDao;
	  
	  @RequestMapping(value="/delete")
	  @ResponseBody
	  public String delete(int id) {
	    try {
	      Sucursales sucursal = new Sucursales(id);
	      _sucursalesDao.delete(sucursal);
	      return "Se ha eliminado la sucursal";
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
	  }

	  @RequestMapping(value="/save", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Sucursales> create(@RequestBody Sucursales sucursales) {
		  try {
			  _sucursalesDao.save(sucursales);
			  System.out.println("Se ha guardado la fabrica");
			  return new ResponseEntity<Sucursales>(sucursales, HttpStatus.OK);
		  }
		  catch(Exception ex) {
			  System.out.println("Error "+ ex);
			  return new ResponseEntity<Sucursales>(sucursales, HttpStatus.BAD_REQUEST);
		  }
	  }
	  
	  @RequestMapping(value="/delete_fabrica")
	  @ResponseBody
	  public String delete_Fabrica(int id){
		  try {
		      _sucursalesDao.deleteFabrica(id);
		      return "Elminados";
		    }
		    catch(Exception ex) {
		      return ex.getMessage();
		    }
	  }

	  @RequestMapping(value="/all")
	  @ResponseBody
	  public List<Sucursales> getAll(){
		  try{
			  return _sucursalesDao.getAll();
		  }catch(Exception ex){
			  System.out.println("error "+ex);
			 return null; 
		  }
	  }
	  
	  @RequestMapping(value="/update", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Sucursales> update(@RequestBody Sucursales sucursales) {
	    try {
	      _sucursalesDao.update(sucursales);
	      System.out.println("Se ha actualizado la fabrica");
		  return new ResponseEntity<Sucursales>(sucursales, HttpStatus.OK);
	    }
	    catch(Exception ex) {
	    	System.out.println("Error "+ ex);
	    	return new ResponseEntity<Sucursales>(sucursales, HttpStatus.BAD_REQUEST);
	    }
	  }
}
