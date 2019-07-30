package track

class Cours {

    Integer id
    String nom
    String imgUrl
    String domaine
    Long duree
    String requis
    String competences









    static hasMany = [modules:Module,users:User]
    static belongsTo = [User]





    static constraints = {
        id(unique: true)
        nom(nullable: false,unique: true)
        imgUrl(nullable: true)

    }

    String toString(){

        return nom
    }

}
