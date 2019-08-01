package track

import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonSlurper
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import authentication.AuthenticationCalculService
import authentication.AuthenticationStatisticsService

import static org.springframework.http.HttpStatus.NOT_FOUND

//import authentication.decision.DecisionService

class AdminController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	//DecisionService decisionService
	AuthenticationStatisticsService authenticationStatisticsService
	AuthenticationCalculService  authenticationCalculService

	@Secured(['ROLE_ADMIN'])
	def index() {
//		params.max = Math.min(max ?: 10, 100)
//		def listUsers = User.list()
//
//
////		listUsers.each {
////	String idSignature = it.id
////			listUsers.each {
////			String idProfil = it.id
////				authenticationCalculService.authentificationClassic(idSignature,idProfil)
////			}
////		}
//		def authlist = AuthenticationResult.findAllByTypeModalitie("BIOMETRIC");
//		println (authlist);
//		//def authlist = AuthenticationResult.list()
		render(view: '/app/admin/index', model:[msg:'Chihab'])
	}

	@Secured(['ROLE_ADMIN'])
	def userList(Integer max) {
		params.max = Math.min(max ?: 10, 100)
        def listUsers = User.list(params)
		ArrayList<Tuple> usersTuples = new ArrayList<Tuple>()



		listUsers.each {
			int signatureAuth = 0
			int profileAuth = 0
			signatureAuth = AuthenticationResult.findAllByUserSignatureAndTypeAuthAndTypeModalitie(it,params.typeAuth,params.type)?.size()
			profileAuth = AuthenticationResult.findAllByUserProfileAndTypeAuthAndTypeModalitie(it,params.typeAuth,params.type)?.size()

			Tuple userTuple = new Tuple(it, signatureAuth, profileAuth)

			usersTuples.add(userTuple)
		}

		render(view: '/app/admin/authenticationResults/userList', model:[usersTuples:usersTuples, userInstanceList: listUsers,
																		 userInstanceCount: User.count(),
																		  typeModality: params.type
																		  ,typeAuth:params.typeAuth])
	}



	@Secured(['ROLE_ADMIN'])
	def authenticationResults(User userInstance) {
		if (userInstance == null) {
			notFound()
			return
		}


		List<AuthenticationResult> authenticationResults = AuthenticationResult.findAllByUserSignatureOrUserProfile(userInstance, userInstance,[max: 100, sort: "start", order: "asc", offset: 0])


		authenticationResults.removeAll{!(it.typeModalitie.equals(params.type) && it.typeAuth.equals(params.typeAuth))}
//		List<ObselSessionTrust> listObselSessionTrust = ObselSessionTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "id", order: "asc", offset: 0])

//		println(" heree size obsels ="+authenticationResults.get(0).obselSessionTrusts.size())
		render(view: '/app/admin/authenticationResults/resultsForUser', model:[authenticationResults: authenticationResults,
																			   userInstance: userInstance
												                               ,typeModality: params.type
																			   ])
	}

    @Secured(['ROLE_ADMIN'])
    def authenticationDetail(AuthenticationResult authenticationResult) {
        if (authenticationResult == null) {
            notFound()
            return
        }

		String typeModality=params.type
		if(typeModality.equals("BIOMETRIC"))
		{
			List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "begin", order: "asc", offset: 0])

			ArrayList<Float> listTrusts = new ArrayList<Float>()

			listObselTrust.each
					{
						listTrusts.add(it.trust)
					}

			listTrusts.reverse()




			//render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts, distanceMean:distanceMean, distanceMedian:distanceMedian, distanceVariance:distanceVariance, distanceStandardDeviation:distanceStandardDeviation])
			render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts])

		}
		else if(typeModality.equals("ENVAPP"))
		{
			List<ObselSessionTrust> listObselSessionTrust = ObselSessionTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "id", order: "asc", offset: 0])

			ArrayList<Float> listTrusts = new ArrayList<Float>()
			ArrayList<String> detailsDist = new ArrayList<String>()
			ArrayList<String> oldBary = new ArrayList<String>()
			ArrayList<String> newBary = new ArrayList<String>()
			ArrayList<String> oldBaryAdd = new ArrayList<String>()
			ArrayList<String> newBaryAdd = new ArrayList<String>()
			def distanceDetails


			def jsonSlurper = new JsonSlurper()

			listObselSessionTrust.each
					{
						listTrusts.add(it.trust)

						String distance="",ancienBary,nouveauBary,ancienBaryAdd=""
						String nouveauBaryAdd=""


						try {
							String formattedDistance="{"+'''"distances"'''+":"+it.detailsDistances+"}"
//							println(" Formatted JSON : "+formattedDistance)

							JSONObject jsonObject = new JSONObject(formattedDistance)

							JSONArray jsonArray = jsonObject.getJSONArray("distances")
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject explrObject = jsonArray.getJSONObject(i)


								distance+=" ClusterID     	: "+explrObject.get("clusterID ").toString().trim()
								distance+=" \nSimularité "+explrObject.get("Simularité").toString().trim()
								distance+=" -----------------------"




							}

							detailsDist.add(distance)


//							println(" Formatted JSON : "+formattedDistance)

							ancienBary=""
							ancienBaryAdd=""
							if(it.ancienbarycentre!=null)
							{
								jsonObject = new JSONObject(it.ancienbarycentre)


//								println(" JSON obj m:poid : ="+jsonObject.get("m:poid"))
//								println(" JSON obj m:dayOfWeek : ="+jsonObject.get("m:dayOfWeek"))
//								println(" JSON obj m:persistantID : ="+jsonObject.get("m:persistantID"))
//								println(" JSON obj @type        : ="+jsonObject.get("@type"))

								ancienBary+=" m:name \n "+jsonObject.get("m:name")
								ancienBary+="\n m:poid \n "+jsonObject.get("m:poid")

								ancienBaryAdd+="  m:dayOfWeek    =  "+jsonObject.get("m:dayOfWeek")
								ancienBaryAdd+="\n "
								ancienBaryAdd+=" m:hoursOfDay   =  "+jsonObject.get("m:hoursOfDay")
								ancienBaryAdd+="\n "
								ancienBaryAdd+=" m:persistantID =  "+jsonObject.get("m:persistantID")
								ancienBaryAdd+="\n "
									ancienBaryAdd+=" m:adressIP     =  "+jsonObject.get("m:adressIP")
									ancienBaryAdd+="\n "
									ancienBaryAdd+=" @type 	 =  "+jsonObject.get("@type")
									ancienBaryAdd+="\n "
									ancienBaryAdd+=" m:listSession  = \n   "
									String[] ary = jsonObject.get("m:listSession").toString().split(",")
									ary.each { s->
										ancienBaryAdd+=s+","+"\n   "

									}
									ancienBaryAdd+="]"

							}

							oldBary.add(ancienBary)
							oldBaryAdd.add(ancienBaryAdd)

							nouveauBary=""
							nouveauBaryAdd=""

							if(it.nouveaubarycentre!=null)
							{
								jsonObject = new JSONObject(it.nouveaubarycentre)



								nouveauBary+=" m:name \n "+jsonObject.get("m:name")
								nouveauBary+="\n m:poid \n "+jsonObject.get("m:poid")

								nouveauBaryAdd+="  m:dayOfWeek    =  "+jsonObject.get("m:dayOfWeek")
								nouveauBaryAdd+="\n "
								nouveauBaryAdd+=" m:hoursOfDay   =  "+jsonObject.get("m:hoursOfDay")
								nouveauBaryAdd+="\n "
								nouveauBaryAdd+=" m:persistantID =  "+jsonObject.get("m:persistantID")
								nouveauBaryAdd+="\n "
								nouveauBaryAdd+=" m:adressIP     =  "+jsonObject.get("m:adressIP")
								nouveauBaryAdd+="\n "
								nouveauBaryAdd+=" @type 	 =  "+jsonObject.get("@type")
								nouveauBaryAdd+="\n "
								nouveauBaryAdd+=" m:listSession  = \n   "
								String[] ary2 = jsonObject.get("m:listSession").toString().split(",")
								ary2.each { s->
									nouveauBaryAdd+=s+","+"\n   "

								}
								nouveauBaryAdd+="]"

							}

							newBary.add(nouveauBary)
							newBaryAdd.add(nouveauBaryAdd)


						}catch (JSONException err){
							println("Error"+err.toString())
						}






//						def dists = jsonSlurper.parseText(it.detailsDistances)
//						with (dists){
//							println(" Id_C)
//
//						}
//						distanceDetails.add(dists)

					}

			listTrusts.reverse()


			//render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts, distanceMean:distanceMean, distanceMedian:distanceMedian, distanceVariance:distanceVariance, distanceStandardDeviation:distanceStandardDeviation])
			render(view: '/app/admin/authenticationResults/authDetailObselSession', model:[listObselTrust: listObselSessionTrust,
																						   authenticationResult: authenticationResult,
																						   listTrusts: listTrusts, distances:detailsDist,
																						   oldBaryCentre:oldBary,oldBaryCentreAdd:oldBaryAdd
																						   , newBaryCentre:newBary,newBaryCentreAdd:newBaryAdd])
		}


    }

	@Secured(['ROLE_ADMIN'])
	def calculateAuthenticationStatistics(AuthenticationResult authenticationResult)
	{
		if(authenticationResult == null)
		{
			notFound()
			return
		}

		authenticationStatisticsService.calculateStats(authenticationResult)
		 
		redirect(controller: "Admin", action: "authenticationDetail", id: authenticationResult.id )
	}
	@Secured(['ROLE_ADMIN'])
	def calculateAuthenticationStatisticsDash(AuthenticationResult authenticationResult)
	{
		if(authenticationResult == null)
		{
			notFound()
			return
		}

		authenticationStatisticsService.calculateStats(authenticationResult)
		 
		render(view: '/app/admin/index', model:[userInstanceList: User.list(params), userInstanceCount: User.count(), authResult:AuthenticationResult.list()])
	}

    @Secured(['ROLE_ADMIN'])
    def manualAuthenticationIndex() {

        //render "Signature user id is [" + idSignatureUser + "] \n Profile user id is [" + idProfileUser + "]"
        render(view: '/app/admin/manualAuthentication/index', model:[])
    }

	@Secured(['ROLE_ADMIN'])
	def manuallyAuthenticateUser()
    {
        render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
		JSONObject objResultAuth = authenticationCalculService.authentificationClassic(params.signatureUser,params.profileUser)
		render(objResultAuth)
		//render(view: '/app/admin/manualAuthentication/index', model:[])
	}
	@Secured(['ROLE_ADMIN'])
	def manuallyAuthenticateUserDash()
	{
		//println ("dash")
		//render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
		authenticationCalculService.authentificationClassic (params.signatureUser,params.profileUser)
		render(view: '/app/admin/index', model:[userInstanceList: User.list(params), userInstanceCount: User.count(), authResult:AuthenticationResult.list()])
	}
	@Secured(['ROLE_ADMIN'])
//	def manuallyAuthenticateDynamicUser()
//	{
//		render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
//		JSONObject objResultAuth = authenticationCalculService.authentificationDynamic (params.signatureUser,params.profileUser)
//		render(objResultAuth)
//		//render(view: '/app/admin/manualAuthentication/index', model:[])
//	}
	def manuallyAuthenticateBiometricUser()
	{
//		render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
		AuthenticationResult authenticationResultObj = authenticationCalculService.authentificationBiometric (params.signatureUser,params.profileUser)

		AuthenticationResult authenticationResult=AuthenticationResult.findById(authenticationResultObj.id)

		List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "begin", order: "asc", offset: 0])

		ArrayList<Float> listTrusts = new ArrayList<Float>()

		listObselTrust.each
				{
					listTrusts.add(it.trust)
				}

		listTrusts.reverse()




		//render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts, distanceMean:distanceMean, distanceMedian:distanceMedian, distanceVariance:distanceVariance, distanceStandardDeviation:distanceStandardDeviation])
		render(view: '/app/admin/manualAuthentication/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts])


//		render(view: '/app/admin/manualAuthentication/index', model:[])
	}
	@Secured(['ROLE_ADMIN'])
	def manuallyAuthenticateEnvAppUser()
   {

	 AuthenticationResult authenticationResultObj=authenticationCalculService.authentificationEnvApp (params.signatureUser,params.profileUser)

     AuthenticationResult authenticationResult=AuthenticationResult.findById(authenticationResultObj.id)

	 List<ObselSessionTrust> listObselSessionTrust = ObselSessionTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "id", order: "asc", offset: 0])

	 ArrayList<Float> listTrusts = new ArrayList<Float>()
	 ArrayList<String> detailsDist = new ArrayList<String>()
	 ArrayList<String> oldBary = new ArrayList<String>()
	 ArrayList<String> newBary = new ArrayList<String>()
	 ArrayList<String> oldBaryAdd = new ArrayList<String>()
	 ArrayList<String> newBaryAdd = new ArrayList<String>()
	 def distanceDetails



	 listObselSessionTrust.each
			 {
				 listTrusts.add(it.trust)

				 String distance="",ancienBary,nouveauBary,ancienBaryAdd=""
				 String nouveauBaryAdd=""


				 try {
					 String formattedDistance="{"+'''"distances"'''+":"+it.detailsDistances+"}"

					 JSONObject jsonObject = new JSONObject(formattedDistance)

					 JSONArray jsonArray = jsonObject.getJSONArray("distances")
					 for (int i = 0; i < jsonArray.length(); i++) {
						 JSONObject explrObject = jsonArray.getJSONObject(i)


						 distance+=" ClusterID     	: "+explrObject.get("clusterID ").toString().trim()
						 distance+=" \nSimularité "+explrObject.get("Simularité").toString().trim()
						 distance+=" -----------------------"




					 }

					 detailsDist.add(distance)


					 ancienBary=""
					 ancienBaryAdd=""
					 if(it.ancienbarycentre!=null)
					 {
						 jsonObject = new JSONObject(it.ancienbarycentre)



						 ancienBary+=" m:name \n "+jsonObject.get("m:name")
						 ancienBary+="\n m:poid \n "+jsonObject.get("m:poid")

						 ancienBaryAdd+="  m:dayOfWeek    =  "+jsonObject.get("m:dayOfWeek")
						 ancienBaryAdd+="\n "
						 ancienBaryAdd+=" m:hoursOfDay   =  "+jsonObject.get("m:hoursOfDay")
						 ancienBaryAdd+="\n "
						 ancienBaryAdd+=" m:persistantID =  "+jsonObject.get("m:persistantID")
						 ancienBaryAdd+="\n "
						 ancienBaryAdd+=" m:adressIP     =  "+jsonObject.get("m:adressIP")
						 ancienBaryAdd+="\n "
						 ancienBaryAdd+=" @type 	 =  "+jsonObject.get("@type")
						 ancienBaryAdd+="\n "
						 ancienBaryAdd+=" m:listSession  = \n   "
						 String[] ary = jsonObject.get("m:listSession").toString().split(",")
						 ary.each { s->
							 ancienBaryAdd+=s+","+"\n   "

						 }
						 ancienBaryAdd+="]"

					 }

					 oldBary.add(ancienBary)
					 oldBaryAdd.add(ancienBaryAdd)

					 nouveauBary=""
					 nouveauBaryAdd=""

					 if(it.nouveaubarycentre!=null)
					 {
						 jsonObject = new JSONObject(it.nouveaubarycentre)



						 nouveauBary+=" m:name \n "+jsonObject.get("m:name")
						 nouveauBary+="\n m:poid \n "+jsonObject.get("m:poid")

						 nouveauBaryAdd+="  m:dayOfWeek    =  "+jsonObject.get("m:dayOfWeek")
						 nouveauBaryAdd+="\n "
						 nouveauBaryAdd+=" m:hoursOfDay   =  "+jsonObject.get("m:hoursOfDay")
						 nouveauBaryAdd+="\n "
						 nouveauBaryAdd+=" m:persistantID =  "+jsonObject.get("m:persistantID")
						 nouveauBaryAdd+="\n "
						 nouveauBaryAdd+=" m:adressIP     =  "+jsonObject.get("m:adressIP")
						 nouveauBaryAdd+="\n "
						 nouveauBaryAdd+=" @type 	 =  "+jsonObject.get("@type")
						 nouveauBaryAdd+="\n "
						 nouveauBaryAdd+=" m:listSession  = \n   "
						 String[] ary2 = jsonObject.get("m:listSession").toString().split(",")
						 ary2.each { s->
							 nouveauBaryAdd+=s+","+"\n   "

						 }
						 nouveauBaryAdd+="]"

					 }

					 newBary.add(nouveauBary)
					 newBaryAdd.add(nouveauBaryAdd)


				 }catch (JSONException err){
					 println("Error"+err.toString())
				 }




			 }

	 listTrusts.reverse()


	 //render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts, distanceMean:distanceMean, distanceMedian:distanceMedian, distanceVariance:distanceVariance, distanceStandardDeviation:distanceStandardDeviation])
	 render(view: '/app/admin/manualAuthentication/authDetailObselSession', model:[listObselTrust: listObselSessionTrust,
																					authenticationResult: authenticationResult,
																					listTrusts: listTrusts, distances:detailsDist,
																					oldBaryCentre:oldBary,oldBaryCentreAdd:oldBaryAdd
																					, newBaryCentre:newBary,newBaryCentreAdd:newBaryAdd])


	}
	

	@Secured(['ROLE_ADMIN'])
	def manuallyAuthenticateDynamicUserDash()
	{
		//render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
		authenticationCalculService.authentificationDynamic (params.signatureUser,params.profileUser)
		render(view: '/app/admin/index', model:[userInstanceList: User.list(params), userInstanceCount: User.count(), authResult:AuthenticationResult.list()])
	}

    @Secured(['ROLE_ADMIN'])
    def dashBoard(Integer max) {

		params.max = Math.min(max ?: 10, 100)
		def listUsers = User.list()


//		listUsers.each {
//	String idSignature = it.id
//			listUsers.each {
//			String idProfil = it.id
//				authenticationCalculService.authentificationClassic(idSignature,idProfil)
//			}
//		}
		def authlist = AuthenticationResult.findAllByTypeModalitie("BIOMETRIC");
		println (authlist);
		//def authlist = AuthenticationResult.list()
		render(view: '/app/admin/dashboard', model:[userInstanceList: listUsers, userInstanceCount: User.count(), authResult:authlist])
	}




	// Gestion de la formation



	@Secured(['ROLE_ADMIN'])
	def paramFormation(){



		render(view: '/app/admin/formation/formation_param', model:[])

//		redirect( uri:'/admin/gestion')



	}


	// Cours

	// Add UI
	@Secured(['ROLE_ADMIN'])
	def addCoursUI(Cours coursInstance){

		render(view: '/app/admin/formation/add/add_cours', model:[coursInstance:coursInstance])

	}

	// Add (save)
	@Secured(['ROLE_ADMIN'])
	def addCours(Cours coursInstance){






		if (coursInstance == null) {notFound() ; return }
		if (coursInstance.hasErrors()) {
			render (view:'/app/admin/formation/add/add_cours', model:[coursInstance:coursInstance])
			return
		}


		coursInstance.save()

		Section forumDeCours=new Section(title: coursInstance.nom)
		forumDeCours.save(flush: true)

		Topic topic1=new Topic(title: "Topic 1",section: forumDeCours,description: "topic 1 du Cours "+coursInstance.nom)
		topic1.save()

		Topic topic2=new Topic(title: "Topic 2",section: forumDeCours,description: "topic 2 du Cours "+coursInstance.nom)
		topic2.save()

		listCours()

	}

	// Edit UI
	@Secured(['ROLE_ADMIN'])
	def editCoursUI(){

		Cours cours=Cours.findById(Integer.parseInt(params.cours))
		// delete Module
//		if(params.module!=null)
//		{
//
//			 Module moduleD=Module.findById(Integer.parseInt(params.module))
////			 cours.modules.remove(moduleD)
//			 moduleD.delete(flush: true)
////			 cours.save(flush: true)
//		}


		render(view: '/app/admin/formation/edit/edit_cours', model:[coursInstance:cours,coursId:cours.id])
	}

	// Edit (save)
	@Secured(['ROLE_ADMIN'])
	def editCours(Cours coursInstance,String coursId){




		if (coursInstance == null) {notFound() ; return }


		else {
			Cours cours=Cours.findById(Integer.parseInt(coursId))

			if (coursInstance.hasErrors()) {
				if(coursInstance.errors.getErrorCount()==1)
				{



					if(Cours.findAllByNom(coursInstance.nom)==null)
					{
						cours.nom = coursInstance.nom

					}
					cours.imgUrl=coursInstance.imgUrl
					cours.domaine=coursInstance.domaine
					cours.duree=coursInstance.duree
					cours.competences=coursInstance.competences
					cours.requis=coursInstance.requis

					cours.save flush: true

					listCours()

					return
				}


				render (view:'/app/admin/formation/edit/edit_cours', model:[coursInstance:coursInstance])
				return
			}

			// Changer mm le nom
			else {

				cours.nom = coursInstance.nom
				cours.imgUrl = coursInstance.imgUrl
				cours.domaine = coursInstance.domaine
				cours.duree = coursInstance.duree
				cours.competences = coursInstance.competences
				cours.requis = coursInstance.requis





				cours.save flush: true

				listCours()

				return



			}
		}














	}

	// List
	@Secured(['ROLE_ADMIN'])
	def listCours(){


		ArrayList<Cours> coursDispo=Cours.findAll()

		render(view: '/app/admin/formation/list/list_cours', model:[coursDispo:coursDispo])

	}



	// Module
	// Add
	@Secured(['ROLE_ADMIN'])
	def addModuleUI(Module moduleInstance){

		render(view: '/app/admin/formation/add/add_module', model:[moduleInstance:moduleInstance])

	}

    // Add (save)
    @Secured(['ROLE_ADMIN'])
    def addModule(Module moduleInstance,String coursNom){


        Cours cours=Cours.findByNom(coursNom)

        moduleInstance.cours=cours

        if (moduleInstance == null) {notFound() ; return }
        if (moduleInstance.hasErrors()) {
            render (view:'/app/admin/formation/add/add_module', model:[moduleInstance:moduleInstance])
            return
        }


        moduleInstance.save()

        listModule()

    }

	// List
	@Secured(['ROLE_ADMIN'])
	def listModule(){

		ArrayList<Module> modules=Module.findAll()
		render(view: '/app/admin/formation/list/list_module', model:[modules:modules])

	}
	// Edit UI
	@Secured(['ROLE_ADMIN'])
	def editModuleUI(){

		Module module=Module.findById(Integer.parseInt(params.module))

		render(view: '/app/admin/formation/edit/edit_module', model:[moduleInstance:module,moduleId:module.id])
	}

	// Edit (save)

	@Secured(['ROLE_ADMIN'])
	def editModule(Module moduleInstance,String moduleId,String cours)
	{


		if (moduleInstance == null) {
			notFound() ; return }





		if (moduleInstance.hasErrors()) {

			if(moduleInstance.errors.getErrorCount()==1)
			{
				Module moduleEdited=Module.findById(Integer.parseInt(moduleId))

				println(" avant :"+moduleEdited.cours)

				moduleEdited.nom=moduleInstance.nom
				moduleEdited.duree=moduleInstance.duree
				moduleEdited.cours=Cours.findByNom(cours)
				println(" apreees :"+moduleEdited.cours)


				moduleEdited.save flush: true

				listModule()

				return
			}
			render (view:'/app/admin/formation/edit/edit_module', model:[moduleInstance:moduleInstance])
			return
		}

	}

	// Delete
	// List
	@Secured(['ROLE_ADMIN'])
	def deleteModule(int nbModules){

		render(view: '/app/admin/formation/add/add_module', model:[nbModules:nbModules])

	}
	// Chapitre
	// Add
	@Secured(['ROLE_ADMIN'])
	def addChapitreUI(){

		render(view: '/app/admin/formation/add/add_chapitre', model:[])

	}

	// Add (save)
	@Secured(['ROLE_ADMIN'])
	def addChapitre(Chapitre chapitreInstance,String moduleNom){


		String coursNom=""
		String moduleNom2=""

		boolean coursOk=false,moduleOk=true

		moduleNom.each { c->


			if(c=="/")
			{
				coursOk=true
				moduleOk=false
			}

			else {
				if (!coursOk)
				{
					coursNom+=c
				}

				if (!moduleOk)
				{
					moduleNom2+=c
				}
			}

		}


		println(" Cours = "+coursNom)
		println(" Module = "+moduleNom2)

		Cours cours=Cours.findByNom(coursNom)
		Module module=Module.findByNomAndCours(moduleNom2,cours)

		chapitreInstance.module=module

		if (chapitreInstance == null) {notFound() ; return }
		if (chapitreInstance.hasErrors()) {
			render (view:'/app/admin/formation/add/add_chapitre', model:[chapitreInstance:chapitreInstance])
			return
		}


		chapitreInstance.save()

		listChapitre()

	}


	// Edit UI
	@Secured(['ROLE_ADMIN'])
	def editChapitreUI(){

		Chapitre chapitre=Chapitre.findById(Integer.parseInt(params.chapitre))

		render(view: '/app/admin/formation/edit/edit_chapitre', model:[chapitreInstance:chapitre])


	}

	// Edit (save)

	@Secured(['ROLE_ADMIN'])
	def editChapitre(Module chapitreInstance,String chapitreId,String module)
	{


		if (chapitreInstance == null) {
			notFound() ; return }





		if (chapitreInstance.hasErrors()) {

			if(chapitreInstance.errors.getErrorCount()==1)
			{
				Chapitre chapitreEdited=Chapitre.findById(Integer.parseInt(chapitreId))


				chapitreEdited.nom=chapitreInstance.nom


				chapitreEdited.module=Module.findById(Integer.parseInt(module))


				chapitreEdited.save flush: true

				listChapitre()

				return
			}
			render (view:'/app/admin/formation/edit/edit_module', model:[moduleInstance:chapitreInstance])
			return
		}
	}

	// List
	@Secured(['ROLE_ADMIN'])
	def listChapitre(){

		ArrayList<Chapitre> chapitres=Chapitre.findAll()
		render(view: '/app/admin/formation/list/list_chapitre', model:[chapitres:chapitres])


	}







	//------------------------------------------------------
	// Ressource

	// Add
	@Secured(['ROLE_ADMIN'])
	def addRessourceUI(Ressource ressourceInstance){

		String type=params.type
		render(view: '/app/admin/formation/add/add_ressource', model:[ressourceInstance:ressourceInstance
		                  											 ,type:type])

	}

	// Add ( save )
	@Secured(['ROLE_ADMIN'])
	def addRessource(Ressource ressourceInstance,String type,String chapitre,String contenu,String url,String format){








		if (ressourceInstance == null) {notFound() ; return }
		if (ressourceInstance.hasErrors()) {

			if(type.equals("img") || type.equals("audio") || type.equals("video") )
			{
				type="media"
			}


			render (view:'/app/admin/formation/add/add_ressource', model:[ressourceInstance:ressourceInstance
																		  ,type:type])
			return
		}




		if(type.equals("img") || type.equals("audio") || type.equals("video") )
		{
			type="media"
		}



		if(type.equals("txt"))
		{

			ressourceInstance.path=Chapitre.findById(Integer.parseInt(chapitre)).nom


			TextRessource tR=new TextRessource(nom: ressourceInstance.nom,
												path: Chapitre.findById(Integer.parseInt(chapitre)).toString(),
												chapitre:Chapitre.findById(Integer.parseInt(chapitre)),
												type: ressourceInstance.type,
											    url: ressourceInstance.path,
												contenu: contenu)
			tR.save()


		}


		else if(type.equals("media"))
		{

			ressourceInstance.path=Chapitre.findById(Integer.parseInt(chapitre)).nom


			println(" hnaa format :"+ressourceInstance.type)

			AudioImgVideo tR=new AudioImgVideo(nom: ressourceInstance.nom,
					path: Chapitre.findById(Integer.parseInt(chapitre)).toString(),
					chapitre:Chapitre.findById(Integer.parseInt(chapitre)),
					type: ressourceInstance.type,
					url: url,
					format: ressourceInstance.type)
			tR.save()

		}
		else if(type.equals("quiz"))
		{

			ressourceInstance.path=Chapitre.findById(Integer.parseInt(chapitre)).nom


			Quiz tR=new Quiz(nom: ressourceInstance.nom,
					path: Chapitre.findById(Integer.parseInt(chapitre)).toString(),
					chapitre:Chapitre.findById(Integer.parseInt(chapitre)),
					type: ressourceInstance.type,
					typeExo: ressourceInstance.type
			)


			tR.save()


			listRessource("quiz")
			return

		}

		listChapitre()


	}

	// Edit UI
	@Secured(['ROLE_ADMIN'])
	def editRessourceUI(){

		Ressource ressource=Ressource.findById(Integer.parseInt(params.ressource))


		render(view: '/app/admin/formation/edit/edit_ressource', model:[ressourceInstance:ressource,
																		type:params.type,
																		resId:ressource.id])


	}

	// Edit (save)

	@Secured(['ROLE_ADMIN'])
	def editRessource(Ressource ressourceInstance,String resId,String chapitre,String type,String contenu,String url)
	{


		if (ressourceInstance == null) {
			notFound() ; return }



		if (ressourceInstance.hasErrors()) {


			render (view:'/app/admin/formation/edit/edit_ressource', model:[moduleInstance:ressourceInstance])
			return
		}




		if(type.equals("txt"))
		{

			TextRessource ressEdited=TextRessource.findById(Integer.parseInt(resId))

			println(" avant :"+ressEdited.chapitre)

			ressEdited.nom=ressourceInstance.nom
			ressEdited.chapitre=Chapitre.findById(Integer.parseInt(chapitre))
			ressEdited.path=ressEdited.chapitre.toString()
			ressEdited.type=type
			ressEdited.url=ressEdited.chapitre.toString()
			ressEdited.contenu=contenu

			println(" apreees :"+ressEdited.chapitre)


			ressEdited.save flush: true

			listRessource("txt")

			return



		}


		else if(type.equals("img") || type.equals("audio") || type.equals("video"))
		{



			AudioImgVideo ressEdited=AudioImgVideo.findById(Integer.parseInt(resId))

			println(" avant :"+ressEdited.chapitre)

			ressEdited.nom=ressourceInstance.nom
			ressEdited.chapitre=Chapitre.findById(Integer.parseInt(chapitre))
			ressEdited.path=ressEdited.chapitre.toString()
			ressEdited.type=type
			ressEdited.url=url
			ressEdited.format=type

			println(" apreees :"+ressEdited.chapitre)


			ressEdited.save flush: true

			listRessource("media")

			return
		}
		else if(type.equals("quiz"))
		{

			Quiz ressEdited=Quiz.findById(Integer.parseInt(resId))

			println(" avant :"+ressEdited.chapitre)

			ressEdited.nom=ressourceInstance.nom
			ressEdited.chapitre=Chapitre.findById(Integer.parseInt(chapitre))
			ressEdited.path=ressEdited.chapitre.toString()
			ressEdited.type=type
			ressEdited.typeExo="quiz"

			println(" apreees :"+ressEdited.chapitre)


			ressEdited.save flush: true

			listRessource("quiz")
			return

		}






	}

	// List Questions
	@Secured(['ROLE_ADMIN'])
	def listQuestions(String quizId){

		String quiz=params.quiz

		if(quiz==null)
		{
			quiz=quizId
		}

		Quiz quizR=Quiz.findById((long)Integer.parseInt(quiz))

		ArrayList<Question> questions=Question.findAllByQuizR(quizR)
		render(view: '/app/admin/formation/list/list_question', model:[questions:questions,qID:quiz])

		return
	}

	// Edit UI
	@Secured(['ROLE_ADMIN'])
	def editQuestionUI(){

		Question question=Question.findById(Integer.parseInt(params.question))
		String idAnswer=question.answer





		render(view: '/app/admin/formation/edit/edit_question', model:[question: question
																	  ,answer:idAnswer])


	}
	// Add Question UI

	@Secured(['ROLE_ADMIN'])
	def addQuestionUI(Question questionInstance)
	{

		render(view: '/app/admin/formation/add/add_question', model:[questionInstance:questionInstance,
																	 quizId:params.quizId])

	}


	// Add Question (save)

	@Secured(['ROLE_ADMIN'])
	def addQuestion(Question questionInstance,String quizId)
	{

		if (questionInstance == null) {notFound() ; return }




		if (questionInstance.hasErrors()) {
			// Quiz inconnu
			if(questionInstance.errors.getErrorCount()==1)
			{
				Quiz quiz=Quiz.findById((long)Integer.parseInt(quizId))

				questionInstance.quizR=quiz

				questionInstance.save()

				listQuestions(quizId)

				return

			}
			render (view:'/app/admin/formation/add/add_question', model:[questionInstance:questionInstance])
			return
		}





	}

	// List
	@Secured(['ROLE_ADMIN'])
	def listRessource(String typeR){

		String type=params.type



		println(" typee ="+type)
		println(" typee R ="+typeR)


		if(type==null || type.isEmpty())
		{
			type=typeR
		}

		if(typeR!=null)
		{
			type=typeR
		}


		if(!type.isEmpty())
		{
			if(type.equals("media"))
			{
				ArrayList<AudioImgVideo> ressources
				ressources=Ressource.findAllByTypeInList(["img", "video","audio"])
				render(view: '/app/admin/formation/list/list_ressource', model:[ressources:ressources,type:type])

			}
			else {
				if(type.equals("txt"))
				{
					ArrayList<AudioImgVideo> ressources
					ressources=Ressource.findAllByType(type)
					render(view: '/app/admin/formation/list/list_ressource', model:[ressources:ressources,type:type])

				}
				else if(type.equals("quiz")) {
					ArrayList<Quiz> ressources
					ressources=Ressource.findAllByType(type)
					render(view: '/app/admin/formation/list/list_ressource', model:[ressources:ressources,type:type])

				}
				else {
					paramFormation()

				}

			}
		}






	}

	//------------------------------------------------------

	@Secured(['ROLE_ADMIN'])
	def uploadImg(){
		println(" yoooho Done upload !")

		if(params.cfile){
			if(params.cfile instanceof org.springframework.web.multipart.commons.CommonsMultipartFile){
				new FileOutputStream('d:/submitted_file').leftShift( params.cfile.getInputStream() );
				println("good attachment type [${params.cfile.getClass()}]")

				//params.cfile.transferTo(new File('D:/submitted_file'));
			}else{
				println("wrong attachment type [${params.cfile.getClass()}]")
				println("Valeur of file = ["+params.cfile+"]")
			}
		}
	}

	@Secured(['ROLE_ADMIN'])
	def gestionUsers(Integer max){


		params.max=5

		def userList=User.list(params)



		render(view: '/app/admin/formation/list/list_user' , model: [users:userList])
//		redirect( uri:'/admin/gestion/list/user')

	}



	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}


}
