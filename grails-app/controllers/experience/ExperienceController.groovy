package experience

import grails.plugin.springsecurity.annotation.Secured
import track.Cours

import java.text.DateFormat
import java.text.SimpleDateFormat

import ktbs.KtbsService
import track.User
import app.AuthenticationController


import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Trace

import defaults.Constants

class ExperienceController {

//	 def grailsApplication
//	 def versionexperience
//	 def springSecurityService
//
//	 KtbsService ktbsService
//	
//	
//	 
//	 @Secured(['ROLE_ADMIN','ROLE_USER'])
//    def index() { 
//		boolean  Signature
//		boolean Classic
//		boolean Dynamic
//		User user = springSecurityService.currentUser
//		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
//		def base = new Base (urlKTBSRoot + user.iduser+"/")
//		
//		// Test Signature trace 
//		Trace primarytraceins = new Trace (urlKTBSRoot + user.iduser+"/primarytraceInscription/")
//		Signature = primarytraceins.Exist();
//		
//		// Test Profil Classique
//		Trace primarytraceauth = new Trace (urlKTBSRoot + user.iduser+"/primarytraceAuthenClassic/")
//		Classic = primarytraceauth.Exist()
//		
//		//Test Profil dynalique
//		Trace primarytraceauthDynamic = new Trace (urlKTBSRoot + user.iduser+"/primarytraceAuthenDynamic/")
//		Dynamic = primarytraceauthDynamic.Exist()
//
//
//
//
//
//
////		 render(view: "/formation/home",model: [userInstance:user])
//
//		render(view: "/experience/index", model:[userInstance:user,Signature:Signature, Classic: Classic, Dynamic: Dynamic])
//	}
//
//
//
//	/**
//	 * 
//	 * @return interface Inscription
//	 */
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def createSignature () {
//		
//		User user = springSecurityService.currentUser
//		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
//		versionexperience = "S"
//		def base = new Base (urlKTBSRoot + user.iduser+"/")
//		
//		//create PrimaryTrace in not exist
////		Trace primarytraceins = new Trace (urlKTBSRoot + user.iduser+"/primarytraceInscription/")
////		def bol = primarytraceins.Exist();
////		println (bol)
////		if (bol == false)
////		{
////			println "start create"
////			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
////			System.out.println(dateFormat.format(new Date())); //2016/11/16 12:08:43
////		    primarytraceins = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceInscription",null)
////			primarytraceins.init()
////			//Trace primarytraceauth = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceAuthentification",null)
////			//primarytraceauth.init()
////			// create signature inscription
////			Trace traceprofilIns = profileUserService.createTraceProfil(base,primarytraceins,Constants.Trace_Profil)
////			Trace tracesignatureIns = signatureUserService.createTraceSignature (base,primarytraceins,Constants.Trace_SignatureClassic)
////			// create profil authentification
////			//Trace traceprofilAuth = profileUserService.createTraceProfil(base,primarytraceauth,primarytraceauth.get_Name()+Constants.Trace_Profil)
////			
////			// create signature dynamic
////			Trace tracesignatureDyn = signatureUserService.createTraceSignatureDynamic (base,primarytraceins,Constants.Trace_SignatureDynamic)
////		}
//		//Trace tracesignatureDyn = signatureUserService.createTraceSignatureDynamic (base,primarytraceins,primarytraceins.get_Name()+Constants.Trace_SignatureDynamic)
//		
//		render (view:"/game/gameInscription")
//	}
//	
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def experienceAuthClasic () {
//
//		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
//		User user  = springSecurityService.currentUser
//		def base = new Base (urlKTBSRoot + user.iduser+"/")
//		user.primaryTraceName = "primarytraceAuthenClassic"
//		user.signatureTrace = Constants.Trace_SignatureClassic
//		user.profilTraceName = Constants.Trace_ProfilClassic
//		versionexperience = "AC"
////		Trace primarytraceauth = new Trace (urlKTBSRoot + user.iduser+"/primarytraceAuthenClassic/")
////		println (primarytraceauth.Exist())
////		if (primarytraceauth.Exist() == false){
////			primarytraceauth = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceAuthenClassic",null)
////			primarytraceauth.init()
////			Trace traceprofilAuth = profileUserService.createTraceProfil(base,primarytraceauth,Constants.Trace_ProfilClassic)
////		}
//		
//		render (view:"/game/gameAuthentification")
//		
//	}
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def experienceAuthDynamic () {
//		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
//		User user = springSecurityService.currentUser
//		def base = new Base (urlKTBSRoot + user.iduser+"/")
//		versionexperience = "AD"
//		user.primaryTraceName ="primarytraceAuthenDynamic";
//		user.signatureTrace = Constants.Trace_SignatureDynamic
//		user.profilTraceName = Constants.Trace_ProfilDynamic
//		//Trace primarytraceauth = new Trace (urlKTBSRoot + user.iduser+"/primarytraceAuthenDynamic/")
////		if (primarytraceauth.Exist() == false){
////			primarytraceauth = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceAuthenDynamic",null)
////			primarytraceauth.init()
////			Trace traceprofilAuth = profileUserService.createTraceProfil(base,primarytraceauth,Constants.Trace_ProfilDynamic)
////		}
//		
//		render (view:"/game/gameAuthentification")
//	}
//	@Secured(['ROLE_ADMIN','ROLE_USER'])
//	def experienceParam () {
//		
//		render (  versionexperience)
//		
//	}
//	/**
//	 * 
//	 * @param type
//	 * @return
//	 * Create trace Signature Profil according to type
//	 */
////	@Secured(['ROLE_ADMIN','ROLE_USER'])
////	def createTrace () {
////		def type = params.type
////		User user = springSecurityService.currentUser
////		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
////		def base = new Base (urlKTBSRoot + user.iduser+"/")
////		
////		if (type.equalsIgnoreCase("Signature")){
////			Trace primarytraceins = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceInscription",null)
////			primarytraceins.init()
////			Trace traceprofilIns = profileUserService.createTraceProfil(base,primarytraceins,Constants.Trace_Profil)
////			Trace tracesignatureIns = signatureUserService.createTraceSignature (base,primarytraceins,Constants.Trace_SignatureClassic)
////			// create signature dynamic
////			Trace tracesignatureDyn = signatureUserService.createTraceSignatureDynamic (base,primarytraceins,Constants.Trace_SignatureDynamic)
////			
////		} else if (type.equalsIgnoreCase("Classic")){
////		
////		Trace primarytraceauth = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceAuthenClassic",null)
////		primarytraceauth.init()
////		Trace traceprofilAuth = profileUserService.createTraceProfil(base,primarytraceauth,Constants.Trace_ProfilClassic)
////		
////		}else if (type.equalsIgnoreCase("Dynamic")){
////		Trace primarytraceauthDyn = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"primarytraceAuthenDynamic",null)
////		primarytraceauthDyn.init()
////		Trace traceprofilAuthDyn = profileUserService.createTraceProfil(base,primarytraceauthDyn,Constants.Trace_ProfilDynamic)
////		
////		}
////		redirect (view:"/experience/index")
//	}
}
