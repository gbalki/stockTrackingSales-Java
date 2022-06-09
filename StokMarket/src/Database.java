import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

	static String userName="root";
	static String password="";
	static String dbName="stokmarket";
	static String host="localhost";
	static int port=3306;
	static Connection con;
	static Statement st;
	static PreparedStatement prst;
	
	
	
	static void baglanti() {
		String url="jdbc:mysql://"+host+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver Bulunamadý..");
		}
		try {
			con=DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Veri tabanýna baðlanamadý");
		}
	}
	
	static ResultSet stokListele(String sorgu) {
		try {
			st=con.createStatement();
			ResultSet rs=st.executeQuery(sorgu);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	static void stokazalt(String barkodal,int adet) {
		String sorgu1="Select * from stok where barkod=?";
		String sorgu2="UPDATE stok SET stokadet=? where barkod=?";
		
		try {
			prst=con.prepareStatement(sorgu1);
			prst.setString(1,barkodal);
			ResultSet rs=prst.executeQuery();
			rs.next();
			int eskiadet=rs.getInt("stokadet");
			int yeniadet=eskiadet-adet;
	
			prst=con.prepareStatement(sorgu2);
			prst.setInt(1, yeniadet);
			prst.setString(2, barkodal);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	static boolean kullanici(String ad,String parola) {
		String sorgu="Select * from adminler where userName=? and password=?";
		try {
			prst=con.prepareStatement(sorgu);
			prst.setString(1,ad);
			prst.setString(2,parola);
			ResultSet rs=prst.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	static void kayitOlustur(String ad,String parola) {
		String sorgu="Insert Into adminler (userName,password)VALUES(?,?)";
		try {
			
			prst=con.prepareStatement(sorgu);
			prst.setString(1,ad);
			prst.setString(2,parola);
			prst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void urunEkle(String barkod,String ad,String fiyat,String tur,String stok) {
		String sorgu1="Insert Into stok (barkod,ad,fiyati,tur,stokadet)VALUES(?,?,?,?,?)";
		try {
			prst=con.prepareStatement(sorgu1);
			prst.setString(1,barkod);
			prst.setString(2,ad);
			prst.setString(3,fiyat);
			prst.setString(4,tur);
			prst.setString(5,stok);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static void stokSil(String barkod) {
		  String sorgu="Delete from stok where barkod=?";
		  try {
			prst=con.prepareStatement(sorgu);
			prst.setString(1, barkod);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	static void stokGuncelle(String barkod,String ad,String fiyat,String tur,String stok) {
		String sorgu="UPDATE stok SET barkod=?,ad=?,fiyati=?,tur=?,stokadet=? where barkod=?";
		
		try {
			prst=con.prepareStatement(sorgu);
			prst.setString(1, barkod);
			prst.setString(2, ad);
			prst.setString(3, fiyat);
			prst.setString(4, tur);
			prst.setString(5, stok);
			prst.setString(6, barkod);
			prst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void borcEkle(String ad,String soyad,String telefon,String borc) {
		String sorgu="Insert Into musteri (ad,soyad,telefon,borcu)VALUES (?,?,?,?)";
		try {
			prst=con.prepareStatement(sorgu);
			prst.setString(1,ad);
			prst.setString(2,soyad);
			prst.setString(3,telefon);
			prst.setString(4, borc);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
static ResultSet musteriListele(String sorgu) {
		
		try {
			st=con.createStatement();
			ResultSet rs=st.executeQuery(sorgu);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
static void musteriGuncelle(String ad,String soyad,String telefon,String borc,int silid) {
	String sorgu="UPDATE musteri SET ad=?,soyad=?,telefon=?,borcu=? where id=?";
	try {
		prst=con.prepareStatement(sorgu);
		prst.setString(1,ad);
		prst.setString(2,soyad);
		prst.setString(3,telefon);
		prst.setString(4, borc);
		prst.setInt(5,silid);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static void musteriSil(int id) {
	String sorgu="Delete from musteri where id=?";
	try {
		prst=con.prepareStatement(sorgu);
		prst.setInt(1, id);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

static boolean kasakosul(String tarihal) {
	String sorgu="Select * from kasa where tarih=?";
			try {
				prst=con.prepareStatement(sorgu);
				prst.setString(1,tarihal);
				ResultSet rs=prst.executeQuery();
				return rs.next();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
}
static void paraEkle(String tarihal,Double paraal) {
	boolean ks=kasakosul(tarihal);
	if(ks) {
		String sorgu1="Select * from kasa where tarih=?";
		String sorgu2="UPDATE kasa SET para=? where tarih=?";
		
		try {
			prst=con.prepareStatement(sorgu1);
			prst.setString(1, tarihal);
			ResultSet rs=prst.executeQuery();
			rs.next();
			Double eskimiktar=rs.getDouble("para");
			Double yenimiktar=eskimiktar+paraal;
			
			prst=con.prepareStatement(sorgu2);
			prst.setDouble(1, yenimiktar);
			prst.setString(2, tarihal);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else {
		
		String sorgu="Insert Into kasa (tarih,para)VALUES (?,?)";
		try {
			prst=con.prepareStatement(sorgu);
			prst.setString(1, tarihal);
			prst.setDouble(2, paraal);
			prst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
static ResultSet kasaListele(String sorgu) {
	
	try {
		st=con.createStatement();
		ResultSet rs=st.executeQuery(sorgu);
		return rs;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return null;
	}
}
static void detaySatis(String barkod,String ad,Double fiyat,int adet,String tarih) {
	String sorgu="Insert Into detaylisatis(barkod,ad,fiyati,adet,tarih)VALUES (?,?,?,?,?)";
	try {
		prst=con.prepareStatement(sorgu);
		prst.setString(1, barkod);
		prst.setString(2, ad);
		prst.setDouble(3, fiyat);
		prst.setInt(4, adet);
		prst.setString(5, tarih);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static ResultSet detayliListele(String sorgu) {
	
	try {
		st=con.createStatement();
		ResultSet rs=st.executeQuery(sorgu);
		return rs;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return null;
	}
}
static void detayTabloTemizle() {
	String sorgu="Delete from detaylisatis";
	try {
		prst=con.prepareStatement(sorgu);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void kasasil() {
	String sorgu="Delete from kasa";
	try {
		prst=con.prepareStatement(sorgu);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
static void iadeRaporu(String date,String barkod,String ad,Double toplampara,int adet,String neden) {
	String sorgu="Insert Into iadeTablosu(tarih,barkod,ad,para,adet,nedeni)VALUES(?,?,?,?,?,?)";
	try {
		prst=con.prepareStatement(sorgu);
		prst.setString(1,date);
		prst.setString(2,barkod);
		prst.setString(3,ad);
		prst.setDouble(4, toplampara);
		prst.setInt(5, adet);
		prst.setString(6,neden);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static void kasaiadeEt(String date,Double toplampara) {
	String sorgu1="Select * from kasa where tarih=?";
	String sorgu2="UPDATE kasa SET para=? where tarih=?";
	try {
		prst=con.prepareStatement(sorgu1);
		prst.setString(1, date);
		ResultSet rs=prst.executeQuery();
		rs.next();
		Double eskimiktar=rs.getDouble("para");
		Double yenimiktar=eskimiktar-toplampara;
		
		prst=con.prepareStatement(sorgu2);
		prst.setDouble(1,yenimiktar);
		prst.setString(2, date);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static ResultSet iadeNedeni(String sorgu) {
	
	try {
		st=con.createStatement();
		ResultSet rs=st.executeQuery(sorgu);
		return rs;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return null;
	}
}
static void iadeSil() {
	String sorgu="Delete from iadetablosu";
	try {
		prst=con.prepareStatement(sorgu);
		prst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
static ResultSet raporla(String sorgu) {
	try {
		st=con.createStatement();
		ResultSet rs=prst.executeQuery(sorgu);
		return rs;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return null;
	}
}
}
