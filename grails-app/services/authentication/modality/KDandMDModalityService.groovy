package authentication.modality

import ktbs.KtbsService;

import org.json.JSONArray
import org.json.JSONObject
import org.springframework.web.context.request.RequestAttributes;

import track.AuthenticationResult
import track.Cours
import track.ObselTrust
import track.User
import track.UserAuthentificationFormation

import com.ignition_factory.ktbs.bean.Base;
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.Trace;

import grails.transaction.Transactional
import defaults.Constants
import authentication.AuthenticationStatisticsService

@Transactional
class KDandMDModalityService {

    KeystrokeDynamicService  keystrokeDynamicService
	MouseDynamicService   mouseDynamicService
	KtbsService ktbsService
	def grailsApplication
	EnvironnementAndSessionApprentissageService environnementAndSessionApprentissageService
	AuthenticationStatisticsService  authenticationStatisticsService
	
	
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
		Trace tracesignaturekeystroke  = keystrokeDynamicService.createSignature(base,primarytrace, primarytrace.get_Name()+ "_" +Constants.Trace_Profile_Keystroke,primarytrace.get_Name()+Constants.Trace_Signature_Keystroke,null)
		// mouse dynamic
		Trace tracesignaturemouse = mouseDynamicService.createSignature(base,primarytrace,primarytrace.get_Name()+ "_" +Constants.Trace_Profile_Mouse,primarytrace.get_Name()+Constants.Trace_Signature_Mouse,null)
		// fusion
		String methode = "fusion"
		String [] tracesources = [tracesignaturekeystroke.get_Name()+"/",tracesignaturemouse.get_Name()+"/"]
		String[] parametres = []
		Trace traceprofil = ktbsService.createTransformedTrace(base, name ,methode , tracesources, parametres)
		return traceprofil
		
		
	}
	/**
	 * calculet distance entre Obsel profil and Trace Signature
	 * @param obs
	 * @param obselsignature
	 * @return
	 */
	float calculDistance (Obsel obs, Collection<Obsel> obselsignature ) {
		
		float distance = -1
		// for kK_PressRelease
		if (obs.get_obsel_type().getTypeId().equalsIgnoreCase(Constants.type_K_PR)){
			
			JSONObject obAttribute = new JSONObject()
			obAttribute.put(Constants.att_keySource,obs?.get_attribute (Constants.att_keySource)?.getValue())
			Obsel obssignature = ktbsService?.getObselWithType (Constants.type_K_MPR,obAttribute,obselsignature)
			if (obssignature){
				float mean = Float.parseFloat (obssignature?.get_attribute (Constants.att_mean)?.getValue())
				float deviation = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviation)?.getValue())
				float time = Float.parseFloat (obs?.get_attribute (Constants.att_time)?.getValue())
				distance = Math.abs ((time - mean)/deviation )
				//trust = calculateCoefficient(distance, obs, trust)
			}
		}
		// for K_PP et K_RR et K_RP
		else if (obs?.get_obsel_type().getTypeId().equalsIgnoreCase(Constants.type_K_PP) || obs?.get_obsel_type().getTypeId().equalsIgnoreCase(Constants.type_K_RR) || obs?.get_obsel_type().getTypeId().equalsIgnoreCase(Constants.type_K_RP)){
			
			String partType = obs.get_obsel_type().getTypeId().substring(2,obs?.get_obsel_type()?.getTypeId().length())
			JSONObject obAttribute = new JSONObject();
			obAttribute.put(Constants.att_keySource,obs.get_attribute (Constants.att_keySource)?.getValue());
			obAttribute.put(Constants.att_keyDestination,obs.get_attribute (Constants.att_keyDestination)?.getValue());
			
			Obsel obssignature = ktbsService?.getObselWithType ("K_M"+partType,obAttribute,obselsignature);
			
			if (obssignature){
				float mean = Float.parseFloat (obssignature?.get_attribute (Constants.att_mean)?.getValue());
				float deviation = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviation)?.getValue());
				float time = Float.parseFloat (obs?.get_attribute (Constants.att_time)?.getValue());
				distance = Math.abs ((time - mean)/deviation )
				//trust = calculateCoefficient(distance, obs, trust)
			}
		}
		//for M_MS and M_DD
		else if (obs?.get_obsel_type()?.getTypeId().equalsIgnoreCase(Constants.type_M_MS) || obs?.get_obsel_type()?.getTypeId().equalsIgnoreCase(Constants.type_M_DD)) {
			String partType = obs.get_obsel_type()?.getTypeId().substring(2,obs.get_obsel_type()?.getTypeId().length())
			JSONObject obAttribute = new JSONObject();
			obAttribute.put(Constants.att_direction,obs?.get_attribute (Constants.att_direction)?.getValue());
			Obsel obssignature = ktbsService?.getObselWithType ("M_M"+partType,obAttribute,obselsignature);
			if (obssignature){
				float meanTime = Float.parseFloat (obssignature?.get_attribute (Constants.att_meanTime)?.getValue());
				float deviationTime = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviationTime)?.getValue());
				float timeElapsed = Float.parseFloat (obs?.get_attribute (Constants.att_timeElapsed)?.getValue());

				float meanacceleration = Float.parseFloat (obssignature?.get_attribute (Constants.att_meanacceleration)?.getValue());
				float deviationacceleration = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviationacceleration)?.getValue());
				float acceleration = Float.parseFloat (obs?.get_attribute (Constants.att_acceleration)?.getValue());

				float meanSpeed = Float.parseFloat (obssignature?.get_attribute (Constants.att_meanSpeed)?.getValue());
				float deviationSpeed = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviationSpeed)?.getValue());
				float actualSpeed = Float.parseFloat (obs.get_attribute (Constants.att_actualSpeed)?.getValue());

				float meancurveAcceleration = Float.parseFloat (obssignature.get_attribute (Constants.att_meancurveAcceleration)?.getValue());
				float deviationcurveAcceleration = Float.parseFloat(obssignature.get_attribute (Constants.att_deviationcurveAcceleration)?.getValue());
				float curveAcceleration = Float.parseFloat (obs?.get_attribute (Constants.att_curveAcceleration)?.getValue());

				float meancurveLength = Float.parseFloat (obssignature?.get_attribute (Constants.att_meancurveLength)?.getValue());
				float deviationcurveLength = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviationcurveLength)?.getValue());
				float curveLength = Float.parseFloat (obs?.get_attribute (Constants.att_curveLength)?.getValue());

				float meantravelledDistance = Float.parseFloat (obssignature?.get_attribute (Constants.att_meantravelledDistance)?.getValue());
				float deviationtravelledDistance = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviationtravelledDistance)?.getValue());
				float travelledDistance = Float.parseFloat (obs?.get_attribute (Constants.att_travelledDistance)?.getValue());

				float postdistance = Math.abs ((timeElapsed - meanTime)/deviationTime ) + Math.abs ((acceleration - meanacceleration)/deviationacceleration ) + Math.abs ((actualSpeed - meanSpeed)/deviationSpeed ) + Math.abs ((curveAcceleration - meancurveAcceleration)/deviationcurveAcceleration ) +Math.abs ((curveLength - meancurveLength)/deviationcurveLength ) +Math.abs ((travelledDistance - meantravelledDistance)/deviationtravelledDistance ) ;
				distance = postdistance/6;
				//trust = calculateCoefficient(distance, obs, trust)
			}

		}
		else if (obs?.get_obsel_type()?.getTypeId().equalsIgnoreCase(Constants.type_M_BPR)) {

			Obsel obssignature = ktbsService?.getObselWithType (Constants.type_M_MBPR,null,obselsignature);
			if (obssignature){
				float meanTime = Float.parseFloat (obssignature?.get_attribute (Constants.att_meanTime)?.getValue());
				float deviationTime = Float.parseFloat(obssignature?.get_attribute (Constants.att_deviationTime)?.getValue());
				float duration = Float.parseFloat (obs?.get_attribute (Constants.att_duration)?.getValue());
				distance = Math.abs ((duration - meanTime)/deviationTime );
				//trust = calculateCoefficient(distance, obs, trust)
			}

		}
		return distance
	}
	
	
	/**
	 * Function to calculate the trust
	 * @param distance
	 * @param obs
	 * @param previousCalculatedTrust
	 * @param type
	 * @param urlBaseSignature
	 * @return
	 */
	def calculateCoefficient(float distance, Obsel obs, float previousCalculatedTrust, String urlBaseSignature) {
			float trust = previousCalculatedTrust
			

			if (distance <17){
				trust = trust + 1.5 -distance
			}
			else if(distance >= 17)
			{
				//println (obs.get_id()+ ";" + obs.get_obsel_type().getTypeId()+ ";" + distance + ";" + trust + " DISTANCE TOO BIG")
			}
			
//			if (distance <5 ){
//			if (type.equalsIgnoreCase("DYNAMIC") ){
//					Trace trace = new Trace(urlBaseSignature+"primarytraceInscriptionTraceObselDynamic/")
//					trace.PostObsel(obs)
//				}
//			}
			
			return trust
		}

	
	/**
	 * 
	 * @param user
	 * @return
	 */
	def CalculTrustKDandMD (User user , Cours cours , RequestAttributes currentRequest) {
		
		println("calcul Trust KD and MD"); 
		// init variable
		
		float trust
		float distance
		Collection<Obsel> obselsignature
		Collection<Obsel> obselprofil
		AuthenticationResult authenticationResult
		Object lastBeginObsel
		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		List<ObselTrust> listObselTrust = new ArrayList<ObselTrust>()
		// initial value (useful for calculateCoefficient which is based on last trust value calculated)
	   
	   JSONArray items = new JSONArray()
	   JSONObject objResultAuth = new JSONObject()
	   JSONObject  lastobsel = new JSONObject()
		
		// init Trace BAse
		String urlBaseProfile = urlKTBSRoot + user.iduser + "/"
		String urlBaseSignature = urlKTBSRoot + user.iduser + "/"
		
		// init Trace BAse
		String urlBaseUserProfile = urlKTBSRoot + user.iduser + "/"
		String urlBaseUserSignature = urlKTBSRoot + user.iduser + "/"
		
		Base baseProfile = new Base (urlBaseUserProfile)
		Base baseSignature = new Base (urlBaseUserSignature)
		
		// init Trace Profil
		 def TraceprofileName = "Primarytracejava1_Trace_ProfilKDMD"
		 //"primarytraceAuthentificationTrace_Profil"
	   // Constants.Trace_ProfilDynamic
	   Trace traceProfil = new Trace (urlKTBSRoot + user.iduser + "/"+TraceprofileName+"/")
	  
		//init Trace Signature
	    def TracesignatureName = "Primarytracejava1_Trace_SignatureKDMD"
		//"primarytraceInscriptionTrace_Signature"
	   //Constants.Trace_SignatureDynamic
	   Trace traceSignature = new Trace (urlKTBSRoot + user.iduser + "/"+TracesignatureName+"/")
	   
	   // Begin Calcul Trust
	   
	   int FirstObsel ;
	   
	   boolean userAuthbooleaan = UserAuthentificationFormation.findWhere (user : user, cours : cours ) != null
	   UserAuthentificationFormation userAuth
	   if (userAuthbooleaan){
		   println ("second authentication ")
		   userAuth = UserAuthentificationFormation.findWhere (user : user, cours : cours )
		   trust = userAuth.trustBIOMETRIC ;
		   println ("ancien Trust " + trust);
		   println (userAuth.lastObselProfilBIOMETRIC);
		   obselprofil = ktbsService?.getListObsel (baseProfile, userAuth.lastObselProfilBIOMETRIC, null,TraceprofileName)
		   lastBeginObsel = ktbsService.getIdLastObsel(obselprofil)
		   FirstObsel=0
		   }
       else { 
		   System.out.println("first authentication ")
		    userAuth = new UserAuthentificationFormation (user : user, cours : cours )
		   trust = 0
		  
			// Get Obsel From Profil
		   obselprofil = ktbsService?.getListObsel (baseProfile, "0", null,TraceprofileName)
		   println ( obselprofil.toString())
		 
			lastBeginObsel = ktbsService.getIdLastObsel(obselprofil)
			FirstObsel = 5 ;
	   }
	   
	   obselsignature = ktbsService?.getListObsel (baseSignature, null, null,TracesignatureName)
	   authenticationResult = new AuthenticationResult(userProfile: user,userSignature: user, start: new Date(),typeModalitie: "BIOMETRIC" , typeAuth : "System",sessionId :currentRequest.getSessionId() )
	   
//	   boolean authResult = AuthenticationResult.findWhere (userProfile : user, userSignature : user , type: "BIOMETRIC" ) != null
//	   if (authResult){
//		   authenticationResult = AuthenticationResult.findWhere (userProfile : user, userSignature : user , type: "BIOMETRIC" )
//		  }
//	   else {
//		   authenticationResult = new AuthenticationResult(userProfile: user,userSignature: user, start: new Date(), type: "BIOMETRIC")
//		  }
	   
	 
	   println ("trust Initial"+trust)
	   				
	   for (int i=FirstObsel ;i < obselprofil.size(); i++) {
	   		Obsel obs = obselprofil[i]
	   		distance = calculDistance (obs, obselsignature)
			   println ("distance obsel id " + obs.get_id() +"est égale = "+ distance)
	   		if(distance >= 0){
	   						trust = calculateCoefficient(distance, obs, trust, urlBaseSignature)
	   						JSONObject Obj = new JSONObject();
	   						Obj.put("id_action", obs.get_id())
	   						Obj.put("type_action", obs.get_obsel_type()?.getTypeId())
	   						Obj.put("begin", obs.get_begin())
	   						Obj.put("distance", distance)
	   						Obj.put ("end",obs.get_end())
	   						Obj.put("trust", trust)
	   						items.put(Obj)
	   
	   						ObselTrust obselTrust = new ObselTrust(idAction: obs.get_uri(), typeAction: obs.get_obsel_type()?.getTypeId(), distance: distance, trust: trust, begin: obs.get_begin(), end: obs.get_end())
	   						authenticationResult.addToObselTrusts(obselTrust)
	   						authenticationResult.save() // authenticationResult is the owner of ObselTrust. Then cascade are done from the owner authenticationResult to the ObselTrust childs.
	   						
							 //println (items.toString())  ;
							objResultAuth.put("result", items)
	   
	   						authenticationStatisticsService.calculateStats(authenticationResult)
	   					}
	   }
	   
	   
	  // save user authentication
	   // change TrustKDandMD add TrustEnv and TrustAct add Trust CompApp
	   // Same for 
	   
	   authenticationResult.trustBiometric = trust;
	   authenticationResult.save()
		   
		   // save AncienTrust In list
		   JSONArray ListTrustBiometricJson = new JSONArray (userAuth.listTrustBiometric)
		   JSONObject Objtrust = new JSONObject();
		   Objtrust.put("sessionID", currentRequest.getSessionId())
		   Objtrust.put("trust", trust)
		   ListTrustBiometricJson.put(Objtrust);
		   userAuth.listTrustBiometric =  ListTrustBiometricJson.toString();
		   
		   userAuth.trustBIOMETRIC = trust
		   userAuth.lastObselProfilBIOMETRIC = lastBeginObsel.toString()
		    userAuth.addToAuthenticationResult(authenticationResult)
			userAuth.save();
		  
		   
	 
	 
	   				
	 println ("trust final"+trust)
	   				//return trust
	 return objResultAuth ;
	 }
	   
/**
 * 
 * @param user1
 * @param user2
 * @return
 */
	def CalculTrustKDandMDForTwoUSer (User userProfil,User userSignature) {
		
		println("calcul Trust KD and MD Manuel For Two Users");
		// init variable
		
		float trust
		float distance
		Collection<Obsel> obselsignature
		Collection<Obsel> obselprofil
		AuthenticationResult authenticationResult
		int lastBeginObsel
		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		List<ObselTrust> listObselTrust = new ArrayList<ObselTrust>()
		// initial value (useful for calculateCoefficient which is based on last trust value calculated)
	   
	   JSONArray items = new JSONArray()
	   JSONObject objResultAuth = new JSONObject()
	   JSONObject  lastobsel = new JSONObject()
		
		// init Trace BAse
		String urlBaseProfile = urlKTBSRoot + userProfil.iduser + "/"
		String urlBaseSignature = urlKTBSRoot + userSignature.iduser + "/"
		
		
		
		// init Trace BAse
		String urlBaseUserProfile = urlKTBSRoot + userProfil.iduser + "/"
		String urlBaseUserSignature = urlKTBSRoot + userSignature.iduser + "/"
		
		Base baseProfile = new Base (urlBaseUserProfile)
		Base baseSignature = new Base (urlBaseUserSignature)
		
		// init Trace Profil
		 def TraceprofileName = "primarytraceAuthentificationTrace_Profil"
	   // Constants.Trace_ProfilDynamic
	   Trace traceProfil = new Trace (urlKTBSRoot + userProfil.iduser + "/"+TraceprofileName+"/")
	  
		//init Trace Signature
		def TracesignatureName = "primarytraceInscriptionTrace_Signature"
	   //Constants.Trace_SignatureDynamic
	   Trace traceSignature = new Trace (urlKTBSRoot + userSignature.iduser + "/"+TracesignatureName+"/")
	   
	   // Begin Calcul Trust
	   
	   System.out.println("begin authentication ") // compare all dont use user.auth
	   trust = 0
	  
		// Get Obsel From Profil
	   obselprofil = ktbsService?.getListObsel (baseProfile, null, null,TraceprofileName)
	   obselsignature = ktbsService?.getListObsel (baseSignature, null, null,TracesignatureName)
	  
	    authenticationResult = new AuthenticationResult(userProfile: userProfil,userSignature: userSignature, start: new Date(), typeModalitie: "BIOMETRIC" , typeAuth : "Manuel")
	   
	 
	   
	   
	   int i ;
	   println ("trust Initial"+trust)
					   
	   for (i=5 ;i < obselprofil.size(); i++) {
			   Obsel obs = obselprofil[i]
			   distance = calculDistance (obs, obselsignature)
			   println ("distance obsel id " + obs.get_id() +"est égale = "+ distance)
			   if(distance >= 0){
							   trust = calculateCoefficient(distance, obs, trust, urlBaseSignature)
							   JSONObject Obj = new JSONObject();
							   Obj.put("id_action", obs.get_id())
							   Obj.put("type_action", obs.get_obsel_type()?.getTypeId())
							   Obj.put("begin", obs.get_begin())
							   Obj.put("distance", distance)
							   Obj.put ("end",obs.get_end())
							   Obj.put("trust", trust)
							   items.put(Obj)
	   
							   ObselTrust obselTrust = new ObselTrust(idAction: obs.get_id(), typeAction: obs.get_obsel_type()?.getTypeId(), distance: distance, trust: trust, begin: obs.get_begin(), end: obs.get_end())
							   authenticationResult.addToObselTrusts(obselTrust)
							   authenticationResult.save() // authenticationResult is the owner of ObselTrust. Then cascade are done from the owner authenticationResult to the ObselTrust childs.
							   
							 //println (items.toString())  ;
							objResultAuth.put("result", items)
	   
							   authenticationStatisticsService.calculateStats(authenticationResult)
						   }
	   }
	   
	   
     println ("trust final"+trust)
	 return authenticationResult ;
	 }
}

	
