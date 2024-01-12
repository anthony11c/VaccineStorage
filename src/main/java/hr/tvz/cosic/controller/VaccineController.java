package hr.tvz.cosic.controller;

import hr.tvz.cosic.service.VaccineDTO;
import hr.tvz.cosic.service.VaccineService;
import hr.tvz.cosic.vaccine.VaccineCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccine")
@CrossOrigin(origins = "http://localhost:4200")
public class VaccineController {

    @Autowired
    VaccineService vaccineService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<VaccineDTO> getAllVaccines(){
        return vaccineService.findAll();
    }

    @GetMapping("/{researchName}")
    public ResponseEntity<VaccineDTO> getResearchName(@PathVariable final String researchName){

        Optional<VaccineDTO> vaccineOpt = vaccineService.findByResearchName(researchName);
//        if(vaccineOpt.isPresent()) {
//            return new ResponseEntity(vaccineOpt.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }

        return vaccineService.findByResearchName(researchName)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{researchName}")
    public void delete(@PathVariable String researchName){
        vaccineService.deleteByResearchName(researchName);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/updateQuantityInStorage/{researchVaccineName}")
    public ResponseEntity<VaccineDTO> updateQuantityInStorage(@PathVariable String researchVaccineName, @Valid @RequestBody final VaccineCommand updateVaccineCommand){
        return vaccineService.updateQuantityInStorage(researchVaccineName, updateVaccineCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured({"ROLE_ADMIN", "ROLE_CREATOR"})
    @PostMapping
    public ResponseEntity<VaccineDTO> save(@Valid @RequestBody final VaccineCommand command){
        return vaccineService.save(command)
                .map(
                        vaccineDTO -> ResponseEntity.status(HttpStatus.CREATED).body(vaccineDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }
}
