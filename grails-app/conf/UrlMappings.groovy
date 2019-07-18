class UrlMappings {



	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here

            }
        }

        "/"(controller: "home", action: "index",access: ['permitAll'])
		"/game/game"(controller: "Game", action:"game")
        "/formation/home"(controller: "HomeFormation", action:"index",access: ['permitAll'])
        "/formation/forumHome"(controller: "forum", action:"index",access: ['permitAll'])
		"/game"(controller: "Game")
        "500"(view:'/error')

        /** ADMINISTRATION */
        /** Administration Home*/

        /** Manual authentication*/
        "/admin/"(controller: "Admin", action:"index")
        "/admin/authenticationResults/userList"(controller: "Admin", action:"userList")
        "/admin/authenticationResults/resultsForUser"(controller: "Admin", action:"authenticationResults")
        "/admin/manualAuthentication"(controller: "Admin", action:"manualAuthentication")
	}
}
