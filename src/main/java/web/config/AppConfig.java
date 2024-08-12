package web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driver = env.getProperty("db.driver");
        String url = env.getProperty("db.url");
        String username = env.getProperty("db.username");
        String password = env.getProperty("db.password");

        // Debugging outputs
        System.out.println("DB Driver: " + driver);
        System.out.println("DB URL: " + url);
        System.out.println("DB Username: " + username);
        System.out.println("DB Password: " + password);

        // Null check
        if (driver == null || url == null || username == null || password == null) {
            throw new IllegalArgumentException("One of the required DB properties is null.");
        }

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        Properties properties = new Properties();

        String dialect = env.getProperty("hibernate.dialect");
        String showSql = env.getProperty("hibernate.show_sql");
        String hbm2ddl = env.getProperty("hibernate.hbm2ddl.auto");

        // Debugging outputs
        System.out.println("Hibernate Dialect: " + dialect);
        System.out.println("Hibernate Show SQL: " + showSql);
        System.out.println("Hibernate HBM2DDL Auto: " + hbm2ddl);

        // Null check
        if (dialect == null || showSql == null || hbm2ddl == null) {
            throw new IllegalArgumentException("One of the required Hibernate properties is null.");
        }

        emf.setDataSource(getDataSource());
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(properties);
        emf.setPackagesToScan("web.entity");
        return emf;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
