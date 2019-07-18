package formation

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import sun.security.provider.MD5
import track.Comment
import track.Cours
import track.DiscussionThread
import track.User
import authentification.collect.EventService




class HomeFormationController {
    def springSecurityService
    def EventService
    def userAgentIdentService



    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index() {
        User user = springSecurityService.currentUser
        render(view: "/formation/home",model: [userInstance:user])
    }



    def similarIp(String ip1, String ip2){

        double taux=0.0


        int seq_long=longPrefixIp(ip1,ip2)
        int total_long=longTotalIp(ip1)


        taux=seq_long/total_long

        println(" seq_long = "+seq_long)
        println(" taux     = "+taux)


        return taux



    }


    def longPrefixIp(String ip1,String ip2)
    {
        String[] ip1_tab = ip1.split("\\.")
        String[] ip2_tab = ip2.split("\\.")

        int seq_long=0


        for (int i=0;i<ip1_tab.size();i++)
        {

            if( ip2_tab[i].equals(ip1_tab[i]))
                seq_long++

        }

        return seq_long

    }


    def longTotalIp(String ip)
    {
        String[] ip_tab = ip.split("\\.")

        int total_long= ip_tab.size()

        return total_long

    }



    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def accederModules() {
        User user = springSecurityService.currentUser



//        println(" Persistent ID "+generatePersistentId())


        Cours cours=Cours.findAllById(Integer.parseInt(params.indice)).first()

       // acces cours 
//		EventService.AccessFormation(user,cours)
        render(view : "/formation/modules",model: [userInstance:user,cours:cours] )
    }


//    String generatePersistentId(){
//
//
//        // N2 : browser used
//        String n1 =userAgentIdentService.browser
//
//        // N4 : OS used
//        String n2=userAgentIdentService.operatingSystem
//
//
//
//        String pID=(n1+n2).encodeAsBase64()
//
//
//
//
//        return pID
//
//
//    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def inscriptionCours() {
        User user = springSecurityService.currentUser
        Cours cours=Cours.findAllById(params.indice).first()
        if(!user.cours.contains(cours))
            user.addToCours(cours).save(flush: true)
			
			// Inscription Cours
			EventService.InscriptionFormation(user,cours)
        
			render(view: "/formation/home",model: [userInstance:user])


    }


    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def monCompte() {
        User user = springSecurityService.currentUser
        render(view: "/formation/monCompte",model: [userInstance:user])
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def mesCours() {
        User user = springSecurityService.currentUser
        render(view: "/formation/mesCours",model: [userInstance:user])
    }



    @Secured(['ROLE_USER'])
    def sendEmail(String email,String sujet ,String body) {

        println(email+"  sujet " + sujet)
        println(" msg "+body)

//        sendMail {
//            to "ga_benamara@esi.dz"
//            from email
//            subject sujet
//            text body
//        }

//        redirect(action:'index')
    }



}
