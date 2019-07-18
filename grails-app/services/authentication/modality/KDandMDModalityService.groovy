package authentication.modality

import ktbs.KtbsService;
import track.User

import com.ignition_factory.ktbs.bean.Base;
import com.ignition_factory.ktbs.bean.Trace;

import grails.transaction.Transactional
import defaults.Constants

@Transactional
class KDandMDModalityService {

    KeystrokeDynamicService  keystrokeDynamicService
	MouseDynamicService   mouseDynamicService
	KtbsService ktbsService
	EnvironnementAndSessionApprentissageService environnementAndSessionApprentissageService
	
	/**
	 * 
	 * @param base
	 * @param primarytrace
	 * @param name
	 * @return
	 */
	Trace createTraceProfilKDandMD (Base base, Trace primarytrace, String name) {
		// keysroke 
		Trace traceprofilkeystroke = keystrokeDynamicService.createProfil(base,primarytrace,primarytrace.get_Name()+ "_" + Constants.Trace_Profile_Keystroke)
		// mouse dynamic 
		mouseDynamicService.extractFeature(base,primarytrace)
		Trace traceprofilmouse = mouseDynamicService.createProfil(base,primarytrace,primarytrace.get_Name()+ "_" + Constants.Trace_Profile_Mouse)
		// fusion 
		String methode = "fusion"
		String [] tracesources = [traceprofilkeystroke.get_Name()+"/",traceprofilmouse.get_Name()+"/"]
		String[] parametres = []
		Trace traceprofil = ktbsService.createTransformedTrace(base,name ,methode , tracesources, parametres)
		return traceprofil
	}
	/**
	 * 
	 * @param base
	 * @param primarytrace
	 * @param name
	 * @return
	 */
	Trace createTraceSignatureKDandMD (Base base,Trace primarytrace, String name) {
		// keystroke
		Trace tracesignaturekeystroke  = keystrokeDynamicService.createSignature(base,primarytrace, primarytrace.get_Name()+Constants.Trace_Profile_Keystroke,primarytrace.get_Name()+Constants.Trace_Signature_Keystroke,null)
		// mouse dynamic
		Trace tracesignaturemouse = mouseDynamicService.createSignature(base,primarytrace,primarytrace.get_Name()+Constants.Trace_Profile_Mouse,primarytrace.get_Name()+Constants.Trace_Signature_Mouse,null)
		// fusion
		String methode = "fusion"
		String [] tracesources = [tracesignaturekeystroke.get_Name()+"/",tracesignaturemouse.get_Name()+"/"]
		String[] parametres = []
		Trace traceprofil = ktbsService.createTransformedTrace(base, name ,methode , tracesources, parametres)
		return traceprofil
		
		
	}
	/**
	 * 
	 * @param user
	 * @return
	 */
	def CalculTrustKDandMD (User user) {
		
	}
}
