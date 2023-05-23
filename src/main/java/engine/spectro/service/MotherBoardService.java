package engine.spectro.service;

import engine.spectro.entity.MotherBoardEntity;
import engine.spectro.entity.MotherBoardEntity;
import engine.spectro.enums.GeneralProductEnum;
import engine.spectro.exception.UserAlreadyExistException;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.repository.MotherBoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class MotherBoardService {
    @Autowired
    MotherBoardRepo motherBoardRepo;

    public void save(MotherBoardEntity motherBoardEntity) throws UserAlreadyExistException {
        if(motherBoardRepo.findByModel(motherBoardEntity.getModel())==null){
            if(motherBoardEntity.getAmount()>0){
                motherBoardEntity.setStatus(GeneralProductEnum.available);
            }
        }
        motherBoardRepo.save(motherBoardEntity);
    }

    public void update(String model, Integer amount) throws UserNotFoundException {
        MotherBoardEntity motherBoardEntity1 = motherBoardRepo.findByModel(model);
        if(motherBoardEntity1 !=null){
            motherBoardEntity1.setAmount(amount);
            motherBoardRepo.save(motherBoardEntity1);
        }else{
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }
    public void update(MotherBoardEntity motherBoardEntity) throws UserNotFoundException {
        MotherBoardEntity motherBoardEntity1 = motherBoardRepo.findByModel(motherBoardEntity.getModel());
        if (motherBoardEntity1 != null) {
            Field[] fields = MotherBoardEntity.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(motherBoardEntity);
                    if (value != null) {
                        field.set(motherBoardEntity1, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            motherBoardRepo.save(motherBoardEntity1);
        } else {
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }


    public void update(String model, Integer amount, GeneralProductEnum status) throws UserNotFoundException {
        MotherBoardEntity motherBoardEntity1 = motherBoardRepo.findByModel(model);
        if(motherBoardEntity1 !=null){
            motherBoardEntity1.setAmount(amount);
            motherBoardEntity1.setStatus(status);
            motherBoardRepo.save(motherBoardEntity1);
        }else{
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        motherBoardRepo.findById(id).get();
        MotherBoardEntity motherBoardEntity = motherBoardRepo.findById(id).get();
        motherBoardEntity.setStatus(GeneralProductEnum.deleted);
    }

    public void delete(String model) throws UserNotFoundException {
        if(motherBoardRepo.findByModel(model)!=null) {
            MotherBoardEntity motherBoardEntity = motherBoardRepo.findByModel(model);
            motherBoardEntity.setStatus(GeneralProductEnum.deleted);
            motherBoardRepo.save(motherBoardEntity);
        }else throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
    }

    public MotherBoardEntity findByModel(String model){
        return motherBoardRepo.findByModel(model);
    }
    public MotherBoardEntity findById(Long id){
        return motherBoardRepo.findById(id).get();
    }
}