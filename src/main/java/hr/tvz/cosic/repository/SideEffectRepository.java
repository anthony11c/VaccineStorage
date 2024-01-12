package hr.tvz.cosic.repository;

import hr.tvz.cosic.sideeffect.SideEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SideEffectRepository extends JpaRepository<SideEffect, Long> {

    List<SideEffect> findAllByVaccine_ResearchName(String researchName);
}

