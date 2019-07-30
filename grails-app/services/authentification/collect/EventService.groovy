package authentification.collect

import ktbs.KtbsService;

import grails.plugin.cookie.CookieService
import grails.transaction.Transactional
import track.Comment
import track.Quiz
import track.QuizScores
import track.User
import track.Cours

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Trace
import authentication.modality.EnvironnementAndSessionApprentissageService

import ktbs.KtbsService
import defaults.Constants;
import org.json.JSONObject
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import track.UserRole

import javax.servlet.http.Cookie

import grails.plugin.cookie.CookieService
import authentication.modality.KDandMDModalityService

@Transactional
class EventService {
	
	KtbsService ktbsService
	
	def KDandMDModalityService 
	def CookieService
	def grailsApplication
	EnvironnementAndSessionApprentissageService  environnementAndSessionApprentissageService
	def userAgentIdentService

	public String generatePersistentId(){


		// N2 : browser used
		String n1 =userAgentIdentService.browser+userAgentIdentService.browserVersion

		// N4 : OS used
		String n2=System.getProperty("os.name")+System.getProperty("os.version")



		String pID=(n1+" "+n2).encodeAsBase64()


		return pID

	}





	/**
	 * évènement d'inscription 
	 * créer la base de trace User.id
	 * créer la trace primary EnvApprentisage
	 * créer la trace profile EnvApprenttisage
	 * créer la trace signature EnvApprenttisage
	 * créer cookies persistent ID ===============================> à faire 
	 * @return
	 */
	def inscriptionEvent(User userInstance) {


		boolean estAdmin= UserRole.findByUser(userInstance).role.authority.equals("ROLE_ADMIN")

		if (!estAdmin)
		{
			// create Base de trace user
			Base base = ktbsService.createBase (userInstance.iduser, "base user ")
			// create primary Trace for envApprentissage
			Trace primarytrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,Constants.PrimarytraceEnv,null)
			// create profile Trace for envApprentissage
			//	profileUserService.createTraceProfilEnv (base, primarytrace, Constants.PrimarytraceEnvProfil)
			environnementAndSessionApprentissageService.createTraceProfilEnv (base, primarytrace)
			// create primary Trace signature for envApprentissage
			//	Trace signaturetrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,Constants.PrimarytraceEnvSignature,null)
			environnementAndSessionApprentissageService.createTraceSignatureEnv (base) ;


			// create cookie for persistentID

			String pID=generatePersistentId()
			Cookie cookiePID = new Cookie('PersisentID',pID)

			CookieService.setCookie(cookiePID)
		}








	}
	/**
	 * évènement connect 
	 * Collect SessionID, AdressIP, persistantID, dayOfDay, HoursOfDay
	 * Create Obsel connect in PrimaryTrace EnvApprentissage
	 * Set cookies for collector Client
	 * @param requestContextHolder
	 * @param user
	 * @return
	 */
	def connectEvent(RequestAttributes currentRequest, User user) {
		CookieService cookieService
		// get sessionID
		def sessionId = currentRequest.getSessionId();
		
		// create obsel connect
		Date date = new Date()
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

		// Récupérer l'adresse IP externe ( en String)
		URL whatismyip = new URL("http://checkip.amazonaws.com")
		BufferedReader br = new BufferedReader(new InputStreamReader(
				whatismyip.openStream()))

		String ip = br.readLine(); //you get the IP as a String
		println(" ip : "+ip)



		// Get or Create Persisent ID from the cookie

		Cookie cookiePID=CookieService.getCookie('PersistentID')
		String pID




		if(cookiePID==null)
		{
			String newPID=generatePersistentId()
			pID = new String(newPID.decodeBase64())
			Cookie newCookiePID = new Cookie('PersisentID',newPID)
			CookieService.setCookie(newCookiePID)

		}
		else {
	    pID = cookiePID.getValue()
				println(" Persistent ID already exists  : "+ pID)
				pID = new String(pID.decodeBase64())

		}





		//cal.setTime(date.getTime())
		def jour=date.format("EEEE")
		def heure=date.format("HH:mm")

		println(" hereee jour :"+jour)
		println(" hereee heure :"+heure)


		JSONObject Attributes = new JSONObject();
		Attributes.put ("@id","connect"+sessionId)
		Attributes.put ("begin", date.getTime())
		Attributes.put ("end", date.getTime())
		Attributes.put ("@type","m:connect")
		// Ajouter IP here !
		Attributes.put ("m:adressIP",ip)
		Attributes.put ("m:persistantID",pID)
		Attributes.put ("m:sessionID",sessionId)

		Attributes.put ("m:dayOfWeek",jour)
		Attributes.put ("m:hoursOfDay",heure)
		




		ktbsService.createObsel(user.iduser , Constants.PrimarytraceEnv,Attributes)
		
		// set cookies
		
		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		String urlBase = URLEncoder.encode(urlKTBSRoot + user.iduser+"/", "UTF-8")
		String urlModel = URLEncoder.encode(urlKTBSRoot + user.iduser+"/"+user.modelTraceName+"#", "UTF-8")
		
		Cookie cookieBaseURI = new Cookie('BaseURI',urlBase)
		Cookie cookieTraceNameEnv = new Cookie('TraceNameEnv',Constants.PrimarytraceEnv+"/")
		Cookie cookieModelURI = new Cookie('ModelURI',urlModel)
		Cookie cookieSessionID = new Cookie('SessionID',sessionId)		
		cookieBaseURI.setPath("/")
		cookieTraceNameEnv.setPath("/")
		cookieModelURI.setPath("/")
		cookieSessionID.setPath("/")
	
		CookieService.setCookie(cookieBaseURI)
		CookieService.setCookie(cookieModelURI)
		CookieService.setCookie(cookieTraceNameEnv)
		CookieService.setCookie(cookieSessionID)

    }
	/**
	 * évènement disconnect
	 * create obsel event disconnect in trace primary EnvApprentiisage
	 * @param requestContextHolder
	 * @param user
	 * @return
	 */
    
	def disconnectEvent(RequestAttributes currentRequest, User user) {
		// get sessionID
		def sessionId = currentRequest.getSessionId();
		Date date = new Date()
	   println (sessionId)
	   // create obsel disconnect
	   
	   JSONObject Attributes = new JSONObject();
	   Attributes.put ("@id","disconnect"+sessionId)
	   Attributes.put ("begin",date.getTime())
	   Attributes.put ("end",date.getTime())
	   Attributes.put ("@type","m:disconnect")
	   Attributes.put ("m:sessionID",sessionId)
	   
	   ktbsService.createObsel(user.iduser , Constants.PrimarytraceEnv,Attributes)
		}
	/**
	 * Create Primary Trace Name Cours
	 * Create profil and Signature for MD and KD
	 * create profil and Signature For ComportementAppre
	 * @param user
	 * @return
	 */
	
	def InscriptionFormation (User user, Cours cours){
		
		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		def base = new Base (urlKTBSRoot + user.iduser+"/")
		// create primaryTrace cours Name
		Trace primarytrace = ktbsService.createPrimaryTrace (base,Constants.modelprimary,"Primarytrace"+cours.getNom()+cours.getId(),null)
		primarytrace.init()
		// create Profil KD and MD 
		Trace traceprofilIns = KDandMDModalityService.createTraceProfilKDandMD(base,primarytrace,primarytrace.get_Name()+ "_" + Constants.Trace_ProfilKDMD)
		Trace tracesignatureIns = KDandMDModalityService.createTraceSignatureKDandMD(base,primarytrace,primarytrace.get_Name()+ "_" + Constants.Trace_SignatureKDMD)
		// create Profil and Signature ComportementApprentiisage
	}
	/**
	 * init Primary Trace and set cookies for collector client
	 * Create Obsel Open Cours
	 * @param user
	 * @param cours
	 * @return
	 */
	def AccessFormation (User user, Cours cours){
		def urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		String urlBase = URLEncoder.encode(urlKTBSRoot + user.iduser+"/", "UTF-8")
		Cookie cookieTraceName = new Cookie('TraceName',"Primarytrace"+cours.getNom()+cours.getId()+"/")
		cookieTraceName.setPath("/")
		CookieService.setCookie(cookieTraceName)
		// create Obsel OpenCours 
		Date date = new Date()
		//def sessionId = currentRequest.getSessionId();
		JSONObject Attributes = new JSONObject();
		Attributes.put ("@id","OpenCours"+date.getTime())
		Attributes.put ("begin",date.getTime())
		Attributes.put ("end",date.getTime())
		Attributes.put ("@type","m:OpenCours")
		Attributes.put ("m:coursName", cours.getNom())
		Attributes.put ("m:coursID", cours.getId())
	//	Attributes.put ("m:sessionID",sessionId)
		
		ktbsService.createObsel(user.iduser , "Primarytrace"+cours.getNom()+cours.getId(),Attributes)
	}
	/**
	 * Create Obsel Consult Forum
	 * @param user
	 * @return
	 */
	def ConsultRessource (User user){
		JSONObject Attributes = new JSONObject();
		Date date = new Date()
		
		Attributes.put ("@id","openRessource"+date.getTime())
		Attributes.put ("begin",date.getTime())
		Attributes.put ("end",date.getTime())
		Attributes.put ("@type","m:openRessource")
		// Cours Information
		Attributes.put ("m:coursName", "ressource.cours.getNom()")
		Attributes.put ("m:coursID", "ressource.cours.getId()")
		// Ressource Information
		Attributes.put ("m:ressourceID", "ressource.id")
		Attributes.put ("m:ressourceName", "ressource.nom")
		Attributes.put ("m:ressourceType", "ressource.Type")
		Attributes.put ("m:ressourcePath", "ressource.Path")
		
		//ktbsService.createObsel(user.iduser , "Primarytrace"+cours.getNom()+cours.getId(),Attributes)
		
	}
	/**
	 * 
	 * @param user
	 * @param cours
	 * @return
	 */
	def  ExerciceEvaluated(User user, Cours cours, QuizScores quizScore){
		JSONObject Attributes = new JSONObject();
		Date date = new Date()
		
		Attributes.put ("@id","ExerciceEvaluated"+date.getTime())
		Attributes.put ("begin",date.getTime())
		Attributes.put ("end",date.getTime())
		Attributes.put ("@type","m:ExerciceEvaluated")
		
		// Cours Information
		Attributes.put ("m:coursName", "quizScore.quiz.cours.getNom()")
		Attributes.put ("m:coursID", cours.getId())
		// Ressource Information
		Attributes.put ("m:ressourceID", quizScore.quiz.id)
		Attributes.put ("m:ressourceName", quizScore.quiz.nom)
		Attributes.put ("m:ressourceType", "quizScore.quiz.Type")
		Attributes.put ("m:ressourcePath", "quizScore.quiz.Path")
		Attributes.put ("m:score", quizScore.score)
		ktbsService.createObsel(user.iduser , "Primarytrace"+cours.getNom()+cours.getId(),Attributes)
	}
	/**
	 * 
	 * @param user
	 * @param comment
	 * @return
	 */
	def  ForumCreateResponse(User user, Comment comment){
		JSONObject Attributes = new JSONObject();
		Date date = new Date()
		
		Attributes.put ("@id","ForumCreateResponse"+date.getTime())
		Attributes.put ("begin",date.getTime())
		Attributes.put ("end",date.getTime())
		Attributes.put ("@type","m:ForumCreateResponse")
		
		// Cours Information
		Attributes.put ("m:coursName", "forum.cours.getNom()")
		Attributes.put ("m:coursID", "forum.cours.getId()" )
		// Ressource Information
		Attributes.put ("m:ressourceID", "forum.id")
		Attributes.put ("m:ressourceName", "forum.nom")
		Attributes.put ("m:ressourceType", "forum.Type")
		Attributes.put ("m:ressourcePath", "forum.Path")
		Attributes.put ("m:ressourceSujet", "forum.Sujet")
		Attributes.put ("m:ressourceQuestion", "forum.Question")
		Attributes.put ("m:commentaireThrad", comment.thread)
		Attributes.put ("m:commentaireBody", comment.body)
		//ktbsService.createObsel(user.iduser , "Primarytrace"+cours.getNom()+cours.getId(),Attributes)
	}
}
