package authentication.signature

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.Trace
import grails.transaction.Transactional
import ktbs.KtbsService

import authentication.modality.KeystrokeDynamicService
import authentication.modality.MouseDynamicService
import defaults.Constants
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional
class SignatureUserService {
	KeystrokeDynamicService  keystrokeDynamicService
	MouseDynamicService   mouseDynamicService
	KtbsService ktbsService

//    Trace createTraceSignature (Base base,Trace primarytrace, String name) {
//		// keystroke
//		Trace tracesignaturekeystroke  = keystrokeDynamicService.createSignature(base,primarytrace, primarytrace.get_Name()+Constants.Trace_Profile_Keystroke,primarytrace.get_Name()+Constants.Trace_Signature_Keystroke,null)
//		// mouse dynamic 
//		Trace tracesignaturemouse = mouseDynamicService.createSignature(base,primarytrace,primarytrace.get_Name()+Constants.Trace_Profile_Mouse,primarytrace.get_Name()+Constants.Trace_Signature_Mouse,null)
//		// fusion 
//		String methode = "fusion"
//		String [] tracesources = [tracesignaturekeystroke.get_Name()+"/",tracesignaturemouse.get_Name()+"/"]
//		String[] parametres = []
//		Trace traceprofil = ktbsService.createTransformedTrace(base, name ,methode , tracesources, parametres)
//		return traceprofil
//		
//		
//	}
	Trace createTraceSignatureDynamic (Base base,Trace primarytrace, String name) {
		// keystroke
		//Trace tracesignaturekeystroke  = keystrokeDynamicService.createSignature(base,primarytrace, primarytrace.get_Name()+Constants.Trace_Profile_Keystroke,primarytrace.get_Name()+Constants.Trace_Signature_Keystroke)
		// mouse dynamic
		//Trace tracesignaturemouse = mouseDynamicService.createSignature(base,primarytrace,primarytrace.get_Name()+Constants.Trace_Profile_Mouse,primarytrace.get_Name()+Constants.Trace_Signature_Mouse)
		
		// fusion trace profil limit 
		String methode = "fusion"
		String [] tracesources = [primarytrace.get_Name()+"_TraceProfileKeystrokeLimit/",primarytrace.get_Name()+"_TraceProfileMouseLimit/"]
		String[] parametres = []
		Trace traceprofillimit = ktbsService.createTransformedTrace(base, primarytrace.get_Name()+"traceprofillimit" ,methode , tracesources, parametres)
		traceprofillimit.init()
		println (traceprofillimit.get_origin())
		// create trace obsel dynamic 
		Trace TraceObselDynamic = ktbsService.createPrimaryTrace (base,"modelprimary",primarytrace.get_Name()+"TraceObselDynamic", traceprofillimit.get_origin())
		// fussion trace Before Signature
		String [] tracesources1 = [TraceObselDynamic.get_Name()+"/",traceprofillimit.get_Name()+"/"]
		Trace traceprofilfussion = ktbsService.createTransformedTrace(base, "traceprofilfussion" ,methode , tracesources1, parametres)
		// signature 
		Trace tracesignaturekeystroke  = keystrokeDynamicService.createSignature(base,primarytrace, primarytrace.get_Name()+Constants.Trace_Profile_Keystroke,primarytrace.get_Name()+Constants.Trace_Signature_Keystroke,traceprofilfussion)
		// mouse dynamic
		Trace tracesignaturemouse = mouseDynamicService.createSignature(base,primarytrace,primarytrace.get_Name()+Constants.Trace_Profile_Mouse,primarytrace.get_Name()+Constants.Trace_Signature_Mouse,traceprofilfussion)
		// trace Signature 
		String [] tracesources2 = [tracesignaturekeystroke.get_Name()+"/",tracesignaturemouse.get_Name()+"/"]
		Trace traceSignature = ktbsService.createTransformedTrace(base, name ,methode , tracesources2, parametres)
		return traceSignature
	}
	Collection<Obsel> getListObsel (Base base, Integer begin, Integer end, String name) {
		Trace signaturetrace = new Trace (base.get_uri()+ name)
		Collection<Obsel> obsel = signaturetrace.list_obsels (begin, end, null)
		return obsel
	}
	
	Obsel getObselWithType (String type, JSONObject obAttribute, Collection<Obsel> obselsignature) {
		Obsel obsel = null
		for (Obsel obs : obselsignature){
			
			if (obs.get_obsel_type().getTypeId().equalsIgnoreCase(type)){
				if (obAttribute) {
					Iterator iter =obAttribute.keys()
					if (obAttribute.length().equals(1) ){
						String key = (String)iter.next()
						String value = obAttribute.getString(key)
						if (obs.get_attribute (key).getValue().equalsIgnoreCase(value)){
							return obs ;
							break;
						}
					} else if (obAttribute.length().equals(2)) {
						String key = (String)iter.next()
						String value = obAttribute.getString(key)
						
						//Iterator iter1 = iter.next()
						String key1 = (String)iter.next()
						String value1 = obAttribute.getString(key1)
						if ((obs.get_attribute (key).getValue().equalsIgnoreCase(value))&&(obs.get_attribute (key1).getValue().equalsIgnoreCase(value1))){
							return obs ;
							break;
					 }
					}
//					while(iter.hasNext()){
//					
//						String key = (String)iter.next()
//						String value = obAttribute.getString(key)
//						println ("key+value"+key+value)
//						if (obs.get_attribute (key).getValue().equalsIgnoreCase(value)){
//							println ("iter.hasNext"+iter.hasNext())
//							if ( ! (iter.hasNext())){
//							obsel = obs
//							return obsel
//							}
//						}
//						else
//						{
//							return null
//						}
//
//					}
				}
				else
				{
					return obsel
				}
			}
			
		}
		
	}
}
