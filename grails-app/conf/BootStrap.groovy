import track.Comment
import track.Cours
import track.DiscussionThread
import track.Quiz
import track.Role
import track.Section
import track.Topic
import track.User
import track.UserRole
import track.UserRoleService
import track.UserService
import track.auth.GenerateUUI;


class BootStrap {

	UserService userService
	UserRoleService userRoleService
	def random = new Random()
	def words = ("time,person,year,way,day,thing,man,world,life,hand,part,child,eye,woman,place,work,week,case,point," +
			"government,company,number,group,problem,fact,be,have,do,say,get,make,go,know,take,see,come,think,look," +
			"want,give,use,find,tell,ask,work,seem,feel,try,leave,call,good,new,first,last,long,great,little,own," +
			"other,old,right,big,high,different,small,large,next,early,young,important,few,public,bad,same,able,to,of," +
			"in,for,on,with,at,by,from,up,about,into,over,after,beneath,under,above,the,and,a,that,I,it,not,he,as,you," +
			"this,but,his,they,her,she,or,an,will,my,one,all,would,there,their").split(",")

    def init = { servletContext ->

		GenerateUUI  generateuui = new GenerateUUI();
		
		
		Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role('ROLE_ADMIN').save()
		Role userRole = Role.findByAuthority('ROLE_USER') ?: new Role('ROLE_USER').save()
		User user = User.findByEmail("test@mail.com") ?: new User(firstname : 'test', lastname : 'test', email : 'test@mail.com', password:'Mot2passe', iduser : generateuui.generate("test", "test")).save()
       	UserRole.findByUserAndRole(user,userRole) ?: new UserRole(user,userRole).save()
	   	UserRole.findByUserAndRole(user,adminRole) ?: new UserRole(user,adminRole).save()

		// Add other default users
//		if(!(user = User.findByEmail("nfourniol@ignition-factory.com"))) {
//			user = new User(firstname : 'Nicolas', lastname : 'FOURNIOL', email : 'nfourniol@ignition-factory.com', password:'Nicolas1')
//			userService.createUser(user)
//		}
//		UserRole.findByUserAndRole(user,adminRole) ?: userRoleService.assignRole ("ROLE_ADMIN", user)

//		if ( Section.count() == 0 ) { // create data if no forum data found
//
//
//			// get all users
//			def users = user.list()
//			// create 3 sections
//			('A'..'C').each { sectionLetter ->
//				def sectionTitle = "Section ${sectionLetter}"
//				def section = new Section(title: sectionTitle).save()
//				// create 4 topics per section
//				(1..4).each { topicNumber ->
//					def topicTitle = "Topic ${sectionLetter}-${topicNumber}"
//					def topicDescription = "Description of ${topicTitle}"
//					def topic = new Topic(section: section, title: topicTitle, description: topicDescription).save()
//					// create 10-20 threads each topic
//					def numberOfThreads = random.nextInt(11)+10
//					(1..numberOfThreads).each { threadNo ->
//						def opener = users[random.nextInt(User.count)]
//						def subject = "Subject ${sectionLetter}-${topicNumber}-${threadNo} "
//						def thread = new DiscussionThread(topic:topic, subject:subject, opener:opener).save()
//						new Comment(thread:thread, commentBy:opener, body:generateRandomComment()).save()
//						// create 10-35 replies per thread
//						def numberOfReplies = random.nextInt(26)+10
//						numberOfReplies.times {
//							def commentBy = users[random.nextInt(User.count)]
//							new Comment(thread:thread, commentBy:commentBy, body:generateRandomComment()).save()
//						}
//					}
//				}
//			}
//		}


   }






	private String generateRandomComment() {
		def numberOfWords = random.nextInt(50) + 15
		StringBuilder sb = new StringBuilder()
		numberOfWords.times {
			def randomWord = words[random.nextInt(words.length)]
			sb.append("${randomWord} ")
		}
		return sb.toString()
	}
	def destroy = {
	}
}
