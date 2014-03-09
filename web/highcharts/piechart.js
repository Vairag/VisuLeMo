function mypiechart(args) {
    $('#container3').css('display','block');
    $("#SpaceTree_info").remove();
    $("#HyperTree_info").remove();
    
    var chart;
    var interest_names = JSON.parse(args.interest_names);// As a JSON object
   //console.log("interest_names-all:: "+interest_names);
   
   var interest_weights = JSON.parse(args.interest_weights);// As a JSON object
   //console.log("iinterest_weights-all:: "+interest_weights);
    
    var colors = Highcharts.getOptions().colors;
    //var data=interest_weights;   //For same color in all columns
    //var categories=interest_names; 
           
    var temm= '[';   
    for (var i=0;i<interest_weights.length;i++)
    {
          temm=temm+'[ "'+interest_names[i]+'" , '+interest_weights[i]+' ],';
    }   
            
      temm=temm+' ]';
     // console.log("temm: raja"+temm);
      data=eval ("(" + temm + ")");
       //console.log("temm data: "+data);
     	
    	// Build the chart
        $('#container3').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                zoomType: 'xy'
            },
            title: {
                text: 'Interest share'
            },
            subtitle: {
                text: 'Source: VisuLeMo'
            },
            tooltip: {
        	    pointFormat: '<b>{point.percentage:.1f}%</b> of all interests'
            },
            plotOptions: {
                pie: {
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'interest weight',
                data: data
            }]
        });
   
    
}