package track

class ObselSessionTrust {

    String idAction
	String sessionID=" "
    float distance
    float trust
    String ancienbarycentre =""
	String nouveaubarycentre =""
	float score 
	String detailsDistances ="[]"


    static belongsTo = [authenticationResult: AuthenticationResult]

    
	static constraints = {
		
		//ancienbarycentre   size: 2..15000
		nouveaubarycentre  size: 2..15000
		ancienbarycentre nullable: true
		nouveaubarycentre nullable: true
		detailsDistances   size: 2..15000
		sessionID nullable : true 
	
	}
    static mapping = {
       
        idAction sqlType: 'varchar(170)'
		ancienbarycentre    sqlType: 'longText'
		nouveaubarycentre    sqlType: 'longText'
		detailsDistances    sqlType: 'longText'
        
    }
	
}
