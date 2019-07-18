package track

class Comment {

    static belongsTo = DiscussionThread
    DiscussionThread thread
    User commentBy
    String body
    Date createDate = new Date()



    static constraints = {
        body( maxSize: 8000)

    }


    String toString(){

        String res="par :"+commentBy + " le : " + createDate
        return res
    }

}
