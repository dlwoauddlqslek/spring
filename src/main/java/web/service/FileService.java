package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {
    /*
        프로젝트명
            - build 폴더: 배포된 클래스/파일들
            - src 폴더: 배포 전 개발자가 코드(파일)을 작성하는 곳
        배포 : 내 코드를 웹서버에 올림(배포)으로써 외부(클라이언트)로 부터 리소스 제공할 수 있다.
    */
    // 파일 저장된 위치 경로 정의 필드
    // 배포 전
    //String uploadPath="C:\\Users\\tj-bu-703-021\\Desktop\\오전반\\src\\main\\resources\\static\\upload\\";
    // 배포 후
    String uploadPath="C:\\Users\\tj-bu-703-021\\Desktop\\오전반\\build\\resources\\main\\static\\upload\\";

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

        String filePath=uploadPath+filename;

        File file=new File(filePath);
    try {
        multipartFile.transferTo(file);
    }catch (Exception e){System.out.println("e = " + e); return null;}
        return filename;
    }

    @Autowired private HttpServletRequest request;
    @Autowired private HttpServletResponse response;

    public void fileDownload(String filename) {
        System.out.println("FileService.fileDownload");
        System.out.println("filename = " + filename);
        // 1. 다운로드할 경로 설정 (uploadPath)
        String downloadPath=uploadPath+filename;
        // - File 클래스는 file 관련된 다양한 메서드를 제공
        File file=new File(downloadPath);
        // 해당 경로 파일이 존재하면 true, 아니면 false -> false시 return
        if (!file.exists()){return;}
        // 2. 해당 다운로드할 파일을 JAVA로 바이트 형식으로 읽어들이기
        try {
            //====================파일을 바이트 배열로 읽어오기
            // 2-1. 파일 입력 스트림 객체 생성
            //BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downloadPath));
            FileInputStream fin = new FileInputStream(downloadPath);
            // 2-2. 파일 용량만큼 배열 선언 (여러개의 바이트가 한 파일)
            long fileSize=file.length();
            byte[] bytes=new byte[(int)fileSize];
            fin.read(bytes); // 경로에 해당하는 파일을 바이트로 가져오기
            fin.close();
            System.out.println(Arrays.toString(bytes));

            //====================읽어온 바이트배열을 HTTP 바이트 형식으로 응답하기
            // 3. HTTP 스트림으로 응답하기
            // 3-1 출력 스트림 객체 생성
            //BufferedOutputStream fout=new BufferedOutputStream(response.getOutputStream());
            ServletOutputStream fout=response.getOutputStream();
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename.split("_")[1],"UTF-8"));
            // 3-2 바이트 배열 내보내기/출력/쓰기
            fout.write(bytes);
            // 쓰고 난 후 버퍼 닫기
            fout.close();
        }catch (Exception e){System.out.println("e = " + e);}
    }
}
