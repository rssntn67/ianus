package org.opennms.integrations.ianus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpennmsIanusApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpennmsIanusApplication.class, args);
	}

}
