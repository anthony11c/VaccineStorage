package hr.tvz.cosic.service;

import hr.tvz.cosic.repository.SideEffectRepository;
import hr.tvz.cosic.sideeffect.SideEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideEffectServiceImpl implements SideEffectService {

    @Autowired
    SideEffectRepository sideEffectRepository;

    public List<SideEffectDTO> getAllSideEffects() {
        return sideEffectRepository.findAll().stream().map(this::mapSideEffectToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SideEffectDTO> findAllByVaccine_ResearchName(String researchName) {
        return sideEffectRepository.findAllByVaccine_ResearchName(researchName).stream().map(this::mapSideEffectToDTO).collect(Collectors.toList());
    }


    private SideEffectDTO mapSideEffectToDTO(final SideEffect sideEffect){
        return new SideEffectDTO(sideEffect.getKratkiOpis(), sideEffect.getDugiOpis(), sideEffect.getUcestalost());
    }
}
