package com.br.documents.business.service;

import com.br.documents.api.record.Arquivo;
import com.br.documents.api.record.RenameFile;
import com.br.documents.domain.entity.Document;
import com.br.documents.domain.entity.TypeDocument;
import com.br.documents.domain.repository.DocumentRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
public class DocumentService {
    private final String ROOT_FOLDER = "/media/deivy/hd02/APP_FILES";

    @Autowired
    private DocumentRepository documentRepository;

    private final String[] PERMITED_EXTENCION = { "pdf", "jpg", "gif", "jpeg" };

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public List<Document> findByTypeDocuments_Id(Long idTypeDocument) {
        TypeDocument typeDocument = TypeDocument.builder().id(idTypeDocument).build();
        return documentRepository.findByTypeDocument(typeDocument);
    }

    public boolean checkPermitedExtencion(String extencion) {
        if(!StringUtils.isEmpty(extencion)) {
            if(ArrayUtils.indexOf(PERMITED_EXTENCION,
                    StringUtils.getFilenameExtension(extencion)) != -1){
                return true;
            }
        }
        return false;
    }

    public boolean renameFile(RenameFile renameFile) {
        if(checkPermitedExtencion(renameFile.newName())){
            File file = new File(ROOT_FOLDER + File.separator + renameFile.oldName());
            File newFile = new File(ROOT_FOLDER + File.separator + renameFile.newName());
            if (file.exists() && !newFile.exists()) {
                return file.renameTo(newFile);
            }
        }
        return false;
    }

    public boolean deleteFile(String nameFile){
        File file = new File(ROOT_FOLDER + "/"+nameFile);
        if(file.exists()){
            return file.delete();
        }
        return false;
    }

    public String getFileByName(String nameFile) {
        File file = new File(ROOT_FOLDER + "/"+nameFile);
        String encoded = null;
        try {
            encoded = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
            return encoded;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Arquivo> listFilesInFolder() {
        Set<Arquivo> arquivos = new HashSet<>();
        try {
            Stream<Path> stream = Files.list(Paths.get(this.ROOT_FOLDER));
            for (Path path : stream.filter(file ->
                    (!Files.isDirectory(file)  && checkPermitedExtencion(file.toFile().getPath()))
                ).collect(Collectors.toSet())) {
                FileChannel fileChannel = FileChannel.open(path);
                arquivos.add(new Arquivo(path.toFile().getName(), fileChannel.size()));
            }
            return arquivos;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path fileStorageLocation = Paths.get(this.ROOT_FOLDER).toAbsolutePath().normalize();;
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
