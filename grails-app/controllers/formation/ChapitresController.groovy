package formation

import grails.plugin.springsecurity.annotation.Secured
import track.Cours
import track.Module
import track.Question
import track.Quiz
import track.QuizScores
import track.Ressource
import track.User

class ChapitresController {


    def springSecurityService
	def EventService

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index() {


    }




    static questionsOfQuiz(int id) {

        def quiz=Quiz.findById(id)
        def questions= Question.findAllByQuizR(quiz)

        return questions

    }


    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def submitQuiz(int quizId)
    {
        int score=0


        def questions=questionsOfQuiz(quizId)

        questions.eachWithIndex{ question,i ->

            if (question.answer.equals(params["question$i"]))
            {
                score++

            }

        }


        User user=springSecurityService.currentUser
        Ressource quiz= Ressource.findAllById(quizId).first()



		QuizScores quizScore
        if(QuizScores.findAllByUserAndQuiz(user,quiz).size()==0)
        {
            quizScore=new QuizScores(user: user,quiz: quiz,score: score)
            quizScore.save()
        }
        else {
            quizScore=QuizScores.findAllByUserAndQuiz(user,quiz).first()
            quizScore.score=score
            quizScore.save(flush: true)

        }




        Module module=Module.findAllById(Integer.parseInt(params.indice)).first()
        Cours cours=Cours.findAllById((int)module.coursId).first()
         // Valide Quiz
		
//		EventService.ExerciceEvaluated (user,cours , quizScore)
        render(view : "/formation/chapitres",model: [userInstance:user,chapitres:module.chapitres,cours:cours])






    }



}
