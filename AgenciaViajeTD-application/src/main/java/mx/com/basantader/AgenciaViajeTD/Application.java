package mx.com.basantader.AgenciaViajeTD;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "mx.com.basantader.AgenciaViajeTD")
@EntityScan("mx.com.basantader.AgenciaViajeTD.model")
@EnableJpaRepositories("mx.com.basantader.AgenciaViajeTD.repository")
public class Application {

    public static void main(String[] args) {
        final SpringApplication springApplication =
            new SpringApplication(Application.class);
        // ir is being added here for local run ONLY , spring profiles should be be run time parameters when run spring boot jar
        springApplication.setDefaultProperties(Collections.singletonMap("spring.profiles.default","local"));
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

}
