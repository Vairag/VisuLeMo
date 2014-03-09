/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

/**
 *
 * @author Godhani
 */
public class MyInterest {
    
    Integer learner_interest_id;
    Integer interest_id;
    String interest_name;
    Float weight;
    String action_name;
    String source_name; 
    String source_url;
    String framework_name;
    String framework_link;
    String item_link;

    public MyInterest() {
    }
    
    public Integer getLearner_interest_id() {
        return learner_interest_id;
    }

    public void setLearner_interest_id(Integer learner_interest_id) {
        this.learner_interest_id = learner_interest_id;
    }

    public Integer getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(Integer interest_id) {
        this.interest_id = interest_id;
    }

    public String getInterest_name() {
        return interest_name;
    }

    public void setInterest_name(String interest_name) {
        this.interest_name = interest_name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getFramework_name() {
        return framework_name;
    }

    public void setFramework_name(String framework_name) {
        this.framework_name = framework_name;
    }

    public String getFramework_link() {
        return framework_link;
    }

    public void setFramework_link(String framework_link) {
        this.framework_link = framework_link;
    }

    public String getItem_link() {
        return item_link;
    }

    public void setItem_link(String item_link) {
        this.item_link = item_link;
    }
    
}
