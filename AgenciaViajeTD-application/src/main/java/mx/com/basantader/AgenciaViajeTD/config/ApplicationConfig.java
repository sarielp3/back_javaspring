package mx.com.basantader.AgenciaViajeTD.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 
 */
@Configuration
public class ApplicationConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
