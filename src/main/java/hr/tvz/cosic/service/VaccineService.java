package hr.tvz.cosic.service;

import hr.tvz.cosic.vaccine.VaccineCommand;

import java.util.List;
import java.util.Optional;

public interface VaccineService {

    Optional<VaccineDTO> updateQuantityInStorage(String researchVaccineName, VaccineCommand updateVaccineCommand);

    List<VaccineDTO> findAll();

    VaccineDTO findVaccineByResearchName(String researchName);

    List<VaccineDTO> findVaccineBoolean(Boolean aBoolean);

    Optional<VaccineDTO> save(VaccineCommand command);

    Optional<VaccineDTO> findByResearchName(String researchName);

    void deleteByResearchName(String researchName);
}
