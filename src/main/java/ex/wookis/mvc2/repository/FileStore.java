package ex.wookis.mvc2.repository;

import ex.wookis.mvc2.domain.UploadFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    private final String fileDir = System.getProperty("user.dir")+"/files/";

    public String getFullPath(String fileName) {
        return fileDir+fileName;
    }

    //파일 저장
    public UploadFile storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        String originalFilename = file.getOriginalFilename();
        // 서버에 저장할 파일 이름
        String storeFileName = createStoreFileName(originalFilename);
        file.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    //여러 파일 저장
    public List<UploadFile> storeFileList(List<MultipartFile> files) throws IOException {
        List<UploadFile> storeFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            storeFiles.add(storeFile(file));
        }
        return storeFiles;
    }

    //저장할 파일 이름 생성
    public String createStoreFileName(String originalName) {
        String ext = extractExt(originalName);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ext;
    }

    private String extractExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
}
