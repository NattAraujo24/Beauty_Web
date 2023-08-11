package mx.edu.utez.beautyPalaceApi.controllers.image;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;


@RestController
@RequestMapping("/api-beautypalace/images")
@CrossOrigin({"*"})
public class ImageController {

    @GetMapping(value = "/{img}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable ("img") String img) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/"+ img);
        return Files.readAllBytes(resource.getFile().toPath());
    }
}
