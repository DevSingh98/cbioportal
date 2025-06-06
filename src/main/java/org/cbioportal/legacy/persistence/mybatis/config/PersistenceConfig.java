package org.cbioportal.legacy.persistence.mybatis.config;

import java.io.IOException;
import javax.sql.DataSource;
import org.cbioportal.legacy.model.Sample;
import org.cbioportal.legacy.persistence.mybatis.typehandler.SampleTypeTypeHandler;
import org.cbioportal.legacy.utils.config.annotation.ConditionalOnProperty;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(
    value = "org.cbioportal.legacy.persistence.mybatis",
    sqlSessionFactoryRef = "sqlSessionFactory")
public class PersistenceConfig {

  // This is the only way I was able to register the SampleType TypeHandler to MyBatis.
  // The bean-based approach described in the MyBatis docs for mybatis-spring-boot-autoconfigure
  // did not work in my hands (See:
  // https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure#Detecting_MyBatis_components).
  @Bean
  ConfigurationCustomizer mybatisConfigurationCustomizer() {
    return new ConfigurationCustomizer() {
      @Override
      public void customize(org.apache.ibatis.session.Configuration configuration) {
        configuration
            .getTypeHandlerRegistry()
            .register(Sample.SampleType.class, new SampleTypeTypeHandler());
      }
    };
  }

  @Bean("sqlSessionFactory")
  @Profile("clickhouse")
  public SqlSessionFactoryBean sqlSessionFactorySpecifyDataSource(
      @Qualifier("mysqlDataSource") DataSource dataSource, ApplicationContext applicationContext)
      throws IOException {
    return sqlSessionFactory(dataSource, applicationContext);
  }

  @Bean("sqlSessionFactory")
  @Profile("default")
  @ConditionalOnProperty(name = "clickhouse_mode", havingValue = "false", matchIfMissing = true)
  public SqlSessionFactoryBean sqlSessionFactoryDefault(
      DataSource dataSource, ApplicationContext applicationContext) throws IOException {
    return sqlSessionFactory(dataSource, applicationContext);
  }

  private SqlSessionFactoryBean sqlSessionFactory(
      DataSource dataSource, ApplicationContext applicationContext) throws IOException {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setMapperLocations(
        applicationContext.getResources(
            "classpath:org/cbioportal/legacy/persistence/mybatis/*.xml"));
    sessionFactory.setTypeHandlers(new SampleTypeTypeHandler());
    return sessionFactory;
  }

  @Bean
  @Profile("clickhouse")
  public DataSourceTransactionManager transactionManager(
      @Qualifier("mysqlDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
