package io.autor;

import io.autor.scheme.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private StructureService structureService;

	@Override
	public void run(String... args) throws Exception {

		structureService.createStructure("basicPage", 1)
				.addTextItem("title")
				.addTextItem("body")
					.setMultiline(true)
				.addStructureItem("embedded")
                .addNumberItem("priority")
                .addMomentItem("happened")
				.buildAndRegister();

		"".toString();
	}
}
