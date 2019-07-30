package authentication.modality

import track.AuthenticationResult
import track.ObselSessionTrust
import track.ObselTrust
import track.User;
import track.UserAuthentificationEnvApp

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.Trace
import grails.transaction.Transactional





import java.util.Collection;
import java.util.stream.Stream

import ktbs.KtbsService
import defaults.Constants
import grails.transaction.Transactional

import org.json.JSONArray
import org.json.JSONObject;
import org.springframework.web.context.request.RequestAttributes;

import groovy.transform.Field
import track.utils.SimularityUtils

@Transactional
class EnvironnementAndSessionApprentissageService {
	def KtbsService 
	def grailsApplication
	
	SimularityUtils simularityUtils = new SimularityUtils()
	
	/**
	 * create Tace Profil for modalité Environnement et Session D'apprentissage
	 * @param base
	 * @param primarytrace
	 * @return
	 */
	
	Trace createTraceProfilEnv (Base base, Trace primarytrace) {
		   
		String methode = "fsa"
        String[] tracesource = [primarytrace.get_Name() + "/"];
		String FsaTraceProfile = "fsa= {\n\t\"allow_overlap\": true,\n\t\"states\": {\n\t\t\"start\": {\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#connect\",\n\t\t\t\t\"target\": \"session\"}]\n\t\t},\n\t\t\n\t\t\n\t\t\"session\": {\n                        \"max_duration\": 250000,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"?obs a m:disconnect; m:sessionID ?k . ?pred m:sessionID ?k .\",\n\t\t\t\t\"matcher\": \"sparql-ask\",\n\t\t\t\t\"target\": \"#session\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"session\",\n\t\t\t\t\"silent\": false\n\t\t\t}\n\t\t},\n\t\t\n\t\t\"#session\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#sessionID\": \"first #sessionID\",\n                                \"#adressIP\": \"first #adressIP\",\n                                 \"#persistantID\": \"first #persistantID\",\n                                 \"#dayOfWeek\": \"first #dayOfWeek\",\n                                 \"#hoursOfDay\": \"first #hoursOfDay\",\n\t\t\t\t\"#duration\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t}\n\t}\n}"
		String[] parametre = [FsaTraceProfile]
        Trace traceprofil = ktbsService.createTransformedTrace(base, Constants.PrimarytraceEnvProfil, methode, tracesource, parametre)
        return traceprofil
		
	}
	/**
	 * create trace signature envApprentissage vide 
	 * @param base
	 * @return
	 */
	
	Trace createTraceSignatureEnv (Base base){
		Trace signaturetrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,Constants.PrimarytraceEnvSignature,null)
	}
	
  /**
   * 
   * 
   * @param AncientBarycentre
   * @param NouveauSession
   * @param index
   * @return
   */
	public static JSONObject calculbarycentre (Obsel AncientBarycentre, Obsel NouveauSession,int index, Double similarité) {
		
		JSONObject barycenter = new JSONObject();
		
		barycenter.put ("@id","Cluster"+index)
		barycenter.put ("begin",NouveauSession.Begin)
		barycenter.put ("end",NouveauSession.Begin)
		barycenter.put ("@type","m:cluster")
		barycenter.put ("m:name","cluster"+index)
		//barycenter.put ("m:poid","1")
		
		if (AncientBarycentre != null) { 
			Double NouveauPoid =  Double.parseDouble (AncientBarycentre.get_attribute("poid").getValue()) + similarité 
			barycenter.put ("m:poid",NouveauPoid)
			}
		else {barycenter.put ("m:poid","1")}
		
		//List<String> listSession = new ArrayList<String>();
		JSONArray listSession =new JSONArray () ;
		listSession.put("");
		//barycenter.put("m:listSession", listSession) ;
		if (AncientBarycentre != null) {listSession=new JSONArray (AncientBarycentre.get_attribute("listSession").getValue());}
		listSession.put(NouveauSession.uri);
		println (listSession) ;
		barycenter.put("m:listSession", listSession) ;
		
		JSONArray persistantID =new JSONArray ()
		
		persistantID.put("");
		if (AncientBarycentre != null) {persistantID = new JSONArray (AncientBarycentre.get_attribute("persistantID").getValue());}
		if (!persistantID.toString().contains((String) NouveauSession.get_attribute("persistantID").getValue())) {persistantID.put ((String) NouveauSession.get_attribute("persistantID").getValue());}
		barycenter.put("m:persistantID", persistantID) ;
		
		JSONArray adressIP =new JSONArray ()
		adressIP.put("");
		if (AncientBarycentre != null) { adressIP = new JSONArray (AncientBarycentre.get_attribute("adressIP").getValue());}
		if (!adressIP.toString().contains((String) NouveauSession.get_attribute("adressIP").getValue())) {adressIP.put ((String) NouveauSession.get_attribute("adressIP").getValue()); }
		
	    barycenter.put("m:adressIP", adressIP) ;
	  
		
	    JSONArray dayOfWeek =new JSONArray ()
	    dayOfWeek.put("");
		if (AncientBarycentre != null) { dayOfWeek= new JSONArray (AncientBarycentre.get_attribute("dayOfWeek").getValue());}
		if (!dayOfWeek.toString().contains((String) NouveauSession.get_attribute("dayOfWeek").getValue())) { dayOfWeek.put ((String) NouveauSession.get_attribute("dayOfWeek").getValue());}
		barycenter.put("m:dayOfWeek", dayOfWeek) ;
		
		if (AncientBarycentre != null) {
		double hoursOfDay  = SimularityUtils.moyenne ( Double.parseDouble ((String) AncientBarycentre.get_attribute("hoursOfDay").getValue()),Double.parseDouble ((String) NouveauSession.get_attribute("hoursOfDay").getValue()));
		barycenter.put("m:hoursOfDay", Double.toString (hoursOfDay)) ;
		} else barycenter.put("m:hoursOfDay", NouveauSession.get_attribute("hoursOfDay").getValue()) ;
		
		if (AncientBarycentre != null) {
		double duration  = SimularityUtils.moyenneGeometric (Double.parseDouble ((String) AncientBarycentre.get_attribute("duration").getValue()),Double.parseDouble ((String) NouveauSession.get_attribute("duration").getValue()));
		barycenter.put("m:duration", Double.toString (duration)) ;}
		else barycenter.put("m:duration", NouveauSession.get_attribute("duration").getValue()) ;
		
		
		 
		return barycenter;
	}
	
	/**
	 * 
	 * @param Session
	 * @param barycentre
	 * @return
	 */
	public static double calculSimularitySession (JSONObject Session, JSONObject barycentre, Double[] weigth) {
		
			  double d = (double) (weigth [0] * SimularityUtils.simulairString ((String) Session.get("m:persistantID"),barycentre.get("m:persistantID"))
					  + weigth [1] * SimularityUtils.simulairString ((String) Session.get("m:adressIP"),barycentre.get("m:adressIP"))
					  + weigth [2] * SimularityUtils.simulairString ((String) Session.get("m:dayOfWeek"),barycentre.get("m:dayOfWeek"))
					  + weigth [3] * SimularityUtils.simulairhours (Double.parseDouble ((String) Session.get("m:hoursOfDay")),Double.parseDouble ( barycentre.get("m:hoursOfDay")))
					  + weigth [4] * SimularityUtils.simulairDuration (Double.parseDouble ((String) Session.get("m:duration")),Double.parseDouble (barycentre.get("m:duration"))));
								 
			  return d ;
		  }
	
	
	
	
	/**
	 * calcul Trust
	 * @param user
	 * @return
	 */
  def ClusteringAndCalculTrustEnvApp (User user , RequestAttributes currentRequest) {
		
	  def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
	  def weigth = [0.3,0.3,0.1,0.1,0.2] as Double[] ;
	  def k = 0.5 
	  float trust
	  def nouveauTrust
	  SimularityUtils simularityUtils = new SimularityUtils()
	  AuthenticationResult authenticationResult
	    
	  // init Trace BAse
		String urlBaseUserProfile = urlKTBSRoot + user.iduser + "/"
		String urlBaseUserSignature = urlKTBSRoot + user.iduser + "/"
		
		Base baseProfile = new Base (urlBaseUserProfile)
		Base baseSignature = new Base (urlBaseUserSignature)
		
		// init Trace Profil 
		def TraceprofileName = Constants.PrimarytraceEnvProfil
	   Trace traceProfil = new Trace (urlKTBSRoot + user.iduser + "/"+TraceprofileName+"/")
	  
	    //init Trace Signature
	   String TracesignatureName = Constants.PrimarytraceEnvSignature
	   Trace traceSignature = new Trace (urlKTBSRoot + user.iduser + "/"+TracesignatureName+"/")
	   
	   // get last Obsel from Trace Profil 
	   def obselprofil = ktbsService?.getListObsel (baseProfile, null, null,TraceprofileName)
	   Obsel lastSession = obselprofil.size() ? obselprofil.getAt(obselprofil.size() - 1) : 0
	   
	   // Get List Obsel Signature Trace 
	   def obselSignature = ktbsService?.getListObsel (baseSignature, null, null,TracesignatureName)
	   def index = obselSignature.size()+1
	   
	   // Create InAuthResultEnv 
	   
	  //authenticationResult = new AuthenticationResult(userProfile: user,userSignature: user, start: new Date(), type: "ENVAPP")
//	  boolean authResult = AuthenticationResult.findWhere (userProfile : user, userSignature : user , typeModalitie: "ENVAPP" , typeAuth : "System") != null
//	 if (authResult){
//		 authenticationResult = AuthenticationResult.findWhere (userProfile : user, userSignature : user , typeModalitie: "ENVAPP", typeAuth : "System" )
//		}
//	 else {
//		 authenticationResult = new AuthenticationResult(userProfile: user,userSignature: user, start: new Date(), typeModalitie: "ENVAPP" , typeAuth : "System")
//		}
	  // First or secend auth 
	  
	  if (user.userAuthentificationEnvApp){
		  println ("second authentication ")
		  trust = user.userAuthentificationEnvApp.trustEnvApp ;
		  println ("ancien Trust " + trust);
		  authenticationResult = user.userAuthentificationEnvApp.authenticationResult
		}
	  else {
		  
		  System.out.println("first authentication ")
		  authenticationResult = new AuthenticationResult(userProfile: user,userSignature: user, start: new Date(), typeModalitie: "ENVAPP" , typeAuth : "System")
		  trust = 1
		 
	  }
		// traitement pour First session 
	  if (obselSignature.isEmpty()) {
		  
		  // Add last session to Trace Signature 
		 JSONObject FirstclusterBArycentre = calculbarycentre (null, lastSession,index,0)
		 KtbsService.createObsel(user.iduser , TracesignatureName,FirstclusterBArycentre)
		 // Add To Obsel Session
		 ObselSessionTrust obselSession = new ObselSessionTrust (idAction: lastSession.get_uri(),sessionID : currentRequest.getSessionId(),distance: 0, trust: trust,  ancienbarycentre: "  ", nouveaubarycentre : FirstclusterBArycentre )
		 authenticationResult.addToObselSessionTrusts( obselSession)
		 authenticationResult.save()
		  
	}
	   // Autre session
	else {
		double [] distance = new double [100] ;
		double [] Poid = new double [100] ;
		Double score =0.0;
		
		// Objet distance details 
		JSONArray detailsDistances = new JSONArray ()
		
		
		
		//userAuth.listTrustEnvApp =  ListTrustBiometricJson.toString();
		
		/** Calculer distance par rapport tous les clusters dans trace Signature **/
		for (int j=0; j< obselSignature.size(); j++) {
			
			double Simularité = calculSimularitySession (lastSession.get_jsonObsel(),obselSignature.getAt(j).get_jsonObsel(),weigth);
			//System.out.println ("distance entre " + C.get("Name") + " et " + sessionActuel.get("Name") + " est = "+ dist ) ;
			distance [j] = Simularité ;
			Poid [j] = Double.parseDouble ((String) obselSignature.getAt(j).get_attribute("poid").getValue()) ;
			/*/** calculer score  for trust **/
			score = score + Poid [j]*Simularité
			// Objet distance details
			println ("comparaison cluster " + obselSignature.getAt(j).get_id() +"le poid du cluster est "+ Poid [j]+ " distance est : " + Simularité)
			JSONObject ObjdetailsDistances = new JSONObject();
			ObjdetailsDistances.put("clusterID ", obselSignature.getAt(j).get_id())
			ObjdetailsDistances.put("Simularité", Simularité)
			detailsDistances.put(ObjdetailsDistances);
		}
		Double SimMax = simularityUtils.MaxTableau (distance)
		int indexMax =  simularityUtils.MaxIndex (distance) ;
		println("SimMax =" + SimMax) ;
		println("indexMax =" + indexMax) ;
		println("score =" +  score) ;
		// Clustering and Calcul Trust 
		if (SimMax>k) {
			println ("mise à jour barycenter Signture")
			// Mise à jour l'obsel Cluster de la trace signature 
			Obsel AncienBarycenter = obselSignature.getAt(indexMax)
			// Supprimer Obsel du trace Signature 
			Obsel obs = obselSignature.getAt(indexMax);
			obs.remove() ;
			// create nouveau obsel ==> obsel de mise à jour 
			JSONObject NouveauclusterBArycentre = calculbarycentre (AncienBarycenter, lastSession,indexMax+1,SimMax)
			KtbsService.createObsel(user.iduser , TracesignatureName,NouveauclusterBArycentre)
				nouveauTrust = trust + score ;
			
			// Save in ObselSessionTrust 
		
			// Add To Obsel Session
			ObselSessionTrust obselSession = new ObselSessionTrust (idAction: lastSession.get_uri(),sessionID : currentRequest.getSessionId() , distance: SimMax, trust: trust,  ancienbarycentre: AncienBarycenter.get_jsonObsel(), nouveaubarycentre : NouveauclusterBArycentre.toString(), detailsDistances : detailsDistances, score : score )
			authenticationResult.addToObselSessionTrusts( obselSession)
			authenticationResult.save()
			
		} else {
		println ("creer nouveau cluster")
			// create un nouveau Cluster ==> un nouveau obsel dans signature 
			JSONObject NouveauclusterBArycentre = calculbarycentre (null, lastSession,index,SimMax)
			KtbsService.createObsel(user.iduser , TracesignatureName, NouveauclusterBArycentre)
			nouveauTrust = trust * score ;
			
		// Save in ObselSessionTrust 
		//	ObselSessionTrust obselSessionTrust  = new ObselSessionTrust (idSession: lastSession.get_id(),distance: SimMax, trust: trust,  Ancienbarycentre: "", Nouveaubarycentre : NouveauclusterBArycentre.Tojson )
			ObselSessionTrust obselSession = new ObselSessionTrust (idAction: lastSession.get_uri(),sessionID : currentRequest.getSessionId() , distance: SimMax, trust: trust,  ancienbarycentre: "  ", nouveaubarycentre : NouveauclusterBArycentre.toString(),detailsDistances : detailsDistances, score : score )
			authenticationResult.addToObselSessionTrusts( obselSession)
			authenticationResult.save()
		}
	}
		// save user authentication
		// change TrustKDandMD add TrustEnv and TrustAct add Trust CompApp
		// Same for
	
	   if (user.userAuthentificationEnvApp){
		   def userAuth = user.userAuthentificationEnvApp
		   
		   // save AncienTrust In list
		   JSONArray ListTrustBiometricJson = new JSONArray (userAuth.listTrustEnvApp)
		   JSONObject Objtrust = new JSONObject();
		   Objtrust.put("sessionID", currentRequest.getSessionId())
		   Objtrust.put("trust", trust)
		   ListTrustBiometricJson.put(Objtrust);
		   userAuth.listTrustEnvApp =  ListTrustBiometricJson.toString();
		   
		   userAuth.trustEnvApp =nouveauTrust
		   userAuth.ancientrustEnvApp=  trust
		   userAuth.save()
		   user.userAuthentificationEnvApp = userAuth
		   user.save()
		   
	   } else {
	   UserAuthentificationEnvApp userAuth = new UserAuthentificationEnvApp()
	   // save AncienTrust In list
	   JSONArray ListTrustBiometricJson = new JSONArray (userAuth.listTrustEnvApp)
	   JSONObject Objtrust = new JSONObject();
	   Objtrust.put("sessionID", currentRequest.getSessionId())
	   Objtrust.put("trust", trust)
	   ListTrustBiometricJson.put(Objtrust);
	   userAuth.listTrustEnvApp =  ListTrustBiometricJson.toString();
	   
	    userAuth.trustEnvApp =nouveauTrust
		userAuth.ancientrustEnvApp =  trust
		userAuth.authenticationResult = authenticationResult
		
	   user.userAuthentificationEnvApp = userAuth
	   user.save()
	   }
		
		
	
}
  
  def AuthentificationEnvAprrTwoUSer (User userProfil , User userSignature) {
  
	  def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
	  def weigth = [0.3,0.3,0.1,0.1,0.2] as Double[] ;
	  def k = 0.5
	  float trust
	  SimularityUtils simularityUtils = new SimularityUtils()
	  AuthenticationResult authenticationResult
		
	  // init Trace BAse
		String urlBaseUserProfile = urlKTBSRoot + userProfil.iduser + "/"
		String urlBaseUserSignature = urlKTBSRoot + userSignature.iduser + "/"
		
		Base baseProfile = new Base (urlBaseUserProfile)
		Base baseSignature = new Base (urlBaseUserSignature)
		
		// init Trace Profil
		def TraceprofileName = Constants.PrimarytraceEnvProfil
	   Trace traceProfil = new Trace (urlKTBSRoot + userProfil.iduser + "/"+TraceprofileName+"/")
	   Trace traceProfil2 = new Trace (urlKTBSRoot + userSignature.iduser + "/"+TraceprofileName+"/")
	  
		//init Trace Signature
	  
	  
	    Base base = new Base ("https://liris-ktbs01.insa-lyon.fr:8000/fatma/baseForAuthEnvManuel/")
		base.init()
	   // create primary Trace for envApprentissage
	   Trace primarytraceSignature = ktbsService.createPrimaryTrace (base,Constants.modelprimary, userProfil.firstname+userSignature.firstname+ UUID.randomUUID(),null)
	   
	   // get last Obsel from Trace Profil
	   Collection<Obsel> obselprofil = ktbsService?.getListObsel (baseProfile, null, null,traceProfil.get_Name())
	   Collection<Obsel> obselprofil2 = ktbsService?.getListObsel (baseSignature, null, null,traceProfil2.get_Name())
	   
	//   Stream<Obsel> obselprofil = Stream.concat(obselprofil1.stream(), obselprofil2.stream());
	  boolean t =  obselprofil.addAll(obselprofil2)
	  println  (t)
	   
	   // Get List Obsel Signature Trace
	   def obselSignature
	   def index
	   obselSignature = ktbsService?.getListObsel (base, null, null,primarytraceSignature.get_Name())
	    index = obselSignature.size()+1
	   
	   // Create InAuthResultEnv
	   
	 
		 authenticationResult = new AuthenticationResult(userProfile: userProfil,userSignature: userSignature, start: new Date(), typeModalitie: "ENVAPP" , typeAuth : "Manuel")
		
	 
	
		  trust = 1
		  double [] distance = new double [100] ;
		  double [] Poid = new double [100] ;
		  Double score =0.0;
		  
		  // Objet distance details
		  JSONArray detailsDistances = new JSONArray ()
		  for (int i=0; i< 10; i++) {
			 println ( obselprofil.getAt(i).get_uri () )
		  
			  obselSignature = ktbsService?.getListObsel (base, null, null,primarytraceSignature.get_Name())
			  index = obselSignature.size()+1
			 
			  // traitement pour First session
			  if (i==0){
				  JSONObject FirstclusterBArycentre = calculbarycentre (null, obselprofil.getAt(i),index,0)
				  KtbsService.createObsel(base.get_Name() , primarytraceSignature.get_Name(),FirstclusterBArycentre)
				  trust =1 
				  // Add To Obsel Session
				  ObselSessionTrust obselSession = new ObselSessionTrust (idAction: obselprofil.getAt(i).get_uri(),sessionID : " " , distance: 0, trust: trust,  ancienbarycentre: "  ", nouveaubarycentre : FirstclusterBArycentre )
				  authenticationResult.addToObselSessionTrusts( obselSession)
				  authenticationResult.save()
			   }
			  else {
		
				  // Autre session
				  for (int j=0; j< obselSignature.size(); j++) {
					  
					  double Simularité = calculSimularitySession (obselprofil.getAt(i).get_jsonObsel(),obselSignature.getAt(j).get_jsonObsel(),weigth);
					  //System.out.println ("distance entre " + C.get("Name") + " et " + sessionActuel.get("Name") + " est = "+ dist ) ;
					  distance [j] = Simularité ;
					  Poid [j] = Double.parseDouble ((String) obselSignature.getAt(j).get_attribute("poid").getValue()) ;
					  /*/** calculer score  for trust **/
					  score = score + Poid [j]*Simularité
					  // Objet distance details
					  println ("comparaison cluster " + obselSignature.getAt(j).get_id() +"le poid du cluster est "+ Poid [j]+ " distance est : " + Simularité)
					  JSONObject ObjdetailsDistances = new JSONObject();
					  ObjdetailsDistances.put("clusterID ", obselSignature.getAt(j).get_id())
					  ObjdetailsDistances.put("Simularité", Simularité)
					  detailsDistances.put(ObjdetailsDistances);
				  }
				  Double SimMax = simularityUtils.MaxTableau (distance)
				  int indexMax =  simularityUtils.MaxIndex (distance) ;
				  println("SimMax =" + SimMax) ;
				 // println("indexMax =" + indexMax) ;
				 // println("score =" +  score) ;
				  // Clustering and Calcul Trust
				  if (SimMax>k) {
					  println ("mise à jour barycenter Signture")
					  // Mise à jour l'obsel Cluster de la trace signature
					  Obsel AncienBarycenter = obselSignature.getAt(indexMax)
					  // Supprimer Obsel du trace Signature
					  Obsel obs = obselSignature.getAt(indexMax);
					  obs.remove() ;
					  // create nouveau obsel ==> obsel de mise à jour
					  JSONObject NouveauclusterBArycentre = calculbarycentre (AncienBarycenter, obselprofil.getAt(i),indexMax+1,SimMax)
					  KtbsService.createObsel(base.get_Name() , primarytraceSignature.get_Name(),NouveauclusterBArycentre)
					 
						  trust = trust + score ;
					  
					  // Save in ObselSessionTrust
				  
					  // Add To Obsel Session
					  ObselSessionTrust obselSession = new ObselSessionTrust (idAction: obselprofil.getAt(i).get_uri(),sessionID : " " ,distance: SimMax, trust: trust,  ancienbarycentre: AncienBarycenter.get_jsonObsel(), nouveaubarycentre : NouveauclusterBArycentre.toString(), detailsDistances : detailsDistances, score : score )
					  authenticationResult.addToObselSessionTrusts( obselSession)
					  authenticationResult.save()
					  
				  } else {
				  println ("creer nouveau cluster")
					  // create un nouveau Cluster ==> un nouveau obsel dans signature
					  JSONObject NouveauclusterBArycentre = calculbarycentre (null, obselprofil.getAt(i),index,SimMax)
					  KtbsService.createObsel(base.get_Name() , primarytraceSignature.get_Name(), NouveauclusterBArycentre)
					  trust = trust * score ;
					  
				  // Save in ObselSessionTrust
				  //	ObselSessionTrust obselSessionTrust  = new ObselSessionTrust (idSession: lastSession.get_id(),distance: SimMax, trust: trust,  Ancienbarycentre: "", Nouveaubarycentre : NouveauclusterBArycentre.Tojson )
					  ObselSessionTrust obselSession = new ObselSessionTrust (idAction: obselprofil.getAt(i).get_uri(),sessionID : " ",distance: SimMax, trust: trust,  ancienbarycentre: "  ", nouveaubarycentre : NouveauclusterBArycentre.toString(),detailsDistances : detailsDistances, score : score )
					  authenticationResult.addToObselSessionTrusts( obselSession)
					  authenticationResult.save()
				  }
				  
		 }
			  
	}
	

	   
	
		
		
		
		
		
	   }
	  
	  
}
