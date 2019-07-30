package track.auth

import org.springframework.context.ApplicationListener
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.session.SessionDestroyedEvent
import org.springframework.security.core.userdetails.UserDetails
import org.json.JSONObject;
import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Trace

import grails.plugin.cookie.CookieService

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import ktbs.KtbsService;


import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.springframework.security.core.Authentication
import org.springframework.web.context.request.RequestContextHolder
import defaults.Constants;
import track.Cours
import track.User
import authentication.modality.KDandMDModalityService



public class MyLogoutEventListener implements ApplicationListener<SessionDestroyedEvent> { 
	def KtbsService
	def EventService
	def EnvironnementAndSessionApprentissageService
	def KDandMDModalityService
   // @Override 
	 public void onApplicationEvent(SessionDestroyedEvent event){
		 
		 
	User.withTransaction {
		 
         println("logout event listener"); 
		 Date date = new Date()
		 def currentRequest = RequestContextHolder.requestAttributes
		 // get current user 
		 //def user = User.findByEmail(event.authentication.principal.username);
		 def user
		 List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
		 UserDetails ud;
		 for (SecurityContext securityContext : lstSecurityContext)
		 {
			 ud = (UserDetails)  securityContext.getAuthentication().getPrincipal();
			
             user = User.findByEmail(ud.getUsername());
		 }
		 EventService.disconnectEvent (currentRequest, user)
		
		 
		 /** calculer trust ppour chaque modalité */
		  /* AuthApprentissage */
		// EnvironnementAndSessionApprentissageService.ClusteringAndCalculTrustEnvApp(user,currentRequest)
		
		 
		  /** Auth KDAnd MD**/
		 Cours cours = Cours.findById ("1")
		 KDandMDModalityService.CalculTrustKDandMD(user , cours , currentRequest)
		 	
		 
	}
	   
	   
	   
	 
	    }
}