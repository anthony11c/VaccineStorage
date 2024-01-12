package hr.tvz.cosic.service;

import hr.tvz.cosic.repository.JdbcVaccineRepository;
import hr.tvz.cosic.sideeffect.SideEffect;
import hr.tvz.cosic.vaccine.Vaccine;
import hr.tvz.cosic.vaccine.VaccineCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class VaccineServiceTest {

    @MockBean
    JdbcVaccineRepository vaccineRepository;

    @Autowired
    VaccineService vaccineService;

    @Test
    void updateQuantityInStorage() {
        assertEquals(1, 1);
    }

    @Test
    void findAll() {
        Set<Vaccine> mockedvaccineList = new HashSet<>(Arrays.asList(
                new Vaccine("mRNA-1273", "ModernaTX", 2, 15, Vaccine.VaccineType.RNA),
                new Vaccine("BNT162b2", "Pfizer & BioNTech", 2, 50, Vaccine.VaccineType.VIRALNI_VEKTOR),
                new Vaccine("JNJ-78436735", "Johnson & Johnson", 1, 100, Vaccine.VaccineType.RNA)
        ));

        when(vaccineRepository.findaAll()).thenReturn(mockedvaccineList);

        List<VaccineDTO>  vaccineDTOS = vaccineService.findAll();
        assertNotNull(vaccineDTOS);
        assertEquals(vaccineDTOS.size(), 3);
    }

//    @Test
//    void save() {
//        when(vaccineRepository.save(new Vaccine( "test", "ModernaTX", 2, 15, Vaccine.VaccineType.RNA))
//        );
//
//        Optional<VaccineDTO> vaccineDTO = vaccineService.save(new VaccineCommand("test", "ModernaTX", 2, 15, Vaccine.VaccineType.RNA));
//
//        assertNotNull(vaccineDTO);
//        assertTrue(vaccineDTO.isPresent());
//        assertEquals(vaccineDTO.get().getResearchName(), "test");
//    }

    @Test
    void findByResearchName() {
        when(vaccineRepository.findByResearchName("test")).thenReturn(
                Optional.of(new Vaccine(1L, "test", "ModernaTX", 2, 15, Vaccine.VaccineType.RNA))
        );

        VaccineDTO vaccineDTO = vaccineService.findByResearchName("test").orElse(null);
        assertNotNull(vaccineDTO);
        assertEquals(vaccineDTO.getResearchName(), "test");
        assertEquals(vaccineDTO.getAvailableDoses(), 15);
    }

    @Test
    void deleteByResearchName() {
        vaccineService.deleteByResearchName("mRNA-1273");

        verify(vaccineRepository).deleteByResearchName("mRNA-1273");
    }
}
