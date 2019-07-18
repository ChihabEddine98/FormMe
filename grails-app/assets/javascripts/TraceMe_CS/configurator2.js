var eventObj= ["click","dblclick","Focus","keydown","keypress","mouseover","Load","keyup","change","mouseup","mousedown","wheel"];
var index=0;
var scripts = document.getElementsByTagName('script');
var thisScript = scripts[scripts.length-1];
var path = thisScript.src.replace(/\/script\.js$/, '/');
var Path_SharedWebWorker = path.replace("configurator.js","sharedworkeur.js");

var sharedWorker = new SharedWorker (Path_SharedWebWorker);
sharedWorker.port.start();



	document.addEventListener("DOMContentLoaded", function() {

	"use strict";
  var loadBoutton = document.getElementById("Load");
  /*** Load event ***/
  loadBoutton.addEventListener('click', function() {
		var URL = document.getElementById("URL").value;
		window.open(URL,"nom_popup","menubar=no, status=no, scrollbars=no, menubar=no, width=400, height=400");
		window.setTimeout (function (){
			sharedWorker.port.postMessage({mess:"GetDataPage"});
		},1000);

	},false);
	var urlElement = document.getElementById("URL");
  urlElement.addEventListener('keypress', function(e) {
	     if (e.keyCode===13){
		var URL = document.getElementById("URL").value;
		window.open(URL,"nom_popup","menubar=no, status=no, scrollbars=no, menubar=no, width=400, height=400");
		e.preventDefault();
		window.setTimeout (function (){
			sharedWorker.port.postMessage({mess:"GetDataPage"});
		},1000);
		}
	},false);

  sharedWorker.port.onmessage = function(e) {
		var messName = e.data.mess;
		console.log (messName);

    if (messName === "DataIframe"){
      var contentIframe = document.getElementsByTagName("iframe")[0].contentWindow;
      var head = contentIframe.document.getElementsByTagName("head")[0];
      var body = contentIframe.document.getElementsByTagName("body")[0];
      head.innerHTML = e.data.header;
      body.innerHTML = e.data.body;
      body.addEventListener ('click',function(e){
    	  e.preventDefault();
				document.getElementById("SelectorT").innerHTML= getPath(e.target) ;

		 });
	 }

 };
    /*############### ADD EVENT  ###############*/
    var addEventElement = document.getElementById("ADDEvent");
    addEventElement.addEventListener ('click',function(){
        // Event div
            var eventdiv = document.createElement("div");
            eventdiv.setAttribute ("Id",index);
            var div = document.createElement("div");
            div.setAttribute ('class','input-group');
            var label = document.createElement("label");
            label.setAttribute('for','Event Type');
            label.setAttribute('class','control-label input-group-addon');
            label.appendChild(document.createTextNode("Event Type"));
            var select = document.createElement("select");
            select.setAttribute('class','form-control');
            select.setAttribute('Id','eventType');
            for (var j=0; j< eventObj.length; j++)
            {
            select.options[select.options.length] = new Option(eventObj[j],eventObj[j]);
            }
            div.appendChild(label);
            div.appendChild(select);
        // Selector div
            //var div1 = document.createElement("div");
            //div1.setAttribute ('class','input-group');
            //div1.setAttribute ('Id','Element');
            var label1 = document.createElement("label");
            label1.setAttribute('for','Selector');
            label1.setAttribute('class','control-label input-group-addon');
            label1.appendChild(document.createTextNode("Selector"));
            // div1.appendChild(label1);
            var inputElement = document.createElement("input");
            inputElement.setAttribute ('id','selector');
            inputElement.setAttribute ('type','text');
            inputElement.setAttribute ('class','selector');
            // div1.appendChild(inputElement);
		// obsel type div
            var div2 = document.createElement("div");
            div2.setAttribute ('class','input-group');
            div2.setAttribute ('Id','Type');
            var label2 = document.createElement("label");
            label2.setAttribute('for','TypeO');
            label2.setAttribute('class','control-label input-group-addon');
            label2.appendChild(document.createTextNode("Type Obsel"));
            // div2.appendChild(label2);
            var inputElementType = document.createElement("input");
            inputElementType.setAttribute ('id','TypeO');
						inputElementType.setAttribute ('type','text');
						inputElementType.setAttribute ('class','TypeO');
            // div2.appendChild(inputElementType);

			//
						eventdiv.appendChild(div);
            //eventdiv.appendChild(div1);
						eventdiv.appendChild(label1);
  					eventdiv.appendChild(inputElement);
						//eventdiv.appendChild(div2);
							eventdiv.appendChild(label2);
							eventdiv.appendChild(inputElementType);

            document.getElementById("EventList").appendChild(eventdiv);
            index++;
    },false);
    /*############### EVENT SAVE CONFIGURATION ###############*/
		var saveElement = document.getElementById("Save");
		saveElement.addEventListener ("click", function() {
        var  URL = document.getElementById("URL").value;
        var eventArray= [];
        // parcourir all event $
        for (var i=0 ; i< index ; i++ )
        {
            var eventType = document.getElementById(i).getElementsByTagName("select")[0].value;
						var selector = document.getElementById(i).getElementsByClassName("selector")[0].value;
						var typeO = document.getElementById(i).getElementsByClassName("TypeO")[0].value;
            var SelectorArray=[];
            // selector
            var SelectorObj={frame:"", Selector:selector};
            SelectorArray.push(SelectorObj);
            var eventObj ={type:eventType,typeObsel:typeO,selectors:SelectorArray};
            eventArray.push (eventObj);
       }

        // host or URL
        var Page = {URL: URL , HostName: "", event:eventArray };
        if (document.getElementById("URLCK").checked)
        {
            var Page = {URL: URL , HostName: "", event:eventArray };
        }

        if (document.getElementById("Hostname").checked)
        {
            var hostname = getHostname (URL);
            var Page = {URL:"" , HostName: hostname, event:eventArray };
        }


	    // if (kango.storage.getItem("Config") != undefined  )
      //   {
      //       var PageArray = kango.storage.getItem("Config").Page;
      //   }
      //   else
      //   {
      //       kango.storage.setItem("Config",kango.i18n.getMessage('CalcoConfig'));
      //       var PageArray = JSON.parse(kango.storage.getItem("Config")).Page;
			//
      //   }
      //   PageArray.push(Page);
	    // Config = {Page:PageArray};
      //   kango.storage.setItem("Config",Config);
	    // window.location.reload();
			console.log (Page);
    	var config = document.body.appendChild(document.createElement('pre'))
			config.innerHTML = "";
			config.innerHTML = JSON.stringify(Page, undefined, 4);

		},false);

		function getPath ( e,path ) {
			// The first time this function is called, path won't be defined.
			if ( typeof path == 'undefined' ) path = '';

			// If this element is <html> we've reached the end of the path.
			if ( e.nodeName === "HTML" )
				return 'html' + path;

			// Add the element name.
			var cur = e.nodeName.toLowerCase();

			// Determine the IDs and path.
			var id    = e.id;
			 var   classe = e.className;


			// Add the #id if there is one.
			if ( id !== "" )
				cur += '#' + id;

			// Add any classes.
			if ( classe !== "" )
				cur += '.' + classe.split(/[\s\n]+/).join('.');

			// Recurse up the DOM.
			return getPath( e.parentElement,' > ' + cur + path );
		}
    function  getHostname (href) {
        var l = document.createElement("a");
        l.href = href;
        var hostname = l.hostname ;
        return hostname;
    }
});
