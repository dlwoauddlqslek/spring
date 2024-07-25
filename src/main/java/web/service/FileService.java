package web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    public String fileUpload(MultipartFile multipartFile){
        System.out.println(multipartFile.getContentType());
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getSize());
        System.out.println(multipartFile.isEmpty());

        String uuid= UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);

        String filename=multipartFile.getOriginalFilename();
        filename=uuid+"_"+filename.replaceAll("_","-");
        System.out.println("filename = " + filename);
        String uploadPath="C:\\Users\\tj-bu-703-013\\Desktop\\오전반\\spring\\src\\main\\resources\\static\\upload\\";
        String filePath=uploadPath+filename;

        File file=new File(filePath);
    try {
        multipartFile.transferTo(file);
    }catch (Exception e){System.out.println("e = " + e); return null;}
        return filename;
    }
}
