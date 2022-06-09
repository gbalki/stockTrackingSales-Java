import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import java.awt.Font;

public class KasaEkran extends JFrame {

	private JPanel contentPane;
	private JTable kasaTablosu;
	DefaultTableModel model = new DefaultTableModel();
	Object[] kolonlar = { "Tarih", "Kasaya Giren Para" };
	Object[] satirlar = new Object[2];
	DefaultTableModel model2 = new DefaultTableModel();
	Object[] kolonlar2 = {  "Kasaya Giren Para" };
	Object[] satirlar2 = new Object[1];
	private JTextField toplam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KasaEkran frame = new KasaEkran();
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
	public KasaEkran() {
		setBounds(100, 100, 583, 527);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		toplam = new JTextField();
		toplam.setBounds(10, 391, 76, 20);
		contentPane.add(toplam);
		toplam.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 445, 259);
		contentPane.add(scrollPane);

		kasaTablosu = new JTable();
		scrollPane.setViewportView(kasaTablosu);

		JButton btnNewButton = new JButton("Kapat");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(471, 173, 89, 23);
		contentPane.add(btnNewButton);

		JButton temizle = new JButton("Temizle");
		temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(temizle, "Tabloyu Silmek İstediğinize Eminmisiniz ?",
						"Onayla", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					Database.kasasil();
				} else if (response == JOptionPane.NO_OPTION) {

				} else if (response == JOptionPane.CLOSED_OPTION) {

				}
				kasaListele();
			}
		});
		temizle.setBounds(468, 118, 89, 23);
		contentPane.add(temizle);
		
		JDateChooser kasaTarih = new JDateChooser();
		kasaTarih.setBounds(10, 36, 346, 20);
		contentPane.add(kasaTarih);
		kasaTarih.setDateFormatString("dd/MM/yyyy");
		
		JDateChooser bitisTarih = new JDateChooser();
		bitisTarih.setBounds(10, 90, 346, 20);
		contentPane.add(bitisTarih);
		bitisTarih.setDateFormatString("dd/MM/yyyy");
		JButton raporla_2 = new JButton("Raporla");
		raporla_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.baglanti();
				SimpleDateFormat sDate=new SimpleDateFormat("dd/MM/yyyy");
				String date1=sDate.format(kasaTarih.getDate());
				SimpleDateFormat sDate2=new SimpleDateFormat("dd/MM/yyyy");
				String date2=sDate.format(bitisTarih.getDate());	
				String sorgu="Select sum(para) from kasa where tarih>='"+date1+"'and tarih<='"+date2+"'";
	
					ResultSet rs = Database.kasaListele(sorgu);
					try {
						if(rs.next()) {
							Double sum=rs.getDouble("sum(para)");
							toplam.setText(Double.toString(sum));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				

				
			}
		});
		raporla_2.setBounds(366, 33, 89, 23);
		contentPane.add(raporla_2);
		
		JLabel lblNewLabel = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 103, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblBitiTarihi = new JLabel("Biti\u015F Tarihi:");
		lblBitiTarihi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBitiTarihi.setBounds(10, 65, 103, 23);
		contentPane.add(lblBitiTarihi);
		
		JLabel paraAlani = new JLabel("TL");
		paraAlani.setFont(new Font("Tahoma", Font.PLAIN, 13));
		paraAlani.setBounds(96, 390, 103, 20);
		contentPane.add(paraAlani);
		kasaListele();
		
	}

	public void kasaListele() {
		Database.baglanti();
		String sorgu = "Select * from kasa";
		ResultSet rs = Database.kasaListele(sorgu);
		model.setColumnCount(0);
		model.setRowCount(0);
		model.setColumnIdentifiers(kolonlar);

		try {
			while (rs.next()) {
				satirlar[0] = rs.getString("tarih");
				satirlar[1] = rs.getString("para");

				model.addRow(satirlar);
			}
			kasaTablosu.setModel(model);
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
