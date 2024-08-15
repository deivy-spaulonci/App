package com.br.documents.business.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
//@SpringBootTest
public class DocumentServiceTest {

    private final String ROOT_FOLDER = "/media/deivy/hd02/APP_FILES";

    @Test
    public void testListFile() {
        try (Stream<Path> stream = Files.list(Paths.get(this.ROOT_FOLDER))) {
            Set<String> lista = stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());

            lista.forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void encodeBase64(){
        File file = new File(ROOT_FOLDER + "/text.txt");
        String encoded = null;
        try {
            encoded = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
            log.info(encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(encoded);
    }

    @Test
    public void decodeBase64(){
        File outImage = new File(ROOT_FOLDER + "/text_decode.txt");

        String encoded = "ZGVpdnkKZGVpdnkK";

        byte[] decoded = Base64.getDecoder().decode(encoded);
        try {
            Files.write(outImage.toPath(), decoded);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
