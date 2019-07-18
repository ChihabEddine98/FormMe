package track

import grails.plugin.springsecurity.annotation.Secured

import org.codehaus.groovy.grails.web.json.JSONObject

import authentication.AuthenticationCalculService
import authentication.AuthenticationStatisticsService
import authentication.decision.DecisionService

class AdminController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	DecisionService decisionService
	AuthenticationStatisticsService authenticationStatisticsService
	AuthenticationCalculService  authenticationCalculService

	@Secured(['ROLE_ADMIN'])
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def listUsers = User.list()
		
		
//		listUsers.each {
//	String idSignature = it.id
//			listUsers.each {
//			String idProfil = it.id
//				authenticationCalculService.authentificationClassic(idSignature,idProfil)
//			}
//		}
		def authlist = AuthenticationResult.findAllByType("CLASSIC");
		println (authlist);
		//def authlist = AuthenticationResult.list()
		render(view: '/app/admin/index', model:[userInstanceList: listUsers, userInstanceCount: User.count(), authResult:authlist])
	}

	@Secured(['ROLE_ADMIN'])
	def userList(Integer max) {
		params.max = Math.min(max ?: 10, 100)
        def listUsers = User.list(params)
		ArrayList<Tuple> usersTuples = new ArrayList<Tuple>()

		listUsers.each {
			int signatureAuth = 0
			int profileAuth = 0
			signatureAuth = AuthenticationResult.findAllByUserSignature(it)?.size()
			profileAuth = AuthenticationResult.findAllByUserProfile(it)?.size()

			Tuple userTuple = new Tuple(it, signatureAuth, profileAuth)

			usersTuples.add(userTuple)
		}

		render(view: '/app/admin/authenticationResults/userList', model:[usersTuples:usersTuples, userInstanceList: listUsers, userInstanceCount: User.count()])
	}

	@Secured(['ROLE_ADMIN'])
	def authenticationResults(User userInstance) {
		if (userInstance == null) {
			notFound()
			return
		}

		List<AuthenticationResult> authenticationResults = AuthenticationResult.findAllByUserSignatureOrUserProfile(userInstance, userInstance,[max: 100, sort: "start", order: "desc", offset: 0])
		render(view: '/app/admin/authenticationResults/resultsForUser', model:[authenticationResults: authenticationResults, userInstance: userInstance])
	}

    @Secured(['ROLE_ADMIN'])
    def authenticationDetail(AuthenticationResult authenticationResult) {
        if (authenticationResult == null) {
            notFound()
            return
        }

        List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "begin", order: "desc", offset: 0])

        ArrayList<Float> listTrusts = new ArrayList<Float>()

		listObselTrust.each
		{
            listTrusts.add(it.trust)
        }

		listTrusts.reverse()


		//render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts, distanceMean:distanceMean, distanceMedian:distanceMedian, distanceVariance:distanceVariance, distanceStandardDeviation:distanceStandardDeviation])
		render(view: '/app/admin/authenticationResults/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult, listTrusts: listTrusts])
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
	def manuallyAuthenticateDynamicUser()
	{
		render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
		JSONObject objResultAuth = authenticationCalculService.authentificationDynamic (params.signatureUser,params.profileUser)
		render(objResultAuth)
		//render(view: '/app/admin/manualAuthentication/index', model:[])
	}
	@Secured(['ROLE_ADMIN'])
	def manuallyAuthenticateDynamicUserDash()
	{
		//render "Signature user id is [" + params.signatureUser + "] \n Profile user id is [" + params.profileUser + "]"
		authenticationCalculService.authentificationDynamic (params.signatureUser,params.profileUser)
		render(view: '/app/admin/index', model:[userInstanceList: User.list(params), userInstanceCount: User.count(), authResult:AuthenticationResult.list()])
	}

    @Secured(['ROLE_ADMIN'])
    def dashBoard() {

        render(view: '/app/admin/', model:[])
    }



	@Secured(['ROLE_ADMIN'])
	def paramFormation(){


		redirect( uri:'/admin/gestion')



	}


	@Secured(['ROLE_ADMIN'])
	def addModule(){


		render(view: '/app/admin/formation/add_module', model:[nbModules:params.nbModules])

	}



	@Secured(['ROLE_ADMIN'])
	def gestionUsers(){


		redirect( uri:'/admin/gestion/list/user')

	}

}
