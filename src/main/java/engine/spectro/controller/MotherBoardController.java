//package engine.spectro.controller;
//
//import engine.spectro.entity.MotherBoardEntity;
//import engine.spectro.enums.GeneralProductEnum;
//import engine.spectro.exception.UserNotFoundException;
//import engine.spectro.model.UniversalPage;
//import engine.spectro.service.MotherBoardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController("/motherboard")
//public class MotherBoardController {
//    @Autowired
//    private MotherBoardService motherBoardService;
//
//    @GetMapping(value = "find-by-id")
//    public MotherBoardEntity getMotherBoardById(Long id){
//        return motherBoardService.findById(id);
//    }
//
//    @GetMapping(value = "find-by-model")
//    public MotherBoardEntity getMotherBoardByModel(String model){
//        return motherBoardService.findByModel(model);
//    }
//
//
//    @PostMapping(value = "/saveNewMotherBoard")
//    public ResponseEntity saveNewMotherBoard(@RequestBody MotherBoardEntity motherBoard){
//        try {
//            motherBoard.setStatus(GeneralProductEnum.available);
//            motherBoardService.save(motherBoard);
//            return ResponseEntity.ok("Сохранил");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Ошибка сохранения нового ноутбука");
//        }
//    }
//
//    @PatchMapping(value = "/updateMotherBoard")
//    public String updateMotherBoard(@RequestParam("model") String model, @RequestParam("amount") Integer amount) throws Exception {
//        try {
//            motherBoardService.update(model, amount);
//            return "succsess";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (Exception e){
//            throw new Exception(e);
//        }
//    }
//
//    @PatchMapping(value = "/updateMotherBoardByEntity")
//    public String updateMotherBoard(@RequestBody MotherBoardEntity motherBoard) throws Exception {
//        try {
//            motherBoardService.update(motherBoard);
//            return "succsess";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (Exception e){
//            throw new Exception(e);
//        }
//    }
//
//    @DeleteMapping(value = "/deleteMotherBoard")
//    public String deleteMotherBoard(@RequestParam("model") String model){
//        try {
//            motherBoardService.delete(model);
//            return "Success";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping(value = "/filter")
//    public ResponseEntity<List<MotherBoardEntity>> getMotherBoards(UniversalPage universalPage, MotherBoardSearchCriteria motherBoardSearchCriteria){
//        Page<MotherBoardEntity> p = motherBoardService.filter(universalPage,motherBoardSearchCriteria);
//        List<MotherBoardEntity> pa = p.getContent();
//        return new ResponseEntity<>(pa, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/searchModel")
//    public ResponseEntity<MotherBoardEntity> getOneMotherBoard(String model){
//        return new ResponseEntity<>(motherBoardService.findByModel(model), HttpStatus.OK);
//    }
//
//    @PatchMapping(value = "/sell")
//    public String sell(@RequestParam("model") String model, @RequestParam("amount") int amount) throws Exception {
//        try {
//            MotherBoardEntity l = motherBoardService.findByModel(model);
//            int newAmount = l.getAmount() - amount;
//            if (newAmount==0){
//                motherBoardService.update(model,newAmount,GeneralProductEnum.sold_out);
//            } else if (newAmount>0) {
//                motherBoardService.update(model,newAmount);
//            }
//            return "succsess";
//        } catch (Exception e){
//            throw new Exception(e);
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
