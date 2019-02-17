package jp.co.sunarch.sakatube;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SakaTubeApplication implements ApplicationRunner {

	private GenerateDB generateDb;

	public SakaTubeApplication (GenerateDB generateDb) {
		this.generateDb = generateDb;
	}

	public static void main(String[] args) {
		SpringApplication.run(SakaTubeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// DB環境構築
		this.generateDb.createDB();
	}
}
