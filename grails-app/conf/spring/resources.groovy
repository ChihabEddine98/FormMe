// Place your Spring DSL code here
import grails.plugin.springsecurity.SpringSecurityUtils
import track.auth.LoginEventListener
import track.auth.MyLogoutEventListener

beans = {
	authenticationSuccessHandler(track.auth.MyAuthSuccessHandler) {
		def conf = SpringSecurityUtils.securityConfig
		requestCache = ref('requestCache')
		defaultTargetUrl = conf.successHandler.defaultTargetUrl
		alwaysUseDefaultTargetUrl = conf.successHandler.alwaysUseDefault
		targetUrlParameter = conf.successHandler.targetUrlParameter
		useReferer = conf.successHandler.useReferer
		redirectStrategy = ref('redirectStrategy')
		adminUrl = "/admin/"
		//userUrl = "/game/game"
		userUrl = "/formation/home"

}
	loginEventListener(LoginEventListener){
		bean ->
        bean.autowire = 'byName'
       
    }
	myLogoutEventListener(MyLogoutEventListener){
		bean ->
        bean.autowire = 'byName'
       
    }
}