import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetaySatis extends JFrame {

	private JPanel contentPane;
	private JTable detaySatis;
	DefaultTableModel model = new DefaultTableModel();
	Object[] kolonlar = { "Barkod", "Ürün Adý", "Fiyat", "Adet","Tarih" };
	Object[] satirlar = new Object[5];
	DefaultTableModel model2 = new DefaultTableModel();
	Object[] kolonlar2 = { "Barkod", "Ürün Adý", "Fiyat", "Adet" };
	Object[] satirlar2 = new Object[4];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetaySatis frame = new DetaySatis();
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
	public DetaySatis() {
		Database.baglanti();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 530);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 578, 386);
		contentPane.add(scrollPane);
		
		detaySatis = new JTable();
		scrollPane.setViewportView(detaySatis);
		
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response=JOptionPane.showConfirmDialog(btnTemizle,"Tabloyu Silmek Ýstediðinize Eminmisiniz ?","Onayla",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(response==JOptionPane.YES_OPTION) {
				Database.detayTabloTemizle();
				}
				else if(response==JOptionPane.NO_OPTION) {
					
				}
				else if (response==JOptionPane.CLOSED_OPTION) {
					
				}
				detayliSatis();
			}
		});
		btnTemizle.setBounds(623, 52, 89, 23);
		contentPane.add(btnTemizle);
		
		JButton btnKapat = new JButton("Kapat");
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnKapat.setBounds(623, 91, 89, 23);
		contentPane.add(btnKapat);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			
		});
		
			
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(10, 24, 443, 23);
		contentPane.add(dateChooser);
		
		JButton ara = new JButton("Ara");
		ara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sDate=new SimpleDateFormat("dd/MM/yyyy");
				String date=sDate.format(dateChooser.getDate());
				detaySatis.getModel();
				TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
				detaySatis.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(date.trim()));
			}
		});
		ara.setBounds(484, 24, 104, 23);
		contentPane.add(ara);
		detayliSatis();
	}
	public void detayliSatis() {
		Database.baglanti();
		String sorgu = "Select * from detaylisatis";
		ResultSet rs = Database.detayliListele(sorgu);
		model.setColumnCount(0);
		model.setRowCount(0);
		model.setColumnIdentifiers(kolonlar);

		try {
			while (rs.next()) {
				satirlar[0] = rs.getString("barkod");
				satirlar[1] = rs.getString("ad");
				satirlar[2] = rs.getDouble("fiyati");
				satirlar[3] = rs.getInt("adet");
				satirlar[4]=rs.getString("tarih");

				model.addRow(satirlar);
			}
			detaySatis.setModel(model);
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
