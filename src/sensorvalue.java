import java.sql.*;
import java.util.Scanner;

public class sensorvalue {
    public static void main(String[] args) {
        int temperature,humadity,moisture,date;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/homeautomationdb","root","");
        }
        catch (Exception e){
            System.out.println((e));
        }
        int choice;
        while (true) {
            System.out.println("Select the option");
            System.out.println("1.Add values");
            System.out.println("2.View values");
            System.out.println("3.Search values by date");
            System.out.println("4.Exit ");
            System.out.println("*****************");
            System.out.println("ENTER YOUR CHOICE:--");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("add values");
                    System.out.println("Enter temperature");
                    temperature=sc.nextInt();
                    System.out.println("Enter the humadity");
                    humadity=sc.nextInt();
                    System.out.println("Enter the moisture");
                    moisture=sc.nextInt();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/homeautomationdb","root","");
                        String sql="INSERT INTO `senservalues`(`temperature`, `humadity`, `moisture`, `date`) VALUES  (?,?,?,now())";
                        PreparedStatement stmt=con.prepareStatement((sql));
                        stmt.setInt(1,temperature);
                        stmt.setInt(2,humadity);
                        stmt.setInt(3,moisture);

                        stmt.executeUpdate();
                        System.out.println("value inserted successfully.........!");
                    }
                    catch (Exception e){
                        System.out.println((e));
                    }

                    break;
                case 2:
                    System.out.println("view values");
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/homeautomationdb","root","");
                        String sql="SELECT `id`, `temperature`, `humadity`, `moisture`, `date` FROM `senservalues` ";
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql);
                        while ((rs.next())) {
                            String gettemperature = rs.getString(("temperature"));
                            String gethumadity= rs.getString(("humadity"));
                            String getmoisture = rs.getString(("moisture"));
                            String getdate = rs.getString(("date"));
                            System.out.println("temperature=" + gettemperature);
                            System.out.println("humadity=" + gethumadity);
                            System.out.println("moisture=" + getmoisture);
                            System.out.println("date=" + getdate);
                            System.out.println("\n");
                        }
                    }
                    catch (Exception e){
                        System.out.println((e));
                    }
                    break;
                case 3:
                    System.out.println("search values by date");
                    System.out.println("enter date to search");
                    String  date1= sc.next();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/homeautomationdb","root","");
                        String sql="SELECT `temperature`, `humadity`, `moisture` FROM `senservalues` WHERE `date`='"+date1+"' ";
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql);
                        while ((rs.next())) {
                            temperature=rs.getInt("temperature");
                            humadity=rs.getInt("humadity");
                            moisture=rs.getInt("moisture");

                            System.out.println("temperature=" + temperature);
                            System.out.println("humadity=" + humadity);
                            System.out.println("moisture=" + moisture);
                            System.out.println("\n");
                        }
                    }
                    catch (Exception e){
                        System.out.println((e));
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;


            }
        }


    }

}
