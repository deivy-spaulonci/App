package com.br.documents.api.restcontroller;

import com.br.documents.api.dto.DocumentDto;
import com.br.documents.api.mapper.DocumentMapper;
import com.br.documents.api.record.Arquivo;
import com.br.documents.api.record.RenameFile;
import com.br.documents.business.service.DocumentService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/documents")
public class DocumentRestController {
    private static final DocumentMapper documentMapper = DocumentMapper.INSTANCE;

    @Autowired
    private DocumentService documentService;

    @GetMapping("/type_document/{id}")
    public List<DocumentDto> get(@PathVariable Long id){
        return documentMapper.toDtoList(documentService.findByTypeDocuments_Id(id));
    }

    @GetMapping()
    public List<DocumentDto> getAll(){
        return documentMapper.toDtoList(documentService.findAll());
    }

    @GetMapping("/files")
    public Set<Arquivo> getAllFiles() {
        return documentService.listFilesInFolder();
    }

    @GetMapping("/fileByName/{nameFile}")
    public ResponseEntity<String> getFileByName(@PathVariable String nameFile) {
        String resp = documentService.getFileByName(nameFile);
        if(resp == null)
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        else
            return ok(resp);
    }

    @GetMapping("/deleteFile/{nameFile}")
    public ResponseEntity<String> deleteFileByName(@PathVariable String nameFile) {
        if(documentService.deleteFile(nameFile)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/renameFile")
    public ResponseEntity<?> save(@Valid @RequestBody RenameFile renameFile) {
        if(documentService.renameFile(renameFile))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            documentService.storeFile(file);
            log.info("Uploading file: " + file.getOriginalFilename());
//            Product attachment = fileService.saveAttachment(file);
//            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/download/")
//                    .path(attachment.getId())
//                    .toUriString();
//            ResponseClass response = new ResponseClass(attachment.getFileName(),
//                    downloadUrl,
//                    file.getContentType(),
//                    file.getSize());
//            responseList.add(response);
        }
//        return responseList;
          return new ResponseEntity<>(HttpStatus.OK);
          //return new ResponseEntity<>("deu bosta", HttpStatus.BAD_REQUEST);
    }
}
