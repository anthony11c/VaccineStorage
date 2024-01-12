package hr.tvz.cosic.service;

import hr.tvz.cosic.repository.JdbcVaccineRepository;
import hr.tvz.cosic.vaccine.Vaccine;
import hr.tvz.cosic.vaccine.VaccineCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    JdbcVaccineRepository jdbcVaccineRepository;

    @Override
    public Optional<VaccineDTO> updateQuantityInStorage(String researchName, VaccineCommand updateVaccineCommand) {
        return jdbcVaccineRepository.update(researchName, mapCommandToVaccine(updateVaccineCommand)).map(this::mapVaccineToDTO);
    }

    @Override
    public List<VaccineDTO> findAll() {
        return jdbcVaccineRepository.findaAll().stream().map(this::mapVaccineToDTO).collect(Collectors.toList());
    }

    @Override
    public VaccineDTO findVaccineByResearchName(final String researchName) {
        return jdbcVaccineRepository.findVaccineByResearchName(researchName).map(this::mapVaccineToDTO).orElse(null);
    }

    @Override
    public List<VaccineDTO> findVaccineBoolean(Boolean aBoolean) {
        return jdbcVaccineRepository.findVaccineBoolean(aBoolean).stream().map(this::mapVaccineToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<VaccineDTO> save(final VaccineCommand command) {
        return jdbcVaccineRepository.save(mapCommandToVaccine(command)).map(this::mapVaccineToDTO);
    }

    @Override
    public Optional<VaccineDTO> findByResearchName(String researchName) {
        return jdbcVaccineRepository.findByResearchName(researchName).map(this::mapVaccineToDTO);
    }

    @Override
    public void deleteByResearchName(String researchName) {
        jdbcVaccineRepository.deleteByResearchName(researchName);
    }

    private VaccineDTO mapVaccineToDTO(final Vaccine vaccine){
        return new VaccineDTO(vaccine.getResearchName(), vaccine.getManufacturerName(), vaccine.getVaccineType(), vaccine.getNumberOfShots(), vaccine.getQuantityInStorage());
    }

    private Vaccine mapCommandToVaccine(final VaccineCommand vaccineCommand) {
        return new Vaccine(vaccineCommand.getResearchName(), vaccineCommand.getManufacturerName(), vaccineCommand.getNumberOfShots(), vaccineCommand.getQuantityInStorage(), vaccineCommand.getVaccineType());
    }

}
