package com.htche.oauth;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.htche.database.mybatis.configuration.MyBatisConfiguration;
import com.htche.database.tx.configuration.TransactionManagerConfigure;
import com.htche.oauth.config.OauthConfiguration;
import com.htche.web.configure.ApplicationCoreConfigure;

@EnableAutoConfiguration

@Import({ ApplicationCoreConfigure.class, MyBatisConfiguration.class,TransactionManagerConfigure.class,	OauthConfiguration.class})
public class OauthServerMain extends WebMvcConfigurerAdapter{
	static final Logger logger = Logger
			.getLogger(OauthServerMain.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OauthServerMain.class);
		app.setShowBanner(false);
		app.run(args);
		logger.info("OauthServerMain  Start.");
	}
}
