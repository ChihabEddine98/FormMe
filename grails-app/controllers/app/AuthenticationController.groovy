package app

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Holders

import javax.servlet.http.Cookie

import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.web.context.request.RequestContextHolder

import track.User


class AuthenticationController {
	def springSecurityService
	def grailsApplication
	
    
//	def index() { }
//	
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def setCookies() {
//		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
//		User user = springSecurityService.currentUser
//		println ("set Cookies authentification")
//		def currentRequest = RequestContextHolder.requestAttributes
//		
//		def grailsApplication = Holders.getGrailsApplication()
//		def UrlKTBSRoot =  grailsApplication.config.UrlKTBSRoot
//		String urlBase = URLEncoder.encode(urlKTBSRoot + user.iduser+"/", "UTF-8")
//		String urlModel = URLEncoder.encode(urlKTBSRoot + user.iduser+"/"+user.modelTraceName+"#", "UTF-8")
//		Cookie cookieBaseURI = new Cookie('BaseURI',urlBase)
//		Cookie cookieTraceName = new Cookie('TraceName',"Primarytrace/")
//		
//		Cookie cookieModelURI = new Cookie('ModelURI',urlModel)
//		cookieBaseURI.setPath("/")
//		cookieTraceName.setPath("/")
//		cookieModelURI.setPath("/")
//		response.addCookie cookieBaseURI
//		response.addCookie cookieTraceName
//		response.addCookie cookieModelURI
//		
//		//decisionService.authenticate(UrlKTBSRoot+user.iduser+"/", user.profilTraceName, user.signatureTrace)
//		render""
//	}
//	@Secured('permitAll')
//	def authentifier () {
//		def parameters = params
//		def user = springSecurityService.currentUser
//		def urlKTBSRoot =  grailsApplication.config.grails.ktbs.urlKTBSRoot
//
//		//decisionService.authenticate(urlKTBSRoot + user.iduser+"/", user.profilTraceName, user.signatureTrace, user)
//		if (parameters.params.equals("AD"))
//		{	float  trust = decisionService.authenticate(user, user, "DYNAMIC")
//			render trust
//		}
//		else if (parameters.params.equals("AC")) {
//			float  trust = decisionService.authenticate(user, user, "CLASSIC")
//			render trust
//		}
//		render""
//	}
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def authentifierclassic () {
//		def parameters = params
//		JSONObject objResultAuth
//		
//		def user = springSecurityService.currentUser
//
//		println (parameters.params)
//		if (parameters.params.equals("v2")){
//			objResultAuth = decisionService.authenticate(user, user,"CLASSIC")
//		}else if (parameters.params.equals("v3")) {
//			objResultAuth = decisionService.authenticate(user, user,"DYNAMIC")
//		}
//		render (objResultAuth)
//	}
}
