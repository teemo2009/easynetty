package net;

/**
 * Created by Administrator on 2017/12/8.
 */
public interface ClassFilter {
    /**
     * 是否满足条件
     * @param clazz
     * @return
     */
    boolean accept(Class<?> clazz);
}
