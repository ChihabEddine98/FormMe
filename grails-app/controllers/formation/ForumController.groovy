package formation

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import track.Comment
import track.DiscussionThread
import track.Section
import track.Topic
import track.User


@Validateable
class ForumController {
    def springSecurityService
	def EventService

    @Secured(['ROLE_ADMIN','ROLE_USER'])

    def index()
    {
		//consult forum
		User user = springSecurityService.currentUser
//		EventService.ConsultForum (user)
        render(view: "/formation/forumHome",model: [sections: Section.listOrderByTitle()])

    }


    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def topic(long topicId) {
        Topic topic = Topic.get(topicId)
        params.max = 5
        params.sort = 'createDate'
        params.order = 'desc'

        render(view: "/formation/topics",model:[threads  :DiscussionThread.findAllByTopic(topic, params),
                                                   numberOfThreads: DiscussionThread.countByTopic(topic),
                                                topic:topic])


    }


    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def thread(long threadId) {
        DiscussionThread thread = DiscussionThread.get(threadId)
        params.max = 5
        params.sort = 'createDate'
        params.order = 'asc'






        render(view: "/formation/thread",model:[comments: Comment.findAllByThread(thread, params),
                                           numberOfComments:Comment.countByThread(thread), thread:thread])


    }



    @Secured(['ROLE_USER','ROLE_ADMIN'])

    def postReply(long threadId, String body) {
        def offset = params.offset
        def commentBy=null
        def comment=null
        if (body != null && body.trim().length() > 0) {
            DiscussionThread thread = DiscussionThread.get(threadId)
            commentBy = springSecurityService.currentUser
            comment = new Comment(thread:thread, commentBy:commentBy, body:body)
            // go to last page so user can view his comment
            def numberOfComments = Comment.countByThread(thread)
            def lastPageCount = numberOfComments % 10 == 0 ? 10 : numberOfComments % 10
            offset = numberOfComments - lastPageCount

            comment.save(flush: true)
        }






		// Reponse Forum
		// Ajout Ressource Forum pour acces cours etc 
		EventService.ForumCreateResponse (commentBy,comment)
        redirect(action:'thread', params:[threadId:threadId, offset:offset])
    }




    def DisplayImage()
    {
        def img=Comment.findById(params.idComment).capture

        println("img :"+img)
        response.contentType = "image/jpg"
        response.contentLength = img.length
        response.outputStream.write(img)
//        response.outputStream.flush()

    }


    @Secured(['ROLE_USER'])
    def ajouterSujet(String body,long topicId) {

        User user = springSecurityService.currentUser
        Topic topic=Topic.findById(topicId)

        if (body != null && body.trim().length() > 0) {

            if(DiscussionThread.findAllBySubjectAndTopic(body,topic).size()==0)
            {
                DiscussionThread discussion=new DiscussionThread(opener:user,subject: body,topic: topic)
                discussion.save(flush: true)
            }
        }


        redirect(action:'topic',params: [topicId:topicId])


    }


//    @Secured(['ROLE_ADMIN','ROLE_USER'])
//    def save(Comment DBPhotoInstance) {
//
//        if (DBPhotoInstance == null) {
//            return
//        }
//        def uploadedFile = request.getFile("capture")
//        //converting file to bytes
//        DBPhotoInstance.capture = uploadedFile.getBytes()
//
//        //Create the record in DB by sending the needed Select command
//        DBPhotoInstance.save(flush:true)
//        redirect(action: “index”)
//    }

}
