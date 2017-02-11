package com.baobaotao.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/10.
 */
public class MyRolesAuthorizationFilter extends AuthorizationFilter {
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        Subject subject = this.getSubject(servletRequest, servletResponse);
        String[] rolesArray = (String[])((String[])mappedValue);
        if(rolesArray != null && rolesArray.length != 0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            for (String role : roles) {
                if (subject.hasRole(role)) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
