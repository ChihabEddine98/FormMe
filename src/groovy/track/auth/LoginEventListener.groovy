package track.auth

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
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

import defaults.Constants;
import track.User
import track.UserRole
import authentication.profil.ProfileUserService
import authentication.modality.EnvironnementAndSessionApprentissageService
import authentification.collect.EventService
class LoginEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
	
	
	
	def KtbsService
	def CookieService
	def grailsApplication
	def ProfileUserService
	def EnvironnementAndSessionApprentissageService
	 EventService eventService
	void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
	{
		
		User.withTransaction
		
			
			{
				
				println("login event listener");
				//CookieService cookieService
				
				RequestAttributes currentRequest = RequestContextHolder.requestAttributes
				def user = User.findByEmail(event.authentication.principal.username);
				boolean estAdmin= UserRole.findByUser(user).role.authority.equals("ROLE_ADMIN")
				println("est admin "+estAdmin)
				if (!estAdmin)
				{
					eventService.connectEvent (currentRequest,user)
				} 

			
			}	
	}
	
	
	
	
	}
