package action.base;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Archibald on 1/5/2017.
 */
public class BaseAction implements ServletRequestAware {
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }
    public HttpSession getSession(){
        return request.getSession();
    }
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
}
