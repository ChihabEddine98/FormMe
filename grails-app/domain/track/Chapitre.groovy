package track


class Chapitre {


    Integer id
    String nom
    Module module


    static belongsTo = Module
    static hasMany = [Ressource]


    static constraints = {
        id(unique: true)
        nom(nullable: false)
        module(nullable: true)


    }


    String toString(){
        return module.toString()+"/"+nom
    }

}
