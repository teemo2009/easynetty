package annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/12/8.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageMeta {
        short module() default 0;
        short cmd() default 0;
}
