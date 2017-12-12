package annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/12/11.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
}
