package io.autor.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Stephan Grundner
 */
@Service
public class UploadService {

    @Autowired
    private UploadRepository uploadRepository;

    public Upload findUploadById(Long id) {
        return uploadRepository.findById(id).orElse(null);
    }

    public Upload saveUpload(Upload upload) {
        return uploadRepository.save(upload);
    }

    public FileSystemResource toResource(Upload upload) {
        String filename = String.format("%s.asset", upload.getId());
        return new FileSystemResource("assets/" + filename);
    }

    @Transactional
    public Upload receive(MultipartFile multipartFile) {
        Upload upload = new Upload();
        upload.setFilename(multipartFile.getOriginalFilename());
        upload = saveUpload(upload);

        FileSystemResource target = toResource(upload);
        try (InputStream inputStream = multipartFile.getInputStream();
             OutputStream outputStream = target.getOutputStream()) {

            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return upload;
    }
}
