package com.springmvc.annotations;


import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration   //it will provide <bean> tag section
@ComponentScan(basePackages = "com.springmvc.annotations")
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableTransactionManagement
public class SpringMvcConfig{
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() { //<bean id="dataSource" 
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driver"));
		dataSource.setUrl("database.url");
		dataSource.setUsername("database.username");
		dataSource.setPassword("database.password");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getsessionFactory(){
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setAnnotatedClasses(Login.class, Role.class, Register.class);  
		sessionFactory.setHibernateProperties(loadProperties());
		return sessionFactory;
	}
	
	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate=new HibernateTemplate(sessionFactory);
		hibernateTemplate.setSessionFactory(sessionFactory);
		hibernateTemplate.afterPropertiesSet();
		return hibernateTemplate;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager manager=new HibernateTransactionManager(sessionFactory);
		return manager;
	}
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl impl=new JavaMailSenderImpl();
		impl.setHost("smtp-mail.outlook.com");
		impl.setPort(587);
		impl.setUsername("manicharan7002@outlook.com");
		impl.setPassword("mani0504");
		impl.setJavaMailProperties(javaMailProperties());
		return impl;
	}
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		return multipartResolver;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate=new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestinationName("user.alert.queue");
		return jmsTemplate;
	}
	
	
	private ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
		
	}

	private Properties javaMailProperties() {
		Properties properties=new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.debug", "true");
		properties.put("authentication", "plain");
		properties.put("mail.smtp.ssl.trust", "smtp-mail.outlook.com");
		return properties;
	}
	
	
	private Properties loadProperties() {
		Properties properties=new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
	
	
	
	
	
}
