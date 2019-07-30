package track

class Module {




    Integer id
    String nom
    Cours cours
    Long duree


    static belongsTo = Cours
//
//
    static hasMany = [chapitres:Chapitre]

    static constraints = {
        id(unique: true)
        nom(nullable: false)
        cours(nullable: true)

    }

    static mapping = {

    }


    String toString(){

        return cours.toString()+"/"+nom
    }

}
