package engine.spectro.service;

import engine.spectro.entity.PhoneEntity;
import engine.spectro.entity.ROMEntity;import engine.spectro.enums.GeneralProductEnum;
import engine.spectro.exception.*;
import engine.spectro.repository.ROMRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ROMService {
    @Autowired
    ROMRepo romRepo;

    public void save(ROMEntity rom) throws UserAlreadyExistException {
        if(romRepo.findByModel(rom.getModel())==null){
            if(rom.getAmount()>0){
                rom.setStatus(GeneralProductEnum.available);
            }
        }
        romRepo.save(rom);
    }

    public void update(String model, Integer amount) throws UserNotFoundException {
        ROMEntity rom1 = romRepo.findByModel(model);
        if(rom1!=null){
            rom1.setAmount(amount);
            romRepo.save(rom1);
        }else{
            throw new UserNotFoundException("Can not update rom list. It doesn't exist");
        }
    }
    public void update(ROMEntity rom) throws UserNotFoundException {
        ROMEntity rom1 = romRepo.findByModel(rom.getModel());
        if (rom1 != null) {
            Field[] fields = ROMEntity.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(rom);
                    if (value != null) {
                        field.set(rom1, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            romRepo.save(rom1);
        } else {
            throw new UserNotFoundException("Can not update rom list. It doesn't exist");
        }
    }


    public void update(String model, Integer amount, GeneralProductEnum status) throws UserNotFoundException {
        ROMEntity rom1 = romRepo.findByModel(model);
        if(rom1!=null){
            rom1.setAmount(amount);
            rom1.setStatus(status);
            romRepo.save(rom1);
        }else{
            throw new UserNotFoundException("Can not update rom list. It doesn't exist");
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        romRepo.findById(id).get();
        ROMEntity rom = romRepo.findById(id).get();
        rom.setStatus(GeneralProductEnum.deleted);
    }

    public void delete(String model) throws UserNotFoundException {
        if(romRepo.findByModel(model)!=null) {
            ROMEntity rom = romRepo.findByModel(model);
            rom.setStatus(GeneralProductEnum.deleted);
            romRepo.save(rom);
        }else throw new UserNotFoundException("Can not update rom list. It doesn't exist");
    }

    public List<ROMEntity> filterRom(Map<String, Object> filters) {
        return romRepo.findByFilters(filters);
    }

    public ROMEntity findByModel(String model){
        return romRepo.findByModel(model);
    }
    public ROMEntity findById(Long id){
        return romRepo.findById(id).get();
    }
}