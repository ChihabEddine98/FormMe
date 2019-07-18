package formation

import grails.plugin.springsecurity.annotation.Secured
import track.Chapitre
import track.Cours
import track.Module
import track.User

class ModulesController {



    def springSecurityService

    def index() {


    }



    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def accederChapitres() {


        User user = springSecurityService.currentUser
        Module module=Module.findAllById(Integer.parseInt(params.indice)).first()
        Cours cours=Cours.findAllById((int)module.coursId).first()


        render(view : "/formation/chapitres",model: [userInstance:user,chapitres:module.chapitres,cours:cours])
    }

}
