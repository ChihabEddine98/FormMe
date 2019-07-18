package track

class Ressource {


    String nom
    String type
    String path
    Chapitre chapitre

    static belongsTo = [Chapitre]

    static constraints = {
    }
}
