package authentication

import grails.transaction.Transactional

import org.codehaus.groovy.grails.web.json.JSONObject

import track.ObselTrust
import track.User
import authentication.decision.DecisionService

@Transactional

class AuthenticationCalculService {

	DecisionService decisionService
	
	def authentificationClassic ( String signatureUserID, String profilUserID){
		
		
		User signatureUser = User.findById(signatureUserID)
		User profileUser = User.findById(profilUserID)
		JSONObject objResultAuth = decisionService.authenticate(profileUser, signatureUser, "CLASSIC")
        return objResultAuth;
	}
	def authentificationDynamic (signatureUserID, profilUserID){
		System.out.println ("dynamicservice")
		User signatureUser = User.findById(signatureUserID)
		User profileUser = User.findById(profilUserID)
		JSONObject objResultAuth = decisionService.authenticate (profileUser, signatureUser, "DYNAMIC")
		return objResultAuth;
	}
	
}
