package track

class Ressource {


    String nom
    String type
    String path=chapitre.toString()
    Chapitre chapitre

    static belongsTo = [Chapitre]

    static constraints = {

        type( inList: ["audio","txt", "img", "video","quiz"])
    }
}
