package spring.qlbh.QLBHDT.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("spring.qlbh.QLBHDT.*")
//Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {

	@Autowired
	private Environment env;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean(name = "dataSource")
	  public DataSource getDataSource() {
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 
	      dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
	      dataSource.setUrl(env.getProperty("ds.url"));
	      dataSource.setUsername(env.getProperty("ds.username"));
	      dataSource.setPassword(env.getProperty("ds.password"));
	 
	      System.out.println(dataSource);
	      
	      return dataSource;
	  }
	 
	  @Autowired
	  @Bean(name = "sessionFactory")
	  public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
	      Properties properties = new Properties();
	 
	      // Xem: ds-hibernate-cfg.properties
	      properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
	      properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	      properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
	      
	 
	      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	      factoryBean.setPackagesToScan(new String[] { "spring.qlbh.QLBHDT.entity" });
	      factoryBean.setDataSource(dataSource);
	      factoryBean.setHibernateProperties(properties);
	      factoryBean.afterPropertiesSet();
	      //
	      SessionFactory sf = factoryBean.getObject();
	      System.out.println(sf);
	      
	      return sf;
	  }
	 
	  @Autowired
	  @Bean(name = "transactionManager")
	  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	 
	      return transactionManager;
	  }
}
