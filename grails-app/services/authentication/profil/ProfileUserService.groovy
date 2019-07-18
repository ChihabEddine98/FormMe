package authentication.profil

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.Trace

import grails.transaction.Transactional
import ktbs.KtbsService
import authentication.modality.KeystrokeDynamicService
import authentication.modality.MouseDynamicService
import authentication.modality.EnvironnementAndSessionApprentissageService
import defaults.Constants;

@Transactional
class ProfileUserService {
//	KeystrokeDynamicService  keystrokeDynamicService
//	MouseDynamicService   mouseDynamicService
//	KtbsService ktbsService
//	EnvironnementAndSessionApprentissageService environnementAndSessionApprentissageService
//	
//	Trace createTraceProfil (Base base, Trace primarytrace, String name) {
//		// keysroke 
//		Trace traceprofilkeystroke = keystrokeDynamicService.createProfil(base,primarytrace,primarytrace.get_Name()+Constants.Trace_Profile_Keystroke)
//		// mouse dynamic 
//		mouseDynamicService.extractFeature(base,primarytrace)
//		Trace traceprofilmouse = mouseDynamicService.createProfil(base,primarytrace,primarytrace.get_Name()+Constants.Trace_Profile_Mouse)
//		// fusion 
//		String methode = "fusion"
//		String [] tracesources = [traceprofilkeystroke.get_Name()+"/",traceprofilmouse.get_Name()+"/"]
//		String[] parametres = []
//		Trace traceprofil = ktbsService.createTransformedTrace(base,name ,methode , tracesources, parametres)
//		return traceprofil
//	}
	
	
//	Trace createTraceProfilEnv (Base base, Trace primarytrace, String tracename) {
//	
//		Trace traceprofil = environnementAndSessionApprentissageService.createTraceProfilEnv (base, primarytrace)
//        return traceprofil
//		
//	}
	
	
//	Collection<Obsel> getListObsel (Base base, Integer begin, Integer end, String name) {
//		
//		Trace profiltrace = new Trace (base.get_uri()+name)
//		Collection<Obsel> obsel = profiltrace?.list_obsels (begin, end, null)
//		return obsel
//	}
}
