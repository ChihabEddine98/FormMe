package authentication.modality

import track.User;
import authentication.profil.ProfileUserService;

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.Trace
import grails.transaction.Transactional



import ktbs.KtbsService
import defaults.Constants
import grails.transaction.Transactional
import org.json.JSONArray
import org.json.JSONObject;
import groovy.transform.Field
import track.utils.SimularityUtils

@Transactional
class EnvironnementAndSessionApprentissageService {
	def KtbsService 
	def grailsApplication
	ProfileUserService profileUserService
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
			def NouveauPoid = AncientBarycentre.get_attribute("poid").getValue() + similarité 
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
  def ClusteringAndCalculTrustEnvApp (User user) {
		
	  def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
	  def weigth = [0.3,0.3,0.1,0.1,0.2] as Double[] ;
	  def k = 0.5 
	  SimularityUtils simularityUtils = new SimularityUtils()
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
		// traitement pour First session 
	  if (obselSignature.isEmpty()) {
		  
		  // Add last session to Trace Signature 
		 JSONObject FirstclusterBArycentre = calculbarycentre (null, lastSession,index,0)
		 println (FirstclusterBArycentre)
		 KtbsService.createObsel(user.iduser , TracesignatureName,FirstclusterBArycentre)
		  
	}
	   // Autre session
	else {
		double [] distance = new double [100] ;
		double [] Poid = new double [100] ;
		Double score =0.0;
		/** Calculer distance par rapport tous les clusters dans trace Signature **/
		for (int j=0; j< obselSignature.size(); j++) {
			println (lastSession.get_jsonObsel())
			println (obselSignature.getAt(j).get_jsonObsel())
			double Simularité = calculSimularitySession (lastSession.get_jsonObsel(),obselSignature.getAt(j).get_jsonObsel(),weigth);
			//System.out.println ("distance entre " + C.get("Name") + " et " + sessionActuel.get("Name") + " est = "+ dist ) ;
			distance [j] = Simularité ;
			Poid [j] = Double.parseDouble ((String) obselSignature.getAt(j).get_attribute("duration").getValue()) ;
			/*/** calculer score  for trust **/
			score = score + Poid [j]*Simularité
		}
		Double SimMax = 0.4
		//simularityUtils.MaxTableau (distance) ;
		int indexMax =  simularityUtils.MaxIndex (distance) ;
		println("SimMax =" + SimMax) ;
		println("indexMax =" + indexMax) ;
		// Clustering 
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
			
		} else {
		println ("creer nouveau cluster")
			// create un nouveau Cluster ==> un nouveau obsel dans signature 
			JSONObject NouveauclusterBArycentre = calculbarycentre (null, lastSession,index,SimMax)
			KtbsService.createObsel(user.iduser , TracesignatureName, NouveauclusterBArycentre)
		}
		
		
		// Calculer Trust
		if (SimMax>k) {
		//	Nouveautrust = Ancientrust + score ;
		} else {
		 //   Nouveautrust = Ancientrust * score ;
		}
		
	}
}
	
}
