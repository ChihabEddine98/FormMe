package track

class TextRessource extends Ressource{


    String url
    String contenu

    static constraints = {

        contenu  type: 'text'
    }
}
