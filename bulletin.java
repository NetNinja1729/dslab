package CSE105_DSLAB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bulletin {
    static Connection con;
    static Statement st;
    static ResultSet rs;

    public static void main(String[] args) throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BulletinBoard", "root", "");
            st = con.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (con != null) {
            System.out.println("Connected to server...\n");
            System.out.println("please Enter your group name:\n");
            try {
                rs = st.executeQuery("select*from Groups");
                while (rs.next()) {
                    System.out.println(rs.getString("Group"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String grpname;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            grpname = br.readLine();
            System.out.print("Enter username:\t");
            String usr = br.readLine();
            System.out.println("\nenter password:\t");
            String pass = br.readLine();
            try {
                String query = "select * from " + grpname.trim() + "Mem where User=\"" + usr + "\"";
                // System.out.println(query);
                rs = st.executeQuery(query);
                while (rs.next()) {
                    if (rs.getString("password").equals(pass)) {
                        System.out.println("login successfull! Have A NICE DAY!!!");
                        while (true) {
                            System.out.println("enter your choice:\n");
                            System.out.println("1.read message:\n");
                            System.out.println("2.write message:\n");
                            System.out.println("3.logout:\n");
                            int option = Integer.parseInt(br.readLine());
                            switch (option) {
                                case 1:
                                    query = "select * from " + grpname + "Msg";
                                    // System.out.println(query);
                                    rs = st.executeQuery(query);
                                    while (rs.next())
                                        System.out
                                                .println(rs.getString("user") + ":" + rs.getString("message") + "\n\n");
                                    break;
                                case 2:
                                    System.out.println("enter your message :\n");
                                    String msg = br.readLine();
                                    query = "insert into " + grpname + "Msg values('" + msg + "','" + usr + "')";
                                    // System.out.println(query);
                                    st.executeUpdate(query);
                                    break;
                                case 3:
                                    System.exit(1);
                                default:
                                    System.out.println("choose a valid option!!\n");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("please login again\n");
                        System.exit(1);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
