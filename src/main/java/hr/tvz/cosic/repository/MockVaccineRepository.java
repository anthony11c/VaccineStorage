package hr.tvz.cosic.repository;

import hr.tvz.cosic.vaccine.Vaccine;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MockVaccineRepository implements VaccineRepository {

    private Set<Vaccine> MOCKED_VACCINES = new HashSet<>(Arrays.asList(
            new Vaccine("mRNA-1273", "ModernaTX", 2, 15, Vaccine.VaccineType.RNA),
            new Vaccine("BNT162b2", "Pfizer & BioNTech", 2, 50, Vaccine.VaccineType.VIRALNI_VEKTOR),
            new Vaccine("JNJ-78436735", "Johnson & Johnson", 1, 100, Vaccine.VaccineType.RNA)
    ));

    @Override
    public Set<Vaccine> findaAll() {
        return MOCKED_VACCINES;
    }

    @Override
    public Optional<Vaccine> findVaccineByResearchName(String researchName) {
        return MOCKED_VACCINES.stream().filter(vac -> Objects.equals(vac.getResearchName(), researchName)).findAny();
    }

    @Override
    public List<Vaccine> findVaccineBoolean(Boolean aBoolean) {
        if(aBoolean){
            return MOCKED_VACCINES.stream().filter(vac -> Objects.equals(vac.getNumberOfShots(), 1)).collect(Collectors.toList());
        }
        if (!aBoolean) {
            return MOCKED_VACCINES.stream().filter(vac -> Objects.equals(vac.getNumberOfShots(), 2)).collect(Collectors.toList());
        }
        else return null;
    }

    @Override
    public Optional<Vaccine> save(final Vaccine vaccine) {
        if(!MOCKED_VACCINES.contains(vaccine)){
            MOCKED_VACCINES.add(vaccine);
            return Optional.of(vaccine);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByResearchName(final String researchName) {
        MOCKED_VACCINES.removeIf(it -> Objects.equals(it.getResearchName(), researchName));
    }

    @Override
    public Optional<Vaccine> findByResearchName(final String researchName) {
        return MOCKED_VACCINES.stream().filter(it -> Objects.equals(it.getResearchName(), researchName)).findAny();
    }

    @Override
    public Optional<Vaccine> update(String researchVaccineName,final Vaccine updatedVaccineQuantityInStorage) {
        boolean exist = MOCKED_VACCINES.removeIf(
                o -> Objects.equals(o.getQuantityInStorage(), researchVaccineName) && Objects.equals(o.getQuantityInStorage(), updatedVaccineQuantityInStorage.getQuantityInStorage())
        );

        if(exist){
            MOCKED_VACCINES.add(updatedVaccineQuantityInStorage);
                    return Optional.of(updatedVaccineQuantityInStorage);
        } else {
            return Optional.empty();
        }
    }
}
