package org.mybeanframework.web.mvc.request;

import java.lang.reflect.Method;

/**
 * @author herenpeng
 * @since 2020-12-15 15:26
 */
public class BeanAndMethod {


    private Object bean;

    private Method method;


    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }


}
