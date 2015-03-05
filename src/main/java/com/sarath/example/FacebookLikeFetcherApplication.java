package com.sarath.example;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FacebookLikeFetcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookLikeFetcherApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
				new ServletContainer(), "/facebook/*");
		servletRegistrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return servletRegistrationBean;
	}
}
