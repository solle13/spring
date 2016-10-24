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

import com.solle13.SistemaWeb.Models.Fabricas;
import com.solle13.SistemaWeb.Models.FabricasDao;
@Controller
@RequestMapping(value="/fabrica")
public class FabricaController {
	@Autowired
	  private FabricasDao _fabricasDao;
	  
	  @RequestMapping(value="/delete")
	  @ResponseBody
	  public String delete(int id) {
	    try {
	      Fabricas fabrica = new Fabricas(id);
	      _fabricasDao.delete(fabrica);
	      return "Se ha eliminado la fabrica";
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
	  }

	  @RequestMapping(value="/save", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Fabricas> create(@RequestBody Fabricas fabrica) {
		try {
	    	_fabricasDao.save(fabrica);
		    return new ResponseEntity<Fabricas>(fabrica, HttpStatus.OK);
	    }
	    catch(Exception ex) {
	    	System.out.println("Error "+ ex);
	    	return new ResponseEntity<Fabricas>(fabrica, HttpStatus.BAD_REQUEST);
	     	}
	  }
	  
	  @RequestMapping(value="/update", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<Fabricas> update(@RequestBody Fabricas fabrica) {
	    try {
	    	_fabricasDao.update(fabrica);
		    return new ResponseEntity<Fabricas>(fabrica, HttpStatus.OK);
	    }
	    catch(Exception ex) {
	    	System.out.println("Error "+ ex);
	    	return new ResponseEntity<Fabricas>(fabrica, HttpStatus.BAD_REQUEST);
	    }
	  }
	  
	  @RequestMapping(value="/all")
	  @ResponseBody
	  public List<Fabricas> getAll(){
		  try{
			  return _fabricasDao.getAll();
		  }catch(Exception ex){
			  System.out.println("error "+ex);
			 return null; 
		  }
	  }
	  
	  @RequestMapping(value="/get-by-lider")
	  @ResponseBody
	  public Fabricas getByLider(String lider) {
	    try {
	      Fabricas fabrica = _fabricasDao.getByLider(lider);
	      return fabrica;
	    }
	    catch(Exception ex) {
	      return null;
	    }
	    
	  }
}
