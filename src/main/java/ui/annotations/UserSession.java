package ui.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Указывает когда будет исполняться (во время теста)
@Target(ElementType.METHOD) // Указывает таргет, к которому будет применяться (метод)
public @interface UserSession {
    int value() default 1;
    int auth() default 1;
}
