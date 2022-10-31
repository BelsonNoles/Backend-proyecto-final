
package com.portfolio.BackEndportf.Security.Service;

import com.portfolio.BackEndportf.Security.Entity.Rol;
import com.portfolio.BackEndportf.Security.Enums.RolNombre;
import com.portfolio.BackEndportf.Security.Repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository iRolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        iRolRepository.save(rol);
    }
    
}
