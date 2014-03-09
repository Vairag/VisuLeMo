function mydotchart(args) {
     $('#container3').css('display','block');
    var interest_names = JSON.parse(args.interest_names);// As a JSON object
   console.log("interest_names-all:: "+interest_names);
   
   var interest_weights = JSON.parse(args.interest_weights);// As a JSON object
   console.log("iinterest_weights-all:: "+interest_weights);
    
    var colors = Highcharts.getOptions().colors;
    //var data=interest_weights;   //For same color in all columns
    var categories=interest_names; 
            
    var temm= '[';
    
    var ic=0;
    for (var i=0;i<interest_weights.length;i++)
    { 
          ic++;
          if(ic>9){ic=0;}
          temm=temm+'{ "y":'+interest_weights[i]+' , "color":"'+colors[ic]+'" },';
    }   
            
      temm=temm+' ]';
      console.log("temm: "+temm);
      data=eval ("(" + temm + ")");
     
            $('#container3').highcharts({
            chart: {
                type: 'scatter',
                zoomType: 'xy'
            },
            title: {
                text: 'interest v/s weight'
            },
            subtitle: {
                text: 'Source: VisuLeMo'
            },
            xAxis: {
                title: {
                    enabled: true,
                    text: 'interest names'
                },
		categories: categories,
                startOnTick: true,
                endOnTick: true,
                showLastLabel: true,
                labels: {
                    rotation: -90,
                    align: 'right',
                }
            },
            yAxis: {
                title: {
                    text: 'interest weight (%)'
                }
            },
            
            plotOptions: {
                scatter: {
                    marker: {
                        radius: 5,
                        states: {
                            hover: {
                                enabled: true,
                                lineColor: 'rgb(100,100,100)'
                            }
                        }
                    },
                    states: {
                        hover: {
                            marker: {
                                enabled: false
                            }
                        }
                    },
                    tooltip: {
                        headerFormat: '<b style="font-size:10px">{point.key} </b><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">with interest weight: </td>' +
                            '<td style="padding:0"><b>{point.y:.2f} %</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    }
                }
            },
            series: [{
                name: 'interests',
                color: '#1aadce',
                data: data
            }]
        });
    }