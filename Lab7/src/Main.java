import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        DBConnection duc = new DBConnection();
        duc.getConnection();
        People test = new People("ducdep",23, Job.STUDENT,true);
        duc.insertDB("people",2,test);
        ResultSet rs = duc.execQuery("SELECT * FROM people");
        while(rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
        }


    }
}
