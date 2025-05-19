package org.exp.ecommerce.api.controllers;

import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.base.Attachment;
import org.exp.ecommerce.api.repositories.AttachmentRepository;
import org.exp.ecommerce.api.utils.Const;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Const.API + "/attachments")
public class AttachmentController {

    private final AttachmentRepository attachmentRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getAttachment(@PathVariable Integer id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(getMediaType(attachment.getName())));

        return new ResponseEntity<>(attachment.getContent(), headers, HttpStatus.OK);
    }

    private String getMediaType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (fileExtension) {
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            case "pdf":
                return "application/pdf";
            default:
                return "application/octet-stream";
        }
    }
}