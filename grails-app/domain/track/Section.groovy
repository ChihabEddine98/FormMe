package track

class Section {



    static hasMany = [topics:Topic]
    String title




    String toString(){

        String res=title
        return res
    }

    static constraints = {
    }
}
