package authentication

import grails.transaction.Transactional
import track.AuthenticationResult

import track.ObselTrust
import track.utils.StatisticsUtil

@Transactional
class AuthenticationStatisticsService {



    void calculateStats(AuthenticationResult authenticationResult)
    {
        List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(authenticationResult, [max: 1000, sort: "begin", order: "asc", offset: 0])
        ArrayList<Float> distanceList = new ArrayList<Float>()
        ArrayList<Float> aniaList = new ArrayList<Float>()
        Float treshold = 37f
        int anga = 0
		

        for (int i = 0; i < listObselTrust.size(); i++)
        {
            ObselTrust obselTrust = listObselTrust.get(i)
            if(obselTrust.trust < treshold)
            {
                if (anga == 0)
                {
                    anga = i
                }
                aniaList.add(obselTrust.trust)
            }
            distanceList.add(obselTrust.distance)
			
			
        }

        StatisticsUtil statisticsDistance = new StatisticsUtil(distanceList)
        StatisticsUtil statisticsAnia = new StatisticsUtil(aniaList)

        authenticationResult.distanceMean = statisticsDistance.getMean()
        authenticationResult.distanceMedian = statisticsDistance.getMedian()
        authenticationResult.distanceVariance = statisticsDistance.getVariance()
        authenticationResult.distanceStandardDeviation = statisticsDistance.getStandardDeviation()
        authenticationResult.ania = statisticsAnia.getMean()
        authenticationResult.anga = anga
        authenticationResult.trustTreshold = treshold
		//authenticationResult.DiffPNT = nbTrustP - nbTrustN

        authenticationResult.save()
    }
}
