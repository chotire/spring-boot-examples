package spring.boot.actuator;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActuatorController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, String> hello() {
        return Collections.singletonMap("message", "Hello Actuator!!");
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Map<String, Object> olleh(@Validated Message message) {
        Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("message", message.getValue());
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return model;
    }
    
    @RequestMapping("/foo")
    public String foo() {
        throw new IllegalArgumentException("Server error");
    }

    protected static class Message {
        @NotBlank(message = "Message value cannot be empty")
        private String value;

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}