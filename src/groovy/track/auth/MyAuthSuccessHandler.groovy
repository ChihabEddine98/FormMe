package track.auth

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import grails.plugin.springsecurity.SpringSecurityUtils


class MyAuthSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler  {
	
	@Override
    protected String determineTargetUrl(HttpServletRequest request,
                                            HttpServletResponse response) {
											
		println ("here")
         
        boolean hasAdmin = SpringSecurityUtils.ifAllGranted("ROLE_ADMIN");
        boolean hasUser = SpringSecurityUtils.ifAllGranted("ROLE_USER");
         
        if(hasAdmin){
            return adminUrl;
        }else if (hasUser){
            return userUrl;
        }else{
            return super.determineTargetUrl(request, response);
        }
    }
 
    private String userUrl;
    private String adminUrl;
     
    public void setUserUrl(String userUrl){
        this.userUrl = userUrl;
    }
     
    public void setAdminUrl(String adminUrl){
        this.adminUrl = adminUrl;
    }

}
