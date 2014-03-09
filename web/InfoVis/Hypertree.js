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


function initHypertree(args){     
  
  //init data
        // var json={ "id": "01", "name": "Kumar", "children": [] };
        //$.get("JSON/newjson.json", function(data) { //json = data;  });
        //var temp = JSON.parse(args.temp1);//serialized data from server
        //var json= eval("(" + temp + ")");
  var json = JSON.parse(args.testdata);//serialized data from server
     $('#SpaceTree_info').remove();   
        //$( "<p>Test</p>" ).insertBefore( "#container3" );
  $('#container3').css('display','block').html('<div id="center-container" class="span11"><div class="span11" id="infovis"></div></div><div class="span1" id="log"></div>');     
    $("#SpaceTree_info").remove();
    
    if ($('#HyperTree_info').length == 0) {
         $( "<div id='HyperTree_info' class='span11'>*Use the mouse wheel to zoom and drag and drop the canvas to pan.<br/>\n\
        *Click on node to center it.<br/>\n\
        *Leaves represent interests and parent nodes represent categories.</div>" ).insertBefore( "#container3" ); 
    }
        //console.log("Raja Rani--"+json.toString());     
  
    var infovis = document.getElementById('infovis');
    var w = infovis.offsetWidth - 50, h = infovis.offsetHeight - 50;
    
    //init Hypertree
    var ht = new $jit.Hypertree({
      //id of the visualization container
      injectInto: 'infovis',
      //canvas width and height
      width: w,
      height: h,
      //Change node and edge styles such as
      //color, width and dimensions.
      //enable panning
        Navigation: {
          enable:true,
          panning:true,
           zooming: 40  
        },
      
      
      Node: {
          dim: 5,
          color: "#000021"
      },
      Edge: {
          lineWidth: 1,
         color: "#088"
      },
      onBeforeCompute: function(node){
          Log.write("Focusing on center");
      },
      //Attach event handlers and add text to the
      //labels. This method is only triggered on label
      //creation
      onCreateLabel: function(domElement, node){
          domElement.innerHTML = node.name;
          $jit.util.addEvent(domElement, 'click', function () {
              ht.onClick(node.id, {
                  onComplete: function() {
                      ht.controller.onComplete();
                  }
              });
          });
      },
      //Change node styles when labels are placed
      //or moved.
      onPlaceLabel: function(domElement, node){
          var style = domElement.style;
          style.display = '';
          style.cursor = 'pointer';
          if (node._depth < 1) {
              style.fontSize = "1.0em";
              style.color = "black";

          }else if(node._depth == 1){
              style.fontSize = "0.9em";
              style.color = "#333300";

          } else if(node._depth == 2){
              style.fontSize = "0.8em";
              style.color = "#3D003D";

          } else if(node._depth == 3){
              
              style.fontSize = "0.8em";
              style.color = "black";
          }else {
              style.display = 'none';
          }
          

          var left = parseInt(style.left);
          var w = domElement.offsetWidth;
          style.left = (left - w / 2) + 'px';
      },
      
      onComplete: function(){
          Log.write("done");
          
          //Build the right column relations list.
          //This is done by collecting the information (stored in the data property) 
          //for all the nodes adjacent to the centered node.
         /* var node = ht.graph.getClosestNodeToOrigin("current");
          var html = "<h4>" + node.name + "</h4><b>Connections:</b>";
          html += "<ul>";
          node.eachAdjacency(function(adj){
              var child = adj.nodeTo;
              if (child.data) {
                  var rel = (child.data.band == node.name) ? child.data.relation : node.data.relation;
                  html += "<li>" + child.name + " " + "<div class=\"relation\">(relation: " + rel + ")</div></li>";
              }
          });
          html += "</ul>";
          $jit.id('inner-details').innerHTML = html; */
      }
    });
    //load JSON data.
     //console.log("Jn ::-"+JSON.stringify(json));
    ht.loadJSON(json);
    //compute positions and plot.
    ht.refresh();
    //end
    ht.controller.onComplete();
    
  
}
