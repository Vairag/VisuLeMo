/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Godhani
 */
public class WebtraceInterest {
    
    //ArrayList<String> items=new ArrayList<String>();
    long item_id;
    String wb_interest,wb_item, wb_action, wb_source, wb_timestamp;
    Float wb_weight;
    

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }
    
    public String getWb_interest() {
        return wb_interest;
    }

    public void setWb_interest(String wb_interest) {
        this.wb_interest = wb_interest;
    }

    public String getWb_item() {
        return wb_item;
    }

    public void setWb_item(String wb_item) {
        this.wb_item = wb_item;
    }

    public String getWb_action() {
        return wb_action;
    }

    public void setWb_action(String wb_action) {
        this.wb_action = wb_action;
    }

    public String getWb_source() {
        return wb_source;
    }

    public void setWb_source(String wb_source) {
        this.wb_source = wb_source;
    }

    public Float getWb_weight() {
        return wb_weight;
    }

    public void setWb_weight(Float wb_weight) {
        this.wb_weight = wb_weight;
    }

    public String getWb_timestamp() {
        return wb_timestamp;
    }

    public void setWb_timestamp(String wb_timestamp) {
        this.wb_timestamp = wb_timestamp;
    }
    
}
