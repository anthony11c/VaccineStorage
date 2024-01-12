package hr.tvz.cosic;

import hr.tvz.cosic.repository.SideEffectRepository;
import hr.tvz.cosic.sideeffect.SideEffect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VaxappApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaxappApplication.class, args);
    }
}
