package hr.tvz.cosic.repository;

import hr.tvz.cosic.vaccine.Vaccine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VaccineRepositoryTest {

    @Autowired
    VaccineRepository vaccineRepository;

    @Test
    void findaAll() {
        Set<Vaccine> vaccineSet = vaccineRepository.findaAll();

        assertNotNull(vaccineSet);
        assertEquals(vaccineSet.size(), 3);
    }

    @Test
    void findVaccineByResearchName() {
        Optional<Vaccine> optionalVaccine = vaccineRepository.findVaccineByResearchName("JNJ-78436735");

        assertNotNull(optionalVaccine);
        assertEquals(optionalVaccine.get().getResearchName(), "JNJ-78436735");
    }

    @Test
    void findVaccineByInvalidResearchName() {
        Optional<Vaccine> optionalVaccine = vaccineRepository.findVaccineByResearchName("ZGDERTEZ");

        assertNotNull(optionalVaccine);
        assertTrue(optionalVaccine.isEmpty());
    }

    @Test
    void save() {
        Optional<Vaccine> optionalVaccine = vaccineRepository.save(new Vaccine(4L, "MAM-453", "MAMIC-FOUNDATION", 1, 100, Vaccine.VaccineType.RNA));

        assertNotNull(optionalVaccine);
        assertTrue(optionalVaccine.isPresent());
        assertEquals(optionalVaccine.get().getManufacturerName(), "MAMIC-FOUNDATION");
    }

    @Transactional
    @DirtiesContext
    @Test
    void deleteByResearchName() {
        vaccineRepository.deleteByResearchName("mRNA-1273");
        Optional<Vaccine> optionalVaccine = vaccineRepository.findVaccineByResearchName("mRNA-1273");

        assertNotNull(optionalVaccine);
        assertFalse(optionalVaccine.isPresent());
    }
}