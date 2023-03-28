package com.apartment.vmoproject.api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.apartment.vmoproject.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class.getName());

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3Client s3;



    public FileServiceImpl(AmazonS3Client s3) {
        this.s3 = s3;
    }


    @Override
    public String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            String fileName = System.currentTimeMillis()+"_"+originalFilename;
            File fileCv = convertMultiPartToFile(file);
            PutObjectResult putObjectResult = s3.putObject(bucketName,fileName,fileCv);
            return s3.getResourceUrl(bucketName,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public String deleteFile(String fileName) {
        s3.deleteObject(bucketName,fileName);
        return "File delete";
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException{
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        return convFile;
    }


//    @Override
//    public String uploadImage(String path, MultipartFile file) {
//        String name = file.getOriginalFilename();
//
//        String randomId = UUID.randomUUID().toString();
//        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
//
//        String filePath = path + File.separator+fileName;
//
//        File f = new File(path);
//        if(!f.exists()){
//            f.mkdir();
//        }
//
//
//
//        try {
//            Files.copy(file.getInputStream(), Paths.get(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        LOGGER.info("upload file successfully!!");
//        return filePath;
//    }
}
