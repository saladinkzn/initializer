<#if (packageName!)?has_content>
package ${packageName};
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

<#if templateEngineConfig.useFreemarker>
    @RequestMapping("/freemarker")
    public String renderFreemarker() {
        request.setAttribute("message", "Hello, world!");
        return "freemarker";
    }
</#if>

<#if templateEngineConfig.useVelocity>
    @RequestMapping("/velocity")
    public String renderVelocity() {
        request.setAttribute("message", "Hello, world!");
        return "velocity";
    }
</#if>
}