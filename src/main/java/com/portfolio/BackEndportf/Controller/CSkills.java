
package com.portfolio.BackEndportf.Controller;

import com.portfolio.BackEndportf.Dto.dtoSkills;
import com.portfolio.BackEndportf.Entity.Skills;
import com.portfolio.BackEndportf.Security.Controller.Mensaje;
import com.portfolio.BackEndportf.Service.SSkills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skills")
public class CSkills {
    @Autowired
    SSkills sSkills;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id){
        if(!sSkills.existsById(id))
            return new ResponseEntity(new Mensaje("La habilidad no existe"), HttpStatus.NOT_FOUND);
        Skills skills= sSkills.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sSkills.existsById(id)){
            return new ResponseEntity(new Mensaje("La habilidad no existe"), HttpStatus.NOT_FOUND);
        }
        sSkills.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtosk){
        if(StringUtils.isBlank(dtosk.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombre(dtosk.getNombre()))
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        
        Skills skills =new Skills(dtosk.getNombre(), dtosk.getPorcentaje());
        sSkills.save(skills);
        
        return new ResponseEntity(new Mensaje("Habilidad agregada exitosamente"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtosk){
        if(!sSkills.existsById(id))
            return new ResponseEntity(new Mensaje("El id de la habilidad no existe"), HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombre(dtosk.getNombre()) && sSkills.getByNombre(dtosk.getNombre()).get().getId() !=id)
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtosk.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Skills skills = sSkills.getOne(id).get();
        skills.setNombre(dtosk.getNombre());
        skills.setPorcentaje(dtosk.getPorcentaje());
        
        sSkills.save(skills);
        return new ResponseEntity(new Mensaje("Habilidad actualizada correctamente"), HttpStatus.OK);
    }
}
