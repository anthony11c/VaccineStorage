package hr.tvz.cosic.service;

import hr.tvz.cosic.sideeffect.SideEffect;

import java.util.List;

public interface SideEffectService {

    List<SideEffectDTO> getAllSideEffects();

    List<SideEffectDTO> findAllByVaccine_ResearchName(String researchName);

}
