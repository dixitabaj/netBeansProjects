package com.craft.controlller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 *
 * @author dixitabajracharya
 */
public class Validation {
//    private final static Pattern price=Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
    private static final Pattern name=Pattern.compile("^[A-Za-z]+([A-Za-z]+)*$");
        private static final Pattern email=Pattern.compile("^( [A-Za-z0-9]+)*$");
    private final static Pattern code = Pattern.compile("[a-zA-Z0-9]{10}");
   private final static Pattern stock=Pattern.compile("[0-9]+$");

    // check if a string is null or empty
    public static boolean isNull(String value){
    return value==null || value.trim().isEmpty() ;
    }
    
    //validate product code
    public static boolean validateCode(String prodCode) {
            return code.matcher(prodCode).matches();
       
    }
    
    //validate product price
    public static boolean validatePrice(float prodprice){
        
        return prodprice>0;
    }
    //validate product name
    public static boolean validateName(String prodName){
        return name.matcher(prodName).matches();
    }
    //validate product stock
    public static boolean validateStock(int prodStock){
        
        
        return prodStock>0;
        
    }
    
    //validate product date
        public static boolean validateDate(String date){
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    dateFormat.setLenient(false); // Ensures strict validation
    try {
        dateFormat.parse(date);
        return true;
    } catch (ParseException e) {
        return false;
    
    }
        }
        
        //validate product selected product of combobox
        public static boolean validateSelected(String item)
        {
            return "Select an item".equals(item);
        }      
        
        //validate product weight
        public static boolean validateWeight(float weight){
        return weight>0;
}
        
        //validate product total sales
    public static boolean validateSales(int totalSales){
      
        return totalSales>0;
    }
    
    
    








}


   
