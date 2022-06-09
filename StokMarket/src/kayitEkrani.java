import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class kayitEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField kullaniciAdi;
	private JPasswordField parola;
	private JPasswordField gerekli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kayitEkrani frame = new kayitEkrani();
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
	public kayitEkrani() {
		Database.baglanti();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 336);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mesajAlani = new JLabel("");
		mesajAlani.setForeground(Color.RED);
		mesajAlani.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mesajAlani.setBounds(150, 183, 197, 14);
		contentPane.add(mesajAlani);
		
		kullaniciAdi = new JTextField();
		kullaniciAdi.setBounds(141, 40, 216, 30);
		contentPane.add(kullaniciAdi);
		kullaniciAdi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(41, 47, 99, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblParola.setBounds(41, 89, 99, 14);
		contentPane.add(lblParola);
		
		JLabel lblKaytOlmakIin = new JLabel("Kay\u0131t Olmak \u0130\u00E7in Gerekli Parola Alan\u0131");
		lblKaytOlmakIin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKaytOlmakIin.setBounds(141, 123, 206, 14);
		contentPane.add(lblKaytOlmakIin);
		
		JButton btnNewButton = new JButton("Kay\u0131t");
		Image btnIco=new ImageIcon(this.getClass().getResource("/register.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(btnIco));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mesajAlani.setText("");
				String kullaniciadi=kullaniciAdi.getText();
				String password=new String(parola.getPassword());
				String parola=new String(gerekli.getPassword()) ;
			
				if(parola.equals("admin12345")) {
					Database.kayitOlustur(kullaniciadi,password);
					mesajAlani.setText("Kayýt Baþarýlý");
				
				}
				else {
					mesajAlani.setText("Kayýt Baþarýsýz");
				}
			}
		});
		btnNewButton.setBounds(141, 205, 99, 36);
		contentPane.add(btnNewButton);
		
		JButton btnKapat = new JButton("Kapat");
		Image kapatIco=new ImageIcon(this.getClass().getResource("/close.png")).getImage();
		btnKapat.setIcon(new ImageIcon(kapatIco));
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnKapat.setBounds(267, 205, 90, 36);
		contentPane.add(btnKapat);
		
		parola = new JPasswordField();
		parola.setBounds(141, 82, 216, 30);
		contentPane.add(parola);
		
		gerekli = new JPasswordField();
		gerekli.setBounds(141, 148, 216, 30);
		contentPane.add(gerekli);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image lblicon=new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(lblicon));
		lblNewLabel_1.setBounds(377, 11, 145, 202);
		contentPane.add(lblNewLabel_1);
		
	
		Date bugun=new Date();
		
		DateFormat f=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String str=f.format(bugun);
	}
}
