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

import com.solle13.SistemaWeb.Models.Ventas;
import com.solle13.SistemaWeb.Models.VentasDao;

@Controller
@RequestMapping(value="/ventas")
public class VentasController {
	
	@Autowired
	  private VentasDao _ventasDao;
	  
	  @RequestMapping(value="/delete")
	  @ResponseBody
	  public String delete(int id) {
	    try {
	      Ventas venta = new Ventas(id);
	      _ventasDao.delete(venta);
	      return "Se ha eliminado la venta";
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
	  }	  
	 
	  @RequestMapping(value="/save", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Ventas> create(@RequestBody Ventas venta) {
	    try {
	      _ventasDao.save(venta);
	      return new ResponseEntity<Ventas>(venta, HttpStatus.OK);
	    }
	    catch(Exception ex) {
	    	return new ResponseEntity<Ventas>(venta, HttpStatus.BAD_REQUEST);
	    }
	  }
	  
	  @RequestMapping(value="/all")
	  @ResponseBody
	  public List<Ventas> getAll(){
		  try{
			  return _ventasDao.getAll();
		  }catch(Exception ex){
			  System.out.println("error "+ex);
			 return null; 
		  }
	  }
	  
	  @RequestMapping(value="/all_date")
	  @ResponseBody
	  public List<Ventas> getbyDate(String fecha1, String fecha2){
		  try{
			  return _ventasDao.getbyDate(fecha1,fecha2);
		  }catch(Exception ex){
			  System.out.println("error "+ex);
			 return null; 
		  }
	  }
	  
	  @RequestMapping(value="/delete_fabrica")
	  @ResponseBody
	  public String delete_Fabrica(int id){
		  try {
		      _ventasDao.deleteFabrica(id);
		      return "Elminados";
		    }
		    catch(Exception ex) {
		      return ex.getMessage();
		    }
	  }
	  
	  @RequestMapping(value="/delete_sucursal")
	  @ResponseBody
	  public String delete_Sucursal(int id){
		  try {
		      _ventasDao.deleteSucursal(id);
		      return "Elminados";
		    }
		    catch(Exception ex) {
		      return ex.getMessage();
		    }
	  }
	  
	  @RequestMapping(value="/update", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Ventas> update(@RequestBody Ventas ventas) {
	    try {
	      _ventasDao.update(ventas);
	      System.out.println("Se ha actualizado la fabrica");
		   return new ResponseEntity<Ventas>(ventas, HttpStatus.OK);
	    }
	    catch(Exception ex) {
	    	System.out.println("Error "+ ex);
	    	return new ResponseEntity<Ventas>(ventas, HttpStatus.BAD_REQUEST);
	    }
	  }

}
