package com.leo.initializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {
	//private static final Logger log = LoggerFactory.getLogger(App.class);
	//public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-DD");
	public static void main(String[] args) {
		/*ConfigurableApplicationContext context =*/
		SpringApplication.run(App.class, args);
		/*Controller contr =  (Controller) context.getBean("controller");
		System.out.println(contr);*/
	}
/*
@Bean
CommandLineRunner runner()
{
	return args ->{
	Tool_info inf = new Tool_info(23, "SomeApp", "ABC", "ABC.SomeApp.23", 1024, null, LocalDate.now());
	log.info("App Data: "+ inf);
	};
}*/
}
