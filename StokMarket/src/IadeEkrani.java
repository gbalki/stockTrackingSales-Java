import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IadeEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField aramaCubugu;
	private JTable iadeTablosu;
	DefaultTableModel model = new DefaultTableModel();
	Object[] kolonlar = { "Tarih","Barkod","Ürün Adý", "Fiyat", "Stok","Ýade Nedeni" };
	Object[] satirlar = new Object[6];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IadeEkrani frame = new IadeEkrani();
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
	public IadeEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 436);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 576, 338);
		contentPane.add(scrollPane);
		
		iadeTablosu = new JTable();
		scrollPane.setViewportView(iadeTablosu);
		
		aramaCubugu = new JTextField();
		aramaCubugu.addKeyListener(new KeyAdapter() {
			public void dinamikAra(String ara) {
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);

				iadeTablosu.setRowSorter(tr);

				tr.setRowFilter(RowFilter.regexFilter(ara));
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String dinamikara = aramaCubugu.getText();
				dinamikAra(dinamikara);
			}
		});
		aramaCubugu.setBounds(52, 17, 486, 20);
		contentPane.add(aramaCubugu);
		aramaCubugu.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 43, 599, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(596, 0, 16, 397);
		contentPane.add(separator_1);
		
		JButton btnNewButton = new JButton("Temizle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(btnNewButton, "Tabloyu Temizlemek Ýstediðinize Emin misiniz?",
						"Onayla", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					Database.iadeSil();
				} else if (response == JOptionPane.NO_OPTION) {
					
				} else if (response == JOptionPane.CLOSED_OPTION) {

				}
				
			}
		});
		btnNewButton.setBounds(609, 45, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnKapat = new JButton("Kapat");
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnKapat.setBounds(609, 93, 89, 23);
		contentPane.add(btnKapat);
		Listele();
	}
	public void Listele() {
		Database.baglanti();
		String sorgu = "Select * from iadetablosu";
		ResultSet rs = Database.iadeNedeni(sorgu);
		model.setColumnCount(0);
		model.setRowCount(0);
		model.setColumnIdentifiers(kolonlar);

		try {
			while (rs.next()) {
				satirlar[0] = rs.getString("tarih");
				satirlar[1] = rs.getString("barkod");
				satirlar[2] = rs.getString("ad");
				satirlar[3] = rs.getDouble("para");
				satirlar[4]=rs.getInt("adet");
				satirlar[5]=rs.getString("nedeni");

				model.addRow(satirlar);
			}
			iadeTablosu.setModel(model);
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
