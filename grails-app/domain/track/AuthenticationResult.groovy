package track

class AuthenticationResult implements Serializable {

    Date start
    AuthenticationType type

    Float distanceMean =0
    Float distanceMedian =0
    Float distanceVariance =0
    Float distanceStandardDeviation =0
    Float trustTreshold =0
    Float ania =0
    int anga =0

    static hasMany = [ obselTrusts : ObselTrust ]
    static belongsTo = [userSignature: User, userProfile: User]

    enum AuthenticationType {
        CLASSIC,CLASSIC55, DYNAMIC, CLASSIC1, CLASSIC2, CLASSIC3, CLASSIC51, CLASSIC52, CLASSIC53, CLASSIC54,CLASSIC15,CLASSIC6,CLASSIC12

        /**
         * @return String : the key for translation (see keys in message.properties)
         */
        String getMessageCode() {
            return "authenticationResult.authenticationType."+name()
        }
    }

    static constraints = {

    }

    static mapping = {
        version false // No concurrent access possible in writing because no update possible (only write once)
        type sqlType: 'varchar(30)'
		ania defaultValue: "0"
		anga defaultValue: "0"
		trustTreshold defaultValue: "0"
		distanceMean defaultvalue: "0"
		distanceMedian defaultValue: "0"
		distanceVariance defaultValue: "0"
		distanceStandardDeviation defaultValue: "0"
		
    }
	int sizeObselTrust (){
		List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(this, [max: 1000, sort: "begin", order: "desc", offset: 0])
		return (listObselTrust.size)
	}
	float lastTrust (){
		List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(this, [max: 1000, sort: "begin", order: "desc", offset: 0])
		ArrayList<Float> listTrusts = new ArrayList<Float>()
		listObselTrust.each
		{
			listTrusts.add(it.trust)
		}

		listTrusts.reverse()
		float trust = listTrusts.get(0)
		return (trust)
	}
	float minimumTrust () {
		List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(this, [max: 1000, sort: "begin", order: "desc", offset: 0])
		ArrayList<Float> listTrusts = new ArrayList<Float>()
		listObselTrust.each
		{
			listTrusts.add(it.trust)
		}
		 float min = Float.MAX_VALUE;
			 for (int i = 0; i < listTrusts.size(); i++) {
			Float f = listTrusts.get(i);
            if (f < min)  {
            min = f.floatValue();
           }
			 }
			 return min
	}
	float ratio() {
//		int nbTrustN = 0
//		int nbTrustP =0
//		List<ObselTrust> listObselTrust = ObselTrust.findAllByAuthenticationResult(this, [max: 1000, sort: "begin", order: "desc", offset: 0])
//		ArrayList<Float> listTrusts = new ArrayList<Float>()
//		listObselTrust.each
//		{
//			listTrusts.add(it.trust)
//		}
//		for (int i = 0; i < listTrusts.size(); i++) {
//			Float f = listTrusts.get(i);
//			if (f < 0){nbTrustN = nbTrustN+1}
//			else {nbTrustP = nbTrustP+1}
//		}
//		float ratio = nbTrustN/listTrusts.size()
		float ltrust = this.lastTrust () ;
		float ratio = (1-Math.exp(ltrust))/(1+Math.exp(ltrust))
		return ratio
	}
}
