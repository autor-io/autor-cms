package io.autor.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Stephan Grundner
 */
@Controller
@RequestMapping(path = "/assets")
public class AssetController {

    @Autowired
    private UploadService uploadService;

    /**
     * http://localhost:8080/assets/?id=1
     *
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Resource> asset(@RequestParam(name = "id") Long id,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {

        Upload upload = uploadService.findUploadById(id);
        FileSystemResource source = uploadService.toResource(upload);

        String contentDisposition = String.format("attachment; filename=\"%s\"", upload.getFilename());
        return ResponseEntity.ok()
                .contentLength(source.contentLength())
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(source);
    }
}
