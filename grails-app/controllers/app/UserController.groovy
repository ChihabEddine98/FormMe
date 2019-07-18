package app

import track.AuthenticationResult
import track.ObselTrust

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import track.User
import track.UserService

@Transactional(readOnly = true)
class UserController {
	UserService  userService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		render(view: '/app/admin/index', model:[userInstanceList: User.list(params), userInstanceCount: User.count()])
    }
    
    def show(User userInstance) {
        respond userInstance
    }
	@Secured('permitAll')
    def create() {
		render (view:'/app/user/create', model:[new User(params)])
    }

    @Transactional
	@Secured('permitAll')
    def save(User userInstance) {
        if (userInstance == null) {notFound() ; return }
        if (userInstance.hasErrors()) {
			render (view:'/app/user/create', model:[userInstance:userInstance])
            return
        }

//        userInstance.save flush:true
//		def role = Role.findByAuthority("ROLE_USER")
//		if (!role)
//		{
//		  return false
//		}
//		if (!userInstance.authorities.contains(role))
//		{
//		  UserRole.create userInstance, role
//		}
		userService.createUser(userInstance)
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                //redirect userInstance
				redirect(controller: 'game', action:'game')
            }
            '*' { 
				respond userInstance, [status: CREATED]
				
				}
        }
    }

	@Secured(['ROLE_ADMIN'])
    def edit(User userInstance) {
        respond userInstance
    }

	@Secured(['ROLE_ADMIN'])
    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
	@Secured(['ROLE_ADMIN'])
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }


    @Secured(['ROLE_ADMIN'])
    def authenticationResults(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        List<AuthenticationResult> authenticationResults = AuthenticationResult.findAllByUser(userInstance, [max: 100, sort: "start", order: "desc", offset: 0])
        render(view: '/app/user/authenticationResults', model:[authenticationResults: authenticationResults, userInstance: userInstance])
    }

    @Secured(['ROLE_ADMIN'])
    def authenticationDetail(AuthenticationResult authenticationResult) {
        if (authenticationResult == null) {
            notFound()
            return
        }


        List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "begin", order: "desc", offset: 0])

        render(view: '/app/user/authenticationDetail', model:[listObselTrust: listObselTrust, authenticationResult: authenticationResult])
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
