package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    @Autowired private FileService fileService;
    @Autowired private ProductDao productDao;

    public boolean pRegister(ProductDto productDto){
        System.out.println("ProductService.pRegister");

        // 여러개 첨부파일 업로드 하기
        List<String> fileNames=new ArrayList<>();
        // (1) 첨부파일 개수만큼 반복문 돌린다
        productDto.getFiles().forEach(file->{
            // (2) 각 첨부파일 하나씩 업로드 메소드에 대입한다.
            String fileName= fileService.fileUpload(file);
            if (fileName!=null){
                // (3) 업로드 된 파일명을 리스트에 담는다.
                // 파일명들을 DB에 저장하기 위해
                fileNames.add(fileName);
                System.out.println("fileNames = " + fileNames);
            } // if e
        }); // for e
        productDto.setFileNames(fileNames);
        System.out.println("productDto = " + productDto);
        return productDao.pRegister(productDto);
    } // m e

    public List<ProductDto> getProductAll(){
        return productDao.getProductAll();
    }
}
