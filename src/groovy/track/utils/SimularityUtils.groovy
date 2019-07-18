package track.utils

import java.util.ArrayList;

import org.json.JSONArray;

class SimularityUtils {
	
	SimularityUtils()
	{
		
	}
	/**
	 *
	 * @param str1
	 * @param str2
	 * @return
	 */
  public static double simulairString (String str, JSONArray Liststr2) {
	  
	  if (Liststr2.toString().contains(str)) {return 1 ;}
	  else return 0;
	  
  }
  /**
   *
   * @param H1
   * @param H2
   * @return
   */
  
  public static double simulairhours (double H1, double H2) {
	 
	  double  s = Math.abs( 1- (((Math.abs(H2-H1))%24)/(double) 12)) ;
	  return s*s ;
  }
  /**
   *
   * @param H1
   * @param H2
   * @return
   */
	  public static double simulairDuration (double D1, double D2) {

		  double d = min (D1,D2) /  (double) (max(D1,D2)) ;
		  return d ;
	  }






	  static double simulairAdressIP (String ip1, String ip2) {

		  double taux=0.0


		  int seq_long=longPrefixIp(ip1,ip2)
		  int total_long=longTotalIp(ip1)


		  taux=seq_long/total_long

		  return taux

	  }



	 static int longPrefixIp(String ip1,String ip2)
	  {
		String[] ip1_tab = ip1.split("\\.")
		String[] ip2_tab = ip2.split("\\.")

		int seq_long=0


		for (int i=0;i<ip1_tab.size();i++)
		{

			if( ip2_tab[i].equals(ip1_tab[i]))
				seq_long++

		}

		return seq_long

	}


	static int longTotalIp(String ip)
	{
		String[] ip_tab = ip.split("\\.")

		int total_long= ip_tab.size()

		return total_long

	}

  /**
   * Liste function aide
   */

  /**
   *
   * @param a
   * @param b
   * @return
   */
	public static double min (double a, double b) {
		if (a<b) return a;
		else return b;
		
	}
	/**
	 *
	 * @param a
	 * @param b
	 * @return
	 */
  public static double max (double a, double b) {
	  if (a>b) return a;
	  else return b;
		
	}
  /**
   *
   * @param a
   * @param b
   * @return
   */
  public static double moyenne (double a, double b) {
	  
	  double r = (double)(a+b)/2 ;
	  //System.out.println ("moyenne "+ a +" et" + b + "=" + r);
	   return   r ;
  }
  
  public static double moyenneGeometric (double a, double b) {
	  
	  double m =  (double) Math.sqrt( (double) a*b ) ;
	  return m ;
  }
  /**
   *
   * @param tab
   * @return
   */
  public static double MaxTableau (double [] tab ){

	  double max = 0;

	  for (int i = 0; i < tab.length; i++) {
		  if (tab[i] > max){
			  max = tab[i];
		  }
	  }

	  return max;
  }
  /**
   *
   * @param tab
   * @return
   */
  public static int MaxIndex (double [] tab ){

	  double max = 0;
	  int index = 0 ;

	  for (int i = 0; i < tab.length; i++) {
		  if (tab[i] > max){ index = i; max = tab[i]; }
	  }

	  return index;
  }

}
