package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
@RequestMapping("/api")
public class ConfigClientApplication {

	@Value("${dev.tech:sharief}")
	private String name;

	@Autowired
	private Environment env;

	@GetMapping(value = "/config")
	public String Config(){
		return String.format("The Configuration is %s", name);
	}

	@GetMapping(value = "/config/{key}")
	public String getConfig(@PathVariable(value = "key") String key){
		return String.format("key : %s\nvalue : %s", key, env.getProperty(key));
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
