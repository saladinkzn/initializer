<#if (packageName!)?has_content>
package ${packageName};
</#if>

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
class HelloController {

    @RequestMapping("/")
    String index() {
        "Greetings from Spring Boot!";
    }
}