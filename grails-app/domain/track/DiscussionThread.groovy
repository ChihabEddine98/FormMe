package track

class DiscussionThread {


    static belongsTo = Topic
    static hasMany = [comments:Comment]
    Topic topic
    String subject
    User opener
    Date createDate = new Date()




    public long getNumberOfReplies() {
        Comment.countByThread(this)
    }

    static constraints = {
    }

    String toString(){

        String res= subject+"     ##-Par : "+opener+ "          "
        return res
    }
}
