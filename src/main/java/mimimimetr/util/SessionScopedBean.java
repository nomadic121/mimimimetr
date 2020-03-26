package mimimimetr.util;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class SessionScopedBean {

    private String name = "гость-" + Math.random();

}
