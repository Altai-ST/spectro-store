//package engine.spectro.controller;
//
//import engine.spectro.entity.ROMEntity;
//import engine.spectro.enums.GeneralProductEnum;
//import engine.spectro.exception.UserNotFoundException;
//import engine.spectro.model.UniversalPage;
//import engine.spectro.service.ROMService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rom")
//public class ROMController {
//    @Autowired
//    private ROMService romService;
//
//    @GetMapping(value = "find-by-id")
//    public ROMEntity getROMById(Long id){
//        return romService.findById(id);
//    }
//
//    @GetMapping(value = "find-by-model")
//    public ROMEntity getROMByModel(String model){
//        return romService.findByModel(model);
//    }
//
//
//    @PostMapping(value = "/saveNewROM")
//    public ResponseEntity saveNewROM(@RequestBody ROMEntity rom){
//        try {
//            rom.setStatus(GeneralProductEnum.available);
//            romService.save(rom);
//            return ResponseEntity.ok("Сохранил");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Ошибка сохранения нового ноутбука");
//        }
//    }
//
//    @PatchMapping(value = "/updateROM")
//    public String updateROM(@RequestParam("model") String model, @RequestParam("amount") Integer amount) throws Exception {
//        try {
//            romService.update(model, amount);
//            return "succsess";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (Exception e){
//            throw new Exception(e);
//        }
//    }
//
//    @PatchMapping(value = "/updateROMByEntity")
//    public String updateROM(@RequestBody ROMEntity rom) throws Exception {
//        try {
//            romService.update(rom);
//            return "succsess";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }catch (Exception e){
//            throw new Exception(e);
//        }
//    }
//
//    @DeleteMapping(value = "/deleteROM")
//    public String deleteROM(@RequestParam("model") String model){
//        try {
//            romService.delete(model);
//            return "Success";
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping(value = "/filter")
//    public ResponseEntity<List<ROMEntity>> getROMs(UniversalPage universalPage, ROMSearchCriteria romSearchCriteria){
//        Page<ROMEntity> p = romService.filter(universalPage,romSearchCriteria);
//        List<ROMEntity> pa = p.getContent();
//        return new ResponseEntity<>(pa, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/searchModel")
//    public ResponseEntity<ROMEntity> getOneROM(String model){
//        return new ResponseEntity<>(romService.findByModel(model), HttpStatus.OK);
//    }
//
//    @PatchMapping(value = "/sell")
//    public String sell(@RequestParam("model") String model, @RequestParam("amount") int amount) throws Exception {
//        try {
//            ROMEntity l = romService.findByModel(model);
//            int newAmount = l.getAmount() - amount;
//            if (newAmount==0){
//                romService.update(model,newAmount,GeneralProductEnum.sold_out);
//            } else if (newAmount>0) {
//                romService.update(model,newAmount);
//            }
//            return "succsess";
//        } catch (Exception e){
//            throw new Exception(e);
//        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
