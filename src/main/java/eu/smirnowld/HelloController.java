package eu.smirnowld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(final Model model,
                        @Value("${soy.hello-name}") final String helloName) {
        model.addAttribute("name", helloName);
        return "eu.smirnowld.hello";
    }
}
