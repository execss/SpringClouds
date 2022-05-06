//package top.byteinfo.blog.x.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = {"top.byteinfo.x.blog.mbg.mapper","top.byteinfo.blog.x.dao"}, sqlSessionTemplateRef = "druidTestSqlSessionTemplate")
//public class DruidTest {
//
//    @Primary
//    @Bean(name = "druidTestSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("Druid3") DataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:mapper/**/*.xml");
//
//        sessionFactoryBean.setMapperLocations(resources);
//        return sessionFactoryBean.getObject();
//    }
//
//    @Primary
//    @Bean("druidTestTransactionManager")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("Druid3") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean("druidTestSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("druidTestSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
