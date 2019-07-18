



// Shared Variable

var connections = 0; // count active connections
var ModelURI=null;
var header;
var body;
var TraceName=null ;
var TraceNameEnv =null
var BaseURI=null ;
var obselQueue = [];
var obselXhr = null;

/*=======================================================||
The onconnect is an EventHandler representing        ||
				the code                             ||
	to be called when the connect event is raised    ||
=======================================================  ||*/

onconnect=function(e){

/**Import samotraces-core-debug.js**/
/** MessagePort connection is opened between the associated SharedWorker and the main thread.**/

port = e.ports[0];
connections++;
port.postMessage ({mess:'open'+connections});

port.onmessage = function (event) {
	var DataRecu = event.data;
	var messName = DataRecu.mess;
	port.postMessage ({mess:messName});

	if (messName=== "Emptythewaitinglist") {
		sendObsels();
	}
		// receive TraceInformation
	else if (messName==='TraceInformation') {
		port.postMessage({mess:"info received"});
		TraceName = DataRecu.Trace_Information.TraceName;
		BaseURI  = DataRecu.Trace_Information.BaseURI;
		ModelURI = DataRecu.Trace_Information.ModelURI;
		port.postMessage({mess:"TraceName "+TraceName});
		port.postMessage({mess:"BaseURI "+BaseURI});
		port.postMessage({mess:"Model_URI "+ModelURI});

	} else if (messName==='TraceNameEnv') {
		port.postMessage({mess:"TraceNameEnv received = "+ DataRecu.TraceNameEnv});
	    TraceNameEnv = DataRecu.TraceNameEnv
	   // port.postMessage (mess:"TraceNameEnv received = "+ TraceNameEnv)
	    port.postMessage({mess:"TraceNameEnv "+TraceNameEnv});
	}
		// receive Obsel To send it to ktbs
	else if (messName==='obsel') {
		port.postMessage({mess:"obsel received"});
		//port.postMessage({mess:"ModelURI "+ModelURI})
    if ((TraceName=== null)||(BaseURI=== null)||(ModelURI=== null)) {
 			// send message to the collecteur to get information about trace
 			port.postMessage({mess:'GetTraceInf'});
 		}
    enqueueObsel(DataRecu.OBSEL);
	}
	
	/* obsel close Browser */ 
	
	
	else if (messName==='obselunload') {
		port.postMessage({mess:"obselunload received"});
		
		//port.postMessage({mess:'GetTraceInf'});
		port.postMessage({mess:"BaseURI obselunload "+BaseURI});
		 port.postMessage({mess:"TraceNameEnv obselunload "+TraceNameEnv});
   
		obselXhr = new XMLHttpRequest();
		obselXhr.open('POST', BaseURI+TraceNameEnv,true);
		obselXhr.setRequestHeader('content-type', 'application/json');

		obselXhr.onreadystatechange = function () {
    			if (obselXhr.readyState === 4) {
    				if(obselXhr.status === 201) {
    			port.postMessage({mess:"ok post obselunload:"+obselXhr.status});
    					
    					obselXhr = null;
    				} else {
    					port.postMessage({mess:"error posting obsels to :"+BaseURI+TraceNameEnv});
    					port.postMessage({mess:"error posting obsels:"+obselXhr.status});
    					obselXhr = null;

    				}
    			}
    		};
    		obselXhr.onerror = function(e) {
    			port.postMessage({mess:"Error Status: " + e.target.status});
    		};
        obselXhr.send(JSON.stringify(createjsonobsel (DataRecu.OBSEL)));

	}
	
	
	
  else if (messName=== 'DataPage'){
		header=DataRecu.header;
		body = DataRecu.body;
	}
  else if (messName=== 'GetDataPage'){
		port.postMessage({mess:body});
		port.postMessage({mess:'DataIframe',header:header,body:body});
	}

port.start();
};

function enqueueObsel(obsel) {
  port.postMessage({mess:"enqueueObsel"});
  port.postMessage({mess:"************"+obsel.type+"*****************"});
  //createjsonobsel (obsel) ;
  obselQueue.push(createjsonobsel (obsel));
  //port.postMessage({mess:JSON.stringify(obselQueue)});
   if (obselQueue.length > 2 && obselXhr === null) {
       sendObsels();
   }
}
/**
 * Sends the obsels in obselQueue to the configured trace.
 * Asssumes that config has been correctly loaded, obselXhr is null and obselQueue is not empty.
 * Also, ensures that a delay of 'config.postDelay' ms is kept between two queries.
 */
function sendObsels() {
    
	port.postMessage({mess:"sendObseltoKTBs In " +BaseURI+TraceName});
    port.postMessage({mess:"sendObseltoKTBs"+JSON.stringify(obselQueue)});
    obselXhr = new XMLHttpRequest();
    obselXhr.open('POST', BaseURI+TraceName,true);
    //obselXhr.withCredentials = true;
    obselXhr.setRequestHeader('content-type', 'application/json');
    // obselXhr.onerror = function () {
    //     port.postMessage({mess:"error posting obsels: no response"});
    //     obselXhr = null;
    // };

		obselXhr.onreadystatechange = function () {
			port.postMessage({mess:"IN post:"+obselXhr.status});
			if (obselXhr.readyState === 4) {
				if(obselXhr.status === 201) {
					port.postMessage({mess:"ok post:"+obselXhr.status});
					//port.postMessage({mess:"sendObseltoKTBs"});
					obselQueue = [];
					obselXhr = null;

				} else {
					port.postMessage({mess:"error posting obsels to :"+BaseURI+TraceName});
					port.postMessage({mess:"error posting obsels:"+obselXhr.status});
					obselXhr = null;

				}
			}
		};
		obselXhr.onerror = function(e) {
			port.postMessage({mess:"Error Status: " + e.target.status});
		};
    obselXhr.send(JSON.stringify(obselQueue));

    obselQueue = [];
    port.postMessage({mess:"After sendObseltoKTBs"+JSON.stringify(obselQueue)});
}

function createjsonobsel (params){
	port.postMessage({mess:"createjsonobsel"});
  var json_obsel = {
        // "@context":	[
        // "http://liris.cnrs.fr/silex/2011/ktbs-jsonld-context",
        //        { "m": ModelURI }
        // ],
        "@type":	"m:" + params.type, // fixed: "SimpleObsel", // TODO KTBS BUG TO FIX
        hasTrace:	"",
        subject:	params.hasOwnProperty("subject")?params.subject:this.get_default_subject(),
        //"m:type":	params.type
        "begin"	:	new Date().getTime(),
        "end"	:	new Date().getTime(),
      };
      //console.log(params.hasOwnProperty("subject")?params.subject:this.get_default_subject(),params.hasOwnProperty("subject"),params.subject,this.get_default_subject());
      if (params.hasOwnProperty("begin")) { json_obsel.begin = params.begin; }
      if (params.hasOwnProperty("end")) { json_obsel.begin = params.end;}
      if (params.hasOwnProperty("attributes")) {
        for (var attr in params.attributes) {
          if (params.attributes.hasOwnProperty(attr))          {json_obsel["m:" + attr] = params.attributes[attr];}
        }
      }
      port.postMessage({mess:"json_obsel: " + JSON.stringify(json_obsel)});
      return json_obsel ;
}

}
