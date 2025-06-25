package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.dto.MozoDto;
import com.incade.poo.mozo.exception.PasswordException;
import com.incade.poo.mozo.model.Mozo;
import com.incade.poo.mozo.repository.MozoJpaController;
import com.incade.poo.mozo.repository.exceptions.NonexistentEntityException;
import java.util.List;

public class MozoController {
    MozoJpaController mozoJpaController = new MozoJpaController();
    
    public MozoDto create(String nombre, String email, String password){
        Mozo mozo = new Mozo();
        mozo.setNombre(nombre);
        mozo.setEmail(email);
        mozo.setPassword(password);
        
        mozoJpaController.create(mozo);
        
        return toDto(mozo);
    }
    
    public MozoDto edit(String nombre, String email) throws NonexistentEntityException{
        Mozo mozo = mozoJpaController.findMozoByEmail(email);
    
        if (mozo == null) {
            throw new NonexistentEntityException("No mozo found with name: " + nombre);
        }
    
        mozo.setNombre(nombre);
        mozo.setEmail(email);
    
        try {
            mozoJpaController.edit(mozo);
        } catch (Exception ex) {
            System.getLogger(MozoController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return toDto(mozo);
    }
    
    public MozoDto getByEmail(String email) {
        return toDto(mozoJpaController.findMozoByEmail(email));
    }
    
    public List<MozoDto> getAll(){
        return mozoJpaController.findMozoEntities()
                .stream()
                .map(this::toDto)
                .toList();
    }
    
    public void updatePassword(String email, String currentPassword, String newPassword){
        Mozo mozo = mozoJpaController.findMozoByEmail(email);
        login(email, currentPassword);
        mozo.setPassword(newPassword);
        try {
            mozoJpaController.edit(mozo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public MozoDto login(String email, String password)  {
        Mozo mozo = mozoJpaController.findMozoByEmail(email);
        
        try {
            if(!password.contentEquals(mozo.getPassword())){
                throw new PasswordException("Incorrect password");
            }
        } catch (PasswordException ex) {
            ex.printStackTrace();
        }
        
        return toDto(mozo);
    }
    
    public MozoDto toDto(Mozo mozo){
        return new MozoDto(mozo.getNombre(), mozo.getEmail());
    }
}
