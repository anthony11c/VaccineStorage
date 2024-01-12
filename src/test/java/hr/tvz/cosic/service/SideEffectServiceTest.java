package hr.tvz.cosic.service;

import hr.tvz.cosic.repository.JdbcVaccineRepository;
import hr.tvz.cosic.repository.SideEffectRepository;
import hr.tvz.cosic.sideeffect.SideEffect;
import hr.tvz.cosic.vaccine.Vaccine;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SideEffectServiceTest {

    @MockBean
    SideEffectRepository sideEffectRepository;

    @MockBean
    JdbcVaccineRepository vaccineRepository;

    @Autowired
    SideEffectService sideEffectService;



    @Test
    void getAllSideEffects() {
         List<SideEffect> mockedSideEffects = new ArrayList<>(Arrays.asList(
                new SideEffect( "Temperatura", "Temperatura koja prelazi 38 stupnjeva celzijevih!", 5),
                new SideEffect( "Osip", "Osip na mjestu oko uboda ili po ruci u koju je primljeno cjepivo!", 3),
                new SideEffect("Mučnina", "labost u probavnom traktu nastupa dan nakon cijepljenja!", 2)
        ));

        when(sideEffectRepository.findAll()).thenReturn(mockedSideEffects);

        List<SideEffectDTO>  sideEffects = sideEffectService.getAllSideEffects();
        assertNotNull(sideEffects);
        assertEquals(sideEffects.size(), 3);
    }

    @Test
    void findAllByVaccine_ResearchName() {
        List<SideEffect> mockedSideEffects = new ArrayList<>(Arrays.asList(
                new SideEffect( "Temperatura", "Temperatura koja prelazi 38 stupnjeva celzijevih!", 5),
                new SideEffect( "Osip", "Osip na mjestu oko uboda ili po ruci u koju je primljeno cjepivo!", 3),
                new SideEffect("Mučnina", "labost u probavnom traktu nastupa dan nakon cijepljenja!", 2)
        ));

        when(sideEffectRepository.findAllByVaccine_ResearchName("mRNA-1273")).thenReturn(mockedSideEffects);

        List<SideEffectDTO>  sideEffects = sideEffectService.findAllByVaccine_ResearchName("mRNA-1273");
        assertNotNull(sideEffects);
        assertEquals(sideEffects.size(), 3);
    }
}