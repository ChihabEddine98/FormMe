package track

class Quiz extends Exercice{


    String nom
    static hasMany = [questions:Question]


    static constraints = {
        id(unique: true)

    }

    String toString(){

        return nom
    }
}
