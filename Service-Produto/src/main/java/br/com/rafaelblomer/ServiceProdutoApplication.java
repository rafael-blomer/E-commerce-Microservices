package br.com.rafaelblomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceProdutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProdutoApplication.class, args);
	}

}
