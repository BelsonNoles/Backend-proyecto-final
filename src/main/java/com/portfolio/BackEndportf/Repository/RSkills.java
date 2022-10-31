
package com.portfolio.BackEndportf.Repository;

import com.portfolio.BackEndportf.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RSkills extends JpaRepository<Skills, Integer>{
    Optional<Skills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
