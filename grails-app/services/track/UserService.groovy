package track

import grails.transaction.Transactional
import ktbs.KtbsService
import track.auth.GenerateUUI
import authentification.collect.EventService
import defaults.Constants;

//import com.ignition_factory.ktbs.bean.Base
//import com.ignition_factory.ktbs.bean.Trace
//import authentication.profil.ProfileUserService
//import authentication.signature.SignatureUserService

@Transactional
class UserService {
	
	UserRoleService userRoleService
	EventService eventService
	
//	KtbsService ktbsService 
//	SignatureUserService  signatureUserService
//	ProfileUserService profileUserService
	 
	public void createUser(User userInstance)
	{
		GenerateUUI  generateuui = new GenerateUUI()
		
		userInstance.iduser = generateuui.generate(userInstance.firstname, userInstance.lastname)
		userInstance.modelTraceName = Constants.modelprimary
		
		userInstance.save flush:true
		userRoleService.assignRole ("ROLE_USER", userInstance)
		
		
		eventService.inscriptionEvent(userInstance) 
		
//		// create Basede trace user
//		Base base = ktbsService.createBase (userInstance.iduser, "base user ")
//		// create primary Trace for envApprentissage 
//		Trace primarytrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,Constants.PrimarytraceEnv,null)
//		// create profil Trace for envApprentissage
//		profileUserService.createTraceProfilEnv (base, primarytrace, Constants.PrimarytraceEnvProfil)
//		// create primary Trace for envApprentissage
//		Trace signaturetrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,Constants.PrimarytraceEnvSignature,null)
		
		
		
		
		
		


		
		
		
		
		
		// create PrimaryTrace
		//userInstance.primaryTraceName = Constants.Primarytrace
		//userInstance.signatureTrace = Constants.Trace_Signature
		//userInstance.profilTraceName = Constants.Trace_Profil
		
		//Trace primarytrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,Constants.Primarytrace)
		//primarytrace.init()
		// create profil and signature trace 
		//Trace traceprofil = profileUserService.createTraceProfil(base,primarytrace,Constants.Trace_Profil)
		//Trace tracesignature = signatureUserService.createTraceSignature (base,primarytrace,Constants.Trace_Signature)
		
	}
}
