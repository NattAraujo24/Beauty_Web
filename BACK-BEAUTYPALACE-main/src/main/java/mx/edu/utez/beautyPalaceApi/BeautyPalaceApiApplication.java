package mx.edu.utez.beautyPalaceApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class BeautyPalaceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautyPalaceApiApplication.class, args);
	}

}
