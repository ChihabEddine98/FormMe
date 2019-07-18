package track

class Question {



    String question
    String answer
    String sugg1
    String sugg2
    String sugg3
    String sugg4
    Ressource quizR

    static belongsTo = [quizR:Ressource]

    static constraints = {
        id(unique: true)


    }

    String toString(){

        return (" Question "+id)
    }
}
