package authentication.decision

import grails.transaction.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

import ktbs.KtbsService

import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject

import track.AuthenticationResult
import track.ObselTrust
import track.User
import track.UserAuthentification
import authentication.AuthenticationStatisticsService
import authentication.profil.ProfileUserService
import authentication.signature.SignatureUserService

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.Trace

import defaults.Constants

@Transactional
class DecisionService {

	def grailsApplication

    SignatureUserService signatureUserService
    ProfileUserService profileUserService
    KtbsService ktbsService
	AuthenticationStatisticsService  authenticationStatisticsService
	/**
	 * https://liris-ktbs01.insa-lyon.fr:8000/fatma/NicolasFOURNIOL5f5a1891-a94f-4f95-9b9f-896c58adfe39/primarytraceInscription/
	 * urlKtbs = https://liris-ktbs01.insa-lyon.fr:8000/fatma
	 * uriBase = NicolasFOURNIOL5f5a1891-a94f-4f95-9b9f-896c58adfe39
	 * Trace name = primarytraceInscription
	 * @param urlbase
	 * @param profileName
	 * @param signatureName
	 * @param user
	 * @param type
	 * @return
	 */
	def authenticate(User userProfile, User userSignature, String type) {

		
		System.out.println("authentication"+type)
		// init variable
		String profileName
		String signatureName
		float trust
		float distance
		Collection<Obsel> obselsignature
		Collection<Obsel> obselprofil
		AuthenticationResult authenticationResult
		int lastBeginObsel
		int lastBeginObselDynamic
		List<ObselTrust> listObselTrust = new ArrayList<ObselTrust>()
		 // initial value (useful for calculateCoefficient which is based on last trust value calculated)
		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		JSONArray items = new JSONArray()
		JSONObject objResultAuth = new JSONObject()
		JSONObject  lastobsel = new JSONObject()
		String urlBaseProfile = urlKTBSRoot + userProfile.iduser + "/"
		String urlBaseSignature = urlKTBSRoot + userSignature.iduser + "/"
		Base baseProfile = new Base (urlBaseProfile)
		Base baseSignature = new Base (urlBaseSignature)
		
		//userProfile.userauthentification = null
		//userProfile.save ()
		
		//trust =0 ;
		
		if (type.equalsIgnoreCase("DYNAMIC")) 
		{	
			
			signatureName = "primarytraceInscriptionTrace_Signature"
			//Constants.Trace_SignatureDynamic
			profileName = "primarytraceAuthentificationTrace_Profil"
			// Constants.Trace_ProfilDynamic
		    // real time 
			if (userProfile.userauthentification.trustDynamic){
				println ("second authentication dynamique")
				trust = userProfile.userauthentification.trustDynamic ;
				println (userProfile.userauthentification.lastObselProfilDynamic);
				obselprofil = profileUserService?.getListObsel (baseProfile, userProfile.userauthentification.lastObselProfilDynamic, null,profileName)
				lastBeginObsel = ktbsService.getIdLastObsel(obselprofil)
				println (lastBeginObsel);
			} else {
				 System.out.println("first authentication dynamique")
				 trust = 0
				 obselprofil = profileUserService?.getListObsel (baseProfile, null, null,profileName)
				 lastBeginObsel = obselprofil.size() ? obselprofil.getAt(obselprofil.size() - 1).get_begin() : 0
			}
		} else if (type.equalsIgnoreCase("CLASSIC")){
			
				signatureName = "primarytraceInscriptionTrace_Signature"
			//Constants.Trace_SignatureDynamic
			profileName = "primarytraceAuthentificationTrace_Profil"
			// Constants.Trace_ProfilDynamic
			if (userProfile.userauthentification){
				println ("second authentication classique")
				trust = userProfile.userauthentification.trust ;
				println (userProfile.userauthentification.lastObselProfil);
				obselprofil = profileUserService?.getListObsel (baseProfile, userProfile.userauthentification.lastObselProfil, null,profileName)
				lastBeginObsel = ktbsService.getIdLastObsel(obselprofil)
				println (lastBeginObsel);
			}else {
			System.out.println("first authentication")
			trust = 0
			obselprofil = profileUserService?.getListObsel (baseProfile, null, null,profileName)
			lastBeginObsel = obselprofil.size() ? obselprofil.getAt(obselprofil.size() - 1).get_begin() : 0
			}
		} 
		
		
		// start authentication
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(new Date())); //2016/11/16 12:08:43

		obselsignature = signatureUserService?.getListObsel (baseSignature, null, null,signatureName)
		authenticationResult = new AuthenticationResult(userProfile: userProfile,userSignature: userSignature, start: new Date(), type: type)
		int i ;
		println ("trust Initial"+trust)
		for (i=0 ;i < obselprofil.size(); i++) {
			Obsel obs = obselprofil[i]
			distance = calculDistance (obs, obselsignature)
			if(distance >= 0)
			{
				
				trust = calculateCoefficient(distance, obs, trust, type, urlBaseSignature)
				JSONObject Obj = new JSONObject();
				Obj.put("id_action", obs.get_id())
				Obj.put("type_action", obs.get_obsel_type()?.getTypeId())
				Obj.put("begin", obs.get_begin())
				Obj.put("distance", distance)
				Obj.put ("end",obs.get_end())
				Obj.put("trust", trust)
				items.add(Obj)

				ObselTrust obselTrust = new ObselTrust(idAction: obs.get_id(), typeAction: obs.get_obsel_type()?.getTypeId(), distance: distance, trust: trust, begin: obs.get_begin(), end: obs.get_end())
				authenticationResult.addToObselTrusts(obselTrust)
				authenticationResult.save() // authenticationResult is the owner of ObselTrust. Then cascade are done from the owner authenticationResult to the ObselTrust childs.
				objResultAuth.put("result", new JSONArray(items))
				
				authenticationStatisticsService.calculateStats(authenticationResult)
			}
		}
	 
		
		
		// save user authentication
		UserAuthentification userAuth = new UserAuthentification()
		
		if (type.equalsIgnoreCase("DYNAMIC")){
			userAuth.trustDynamic = trust
			userAuth.lastObselProfilDynamic = lastBeginObsel
		}
		else {
			userAuth.trust = trust
			userAuth.lastObselProfil = lastBeginObsel
		}
		userProfile.userauthentification = userAuth
		userProfile.save()
		// flush:true
		println ("trust final"+trust)
		//return trust
		return objResultAuth ;
	}
	
  

    /**
     * Function to calculate the distance
     * @param obs
     * @return
     */
    float calculDistance (Obsel obs, Collection<Obsel> obselsignature ) {
		
		float distance = -1
		// for kK_PressRelease
		if (obs.get_obsel_type().getTypeId().equalsIgnoreCase(Constants.type_K_PR)){
			
			JSONObject obAttribute = new JSONObject()
			obAttribute.put(Constants.att_keySource,obs?.get_attribute (Constants.att_keySource)?.getValue())
			Obsel obssignature = signatureUserService?.getObselWithType (Constants.type_K_MPR,obAttribute,obselsignature)
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
			
			Obsel obssignature = signatureUserService?.getObselWithType ("K_M"+partType,obAttribute,obselsignature);
			
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
			Obsel obssignature = signatureUserService?.getObselWithType ("M_M"+partType,obAttribute,obselsignature);
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

			Obsel obssignature = signatureUserService?.getObselWithType (Constants.type_M_MBPR,null,obselsignature);
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
	def calculateCoefficient(float distance, Obsel obs, float previousCalculatedTrust, String type, String urlBaseSignature) {
			float trust = previousCalculatedTrust
			

			if (distance <17){
				trust = trust + 1.5 -distance
			}
			else if(distance >= 17)
			{
				//println (obs.get_id()+ ";" + obs.get_obsel_type().getTypeId()+ ";" + distance + ";" + trust + " DISTANCE TOO BIG")
			}
			
	        if (distance <5 ){
			if (type.equalsIgnoreCase("DYNAMIC") ){
					Trace trace = new Trace(urlBaseSignature+"primarytraceInscriptionTraceObselDynamic/")
					trace.PostObsel(obs)
				}
			}
			
			return trust
		}
	
	
}
