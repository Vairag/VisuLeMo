/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import com.google.gson.*;
import com.mysql.jdbc.Statement;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godhani
 */
@ManagedBean(name = "apijsonbean")
@SessionScoped
public class APIJsonBean {
    
       String json;    
       Gson gson =new GsonBuilder().setPrettyPrinting().create();
       JsonParser parser = new JsonParser();
       JsonArray Jarray, Jarray_related;
       ArrayList<Author> lcs = new ArrayList<Author>();
       ArrayList<Category> ccs = new ArrayList<Category>();
       ArrayList<Related> rcs = new ArrayList<Related>();
       InterestPage ipage;
       int learner_interest_id; 
       float weight;
       String interest, action, items, source, str_weight, timestamp;
       String  learner_name, learner_uid, learner_pwd, learner_email, learner_university, learner_designation;
       DbConnection db = new DbConnection();
       ResultSet rs;
       PreparedStatement stmt;
       Connection con = db.getConnection();
       Connection conWebtrace = db.getWebtraceConnection();
       List<String> selectedFrameworks;
       WebtraceInterest wbI_temp;
       ArrayList<WebtraceInterest> wbIs=new ArrayList<WebtraceInterest>();
         
    
    public APIJsonBean() {

    }
       
    public ArrayList<Author> getLcs() {
        return lcs;
    }

    public void setLcs(ArrayList<Author> lcs) {
        this.lcs = lcs;
    }

    public ArrayList<Category> getCcs() {
        return ccs;
    }

    public void setCcs(ArrayList<Category> ccs) {
        this.ccs = ccs;
    }

    public ArrayList<Related> getRcs() {
        return rcs;
    }

    public void setRcs(ArrayList<Related> rcs) {
        this.rcs = rcs;
    }

    public InterestPage getIpage() {
        return ipage;
    }

    public void setIpage(InterestPage ipage) {
        this.ipage = ipage;
    }

    public List<String> getSelectedFrameworks() {
        return selectedFrameworks;
    }

    public void setSelectedFrameworks(List<String> selectedFrameworks) {
        this.selectedFrameworks = selectedFrameworks;
    }

    public ArrayList<WebtraceInterest> getWbIs() {
        return wbIs;
    }

    public void setWbIs(ArrayList<WebtraceInterest> wbIs) {
        this.wbIs = wbIs;
    }
       
    
public String frameworkSelection(String nameInPalm, String nameInWebtrace){

    if((selectedFrameworks.size()==1) && selectedFrameworks.get(0).equals("PALM"))
    {
        //System.out.println("Selected learner model is PALM");
        wbIs.clear();
        try{ 
         String palm_interests_URL="http://subprogra.informatik.rwth-aachen.de/~ddugosija/llmian/Api/interests/";
         nameInPalm = nameInPalm.replaceAll(" ", "%20");
         json = readUrl(palm_interests_URL+nameInPalm);

         JsonObject jobj = parser.parse(json).getAsJsonObject();
         ipage=gson.fromJson( jobj , InterestPage.class);

         }catch(Exception e)
         {
                 e.printStackTrace();
         }
         return "editLearnerInterests";    

    }else if((selectedFrameworks.size()==1) && selectedFrameworks.get(0).equals("webtrace")){
          //System.out.println("Selected learner model is Webtrace");
            ipage= new InterestPage();
            wbIs.clear();
            
            //String webtrace_query = "SELECT CFL.id, CFL.created, CFL.name FROM cake_facebook_profiles_likes AS CFPL, cake_facebook_likes AS CFL WHERE CFPL.facebook_like_id = CFL.id AND CFPL.facebook_profile_id =13 ORDER BY CFL.id LIMIT 5";
            String webtrace_query = "SELECT CFL.id, CFL.modified, CFL.name FROM cake_facebook_profiles_likes AS CFPL, cake_facebook_likes AS CFL, cake_facebook_profiles AS CFP WHERE CFPL.facebook_like_id = CFL.id AND CFPL.facebook_profile_id = CFP.id AND CFP.name='"+nameInWebtrace+"' ORDER BY CFL.id LIMIT 5";
            
            long item_id;
            
            String wb_interest, item_temp, creationtime;
            
            try {
                stmt = conWebtrace.prepareStatement(webtrace_query);
                stmt.execute();
                rs = stmt.getResultSet();
                while (rs.next()) {
                   
                   wbI_temp=new WebtraceInterest();
                   item_id=rs.getLong("id");
                   item_temp="http://www.facebook.com/"+item_id;
                   creationtime=rs.getString("modified");
                   wb_interest=rs.getString("name");
                   
                   wbI_temp.setItem_id(item_id);
                   wbI_temp.setWb_interest(wb_interest);
                   wbI_temp.setWb_weight(1.0f);
                   wbI_temp.setWb_action("Facebook like");
                   wbI_temp.setWb_item(item_temp);
                   wbI_temp.setWb_source("Facebook");
                   wbI_temp.setWb_timestamp(creationtime);
                   
                   wbIs.add(wbI_temp);
                  // System.out.println("Webtrace data: "+ item_id +" : "+ creationtime +" : "+wb_interest+" : "+ item_temp); 
                }
            } catch (SQLException ex) {
                   Logger.getLogger(APIJsonBean.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return "editLearnerInterests";
            
         }else if((selectedFrameworks.size()==2) && selectedFrameworks.get(0).equals("PALM") && selectedFrameworks.get(1).equals("webtrace")){
            
             //System.out.println("PALM & webtrace");
             wbIs.clear();
             try{ 
                String palm_interests_URL="http://subprogra.informatik.rwth-aachen.de/~ddugosija/llmian/Api/interests/";
                nameInPalm = nameInPalm.replaceAll(" ", "%20");
                json = readUrl(palm_interests_URL+nameInPalm);

                JsonObject jobj = parser.parse(json).getAsJsonObject();
                ipage=gson.fromJson( jobj , InterestPage.class);
                // PALM END
                
                //Webtrace START
                //String webtrace_query = "SELECT CFL.id, CFL.created, CFL.name FROM cake_facebook_profiles_likes AS CFPL, cake_facebook_likes AS CFL WHERE CFPL.facebook_like_id = CFL.id AND CFPL.facebook_profile_id =13 ORDER BY CFL.id LIMIT 5";
                String webtrace_query = "SELECT CFL.id, CFL.modified, CFL.name FROM cake_facebook_profiles_likes AS CFPL, cake_facebook_likes AS CFL, cake_facebook_profiles AS CFP WHERE CFPL.facebook_like_id = CFL.id AND CFPL.facebook_profile_id = CFP.id AND CFP.name='"+nameInWebtrace+"' ORDER BY CFL.id LIMIT 5";
                long item_id;
                String wb_interest, item_temp, creationtime;
                    stmt = conWebtrace.prepareStatement(webtrace_query);
                    stmt.execute();
                    rs = stmt.getResultSet();
                    while (rs.next()) {

                       wbI_temp=new WebtraceInterest();
                       item_id=rs.getLong("id");
                       item_temp="http://www.facebook.com/"+item_id;
                       creationtime=rs.getString("modified");
                       wb_interest=rs.getString("name");

                       wbI_temp.setItem_id(item_id);
                       wbI_temp.setWb_interest(wb_interest);
                       wbI_temp.setWb_weight(1.0f);
                       wbI_temp.setWb_action("Facebook like");
                       wbI_temp.setWb_item(item_temp);
                       wbI_temp.setWb_source("Facebook");
                       wbI_temp.setWb_timestamp(creationtime);

                       wbIs.add(wbI_temp);
                       //System.out.println("Webtrace data: "+ item_id +" : "+ creationtime +" : "+wb_interest+" : "+ item_temp); 
                    }
                        
                 }catch(Exception e)
                 { e.printStackTrace();  }
            return "editLearnerInterests";
            
         }else{
           return null;
         }   
           
         
       
     } 

     private static String readUrl(String urlString) throws Exception {
        
    	BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            //System.out.println(buffer.toString());
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }  
    }

     public String saveInterestsToDatabase(int learner_id){         
        
         // Saving PALM category interests
        if(ipage.getCategories()!=null){                   
           savePalmCategoryInterestsToDB(learner_id);    
        }     
      
        // Saving PALM related interests  
        if(ipage.getRelated()!=null){ 
           savePalmRelatedInterestsToDB(learner_id); 
         } 
       
         // Saving Webtrace interests  
       if(wbIs!=null){ 
           saveWebtraceInterestsToDB(learner_id);
            
       }
              
      System.out.println("All interests and categories are successfully saved to Database!");
            
     return "visualizations";
       
     }
     
     public void savePalmCategoryInterestsToDB(int learner_id)
     {
               for(Category c: ipage.getCategories()) {

                  interest = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:interest_"+c.getInterest().getId());
                  learner_name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:learner_"+c.getInterest().getId());
                  str_weight = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:weight_"+c.getInterest().getId());
                  action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:action_"+c.getInterest().getId());
                  items = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:items_"+c.getInterest().getId());
                  source = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:source_"+c.getInterest().getId());
                  timestamp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:timestamp_"+c.getInterest().getId());

                // System.out.println("From Category: "+interest+ " -- "+learner_name+"--"+str_weight+"--"+action+"--"+items+"--"+source+"--"+timestamp);

                   int interest_id=insertInterest(interest);
                   weight= Float.parseFloat(str_weight);
                   int action_id=insertAction(action);
                   int source_id;
                   if("No source".equals(source)){  source_id=0;  }else{ source_id=insertSource(source); }
                   int framework_id=1; //for PALM
                    int tmp_li_id=0;
                   int item_id=insertItems(items, tmp_li_id);
                   FreebaseJsonBean fjb_palmC=new FreebaseJsonBean();

                  String exist_interest_query = "Select * from learners_interests WHERE learner_id= ? AND interest_id=? AND action_id=? AND source_id=? AND item_id=?";
                  try {
                      stmt = con.prepareStatement(exist_interest_query);
                      stmt.setInt(1, learner_id);
                      stmt.setInt(2, interest_id);
                      stmt.setInt(3, action_id);
                      stmt.setInt(4, source_id);
                      stmt.setInt(5, item_id);
                      boolean execute = stmt.execute();
                      rs = stmt.getResultSet();
                      if(rs.next()) {
                            // System.out.println(interest_id + "interest is already in database for the learner: "+ learner_id ); 
                      } 
                      else{
                       // if totally new interest, then insert it & get id of last inserted interest.

                       tmp_li_id=0;
                       String learners_interests_query ="INSERT INTO learners_interests (`learner_id`, `interest_id`, `weight`, `action_id`, `source_id`, `item_id`, `framework_id`, `timestamp`) VALUES ("+learner_id+", "+interest_id+", "+weight+", "+action_id+", "+source_id+", "+item_id+", "+framework_id+", '"+timestamp+"')";
                       Statement tmp_stmt;

                        tmp_stmt = (Statement) con.createStatement();
                        tmp_stmt.executeUpdate(learners_interests_query,new String[] { "learner_interest_id" });

                         ResultSet generatedKeys = tmp_stmt.getGeneratedKeys();
                          if (null != generatedKeys && generatedKeys.next()) {
                              tmp_li_id=generatedKeys.getInt(1);
                          } 
                      // if("No items".equals(source)){ System.out.println("Mine-"); }else{ insertItems(items, tmp_li_id); }
                      
                          Map<String, String> typeDomains = fjb_palmC.fetchTypeDomainOfInterest(interest);
                       
                            for (Map.Entry<String, String> entry : typeDomains.entrySet()) {
                          
                                 int dom_id=fjb_palmC.insertDomain(entry.getValue());
                                 int typ_id=fjb_palmC.insertType(entry.getKey(),dom_id);
                                 fjb_palmC.insertTypeOfInterest(typ_id, interest_id);
                             }

                  } 
                 } catch (SQLException ex) {
                        Logger.getLogger(APIJsonBean.class.getName()).log(Level.SEVERE, null, ex);
                 } 
             }
               
     }        
     
     public void savePalmRelatedInterestsToDB(int learner_id)
     {
         for(Related r: ipage.getRelated()) {    
            
            interest = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:interest_"+r.getInterest().getId());
            learner_name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:learner_"+r.getInterest().getId());
            str_weight = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:weight_"+r.getInterest().getId());
            action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:action_"+r.getInterest().getId());
            items = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:items_"+r.getInterest().getId());
            source = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:source_"+r.getInterest().getId());
            timestamp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:timestamp_"+r.getInterest().getId());
           // System.out.println("From Related: "+interest+ " -- "+learner_name+"--"+str_weight+"--"+action+"--"+items+"--"+source+"--"+timestamp);
             
             int interest_id=insertInterest(interest);
             weight= Float.parseFloat(str_weight);
             int action_id=insertAction(action);
             int source_id;
             if("No source".equals(source)){  source_id=0;  }else{ source_id=insertSource(source); }
             int framework_id=1; //for PALM
             int tmp_li_id=0;
            int item_id=insertItems(items, tmp_li_id);
            
             FreebaseJsonBean fjb_palmR=new FreebaseJsonBean();
              
            String exist_interest_query = "Select * from learners_interests WHERE learner_id= ? AND interest_id=? AND action_id=? AND source_id=? AND item_id=?";
            try {
                stmt = con.prepareStatement(exist_interest_query);
                stmt.setInt(1, learner_id);
                stmt.setInt(2, interest_id);
                stmt.setInt(3, action_id);
                stmt.setInt(4, source_id);
                stmt.setInt(5, item_id);
                boolean execute = stmt.execute();
                rs = stmt.getResultSet();
                if(rs.next()) {
                      // System.out.println(interest_id + "interest is already in database for the learner: "+ learner_id ); 
                } 
                else{
                   String learners_interests_query ="INSERT INTO learners_interests (`learner_id`, `interest_id`, `weight`, `action_id`, `source_id`, `item_id`, `framework_id`, `timestamp`) VALUES ("+learner_id+", "+interest_id+", "+weight+", "+action_id+", "+source_id+", "+item_id+", "+framework_id+", '"+timestamp+"')";
                   Statement tmp_stmt = (Statement) con.createStatement();
                   tmp_stmt.executeUpdate(learners_interests_query,new String[] { "learner_interest_id" });
                   
                   ResultSet generatedKeys = tmp_stmt.getGeneratedKeys();
                    if (null != generatedKeys && generatedKeys.next()) {
                        tmp_li_id=generatedKeys.getInt(1);
                       
                    } 
                  // insertItems(items, tmp_li_id);  
                    
                     Map<String, String> typeDomains = fjb_palmR.fetchTypeDomainOfInterest(interest);
                       // System.out.println("For type & domains of "+interest);
                       for (Map.Entry<String, String> entry : typeDomains.entrySet()) {
                         //   System.out.println("Type -"+ entry.getKey() +" Domains -"+entry.getValue());
                            int dom_id=fjb_palmR.insertDomain(entry.getValue());
                            int typ_id=fjb_palmR.insertType(entry.getKey(),dom_id);
                            fjb_palmR.insertTypeOfInterest(typ_id, interest_id);
                        }
                  
                 } 
                } catch (SQLException ex) {
                       Logger.getLogger(APIJsonBean.class.getName()).log(Level.SEVERE, null, ex);
                } 
     
        }
     }
     
     public void saveWebtraceInterestsToDB(int learner_id)
     {
          
          for(WebtraceInterest wi: wbIs)
            {
                interest = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:interest_"+wi.getItem_id());
                learner_name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:learner_"+wi.getItem_id());
                str_weight = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:weight_"+wi.getItem_id());
                action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:action_"+wi.getItem_id());
                items = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:items_"+wi.getItem_id());
                source = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:source_"+wi.getItem_id());
                timestamp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editInterestform:timestamp_"+wi.getItem_id());
               System.out.println("From Webtrace: "+interest+ " -- "+learner_name+"--"+str_weight+"--"+action+"--"+items+"--"+source+"--"+timestamp);
                
                int interest_id=insertInterest(interest);
                weight= Float.parseFloat(str_weight);
                int action_id=insertAction(action);
                int source_id;
                if("No source".equals(source)){  source_id=0;  }else{ source_id=insertSource(source); }
                int framework_id=2; //for webtrace
                int tmp_li_id=0;
                int item_id=insertItems(items, tmp_li_id);
                
                FreebaseJsonBean fjb_wb=new FreebaseJsonBean();

               String exist_interest_query = "Select * from learners_interests WHERE learner_id= ? AND interest_id=? AND action_id=? AND source_id=? AND item_id=?";
               try {
                   stmt = con.prepareStatement(exist_interest_query);
                   stmt.setInt(1, learner_id);
                   stmt.setInt(2, interest_id);
                   stmt.setInt(3, action_id);
                   stmt.setInt(4, source_id);
                   stmt.setInt(5, item_id);
                   boolean execute = stmt.execute();
                   rs = stmt.getResultSet();
                   if(rs.next()) {
                         // System.out.println(interest_id + "interest is already in database for the learner: "+ learner_id ); 
                   } 
                   else{
                      String learners_interests_query ="INSERT INTO learners_interests (`learner_id`, `interest_id`, `weight`, `action_id`, `source_id`, `item_id`, `framework_id`, `timestamp`) VALUES ("+learner_id+", "+interest_id+", "+weight+", "+action_id+", "+source_id+", "+item_id+", "+framework_id+", '"+timestamp+"')";
                      Statement tmp_stmt = (Statement) con.createStatement();
                      tmp_stmt.executeUpdate(learners_interests_query,new String[] { "learner_interest_id" });

                      ResultSet generatedKeys = tmp_stmt.getGeneratedKeys();
                       if (null != generatedKeys && generatedKeys.next()) {
                           tmp_li_id=generatedKeys.getInt(1);

                       } 
                       
                       // insertItems(items, tmp_li_id); 
                       
                       Map<String, String> typeDomains = fjb_wb.fetchTypeDomainOfInterest(interest);
                       // System.out.println("For type & domains of "+interest);
                       for (Map.Entry<String, String> entry : typeDomains.entrySet()) {
                         //   System.out.println("Type -"+ entry.getKey() +" Domains -"+entry.getValue());
                            int dom_id=fjb_wb.insertDomain(entry.getValue());
                            int typ_id=fjb_wb.insertType(entry.getKey(),dom_id);
                            fjb_wb.insertTypeOfInterest(typ_id, interest_id);
                        }
                       
                            

                    } 
                   } catch (SQLException ex) {
                          Logger.getLogger(APIJsonBean.class.getName()).log(Level.SEVERE, null, ex);
                   } 
                
                
            }
     }
     
      
      public int insertInterest(String tmp_interest){
        int tmp_interest_id = 0;
        String query = "Select * from interests WHERE interest_name = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tmp_interest);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_interest_id= rs.getInt("interest_id");
                  // System.out.println(tmp_interest+ " interest is already  in database with id: "+ tmp_interest_id ); 
            }
            else{
                //if interest is not in the table then insert it!
                String interests_query = "INSERT INTO interests (`interest_name`) VALUES ( ? )";
                stmt = con.prepareStatement(interests_query, new String[] { "interest_id" });
                stmt.setString(1, tmp_interest);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_interest_id=generatedKeys.getInt(1);
                     //System.out.println("Viru2--"+tmp_interest_id );
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return tmp_interest_id;
     }
      
      
      
      public int insertAction(String tmp_action){
        int tmp_action_id = 0;
        String query = "Select * from actions WHERE action_name = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tmp_action);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_action_id= rs.getInt("action_id");
                  // System.out.println(tmp_action+ " action is already  in database with id: "+ tmp_action_id ); 
            }
            else{
                //if action is not in the table then insert it!
                String actions_query = "INSERT INTO actions (`action_name`) VALUES ( ? )";
                stmt = con.prepareStatement(actions_query, new String[] { "action_id" });
                stmt.setString(1, tmp_action);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_action_id=generatedKeys.getInt(1);
                    // System.out.println("Viru2--"+tmp_action_id );
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return tmp_action_id;
     }
    
      public int insertSource(String tmp_source){
        int tmp_source_id=0;
        String query = "Select * from sources WHERE source_name = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tmp_source);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_source_id= rs.getInt("source_id");
                  // System.out.println(tmp_source+ " source is already  in database with id: "+ tmp_source_id ); 
            }
            else{
                //if action is not in the table then insert it!
                String source_query = "INSERT INTO sources (`source_name`) VALUES ( ? )";
                stmt = con.prepareStatement(source_query, new String[] { "source_id" });
                stmt.setString(1, tmp_source);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_source_id=generatedKeys.getInt(1);
                     
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return tmp_source_id;
     }
      
       public int insertItems(String tmp_items, int tmp_li_id){
        int tmp_items_id=0;
        String query = "Select * from items WHERE item_link=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tmp_items);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_items_id= rs.getInt("item_id");
                  // System.out.println(tmp_items+ " items is already  in database with id: "+ tmp_items_id ); 
            }
            else{
                //if Items is not in the table then insert it!
                String item_query = "INSERT INTO items (`item_link`) VALUES ( ? )";
                stmt = con.prepareStatement(item_query, new String[] { "item_id" });
                stmt.setString(1, tmp_items);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_items_id=generatedKeys.getInt(1);
                     
                 }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return tmp_items_id;
     }
      
}
