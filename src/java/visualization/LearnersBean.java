package visualization;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

//JSON
/**
 *
 * @author Godhani
 */
@ManagedBean(name = "learner")
@SessionScoped
public class LearnersBean implements Serializable {

    Integer learnerId = null;
    String  learner_name,learner_uid,learner_pwd, new_learner_pwd, learner_email,learner_university,learner_designation, nameInPalm, nameInWebtrace;
    DbConnection db = new DbConnection();
    ResultSet rs;
    PreparedStatement stmt;
    Connection con = db.getConnection();
  
    
       /**
     * Creates a new instance of User
     */
    public LearnersBean() {
        
    }

    public String welcomeMethod(){
        
        //here you can check uid & pwd in DB
     learner_uid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginform:lid");
     learner_pwd = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginform:pwd");
      
     if(checkLearnerUidPwd())
     { 
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successfully, logged in as: "+ learner_name,"Successfully, logged in as: "+ learner_name));    
         return "learner";
     }else
     {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Id and password does not match!", "Login Id and password does not match!"));
        return "loginVisuLeMo";
     }
    }
    
    public boolean checkLearnerUidPwd(){
       
        String query = "Select * from learners WHERE learner_uid = ? and learner_password = ?";
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, learner_uid);
            stmt.setString(2, learner_pwd);
            stmt.execute();
            rs = stmt.getResultSet();
            if(rs.next()) {
                   learnerId= rs.getInt("learner_id");
                   learner_name=rs.getString("learner_name");
                   learner_email=rs.getString("learner_email");
                   learner_university=rs.getString("learner_university");
                   learner_designation=rs.getString("learner_designation");
                   nameInPalm=rs.getString("nameInPalm");
                  
                   nameInWebtrace=rs.getString("nameInWebtrace");
                   System.out.println("Successfully, logged in as: "+ learner_name ); 
                   return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return false;
    }
    
    public String logout(){

    //learner_uid=null;
    //learner_pwd=null;

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    session.invalidate();

    //here you wanna forward to your login page
      return "loginVisuLeMo";
    }
    
    public String newLearner(){
        
     learner_name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newlearner:lname");
     learner_uid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newlearner:luid");
     learner_pwd = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newlearner:lpwd");
     learner_email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newlearner:lemail");
     learner_university = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newlearner:luni");
     learner_designation = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newlearner:ldesign");
     
   
     String query = "INSERT INTO learners (`learner_name`, `learner_uid`, `learner_password`, `learner_email`, `learner_university`, `learner_designation`, `nameInPalm`, `nameInWebtrace`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
     try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, learner_name);
            stmt.setString(2, learner_uid);
            stmt.setString(3, learner_pwd);
            stmt.setString(4, learner_email);
            stmt.setString(5, learner_university);
            stmt.setString(6, learner_designation);
            stmt.setString(7, learner_name);
            stmt.setString(8, learner_name);            
            stmt.execute();    
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"New user has been created, please Login!","New user has been created, please Login!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in creating new User!","Error in creating new User!"));
            e.printStackTrace();
        }     
     
      return "loginVisuLeMo";
    }
    
     public void forgotPassword(){
       
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Your password has been sent to your email address!"));  
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sorry, an Email setting has not been configured on the server!", "Sorry, an Email setting has not been configured on the server!"));  
        //return "loginVisuLeMo";
      
    }
    
    public String updateLearnerDetails(){
        
     learner_university = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updatelearner:university");
     learner_designation = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updatelearner:designation");
     new_learner_pwd = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updatelearner:newpwd");
     learner_email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updatelearner:email");
     nameInPalm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updatelearner:palm_name");
     
     nameInWebtrace = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("updatelearner:webtrace_name");
    
     //System.out.println("Values "+ learner_university + learner_designation + new_learner_pwd + learner_email + nameInPalm + nameInFb + nameInWebtrace); 
     String query ="UPDATE learners SET learner_university=?, learner_designation=?, learner_password=?, learner_email=?, nameInPalm=?, nameInWebtrace=? WHERE learner_id=?";
     try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, learner_university);
            stmt.setString(2, learner_designation);
            if(new_learner_pwd != null && !new_learner_pwd.isEmpty())
            { stmt.setString(3, new_learner_pwd); learner_pwd=new_learner_pwd;}else{ stmt.setString(3, learner_pwd);}
            stmt.setString(4, learner_email);
            stmt.setString(5, nameInPalm);
            stmt.setString(6, nameInWebtrace);
            stmt.setInt(7, learnerId);
            stmt.execute();          
        } catch (SQLException e) {
            e.printStackTrace();
        }   
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Your details have been updated!", "Your details have been updated!"));  
      return "settings";
    }  
     
     public Integer getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Integer learnerId) {
        this.learnerId = learnerId;
    }
     
    public String getLearner_uid() {
        return learner_uid;
    }

    public void setLearner_uid(String learner_uid) {
        this.learner_uid = learner_uid;
    }

    public String getLearner_pwd() {
        return learner_pwd;
    }

    public void setLearner_pwd(String learner_pwd) {
        this.learner_pwd = learner_pwd;
    }

    public String getLearner_name() {
        return learner_name;
    }

    public void setLearner_name(String learner_name) {
        this.learner_name = learner_name;
    }

    public String getLearner_email() {
        return learner_email;
    }

    public void setLearner_email(String learner_email) {
        this.learner_email = learner_email;
    }

    public String getLearner_university() {
        return learner_university;
    }

    public void setLearner_university(String learner_university) {
        this.learner_university = learner_university;
    }

    public String getLearner_designation() {
        return learner_designation;
    }

    public void setLearner_designation(String learner_designation) {
        this.learner_designation = learner_designation;
    }    

    public String getNameInPalm() {
        return nameInPalm;
    }

    public void setNameInPalm(String nameInPalm) {
        this.nameInPalm = nameInPalm;
    }

    
    public String getNameInWebtrace() {
        return nameInWebtrace;
    }

    public void setNameInWebtrace(String nameInWebtrace) {
        this.nameInWebtrace = nameInWebtrace;
    }
    
    
}
