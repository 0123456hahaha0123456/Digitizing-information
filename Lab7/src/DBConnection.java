import java.sql.*;

public class DBConnection {
    Connection con = null;
    public Connection getConnection(){

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "123456";
        String port = "5432";
        try{
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url,user,pass);
            System.out.println("Successfully to connect DB");
            return this.con;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet execQuery(String query) throws SQLException {
        return this.con.createStatement().executeQuery(query);
    }

    // String[] value = {id, name, age, Job, onTV)
    public int insertDB(String table,int id,People people) throws SQLException {
        StringBuilder vals = new StringBuilder();
        vals.append(id).append(",");
        vals.append("'").append(people.getName()).append("',");
        vals.append(people.getAge()).append(",");
        vals.append("'").append(people.getJob().toString()).append("',");
        vals.append("'").append(people.getOnTV()).append("',");
        vals.setLength(vals.length()-1);

        String query = String.format("INSERT INTO %s VALUES (%s)", table,
                vals.toString());

        return this.con.createStatement().executeUpdate(query);
    }
    //String[] value = {condition1, condition2}
    public int deleteDB(String table,String key)throws SQLException{

        int i =0;
        while (i<key.length()){
            if (key.charAt(i)>='0' && key.charAt(i)<='9') break;
            i++;
        }
        String name = key.substring(0,i);
        String age = key.substring(i,key.length());

        String query = String.format("DELETE FROM %s WHERE (name = '%s' AND age = %s);",table,name,age);
        return this.con.createStatement().executeUpdate(query);
    }

    public int updateDB(String table,String first, People last) throws SQLException{
        int i =0;
        while (i<first.length()){
            if (first.charAt(i)>='0' && first.charAt(i)<='9') break;
            i++;
        }
        String name = first.substring(0,i);
        String age = first.substring(i,first.length());
        System.out.println("Loan ham");
        String query = String.format("UPDATE %s SET name = '%s', age = %s,job = '%s',ontv ='%s'" +
                "WHERE (name = '%s' AND age = %s);", table,last.getName(),String.valueOf(last.getAge()),last.getJob().toString(),last.getOnTV(),name,age);
        System.out.println("hahaha");
        return this.con.createStatement().executeUpdate(query);
    }

}
