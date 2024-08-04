package xyz.game.merchants;

import org.springframework.boot.SpringApplication;

public class TestMerchantsApplication {

	public static void main(String[] args) {
		SpringApplication.from(MerchantsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
