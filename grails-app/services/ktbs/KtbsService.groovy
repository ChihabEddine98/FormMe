package ktbs


import java.util.Collection;
import java.util.LinkedHashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import grails.transaction.Transactional

import com.ignition_factory.ktbs.bean.Base;
import com.ignition_factory.ktbs.bean.ComputedTrace
import com.ignition_factory.ktbs.bean.HttpConnectionKtbs;
import com.ignition_factory.ktbs.bean.Model
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.RootKtbs
import com.ignition_factory.ktbs.bean.Trace

@Transactional
class KtbsService {

	def grailsApplication
	/**
	 * 
	 * @param basename
	 * @param label
	 * @return
	 */
	Base createBase (String basename, String label) {
		String urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot

		RootKtbs ktbsRoot = new RootKtbs (urlKTBSRoot)
		ktbsRoot.create_base(basename,label)
		Base base = new Base (urlKTBSRoot + basename + "/")
		return base
    }
/**
 * 
 * @param base
 * @param modelname
 * @param tracename
 * @param origin
 * @return
 */
	Trace createPrimaryTrace (Base base,String modelname, String tracename, String origin) {
		
		String orgin
		println "create primary trace and heir model"
		base.create_model(modelname, null, "modelprimary")
		Model model = new Model (base.get_uri()+ modelname)
		base.init ()
		if (origin) {
			orgin = origin
		} else {
		Date date = new Date() 
		orgin = date.toString()
		}
		
		base.create_stored_trace(tracename, model, orgin , null, "primary trace")
		Trace trace = new Trace (base.get_uri()+ tracename)
	}
	/**
	 * 
	 * @param basename
	 * @param tracename
	 * @param Attributes
	 * @return
	 */
	
	def createObsel (String basename , String tracename , JSONObject Attributes) 
	{
		String urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		
		println "create obsel"
		Base base = new Base (urlKTBSRoot+basename+"/")
		Trace trace = new Trace (urlKTBSRoot+basename+"/"+tracename+"/")
		trace.init()
		
		Obsel obs = new Obsel (trace.get_uri(),trace.get_model().get_uri(), Attributes)
		trace.PostObsel(obs)
		
	}
	/**
	 * 
	 * @param basename
	 * @param tracename
	 * @param URI
	 * @param Attributes
	 * @return
	 */
	
	def updateObsel (String basename , String tracename , String URI , JSONObject Attributes)
	{
		String urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot
		
		println "update obsel"
		Base base = new Base (urlKTBSRoot+basename+"/")
		Trace trace = new Trace (urlKTBSRoot+basename+"/"+tracename+"/")
		trace.init()
		
		Obsel obs = new Obsel (URI)
		trace.PostObsel(obs)
		
	}
	/**
	 * 
	 * @param base
	 * @param tracename
	 * @param methode
	 * @param tracesource
	 * @param parametre
	 * @return
	 */
	
	Trace createTransformedTrace (Base base,String tracename,String methode , String[] tracesource, String[] parametre) {
		
		base.create_computed_trace (tracename,methode,tracesource,parametre)
		ComputedTrace computedtrace = new ComputedTrace(base.get_uri()+tracename)
		return computedtrace;
		
	}
	/**
	 * 
	 * @param base
	 * @param trace
	 * @return
	 */
	
	String getUrlModel (Base base,Trace trace){
		trace.init();
		Model model = trace.get_model()
		String url = model.get_uri().substring(0,model.get_uri().length()-1)
		println ("url model" + url)
		return url
	}
	/**
	 * 
	 * @param listobsel
	 * @return
	 */

	Object getIdLastObsel (Collection<Obsel> listobsel) {
		def beginmax = 0
		Object beginlastobsel = 0
		for (Obsel obs : listobsel){
			
			if (obs.get_begin() > beginmax) {
				beginmax = obs.get_begin() 
				beginlastobsel = obs.get_begin()
			}
		}
		
		return beginlastobsel
	}
	/**
	 * 
	 * @param base
	 * @param begin
	 * @param end
	 * @param name
	 * @return
	 */
	Collection<Obsel> getListObsel (Base base, String begin, String end, String name) {
		
		Trace profiltrace = new Trace (base.get_uri()+name)
		Collection<Obsel> ListObsels
		// = profiltrace?.list_obsels (begin, end, null)
		HttpConnectionKtbs htppKtbs = new HttpConnectionKtbs();
		String  url = profiltrace.uri+"@obsels.json" ;
		if ((begin != null)&& (end != null)) {
			url = url +"?minb="+begin + "?maxb="+end ;
		} else if (begin != null) {url = url +"?minb="+begin;}
		else if (end != null) {url = url +"?maxb="+end ;}
		
		JSONObject jsonData = htppKtbs.GetJsonResponse(url,true) ;
		JSONArray arrayObsels = (jsonData != null) ? jsonData.getJSONArray("obsels") : null;
		ListObsels = new LinkedHashSet<Obsel>();
		
		if(arrayObsels != null) {
			for (int i = 0; i < arrayObsels.length(); i++){
				//System.out.println (arrayObsels.getJSONObject(i));
				Obsel objObsel = new Obsel (profiltrace.uri,jsonData.getJSONArray("@context").getJSONObject(1).getString("m"),arrayObsels.getJSONObject(i));
				
						ListObsels.add(objObsel);
						
			}
		}
		
		return ListObsels
	}
	/**
	 * 
	 * @param listobsel
	 * @param order
	 * @return
	 */
	Obsel getObselByOrder (Collection<Obsel> listobsel, int order) {
		
		return listobsel.get(order) ;
	}
	/**
	 * 
	 * @param type
	 * @param obAttribute
	 * @param obselsignature
	 * @return
	 */
	
	Obsel getObselWithType (String type, JSONObject obAttribute, Collection<Obsel> obselsignature) {
		Obsel obsel = null
		for (Obsel obs : obselsignature){
			
			if (obs.get_obsel_type().getTypeId().equalsIgnoreCase(type)){
				if (obAttribute) {
					Iterator iter =obAttribute.keys()
					if (obAttribute.length().equals(1) ){
						String key = (String)iter.next()
						String value = obAttribute.getString(key)
						if (obs.get_attribute (key).getValue().equalsIgnoreCase(value)){
							return obs ;
							break;
						}
					} else if (obAttribute.length().equals(2)) {
						String key = (String)iter.next()
						String value = obAttribute.getString(key)
						
						//Iterator iter1 = iter.next()
						String key1 = (String)iter.next()
						String value1 = obAttribute.getString(key1)
						if ((obs.get_attribute (key).getValue().equalsIgnoreCase(value))&&(obs.get_attribute (key1).getValue().equalsIgnoreCase(value1))){
							return obs ;
							break;
					 }
					}

				}
				else
				{
					return obsel
				}
			}
			
		}
		
	}
}
