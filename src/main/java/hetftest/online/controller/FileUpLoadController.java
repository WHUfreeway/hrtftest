package hetftest.online.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


@RestController
public class FileUpLoadController {

    private static final String UPLOAD_DIR = "C:/Users/freeway/upload";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir.getAbsolutePath() + File.separator + fileName);
            file.transferTo(dest);

            return "File uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed";
        }
    }
}
