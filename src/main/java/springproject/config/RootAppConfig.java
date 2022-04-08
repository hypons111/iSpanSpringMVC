package springproject.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 等如 beans.config.xml <context:annotation-config>
@Configuration
//等如 beans.config.xml <context:component-scan>
@ComponentScan(basePackages = "springproject")
//等如 beans.config.xml <bean id="transactionManager">
@EnableTransactionManagement

public class RootAppConfig {
	
	// 等如 beans.config.xml <bean id="dataSource">
	@Bean
    public DataSource dataSource() throws IllegalArgumentException, NamingException {
    	JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
    	jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
    	jndiBean.afterPropertiesSet();
    	DataSource ds = (DataSource)jndiBean.getObject();
    	return ds;
    }
	
	// 等如 beans.config.xml <bean id="sessionFactory"
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("springproject");
		factory.setHibernateProperties(addProperties());
		return factory;
	}
	
	// 等如 beans.config.xml <property name="hibernateProperties">
	private Properties addProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
//		props.put("hibernate.current_session_context_class", "thread");
		return props;
	}
	
	// 等如 beans.config.xml <bean id="transactionManager" 
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory);
		return txMgr;
	}
}
