function mytimechartBubble(args) {
    $('#container3').css('display','block');
    $("#SpaceTree_info").remove();
    $("#HyperTree_info").remove();
    var interest_names = JSON.parse(args.interest_names);// As a JSON object
  // console.log("interest_names-all:: "+interest_names);
   
   var interest_weights = JSON.parse(args.interest_weights);// As a JSON object
   //console.log("iinterest_weights-all:: "+interest_weights);
   
    var timesDt = JSON.parse(args.timesDt);// As a JSON object
    //console.log("Time in date:: "+timesDt);
   
    var categories=interest_names; 
    var temm= '[';
    for (var i=0;i<timesDt.length;i++)
    { 
         var t_year=new Date(timesDt[i]).getFullYear();
         var t_month=new Date(timesDt[i]).getMonth();
         var t_day=new Date(timesDt[i]).getDate();
          temm=temm+'[ '+Date.UTC(t_year,t_month,t_day)+' , '+interest_weights[i]+' ],';
          
    }         
    temm=temm+' ]';
    //console.log("temm: "+temm);
    data=eval ("(" + temm + ")");
    //console.log("currenty used time:: "+data);
      
    
    
    
        $('#container3').highcharts({
            chart: {
                type: 'bubble',
            },
            title: {
                text: 'Evaluation of interests over time'
            },
            subtitle: {
                text: 'Source: VisuLeMo'
            },
            xAxis: {
                categories: categories,
                title: {
                    text: 'Interest names'
                },
                labels: {
                    rotation: -90,
                    align: 'right',
                }
            },
            yAxis: {
                type: 'datetime',
                title: {
                    text: 'Modified time'
                }
				
            },
            tooltip: {
                formatter: function() {
                     //console.log(this);
                        return '<b>'+ this.x +'</b><br/>was modified on '+Highcharts.dateFormat('%e. %b %Y', this.y)+'<br/><b>with importance value: </b>'+this.point.z;
                }
            },
            series: [{
                name: 'interests',
                color: '#660033',
                data: data,
                showInLegend: false
            }]
        });
    }

