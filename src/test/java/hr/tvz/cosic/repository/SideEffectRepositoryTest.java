package hr.tvz.cosic.repository;

import hr.tvz.cosic.sideeffect.SideEffect;
import hr.tvz.cosic.vaccine.Vaccine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SideEffectRepositoryTest {

    @Autowired
    SideEffectRepository sideEffectRepository;

    @Test
    void findAllByVaccine_ResearchName() {
        List<SideEffect> sideEffectList = sideEffectRepository.findAllByVaccine_ResearchName("BNT162b2");

        assertNotNull(sideEffectList);
        assertEquals(sideEffectList.size(), 1);
    }

    @Test
    void findAllByVaccine_InvalidResearchName(){
        List<SideEffect> sideEffectList = sideEffectRepository.findAllByVaccine_ResearchName("ZORO");

        assertNotNull(sideEffectList);
        assertEquals(sideEffectList.size(), 0);
    }
}