package com.mufan.utils.intercept;

import com.mufan.utils.JsonHandlerException;
import com.mufan.utils.JsonResult;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aswe on 2016/8/1.
 */
public class MyErrorInterceptor extends AbstractInterceptor {

    public void destroy() {

    }

    public void init() {

    }

    public String intercept(ActionInvocation invocation) throws Exception {
        String result = null;
        try {
            result = invocation.invoke();
        } catch (Exception e) {
           /* ActionContext actionContext = invocation.getInvocationContext();
            HttpServletRequest request = (HttpServletRequest) actionContext
                    .get(StrutsStatics.HTTP_REQUEST);
            if (isAjaxRequest(request)) {
            }*/
            ActionContext actionContext = invocation.getInvocationContext();
            HttpServletRequest request = (HttpServletRequest) actionContext
                    .get(StrutsStatics.HTTP_REQUEST);
            if (isAjaxRequest(request)) {//如果是ajax请求方式
                ValueStack stack = invocation.getStack();
                List<ExceptionMappingConfig> exceptionMappings = invocation
                        .getProxy().getConfig().getExceptionMappings();
                JsonHandlerException je = new JsonHandlerException(e);
                String mappedResult = this.findResultFromExceptions(
                        exceptionMappings, je);
                result = mappedResult;
                /*Map<String, Object> dataMap = new HashMap<String, Object>();
                stack.set("dataMap", dataMap);
                dataMap.put("result", "500");*/
                JsonResult jsonResult = new JsonResult();
                jsonResult.setStatusCode(500);
                StringBuffer msg = new StringBuffer(e.toString() + "\n");
                StackTraceElement[] trace = e.getStackTrace();
                for (int i = 0; i < trace.length; i++)
                    msg.append("\tat " + trace[i] + "\n");
//                ServletActionContext.getRequest().getSession().setAttribute("errMsg", msg);
                jsonResult.setMessage(msg.toString());
            } /*else {// 默认处理方式
                if (isLogEnabled()) {
                    handleLogging(e);
                }
                List<ExceptionMappingConfig> exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
                String mappedResult = this.findResultFromExceptions(exceptionMappings, e);
                if (mappedResult != null) {
                    result = mappedResult;
                    publishException(invocation, new ExceptionHolder(e));
                } else {
                    throw e;
                }
                invocation.getStack();
                invocation.getInvocationContext().get(Action.ERROR);
//              invocation.getStack().findString("exceptionStack");
                invocation.getInvocationContext().get(Action.ERROR);
            }*/
        }

        return result;
    }
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }

    protected String findResultFromExceptions(
            List<ExceptionMappingConfig> exceptionMappings, Throwable t) {
        String result = null;

        // Check for specific exception mappings.
        if (exceptionMappings != null) {
            int deepest = Integer.MAX_VALUE;
            for (Object exceptionMapping : exceptionMappings) {
                ExceptionMappingConfig exceptionMappingConfig = (ExceptionMappingConfig) exceptionMapping;
                int depth = getDepth(exceptionMappingConfig
                        .getExceptionClassName(), t);
                if (depth >= 0 && depth < deepest) {
                    deepest = depth;
                    result = exceptionMappingConfig.getResult();
                }
            }
        }

        return result;
    }
    public int getDepth(String exceptionMapping, Throwable t) {
        return getDepth(exceptionMapping, t.getClass(), 0);
    }
    private int getDepth(String exceptionMapping, Class exceptionClass,
                         int depth) {
        if (exceptionClass.getName().contains(exceptionMapping)) {
            // Found it!
            return depth;
        }
        // If we've gone as far as we can go and haven't found it...
        if (exceptionClass.equals(Throwable.class)) {
            return -1;
        }
        return getDepth(exceptionMapping, exceptionClass.getSuperclass(),
                depth + 1);
    }

}
