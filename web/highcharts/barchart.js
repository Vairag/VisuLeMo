function mybarchart(args) {
     $('#container3').css('display','block');
    $("#SpaceTree_info").remove();
    $("#HyperTree_info").remove();
    
    var interest_namesPALM = JSON.parse(args.interest_namesPALM);// As a JSON object
  // console.log("interest_names-all:: "+interest_names);
   
   var interest_weightsPALM = JSON.parse(args.interest_weightsPALM);// As a JSON object
   //console.log("iinterest_weights-all:: "+interest_weights);
   
    //Webtrace
    var interest_namesWb = JSON.parse(args.interest_namesWb);
    var interest_weightsWb = JSON.parse(args.interest_weightsWb);
    
    var colors = Highcharts.getOptions().colors;
    //#2f7ed8,#0d233a,#8bbc21,#910000,#1aadce,#492970,#f28f43,#77a1e5,#c42525,#a6c96a 
    
   // var categories=interest_namesPALM+" ,"+interest_namesWb; 
    //console.log("categories before:: "+categories);
     //categories1=eval ("(" + categories + ")");
    //console.log("categories 1:: "+categories1);
     /*data = [{
                    y: 55.11,
                    color: colors[0]
                }, {
                    y: 21.63,
                    color: colors[1]
                }];    */
             
    var temm= '[';
    var ctemp= '[';
    
    for (var i=0;i<interest_weightsPALM.length;i++)
    { 
          ctemp=ctemp+'"'+interest_namesPALM[i]+'", ';
          temm=temm+'{ "y":'+interest_weightsPALM[i]+' , "color":"#492970" },';
    }   
    for (var i=0;i<interest_weightsWb.length;i++)
    { 
          ctemp=ctemp+'"'+interest_namesWb[i]+'", ';
          temm=temm+'{ "y":'+interest_weightsWb[i]+' , "color":"#2f7ed8" },';
    }   
      
      ctemp=ctemp+' ]';
      temm=temm+' ]';
      console.log("ctemp: "+ctemp);
      console.log("temm: "+temm);
      
      categories=eval ("(" + ctemp + ")");
      console.log("categories: "+categories);
      data=eval ("(" + temm + ")");
       console.log("data: "+data);
      $('#container3').highcharts({
            chart: {
                type: 'column'
                
            },
            title: {
                text: 'Importance of interests'
            },
            subtitle: {
                text: 'Source: VisuLeMo'
            },
            xAxis: {
                categories: categories,
                title: {
                    text: '<b style="color:#492970;">PALM </b> <span style="color:black;">  AND  </span><b style="color:#2f7ed8;"> Webtrace</b>'
                },
                labels: {
                    rotation: -90,
                    align: 'right',
                }
            },
            yAxis: {
                min: 0,
                max: 1,
                title: {
                    text: 'Importance value (0 - 1)'
                }
            },
            tooltip: {
                headerFormat: '<span>{point.key} </span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding-top:10px">with importance value: </td>' +
                            '<td style="padding-top:10px;"><b>{point.y:.2f}</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
            },
            plotOptions: {
                
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                    
                }
            },
            series: [{
               
                name: 'Interest names',
                data: data,
                showInLegend: false
    
            }]
        });
    
    }