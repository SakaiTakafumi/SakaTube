package jp.co.sunarch.choseisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChoseiSanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoseiSanApplication.class, args);
		GenerateDB generateDb = new GenerateDB();
		generateDb.createDB();
		
	}
}
