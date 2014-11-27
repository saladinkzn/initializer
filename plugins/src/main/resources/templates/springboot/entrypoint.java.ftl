<#if (packageName!)?has_content>
package ${packageName};
</#if>
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class ${className} {
    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }
}