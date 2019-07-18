package track

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String firstname
	String lastname
	//String username
	String email
	String password
	String iduser = ""
	String primaryTraceName = ""
	String modelTraceName = ""
	String profilTraceName = ""
	String signatureTrace = ""
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	UserAuthentification userauthentification

	String imgUrl=""
	char sexe='m'


	// Formation en ligne " données "



	static hasMany = [cours:Cours]


	String toString(){

		return (firstname+" "+lastname)
	}


	User(String firstname , String lastname , String email, String password) {
		//this.username = username
		this.email = email
		this.password = password
		this.firstname = firstname
		this.lastname = lastname
	}
    def setTraceName (String primaryTraceName){
		this.primaryTraceName = primaryTraceName;
	}
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		//username blank: false
		email  blank: false, unique: true, email: true
		password blank: false, nullable: false, password: true, matches: "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+\$).{8,}\$"
		firstname blank: false
		lastname blank: false
		userauthentification nullable: true
		imgUrl nullable: true


	}

	static mapping = {
		password column: '`password`'


	}
}
