package com.br.documents;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@SpringBootApplication
public class DocumentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentsApplication.class, args);
	}

}
