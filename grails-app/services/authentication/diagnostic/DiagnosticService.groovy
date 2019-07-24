package authentication.diagnostic

import grails.transaction.Transactional

@Transactional
class DiagnosticService {

    def serviceMethod() {

    }





    // -------------------------------------------------------
    //  Normal trust KDMD
    // -------------------------------------------------------

    double NormalTrustKDMD(double trustKDMD)
    {

        // ------------------------------------------
        //   La formule :
        // ------------------------------------------
        // /                                        /
        // /                  1                     /
        // /       -------------------------        /
        // /          1+Exp(-k*TrustKDMD)           /
        // /                                        /
        // ------------------------------------------


        double normal=0.0
        double k=1

        boolean faussesEntrees= trustKDMD>0 && trustKDMD<1

        if(!faussesEntrees)
        {
            double denominateur=Math.exp(-k*trustKDMD)+1
            normal=Math.pow(denominateur,-1)

        }
        else
        {
            println(" Le trust doit etre compris entre 0 et 1 ")

        }


        return normal

    }



    // -------------------------------------------------------
    //  Normal Trust Environnement Apprentissage
    // -------------------------------------------------------
    double NormalTrustEnv(double trustEnv1,double trustEnv2)
    {



        // ------------------------------------------
        //   La formule :
        // ------------------------------------------
        // /                                        /
        // /        Exp[ k*Delta(Trust)-1 ]         /
        // /                                        /
        // ------------------------------------------


        double normal=0.0
        double k=1

        boolean faussesEntrees= trustEnv1<0 || trustEnv1>1 || trustEnv2<0 || trustEnv2>1


        if(faussesEntrees)
        {
            println(" Les trusts doivent etre compris entre 0 et 1 ")

        }
        else
        {
            double deltaTrust=trustEnv2-trustEnv1
            normal=Math.exp(k*deltaTrust-1)
        }


        return normal

    }



    // A ajouter les deux methodes de normatlisation pour autres méthodes d'authentification


    // -----------------------------------------------------------------------
    //  Pour savoir si tout les elements d'un tableau sont compris entre 0 et 1
    // ------------------------------------------------------------------------

    boolean comprisEntre0Et1(double[] tab)
    {
        boolean ok=true
        tab.each{ double element ->

            if(element<0 || element >1)
            {
                ok=false
            }
        }

        return ok
    }


    // -------------------------------------------------------------------
    //  Pour savoir si la somme des elements d'un tableau est égale à 1
    // -------------------------------------------------------------------

    boolean sommeEstUn(double[] tab)
    {

        double somme=0.0

        if (!comprisEntre0Et1(tab))
        {
            return false
        }

        tab.each { double element ->

            somme+=element
        }


        return somme==1
    }




    // ------------------------------------------------------
    //  degré Confiance Totale
    // ------------------------------------------------------

    double DegreConfTotal(double[] poids,double[] normatTrusts)
    {
        // ------------------------------------------------------
        //   La formule :
        // ------------------------------------------------------
        // /                                                    /
        // /                                                    /
        // /     100 * Somme[ Wi * NormalTrust(trust) ]         /
        // /                                                    /
        // /                                                    /
        // ------------------------------------------------------

        double degre=0.0
        boolean mmTaille=poids.size()==normatTrusts.size()


        if(mmTaille)
        {
            // Si tout est bon !!
            if(sommeEstUn(poids) && comprisEntre0Et1(normatTrusts))
            {
                poids.eachWithIndex { double poid, int i ->

                    degre+=poid*normatTrusts[i]
                }

                degre*=100

            }
            else if(!comprisEntre0Et1(normatTrusts))
            {
                println(" les valeurs normales doivent etre comprises entre 0 et 1 ")
            }

            // Si les 2 tableaux ont bien la mm taille
            //  Mais que la somme des poids n'est pas égale

            else
            {
                println(" la somme des poids n'est pas égale à 1")
            }

        }
        // Si les deux tableaux ont des tailes différentes
        else
        {
            println(" Les deux tableaux doivent avoir la meme taille")
        }

        return degre

    }


}
