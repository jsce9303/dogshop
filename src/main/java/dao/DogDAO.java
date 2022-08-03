package dao;
//오라클 데이터 베이스로 sql구문을 전송하는 클래스 DAO(Data Access Object)
import static db.JdbcUtil.*;
import java.sql.*;
import java.util.ArrayList;
import vo.Dog;

public class DogDAO {
	
	Connection con;
	private static DogDAO boardDAO;
	
	private DogDAO() {
		
	}
	
	public void setConnection(Connection con){
		this.con = con;
	}
	// 생성자 접근 제한자를 private으로 지정했으므로 DAO 객체 값을 얻어갈 때는 반드시 getInstance메소드를 호출해서 얻어가야한다.
	public static DogDAO getInstance(){
		
		if(boardDAO ==null){
			boardDAO = new DogDAO();
		}
		
		return boardDAO;
	}
	//데이터베이스에 저장된 모든 개 상품 정보를 반환하는 메소드를 정의한 부분
	public ArrayList<Dog> selectDogList() {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<Dog> dogList = null;
		
		try {
			pstmt = con.prepareStatement("SELECT * FROM dog"); // 테이블 이름은 간단하게 dog로 지었다.
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //값이 없을 때 까지 계속 반복한다 next()
				dogList = new ArrayList<Dog>();
				
				do {	// do while은 우선 do를 먼저 실행하고 이후 while의 조건에 따라 반복한다.
					dogList.add(new Dog(
							rs.getInt("id")
							,rs.getString("kind")
							,rs.getInt("price")
							,rs.getString("image")
							,rs.getString("country")
							,rs.getInt("height")
							,rs.getInt("weight")
							,rs.getString("content")
							,rs.getInt("readcount")));
				} while (rs.next());
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dogList;
	}
	
	public Dog selectDog(int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dog dog = null;
		
		try {	//데이터 베이스에 저장된 모든 개 정보 중 id값에 따라 반환하는 부분이다.
			pstmt = con.prepareStatement("SELECT * FROM dog WHERE id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dog = new Dog(
						rs.getInt("id")
						,rs.getString("kind")
						,rs.getInt("price")
						,rs.getString("image")
						,rs.getString("country")
						,rs.getInt("height")
						,rs.getInt("weight")
						,rs.getString("content")
						,rs.getInt("readcount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return dog;
	}
	// 읽은횟수 1을 증가키는 부분
	public int updateReadCount(int id) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "";
		
		try {
			sql = "UPDATE dog SET readcount = readcount + 1 WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	// 새로운 개 등록 SQL구문을 적용시키는 부분
	public int insertDog(Dog dog) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO dog VALUES(dog_seq.nextval,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dog.getKind());
			pstmt.setInt(2, dog.getPrice());
			pstmt.setString(3, dog.getImage());
			pstmt.setString(4, dog.getCountry());
			pstmt.setInt(5, dog.getHeight());
			pstmt.setInt(6, dog.getWeight());
			pstmt.setString(7, dog.getContent());
			pstmt.setInt(8, dog.getReadcount());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
}
