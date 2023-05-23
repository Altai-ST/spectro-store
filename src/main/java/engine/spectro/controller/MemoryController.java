//package engine.spectro.controller;
//
//import engine.spectro.entity.MemoryEntity;
//import engine.spectro.enums.GeneralProductEnum;
//import engine.spectro.exception.UserNotFoundException;
//import engine.spectro.model.UniversalPage;
//import engine.spectro.model.MemorySearchCriteria;
//import engine.spectro.service.MemoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/memory")
//public class MemoryController {
//    @Autowired
//    private MemoryService memoryService;
//
//    @GetMapping(value = "find-by-id")
//    public MemoryEntity getMemoryById(Long id){
//        return memoryService.findById(id);
//    }
//
//    @GetMapping(value = "find-by-model")
//    public MemoryEntity getMemoryByModel(String model){
//        return memoryService.findByModel(model);
//    }
//
//
//    @PostMapping(value = "/saveNewMemory")
//    public ResponseEntity saveNewMemory(@RequestBody MemoryEntity memory){
//        try {
//            memory.setStatus(GeneralProductEnum.available);
//            memoryService.save(memory);
//            return ResponseEntity.ok("Сохранил");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Ошибка сохранения нового ноутбука");
//        }
//    }
//
//    @PatchMapping(value = "/updateMemory")
//    public String updateMemory(@RequestParam("model") String model, @RequestParam("amount") Integer amount) throws Exception {
//        try {
//            memoryService.update(model, amount);
//            return "succsess";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (Exception e){
//            throw new Exception(e);
//        }
//    }
//
//    @PatchMapping(value = "/updateMemoryByEntity")
//    public String updateMemory(@RequestBody MemoryEntity memory) throws Exception {
//        try {
//            memoryService.update(memory);
//            return "succsess";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (Exception e){
//            throw new Exception(e);
//        }
//    }
//
//    @DeleteMapping(value = "/deleteMemory")
//    public String deleteMemory(@RequestParam("model") String model){
//        try {
//            memoryService.delete(model);
//            return "Success";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping(value = "/filter")
//    public ResponseEntity<List<MemoryEntity>> getMemorys(UniversalPage universalPage, MemorySearchCriteria memorySearchCriteria){
//        Page<MemoryEntity> p = memoryService.filter(universalPage,memorySearchCriteria);
//        List<MemoryEntity> pa = p.getContent();
//        return new ResponseEntity<>(pa, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/searchModel")
//    public ResponseEntity<MemoryEntity> getOneMemory(String model){
//        return new ResponseEntity<>(memoryService.findByModel(model), HttpStatus.OK);
//    }
//
//    @PatchMapping(value = "/sell")
//    public String sell(@RequestParam("model") String model, @RequestParam("amount") int amount) throws Exception {
//        try {
//            MemoryEntity l = memoryService.findByModel(model);
//            int newAmount = l.getAmount() - amount;
//            if (newAmount==0){
//                memoryService.update(model,newAmount,GeneralProductEnum.sold_out);
//            } else if (newAmount>0) {
//                memoryService.update(model,newAmount);
//            }
//            return "succsess";
//        } catch (Exception e){
//            throw new Exception(e);
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
