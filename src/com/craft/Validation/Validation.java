package com.craft.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 *
 * @author dixitabajracharya
 */
public class Validation {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
    private final static Pattern CODE_PATTERN = Pattern.compile("[a-zA-Z0-9]{8}");

    /**
     * check if a string is null or empty
     *
     * @param value
     * @return true if valid, otherwise false
     */
    public static boolean isNull(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * validate product code
     *
     * @param prodCode the product code entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validateCode(String prodCode) {
        return CODE_PATTERN.matcher(prodCode).matches();

    }

    /**
     * validate product price
     *
     * @param prodprice the product price entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validatePrice(String prodprice) {

        try {
            int price = Integer.parseInt(prodprice);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * validate product name
     *
     * @param prodName the product name entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validateName(String prodName) {
        return NAME_PATTERN.matcher(prodName).matches();
    }

    /**
     * validate product stock
     *
     * @param prodStock the product stock entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validateStock(String prodStock) {
        try {
            int stock = Integer.parseInt(prodStock);
            return stock > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * validate product date
     *
     * @param date the product date entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validateDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false); // Ensures strict validation
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;

        }
    }

    /**
     * validate product selected product of combobox
     *
     * @param item the product item entered by the user
     * @return true if valid, otherwise false
     */
    //
    public static boolean validateSelected(String item) {
        return "Select an item".equals(item);
    }

    /**
     * validate product weight
     *
     * @param weight the product weight entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validateWeight(String weight) {
        try {
            int intWeight = Integer.parseInt(weight);
            return intWeight > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * validate product total sales
     *
     * @param totalSales the totalSales entered by the user
     * @return true if valid, otherwise false
     */
    public static boolean validateSales(String totalSales) {
        try {
            int sales = Integer.parseInt(totalSales);
            return sales > 0;
        } catch (NumberFormatException e) {
            return false;

        }
    }
}
