package jp.co.sunarch.sakatube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SakaTubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SakaTubeApplication.class, args);
		GenerateDB generateDb = new GenerateDB();
		generateDb.createDB();
	}
}
