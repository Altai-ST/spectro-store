package engine.spectro.service;

import engine.spectro.entity.LaptopEntity;
import engine.spectro.entity.MemoryEntity;
import engine.spectro.enums.GeneralProductEnum;
import engine.spectro.exception.UserAlreadyExistException;
import engine.spectro.exception.UserNotFoundException;
import engine.spectro.repository.MemoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class MemoryService {
    @Autowired
    MemoryRepo memoryRepo;

    public void save(MemoryEntity motherBoard) throws UserAlreadyExistException {
        if(memoryRepo.findByModel(motherBoard.getModel())==null){
            if(motherBoard.getAmount()>0){
                motherBoard.setStatus(GeneralProductEnum.available);
            }
        }
        memoryRepo.save(motherBoard);
    }

    public void update(String model, Integer amount) throws UserNotFoundException {
        MemoryEntity motherBoard1 = memoryRepo.findByModel(model);
        if(motherBoard1!=null){
            motherBoard1.setAmount(amount);
            memoryRepo.save(motherBoard1);
        }else{
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }
    public void update(MemoryEntity motherBoard) throws UserNotFoundException {
        MemoryEntity motherBoard1 = memoryRepo.findByModel(motherBoard.getModel());
        if (motherBoard1 != null) {
            Field[] fields = MemoryEntity.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(motherBoard);
                    if (value != null) {
                        field.set(motherBoard1, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            memoryRepo.save(motherBoard1);
        } else {
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }


    public void update(String model, Integer amount, GeneralProductEnum status) throws UserNotFoundException {
        MemoryEntity motherBoard1 = memoryRepo.findByModel(model);
        if(motherBoard1!=null){
            motherBoard1.setAmount(amount);
            motherBoard1.setStatus(status);
            memoryRepo.save(motherBoard1);
        }else{
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        memoryRepo.findById(id).get();
        MemoryEntity motherBoard = memoryRepo.findById(id).get();
        motherBoard.setStatus(GeneralProductEnum.deleted);
    }

    public void delete(String model) throws UserNotFoundException {
        if(memoryRepo.findByModel(model)!=null) {
            MemoryEntity motherBoard = memoryRepo.findByModel(model);
            motherBoard.setStatus(GeneralProductEnum.deleted);
            memoryRepo.save(motherBoard);
        }else throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
    }


}