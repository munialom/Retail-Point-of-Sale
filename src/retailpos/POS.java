/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailpos;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import util.Connect;

/**
 *
 * @author INFOTECH
 */
public class POS extends javax.swing.JFrame {
    DefaultListModel model = new DefaultListModel();
    DefaultListModel md = new DefaultListModel();
    Connection con=null;
    ResultSet rs=null;
   PreparedStatement pst=null;
   static int month, day, hour, minute, second;
    static String monthVar, dayVar, hourVar, minuteVar, secondVar;
                   public String hotel;
                   public String address;
                   public String till;
                   public String phone;
                 

    /**
     * Creates new form TouchScreen
     */
    public POS() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
         setVisible(true);
        setResizable(true);
     
        setCurrentDate();
 
       
       
       
        
        
        
    }

 
  
          
    
    
    
    

      
        

        
    
   
      
        

        
        
        
        
    
        public void addingPanelToContainerPanel(JPanel jPanel) {
        containerPanel.removeAll();
  
        containerPanel.setLayout(new GridBagLayout());
        containerPanel.add(jPanel, new GridBagConstraints());
        containerPanel.add(jPanel);
        jPanel.setOpaque(false);
        jPanel.setVisible(true);
        containerPanel.validate();
        containerPanel.repaint();
    }
    
    
    
    
    /**
     * hotel data management
     */
    
            public  void HotelData(){
            con=Connect.ConnectDB();
    
        try {
            
            String sql="select * from hotel";
            pst=con.prepareStatement(sql);
            rs= pst.executeQuery();
            while (rs.next()) {     
                
                    String add1=rs.getString("HotelName");
                    hotel=add1;
                    String add2=rs.getString("PhoneNo");
                    phone=add2;
                    String add3=rs.getString("Email");
                    address=add3;
                    String add4=rs.getString("Pin");
                    till=add4;
                    
                    
                      }
            
            
        } catch (Exception e) {
            
            
     }}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   /**
    * method to generate auto bill number
    */
    
                            
    
    /**
     * method to set printer
     */
  private void PrintReportToPrinter(JasperPrint jp) throws JRException {
    // TODO Auto-generated method stub
    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
    printRequestAttributeSet.add(new Copies(1));

    PrinterName printerName = new PrinterName("Microsoft Print to PDF", null); //gets printer 

    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
    printServiceAttributeSet.add(printerName);

    JRPrintServiceExporter exporter = new JRPrintServiceExporter();

    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
    exporter.exportReport();
}
    
    
 /**
  * order printing method
  */   
        
  public void PrintOrder(){
  
        HotelData();
           try{
            con=util.Connect.ConnectDB();
            HashMap parameterMap = new HashMap();
            //parameterMap.put("ordernum",billnum.getText()); 
            parameterMap.put("phone",phone); 
            parameterMap.put("address",address); 
            parameterMap.put("hname",hotel); 
            parameterMap.put("till",till); 
            JasperReport rpt = (JasperReport)JRLoader.loadObjectFromFile("../rpts/OrderR.jasper");
            JasperPrint jp = JasperFillManager.fillReport(rpt, parameterMap, con);
    
            PrintReportToPrinter(jp);//call method
            //Report rpt = new Report(parameterMap, con);
            //rpt.setReportName("./rpts/OrderR"); //productlist is the name of my jasper file.
            //rpt.callReport();
            

        }

        catch (Exception e) {
            e.printStackTrace();
        }
  
  }
    
   /**
    * kot data processing 
    */ 
    
      public void PrintOrderK(
      ){
  
           HotelData();
           try{
            con=util.Connect.ConnectDB();
            HashMap parameterMap = new HashMap();
          //  parameterMap.put("ordernum",billnum.getText()); 
            parameterMap.put("phone",phone); 
            parameterMap.put("address",address); 
            parameterMap.put("hname",hotel); 
            //parameterMap.put("till",till); 
            JasperReport rpt = (JasperReport)JRLoader.loadObjectFromFile("../rpts/KOT.jasper");
            JasperPrint jp = JasperFillManager.fillReport(rpt, parameterMap, con);
    
            PrintReportToPrinter(jp);//call method
            //Report rpt = new Report(parameterMap, con);
            //rpt.setReportName("./rpts/OrderR"); //productlist is the name of my jasper file.
            //rpt.callReport();
            

        }

        catch (Exception e) {
            e.printStackTrace();
        }
  
  } 
    
    
    
    
    
    
      public void setCurrentDate() {

        Thread clock = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    Calendar calendar = new GregorianCalendar();
                    month = calendar.get(Calendar.MONTH) + 1;
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    monthVar = (month <= 9) ? ("0" + month) : ("" + month);
                    dayVar = (day <= 9) ? ("0" + day) : ("" + day);
                    //  date12.setText(calendar.get(Calendar.YEAR) + "-" + monthVar + "-" + dayVar);

                    date12.setText("<html> <font color=\'white\'>" + calendar.get(Calendar.YEAR) + '-' + monthVar + '-' + dayVar + "</font> </html>"
                    );

                    hour = calendar.get(Calendar.HOUR);
                    minute = calendar.get(Calendar.MINUTE);
                    second = calendar.get(Calendar.SECOND);

                    hourVar = (hour <= 9) ? ("0" + hour) : (" " + hour);
                    minuteVar = (minute <= 9) ? ("0" + minute) : ("" + minute);
                    secondVar = (second <= 9) ? ("0" + second) : ("" + second);

                    //time12.setText("<html> < body >" + hourVar + ":" + minuteVar + ":" + secondVar + " <  / body > < / html >");
                    time12.setText(hourVar + ":" + minuteVar + ":" + secondVar);
                    time12.setText("<html> <font color=\'white\'>" + hourVar + ':' + minuteVar + ':' + secondVar + "</font> </html>"
                    );

                    try {
                        sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        };
        clock.start();
    }
    
    
    

      
  
      
 
      
 
      
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        shift = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        date12 = new javax.swing.JLabel();
        time12 = new javax.swing.JLabel();
        UserName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        containerPanel.setBackground(new java.awt.Color(255, 255, 255));
        containerPanel.setDoubleBuffered(false);
        containerPanel.setPreferredSize(new java.awt.Dimension(792, 581));

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1373, Short.MAX_VALUE)
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(19, 137, 253));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/menu_38px.png"))); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(3, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SMART POS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/sell_stock_20px.png"))); // NOI18N
        jLabel3.setText("Point of Sale");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/filing_cabinet_20px.png"))); // NOI18N
        jLabel4.setText("Sales Orders");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/box_20px.png"))); // NOI18N
        jLabel5.setText("Inventory");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/truck_20px.png"))); // NOI18N
        jLabel6.setText("Purchases");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/member_20px.png"))); // NOI18N
        jLabel10.setText("Customers");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/supplier_20px.png"))); // NOI18N
        jLabel11.setText("Suppliers");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/accounting_20px.png"))); // NOI18N
        jLabel12.setText("Accounting");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/truck_20px.png"))); // NOI18N
        jLabel13.setText("Warehouse");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/truck_20px.png"))); // NOI18N
        jLabel14.setText("Reports");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(547, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(179, 179, 179))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        shift.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        shift.setForeground(new java.awt.Color(123, 6, 6));
        shift.setText("                         ");
        shift.setMaximumSize(new java.awt.Dimension(200, 14));
        shift.setMinimumSize(new java.awt.Dimension(10, 14));
        shift.setPreferredSize(new java.awt.Dimension(200, 14));

        jPanel1.setBackground(new java.awt.Color(0, 204, 102));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/user_20px.png"))); // NOI18N
        jLabel15.setText("Current User:");

        date12.setForeground(new java.awt.Color(255, 255, 255));

        time12.setForeground(new java.awt.Color(255, 255, 255));

        UserName.setEditable(false);
        UserName.setBackground(new java.awt.Color(0, 204, 51));
        UserName.setForeground(new java.awt.Color(255, 255, 255));
        UserName.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(date12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time12, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(date12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(time12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addComponent(shift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(shift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(629, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        
        
        addingPanelToContainerPanel(new SalesManager());
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        
         addingPanelToContainerPanel(new SalesOrders());
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        
                 addingPanelToContainerPanel(new Inventory());
        
        
        
        
        
        
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
                                        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
            */
            //com.jtattoo.plaf.luna.LunaLookAndFee
            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            
             //javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
                   if (defaults.get("Table.alternateRowColor") == null)
                    defaults.put("Table.alternateRowColor", new Color(188, 255, 235));
            
            
            //</editor-fold>
            
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new POS().setVisible(true);
                    
                    
                }
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(POS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UserName;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JLabel date12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel shift;
    private javax.swing.JLabel time12;
    // End of variables declaration//GEN-END:variables
    public String cone="MAIN DRINKS";
    public String ctwo="MAIN MEALS";
    public String c3="TEA";
    public String c4="SNACKS";


}
