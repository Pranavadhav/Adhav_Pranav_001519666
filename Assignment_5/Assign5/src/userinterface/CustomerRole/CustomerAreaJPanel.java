/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.CustomerRole;

import Business.Customer.Customer;
import Business.Customer.CustomerDirectory;
import Business.DeliveryMan.DeliveryManDirectory;
import Business.EcoSystem;
import Business.Item.Item;
import Business.Menu.Menu;
import Business.Menu.MenuDirectory;
import Business.Order.Order;
import Business.Order.OrderDirectory;
import Business.Restaurant.Restaurant;
import Business.Restaurant.RestaurantDirectory;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkRequest;
import com.db4o.collections.ActivatableArrayList;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import static sun.net.www.MimeTable.loadTable;

/**
 *
 * @author raunak
 */
public class CustomerAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem ecoSystem;
    private UserAccount account;
    private CustomerDirectory customerDirectory;
    private RestaurantDirectory restaurantDirectory;
    private DeliveryManDirectory deliveryManDirectory;
    private MenuDirectory menuDirectory;
    private OrderDirectory orderDirectory;
    private static int count = 1;
    
     ArrayList<Item> arrayListItems = new ActivatableArrayList<>();
   
     
    public CustomerAreaJPanel(JPanel userProcessContainer, UserAccount account, EcoSystem ecoSystem, 
            CustomerDirectory customerDirectory, RestaurantDirectory restaurantDirectory, 
            DeliveryManDirectory deliveryManDirectory, MenuDirectory menuDirectory, OrderDirectory orderDirectory) {
        initComponents();
        
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.ecoSystem = ecoSystem;
        this.customerDirectory = ecoSystem.getCustomerDirectory();
        this.restaurantDirectory = ecoSystem.getRestaurantDirectory();
        this.menuDirectory = ecoSystem.getMenuDirectory();
        this.deliveryManDirectory = ecoSystem.getDeliveryManDirectory();
        this.orderDirectory = ecoSystem.getOrderDirectory();
        valueLabel.setText(account.getUsername());
        populateRequestTable();
        populateRestaurantCombo();
        
        
    }
    
    public void populateTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblItem.getModel();
        dtm.setRowCount(0);
        String restaurantName = boxRestaurant.getSelectedItem().toString();
        Restaurant restaurant = ecoSystem.getRestaurantDirectory().getRestaurant(restaurantName);
        for(Menu menu : ecoSystem.getMenuDirectory().getMenuDirectory()){
            if(restaurant.getRestaurantName().equals(menu.getRestaurantName())) {
                Object [] row = new Object[2];
                row[0] = menu;
                row[1] = menu.getPrice();
                dtm.addRow(row);
            }
        }
    }
    
    public void populateRestaurantCombo() {
        boxRestaurant.removeAllItems();
        boxRestaurant.addItem("  ");
        for(Restaurant res : ecoSystem.getRestaurantDirectory().getRestaurantDirectory()) {
            //System.out.println("res" + res);
            boxRestaurant.addItem(res.getRestaurantName());
        }
    }
    
    public void populateRequestTable(){
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
        model.setRowCount(0);
        for (Order order : ecoSystem.getOrderDirectory().getOrderDirectory()){
            //System.out.println("Order" + order.getOrderId());
            if(account.getEmployee().getName().equals(order.getCustomer().getName())) {
                Object[] row = new Object[8];
                row[0] = order;
                row[1]=order.getMessage();
                row[2] = order.getCustomer().getName();
                row[3] = order.getOrderStatus();
                row[4] = order.getQuantity();
                model.addRow(row);
            }
        }
    }
      private void displayCartTable() {
        DefaultTableModel dtm = (DefaultTableModel) carttable.getModel();
        dtm.setRowCount(0);
        for (Item item : arrayListItems) {
            Object[] row = new Object[3];
            row[0] = item.getItemname();
            row[1] = item.getQty();
            row[2] = item.getQty() * item.getPrice();
            dtm.addRow(row);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void calTotalPrice() {
        double total = 0.0;
        for (Item item : arrayListItems) {
            total = item.getPrice() * item.getQty() + total;
            totalPriceJTextField.setText(String.valueOf(total));
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestTestJButton = new javax.swing.JButton();
        refreshTestJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        boxRestaurant = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtComment = new javax.swing.JTextField();
        btnMenuShow = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        carttable = new javax.swing.JTable();
        confirm = new javax.swing.JButton();
        totalPriceJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();

        requestTestJButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        requestTestJButton.setText("comment");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });

        refreshTestJButton.setText("Refresh");
        refreshTestJButton.setBorderPainted(false);
        refreshTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTestJButtonActionPerformed(evt);
            }
        });

        enterpriseLabel.setBackground(new java.awt.Color(255, 255, 255));
        enterpriseLabel.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        enterpriseLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        enterpriseLabel.setText("you are logged in as ");

        valueLabel.setBackground(new java.awt.Color(255, 255, 255));
        valueLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        valueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        valueLabel.setText("<value>");
        valueLabel.setAlignmentY(0.0F);
        valueLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jScrollPane2.setBorder(null);

        tblItem.setForeground(new java.awt.Color(109, 109, 109));
        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Item Name", "Item Price"
            }
        ));
        tblItem.setGridColor(new java.awt.Color(255, 255, 255));
        tblItem.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblItem.setRowHeight(20);
        jScrollPane2.setViewportView(tblItem);

        boxRestaurant.setForeground(new java.awt.Color(109, 109, 109));
        boxRestaurant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxRestaurant.setBorder(null);
        boxRestaurant.setOpaque(false);
        boxRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxRestaurantActionPerformed(evt);
            }
        });

        jLabel1.setText("Quantity:");

        txtQuantity.setForeground(new java.awt.Color(51, 51, 51));
        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });

        jLabel2.setText("Comment:");

        txtComment.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        btnMenuShow.setText("Restaurant Menu");
        btnMenuShow.setBorderPainted(false);
        btnMenuShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuShowActionPerformed(evt);
            }
        });

        add.setText("Add Item");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        carttable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        carttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Item Name", "Quantity", "TotalPrice"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        carttable.setToolTipText("");
        carttable.setShowVerticalLines(false);
        jScrollPane3.setViewportView(carttable);

        confirm.setText("Confirm Order");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        totalPriceJTextField.setEditable(false);

        jLabel3.setText("Total:");

        workRequestJTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Message", "Receiver", "Status", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(requestTestJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(29, 29, 29)
                            .addComponent(totalPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(67, 67, 67)
                            .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(128, 128, 128)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(boxRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(479, 479, 479)
                                .addComponent(btnMenuShow, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(refreshTestJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane1)))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterpriseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshTestJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuShow, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(totalPriceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(requestTestJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed

        if(txtComment.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Field cannot be empty");
            return;
        }
        
        int selectedRow = workRequestJTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(null,"Please Select a row from table first", "Warining", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Order order = (Order)workRequestJTable.getValueAt(selectedRow, 0);
        order.setMessage(txtComment.getText());
        populateRequestTable();
    }//GEN-LAST:event_requestTestJButtonActionPerformed

    private void refreshTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTestJButtonActionPerformed

        populateRequestTable();
        
    }//GEN-LAST:event_refreshTestJButtonActionPerformed

    private void boxRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxRestaurantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxRestaurantActionPerformed

    private void btnMenuShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuShowActionPerformed
        // TODO add your handling code here:
        if(boxRestaurant.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a restaurant");
            return;
        }
        populateTable();
    }//GEN-LAST:event_btnMenuShowActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        boolean flag;
        int selectedRow = tblItem.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please Select a row from table first", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter quantity");
            return;
        }

        flag = txtQuantity.getText().matches("^[0-9]+$");
        if (!flag) {
            JOptionPane.showMessageDialog(null, "Please enter digits only");
            return;
        }

        int quantity = Integer.parseInt(txtQuantity.getText());
        if (quantity > 0) {
            Menu menu = (Menu) tblItem.getValueAt(selectedRow, 0);
            int qty = Integer.parseInt(txtQuantity.getText());
            Item item = new Item(menu.getItemName(), menu.getPrice(), qty);
            arrayListItems.add(item);
            displayCartTable();
            calTotalPrice();

        }
    }//GEN-LAST:event_addActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here:

        Random random = new Random();
        int randNumber = random.nextInt(200000 - 100000 + 1) + 100000;
        int rowcount = carttable.getRowCount();
        if (rowcount > 0) {
            int quantity = Integer.parseInt(txtQuantity.getText());
            String restaurantName = boxRestaurant.getSelectedItem().toString();
            Restaurant restaurant = ecoSystem.getRestaurantDirectory().getRestaurant(restaurantName);
            Customer customer = ecoSystem.getCustomerDirectory().getCustomer(account.getEmployee().getName());
            //Menu menu = (Menu) tblItem.getValueAt(selectedRow, 0);
            String status = "Order Placed";

            Order order = ecoSystem.getOrderDirectory().newOrder();
            order.setCustomer(customer);
            order.setOrderId(String.valueOf(randNumber));
            order.setRestaurant(restaurant);
            order.setOrderStatus(status);
            order.setAssign(false);

            double totalPrice = 0.0;
            StringBuilder menuItems = new StringBuilder();
            for (Item item : arrayListItems) {
                totalPrice = item.getPrice() * item.getQty() + totalPrice;
                menuItems.append(" "+item.getItemname()+ ":"+ (item.getQty())+ ",");
            }
            order.setQuantity((int) totalPrice);
            // order.setMenu(menu);

            JOptionPane.showMessageDialog(null,"You have placed an order");
            loadTable();
            DefaultTableModel cartModel = (DefaultTableModel) carttable.getModel();
            cartModel.setRowCount(0);
            totalPriceJTextField.setText("");
            arrayListItems = new ActivatableArrayList<>();
            populateRequestTable();
        } else {
            JOptionPane.showMessageDialog(null, "your cart is empty", "warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_confirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> boxRestaurant;
    private javax.swing.JButton btnMenuShow;
    private javax.swing.JTable carttable;
    private javax.swing.JButton confirm;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton refreshTestJButton;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField totalPriceJTextField;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
