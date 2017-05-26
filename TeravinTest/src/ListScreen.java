import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PUBLIC_MEMBER;



public class ListScreen {
	
	//public Connect con;
	
	Connection conn;
	Statement stat = null;
	ResultSet rs;
	
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Teravin_Test","root","");
			stat = conn.createStatement();
			stat.executeQuery("USE Teravin_Test");
			
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	public ResultSet readTable(String query){
		
		connect();
		try {
			
			rs = stat.executeQuery(query);
			return rs;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	ResultSet IsiTable;
	
	//declare part
	private JFrame ListScreenFrame;
	private JDesktopPane dp;
	private Container contain;
	private JLabel Title;
	private JLabel Content;
	private JButton AddButton;
	private JLabel NamaLbl;
	private JTextField NamaInput;
	private JButton SearchButton;
	private JTable UserTable;
	private JScrollPane sp;
	private DefaultTableModel dtm;
	private JPanel Top;
	private JPanel Center;
		private JPanel Top2;
		private JPanel Center2;
		private JPanel Bottom2;
	private JPanel Bottom;
	
	//fill table
	public Vector<Vector> FillTable(String nama){
		
		Vector<Vector> Content = new Vector<Vector>();
		try {
			ResultSet res = null;
			
			if (nama.equals("")) {
				res = readTable("Select Nama,Email from ListUser");
				
			} else {
				res = readTable("Select Nama,Email from ListUser WHERE Nama like '%"+nama+"%'");
			}
			
			while(res.next()){				
				Vector<String> data= new Vector<String>();
				data.add(res.getString(1));
				data.add(res.getString(2));
				
				Content.add(data);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		return Content;
	}
	
	public ListScreen(){
	
		ListScreenFrame = new JFrame();
		ListScreenFrame.setTitle("List Screen");
		ListScreenFrame.setBounds(100, 100, 450, 500);
		ListScreenFrame.setLocationRelativeTo(null);
		ListScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	dp = new JDesktopPane();
	
	contain = ListScreenFrame.getContentPane();
	contain.setLayout(new BorderLayout());
	contain.add(dp);
	
	// item
	Top = new JPanel();
	Top.setLayout(new FlowLayout(FlowLayout.LEFT));
	contain.add(Top,BorderLayout.NORTH);
	
		Title = new JLabel("LIST USER");
		Top.add(Title);
	
	Center = new JPanel();
	Center.setLayout(new BorderLayout());
	contain.add(Center,BorderLayout.CENTER);
	
		Top2 = new JPanel();
		Center.add(Top2, BorderLayout.NORTH);
		
		Center2 = new JPanel();
		Center.add(Center2, BorderLayout.CENTER);
		
			//test isi db
			ResultSet tes = null;
			boolean flag = false;
			try {
			
				tes = readTable("Select * FROM ListUser");
				if (tes.next()) {
					
					flag = true;
				} else {
					flag = false;
				}
			} catch (Exception e) {}			
			
			if (flag==false) {
				
				Content = new JLabel("<html><center><u>There's Currently No Data Exists<br>Please Create by Clicking the ADD Button Below</u></center></html>");
				Center2.add(Content);
				
			} else {
								
				NamaLbl = new JLabel("NAMA");
				Top2.add(NamaLbl);
				
				NamaInput = new JTextField();
				Dimension a =NamaInput.getPreferredSize();
				a.width = 150;
				NamaInput.setPreferredSize(a);
				Top2.add(NamaInput);
				
				SearchButton = new JButton("Search");
				Top2.add(SearchButton);
				
				Vector<String> head = new Vector<String>();
				head.add("NAMA");
				head.add("EMAIL");
				
				dtm = new DefaultTableModel(FillTable(""),head){
					
					public boolean isCellEditable(int row,int col){
						return false;
					}
				};
				
				UserTable = new JTable(dtm);
				sp= new JScrollPane();
				Dimension t = sp.getPreferredSize();
				t.height=300;
				t.width=400;
				sp.setPreferredSize(t);
				sp.setViewportView(UserTable);
				UserTable.setFillsViewportHeight(true);
				UserTable.getTableHeader().setReorderingAllowed(false);
				UserTable.setAutoCreateRowSorter(true);
				Center2.add(sp);
				
				
			}
		
		Bottom2 = new JPanel();
		Bottom2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		Center.add(Bottom2, BorderLayout.SOUTH);
		
			AddButton = new JButton("ADD");
			Bottom2.add(AddButton);
	
		
	
	Bottom = new JPanel();
	
	contain.add(Bottom,BorderLayout.SOUTH);
	
	//action
	
	SearchButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String name = NamaInput.getText();
			Vector<String> head = new Vector<String>();
			head.add("NAMA");
			head.add("EMAIL");
			dtm.setDataVector(FillTable(name), head);
			UserTable.setModel(dtm);
		}
	});
	
	AddButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			AddScreen addScreen = new AddScreen();
			ListScreenFrame.dispose();
		}
	});	
		
	ListScreenFrame.setVisible(true);
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ListScreen();

	}

}
