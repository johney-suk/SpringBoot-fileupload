package org.zerock.j08.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.j08.dto.UploadResultDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {

    @Value("${spring.servlet.multipart.location}")
    private String path;

    @ResponseBody
    @GetMapping(value = "/down")
    public ResponseEntity<byte[]> down(String file){

        File target = new File(path, file);

        String mimeType = null;
        try {
            mimeType = Files.probeContentType(target.toPath());

            if (mimeType.startsWith("image") == false){
                mimeType = "application/octet-stream";
            }
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//            FileInputStream fin = new FileInputStream(target);

            byte[] arr = FileCopyUtils.copyToByteArray(target);

            ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @PostMapping(value ="/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UploadResultDTO> upload(MultipartFile[] files){

        log.warn(path);

        List<UploadResultDTO> result = new ArrayList<>();

        for (MultipartFile file:files) {

            log.warn(file);

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            File outFile = new File(path, uuid+"_"+fileName );
            File thumbFile = new File(path, "s_"+uuid+"_"+fileName );

            try {
                InputStream fin = file.getInputStream(); //< -- 변경
                Files.copy(fin, outFile.toPath()); // <-- 변경

                BufferedImage originalImage = ImageIO.read(outFile);

                BufferedImage thumbnail = Thumbnails.of(originalImage)
                        .size(100, 100)
                        .asBufferedImage();

                ImageIO.write(thumbnail, "JPG", thumbFile);

                fin.close(); // <-- 추가


            } catch (IOException e) {
                e.printStackTrace();
            }

            result.add(UploadResultDTO.builder().uuid(uuid).fileName(fileName).build());
        }//end for

        return result;
    }

}
