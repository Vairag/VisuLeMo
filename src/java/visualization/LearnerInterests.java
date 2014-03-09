package visualization;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import visualization.APIJsonBean;
import visualization.Related;

/**
 *
 * @author Vairag
 */
@ManagedBean(name = "learnerinterests")
@SessionScoped
public class LearnerInterests implements Serializable {
    
    APIJsonBean pjb=new APIJsonBean();
    DbConnection db = new DbConnection();
    ResultSet rs,rs_types,rs_interests;
    PreparedStatement stmt,stmt_types, stmt_interests;
    Connection con = db.getConnection();
    ArrayList<MyInterest> myI = new ArrayList<MyInterest>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<String> domains = new ArrayList<String>();
    ArrayList<String> domains_temp2 = new ArrayList<String>();
    Map <Integer, String> domains_temp = new HashMap<Integer, String>();
    MyInterest currentInterest=new MyInterest();
    
     
    /**
     * Creates a new instance of User
     */
    public LearnerInterests() {
       
     }
    
    public void getLearnerInterests(int current_Lid) {    }
    
    
    public String getLearnerInterests(int current_Lid, String steps) {
        
        myI.clear();
        String query = "SELECT LI.learner_interest_id, I.interest_id, I.interest_name, LI.weight, A.action_name, S.source_name, S.source_url, F.framework_name, F.framework_link, IT.item_link FROM learners_interests AS LI, interests AS I, actions AS A, sources AS S, frameworks AS F, items AS IT WHERE LI.interest_id = I.interest_id AND LI.action_id=A.action_id AND LI.source_id=S.source_id AND LI.framework_id=F.framework_id AND LI.item_id=IT.item_id AND learner_id =?";
        //String query = "SELECT LI.learner_interest_id, I.interest_id, I.interest_name, LI.weight, A.action_name, S.source_name, S.source_url, F.framework_name, F.framework_link FROM learners_interests AS LI, interests AS I, actions AS A, sources AS S, frameworks AS F WHERE LI.interest_id = I.interest_id AND LI.action_id=A.action_id AND LI.source_id=S.source_id AND LI.framework_id=F.framework_id AND learner_id =?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,current_Lid);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                MyInterest m= new MyInterest();
                
                m.setLearner_interest_id(rs.getInt("learner_interest_id"));
                m.setInterest_id(rs.getInt("interest_id")); 
                m.setInterest_name(rs.getString("interest_name"));
                m.setWeight(rs.getFloat("weight"));
                m.setAction_name(rs.getString("action_name"));
                m.setSource_name(rs.getString("source_name"));
                m.setSource_url(rs.getString("source_url"));
                m.setFramework_name(rs.getString("framework_name"));
                m.setFramework_link(rs.getString("framework_link"));
                m.setItem_link(rs.getString("item_link"));
    	        myI.add(m);
                    
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LearnerInterests.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        if(steps.equals("yes")){
                return "step_myinterests";    
            }else{
                return "myinterests";
            }
    }
    
    public String viewTypesDomainOfInterest(MyInterest mi)
    {
        currentInterest=mi;
        types.clear();
        domains.clear();
        
        String query = "SELECT DISTINCT T.type_name, D.domain_name FROM interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE TI.type_id = T.type_id AND T.domain_id = D.domain_id AND TI.interest_id =?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,mi.getInterest_id());
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                
    	        types.add(rs.getString("type_name"));
                domains.add(rs.getString("domain_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "viewtypedomain";
    }  
    
    public void updateLearnerInterests(ArrayList<MyInterest> myI_temp) {
        
        int temp_interestId, temp_actionId,temp_itemId;
        float weight;
        for(MyInterest m1: myI_temp) {
                    String temp_interest = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("myInterestformTable:interest"+m1.learner_interest_id);
                    String temp_weight = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("myInterestformTable:weight"+m1.learner_interest_id);
                    String temp_action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("myInterestformTable:action"+m1.learner_interest_id);
                    String temp_items = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("myInterestformTable:items"+m1.learner_interest_id);
                    
                   //System.out.println("got from form::"+m1.learner_interest_id + temp_interest+temp_weight +temp_action);   
                   temp_interestId= pjb.insertInterest(temp_interest);
                   weight = Float.parseFloat(temp_weight);
                   temp_actionId= pjb.insertAction(temp_action);
                   temp_itemId= pjb.insertItems(temp_items, 1);
                           
                   
                    String query ="UPDATE learners_interests SET interest_id=?, weight=?, action_id=?, item_id=? WHERE learner_interest_id=?";
                    try {
                           stmt = con.prepareStatement(query);
                           stmt.setInt(1, temp_interestId);
                           stmt.setFloat(2, weight);
                           stmt.setInt(3, temp_actionId);
                           stmt.setInt(4, temp_itemId);
                           stmt.setInt(5, m1.learner_interest_id);
                           stmt.execute();          
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }           
         }
    }
    
    public void getweightChartData(int learnerId, int num_of_interest) { 
       myI.clear();
       ArrayList<String> interest_namesPALM = new ArrayList<String>();
       ArrayList<Float> interest_weightsPALM = new ArrayList<Float>();
       ArrayList<String> interest_namesWb = new ArrayList<String>();
       ArrayList<Float> interest_weightsWb = new ArrayList<Float>();
       
        ArrayList<String> interest_names = new ArrayList<String>();
       ArrayList<Float> interest_weights = new ArrayList<Float>();
       
       ArrayList<Date> timesDt = new ArrayList<Date>();
       ArrayList<Timestamp> timesTS = new ArrayList<Timestamp>();
        
        String query;
        if(num_of_interest==0)
        {    
           query = "SELECT LI.learner_interest_id, I.interest_id, I.interest_name, LI.weight, A.action_name, S.source_name, S.source_url, F.framework_name, F.framework_link, LI.timestamp FROM learners_interests AS LI, interests AS I, actions AS A, sources AS S, frameworks AS F WHERE LI.interest_id = I.interest_id AND LI.action_id=A.action_id AND LI.source_id=S.source_id AND LI.framework_id=F.framework_id AND learner_id =?";
        }else{     
            query = "SELECT LI.learner_interest_id, I.interest_id, I.interest_name, LI.weight, A.action_name, S.source_name, S.source_url, F.framework_name, F.framework_link, LI.timestamp FROM learners_interests AS LI, interests AS I, actions AS A, sources AS S, frameworks AS F WHERE LI.interest_id = I.interest_id AND LI.action_id=A.action_id AND LI.source_id=S.source_id AND LI.framework_id=F.framework_id AND learner_id =? ORDER BY LI.weight DESC, LI.interest_id DESC LIMIT "+num_of_interest;
        }  
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1,learnerId);
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs.next()) {
                MyInterest m= new MyInterest();
                
                m.setLearner_interest_id(rs.getInt("learner_interest_id"));
                m.setInterest_id(rs.getInt("interest_id")); 
                m.setInterest_name(rs.getString("interest_name"));
                m.setWeight(rs.getFloat("weight"));
                m.setAction_name(rs.getString("action_name"));
                m.setSource_name(rs.getString("source_name"));
                m.setSource_url(rs.getString("source_url"));
                m.setFramework_name(rs.getString("framework_name"));
                m.setFramework_link(rs.getString("framework_link"));
                
                if(rs.getString("framework_name").equals("PALM"))
                {    
                    interest_namesPALM.add(rs.getString("interest_name"));
                    interest_weightsPALM.add(rs.getFloat("weight"));
                }else if(rs.getString("framework_name").equals("Webtrace"))
                {
                    interest_namesWb.add(rs.getString("interest_name"));
                    interest_weightsWb.add(rs.getFloat("weight"));
                }
                
                interest_names.add(rs.getString("interest_name"));
                interest_weights.add(rs.getFloat("weight"));
                
                timesDt.add(rs.getDate("timestamp"));
                timesTS.add(rs.getTimestamp("timestamp"));
    	        myI.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        //reqCtx.addCallbackParam("interestinfos", new Gson().toJson(myI));//additional serialized data to be sent
        reqCtx.addCallbackParam("interest_namesPALM", new Gson().toJson(interest_namesPALM));//additional serialized data to be sent
        reqCtx.addCallbackParam("interest_weightsPALM", new Gson().toJson(interest_weightsPALM));//additional serialized data to be sent
        
        reqCtx.addCallbackParam("interest_namesWb", new Gson().toJson(interest_namesWb));
        reqCtx.addCallbackParam("interest_weightsWb", new Gson().toJson(interest_weightsWb));
        
        reqCtx.addCallbackParam("interest_names", new Gson().toJson(interest_names));
        reqCtx.addCallbackParam("interest_weights", new Gson().toJson(interest_weights));
        
        reqCtx.addCallbackParam("timesDt", new Gson().toJson(timesDt));//additional serialized data to be sent
        reqCtx.addCallbackParam("timesTS", new Gson().toJson(timesTS));//additional serialized data to be sent
        
    }
    
     public void getHyperChartData(int learnerId, String lname, int num_of_interest) { 
             
        String query_domains, query_types, query_interests;
        if(num_of_interest==0)
        {    
            //System.out.println(num_of_interest+"yes");
           query_domains = "SELECT DISTINCT D.domain_id, D.domain_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE LI.interest_id = I.interest_id AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id AND learner_id =? ORDER BY D.domain_id ASC";
       
            query_types = "SELECT DISTINCT T.type_id, T.type_name, T.domain_id FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE LI.interest_id = I.interest_id AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id AND learner_id =?";

            query_interests = "SELECT T.type_id, T.type_name, I.interest_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T WHERE LI.interest_id = I.interest_id AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND learner_id =?";
        }else{  
          //  System.out.println(num_of_interest+"else");
          
            query_domains = "SELECT DISTINCT D.domain_id, D.domain_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE  I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC, interest_id DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id";
          
            query_types = "SELECT DISTINCT T.type_id, T.type_name, T.domain_id FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC, interest_id DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id";
           
            query_interests = "SELECT distinct T.type_id, T.type_name, I.interest_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T WHERE I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC, interest_id DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id";
        }   
        
         
      try {
            //For Domains
            stmt = con.prepareStatement(query_domains);
            stmt.setInt(1, learnerId);
            stmt.execute();
            rs = stmt.getResultSet();

            JsonObject jsonObject = new JsonObject();
            JsonArray jarray=new JsonArray();
            JsonObject colorData = new JsonObject();
            colorData.addProperty("$color", "#77a1e5");
            
            jsonObject.addProperty("id", "01wefw");
            jsonObject.addProperty("name", lname);
            jsonObject.add("data", colorData);
            //For Types
            stmt_types=con.prepareStatement(query_types);
            stmt_types.setInt(1, learnerId);
            stmt_types.execute();
            rs_types = stmt_types.getResultSet();
            
            //For Interests
            stmt_interests = con.prepareStatement(query_interests);
            stmt_interests.setInt(1, learnerId);
            stmt_interests.execute();
            rs_interests = stmt_interests.getResultSet();
            
            while (rs.next()) {
                int unique_dId=rs.getInt("domain_id");
                String unique_dName= rs.getString("domain_name");
                
                
                JsonObject jsonDomain = new JsonObject();
                JsonArray jarrayDomain=new JsonArray();
                JsonObject colorDataDomain = new JsonObject();
                colorDataDomain.addProperty("$color", "#1aadce");
                //System.out.println("Domain:: "+unique_dId+unique_dName);
                jsonDomain.addProperty("id", "domain"+unique_dId);
                jsonDomain.addProperty("name", unique_dName);
                jsonDomain.add("data", colorDataDomain);
                 
                                
                  rs_types.beforeFirst();
                  while (rs_types.next()) {
                    int tId=rs_types.getInt("type_id");
                    String tName=rs_types.getString("type_name");
                    int type_dId=rs_types.getInt("domain_id");
                    //System.out.println(unique_dName+" ::Type now::"+tId+tName+type_dId);
                     if(type_dId==unique_dId){
             
                         JsonObject jsonType = new JsonObject();   
                         JsonArray jarrayType=new JsonArray();
                         JsonObject colorDataType = new JsonObject();
                         colorDataType.addProperty("$color", "#0d233a");
                         jsonType.addProperty("id", "type"+tId+type_dId);
                         jsonType.addProperty("name", tName);
                         jsonType.add("data", colorDataType);     
                                
                                rs_interests.beforeFirst();
                                while (rs_interests.next()) {

                                    int int_typeId=rs_interests.getInt("type_id");
                                    String int_typeName=rs_interests.getString("type_name");
                                    String intName=rs_interests.getString("interest_name");

                                   // System.out.println(" ::Type Interests daki::"+int_typeId+int_typeName+intName);
                                
                                    if(tId==int_typeId)
                                     {
                                       //System.out.println("In interest with Domain:: "+unique_dName+" Type:: "+tName+" interest:: "+intName);
                                       JsonObject jsonInterest = new JsonObject();   
                                       JsonArray jarrayInterest=new JsonArray();
                                       JsonObject colorDataInterest = new JsonObject();
                                       colorDataInterest.addProperty("$color", "#492970");
                                       jsonInterest.addProperty("id", "interest"+int_typeId+intName);
                                       jsonInterest.addProperty("name", intName);
                                       jsonInterest.add("data", colorDataInterest);
                                       jsonInterest.add("children", jarrayInterest);
                                      jarrayType.add(jsonInterest);
                                     } 
                                }
                         jsonType.add("children", jarrayType);
                         jarrayDomain.add(jsonType);
                     }   
                   }
                jsonDomain.add("children", jarrayDomain);
                jarray.add(jsonDomain);
                
            }
   
        jsonObject.add("children",jarray);
        
        //System.out.println("Computed Json object for the chart:: "+jsonObject);
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        //reqCtx.addCallbackParam("temp1", new Gson().toJson(tempo));//additional serialized data to be sent
        reqCtx.addCallbackParam("testdata", new Gson().toJson(jsonObject));//additional serialized data to be sent
       
    } catch (SQLException e) {
        e.printStackTrace();
    }
 }
     
     public void getHierarchyTop5(int learnerId, String lname, int num_of_interest) { 
             
        String query_domains, query_types, query_interests;
        if(num_of_interest==0)
        {    
            //System.out.println(num_of_interest+"yes");
           query_domains = "SELECT DISTINCT D.domain_id, D.domain_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE LI.interest_id = I.interest_id AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id AND learner_id =? ORDER BY D.domain_id ASC";
       
            //query_types = "SELECT DISTINCT T.type_id, T.type_name, T.domain_id FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE LI.interest_id = I.interest_id AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id AND learner_id =?";
            query_types = "SELECT distinct T.type_id, T.type_name, I.interest_id FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T WHERE I.interest_id=LI.interest_id  AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND learner_id =?";
            //query_interests = "SELECT T.type_id, T.type_name, I.interest_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T WHERE LI.interest_id = I.interest_id AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND learner_id =?";
            query_interests = "SELECT distinct I.interest_id, I.interest_name FROM learners_interests AS LI, interests AS I WHERE LI.interest_id = I.interest_id AND learner_id =?";
        }else{  
          //  System.out.println(num_of_interest+"else");
          
            query_domains = "SELECT DISTINCT D.domain_id, D.domain_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE  I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id";
          
            //query_types = "SELECT DISTINCT T.type_id, T.type_name, T.domain_id FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T, domains AS D WHERE I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id AND T.domain_id = D.domain_id";
            query_types="SELECT distinct T.type_id, T.type_name, I.interest_id FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T WHERE I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC, interest_id DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id";
            
            
           // query_interests = "SELECT distinct T.type_id, T.type_name, I.interest_name FROM learners_interests AS LI, interests AS I, types_interests AS TI, TYPES AS T WHERE I.interest_id IN ( SELECT * FROM (SELECT interest_id FROM learners_interests where learner_id=? ORDER BY weight DESC limit 5) AS TOP5 ) AND TI.interest_id = I.interest_id AND TI.type_id = T.type_id";
            query_interests = "SELECT distinct I.interest_id, I.interest_name FROM learners_interests AS LI, interests AS I WHERE LI.interest_id = I.interest_id AND learner_id =? ORDER BY weight DESC, I.interest_id DESC limit 5";
        }   
        
         
      try {
            //For Domains
            stmt = con.prepareStatement(query_domains);
            stmt.setInt(1, learnerId);
            stmt.execute();
            rs = stmt.getResultSet();

            JsonObject jsonObject = new JsonObject();
            JsonArray jarray=new JsonArray();
            JsonObject colorData = new JsonObject();
            colorData.addProperty("$color", "#77a1e5");
            
            jsonObject.addProperty("id", "01wefw");
             if(num_of_interest==0)
             {   jsonObject.addProperty("name", "All interests"); }else { jsonObject.addProperty("name", "Top 5 interests");}
            
            jsonObject.add("data", colorData);
            //For Types
            stmt_types=con.prepareStatement(query_types);
            stmt_types.setInt(1, learnerId);
            stmt_types.execute();
            rs_types = stmt_types.getResultSet();
            
            //For Interests
            stmt_interests = con.prepareStatement(query_interests);
            stmt_interests.setInt(1, learnerId);
            stmt_interests.execute();
            rs_interests = stmt_interests.getResultSet();
            
            while (rs_interests.next()) {

                int intId=rs_interests.getInt("interest_id");
                String intName=rs_interests.getString("interest_name");

                //System.out.println(" ::Type Interests daki::"+intId+intName);
                   JsonObject jsonInterest = new JsonObject();   
                   JsonArray jarrayInterest=new JsonArray();
                  
                   jsonInterest.addProperty("id", "interest"+intId);
                   jsonInterest.addProperty("name", intName);
                  
                   rs_types.beforeFirst();
                    while (rs_types.next()) {
                       int t_Id=rs_types.getInt("type_id");
                        String tName=rs_types.getString("type_name");
                         int TI_Id=rs_types.getInt("interest_id");
                        //System.out.println(unique_dName+" ::Type now::"+tId+tName+type_dId);
                         if(TI_Id==intId){

                             JsonObject jsonType = new JsonObject();   
                             JsonArray jarrayType=new JsonArray();
                             
                             jsonType.addProperty("id", "type"+t_Id+TI_Id);
                             jsonType.addProperty("name", tName);
                             jsonType.add("children", jarrayType);
                            jarrayInterest.add(jsonType); 
                         }
                    }
                  
                   jsonInterest.add("children", jarrayInterest);
                 jarray.add(jsonInterest);
                                   
            }
           
        jsonObject.add("children",jarray);
        
        //System.out.println("Computed Viru Json object for the chart:: "+jsonObject);
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        //reqCtx.addCallbackParam("temp1", new Gson().toJson(tempo));//additional serialized data to be sent
        reqCtx.addCallbackParam("testdata", new Gson().toJson(jsonObject));//additional serialized data to be sent
       
    } catch (SQLException e) {
        e.printStackTrace();
    }
 }
     
    public ArrayList<MyInterest> getMyI() {
        return myI;
    }

    public void setMyI(ArrayList<MyInterest> myI) {
        this.myI = myI;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<String> getDomains() {
        return domains;
    }

    public void setDomains(ArrayList<String> domains) {
        this.domains = domains;
    }

    public MyInterest getCurrentInterest() {
        return currentInterest;
    }

    public void setCurrentInterest(MyInterest currentInterest) {
        this.currentInterest = currentInterest;
    }
    
    
    

}

