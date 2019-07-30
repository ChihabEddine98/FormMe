package track
import java.io.Serializable;

import groovy.transform.ToString
class UserAuthentificationEnvApp implements Serializable {

	
	float trustEnvApp  =0
	float ancientrustEnvApp  =0
	String listTrustEnvApp ="[]"
	static belongsTo = [user: User]
	AuthenticationResult  authenticationResult
    static constraints = {
		listTrustEnvApp  size: 2..15000
    }
	static mapping = {
	
		listTrustEnvApp    sqlType: 'longText'
		
	}
}
