package game

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Holders

import javax.servlet.http.Cookie

import org.springframework.web.context.request.RequestContextHolder



class GameController {
	def springSecurityService
	
	@Secured(['ROLE_ADMIN','ROLE_USER'])
    //@Secured('ROLE_USER')
    def game() { 
		
		render(view: "/game/game")
	}
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def setCookies() {
//		
//		def user = springSecurityService.currentUser
//		println ("set Cookies game");
//		def currentRequest = RequestContextHolder.requestAttributes
//		
//		def grailsApplication = Holders.getGrailsApplication()
//		def UrlKTBSRoot =  grailsApplication.config.UrlKTBSRoot;
//		String urlBase = URLEncoder.encode(UrlKTBSRoot+user.iduser+"/", "UTF-8") ;
//		String urlModel = URLEncoder.encode(UrlKTBSRoot+user.iduser+"/"+user.modelTraceName+"#", "UTF-8") ;
//		Cookie cookieBaseURI = new Cookie('BaseURI',urlBase)
//		Cookie cookieTraceName = new Cookie('TraceName',user.primaryTraceName+"/")
//		Cookie cookieModelURI = new Cookie('ModelURI',urlModel)
//		cookieBaseURI.setPath("/")
//		response.addCookie cookieBaseURI
//		response.addCookie cookieTraceName
//		response.addCookie cookieModelURI
//		
//		//decisionService.authenticate(UrlKTBSRoot+user.iduser+"/", user.profilTraceName, user.signatureTrace)
//		render""
//	}
}
