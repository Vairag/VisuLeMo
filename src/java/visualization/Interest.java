/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import java.text.DecimalFormat;

/**
 *
 * @author Godhani
 */
public class Interest {
    Integer id;
    Integer authorid;
    Integer analyserid;
    Integer year;
    String term;
    String count;
    String topiascore1;
    String topiascore2;
    double yahoocategoryscore;
    String yahoowiki;
    String yahootypes;
    String yahoorelated;
    String created;
    String modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getAnalyserid() {
        return analyserid;
    }

    public void setAnalyserid(Integer analyserid) {
        this.analyserid = analyserid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTopiascore1() {
        return topiascore1;
    }

    public void setTopiascore1(String topiascore1) {
        this.topiascore1 = topiascore1;
    }

    public String getTopiascore2() {
        return topiascore2;
    }

    public void setTopiascore2(String topiascore2) {
        this.topiascore2 = topiascore2;
    }

    public double getYahoocategoryscore() {
        
        return yahoocategoryscore;
    }

    public void setYahoocategoryscore(double yahoocategoryscore) {
         
        this.yahoocategoryscore = yahoocategoryscore;
    }

    public String getYahoowiki() {
        return yahoowiki;
    }

    public void setYahoowiki(String yahoowiki) {
        this.yahoowiki = yahoowiki;
    }

    public String getYahootypes() {
        return yahootypes;
    }

    public void setYahootypes(String yahootypes) {
        this.yahootypes = yahootypes;
    }

    public String getYahoorelated() {
        return yahoorelated;
    }

    public void setYahoorelated(String yahoorelated) {
        this.yahoorelated = yahoorelated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
