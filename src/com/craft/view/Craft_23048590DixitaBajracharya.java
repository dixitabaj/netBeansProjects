/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.craft.view;
//import necessary frameworks

import com.craft.controlller.Validation;
import com.craft.controlller.algorithm.InsertionSort;
import com.craft.controlller.algorithm.MergeSort;
import com.craft.controlller.algorithm.SelectionSort;
import com.craft.model.CraftModel;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dixitabajracharya
 */
public class Craft_23048590DixitaBajracharya extends javax.swing.JFrame {

    private List<CraftModel> craftList; //declare a list to store products
    private CardLayout layout;//declare cardlayout

    DefaultTableModel table;//declare table model for displaying products in the table
    MergeSort merge = new MergeSort();
    InsertionSort insertionSort = new InsertionSort();
    SelectionSort selectionSort = new SelectionSort();

    ;


    /**
     * Constructor: creates new form of Craft_23048590DixitaBajracharya
     */
    public Craft_23048590DixitaBajracharya() {
        initComponents();// Initialize GUI components
        layoutPane();//set layout for different screen

        startProgress();//start the loading page
        registerProducts();//call registerProducts() craftproducts into the system
        productOverview();//display product overview

    }

    /**
     * Creates card layout that sets up multiple screens and manages them
     */
    private void layoutPane() {
        layout = new CardLayout();//initialize card layout
        getContentPane().setLayout(layout);//set the layout for content pane
        getContentPane().add(panelLoginScreen, "loginScreen");//add login page
        getContentPane().add(panelMainScreen, "mainScreen");//add main screen
        getContentPane().add(loadingScreen, "loadingScreen");//add loading page
    }

    /**
     * Switches the screen to the specified one.
     *
     * @param screenName Name of the screen to display.
     */
    private void loadScreen(String screenName) {
        layout.show(getContentPane(), screenName);//shows specific screen
    }

    /**
     * register handicraft products into the list
     */
    private void registerProducts() {
        craftList = new LinkedList<>();//initalize linked list
        addProduct(new CraftModel("128U2U2A", "Mika", 2999.9f, 60, "pottery", 233, "active", "2024/09/22", 8));
        addProduct(new CraftModel("213ACSWC", "Decorative Plate", 3999.0f, 75, "ceramics", 322, "inactive", "2024/12/24", 12));
        addProduct(new CraftModel("92VCDW24", "Elegant Bowl", 5599.0f, 30, "ceramics", 656, "inactive", "2023/01/24", 7));
        addProduct(new CraftModel("22RKLWB2", "Wood Sculpture", 6999.8f, 50, "woodcarft", 452, "discontinued", "2023/12/14", 3));
        addProduct(new CraftModel("12SFWE23", "Art Deco Ceramic Vase", 7999.9f, 20, "ceramics", 56, "inactive", "2024/02/28", 6));
        addProduct(new CraftModel("GFAJ833A", "Modern pot Pitcher", 9999.7f, 15, "pottery", 34, "discontinued", "2020/12/18", 4));
    }

    /**
     * Display progress bar on the screen
     */
    private void startProgress() {
        loadScreen("loadingScreen");
        javax.swing.SwingWorker<Void, Integer> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int counter = 0;
                while (counter <= 100) {
                    publish(counter);  // Publish the current progress
                    try {
                        Thread.sleep(30);  // Simulate work
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter += 1;
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Get the last published progress
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);  // Update progress bar
                SwingUtilities.invokeLater(() -> {
                    if (progress < 25) {
                        progressBarNaming.setText("Retrieving file...");
                    } else if (progress < 50) {
                        progressBarNaming.setText("Processing data...");
                    } else if (progress < 75) {
                        progressBarNaming.setText("Saving file...");
                    } else if (progress < 100) {
                        progressBarNaming.setText("Finalizing...");
                    } else {
                        progressBarNaming.setText("Done");
                    }
                });
            }

            @Override
            protected void done() {
                loadScreen("loginScreen");  // Switch to login screen
                System.out.println("Progress done!");
            }
        };
        worker.execute();
    }

    /**
     * clear data from the text fields and reset the validation
    *
     */
    private void clearData() {
        productCodeTextField.setText("");
        productNameTextField.setText("");
        productPriceTextField.setText("");
        productStockTextField.setText("");
        productDateTextField.setText("");
        productSalesTextField.setText("");
        productWeightTextField.setText("");
        resetValidation();// Clear validation errors
    }

    /**
     * add products into the list and updates table
     *
     * @param products CraftModel Objects to be added
     */
    private void addProduct(CraftModel products) {
        craftList.add(products);//add product to the list
        table = (DefaultTableModel) craftTable.getModel();
        // Add product details as a new row in the table
        table.addRow(new Object[]{products.getProductId(), products.getName(), products.getPrice(), products.getStock(), products.getCategory(), products.getTotalSales(), products.getStatus(), products.getDateCreated(), products.getWeight()});
        productOverview();// Update the product overview

    }

    /*
     *check validity of the credentials entered
     */
    private boolean checkCredentials() {
        String password = new String(jPasswordField3.getPassword());
        String username = usernameTextField.getText().trim();
        usernameTextField.setText("admin");
        jPasswordField3.setText("admin");
        if (Validation.isNull(username) || Validation.isNull(password)) {
            errorMismatch.setText("Please enter your username and password");
            return false;
        } else if (!password.equals("admin") || !username.equals("admin")) {
            errorMismatch.setText("Invalid Username and Password");
            return false;
        } else {
            errorMismatch.setText("");
            return true;

        }
    }

    /*
    *check the existence of product code
     */
    private boolean checkExistingValue(String productCode) {
        // Iterate through the list of existing craft items to find duplicates
        for (CraftModel obj1 : craftList) {
            if (obj1.getProductId().equals(productCode)) {
                return true; // A match is found, the product code exists
            }
        }
        return false;
    }
/*
    *validate the inputs entered by the user
    */
    private boolean checkEmpty(JTextField jtextField, String title, JLabel errorLbl, String errorMsg, Color errorColor, Color successColor, boolean valid) {
        if (Validation.isNull(jtextField.getText())) {
            errorLbl.setText(title + " is empty");
            jtextField.setBorder(createTitledBorder(errorColor, title));
            successfulMessage.setText("");
            return false;
        } else if (!valid) {
            jtextField.setBorder(createTitledBorder(errorColor, title));
            errorLbl.setText(errorMsg);
            successfulMessage.setText("");
            return false;
        } else {
            errorLbl.setText("");
            jtextField.setBorder(createTitledBorder(successColor, title));
            return true;
        }

    }
    
//reset all validation and error messages
    private void resetValidation() {
        // Reset all error messages and borders to default

        errorMessageProdCode.setText("");

        errorMessageName.setText("");

        errorMessagePrice.setText("");

        errorMessageStock.setText("");

        errorMessageCategory.setText("");

        errorMessageStatus.setText("");

        errorMessageDate.setText("");

        errorMessageWeight.setText("");

        errorMessageSales.setText("");

        successfulMessage.setText("");

        productNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Name"));
        productPriceTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Price"));
        productStockTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Stock"));
        productCategoryComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Category"));
        productDateTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Date Created"));
        productSalesTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Sales"));
        productWeightTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Weight"));
        productStatusComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Status"));
        productCodeTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Code"));
    }
    
//validate the selection of combobox
    private boolean checkComboBoxValidation(JComboBox comboBox, String title, JLabel errorMessage, Color errorColor, Color successColor) {
        String selectedItem = comboBox.getSelectedItem().toString();
        if ("Select an item".equals(selectedItem)) {
            errorMessage.setText("Please select an item");
            comboBox.setBorder(createTitledBorder(errorColor, title)); // Set the border to red
            successfulMessage.setText("");
            return false;
        } else {
            errorMessage.setText(""); // Hide the error message if validation is successful
            comboBox.setBorder(createTitledBorder(successColor, title)); // Set the border to green
            return true;
        }
    }

    //create a titled border ith a specific color and title
    private Border createTitledBorder(Color color, String title) {

        return javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(color, 2),
                title,
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Helvetica Neue", 1, 13),
                color
        );

    }
    
//retrieve total sales of the product
    private void totalSales() {
        int total = 0;
        for (int i = 0; i < craftList.size(); i++) {
            total += craftList.get(i).getTotalSales() * craftList.get(i).getPrice();
        }
        totalSalesLabel.setText("Rs. " + total);
    }

//retrieve total stock of the product
    private void totalStock() {
        int stock = 0;
        for (int i = 0; i < craftList.size(); i++) {
            stock += craftList.get(i).getStock();
        }
        totalStockLevel.setText(String.valueOf(stock));

    }

//retriece total item sold of the product
    private void itemsSold() {
        int soldItems = 0;
        for (int i = 0; i < craftList.size(); i++) {
            soldItems += craftList.get(i).getTotalSales();
        }

        totalItemSoldLabel.setText(String.valueOf(soldItems));
    }
    
//retrieve product overview
    private void productOverview() {
        totalSales();
        totalStock();
        itemsSold();
    }

    /*
    Method to validate form fields for various data types
    */
    private boolean validation() {
        boolean valid = true;
        valid &= checkEmpty(productCodeTextField, "Product Code", errorMessageProdCode, "The code must have at least 10 digits", Color.RED, Color.GREEN, Validation.validateCode(productCodeTextField.getText()));

        valid &= checkEmpty(productNameTextField, "Product Name", errorMessageName, "Name must contain alphabets only", Color.RED, Color.GREEN, Validation.validateName(productNameTextField.getText()));
        //valid &= checkEmpty(productSalesTextField, "Total Sales", errorMessageSales, "Invalid date", Color.RED, Color.GREEN, Validation.validateSales(Integer.parseInt(productSalesTextField.getText())));
        valid &= checkEmpty(productDateTextField, "Product date", errorMessageDate, "Date must be in yyyy/mm/dd format", Color.RED, Color.GREEN, Validation.validateDate(productDateTextField.getText()));
        valid &= checkComboBoxValidation(productCategoryComboBox, "Product Category", errorMessageCategory, Color.RED, Color.GREEN);
        valid &= checkComboBoxValidation(productStatusComboBox, "Product Status", errorMessageStatus, Color.RED, Color.GREEN);

        // Validate price field
        String priceText = productPriceTextField.getText();
        if (Validation.isNull(priceText)) {
            errorMessagePrice.setText("Product price cannot be empty");
            productPriceTextField.setBorder(createTitledBorder(Color.RED, "Product Price"));

        } else {

            try {
                valid &= checkEmpty(productPriceTextField, "Product Price", errorMessagePrice, "Please enter a valid price", Color.RED, Color.GREEN, Validation.validatePrice(Float.parseFloat(priceText)));
            } catch (NumberFormatException e) {
                errorMessagePrice.setText("price must contain numeric values only");
                productPriceTextField.setBorder(createTitledBorder(Color.RED, "Product Price"));
            }
        }

        String sales = productSalesTextField.getText(); // Remove any leading/trailing whitespace
        if (Validation.isNull(sales)) {
            errorMessageSales.setText("Sales cannot be empty");
            productSalesTextField.setBorder(createTitledBorder(Color.RED, "Product Sales"));

        } else {
            try {
                valid &= checkEmpty(productSalesTextField, "Product sales", errorMessageSales, "sales must contain numeric values only", Color.RED, Color.GREEN, Validation.validateSales(Integer.parseInt(sales)));
                System.out.println("");
            } catch (NumberFormatException e) {
                errorMessageSales.setText("Invalid sale entry");
                productSalesTextField.setBorder(createTitledBorder(Color.RED, "Product Sales"));

            }
        }

        // Validate stock field
        String stockText = productStockTextField.getText();
        if (Validation.isNull(stockText)) {
            errorMessageStock.setText("Product stock cannot be empty");
            productStockTextField.setBorder(createTitledBorder(Color.RED, "Product Stock"));
        } else {
            try {
                valid &= checkEmpty(productStockTextField, "Product Stock", errorMessageStock, "Please enter a valid stock", Color.RED, Color.GREEN, Validation.validateStock(Integer.parseInt(stockText)));
            } catch (NumberFormatException e) {
                errorMessageStock.setText("stock must contain numeric values only");
                productStockTextField.setBorder(createTitledBorder(Color.RED, "Product Stock"));
            }
        }

        // Validate weight field
        String weight = productWeightTextField.getText();
        if (Validation.isNull(weight)) {
            errorMessageWeight.setText("Product weight cannot be empty");
            productWeightTextField.setBorder(createTitledBorder(Color.RED, "Product Weight"));
        } else {
            try {
                valid &= checkEmpty(productWeightTextField, "Product Weight", errorMessageWeight, "Please enter a valid stock", Color.RED, Color.GREEN, Validation.validateWeight(Float.parseFloat(weight)));
            } catch (NumberFormatException e) {
                errorMessageWeight.setText(" weight must contain numeric values only");
                productWeightTextField.setBorder(createTitledBorder(Color.RED, "Product Weight"));
            }
        }
        return valid;
    }

    //displays a confirmation dialog with the specified message and title.
    private int dialogConfirm(String message, String title) {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return option;
    }

    //displays a message dialog with the specified message and title.
    private void dialogMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
        successfulMessage.setText("");
        resetValidation();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelLoginScreen = new javax.swing.JPanel();
        getStartedPnl = new javax.swing.JPanel();
        getStartedLabel = new javax.swing.JLabel();
        loginButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        forgetPassLabel = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        logoImgLabel = new javax.swing.JLabel();
        usernamePassword = new javax.swing.JPanel();
        mailImg = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordPanel = new javax.swing.JPanel();
        passwordTextField = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        errorMismatch = new javax.swing.JLabel();
        loginImgOverall = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        panelMainScreen = new javax.swing.JPanel();
        mainScreenTitlePnl = new javax.swing.JPanel();
        logOutButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        mainScreenBodyPnl = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        homePnl = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        aboutUsPnl = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        nameAboutUsTextField = new javax.swing.JTextField();
        subjectTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        nameErrorLable = new javax.swing.JLabel();
        emailErrorLabel = new javax.swing.JLabel();
        subjectErrorLabel = new javax.swing.JLabel();
        messageErrorLabel = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        adminCtrlPnl = new javax.swing.JPanel();
        craftTablePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        craftTable = new javax.swing.JTable();
        productListPnl = new javax.swing.JPanel();
        productCodeTextField = new javax.swing.JTextField();
        productNameTextField = new javax.swing.JTextField();
        productPriceTextField = new javax.swing.JTextField();
        productStockTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        errorMessageProdCode = new javax.swing.JLabel();
        errorMessagePrice = new javax.swing.JLabel();
        errorMessageName = new javax.swing.JLabel();
        productSalesTextField = new javax.swing.JTextField();
        productCategoryComboBox = new javax.swing.JComboBox<>();
        productWeightTextField = new javax.swing.JTextField();
        productDateTextField = new javax.swing.JTextField();
        productStatusComboBox = new javax.swing.JComboBox<>();
        productInfoLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        errorMessageStock = new javax.swing.JLabel();
        errorMessageCategory = new javax.swing.JLabel();
        errorMessageWeight = new javax.swing.JLabel();
        errorMessageDate = new javax.swing.JLabel();
        errorMessageSales = new javax.swing.JLabel();
        errorMessageStatus = new javax.swing.JLabel();
        successfulMessage = new javax.swing.JLabel();
        prodOverviewPnl = new javax.swing.JPanel();
        overallSalesLabel = new javax.swing.JLabel();
        overallStockLabel = new javax.swing.JLabel();
        itemsSoldLabel = new javax.swing.JLabel();
        totalSalesLabel = new javax.swing.JLabel();
        totalStockLevel = new javax.swing.JLabel();
        totalItemSoldLabel = new javax.swing.JLabel();
        prodOverviewLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        loadingScreen = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        progressBarNaming = new javax.swing.JLabel();
        loadingPgImage = new javax.swing.JLabel();

        panelLoginScreen.setMaximumSize(new java.awt.Dimension(1392, 765));
        panelLoginScreen.setMinimumSize(new java.awt.Dimension(1392, 765));
        panelLoginScreen.setPreferredSize(new java.awt.Dimension(1392, 765));
        panelLoginScreen.setSize(new java.awt.Dimension(1392, 765));
        panelLoginScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getStartedPnl.setBackground(new java.awt.Color(195, 202, 197));
        getStartedPnl.setOpaque(false);
        getStartedPnl.setPreferredSize(new java.awt.Dimension(276, 200));
        getStartedPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getStartedLabel.setFont(new java.awt.Font("New Peninim MT", 1, 24)); // NOI18N
        getStartedLabel.setText("GET STARTED");
        getStartedPnl.add(getStartedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        loginButton1.setBackground(new java.awt.Color(51, 51, 51));
        loginButton1.setForeground(new java.awt.Color(255, 255, 255));
        loginButton1.setText("Log in");
        loginButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButton1ActionPerformed(evt);
            }
        });
        getStartedPnl.add(loginButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 210, 40));

        jCheckBox1.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 0, 13)); // NOI18N
        jCheckBox1.setText("Remember Me");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getStartedPnl.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        forgetPassLabel.setForeground(new java.awt.Color(153, 0, 51));
        forgetPassLabel.setText("Forgot your Password?");
        getStartedPnl.add(forgetPassLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        jLabel41.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 0, 13)); // NOI18N
        jLabel41.setText("or continue with");
        getStartedPnl.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        logoImgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot_2024-12-23_at_8.59.45_PM-removebg-preview-6.png"))); // NOI18N
        getStartedPnl.add(logoImgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 310, -1));

        usernamePassword.setBackground(new java.awt.Color(255, 255, 255));

        mailImg.setBackground(new java.awt.Color(255, 255, 255));
        mailImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/image-removebg-preview-3-3.png"))); // NOI18N

        usernameTextField.setBorder(null);
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout usernamePasswordLayout = new javax.swing.GroupLayout(usernamePassword);
        usernamePassword.setLayout(usernamePasswordLayout);
        usernamePasswordLayout.setHorizontalGroup(
            usernamePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usernamePasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mailImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        usernamePasswordLayout.setVerticalGroup(
            usernamePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usernamePasswordLayout.createSequentialGroup()
                .addGroup(usernamePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usernamePasswordLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mailImg))
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getStartedPnl.add(usernamePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 340, -1));

        passwordPanel.setBackground(new java.awt.Color(255, 255, 255));

        passwordTextField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/image-removebg-preview-2-4.png"))); // NOI18N

        jPasswordField3.setBorder(null);
        jPasswordField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout passwordPanelLayout = new javax.swing.GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, passwordPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passwordTextField)
                .addGap(16, 16, 16))
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addComponent(jPasswordField3)
                .addContainerGap())
        );

        getStartedPnl.add(passwordPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 340, 40));

        errorMismatch.setForeground(new java.awt.Color(255, 0, 0));
        getStartedPnl.add(errorMismatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 360, 20));

        panelLoginScreen.add(getStartedPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, 370, 480));

        loginImgOverall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-11 at 8.40.21 PM.png"))); // NOI18N
        panelLoginScreen.add(loginImgOverall, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot_2024-12-23_at_8.59.45_PM-removebg-preview-5.png"))); // NOI18N
        panelLoginScreen.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, 1200, 220));

        panelMainScreen.setMaximumSize(new java.awt.Dimension(1392, 768));
        panelMainScreen.setMinimumSize(new java.awt.Dimension(1392, 768));
        panelMainScreen.setPreferredSize(new java.awt.Dimension(1392, 768));
        panelMainScreen.setRequestFocusEnabled(false);
        panelMainScreen.setSize(new java.awt.Dimension(1392, 768));

        mainScreenTitlePnl.setBackground(new java.awt.Color(134, 103, 83));
        mainScreenTitlePnl.setMaximumSize(new java.awt.Dimension(1294, 86));
        mainScreenTitlePnl.setMinimumSize(new java.awt.Dimension(1294, 86));
        mainScreenTitlePnl.setRequestFocusEnabled(false);
        mainScreenTitlePnl.setSize(new java.awt.Dimension(1392, 758));

        logOutButton.setBackground(new java.awt.Color(242, 240, 231));
        logOutButton.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        logOutButton.setText("LOG OUT");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Kannada MN", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Where Tradition Meets Creativity – Discover Handcrafted Wonders");

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/image-removebg-preview (6).png"))); // NOI18N

        javax.swing.GroupLayout mainScreenTitlePnlLayout = new javax.swing.GroupLayout(mainScreenTitlePnl);
        mainScreenTitlePnl.setLayout(mainScreenTitlePnlLayout);
        mainScreenTitlePnlLayout.setHorizontalGroup(
            mainScreenTitlePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainScreenTitlePnlLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(logoLabel)
                .addGap(96, 96, 96)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        mainScreenTitlePnlLayout.setVerticalGroup(
            mainScreenTitlePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainScreenTitlePnlLayout.createSequentialGroup()
                .addGroup(mainScreenTitlePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainScreenTitlePnlLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(mainScreenTitlePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titleLabel)))
                    .addGroup(mainScreenTitlePnlLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        mainScreenBodyPnl.setMaximumSize(new java.awt.Dimension(1294, 716));
        mainScreenBodyPnl.setMinimumSize(new java.awt.Dimension(1294, 716));
        mainScreenBodyPnl.setSize(new java.awt.Dimension(1294, 716));

        jTabbedPane1.setBackground(new java.awt.Color(228, 222, 208));

        homePnl.setMaximumSize(new java.awt.Dimension(1392, 758));
        homePnl.setPreferredSize(new java.awt.Dimension(1392, 758));
        homePnl.setSize(new java.awt.Dimension(1392, 758));

        jScrollPane1.setLocation(new java.awt.Point(1392, 748));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(1392, 748));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1392, 748));
        jScrollPane1.setSize(new java.awt.Dimension(1392, 748));

        jPanel1.setBackground(new java.awt.Color(204, 199, 197));
        jPanel1.setMaximumSize(new java.awt.Dimension(1392, 3931));
        jPanel1.setMinimumSize(new java.awt.Dimension(1392, 3931));
        jPanel1.setPreferredSize(new java.awt.Dimension(1392, 3931));
        jPanel1.setSize(new java.awt.Dimension(1392, 3931));
        jPanel1.setLayout(null);

        jPanel14.setBackground(new java.awt.Color(227, 221, 214));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/dem-removebg-preview.png"))); // NOI18N
        jPanel14.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 270, 170));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/demo_-removebg-preview.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel14.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 190, 170));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/demo_18-removebg-preview.png"))); // NOI18N
        jPanel14.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, 190, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/d-removebg-preview.png"))); // NOI18N
        jPanel14.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 120, 201, 157));

        jLabel55.setFont(new java.awt.Font("Herculanum", 0, 20)); // NOI18N
        jLabel55.setText("Pottery");
        jPanel14.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 100, 19));

        jLabel61.setFont(new java.awt.Font("Herculanum", 0, 20)); // NOI18N
        jLabel61.setText("METALWARE");
        jPanel14.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, 150, 19));

        jLabel62.setFont(new java.awt.Font("Herculanum", 0, 20)); // NOI18N
        jLabel62.setText("SCULPTING");
        jPanel14.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 150, 19));

        jLabel27.setFont(new java.awt.Font("Herculanum", 0, 20)); // NOI18N
        jLabel27.setText("Loom & Fiber Arts");
        jPanel14.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, 220, 19));

        jLabel71.setFont(new java.awt.Font("Noteworthy", 1, 24)); // NOI18N
        jLabel71.setText("WE OFFER");
        jPanel14.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 260, -1));

        jPanel1.add(jPanel14);
        jPanel14.setBounds(20, 1350, 1330, 390);

        jLabel8.setFont(new java.awt.Font("Kokonor", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 0));
        jLabel8.setText("Bringing Art to Life, One Piece at a Time.");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(250, 130, 860, 140);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 4.47.52 PM.png"))); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(1018, 1962));
        jPanel1.add(jLabel9);
        jLabel9.setBounds(0, 0, 1390, 750);

        jPanel4.setBackground(new java.awt.Color(239, 234, 228));
        jPanel4.setMaximumSize(new java.awt.Dimension(570, 590));
        jPanel4.setMinimumSize(new java.awt.Dimension(570, 590));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 7.01.04 PM.png"))); // NOI18N
        jLabel10.setMaximumSize(new java.awt.Dimension(510, 390));
        jLabel10.setMinimumSize(new java.awt.Dimension(510, 390));
        jLabel10.setPreferredSize(new java.awt.Dimension(510, 390));
        jLabel10.setSize(new java.awt.Dimension(510, 390));

        jLabel30.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel30.setText("<html>     <p>A region's artistic traditions and cultural legacy are reflected in its folk art handicrafts. They combine artistic expression with practicality to portray the spirit of regional tales, celebrations, and ways of life.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(730, 1930, 590, 590);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-25 at 8.42.44 PM.png"))); // NOI18N
        jPanel5.add(jLabel18);
        jLabel18.setBounds(550, 70, 450, 420);

        jLabel1.setBackground(new java.awt.Color(142, 80, 42));
        jLabel1.setFont(new java.awt.Font("PT Mono", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(205, 140, 81));
        jLabel1.setText("SUSTAINABLE MATERIALS");
        jPanel5.add(jLabel1);
        jLabel1.setBounds(1030, 340, 320, 23);
        jPanel5.add(jLabel14);
        jLabel14.setBounds(1289, 236, 0, 0);

        jLabel37.setFont(new java.awt.Font("Kannada MN", 0, 14)); // NOI18N
        jLabel37.setText("<html> <p>Our artisans follow techniques that have been passed down for generations, giving each item a piece of rich cultural history and craftsmanship.");
        jPanel5.add(jLabel37);
        jLabel37.setBounds(1000, 150, 370, 70);

        jLabel45.setBackground(new java.awt.Color(142, 80, 42));
        jLabel45.setFont(new java.awt.Font("PT Mono", 1, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(205, 140, 81));
        jLabel45.setText("UNIQUE");
        jPanel5.add(jLabel45);
        jLabel45.setBounds(150, 150, 72, 23);

        jLabel12.setBackground(new java.awt.Color(142, 80, 42));
        jLabel12.setFont(new java.awt.Font("PT Mono", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(205, 140, 81));
        jLabel12.setText("DURABLE AND LONG LASTING");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(150, 330, 320, 23);

        jLabel47.setBackground(new java.awt.Color(142, 80, 42));
        jLabel47.setFont(new java.awt.Font("PT Mono", 1, 20)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(205, 140, 81));
        jLabel47.setText("TRADITIONAL CRAFTMANSHIP");
        jPanel5.add(jLabel47);
        jLabel47.setBounds(1010, 120, 320, 23);

        jLabel49.setFont(new java.awt.Font("Kannada MN", 0, 14)); // NOI18N
        jLabel49.setText("<html> <p>Each piece is individually crafted, ensuring no two items are the same. You’ll own a truly unique creation, designed to stand out.");
        jPanel5.add(jLabel49);
        jLabel49.setBounds(150, 190, 370, 70);

        jLabel56.setFont(new java.awt.Font("Kannada MN", 0, 14)); // NOI18N
        jLabel56.setText("<html> <p>We use only the highest quality materials, ensuring that each product is not just beautiful, but durable and made to stand the test of time.");
        jPanel5.add(jLabel56);
        jLabel56.setBounds(140, 390, 370, 70);

        jLabel87.setFont(new java.awt.Font("Kannada MN", 0, 14)); // NOI18N
        jLabel87.setText("<html> <p>We care about the environment, which is why we choose sustainable materials that are kind to the planet without compromising on quality or style.");
        jPanel5.add(jLabel87);
        jLabel87.setBounds(1000, 380, 370, 70);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(-10, 770, 1390, 530);

        jPanel3.setBackground(new java.awt.Color(227, 221, 214));
        jPanel3.setMaximumSize(new java.awt.Dimension(570, 590));
        jPanel3.setMinimumSize(new java.awt.Dimension(570, 590));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 7.14.02 PM.png"))); // NOI18N
        jLabel15.setMaximumSize(new java.awt.Dimension(326, 230));
        jLabel15.setMinimumSize(new java.awt.Dimension(326, 230));
        jLabel15.setPreferredSize(new java.awt.Dimension(326, 230));
        jLabel15.setSize(new java.awt.Dimension(42, 17));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 10.09.01 PM.png"))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel34.setText("<html>     <p>Experience the warmth and charm of nature, hand-sculpted into timeless art pieces that bring life to your spaces. Wooden crafts exude a natural elegance that complements both rustic and contemporary interiors, creating a cozy and inviting atmosphere.");

        jLabel69.setFont(new java.awt.Font("Kailasa", 1, 36)); // NOI18N
        jLabel69.setText("WoodCrafts");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel69)
                .addGap(27, 27, 27)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(730, 2590, 560, 600);

        jPanel7.setBackground(new java.awt.Color(227, 221, 214));
        jPanel7.setMaximumSize(new java.awt.Dimension(570, 590));
        jPanel7.setPreferredSize(new java.awt.Dimension(570, 590));
        jPanel7.setSize(new java.awt.Dimension(570, 590));
        jPanel7.setLayout(null);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 6.07.26 PM.png"))); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(326, 230));
        jLabel19.setRequestFocusEnabled(false);
        jPanel7.add(jLabel19);
        jLabel19.setBounds(31, 343, 260, 230);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 6.07.32 PM.png"))); // NOI18N
        jLabel20.setMaximumSize(new java.awt.Dimension(326, 230));
        jLabel20.setMinimumSize(new java.awt.Dimension(326, 230));
        jLabel20.setPreferredSize(new java.awt.Dimension(326, 230));
        jPanel7.add(jLabel20);
        jLabel20.setBounds(309, 343, 326, 230);

        jLabel29.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel29.setText("<html> <p>Explore a beautiful selection of folk art handicrafts that will bring tales from around the globe into your house.");
        jPanel7.add(jLabel29);
        jLabel29.setBounds(40, 170, 532, 79);

        jLabel63.setFont(new java.awt.Font("Kailasa", 1, 36)); // NOI18N
        jLabel63.setText("Folk Art Handicrafts");
        jPanel7.add(jLabel63);
        jLabel63.setBounds(40, 120, 410, 48);

        jPanel1.add(jPanel7);
        jPanel7.setBounds(80, 1930, 570, 590);

        jPanel8.setBackground(new java.awt.Color(227, 221, 214));
        jPanel8.setMaximumSize(new java.awt.Dimension(570, 590));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 7.20.54 PM.png"))); // NOI18N

        jLabel32.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel32.setText("<html> <p>Metal crafts are classic pieces of art made by forming and decorating metals such as iron, copper, or brass. These sculptures are ideal for décor and gift-giving since they frequently combine sophisticated design with usefulness.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(740, 3270, 560, 600);

        jPanel10.setBackground(new java.awt.Color(227, 221, 214));
        jPanel10.setMaximumSize(new java.awt.Dimension(570, 590));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 7.15.21 PM.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel33.setText("<html>     <p>Discover beautifully handcrafted pottery, each piece telling its own story through unique designs, materials, and techniques.");

        jLabel70.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel70.setText("<html>     <p>Wooden crafts are a celebration of natural beauty combined with artistic skill. From intricately carved furniture to vibrant figurines, wooden crafts highlight the versatility of this material.");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel10);
        jPanel10.setBounds(80, 2590, 570, 600);

        jLabel54.setFont(new java.awt.Font("Noteworthy", 1, 24)); // NOI18N
        jLabel54.setText("SEE MORE");
        jPanel1.add(jLabel54);
        jLabel54.setBounds(120, 1050, 130, 50);

        jPanel18.setBackground(new java.awt.Color(227, 221, 214));
        jPanel18.setMaximumSize(new java.awt.Dimension(570, 590));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 7.10.34 PM.png"))); // NOI18N
        jLabel65.setMaximumSize(new java.awt.Dimension(326, 230));
        jLabel65.setMinimumSize(new java.awt.Dimension(326, 230));
        jLabel65.setPreferredSize(new java.awt.Dimension(326, 230));
        jLabel65.setSize(new java.awt.Dimension(42, 17));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-22 at 7.06.21 PM.png"))); // NOI18N

        jLabel67.setFont(new java.awt.Font("Gurmukhi MN", 2, 14)); // NOI18N
        jLabel67.setText("<html>     <p>From ornate traditional designs to sleek modern pieces, metal crafts are versatile enough to complement any decor style, making them perfect for your home or office.  ");

        jLabel68.setFont(new java.awt.Font("Kailasa", 1, 36)); // NOI18N
        jLabel68.setText("MetalCraft");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap(8, Short.MAX_VALUE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel1.add(jPanel18);
        jPanel18.setBounds(90, 3270, 560, 600);

        jLabel53.setFont(new java.awt.Font("Noteworthy", 1, 24)); // NOI18N
        jLabel53.setText("LEARM MORE ABOUT OUR PRODUCTS");
        jPanel1.add(jLabel53);
        jLabel53.setBounds(90, 1860, 480, 39);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout homePnlLayout = new javax.swing.GroupLayout(homePnl);
        homePnl.setLayout(homePnlLayout);
        homePnlLayout.setHorizontalGroup(
            homePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePnlLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homePnlLayout.setVerticalGroup(
            homePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab("Home", homePnl);

        aboutUsPnl.setMaximumSize(new java.awt.Dimension(1392, 758));
        aboutUsPnl.setMinimumSize(new java.awt.Dimension(1392, 758));
        aboutUsPnl.setPreferredSize(new java.awt.Dimension(1392, 758));
        aboutUsPnl.setSize(new java.awt.Dimension(1392, 758));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(1392, 758));
        jScrollPane4.setSize(new java.awt.Dimension(1392, 758));

        jPanel13.setBackground(new java.awt.Color(244, 241, 232));
        jPanel13.setBounds(new java.awt.Rectangle(0, 0, 1392, 1200));
        jPanel13.setMaximumSize(new java.awt.Dimension(1392, 1200));
        jPanel13.setMinimumSize(new java.awt.Dimension(1392, 1200));
        jPanel13.setPreferredSize(new java.awt.Dimension(1392, 1200));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setMaximumSize(new java.awt.Dimension(1317, 658));
        jPanel19.setSize(new java.awt.Dimension(1317, 658));

        jPanel24.setBackground(new java.awt.Color(225, 223, 208));

        jLabel51.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel51.setText("Get In Touch");

        jLabel52.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel52.setText("Name");

        nameAboutUsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameAboutUsTextFieldActionPerformed(evt);
            }
        });

        subjectTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectTextFieldActionPerformed(evt);
            }
        });

        sendButton.setBackground(new java.awt.Color(51, 51, 51));
        sendButton.setFont(new java.awt.Font("Kailasa", 1, 14)); // NOI18N
        sendButton.setForeground(new java.awt.Color(255, 255, 255));
        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel58.setText("E-mail");

        jLabel59.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel59.setText("Subject");

        jLabel60.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel60.setText("Message");

        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane3.setViewportView(messageTextArea);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel51))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailTextField)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailErrorLabel)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameErrorLable)
                                .addGap(28, 28, 28))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subjectErrorLabel)
                                .addGap(24, 24, 24))
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subjectTextField)
                            .addComponent(nameAboutUsTextField)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(messageErrorLabel)
                                .addGap(20, 20, 20))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))))
                .addGap(0, 79, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel51)
                .addGap(43, 43, 43)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(nameErrorLable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameAboutUsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(emailErrorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subjectErrorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(messageErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jLabel50.setFont(new java.awt.Font("Kannada MN", 1, 24)); // NOI18N
        jLabel50.setText("CONTACT US:");

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setPreferredSize(new java.awt.Dimension(211, 180));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/image-removebg-preview-4-3.png"))); // NOI18N

        jLabel76.setFont(new java.awt.Font("Arial Hebrew Scholar", 1, 12)); // NOI18N
        jLabel76.setText("E-mail");

        jLabel77.setText("umbra@gmail.com");

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/image-removebg-preview-5-2.png"))); // NOI18N

        jLabel73.setFont(new java.awt.Font("Arial Hebrew Scholar", 1, 12)); // NOI18N
        jLabel73.setText("TikTok");

        jLabel74.setText("@umbraCraft");

        jLabel78.setBackground(new java.awt.Color(244, 241, 232));
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot_2024-12-23_at_8.59.45_PM-removebg-preview-6.png"))); // NOI18N

        jLabel79.setFont(new java.awt.Font("Arial Hebrew Scholar", 1, 12)); // NOI18N
        jLabel79.setText("Facebook");

        jLabel80.setText("umbraCeramic");

        jLabel82.setFont(new java.awt.Font("Arial Hebrew Scholar", 1, 12)); // NOI18N
        jLabel82.setText("Twitter");

        jLabel83.setText("@umbraCrafts324");

        jLabel81.setBackground(new java.awt.Color(244, 241, 232));
        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot_2024-12-23_at_8.59.45_PM-removebg-preview-4.png"))); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel75)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel77)))
                .addGap(64, 64, 64)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(127, 127, 127)
                                .addComponent(jLabel82))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(105, 105, 105)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel73))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel74)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addGap(8, 8, 8))
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel77))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel73)
                                .addComponent(jLabel82)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel74)
                                .addComponent(jLabel83))
                            .addComponent(jLabel80))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Kannada MN", 1, 24)); // NOI18N
        jLabel16.setText("OUR STORY");

        jLabel17.setFont(new java.awt.Font("Kannada MN", 0, 15)); // NOI18N
        jLabel17.setText("<html><p>Our journey began with a deep love for hand-crafted art and a genuine desire to support artisans from all corners of the world. What began as a small passion project has since blossomed into a community where artisans and customers come together. We are committed to preserving traditional craftsmanship while bringing beautiful, meaningful creations to your home. Every piece tells a story, and we're excited to share that journey with you.");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 106;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 40, 40, 0);
        jPanel13.add(jPanel19, gridBagConstraints);

        jPanel26.setBackground(new java.awt.Color(244, 241, 232));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setFont(new java.awt.Font("Kannada MN", 0, 18)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("<html> <p> We are dedicated to creating a vibrant platform that connects skilled local artisans with customers seeking unique, handmade creations. Our mission is to celebrate traditional craftsmanship, empower small-scale crafters, and bring authentic, handcrafted products to a global stage.");
        jPanel26.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 1290, 150));

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-24 at 9.19.26 PM.png"))); // NOI18N
        jPanel26.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 310, 100));

        jLabel86.setFont(new java.awt.Font("Kohinoor Gujarati", 1, 24)); // NOI18N
        jLabel86.setText("OUR MISSION");
        jPanel26.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        jPanel13.add(jPanel26, new java.awt.GridBagConstraints());

        jScrollPane4.setViewportView(jPanel13);

        javax.swing.GroupLayout aboutUsPnlLayout = new javax.swing.GroupLayout(aboutUsPnl);
        aboutUsPnl.setLayout(aboutUsPnlLayout);
        aboutUsPnlLayout.setHorizontalGroup(
            aboutUsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1392, Short.MAX_VALUE)
            .addGroup(aboutUsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutUsPnlLayout.setVerticalGroup(
            aboutUsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
            .addGroup(aboutUsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(aboutUsPnlLayout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("About Us", aboutUsPnl);

        adminCtrlPnl.setBackground(new java.awt.Color(225, 223, 208));
        adminCtrlPnl.setMaximumSize(new java.awt.Dimension(1392, 748));
        adminCtrlPnl.setMinimumSize(new java.awt.Dimension(1392, 748));
        adminCtrlPnl.setPreferredSize(new java.awt.Dimension(1382, 686));

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));

        craftTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        craftTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        craftTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Product Code", "Product Name", "Price", "Stock", "Category", "Total Sales", "Status", "Date Created(yyyy/mm/dd)", "Weight"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }

    );
    craftTable.setAlignmentX(1.0F);
    craftTable.setAlignmentY(1.0F);
    craftTable.setRowHeight(30);
    craftTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
    craftTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(craftTable);
    if (craftTable.getColumnModel().getColumnCount() > 0) {
        craftTable.getColumnModel().getColumn(0).setResizable(false);
        craftTable.getColumnModel().getColumn(1).setResizable(false);
        craftTable.getColumnModel().getColumn(2).setResizable(false);
        craftTable.getColumnModel().getColumn(3).setResizable(false);
        craftTable.getColumnModel().getColumn(4).setResizable(false);
        craftTable.getColumnModel().getColumn(5).setResizable(false);
        craftTable.getColumnModel().getColumn(6).setResizable(false);
        craftTable.getColumnModel().getColumn(7).setResizable(false);
        craftTable.getColumnModel().getColumn(8).setResizable(false);
    }
    craftTable.getTableHeader().setFont(new Font("Arial", java.awt.Font.BOLD, 14));

    javax.swing.GroupLayout craftTablePanelLayout = new javax.swing.GroupLayout(craftTablePanel);
    craftTablePanel.setLayout(craftTablePanelLayout);
    craftTablePanelLayout.setHorizontalGroup(
        craftTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(craftTablePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1358, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(16, Short.MAX_VALUE))
    );
    craftTablePanelLayout.setVerticalGroup(
        craftTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, craftTablePanelLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    productListPnl.setBackground(new java.awt.Color(242, 240, 231));

    productCodeTextField.setBackground(new java.awt.Color(242, 240, 231));
    productCodeTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Product Code"));
    productCodeTextField.setSize(new java.awt.Dimension(64, 41));
    productCodeTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            productCodeTextFieldActionPerformed(evt);
        }
    });

    productNameTextField.setBackground(new java.awt.Color(242, 240, 231));
    productNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Name"));
    productNameTextField.setSize(new java.awt.Dimension(64, 41));

    productPriceTextField.setBackground(new java.awt.Color(242, 240, 231));
    productPriceTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Price")));
    productPriceTextField.setSize(new java.awt.Dimension(64, 41));

    productStockTextField.setBackground(new java.awt.Color(242, 240, 231));
    productStockTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock"));
    productStockTextField.setSize(new java.awt.Dimension(64, 41));

    addButton.setBackground(new java.awt.Color(134, 103, 83));
    addButton.setForeground(new java.awt.Color(255, 255, 255));
    addButton.setText("add");
    addButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addButtonActionPerformed(evt);
        }
    });

    updateButton.setBackground(new java.awt.Color(134, 103, 83));
    updateButton.setForeground(new java.awt.Color(255, 255, 255));
    updateButton.setText("update");
    updateButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            updateButtonActionPerformed(evt);
        }
    });

    errorMessageProdCode.setForeground(new java.awt.Color(255, 0, 0));
    errorMessageProdCode.setText("<html><p>");

    errorMessagePrice.setForeground(new java.awt.Color(255, 0, 0));

    errorMessageName.setForeground(new java.awt.Color(255, 0, 0));
    errorMessageName.setText("<html><p>");

    productSalesTextField.setBackground(new java.awt.Color(242, 240, 231));
    productSalesTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Sales"));
    productSalesTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            productSalesTextFieldActionPerformed(evt);
        }
    });

    productCategoryComboBox.setBackground(new java.awt.Color(242, 240, 231));
    productCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an item", "pottery", "ceramics", "woodcraft", "beaded jewellery", "knitting", "weaving", " ", " " }));
    productCategoryComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Category"));
    productCategoryComboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            productCategoryComboBoxActionPerformed(evt);
        }
    });

    productWeightTextField.setBackground(new java.awt.Color(242, 240, 231));
    productWeightTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Weight"));
    productWeightTextField.setSize(new java.awt.Dimension(64, 41));

    productDateTextField.setBackground(new java.awt.Color(242, 240, 231));
    productDateTextField.setToolTipText("");
    productDateTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Date Created"));
    productDateTextField.setSize(new java.awt.Dimension(64, 41));
    productDateTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            productDateTextFieldActionPerformed(evt);
        }
    });

    productStatusComboBox.setOpaque(true);
    productStatusComboBox.setBackground(new java.awt.Color(242, 240, 231));
    productStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an item", "active", "discontinued", "inactive" }));
    productStatusComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Product Status"));
    productStatusComboBox.repaint();

    productInfoLabel.setFont(new java.awt.Font("InaiMathi", 1, 27)); // NOI18N
    productInfoLabel.setText("Product Information");

    deleteButton.setBackground(new java.awt.Color(134, 103, 83));
    deleteButton.setForeground(new java.awt.Color(255, 255, 255));
    deleteButton.setText("Delete");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteButtonActionPerformed(evt);
        }
    });

    clearButton.setBackground(new java.awt.Color(134, 103, 83));
    clearButton.setForeground(new java.awt.Color(255, 255, 255));
    clearButton.setText("clear");
    clearButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearButtonActionPerformed(evt);
        }
    });

    errorMessageStock.setForeground(new java.awt.Color(255, 0, 51));

    errorMessageCategory.setForeground(new java.awt.Color(255, 0, 51));

    errorMessageWeight.setForeground(new java.awt.Color(255, 0, 51));

    errorMessageDate.setForeground(new java.awt.Color(255, 0, 51));

    errorMessageSales.setForeground(new java.awt.Color(255, 0, 0));

    errorMessageStatus.setForeground(new java.awt.Color(255, 0, 51));

    successfulMessage.setForeground(new java.awt.Color(51, 153, 0));

    javax.swing.GroupLayout productListPnlLayout = new javax.swing.GroupLayout(productListPnl);
    productListPnl.setLayout(productListPnlLayout);
    productListPnlLayout.setHorizontalGroup(
        productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(productListPnlLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(productListPnlLayout.createSequentialGroup()
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(productListPnlLayout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessageWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(42, 42, 42)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(errorMessageDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(productDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(27, 27, 27)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(productListPnlLayout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(productListPnlLayout.createSequentialGroup()
                                            .addComponent(errorMessageSales, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(productListPnlLayout.createSequentialGroup()
                                            .addComponent(productSalesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))))
                        .addGroup(productListPnlLayout.createSequentialGroup()
                            .addGap(126, 126, 126)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(productListPnlLayout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessageStatus))))
                    .addGap(864, 864, 864))
                .addGroup(productListPnlLayout.createSequentialGroup()
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(productListPnlLayout.createSequentialGroup()
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessageProdCode))
                            .addGap(18, 18, 18)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessageName)))
                        .addComponent(successfulMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(productListPnlLayout.createSequentialGroup()
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessagePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productStockTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessageStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(47, 47, 47)
                            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(errorMessageCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(productInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))))
    );
    productListPnlLayout.setVerticalGroup(
        productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(productListPnlLayout.createSequentialGroup()
            .addGap(15, 15, 15)
            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(productInfoLabel)
                .addComponent(successfulMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(41, 41, 41)
            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(errorMessageProdCode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorMessagePrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorMessageName))
                .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorMessageStock, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorMessageCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(productCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(productPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(productStockTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(productCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(productListPnlLayout.createSequentialGroup()
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(errorMessageDate, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(errorMessageSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(errorMessageWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(productWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productSalesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 15, Short.MAX_VALUE)
                    .addGroup(productListPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12))
                .addGroup(productListPnlLayout.createSequentialGroup()
                    .addComponent(errorMessageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    productSalesTextField.getAccessibleContext().setAccessibleName("Customizable");

    prodOverviewPnl.setBackground(new java.awt.Color(242, 240, 231));

    overallSalesLabel.setFont(new java.awt.Font("Hiragino Sans GB", 0, 20)); // NOI18N
    overallSalesLabel.setText("Total Sales:");

    overallStockLabel.setFont(new java.awt.Font("Hiragino Sans", 1, 20)); // NOI18N
    overallStockLabel.setText("Total Stock:");

    itemsSoldLabel.setFont(new java.awt.Font("Hiragino Sans", 0, 20)); // NOI18N
    itemsSoldLabel.setText("Total Item Sold:");

    totalSalesLabel.setFont(new java.awt.Font("Kannada MN", 0, 18)); // NOI18N
    totalSalesLabel.setText("jLabel14");

    totalStockLevel.setFont(new java.awt.Font("Kannada Sangam MN", 0, 18)); // NOI18N
    totalStockLevel.setText("jLabel45");

    totalItemSoldLabel.setFont(new java.awt.Font("Kannada MN", 0, 18)); // NOI18N
    totalItemSoldLabel.setText("jLabel56");

    prodOverviewLabel.setFont(new java.awt.Font("InaiMathi", 1, 27)); // NOI18N
    prodOverviewLabel.setText("Product Overview");

    javax.swing.GroupLayout prodOverviewPnlLayout = new javax.swing.GroupLayout(prodOverviewPnl);
    prodOverviewPnl.setLayout(prodOverviewPnlLayout);
    prodOverviewPnlLayout.setHorizontalGroup(
        prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(prodOverviewPnlLayout.createSequentialGroup()
            .addGap(19, 19, 19)
            .addGroup(prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, prodOverviewPnlLayout.createSequentialGroup()
                    .addComponent(itemsSoldLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(totalItemSoldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(prodOverviewPnlLayout.createSequentialGroup()
                    .addGroup(prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(overallStockLabel)
                        .addComponent(overallSalesLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(totalSalesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                        .addComponent(totalStockLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        .addGroup(prodOverviewPnlLayout.createSequentialGroup()
            .addGap(37, 37, 37)
            .addComponent(prodOverviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    prodOverviewPnlLayout.setVerticalGroup(
        prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(prodOverviewPnlLayout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addComponent(prodOverviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(overallSalesLabel)
                .addComponent(totalSalesLabel))
            .addGap(35, 35, 35)
            .addGroup(prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(overallStockLabel)
                .addComponent(totalStockLevel))
            .addGap(53, 53, 53)
            .addGroup(prodOverviewPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(itemsSoldLabel)
                .addComponent(totalItemSoldLabel))
            .addGap(70, 70, 70))
    );

    jComboBox1.setBackground(new java.awt.Color(134, 103, 83));
    jComboBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort By", "Name - Ascending", "Name - Descending", "Price - Ascending", "Price - Descending", "Stock - Ascending", "Stock - Descending" }));
    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
        }
    });

    searchButton.setBackground(new java.awt.Color(134, 103, 83));
    searchButton.setForeground(new java.awt.Color(255, 255, 255));
    searchButton.setText("Search");
    searchButton.setBorder(null);
    searchButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            searchButtonActionPerformed(evt);
        }
    });

    searchField.setBackground(new java.awt.Color(225, 223, 208));
    searchField.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    searchField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            searchFieldActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout adminCtrlPnlLayout = new javax.swing.GroupLayout(adminCtrlPnl);
    adminCtrlPnl.setLayout(adminCtrlPnlLayout);
    adminCtrlPnlLayout.setHorizontalGroup(
        adminCtrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(adminCtrlPnlLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(adminCtrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(adminCtrlPnlLayout.createSequentialGroup()
                    .addGroup(adminCtrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(craftTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(adminCtrlPnlLayout.createSequentialGroup()
                            .addComponent(productListPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(prodOverviewPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(10, 10, 10)))
                    .addContainerGap())
                .addGroup(adminCtrlPnlLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28))))
    );
    adminCtrlPnlLayout.setVerticalGroup(
        adminCtrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(adminCtrlPnlLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(adminCtrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(jComboBox1)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(craftTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(adminCtrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(productListPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prodOverviewPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(129, 129, 129))
    );

    jTabbedPane1.addTab("Admin Control", adminCtrlPnl);

    javax.swing.GroupLayout mainScreenBodyPnlLayout = new javax.swing.GroupLayout(mainScreenBodyPnl);
    mainScreenBodyPnl.setLayout(mainScreenBodyPnlLayout);
    mainScreenBodyPnlLayout.setHorizontalGroup(
        mainScreenBodyPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
    );
    mainScreenBodyPnlLayout.setVerticalGroup(
        mainScreenBodyPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainScreenBodyPnlLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout panelMainScreenLayout = new javax.swing.GroupLayout(panelMainScreen);
    panelMainScreen.setLayout(panelMainScreenLayout);
    panelMainScreenLayout.setHorizontalGroup(
        panelMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(mainScreenTitlePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(mainScreenBodyPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    panelMainScreenLayout.setVerticalGroup(
        panelMainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMainScreenLayout.createSequentialGroup()
            .addComponent(mainScreenTitlePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(mainScreenBodyPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(225, 223, 208));
    setBounds(new java.awt.Rectangle(0, 25, 1392, 768));
    setMinimumSize(new java.awt.Dimension(1392, 748));
    setResizable(false);
    setSize(new java.awt.Dimension(1392, 768));

    loadingScreen.setBackground(new java.awt.Color(233, 220, 205));
    loadingScreen.setMaximumSize(new java.awt.Dimension(1392, 748));
    loadingScreen.setMinimumSize(new java.awt.Dimension(1392, 748));
    loadingScreen.setPreferredSize(new java.awt.Dimension(1392, 748));
    loadingScreen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel11.setFont(new java.awt.Font("Herculanum", 0, 48)); // NOI18N
    jLabel11.setText("UMBRA CERAMICS");
    loadingScreen.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 490, -1));
    loadingScreen.add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 500, 400, 21));
    loadingScreen.add(progressBarNaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, 140, 30));

    loadingPgImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/craft/resources/Screenshot 2024-12-25 at 12.48.26 AM.png"))); // NOI18N
    loadingScreen.add(loadingPgImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, -30, 1390, 810));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(loadingScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(loadingScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
//logs out from the system
    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (option == JOptionPane.YES_OPTION) {
            loadScreen("loginScreen");
        }

    }//GEN-LAST:event_logOutButtonActionPerformed

    private void productCodeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCodeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productCodeTextFieldActionPerformed
//add product into the system
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        boolean valid1 = validation();
        // Check if product code already exists
        if (!checkExistingValue(productCodeTextField.getText())) {
            if (valid1) {
                CraftModel addedProd = new CraftModel(productCodeTextField.getText(), productNameTextField.getText(), Float.parseFloat(productPriceTextField.getText()), Integer.parseInt(productStockTextField.getText()), productCategoryComboBox.getSelectedItem().toString(), Integer.parseInt(productSalesTextField.getText()), productStatusComboBox.getSelectedItem().toString(), productDateTextField.getText(), Float.parseFloat(productWeightTextField.getText())); 
                addProduct(addedProd);// Add the product to the system
                successfulMessage.setText("Product is added Successfully");
                productOverview();
                System.out.println("Original Craft List from add button: " + craftList);
            }
        } else {
            resetValidation();
            dialogMessage("the code already exists", "ERROR");
        }
    }//GEN-LAST:event_addButtonActionPerformed
//update product of the system
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed

        boolean valid2 = validation();
        if (!productCodeTextField.getText().isEmpty()) {
            if (checkExistingValue(productCodeTextField.getText())) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    if (table.getValueAt(i, 0).toString().equals(productCodeTextField.getText())) {
                        if (valid2) {
                            craftList.set(i, new CraftModel(productCodeTextField.getText(), productNameTextField.getText(), Float.parseFloat(productPriceTextField.getText()), Integer.parseInt(productStockTextField.getText()),
                                    productCategoryComboBox.getSelectedItem().toString(), Integer.parseInt(productSalesTextField.getText()),
                                    productStatusComboBox.getSelectedItem().toString(),
                                    productDateTextField.getText(),
                                    Float.parseFloat(productWeightTextField.getText())
                            ));
                            System.out.println("Original Craft List from update button: " + craftList);
                            table.setValueAt(productCodeTextField.getText(), i, 0);
                            table.setValueAt(productNameTextField.getText(), i, 1);
                            table.setValueAt(Float.parseFloat(productPriceTextField.getText()), i, 2);
                            table.setValueAt(productStockTextField.getText(), i, 3);
                            table.setValueAt(productCategoryComboBox.getSelectedItem(), i, 4);
                            table.setValueAt(productSalesTextField.getText(), i, 5);
                            table.setValueAt(productStatusComboBox.getSelectedItem(), i, 6);
                            table.setValueAt(productDateTextField.getText(), i, 7);
                            table.setValueAt(Float.parseFloat(productWeightTextField.getText()), i, 8);
                            successfulMessage.setText("Product is updated Successfully");
                            System.out.println("Original Craft List from add button: " + craftList);
                            productOverview();
                            break;
                        }
                    }
                }

            } else {
                resetValidation();
                dialogMessage("The product code doesn't exist", "ERROR");
            }
        } else {
            errorMessageProdCode.setText("product code is empty");
        }

    }//GEN-LAST:event_updateButtonActionPerformed

    private void jPasswordField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField3ActionPerformed
//login users into the system
    private void loginButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButton1ActionPerformed
        // TODO add your handling code here:
        if (checkCredentials()) {
            loadScreen("mainScreen");
            jPasswordField3.setText("");
            usernameTextField.setText("");
        }


    }//GEN-LAST:event_loginButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void productSalesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productSalesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productSalesTextFieldActionPerformed

    private void productDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productDateTextFieldActionPerformed

    private void productCategoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCategoryComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productCategoryComboBoxActionPerformed
//delete products from the system
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

        int option = dialogConfirm("Are you sure you want to delete?", "Delete");

        if (!productCodeTextField.getText().isEmpty()) {
            if (option == JOptionPane.YES_OPTION) {
                String productCode = productCodeTextField.getText();
                if (checkExistingValue(productCode)) {

                    // Iterate over the table rows to find the matching product code
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if (table.getValueAt(i, 0).toString().equals(productCode)) {
                            craftList.remove(i);
                            DefaultTableModel model = (DefaultTableModel) craftTable.getModel();
                            model.removeRow(i);
                            productOverview();
                            successfulMessage.setText("Deleted Successfully");
                            break; // Exit the loop
                        }
                    }
                } else {
                    // If the product code doesn't exist, show an error dialog
                    clearData();
                    dialogMessage("The code doesn't exist", "ERROR");
                }
            }
        } else {
            // Show an error if the product code text field is empty
            errorMessageProdCode.setText("Product code is empty");
        }


    }//GEN-LAST:event_deleteButtonActionPerformed
//clear textfields and error messages from the screen
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        int option = dialogConfirm("Are you sure you want to clear?", "Clear");
        if (option == JOptionPane.YES_OPTION) {
            clearData();
            successfulMessage.setText("");
        }
    }//GEN-LAST:event_clearButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed


    }//GEN-LAST:event_sendButtonActionPerformed

    private void nameAboutUsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameAboutUsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameAboutUsTextFieldActionPerformed

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void subjectTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectTextFieldActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        String selectedOption = (String) jComboBox1.getSelectedItem();

        switch (selectedOption) {
            case "Name - Ascending":
                System.out.println("Original Craft List: " + craftList);

                List<CraftModel> sortedListByNameAsc = insertionSort.sortByName(craftList, true);
                updateTable(sortedListByNameAsc);
                break;
            case "Name - Descending":
                List<CraftModel> sortedListByNameDesc = insertionSort.sortByName(craftList, false);
                updateTable(sortedListByNameDesc);
                break;
            case "Price - Ascending":
                List<CraftModel> sortedListByPriceAsc = merge.sortByPrice(craftList, true);
                updateTable(sortedListByPriceAsc);
                break;
            case "Price - Descending":
                List<CraftModel> sortedListByPriceDesc = merge.sortByPrice(craftList, false);
                updateTable(sortedListByPriceDesc);
                break;
            case "Stock - Ascending":
                List<CraftModel> sortedListByStockAsc = selectionSort.sortByStock(craftList, false);
                updateTable(sortedListByStockAsc);
                break;
            case "Stock - Descending":
                List<CraftModel> sortedListByStockDesc = selectionSort.sortByStock(craftList, true);
                updateTable(sortedListByStockDesc);
                break;
            default:
                break;
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        // Sort the craft list by name in ascending order
        List<CraftModel> sortedListByName = insertionSort.sortByName(craftList, true);

        String targetName = searchField.getText().trim();

        if (targetName.isEmpty()) {
            System.out.println("Please enter a valid name to search.");
            return;
        }
        // Find matching items with partial match
        List<CraftModel> matchingItems = new ArrayList<>();
        for (CraftModel item : sortedListByName) {
            if (item.getName() != null && item.getName().toLowerCase().equals(targetName.toLowerCase())) {
                matchingItems.add(item);
            }
        }
        if (!matchingItems.isEmpty()) {
            System.out.println("Found " + matchingItems.size() + " items containing name: " + targetName);
            sortedListByName.removeAll(matchingItems);
            sortedListByName.addAll(0, matchingItems);
            updateTable(sortedListByName);
        } else {
            System.out.println("No items containing name: " + targetName + " found.");
        }


    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void updateTable(List<CraftModel> sortedList) {
        // DefaultTableModel to populate the JTable
        DefaultTableModel model = (DefaultTableModel) craftTable.getModel();

        // Clear the table before populating with new data
        model.setRowCount(0);

        // Populate the table with data from the sorted list
        for (CraftModel product : sortedList) {
            model.addRow(new Object[]{
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getTotalSales(),
                product.getStatus(),
                product.getDateCreated(),
                product.getWeight()
            });
        }
        craftTable.revalidate();
        craftTable.repaint();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Craft_23048590DixitaBajracharya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Craft_23048590DixitaBajracharya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Craft_23048590DixitaBajracharya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Craft_23048590DixitaBajracharya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Craft_23048590DixitaBajracharya().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aboutUsPnl;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel adminCtrlPnl;
    private javax.swing.JButton clearButton;
    private javax.swing.JTable craftTable;
    private javax.swing.JPanel craftTablePanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel emailErrorLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel errorMessageCategory;
    private javax.swing.JLabel errorMessageDate;
    private javax.swing.JLabel errorMessageName;
    private javax.swing.JLabel errorMessagePrice;
    private javax.swing.JLabel errorMessageProdCode;
    private javax.swing.JLabel errorMessageSales;
    private javax.swing.JLabel errorMessageStatus;
    private javax.swing.JLabel errorMessageStock;
    private javax.swing.JLabel errorMessageWeight;
    private javax.swing.JLabel errorMismatch;
    private javax.swing.JLabel forgetPassLabel;
    private javax.swing.JLabel getStartedLabel;
    private javax.swing.JPanel getStartedPnl;
    private javax.swing.JPanel homePnl;
    private javax.swing.JLabel itemsSoldLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel loadingPgImage;
    private javax.swing.JPanel loadingScreen;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton loginButton1;
    private javax.swing.JLabel loginImgOverall;
    private javax.swing.JLabel logoImgLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel mailImg;
    private javax.swing.JPanel mainScreenBodyPnl;
    private javax.swing.JPanel mainScreenTitlePnl;
    private javax.swing.JLabel messageErrorLabel;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTextField nameAboutUsTextField;
    private javax.swing.JLabel nameErrorLable;
    private javax.swing.JLabel overallSalesLabel;
    private javax.swing.JLabel overallStockLabel;
    private javax.swing.JPanel panelLoginScreen;
    private javax.swing.JPanel panelMainScreen;
    private javax.swing.JPanel passwordPanel;
    private javax.swing.JLabel passwordTextField;
    private javax.swing.JLabel prodOverviewLabel;
    private javax.swing.JPanel prodOverviewPnl;
    private javax.swing.JComboBox<String> productCategoryComboBox;
    private javax.swing.JTextField productCodeTextField;
    private javax.swing.JTextField productDateTextField;
    private javax.swing.JLabel productInfoLabel;
    private javax.swing.JPanel productListPnl;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JTextField productPriceTextField;
    private javax.swing.JTextField productSalesTextField;
    private javax.swing.JComboBox<String> productStatusComboBox;
    private javax.swing.JTextField productStockTextField;
    private javax.swing.JTextField productWeightTextField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel progressBarNaming;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel subjectErrorLabel;
    private javax.swing.JTextField subjectTextField;
    private javax.swing.JLabel successfulMessage;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalItemSoldLabel;
    private javax.swing.JLabel totalSalesLabel;
    private javax.swing.JLabel totalStockLevel;
    private javax.swing.JButton updateButton;
    private javax.swing.JPanel usernamePassword;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
