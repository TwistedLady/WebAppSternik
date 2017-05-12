

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "com.example" }, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "com\\.example\\.web\\..*") })
@ImportResource({"classpath:/applicationContext.xml"})//,"classpath:/database-config.xml"})
public class SpringBusinessConfig {

/*    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }*/
}
