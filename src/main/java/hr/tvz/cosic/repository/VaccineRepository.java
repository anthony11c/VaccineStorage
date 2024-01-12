package hr.tvz.cosic.repository;

import hr.tvz.cosic.service.VaccineDTO;
import hr.tvz.cosic.vaccine.Vaccine;
import hr.tvz.cosic.vaccine.VaccineCommand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface VaccineRepository {

    Set<Vaccine> findaAll();

    Optional<Vaccine> findVaccineByResearchName(String researchName);

    List<Vaccine> findVaccineBoolean(Boolean aBoolean);

    Optional<Vaccine> save(Vaccine vaccine);

    void deleteByResearchName(String researchName);

    Optional<Vaccine> findByResearchName(String researchName);

    Optional<Vaccine> update(String researchName, Vaccine updatedVaccineQuantityInStorage);
}
