package engine.spectro.service;

import engine.spectro.entity.MotherBoard;
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

    public void save(MotherBoard motherBoard) throws UserAlreadyExistException {
        if(motherBoardRepo.findByModel(motherBoard.getModel())==null){
            if(motherBoard.getAmount()>0){
                motherBoard.setStatus(GeneralProductEnum.available);
            }
        }
        motherBoardRepo.save(motherBoard);
    }

    public void update(String model, Integer amount) throws UserNotFoundException {
        MotherBoard motherBoard1 = motherBoardRepo.findByModel(model);
        if(motherBoard1!=null){
            motherBoard1.setAmount(amount);
            motherBoardRepo.save(motherBoard1);
        }else{
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }
    public void update(MotherBoard motherBoard) throws UserNotFoundException {
        MotherBoard motherBoard1 = motherBoardRepo.findByModel(motherBoard.getModel());
        if (motherBoard1 != null) {
            Field[] fields = MotherBoard.class.getDeclaredFields();
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
            motherBoardRepo.save(motherBoard1);
        } else {
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }


    public void update(String model, Integer amount, GeneralProductEnum status) throws UserNotFoundException {
        MotherBoard motherBoard1 = motherBoardRepo.findByModel(model);
        if(motherBoard1!=null){
            motherBoard1.setAmount(amount);
            motherBoard1.setStatus(status);
            motherBoardRepo.save(motherBoard1);
        }else{
            throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
        }
    }

    public void delete(Long id) throws UserNotFoundException {
        motherBoardRepo.findById(id).get();
        MotherBoard motherBoard = motherBoardRepo.findById(id).get();
        motherBoard.setStatus(GeneralProductEnum.deleted);
    }

    public void delete(String model) throws UserNotFoundException {
        if(motherBoardRepo.findByModel(model)!=null) {
            MotherBoard motherBoard = motherBoardRepo.findByModel(model);
            motherBoard.setStatus(GeneralProductEnum.deleted);
            motherBoardRepo.save(motherBoard);
        }else throw new UserNotFoundException("Can not update motherBoard list. It doesn't exist");
    }
}