package formation

import authentication.diagnostic.DiagnosticService
import grails.plugin.springsecurity.annotation.Secured
import track.Cours
import track.User

class HomeFormationController {
    def springSecurityService
    def EventService
    def DiagnosticService



    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index() {


        User user = springSecurityService.currentUser

        params.max=3
        def listCours=Cours.list(params)

        render(view: "/formation/home",model: [userInstance:user,listCours:listCours])
    }



//    def similarIp(String ip1, String ip2){
//
//        double taux=0.0
//
//
//        int seq_long=longPrefixIp(ip1,ip2)
//        int total_long=longTotalIp(ip1)
//
//
//        taux=seq_long/total_long
//
//        println(" seq_long = "+seq_long)
//        println(" taux     = "+taux)
//
//
//        return taux
//
//
//
//    }
//
//
//    def longPrefixIp(String ip1,String ip2)
//    {
//        String[] ip1_tab = ip1.split("\\.")
//        String[] ip2_tab = ip2.split("\\.")
//
//        int seq_long=0
//
//
//        for (int i=0;i<ip1_tab.size();i++)
//        {
//
//            if( ip2_tab[i].equals(ip1_tab[i]))
//                seq_long++
//
//        }
//
//        return seq_long
//
//    }
//
//
//    def longTotalIp(String ip)
//    {
//        String[] ip_tab = ip.split("\\.")
//
//        int total_long= ip_tab.size()
//
//        return total_long
//
//    }



    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def accederModules() {
        User user = springSecurityService.currentUser



//        println(" Persistent ID "+generatePersistentId())


        Cours cours=Cours.findAllById(Integer.parseInt(params.indice)).first()

       // acces cours
       // EventService.AccessFormation(user,cours)


        // -------------------------------------------------------
        //  Test de Sevice Diagnostiques here :
        // -------------------------------------------------------

        double[] poids=new double[4]

        poids[0]=0.25
        poids[1]=0.1
        poids[2]=0.5
        poids[3]=0.15


        double[] normalTrusts=new double[4]

        normalTrusts[0]=0.27
        normalTrusts[1]=0.9
        normalTrusts[2]=0.47
        normalTrusts[3]=0.78

//        println(" Normal Trust KDMD  ( 0.87 ): "+DiagnosticService.NormalTrustKDMD(0.87))
//        println(" Normal Trust Env   ( 0.35,0.55): "+DiagnosticService.NormalTrustEnv(0.35,0.55))
//        println(" Degré Confiance Total   : "+DiagnosticService.DegreConfTotal(poids,normalTrusts))




        render(view : "/formation/modules",model: [userInstance:user,cours:cours] )
    }
//    def  userAgentIdentService
//
//    String generatePersistentId(){
//
//
//        // N2 : browser used
//        String n1 =userAgentIdentService.browser+userAgentIdentService.browserVersion
//
//        // N4 : OS used
//        String n2=System.getProperty("os.name")+System.getProperty("os.version")
//
//
//
//        String pID=(n1+" "+n2).encodeAsBase64()
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

        params.max=3
        def listCours=Cours.list(params)

        render(view: "/formation/home",model: [userInstance:user,listCours: listCours])


    }


    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def monCompte() {
        User user = springSecurityService.currentUser
        render(view: "/formation/monCompte",model: [userInstance:user])
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def mesCours() {
        User user = springSecurityService.currentUser

        params.max=3

        ArrayList<Integer> ids=new ArrayList<Integer>()

        user.cours.each {
            ids.add(it.id)
        }

        def coursList_bis=Cours.findAllByIdInList(ids,params)
        ArrayList<Cours> coursList=new LinkedList<Cours>()

//        coursList_bis.each {
//
//            if (user.cours.contains(it))
//            {
//                coursList.add(it)
//            }
//

        coursList_bis.each {

            println(it)

        }


        render(view: "/formation/mesCours",model: [userInstance:user,coursList: coursList_bis])
    }







}
