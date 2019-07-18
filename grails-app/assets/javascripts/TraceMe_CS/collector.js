

  /*Trace Information */

	var TraceName 
	var TraceNameEnv
	var BAseURI 
	var Model_URI  ;
	var Trace_Information
	var scripts = document.getElementsByTagName('script');
	var thisScript = scripts[scripts.length-1];
	var path = thisScript.src.replace(/\/script\.js$/, '/');
	var Path_SharedWebWorker = path.replace("collector.js","sharedworkeur.js");
	var Path_Config_File = path.replace("collector.js","configurator.json");
	var version; 
    var SessionID;

  
document.addEventListener("DOMContentLoaded", function() {
  "use strict";
 
 
  

  console.log ("the tracing is started");
  
  
  /******** get information about trace  from server ****************/
  /***** Load webworker ******/
  port = connectToWorker();
  
  
  listenServer(function() {
	   /****** Collect information about document  **********/
	 //  send_URL(document.URL) ;
	   /******** get configuration  information  ************/
	   if (document.getElementsByTagName("iframe")[0]){
	     var iframe = document.getElementsByTagName("iframe")[0];
	     //iframe.contentWindow.addEventListener("DOMContentLoaded", function(){alert("dom loaded")}, true);
	     iframe.onload = function() {
	          getfileconfiguration()
	     }
	   }
	   else  {getfileconfiguration();}
  }); 
});




/**
 * Create the most appropriate supported worker for posting obsels.
 */
function connectToWorker() {
   console.log ("connectToWorker");
    if (window.SharedWorker ) {

        //console.log(workerUrl);
        var worker = new SharedWorker(Path_SharedWebWorker);
        port = worker.port;
        //port.addEventListener('message', workerMessageHandler);
        port.onmessage = workerMessageHandler;
        port.start();
        //worker.onerror = console.error.bind(console);
    } else {
        console.error("simple worker no implemented yet");
        // TODO implement simpleworker.js and uncomment code below
        //workerUrl = scriptUrl.replace('tracingyou.js', 'simpleworker.js');
        //console.log(workerUrl);
        //worker = new Worker(workerUrl);
        //worker.onmessage = workerMessageHandler;
        //port = worker;
    }
    //console.log(port);
    return port;
}

/**
 *  get trace information from cookies ***
 *  
 *  
 **/

function listenServer(callback) {
		"use strict";
		console.log ("cookies");
	var allcookie = document.cookie.split(";");
	for (var i = 0;i < allcookie.length;i++) {
		if (allcookie[i].split("=")[0].trim() === "BaseURI") {
			 BAseURI = decodeURIComponent(allcookie[i].split("=")[1]);
		}
		if (allcookie[i].split("=")[0].trim() === "TraceName") {

			 TraceName = allcookie[i].split("=")[1];
		}
		if (allcookie[i].split("=")[0].trim() === "TraceNameEnv") {

			 TraceNameEnv = allcookie[i].split("=")[1];
		}
		if (allcookie[i].split("=")[0].trim() === "ModelURI") {
			 Model_URI = decodeURIComponent(allcookie[i].split("=")[1]);
		}
		if (allcookie[i].split("=")[0].trim() === "SessionID") {
			SessionID = decodeURIComponent(allcookie[i].split("=")[1]);
		}
	}
		//if ((BAseURI) && (TraceName) && (Model_URI)) {
		Trace_Information = {TraceName: TraceName, BaseURI: BAseURI, ModelURI: Model_URI};
		/**** Send to the webworker traceInformation *****/
		//	sharedWorker.port.postMessage({mess: "TraceInformation", Trace_Information: Trace_Information});
		/**** Send to the webworker traceInformation *****/
		port.postMessage({mess: "TraceInformation", Trace_Information: Trace_Information});
		port.postMessage({mess: "TraceNameEnv", TraceNameEnv: TraceNameEnv});
		//}
	
	callback ();
}




window.addEventListener("load", function(event) {
    console.log("Toutes les ressources sont chargées !");
    console.log ("event before change page to send Obsel load ")
	var attribute = {};
	attribute.type="loadPage";
	attribute.subject="obsel of action closeBrowser ";
	attribute.attributes={};
	attribute.attributes.hasDate =new Date().format("yyyy-MM-dd h:mm:ss");
	attribute.attributes.sessionID = SessionID;
//	  /**** Send to webworker *****/
	  port.postMessage({mess: "obselunload", OBSEL: attribute});
  });

window.onbeforeunload = function(event) {
    var s = "You have unsaved changes. Really leave?";
    console.log ("event before change page")
    port.postMessage({mess: "Emptythewaitinglist"});	  
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
      //port.postMessage({mess:"json_obsel: " + JSON.stringify(json_obsel)});
      return json_obsel ;
}

/**
 * Hanlde message received from the worker.
 * Actually, the worker will only send one message,
 * containing the observation rules for this page.
 * @param evt
 */
function workerMessageHandler(evt) {

    console.log (evt.data.mess);
				var messName = evt.data.mess;
        //var messName = messageRecu.mess;
        if (messName === "GetTraceInf") {
          listenServer(function() {
        	  console.log ("get info")
        	  var Trace_Information = {TraceName: TraceName, BaseURI: BAseURI, ModelURI: Model_URI};
        		/**** Send to the webworker traceInformation *****/
        	 port.postMessage({mess: "TraceInformation", Trace_Information: Trace_Information});
        	 port.postMessage({mess: "TraceNameEnv", TraceNameEnv: TraceNameEnv});
          });
          
        }
        				//        if (messName ==="sendObseltoKTBs") {
        				//        	if ((version ==="AC")||(version==="AD")){
        				//        	console.log ("send to décision algorithme")
        				//            jQuery.ajax({
        				//            	type:'POST', 
        				//            	url: '/TRAC/Authentication/authentifier',
        				//                data: {params: version},
        				//            	success:function(data,textStatus){
        				//            		 var d = data;
        				//                     document.getElementById("trust").value = d ;
        				//            	},
        				//            	error:function(XMLHttpRequest,textStatus,errorThrown
        				//            			){}});
        				//        	}
        				//
        				//        }

}

/**

   get information from file configuration

**/

function getfileconfiguration () {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        var data = JSON.parse(xhr.responseText);
        if (data === null) {return false ; }
        for (var host = 0;host < data.Page.length;host++) {
          if ((document.URL === data.Page[host].URL) || (document.location.host === data.Page[host].HostName)) {
            makeAllListenersForConfiguration(data.Page[host]);
          }
        }
      } else {
      console.log ("erreur get config file ",xhr);
      }
    }
  };
  xhr.open("GET", Path_Config_File, true);
  xhr.send();
}

/**
  Make listener for all event in config file
**/

function makeAllListenersForConfiguration(Data) {
  "use strict";
  console.log ("page collected");
  var events = Data.event;
  for (var i = 0; i < events.length; i++) {
    // browse selector of each event
    for (var j = 0; j < events[i].selectors.length; j++) {
      if (events[i].selectors[j].iframe) {
            var contentIframe = document.getElementsByTagName("iframe")[0].contentWindow;
            var documentiframe = contentIframe.document
            if ((events[i].selectors[j].Selector === undefined) || (events[i].selectors[j].Selector === "")) {
            if ((events[i].typeObsel === undefined) || (events[i].typeObsel === "")) {
              addEvent (contentIframe.document,events[i].type, sendObsel);
            }
            else {
              //  $(document).on(event[i].type, {typeO: event[i].typeObsel}, sendObselWithType);//TODO
              addEvent (contentIframe.document,events[i].type, sendObselWithType);
            }
          }  //$(document).on(event[i].type, sendObsel); //TODO
          else {
            if ((events[i].typeObsel === undefined) || (events[i].typeObsel === "")) {
              //$(event[i].selectors[j].Selector).on (event[i].type, sendObsel);
              console.log (addEvent (contentIframe.document.querySelector(events[i].selectors[j].Selector),events[i].type, sendObsel));
              addEvent (contentIframe.document.querySelector(events[i].selectors[j].Selector),events[i].type, sendObsel);

            }
            else {
              //$(event[i].selectors[j].Selector).on (event[i].type, {typeO: event[i].typeObsel}, sendObselWithType);
              contentIframe.document.querySelector(events[i].selectors[j].Selector).typeO = events[i].typeObsel ;
              addEvent (contentIframe.document.querySelector(events[i].selectors[j].Selector),events[i].type, sendObselWithType);
            }
          }
      }

      else {
          if ((events[i].selectors[j].Selector === undefined) || (events[i].selectors[j].Selector === "")) {
          if ((events[i].typeObsel === undefined) || (events[i].typeObsel === "")) {
            addEvent (document,events[i].type, sendObsel);
          }
          else {
            //  $(document).on(event[i].type, {typeO: event[i].typeObsel}, sendObselWithType);//TODO
            addEvent (document,events[i].type, sendObselWithType);
          }
        }  //$(document).on(event[i].type, sendObsel); //TODO
        else {
          if ((events[i].typeObsel === undefined) || (events[i].typeObsel === "")) {
            //$(event[i].selectors[j].Selector).on (event[i].type, sendObsel);
//            console.log (addEvent (document.querySelector(event[i].selectors[j].Selector),event[i].type, sendObsel));
//            addEvent (document.querySelector(event[i].selectors[j].Selector),event[i].type, sendObsel);
        	  var boxes = document.querySelectorAll(events[i].selectors[j].Selector);
              for(var x=0; x<boxes.length; x++)
              {
                 addEvent (boxes[x],events[i].type, sendObsel);
              }

          }
          else {
            //$(event[i].selectors[j].Selector).on (event[i].type, {typeO: event[i].typeObsel}, sendObselWithType);
//            document.querySelector(event[i].selectors[j].Selector).typeO = event[i].typeObsel ;
//            addEvent (document.querySelector(event[i].selectors[j].Selector),event[i].type, sendObselWithType);
        	  var boxes = document.querySelectorAll(events[i].selectors[j].Selector);
              for(var x=0; x<boxes.length; x++)
              {
                var typeO = events[i].type;
               eval ("boxes[x]."+typeO+" = \""+ events[i].typeObsel+"\"") ;
               addEvent (boxes[x],events[i].type, sendObselWithType);
              }
          }
        }
      }
      }
  }
}

var  getXPath = function (element) {
  "use strict";
  // derived from http://stackoverflow.com/a/3454579/1235487
  while (element && element.nodeType !== 1) {
    element = element.parentNode;
  }
  if (typeof (element) === "undefined") { return "(undefined)"; }
  if (element === null) { return "(null)"; }

  var xpath = "";
  for (true; element && element.nodeType === 1; element = element.parentNode) {
    var id = Array.prototype.indexOf.call(element.parentNode.childNodes, element);
    id = (id > 1  ?  "[" + id + "]"  :  "");
    xpath = "/" + element.tagName.toLowerCase() + id + xpath;
  }
return xpath;
};

var getElementName = function (element) {
  "use strict";
  while (element && element.nodeType !== 1) {
    element = element.parentNode;
  }
  if (typeof (element) === "undefined") { return "(undefined)"; }
  if (element === null) { return "(null)"; }
  var id = Array.prototype.indexOf.call(element.parentNode.childNodes, element);
  id = (id > 1  ?  "[" + id + "]"  :  "");
  var nameE = element.tagName.toLowerCase() + id;
  return nameE;
};

var getElementId = function (element) {
  "use strict";
  while (element && element.nodeType !== 1)  {
    element = element.parentNode;
  }
  if (typeof (element) === "undefined") { return "(undefined)"; }
  if (element === null) { return "(null)"; }
  if (typeof (element.id) !== "undefined") { return element.id; }
  return "#";
};
Date.prototype.format = function(format) { // jshint ignore:line
  "use strict";
  var o = {
  "M+": this.getMonth() + 1, //month
  "d+": this.getDate(),    //day
  "h+": this.getHours(),   //hour
  "m+": this.getMinutes(), //minute
  "s+": this.getSeconds(), //second
  "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
  "S": this.getMilliseconds() //millisecond
  };
  if (/(y+)/.test(format)) {
	   format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for (var k in o) {
	   if (new RegExp("(" + k + ")").test(format)) {
		     format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
       }
  }
  return format;
};

function fillCommonAttributes (e, attributes) {
  "use strict";
  attributes.hasDate = new Date().format("yyyy-MM-dd h:mm:ss");
  attributes.hasType = e.type;
  attributes.hasDocument_URL = document.URL;
  attributes.hasDocument_Title = document.title;
  attributes.ctrlKey = e.ctrlKey;
  attributes.shiftKey = e.shiftKey;
  attributes.hasTarget = getXPath(e.target);
  attributes.hasTarget_targetName = getElementName(e.target);
  if (e.target){
  if (e.target.id) { attributes.hasTarget_targetId = e.target.id; }
  if (e.target.alt) { attributes.hasTarget_ALT = e.target.alt; }
  if (e.target.value) {attributes.hasTarget_Value = e.target.value;}
  if (e.target.firstChild) {if ((e.target.firstChild.nodeValue) && (e.target.firstChild.nodeValue !== "")) {attributes.hasTarget_TextNode = e.target.firstChild.nodeValue.replace(/[\n]/gi, "");}}
  if (e.keyCode) {attributes.keyCode = e.keyCode;}
  if (e.target.className) {attributes.hasTarget_ClassName = e.target.className.toString();}
  if (e.target.text) {
  var text = e.target.text.replace(/[\n]/gi, "");
  attributes.hasTarget_targetText = text; }
  if (e.target.title) {attributes.hasTarget_Title = e.target.title;}
  }
  if (e.currentTarget) {
    attributes.currentTarget = getXPath(e.currentTarget);
    attributes.hascurrentTarget_currentTargetName = getElementName(e.currentTarget);
    if (e.currentTarget.id) {
      attributes.hasCurrentTarget_currentTargetId = getElementId(e.currentTarget);
    }
    if (e.currentTarget.text) {
      var texte = e.currentTarget.text.replace(/[\n]/gi, "");
      attributes.hasCurrentTarget_currentTargetText = texte;
    }
  }
  if (e.explicitOriginalTarget) {
    attributes.hasOriginalTarget = getXPath(e.explicitOriginalTarget);
    attributes.hasOriginalTarget_originalTargetName = getElementName(e.explicitOriginalTarget);
    if (e.explicitOriginalTarget.id) {
      attributes.hasOriginalTarget_originalTargetId = getElementId(e.explicitOriginalTarget);
    }
    if (e.explicitOriginalTarget.text) {
      attributes.hasOriginalTarget_originalTargetText = e.explicitOriginalTarget.text;
    }
  }
  if (e.target) {
  if (e.target.tagName === "IMG") {
    attributes.hasImgSrc = e.target.src;
  }
  }
  if (e.which){
      if (e.which === 1) {attributes.TypeButton = "Rightbutton"}
          else if (e.which === 2) {attributes.TypeButton = "Middlebutton"}
              else if (e.which === 3) {attributes.TypeButton = "Leftbutton"}
  attributes.Character = String.fromCharCode( e.which );
  }
}
/**
  other function
**/
var sendObsel =  function(e) {
  "use strict";
  var obsel = {};
  var attribute = {
    'x': e.clientX,
    'y': e.clientY,
  };
  fillCommonAttributes(e, attribute);
  obsel.type  = e.type ;
  obsel.subject = e.type;
  obsel.attributes = attribute;
  port.postMessage({mess: "obsel", OBSEL: obsel});
};

var sendObselWithType =  function(e) {
  "use strict";
  var obsel = {};
  var attribute = {
    'x': e.clientX,
    'y': e.clientY,
  };
  fillCommonAttributes(e, attribute);
   // console.log("***********************************"+e.target[e.type]+"****************************");
  //obsel.type = e.target.typeO ;
  obsel.type = e.target[e.type];
  obsel.subject = e.type;
  obsel.attributes = attribute ;
  port.postMessage({mess: "obsel", OBSEL: obsel});
};

var addEvent = function (el, eventType, handler) {
  "use strict";
  if (el.addEventListener) { // DOM Level 2 browsers
    el.addEventListener(eventType, handler, false);
  } else if (el.attachEvent) { // IE <= 8
    el.attachEvent('on' + eventType, handler);
  } else { // ancient browsers
    el['on' + eventType] = handler;
  }
};

function send_URL(URL) {
  "use strict";
  var attribute = {};
  attribute.type="Open_Page";
  attribute.subject="obsel of action open page ";
  attribute.attributes={};
  attribute.attributes.hasDate =new Date().format("yyyy-MM-dd h:mm:ss");
  attribute.attributes.hasDocument_URL = URL;
  attribute.attributes.hasDocument_Title = document.title;
  /**** Send to webworker *****/
  port.postMessage({mess: "obsel", OBSEL: attribute});
}


/* jQuery.ajax({
	type:'POST', 
	url:'/TRAC/Experience/experienceParam',
	data: {params:'this is params'},
	success:function(data,textStatus){
		console.log (data);
		version = data
		listenServer(function() {
		   *//****** Collect information about document  **********//*
		   send_URL(document.URL) ;
		   *//******** get configuration  information  ************//*
		   if (document.getElementsByTagName("iframe")[0]){
		     var iframe = document.getElementsByTagName("iframe")[0];
		     //iframe.contentWindow.addEventListener("DOMContentLoaded", function(){alert("dom loaded")}, true);
		     iframe.onload = function() {
		     getfileconfiguration()
		     }
		   }
		   else  {getfileconfiguration();}
	  }); 
	},
	error:function(XMLHttpRequest,textStatus,errorThrown
			){}});
*/
