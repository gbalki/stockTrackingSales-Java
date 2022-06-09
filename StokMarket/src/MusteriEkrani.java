import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MusteriEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField musteriAra;
	private JTextField musteriAd;
	private JTextField musterisoyAd;
	private JTextField musteriTel;
	private JTextField musteriBorc;
	private JTable musteriTablosu;
	DefaultTableModel model=new DefaultTableModel();
	Object[]kolonlar= {"Müþteri ID","Ad" ,"Soyad","Telefon","Borç"};
	Object[]satirlar=new Object [5];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusteriEkrani frame = new MusteriEkrani();
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
	public MusteriEkrani() {
		Database.baglanti();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mesajAlani = new JLabel("");
		mesajAlani.setForeground(Color.RED);
		mesajAlani.setBounds(20, 194, 287, 14);
		contentPane.add(mesajAlani);
		
		musteriAra = new JTextField();
		musteriAra.addKeyListener(new KeyAdapter() {
			public void dinamikAra(String ara) {
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		        
		        
		        musteriTablosu.setRowSorter(tr);
		        
		        
		        tr.setRowFilter(RowFilter.regexFilter(ara));
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String dinamikara=musteriAra.getText();
				dinamikAra(dinamikara);
			}
		});
		musteriAra.setBounds(10, 11, 570, 20);
		contentPane.add(musteriAra);
		musteriAra.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 590, 14);
		contentPane.add(separator);
		
		musteriAd = new JTextField();
		musteriAd.setBounds(119, 67, 188, 20);
		contentPane.add(musteriAd);
		musteriAd.setColumns(10);
		
		musterisoyAd = new JTextField();
		musterisoyAd.setColumns(10);
		musterisoyAd.setBounds(119, 98, 188, 20);
		contentPane.add(musterisoyAd);
		
		musteriTel = new JTextField();
		musteriTel.setColumns(10);
		musteriTel.setBounds(119, 129, 188, 20);
		contentPane.add(musteriTel);
		
		musteriBorc = new JTextField();
		musteriBorc.setColumns(10);
		musteriBorc.setBounds(119, 160, 188, 20);
		contentPane.add(musteriBorc);
		
		JLabel lblNewLabel = new JLabel("Ad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(24, 67, 85, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyad = new JLabel("Soyad:");
		lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoyad.setBounds(24, 98, 85, 20);
		contentPane.add(lblSoyad);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefon.setBounds(24, 132, 85, 20);
		contentPane.add(lblTelefon);
		
		JLabel lblEmail = new JLabel("Bor\u00E7:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(24, 163, 85, 20);
		contentPane.add(lblEmail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 220, 590, 191);
		contentPane.add(scrollPane);
		
		musteriTablosu = new JTable();
		musteriTablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow=musteriTablosu.getSelectedRow();
				musteriAd.setText(musteriTablosu.getValueAt(selectedrow,1).toString());
				musterisoyAd.setText(musteriTablosu.getValueAt(selectedrow, 2).toString());
				musteriTel.setText(musteriTablosu.getValueAt(selectedrow, 3).toString());
				musteriBorc.setText(musteriTablosu.getValueAt(selectedrow, 4).toString());
			}
		});
		scrollPane.setViewportView(musteriTablosu);
		
		JButton btnNewButton = new JButton("Bor\u00E7 Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mesajAlani.setText("");
				String ad=musteriAd.getText();
				String soyad=musterisoyAd.getText();
				String telefon=musteriTel.getText();
				String borc=musteriBorc.getText();
				Database.borcEkle(ad,soyad,telefon,borc);
				musteriListele();
				mesajAlani.setText("Borç Baþarýyla Eklendi");
			}
		});
		btnNewButton.setBounds(348, 67, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnGncelle = new JButton("G\u00FCncelle");
		btnGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad=musteriAd.getText();
				String soyad=musterisoyAd.getText();
				String telefon=musteriTel.getText();
				String borc=musteriBorc.getText();
				
				int selectedRow=musteriTablosu.getSelectedRow();
				if(selectedRow == -1) {
					if(model.getRowCount()==0) {
						mesajAlani.setText("Müþteri Tablosu Þuanda Boþ");
					}
					else {
						mesajAlani.setText("Lütfen Güncelleyecek Bir isim seçiniz");
					}
				}
				else {
					int silid=(int) model.getValueAt(selectedRow, 0);
					Database.musteriGuncelle(ad,soyad,telefon,borc,silid);
					musteriListele();
					mesajAlani.setText("Müþteri Baþarýyla Güncellendi");
				}
			}
		});
		btnGncelle.setBounds(348, 97, 89, 23);
		contentPane.add(btnGncelle);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowsil=musteriTablosu.getSelectedRow();
				if(selectedRowsil==-1) {
					if(model.getRowCount()==0) {
						mesajAlani.setText("Müþteri Tablosu Boþ");
					}
					else {
						mesajAlani.setText("Lütfen Silinecek Bir Müþteri Seçiniz");
					}
				
				}
				else {
					int silid=(int) musteriTablosu.getValueAt(selectedRowsil,0);
					Database.musteriSil(silid);
					musteriListele();
					mesajAlani.setText("Müþteri Baþarýyla Silindi");
				}
			}
		});
		btnSil.setBounds(348, 128, 89, 23);
		contentPane.add(btnSil);
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musteriAd.setText("");
				musterisoyAd.setText("");
				musteriTel.setText("");
				musteriBorc.setText("");
			}
		});
		btnTemizle.setBounds(447, 66, 89, 23);
		contentPane.add(btnTemizle);
		
		JButton btnKapat = new JButton("Kapat");
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnKapat.setBounds(447, 97, 89, 23);
		contentPane.add(btnKapat);
		musteriListele();
	}
	public void musteriListele() {
		Database.baglanti();
		String sorgu="Select * from musteri";
		ResultSet rs=Database.musteriListele(sorgu);
		model.setColumnCount(0);
		model.setRowCount(0);
		model.setColumnIdentifiers(kolonlar);
		try {
			while(rs.next()) {
				satirlar[0]=rs.getInt("id");
				satirlar[1]=rs.getString("ad");
				satirlar[2]=rs.getString("soyad");
				satirlar[3]=rs.getString("telefon");
				satirlar[4]=rs.getString("borcu");
				
				model.addRow(satirlar);
			}
			musteriTablosu.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
