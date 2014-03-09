/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization;

import java.util.List;

/**
 *
 * @author Godhani
 */
public class InterestPage {
    
    List<Category> Categories;
    List<Related>  Related;

    public List<Category> getCategories() {
        return Categories;
    }

    public void setCategories(List<Category> categories) {
        this.Categories = categories;
    }

    public List<Related> getRelated() {
        return Related;
    }

    public void setRelated(List<Related> Related) {
        this.Related = Related;
    }
    
}
