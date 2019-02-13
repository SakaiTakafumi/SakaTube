package jp.co.sunarch.sakatube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SakaTubeApplication {

	private static GenerateDB generateDb;

	public SakaTubeApplication (GenerateDB generateDb) {
		SakaTubeApplication.generateDb = generateDb;
	}

	public static void main(String[] args) {
		SpringApplication.run(SakaTubeApplication.class, args);
		generateDb.createDB();
	}
}
