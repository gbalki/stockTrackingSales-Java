import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.JSplitPane;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AnaEkran extends JFrame {

	private JPanel contentPane;
	private JTable sepetTablosu;
	private JTable urunListele1;
	private JTextField aramaCubugu;
	private JTextField paraAlani;
	private JTextField alinanPara;
	private JTextField paraustuAlani;
	private JTextField gunlukKazancAlani;
	private JTable stokTablosu;
	private JTextField aramaCubugu2;
	private JTextField barkodAlani;
	private JTextField adAlani;
	private JTextField fiyatAlani;
	private JTextField turAlani;
	private JTextField stokAlani;
	DefaultTableModel model = new DefaultTableModel();
	Object[] kolonlar = { "Barkod", "Ürün Adý", "Fiyat", "Stok" };
	Object[] satirlar = new Object[4];
	DefaultTableModel model2 = new DefaultTableModel();
	Object[] kolonlar2 = { "Barkod", "Ürün Adý", "Ürün Fiyatý", "Adet" };
	Object[] satirlar2 = new Object[4];
	DefaultTableModel model3 = new DefaultTableModel();
	Object[] kolonlar3 = { "Barkod", "Ürün Adý", "Fiyat", "Tür", "Stok" };
	Object[] satirlar3 = new Object[5];
	private ArrayList<Double> hesap = new ArrayList<Double>();
	private JTextField barkodIade;
	private JTextField urunIade;
	private JTextField fiyatIade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkran frame = new AnaEkran();
					frame.setLocationByPlatform(true);
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
	public AnaEkran() {
	    
		Database.baglanti();
		setBounds(100, 100, 1052, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);

		JMenuBar menuBar = new JMenuBar();

		JMenu mnNewMenu = new JMenu("M\u00FC\u015Fteri");
		menuBar.add(mnNewMenu);

		JMenuItem mnýtmNewMenuItem = new JMenuItem("Bilgileri A\u00E7");
		mnýtmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusteriEkrani mekran = new MusteriEkrani();
				mekran.setVisible(true);
			}
		});
		mnNewMenu.add(mnýtmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Kasa");
		menuBar.add(mnNewMenu_1);

		JMenuItem mnýtmNewMenuItem_1 = new JMenuItem("Kasa A\u00E7");
		mnýtmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KasaEkran kekran = new KasaEkran();
				kekran.setVisible(true);
			}
		});
		mnNewMenu_1.add(mnýtmNewMenuItem_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		model2.setColumnCount(0);
		model2.setRowCount(0);
		model2.setColumnIdentifiers(kolonlar2);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Satýþ", null, panel_1, null);

		JComboBox adetCombo = new JComboBox();
		adetCombo.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

		JScrollPane scrollPane = new JScrollPane();

		sepetTablosu = new JTable();
		sepetTablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setViewportView(sepetTablosu);
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);

		JScrollPane scrollPane_1 = new JScrollPane();

		urunListele1 = new JTable();
		urunListele1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = urunListele1.getSelectedRow();
				String barkod = urunListele1.getValueAt(selectedRow, 0).toString();
				String urunadi = urunListele1.getValueAt(selectedRow, 1).toString();
				double fiyat = (double) urunListele1.getValueAt(selectedRow, 2);
				int stok = (int) urunListele1.getValueAt(selectedRow, 3);
				int adet = adetCombo.getSelectedIndex() + 1;

				if (selectedRow == -1) {
					if (urunListele1.getRowCount() == 0) {

					} else {

					}
				} else if (adet > stok) {
					JOptionPane.showMessageDialog(scrollPane_1, "Adet Stok'dan fazla olamaz");
				} else {
					satirlar2[0] = barkod;
					satirlar2[1] = urunadi;
					satirlar2[2] = fiyat * adet;
					satirlar2[3] = adet;

					model2.addRow(satirlar2);
				}
				sepetTablosu.setModel(model2);
			}
		});
		scrollPane_1.setViewportView(urunListele1);

		JLabel lblNewLabel = new JLabel("Adet:");

		JLabel lblNewLabel_1 = new JLabel("Sepet");

		aramaCubugu = new JTextField();
		aramaCubugu.addKeyListener(new KeyAdapter() {
			public void dinamikAra(String ara) {
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);

				urunListele1.setRowSorter(tr);

				tr.setRowFilter(RowFilter.regexFilter(ara));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String dinamikara = aramaCubugu.getText();
				dinamikAra(dinamikara);

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String sorgu = "Select * from stok where barkod='" + aramaCubugu.getText() + "'";// texfield'den
																										// gelen deðer
																										// ile sql
																										// sorgusu
																										// yapýyoruz
					ResultSet rs = Database.stokListele(sorgu);
					int adet = adetCombo.getSelectedIndex() + 1;

					model2.setColumnIdentifiers(kolonlar2);
					try {
						while (rs.next()) {
							String barkod = rs.getString("barkod");
							String ad2 = rs.getString("ad");
							double fiyatal = rs.getDouble("fiyati");
							satirlar2[0] = barkod;
							satirlar2[1] = ad2;
							satirlar2[2] = fiyatal * adet;
							satirlar2[3] = adet;

							model2.addRow(satirlar2);
						}
						sepetTablosu.setModel(model2);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					aramaCubugu.setText("");
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				if ("".equals(aramaCubugu.getText())) {
					Listele();
				}
			}
		});
		aramaCubugu.setColumns(10);

		JButton satisButonu = new JButton("Sat\u0131\u015F Yap");
		satisButonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paraAlani.getText().equals("")) {
					JOptionPane.showMessageDialog(satisButonu, "Lütfen Toplam Butonuna basýnýz");

				} else if (alinanPara.getText().equals("")) {
					JOptionPane.showMessageDialog(satisButonu, "Lütfen Önce Alýnan Parayý Giriniz");
				} else {
					double alinanpara = Double.parseDouble(alinanPara.getText());
					double toplampara = Double.parseDouble(paraAlani.getText());
					double paraustu = alinanpara - toplampara;
					paraustuAlani.setText(Double.toString(paraustu));

					double kazanc = Double.parseDouble(paraAlani.getText());
					hesap.add(kazanc);

					Double gunlukkazanc = 0.0;
					for (Double listkazanc : hesap) {

						gunlukkazanc += listkazanc;
					}
					gunlukKazancAlani.setText(Double.toString(gunlukkazanc));

					for (int i = 0; i < sepetTablosu.getRowCount(); i++) {
						String barkod = sepetTablosu.getValueAt(i, 0).toString();
						int adet = (int) sepetTablosu.getValueAt(i, 3);

						Database.stokazalt(barkod, adet);

					}

					JOptionPane.showMessageDialog(satisButonu, "Satýþ Baþarýyla Gerçekleþti");
				}

				Date dtarih = new Date();
				DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String str = f.format(dtarih);

				for (int i = 0; i < sepetTablosu.getRowCount(); i++) {
					String dbarkod = sepetTablosu.getValueAt(i, 0).toString();
					String dad = sepetTablosu.getValueAt(i, 1).toString();
					double dfiyat = (double) sepetTablosu.getValueAt(i, 2);
					int dadet = (int) sepetTablosu.getValueAt(i, 3);
					Database.detaySatis(dbarkod, dad, dfiyat, dadet, str);
				}
				Listele();
				stokListele();
			}
		});

		JButton btnIptal = new JButton("\u0130ptal");
		btnIptal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model2.removeRow(sepetTablosu.getSelectedRow());
			}
		});

		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (model2.getRowCount() > 0) {
					for (int i = 0; i < model2.getRowCount(); i++) {
						model2.removeRow(i);
					}
				}
				paraAlani.setText("");
				alinanPara.setText("");
				paraustuAlani.setText("");
			}
		});

		paraAlani = new JTextField();
		paraAlani.setColumns(10);

		alinanPara = new JTextField();
		alinanPara.setColumns(10);

		paraustuAlani = new JTextField();
		paraustuAlani.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("G\u00FCnl\u00FCk Kazan\u00E7:");

		gunlukKazancAlani = new JTextField();
		gunlukKazancAlani.setColumns(10);

		JButton kasaEkle = new JButton("Kasaya Ekle");
		kasaEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date tarih = new Date();
				DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
				String str = f.format(tarih);

				Double para = Double.parseDouble(gunlukKazancAlani.getText());
				Database.paraEkle(str, para);
				JOptionPane.showMessageDialog(kasaEkle, "Ýþlem Baþarýlý");
				gunlukKazancAlani.setText("");

				hesap.clear();
			}
		});

		JLabel lblNewLabel_3 = new JLabel("Toplam:");

		JLabel lblNewLabel_3_1 = new JLabel("Al\u0131nan Para:");

		JLabel lblNewLabel_3_2 = new JLabel("Para \u00DCst\u00FC:");

		JButton topla = new JButton("Topla");
		topla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double sum = 0.0;
				for (double i = 0; i < sepetTablosu.getRowCount(); i++) {
					sum = sum + Double.parseDouble(sepetTablosu.getValueAt((int) i, 2).toString());

					paraAlani.setText(Double.toString(sum));

				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(aramaCubugu, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(adetCombo, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)))
					.addGap(5)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(0)
									.addComponent(satisButonu, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
									.addGap(10)
									.addComponent(btnIptal, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
									.addGap(16)
									.addComponent(topla, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addGap(9)
									.addComponent(alinanPara, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(gunlukKazancAlani, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
									.addComponent(btnTemizle, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
									.addGap(17)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(paraAlani, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
											.addComponent(kasaEkle, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(paraustuAlani, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)))))
							.addGap(1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
							.addGap(345))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addComponent(aramaCubugu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(adetCombo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(182, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(satisButonu)
						.addComponent(btnIptal)
						.addComponent(topla)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_3_1))
						.addComponent(alinanPara, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(gunlukKazancAlani, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(paraAlani, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(paraustuAlani, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(kasaEkle)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_3)
							.addGap(19)
							.addComponent(lblNewLabel_3_2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(14)
							.addComponent(btnTemizle)))
					.addGap(99))
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Stok", null, panel_2, null);

		JScrollPane scrollPane_2 = new JScrollPane();

		stokTablosu = new JTable();
		stokTablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = stokTablosu.getSelectedRow();
				barkodAlani.setText(stokTablosu.getValueAt(selectedrow, 0).toString());
				adAlani.setText(stokTablosu.getValueAt(selectedrow, 1).toString());
				fiyatAlani.setText(stokTablosu.getValueAt(selectedrow, 2).toString());
				turAlani.setText(stokTablosu.getValueAt(selectedrow, 3).toString());
				stokAlani.setText(stokTablosu.getValueAt(selectedrow, 4).toString());

			}
		});
		scrollPane_2.setViewportView(stokTablosu);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);

		aramaCubugu2 = new JTextField();
		aramaCubugu2.addKeyListener(new KeyAdapter() {
			public void dinamikAra(String ara) {
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model3);

				stokTablosu.setRowSorter(tr);

				tr.setRowFilter(RowFilter.regexFilter(ara));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String dinamikara = aramaCubugu2.getText();
				dinamikAra(dinamikara);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String sorgu = "Select * from stok where barkod='" + aramaCubugu2.getText() + "'";
					ResultSet rs = Database.stokListele(sorgu);
					model3.setColumnCount(0);
					model3.setRowCount(0);
					model3.setColumnIdentifiers(kolonlar3);
					try {
						while (rs.next()) {
							satirlar3[0] = rs.getString("barkod");
							satirlar3[1] = rs.getString("ad");
							satirlar3[2] = rs.getString("fiyati");
							satirlar3[3] = rs.getString("tur");
							satirlar3[4] = rs.getString("stokadet");

							model3.addRow(satirlar3);
						}
						stokTablosu.setModel(model3);
					} catch (ArrayIndexOutOfBoundsException ex) {

					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if ("".equals(aramaCubugu2.getText())) {
					stokListele();
					Listele();
				}
			}
		});
		aramaCubugu2.setColumns(10);

		barkodAlani = new JTextField();
		barkodAlani.addKeyListener(new KeyAdapter() {
			// @Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					Database.baglanti();
					String sorgu = "Select * from stok where barkod='" + barkodAlani.getText() + "'";
					ResultSet rs = Database.stokListele(sorgu);
					try {
						rs.next();
						String ad = rs.getString("ad");
						double fiyat = rs.getDouble("fiyati");
						String tur = rs.getString("tur");
						int adet = rs.getInt("stokadet");

						adAlani.setText(ad);
						fiyatAlani.setText(Double.toString(fiyat));
						turAlani.setText(tur);
						stokAlani.setText(Integer.toString(adet));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});
		barkodAlani.setColumns(10);

		JLabel lblBarkod = new JLabel("Barkod");

		JLabel lblAd = new JLabel("Ad");

		JLabel lblFiyat = new JLabel("Fiyat");

		JLabel lblTur = new JLabel("T\u00FCr");

		JLabel lblStok = new JLabel("Stok");

		stokAlani = new JTextField();
		stokAlani.setColumns(10);

		turAlani = new JTextField();
		turAlani.setColumns(10);

		fiyatAlani = new JTextField();
		fiyatAlani.setColumns(10);

		adAlani = new JTextField();
		adAlani.setColumns(10);

		JLabel mesajAlani = new JLabel("");
		mesajAlani.setForeground(Color.RED);
		mesajAlani.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JButton urunEkle = new JButton("\u00DCr\u00FCn Ekle");
		urunEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mesajAlani.setText("");
				String barkod = barkodAlani.getText();
				String ad = adAlani.getText();
				String fiyat = fiyatAlani.getText();
				String tur = turAlani.getText();
				String stok = stokAlani.getText();

				Database.urunEkle(barkod, ad, fiyat, tur, stok);
				stokListele();
				Listele();
				mesajAlani.setText("Ürün Baþarýyla Eklendi");

			}
		});

		JButton urunSil = new JButton("\u00DCr\u00FCn Sil");
		urunSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = stokTablosu.getSelectedRow();
				if (selectedRow == -1) {
					if (stokTablosu.getRowCount() == 0) {
						mesajAlani.setText("Stok Tablosu Boþ");
					} else {
						mesajAlani.setText("Lütfen Silinecek Bir Ürün seçin");
					}
				} else {
					String silbarkod = stokTablosu.getValueAt(selectedRow, 0).toString();
					Database.stokSil(silbarkod);
					stokListele();
					Listele();
					mesajAlani.setText("Ürün Baþarýyla Silindi");
				}
			}
		});

		JButton urunGuncelle = new JButton("G\u00FCncelle");
		urunGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String barkod = barkodAlani.getText();
				String ad = adAlani.getText();
				String fiyat = fiyatAlani.getText();
				String tur = turAlani.getText();
				String stok = stokAlani.getText();
				int selectedRow = stokTablosu.getSelectedRow();

				Database.stokGuncelle(barkod, ad, fiyat, tur, stok);
				stokListele();
				Listele();
				mesajAlani.setText("Ürün Baþarýyla Güncellendi");
				// }
			}
		});

		JButton temizlebt = new JButton("Temizle");
		temizlebt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				barkodAlani.setText("");
				adAlani.setText("");
				fiyatAlani.setText("");
				turAlani.setText("");
				stokAlani.setText("");
			}
		});

		JSeparator separator_2 = new JSeparator();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(24)
							.addComponent(mesajAlani, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblBarkod, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(barkodAlani, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblAd, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(adAlani, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblFiyat, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(fiyatAlani, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblTur, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(turAlani, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblStok, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(stokAlani, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(urunEkle, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(urunSil, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(urunGuncelle, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(temizlebt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(15)
							.addComponent(aramaCubugu2, GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(17)
					.addComponent(mesajAlani, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(lblBarkod))
						.addComponent(barkodAlani, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(lblAd))
						.addComponent(adAlani, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFiyat))
						.addComponent(fiyatAlani, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTur))
						.addComponent(turAlani, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(lblStok))
						.addComponent(stokAlani, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(urunEkle)
						.addComponent(urunSil))
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(urunGuncelle)
						.addComponent(temizlebt)))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(46)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(scrollPane_2))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(17)
					.addComponent(aramaCubugu2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(423, Short.MAX_VALUE))
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
		);
		panel_2.setLayout(gl_panel_2);

		JMenu mnNewMenu_2 = new JMenu("Raporlar");
		menuBar.add(mnNewMenu_2);

		JMenuItem mnýtmNewMenuItem_2 = new JMenuItem("Detayl\u0131 Sat\u0131\u015F Raporu");
		mnýtmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetaySatis dekran = new DetaySatis();
				dekran.setVisible(true);
			}
		});
		mnNewMenu_2.add(mnýtmNewMenuItem_2);
		
		JMenuItem mnýtmNewMenuItem_3 = new JMenuItem("\u0130ade Raporu");
		mnýtmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		         IadeEkrani iekran=new IadeEkrani();
		         iekran.setVisible(true);
			}
		});
		mnNewMenu_2.add(mnýtmNewMenuItem_3);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Ýade", null, panel_3, null);

		JComboBox iadeAdet = new JComboBox();
		iadeAdet.setBounds(261, 171, 42, 22);
		iadeAdet.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

		JDateChooser iadeTarih = new JDateChooser();
		iadeTarih.setBounds(261, 209, 203, 20);
		iadeTarih.setDateFormatString("dd/MM/yyyy");

		JLabel lblNewLabel_4 = new JLabel("BARKOD:");
		lblNewLabel_4.setBounds(156, 80, 63, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_4_1 = new JLabel("\u00DCR\u00DCN ADI:");
		lblNewLabel_4_1.setBounds(156, 111, 87, 14);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_4_2 = new JLabel("F\u0130YAT:");
		lblNewLabel_4_2.setBounds(156, 142, 63, 14);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_4_3 = new JLabel("ADET:");
		lblNewLabel_4_3.setBounds(156, 173, 63, 14);
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_4_4 = new JLabel("\u0130ADE NEDEN\u0130:");
		lblNewLabel_4_4.setBounds(156, 238, 87, 14);
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.PLAIN, 14));

		barkodIade = new JTextField();
		barkodIade.setBounds(261, 79, 203, 20);
		barkodIade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String sorgu = "Select * from stok where barkod='" + barkodIade.getText() + "'";// texfield'den
																									// gelen deðer
																									// ile sql
																									// sorgusu
																									// yapýyoruz
					ResultSet rs = Database.stokListele(sorgu);
					try {
						rs.next();
						String barkod = rs.getString("barkod");
						String ad3 = rs.getString("ad");
						double fiyatal = rs.getDouble("fiyati");

						urunIade.setText(ad3);
						fiyatIade.setText(Double.toString(fiyatal));

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		barkodIade.setColumns(10);

		urunIade = new JTextField();
		urunIade.setBounds(261, 110, 203, 20);
		urunIade.setColumns(10);

		fiyatIade = new JTextField();
		fiyatIade.setBounds(261, 141, 203, 20);
		fiyatIade.setColumns(10);

		JTextArea iadeNedeni = new JTextArea();
		iadeNedeni.setBounds(261, 240, 351, 147);

		JButton iadeEt = new JButton("\u0130ADE ET");
		iadeEt.setBounds(658, 78, 121, 35);
		iadeEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sDate = new SimpleDateFormat("dd/MM/yyyy");
				String date = sDate.format(iadeTarih.getDate());
				String barkod = barkodIade.getText();
				String ad = urunIade.getText();
				Double fiyat = Double.parseDouble(fiyatIade.getText());
				int adet = iadeAdet.getSelectedIndex() + 1;
				Double toplampara = adet * fiyat;
				String neden = iadeNedeni.getText();
				int response = JOptionPane.showConfirmDialog(iadeEt, "Kasa'dan Silmek Ýstermisiniz ?",
						"Onayla", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					Database.iadeRaporu(date, barkod, ad, toplampara, adet, neden);
					Database.kasaiadeEt(date, toplampara);
				} else if (response == JOptionPane.NO_OPTION) {
					Double kazanc = Double.parseDouble(gunlukKazancAlani.getText());
					Double yenideger = kazanc - toplampara;
					gunlukKazancAlani.setText(Double.toString(yenideger));
					JOptionPane.showMessageDialog(iadeEt, "Günlük Kazanç Alanýndan Ýade Edilmiþtir");
				} else if (response == JOptionPane.CLOSED_OPTION) {

				}
			}
		});

		JButton temizle = new JButton("TEM\u0130ZLE");
		temizle.setBounds(658, 152, 121, 35);
		temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				barkodIade.setText("");
				urunIade.setText("");
				fiyatIade.setText("");
				iadeNedeni.setText("");
			}
		});

		JLabel lblNewLabel_4_3_1 = new JLabel("SATI\u015E TAR\u0130H\u0130:");
		lblNewLabel_4_3_1.setBounds(156, 209, 87, 14);
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.setLayout(null);
		panel_3.add(iadeAdet);
		panel_3.add(iadeTarih);
		panel_3.add(lblNewLabel_4);
		panel_3.add(lblNewLabel_4_1);
		panel_3.add(lblNewLabel_4_2);
		panel_3.add(lblNewLabel_4_3);
		panel_3.add(lblNewLabel_4_4);
		panel_3.add(barkodIade);
		panel_3.add(urunIade);
		panel_3.add(fiyatIade);
		panel_3.add(iadeNedeni);
		panel_3.add(iadeEt);
		panel_3.add(temizle);
		panel_3.add(lblNewLabel_4_3_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1031, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 511, Short.MAX_VALUE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

		Listele();
		stokListele();

	}

	public void Listele() {
		Database.baglanti();
		String sorgu = "Select * from stok";
		ResultSet rs = Database.stokListele(sorgu);
		model.setColumnCount(0);
		model.setRowCount(0);
		model.setColumnIdentifiers(kolonlar);

		try {
			while (rs.next()) {
				satirlar[0] = rs.getString("barkod");
				satirlar[1] = rs.getString("ad");
				satirlar[2] = rs.getDouble("fiyati");
				satirlar[3] = rs.getInt("stokadet");

				model.addRow(satirlar);
			}
			urunListele1.setModel(model);
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void stokListele() {
		Database.baglanti();
		// SELECT SUM(stokadet) from stok GROUP BY barkod;
		String sorgu = "Select * from stok";
		ResultSet rs = Database.stokListele(sorgu);
		model3.setColumnCount(0);
		model3.setRowCount(0);
		model3.setColumnIdentifiers(kolonlar3);

		try {
			while (rs.next()) {
				satirlar3[0] = rs.getString("barkod");
				satirlar3[1] = rs.getString("ad");
				satirlar3[2] = rs.getDouble("fiyati");
				satirlar3[3] = rs.getString("tur");
				satirlar3[4] = rs.getInt("stokadet");

				model3.addRow(satirlar3);
			}
			stokTablosu.setModel(model3);
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
