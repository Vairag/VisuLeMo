/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godhani
 */
@ManagedBean(name = "freebasejsonbean")
@SessionScoped
public class FreebaseJsonBean {
    
       String json;    
       Gson gson =new GsonBuilder().setPrettyPrinting().create();
       JsonParser parser = new JsonParser();
       JsonObject jobj;
       String typename,domainname;
       DbConnection db = new DbConnection();
       ResultSet rs;
       PreparedStatement stmt;
       Connection con = db.getConnection();
       JsonArray Jarray, Jtypes;
       Map <String, String> Itypes = new HashMap<String, String>();
       //Map <String, String> Itypes_temp = new HashMap<String, String>();
       MyInterest currentInterest=new MyInterest();
       
   
     public Map<String, String> fetchTypeDomainOfInterest(String i_name){
       Itypes.clear();
             
       i_name = i_name.replaceAll(" ", "%20");
          try{ 
           
            String freebase_URL="https://www.googleapis.com/freebase/v1/search?query="+i_name+"&output=%28type%29&limit=1";
            //String freebase_URL="https://www.googleapis.com/freebase/v1/search?query="+i_name+"&limit=1";
                       
            json = readUrl(freebase_URL);
            jobj = parser.parse(json).getAsJsonObject();
            for (Map.Entry<String,JsonElement> entry : jobj.entrySet()) {
            if(entry.getKey().equals("result"))
                {
                    Jarray = entry.getValue().getAsJsonArray();
                    for(JsonElement obj : Jarray )
                    {
                        Jtypes=obj.getAsJsonObject().get("output").getAsJsonObject().get("type").getAsJsonObject().getAsJsonArray("/type/object/type");
                        //System.out.println(Jtypes);
                        for(JsonElement Etype : Jtypes )
                        {
                            typename= Etype.getAsJsonObject().get("name").toString().replace("\"", "");
                            domainname= Etype.getAsJsonObject().get("id").toString().replace("\"", "");;
                            
                            String domain_first=domainname.substring(1,domainname.lastIndexOf("/"));                            
                            Itypes.put(typename, domain_first);
                        }    
                    }
                }
            }
            
            
            
            }catch(Exception e)
            {
                    e.printStackTrace();
            }
          return Itypes;   
   }
   
  public void saveTypeDomainOfInterest(MyInterest mi, Map <String, String> Itypes_temp){ 
  
         int dom_id, typ_id;
         int int_id=mi.interest_id;
         
           for (Map.Entry<String, String> entry : Itypes_temp.entrySet()) {
                                 
                dom_id=insertDomain(entry.getValue());
                typ_id=insertType(entry.getKey(),dom_id);
                insertTypeOfInterest(typ_id, int_id);
            }
  
  }
  
  public int insertDomain(String tmp_domain){
        int tmp_domain_id = 0;
        String query = "Select * from domains WHERE domain_name = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tmp_domain);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_domain_id= rs.getInt("domain_id");
                   //System.out.println(tmp_domain+ " domain is already in database with id: "+ tmp_domain_id ); 
            }
            else{
                //if Domain is not in the table then insert it!
                String domain_query = "INSERT INTO domains (`domain_name`) VALUES ( ? )";
                stmt = con.prepareStatement(domain_query, new String[] { "domain_id" });
                stmt.setString(1, tmp_domain);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_domain_id=generatedKeys.getInt(1);
                     //System.out.println("Newly inserted domain id:: "+tmp_domain_id );
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return tmp_domain_id;
     }
  
  
  public int insertType(String tmp_type, int dom_id){
        int tmp_type_id = 0;
        String query = "Select * from types WHERE type_name=? AND domain_id=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tmp_type);
            stmt.setInt(2, dom_id);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_type_id= rs.getInt("type_id");
                   //System.out.println(tmp_type+ " type is already in database with id: "+ tmp_type_id ); 
            }
            else{
                //if Type is not in the table then insert it!
                String type_query = "INSERT INTO types (`type_name`,`domain_id`) VALUES ( ?, ?)";
                stmt = con.prepareStatement(type_query, new String[] { "type_id" });
                stmt.setString(1, tmp_type);
                stmt.setInt(2, dom_id);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_type_id=generatedKeys.getInt(1);
                    // System.out.println("Newly inserted type id:: "+tmp_type_id );
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return tmp_type_id;
     }
     
    public void insertTypeOfInterest(int typ_id, int int_id){
        int tmp_type_id = 0;
        String query = "Select * from types_interests WHERE type_id=? AND interest_id=?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, typ_id);
            stmt.setInt(2, int_id);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   tmp_type_id= rs.getInt("types_interests_id");
                   //System.out.println("Given type "+typ_id+ " & interest "+int_id+" are already in database with id: "+ tmp_type_id ); 
            }
            else{
                //if Type Interest is not in the table then insert it!
                String type_query = "INSERT INTO types_interests (`type_id`,`interest_id`) VALUES ( ?, ?)";
                stmt = con.prepareStatement(type_query, new String[] { "types_interests_id" });
                stmt.setInt(1, typ_id);
                stmt.setInt(2, int_id);
                stmt.executeUpdate();
                //Getting the primary key of last inserted row.   
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                 if (null != generatedKeys && generatedKeys.next()) {
                     tmp_type_id=generatedKeys.getInt(1);
                    // System.out.println("Newly inserted typeInterest id:: "+tmp_type_id );
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

           // System.out.println(buffer.toString());
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }  
    }

    public Map<String, String> getItypes() {
        return Itypes;
    }

    public void setItypes(Map<String, String> Itypes) {
        this.Itypes = Itypes;
    }

    public MyInterest getCurrentInterest() {
        return currentInterest;
    }

    public void setCurrentInterest(MyInterest currentInterest) {
        this.currentInterest = currentInterest;
    }
    
    
}
