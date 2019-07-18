package app

import grails.plugin.springsecurity.annotation.Secured


class HomeController {
	@Secured('permitAll')
    def index() { 
		render(view : "/app/home/home" )
	}
	def version1() {
		render(view : "/app/home/home" )
	}
}
