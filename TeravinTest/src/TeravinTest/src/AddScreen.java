import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddScreen {
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
	
	public void updateTable(String query){
		
		connect();
		try {
			stat.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private JDesktopPane dp;
	private Container contain;
	private JLabel Title;
	private Dimension a;
	private JLabel NamaLbl;
	private JTextField NamaTxt;
	private JLabel HpLbl;
	private JTextField HpTxt;
	private JLabel EmailLbl;
	private JTextField EmailTxt;
	private JLabel AlamatLbl;
	private JTextField AlamatTXt;
	private JButton PlusBtn;
	private JButton CreateBtn;
	private JButton BackBtn;
	private JPanel Top;
	private JPanel Center;
		private JPanel Top2;
		private JPanel Center2;
		private GridBagConstraints Gbc;
			private JPanel Nm1;
			private JPanel Nm2;
			private JPanel Hp1;
			private JPanel Hp2;
			private JPanel Em1;
			private JPanel Em2;
			private JPanel Al1;
			private JPanel Al2;
		private JPanel Bottom2;
	private JPanel Bottom;
	
	
	public AddScreen(){
		
		JFrame AddScreenFrame = new JFrame();
		AddScreenFrame.setTitle("Add Screen");
		AddScreenFrame.setBounds(100, 100, 450, 500);
		AddScreenFrame.setLocationRelativeTo(null);
		AddScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		dp = new JDesktopPane();
		
		contain = AddScreenFrame.getContentPane();
		contain.setLayout(new BorderLayout());
		contain.add(dp);
		
		//item
		Top = new JPanel();
		Top.setLayout(new FlowLayout(FlowLayout.LEFT));
		contain.add(Top,BorderLayout.NORTH);
		
			Title = new JLabel("CREATE USER");
			Top.add(Title);
			
		Center = new JPanel();
		contain.add(Center,BorderLayout.CENTER);
		
			Top2 = new JPanel();
			Center.add(Top2,BorderLayout.NORTH);
			
			Center2 = new JPanel();
			Center2.setLayout(new GridBagLayout());
			Gbc = new GridBagConstraints();
			Center.add(Center2,BorderLayout.CENTER);
		
				NamaLbl = new JLabel("NAMA");
				Nm1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Nm1.add(NamaLbl);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=0;
				Gbc.gridy=0;
				Center2.add(Nm1,Gbc);
				
				NamaTxt = new JTextField();				
				a = NamaTxt.getPreferredSize();
				a.width= 150;
				NamaTxt.setPreferredSize(a);
				Nm2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Nm2.add(NamaTxt);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=1;
				Gbc.gridy=0;
				Center2.add(Nm2,Gbc);
				
				HpLbl = new JLabel("No. HP");
				Hp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Hp1.add(HpLbl);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=0;
				Gbc.gridy=1;
				Center2.add(Hp1,Gbc);
				
				HpTxt = new JTextField();
				a = HpTxt.getPreferredSize();
				a.width= 150;
				HpTxt.setPreferredSize(a);
				Hp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Hp2.add(HpTxt);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=1;
				Gbc.gridy=1;
				Center2.add(Hp2,Gbc);
				
				EmailLbl = new JLabel("EMAIL");
				Em1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Em1.add(EmailLbl);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=0;
				Gbc.gridy=2;
				Center2.add(Em1,Gbc);
				
				EmailTxt = new JTextField();
				a = EmailTxt.getPreferredSize();
				a.width= 150;
				EmailTxt.setPreferredSize(a);
				Em2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Em2.add(EmailTxt);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=1;
				Gbc.gridy=2;
				Center2.add(Em2,Gbc);
				
				AlamatLbl = new JLabel("ALAMAT");
				Al1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Al1.add(AlamatLbl);
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.anchor = GridBagConstraints.FIRST_LINE_START;
				Gbc.gridx=0;
				Gbc.gridy=3;
				Center2.add(Al1,Gbc);
				
				Al2 = new JPanel();
				Al2.setLayout(new BoxLayout(Al2, BoxLayout.Y_AXIS));
				Gbc.fill=GridBagConstraints.HORIZONTAL;
				Gbc.gridx=1;
				Gbc.gridy=3;
				Center2.add(Al2,Gbc);
					JPanel panel = new JPanel();
					AlamatTXt = new JTextField();
					a = AlamatTXt.getPreferredSize();
					a.width= 150;
					AlamatTXt.setPreferredSize(a);
					panel.add(AlamatTXt);
					
					PlusBtn = new JButton("+");
					panel.add(PlusBtn);
					
					Al2.add(panel);
			
			Bottom2 = new JPanel();
			Bottom2.setLayout(new FlowLayout(FlowLayout.RIGHT));
			Center.add(Bottom2,BorderLayout.SOUTH);
			
				
			
		
		Bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		contain.add(Bottom,BorderLayout.SOUTH);
		
			CreateBtn = new JButton("CREATE");
			Bottom.add(CreateBtn);
			
			BackBtn = new JButton("BACK");
			Bottom.add(BackBtn);	
		
		AddScreenFrame.setVisible(true);
		
		//action
		
		PlusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==PlusBtn){
					JPanel panel = new JPanel();
					JTextField newAlamat = new JTextField();
					a = newAlamat.getPreferredSize();
					a.width= 150;
					newAlamat.setPreferredSize(a);
					JButton minBtn = new JButton("-");
					minBtn.addMouseListener(new listener());
					
					panel.add(newAlamat);
					panel.add(minBtn);
					
					Al2.add(panel);
					
					Al2.revalidate();
					Al2.repaint();					
				}			
			}
			
			class listener extends MouseAdapter {
				
				public void mouseClicked(MouseEvent e){
					JButton select = (JButton)e.getSource();
					JPanel panel = (JPanel) select.getParent();
					Al2.remove(panel);
					Al2.revalidate();
					Al2.repaint();
				}
			}
			
		});
		
		CreateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//validasi
				String nama = NamaTxt.getText();
				String hp = HpTxt.getText().trim();
				JOptionPane.showMessageDialog(null, hp);
				String email = EmailTxt.getText();
				
				boolean flagAlamat = true;
				String alamat2[]=new String[10];
				Component[] al2Comp = Al2.getComponents();
				for (int i = 0; i < al2Comp.length; i++) {
					if(al2Comp[i]instanceof JTextField)
					{
						alamat2[i] = ((JTextField)al2Comp[i]).getText();
						if(alamat2[i].equals("")){
							flagAlamat=false;
						}
						else{
							
						}
					}
				}			
				
				boolean flag = true;
				try {
					JOptionPane.showMessageDialog(null, "whhy");
					int a = Integer.parseInt(hp);
					JOptionPane.showMessageDialog(null, a);
				} catch (Exception e2) {
					flag=false;
				}
				
				if (nama.equals("")||hp.equals("")||email.equals("")) {
					JOptionPane.showMessageDialog(null, "Semua Field Harus Diisi!");
				}
				else if(flagAlamat==false) {
					JOptionPane.showMessageDialog(null, "Semua Field Alamat Harus Diisi!");
				}
				else if(nama.length()>50) {
					JOptionPane.showMessageDialog(null, "Nama Max 50 Karakter");
				}
				else if(hp.length()<10||flag==false){
					JOptionPane.showMessageDialog(null, "No. HP Min 10 Karakter dan Harus Numeric");
				}
				else if(email.contains("@")==false||email.contains(".")==false||email.indexOf(".")-email.indexOf("@")<2){
					JOptionPane.showMessageDialog(null, "Email tidak sesuai format");
				}
				else{
					try {
						String id="1";
						rs = readTable("SELECT UserID from ListUser Order By UserID desc");
						
						if(!rs.next()){								
							updateTable("INSERT into ListUser VALUES ('"+id+"','"+nama+"','"+hp+"','"+email+"')");
							for (int i = 0; i < alamat2.length; i++) {
								updateTable("INSERT into ListAlamat VALUES ('"+id+"','"+alamat2[i]+"')");
							}
						}
						else{
							String idAkhir = rs.getString(1);
							int intID = Integer.parseInt(idAkhir);
							intID++;
							String idBaru= Integer.toString(intID);
							
							updateTable("INSERT into UserID VALUES ('"+idBaru+"','"+nama+"','"+hp+"','"+email+"')");
							for (int i = 0; i < alamat2.length; i++) {
								updateTable("INSERT into ListAlamat VALUES ('"+id+"','"+alamat2[i]+"')");
							}
												
						}
						JOptionPane.showMessageDialog(null,"Success");
						ListScreen ListScreen = new ListScreen();
						AddScreenFrame.dispose();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}					
					
					
				}
			}
		});
		
		BackBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ListScreen ListScreen = new ListScreen();
				AddScreenFrame.dispose();
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddScreen();
		
	}

}
