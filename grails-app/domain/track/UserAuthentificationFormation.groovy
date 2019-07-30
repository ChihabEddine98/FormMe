package track

class UserAuthentificationFormation {

	User user 
	Cours cours
	float trustBIOMETRIC =0
	float trustCompApp =0
	float trustActionsApp =0
	String lastObselProfilBIOMETRIC =""
	static hasMany = [ authenticationResult : AuthenticationResult  ]
	String listTrustBiometric ="[]"
     static constraints = {
		listTrustBiometric  size: 2..15000
    }
	static mapping = {
	
		listTrustBiometric    sqlType: 'longText'
		
	}
}
