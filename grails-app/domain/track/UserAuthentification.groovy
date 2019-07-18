package track


import groovy.transform.ToString



class UserAuthentification implements Serializable{
	
		private static final long serialVersionUID = 1
	
		float trust 
		float trustDynamic
		int lastObselProfil 
		int lastObselProfilDynamic
	
		
		static belongsTo = [user: User]
		
		static constraints = {
			
		}
	
		static mapping = {
			
		}
		
}
