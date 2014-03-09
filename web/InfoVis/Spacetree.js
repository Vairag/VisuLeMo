var labelType, useGradients, nativeTextSupport, animate;

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    this.elem.innerHTML = text;
    this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
  
  }
};


function initSpacetree(args){
    //init data
    //$('#SpaceTree_info').remove();
    var json = JSON.parse(args.testdata);//serialized data from server
  $('#container3').css('display','block').html('<div id="center-container" class="span11"><div class="span11" id="infovis"></div></div><div class="span1" id="log"></div>');     
   $("#HyperTree_info").remove();
    if ($('#SpaceTree_info').length == 0) {
         $( "<div id='SpaceTree_info' class='span11'>*Use the mouse wheel to zoom and drag and drop the canvas to pan.<br/> \n\
         *Lighter to darker background of interest represents less to more categories of interests.  Lighter <span style='background:#EBE0D6;'>_<4_</span><span style='background:#aaa;'>_<7_</span><span style='background:#caa;'>_<10_</span><span style='background:#daa;'>_<13_</span><span style='background:#faa;'>_<16_</span><span style='background:black;'>___</span> Darker<br/>\n\
         *Click on interest name to see the categories.</div>" ).insertBefore( "#container3" ); 
    }
    
        //console.log("spacetree json--"+json.toString());  
    
    //end
    //init Spacetree
    //Create a new ST instance
    var st = new $jit.ST({
        //id of viz container element
        injectInto: 'infovis',
        //set duration for the animation
        duration: 800,
        //set animation transition type
        transition: $jit.Trans.Quart.easeInOut,
        constrained:false,
        levelsToShow: 1,
        //set distance between node and its children
        levelDistance: 50,
        offsetX:200,
        //orientation: 'top',
        
        //enable panning
        Navigation: {
          enable:true,
          panning:true,
           zooming: 50  
        },
       
        //set node and edge styles
        //set overridable=true for styling individual
        //nodes or edges
        Node: {
            height: 30,
            width: 160,
            
            type: 'rectangle',
            color: '#aaa',
            overridable: true
        },
        
        Edge: {
            type: 'bezier',
            overridable: true
        },
        
        onBeforeCompute: function(node){
            Log.write("loading " + node.name);
        },
        
        onAfterCompute: function(){
            Log.write("done");
        },
        
        //This method is called on DOM label creation.
        //Use this method to add event handlers and styles to
        //your node.
        onCreateLabel: function(label, node){
            label.id = node.id;            
            label.innerHTML = node.name;
            label.onclick = function(){
            	  st.onClick(node.id);
            };
            //set label styles
            var style = label.style;
            style.width = 155 + 'px';
            style.height = 15 + 'px';            
            style.cursor = 'pointer';
            style.color = '#333';
            style.fontSize = '0.8em';
            style.textAlign= 'center';
            style.paddingTop = '4px';
        },
        
        //This method is called right before plotting
        //a node. It's useful for changing an individual node
        //style properties before plotting it.
        //The data properties prefixed with a dollar
        //sign will override the global node style properties.
        onBeforePlotNode: function(node){
            //add some color to the nodes in the path between the
            //root node and the selected node.
            if (node.selected) {
               // node.data.$color = "#66CC99";  //color for selected node!
            }
            else {
                delete node.data.$color;
                //if the node belongs to the last plotted level
                if(!node.anySubnode("exist")) {
                    //count children number
                    var count = 0;
                    node.eachSubnode(function(n) { count++; });
                    //assign a node color based on
                    //how many children it has
                    node.data.$color = ['#85ADAD', '#EBE0D6', '#EBE0D6', '#EBE0D6', '#aaa', '#aaa', '#aaa', '#caa','#caa','#caa', '#daa', '#daa', '#daa', '#faa', '#faa', '#faa'][count]; 
                    console.log(count+" name:: "+ node.name + "color: "+node.data.$color);
                }
            }
        },
        
        //This method is called right before plotting
        //an edge. It's useful for changing an individual edge
        //style properties before plotting it.
        //Edge data proprties prefixed with a dollar sign will
        //override the Edge global style properties.
        onBeforePlotLine: function(adj){
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#eed";
                adj.data.$lineWidth = 4;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }
    });
   
    //load json data
    st.loadJSON(json);
    
 
    //compute node positions and layout
    st.compute();
    //optional: make a translation of the tree
    st.geom.translate(new $jit.Complex(-200, 0), "current");
    //emulate a click on the root node.
    st.onClick(st.root);
    //end
    
}
