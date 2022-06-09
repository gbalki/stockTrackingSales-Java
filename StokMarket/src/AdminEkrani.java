import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AdminEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField kullaniciAlani;
	private JPasswordField parolaAlani;
	private int say;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminEkrani frame = new AdminEkrani();
	
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminEkrani() {
		
		Database.baglanti();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel uyariMesaji = new JLabel("");
		uyariMesaji.setBounds(461, 272, 264, 21);
		uyariMesaji.setForeground(Color.RED);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(461, 308, 264, 34);
		progressBar.setStringPainted(true);
		
		kullaniciAlani = new JTextField();
		kullaniciAlani.setBounds(461, 78, 264, 28);
		kullaniciAlani.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblNewLabel.setBounds(338, 80, 98, 21);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setBounds(338, 139, 98, 21);
		lblParola.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		
		JButton girisbutonu = new JButton("Giri\u015F");
		girisbutonu.setBounds(461, 227, 98, 34);
		Image btnIco=new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		girisbutonu.setIcon(new ImageIcon(btnIco));
		girisbutonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Timer timer=new Timer(5,null);
				
				timer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						say+=3;
						progressBar.setValue(say);
					
						if(progressBar.getValue()==100) {
							
							String ad=kullaniciAlani.getText();
							String parola=new String(parolaAlani.getPassword());
							 
					
						    
						    
							boolean girisbasarili=Database.kullanici(ad,parola);
						   if(girisbasarili) {
							  
							   AnaEkran ekran=new AnaEkran();
							   timer.stop();
							   ekran.setVisible(true);
							   setVisible(false);
							   
						   }
						   
						   else {
							   AnaEkran ekran=new AnaEkran();
							   ekran.setVisible(false);
							   timer.stop();
							   uyariMesaji.setText("Kullanýcý adý veya Parola Hatalý...");
						   }
						   
						}
						
					}
					
				});
				timer.start();
			}
		});
		
		JButton btnKaytOl = new JButton("Kay\u0131t Ol");
		btnKaytOl.setBounds(627, 227, 98, 34);
		Image kayitIco=new ImageIcon(this.getClass().getResource("/register.png")).getImage();
		btnKaytOl.setIcon(new ImageIcon(kayitIco));
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayitEkrani kayitekrani=new kayitEkrani();
				kayitekrani.setVisible(true);
			}
		});
		
		parolaAlani = new JPasswordField();
		parolaAlani.setBounds(461, 137, 264, 28);
		
		JLabel lblIco = new JLabel("");
		lblIco.setBounds(95, 7, 389, 335);
		Image icon=new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		lblIco.setIcon(new ImageIcon(icon));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(kullaniciAlani);
		contentPane.add(lblParola);
		contentPane.add(parolaAlani);
		contentPane.add(btnKaytOl);
		contentPane.add(girisbutonu);
		contentPane.add(progressBar);
		contentPane.add(uyariMesaji);
		contentPane.add(lblIco);
	}
}
