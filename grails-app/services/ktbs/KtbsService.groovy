package ktbs


import java.util.Collection;

import org.json.JSONObject;

import grails.transaction.Transactional

import com.ignition_factory.ktbs.bean.Base;
import com.ignition_factory.ktbs.bean.ComputedTrace
import com.ignition_factory.ktbs.bean.Model
import com.ignition_factory.ktbs.bean.Obsel
import com.ignition_factory.ktbs.bean.RootKtbs
import com.ignition_factory.ktbs.bean.Trace

@Transactional
class KtbsService {

	def grailsApplication
	
	Base createBase (String basename, String label) {
		String urlKTBSRoot = grailsApplication.config.grails.ktbs.urlKTBSRoot

		RootKtbs ktbsRoot = new RootKtbs (urlKTBSRoot)
		ktbsRoot.create_base(basename,label)
		Base base = new Base (urlKTBSRoot + basename + "/")
		return base
    }

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
	
	Trace createTransformedTrace (Base base,String tracename,String methode , String[] tracesource, String[] parametre) {
		
		base.create_computed_trace (tracename,methode,tracesource,parametre)
		ComputedTrace computedtrace = new ComputedTrace(base.get_uri()+tracename)
		return computedtrace;
		
	}
	
	String getUrlModel (Base base,Trace trace){
		trace.init();
		Model model = trace.get_model()
		String url = model.get_uri().substring(0,model.get_uri().length()-1)
		println ("url model" + url)
		return url
	}

	int getIdLastObsel (Collection<Obsel> listobsel) {
		def beginmax = 0
		int beginlastobsel = 0
		for (Obsel obs : listobsel){
			if (obs.get_begin() > beginmax) {
				beginmax = obs.get_begin() 
				beginlastobsel = obs.get_begin()
			}
		}
		return beginlastobsel
	}
	
	Collection<Obsel> getListObsel (Base base, Integer begin, Integer end, String name) {
		
		Trace profiltrace = new Trace (base.get_uri()+name)
		Collection<Obsel> obsel = profiltrace?.list_obsels (begin, end, null)
		return obsel
	}
}
