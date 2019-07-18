package authentication.modality

import com.ignition_factory.ktbs.bean.Base
import com.ignition_factory.ktbs.bean.Trace
import grails.transaction.Transactional
import ktbs.KtbsService
import defaults.Constants

@Transactional
class KeystrokeDynamicService {
    KtbsService ktbsService

    def extractFeature() {

    }

    Trace createProfil(Base base, Trace primarytrace, String tracename) {

        String methode = "fsa"
        String[] tracesource = [primarytrace.get_Name() + "/"];
        //String FsaTraceProfile = "fsa= {\n\t\"allow_overlap\": true,\n\t\"states\": {\n\t\t\"start\": {\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"pressedrelease\"\n\t\t\t}, {\n\t\t\t\t\"condition\": \"#K_Release\",\n\t\t\t\t\"target\": \"ReleaseRelease\"\n\t\t\t}, {\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"presspress\"\n\t\t\t}, {\n\t\t\t\t\"condition\": \"#K_Release\",\n\t\t\t\t\"target\": \"releasepressed\"\n\t\t\t}]\n\t\t},\n\t\t\"releasepressed\": {\n                        \"max_duration\": 1000,\n\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"#K_RP\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"releasepressed\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"presspress\": {\n                        \"max_duration\": 2000,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"#K_PP\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"presspress\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"pressedrelease\": {\n                        \"max_duration\": 2000,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"?obs a m:K_Release ; m:keyCode ?k . ?pred m:keyCode ?k .\",\n\t\t\t\t\"matcher\": \"sparql-ask\",\n\t\t\t\t\"target\": \"#K_PR\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"pressedrelease\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"ReleaseRelease\": {\n                        \"max_duration\": 2000,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Release\",\n\t\t\t\t\"target\": \"#K_RR\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"ReleaseRelease\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"#K_PP\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"first #Character\",\n\t\t\t\t\"#keySource\": \"first #keyCode\",\n\t\t\t\t\"#CharDestination\": \"last #Character\",\n\t\t\t\t\"#keyDestination\": \"last #keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t},\n\t\t\"#K_RP\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"first #Character\",\n\t\t\t\t\"#keySource\": \"first #keyCode\",\n\t\t\t\t\"#CharDestination\": \"last #Character\",\n\t\t\t\t\"#keyDestination\": \"last #keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t},\n\t\t\"#K_RR\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"first #Character\",\n\t\t\t\t\"#keySource\": \"first #keyCode\",\n\t\t\t\t\"#CharDestination\": \"last #Character\",\n\t\t\t\t\"#keyDestination\": \"last #keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t},\n\t\t\"#K_PR\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"#Character\",\n\t\t\t\t\"#keySource\": \"#keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t}\n\t}\n}"
		String FsaTraceProfile = "fsa= {\n\t\"allow_overlap\": true,\n\t\"states\": {\n\t\t\"start\": {\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"pressedrelease\"\n\t\t\t}, {\n\t\t\t\t\"condition\": \"#K_Release\",\n\t\t\t\t\"target\": \"ReleaseRelease\"\n\t\t\t}, {\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"presspress\"\n\t\t\t}, {\n\t\t\t\t\"condition\": \"#K_Release\",\n\t\t\t\t\"target\": \"releasepressed\"\n\t\t\t}]\n\t\t},\n\t\t\"releasepressed\": {\n                        \"max_duration\": 1000,\n\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"#K_RP\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"releasepressed\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"presspress\": {\n                        \"max_duration\": 1000,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Press\",\n\t\t\t\t\"target\": \"#K_PP\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"presspress\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"pressedrelease\": {\n                        \"max_duration\": 250,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"?obs a m:K_Release ; m:keyCode ?k . ?pred m:keyCode ?k .\",\n\t\t\t\t\"matcher\": \"sparql-ask\",\n\t\t\t\t\"target\": \"#K_PR\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"pressedrelease\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"ReleaseRelease\": {\n                        \"max_duration\": 1000,\n\t\t\t\"transitions\": [{\n\t\t\t\t\"condition\": \"#K_Release\",\n\t\t\t\t\"target\": \"#K_RR\"\n\t\t\t}],\n\t\t\t\"default_transition\": {\n\t\t\t\t\"target\": \"ReleaseRelease\",\n\t\t\t\t\"silent\": true\n\t\t\t}\n\t\t},\n\t\t\"#K_PP\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"first #Character\",\n\t\t\t\t\"#keySource\": \"first #keyCode\",\n\t\t\t\t\"#CharDestination\": \"last #Character\",\n\t\t\t\t\"#keyDestination\": \"last #keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t},\n\t\t\"#K_RP\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"first #Character\",\n\t\t\t\t\"#keySource\": \"first #keyCode\",\n\t\t\t\t\"#CharDestination\": \"last #Character\",\n\t\t\t\t\"#keyDestination\": \"last #keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t},\n\t\t\"#K_RR\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"first #Character\",\n\t\t\t\t\"#keySource\": \"first #keyCode\",\n\t\t\t\t\"#CharDestination\": \"last #Character\",\n\t\t\t\t\"#keyDestination\": \"last #keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t},\n\t\t\"#K_PR\": {\n\t\t\t\"terminal\": true,\n\t\t\t\"ktbs_attributes\": {\n\t\t\t\t\"#CharSource\": \"#Character\",\n\t\t\t\t\"#keySource\": \"#keyCode\",\n\t\t\t\t\"#time\": \"span http://liris.cnrs.fr/silex/2009/ktbs#hasEnd\"\n\t\t\t}\n\t\t}\n\t}\n}"
		
		String[] parametre = [FsaTraceProfile]
        Trace traceprofil = ktbsService.createTransformedTrace(base, tracename, methode, tracesource, parametre)
        return traceprofil
    }

    Trace createSignature(Base base, Trace primarytrace, String traceprofilname, String tracename, Trace traceprofillimit) {

		println (base.Name + primarytrace.get_Name())
        String modeUrl = ktbsService.getUrlModel(base, primarytrace)
		println (modeUrl)
		if (traceprofillimit == null)
        // limit trace profil à 5
       { String methode1 = "sparql"
        String sparlMA1 = "sparql=\nPREFIX : <http://liris.cnrs.fr/silex/2009/ktbs#>\nPREFIX model: <" + modeUrl + "> \n\nCONSTRUCT {\n  [ :hasSourceObsel ?o1 ; model:rank ?co2 ]\n}\nWHERE {\n    {\n        SELECT ?o1 (COUNT(?o2) as ?co2)\n        WHERE {\n          ?o1 a ?typ ;\n              :hasEnd ?end1 ;\n              model:keySource ?keyS .\n          ?o2 a ?typ ;\n              :hasEnd ?end2 ;\n              model:keySource ?keyS .\n              FILTER(?end2 <= ?end1)\n          {\n              ?o1 model:keyDestination ?keyD .\n              ?o2 model:keyDestination ?keyD .\n          } UNION {\n            FILTER NOT EXISTS { ?o1 model:keyDestination ?keyD1 }\n            FILTER NOT EXISTS { ?o2 model:keyDestination ?keyD2 }\n          }\n\n          VALUES (?typ) {\n              (model:K_PR)\n              (model:K_PP)\n              (model:K_RR)\n              (model:K_RP)\n          }\n        } GROUP BY ?o1\n    }\n    FILTER(?co2 <= %(limit)s)\n}\n"
        String inhert = "inherit=true"
        String limit = "limit=5 "
        String[] parametres1 = [sparlMA1, inhert, limit]
        String[] tracesource1 = [traceprofilname + "/"]
        Trace traceprofillimit1 = ktbsService.createTransformedTrace(base, primarytrace.get_Name() + "_" + "TraceProfileKeystrokeLimit", methode1, tracesource1, parametres1)
		traceprofillimit = traceprofillimit1
       }
		 // generate signature PR
        String methode2 = "sparql"
        String sparlMA2 = "sparql= prefix : <http://liris.cnrs.fr/silex/2009/ktbs#>\nprefix model: <" + modeUrl + "> \n\nCONSTRUCT {\n  [ a ?ctyp ;\n    model:obstyp ?typ ;\n    model:keySource ?key ;\n    model:CharSource ?char ;\n    model:mean ?mean;\n    model:deviation ?deviation;\n    model:nbOccurence ?count;\n    :hasTrace <%(__destination__)s> ;\n    :hasBegin ?begin ;\n    :hasEnd ?end ;\n  ]\n}\nwhere {\n\n    SELECT ?key ?typ\n           (SAMPLE(?ctyp2) as ?ctyp)\n           (SAMPLE(?char2) as ?char)\n           (SAMPLE(?count1) as ?count)\n           (SAMPLE(?mean1) as ?mean)\n           (SUM(ABS(?time2 - ?mean1)/(?count1 - 1)) as ?deviation)\n           (MIN(?begin2) as ?begin)\n           (MAX(?end2) as ?end)\n    {\n\n        # first compute ?mean and ?count for each n-uple (?key1 ?key2 ?typ)\n        {\n            SELECT ?key ?typ\n                   (SAMPLE(?ctyp1) as ?ctyp2)\n                   (COUNT(?o1) as ?count1)\n                   (AVG(?time1) as ?mean1)\n            {\n                ?o1 a ?typ ;\n                    model:keySource ?key ;\n                    model:time ?time1 .\n\n                VALUES (?typ ?ctyp1) {\n                    (model:K_PR model:K_MPR)\n                }\n            }\n            GROUP BY ?key ?typ\n            HAVING (COUNT(?o1) >= 5)\n        }\n\n        # then aggregate obsels again, in order to compute deviation (see outer select).\n\n        ?o2 a ?typ ;\n            model:keySource ?key ;\n            model:CharSource ?char2 ;\n            model:time ?time2 ;\n            :hasBegin ?begin2 ;\n            :hasEnd ?end2 .\n\n    } GROUP BY ?key ?typ\n\n}\n"
        String[] parametres2 = [sparlMA2]
        String[] tracesources2 = [traceprofillimit.get_Name() + "/"]
        Trace traceMPR = ktbsService.createTransformedTrace(base, traceprofillimit.get_Name() + "_" + Constants.Trace_MPR, methode2, tracesources2, parametres2)
        // generate signature RR PP RP
        String methode3 = "sparql"
        String sparlMA3 = "sparql= prefix : <http://liris.cnrs.fr/silex/2009/ktbs#>\nprefix model: <" + modeUrl + "> \n\n\nCONSTRUCT {\n  [ a ?ctyp ;\n    model:obstyp ?typ ;\n    model:keySource ?key1 ;\n    model:keyDestination ?key2 ;\n    model:CharDestination ?char2;\n    model:CharSource ?char1 ;\n    model:mean ?mean;\n    model:deviation ?deviation;\n    model:nbOccurence ?count;\n    :hasTrace <%(__destination__)s> ;\n    :hasBegin ?begin ;\n    :hasEnd ?end ;\n  ]\n}\nwhere {\n\n    SELECT ?key1 ?key2 ?typ\n           (SAMPLE(?ctyp2) as ?ctyp)\n           (SAMPLE(?char21) as ?char1)\n           (SAMPLE(?char22) as ?char2)\n           (SAMPLE(?count1) as ?count)\n           (SAMPLE(?mean1) as ?mean)\n           (SUM(ABS(?time2 - ?mean1)/(?count1 - 1)) as ?deviation)\n           (MIN(?begin2) as ?begin)\n           (MAX(?end2) as ?end)\n    {\n\n        # first compute ?mean and ?count for each n-uple (?key1 ?key2 ?typ)\n        {\n            SELECT ?key1 ?key2 ?typ \n                   (SAMPLE(?ctyp1) as ?ctyp2)\n                   (COUNT(?o1) as ?count1)\n                   (AVG(?time1) as ?mean1)\n            {\n                ?o1 a ?typ ;\n                    model:keySource ?key1 ;\n                    model:keyDestination ?key2 ;\n                    model:time ?time1 .\n\n                VALUES (?typ ?ctyp1) {\n                    (model:K_PP model:K_MPP)\n                    (model:K_RR model:K_MRR)\n                    (model:K_RP model:K_MRP)\n                }\n            }\n            GROUP BY ?key1 ?key2 ?typ\n            HAVING (COUNT(?o1) >= 5)\n        }\n\n        ?o2 a ?typ ;\n            model:keySource ?key1 ;\n            model:keyDestination ?key2 ;\n            model:CharSource ?char21 ;\n            model:CharDestination ?char22 ;\n            model:time ?time2 ;\n            :hasBegin ?begin2 ;\n            :hasEnd ?end2 .\n\n        # then aggregate obsels again, in order to compute deviation (see outer select).\n\n    } GROUP BY ?key1 ?key2 ?typ\n\n}\n"
        String[] parametres3 = [sparlMA3]
        String[] tracesources3 = [traceprofillimit.get_Name() + "/"]
        Trace traceMPPRRRP = ktbsService.createTransformedTrace(base, traceprofillimit.get_Name() + "_" + Constants.Trace_MPPRRRP, methode3, tracesources3, parametres3)
	  
		// trace signature
        String methode4 = "fusion"
        String[] tracesources4 = [traceMPR.get_Name() + "/", traceMPPRRRP.get_Name() + "/"]
        String[] parametres4 = []
        Trace tracesignature = ktbsService.createTransformedTrace(base, traceprofillimit.get_Name()+tracename, methode4, tracesources4, parametres4)
       
		 return tracesignature
    }

	
}
