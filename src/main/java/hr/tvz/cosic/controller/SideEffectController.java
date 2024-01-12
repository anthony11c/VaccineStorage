package hr.tvz.cosic.controller;

import hr.tvz.cosic.service.SideEffectDTO;
import hr.tvz.cosic.service.SideEffectServiceImpl;
import hr.tvz.cosic.sideeffect.SideEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/side-effect")
@CrossOrigin(origins = "http://localhost:4200")
public class SideEffectController {

    @Autowired
    SideEffectServiceImpl sideEffectService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<SideEffectDTO> getAllSideEffects(){
        return sideEffectService.getAllSideEffects();
    }

    @GetMapping(params = "vaccineResearchName")
    public List<SideEffectDTO> getSideEffectsByVaccineResearchName(@RequestParam String vaccineResearchName){
        return sideEffectService.findAllByVaccine_ResearchName(vaccineResearchName);
    }
}
