package track

class ObselTrust implements Serializable {

    String idAction
    String typeAction
    float distance
    float trust
    long begin
    long end

    static belongsTo = [authenticationResult: AuthenticationResult]

    static constraints = {

    }

    static mapping = {
        version false // No concurrent access possible in writing because no update possible (only write once)
        idAction sqlType: 'varchar(250)'
        typeAction sqlType: 'varchar(50)'
    }

}
