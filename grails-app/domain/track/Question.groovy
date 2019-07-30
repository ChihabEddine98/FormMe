package track

class Question {



    String question
    String sugg1
    String sugg2
    String sugg3
    String sugg4

    String answer
    Ressource quizR

    static belongsTo = [quizR:Ressource]

    static constraints = {
        id(unique: true)

        answer( inList: ["1", "2","3","4"])


    }

    String toString(){

        return (" Question "+id)
    }
}
