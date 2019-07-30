package authentication

import grails.transaction.Transactional

import org.json.JSONObject

import track.ObselTrust
import track.User
import authentication.modality.EnvironnementAndSessionApprentissageService
import authentication.modality.KDandMDModalityService

@Transactional

class AuthenticationCalculService {

	def KDandMDModalityService
	def EnvironnementAndSessionApprentissageService
	
//	def authentificationClassic ( String signatureUserID, String profilUserID){
//		
//		
//		User signatureUser = User.findById(signatureUserID)
//		User profileUser = User.findById(profilUserID)
//		JSONObject objResultAuth = decisionService.authenticate(profileUser, signatureUser, "CLASSIC")
//        return objResultAuth;
//	}
//	def authentificationDynamic (signatureUserID, profilUserID){
//		System.out.println ("dynamicservice")
//		User signatureUser = User.findById(signatureUserID)
//		User profileUser = User.findById(profilUserID)
//		JSONObject objResultAuth = decisionService.authenticate (profileUser, signatureUser, "DYNAMIC")
//		return objResultAuth;
//	}
	
	/**
	 * For Admin Interface
	 * @param signatureUserID
	 * @param profilUserID
	 * @return
	 */
	def authentificationBiometric ( String signatureUserID, String profilUserID){
		
		KDandMDModalityService kDandMDModalityService
		//User signatureUser = User.findById(signatureUserID)
		//User profileUser = User.findById(profilUserID)
		User signatureUser = User.findByEmail(signatureUserID)
		User profileUser = User.findByEmail(profilUserID)
		
		
		JSONObject objResultAuth = KDandMDModalityService.CalculTrustKDandMDForTwoUSer(profileUser, signatureUser)
		return objResultAuth;
	}
	def authentificationEnvApp ( String signatureUserID, String profilUserID){
		
		
	
		User signatureUser = User.findByEmail(signatureUserID)
		User profileUser = User.findByEmail(profilUserID)
		
		
		EnvironnementAndSessionApprentissageService.AuthentificationEnvAprrTwoUSer(profileUser, signatureUser)
		
		
	}
	
}
