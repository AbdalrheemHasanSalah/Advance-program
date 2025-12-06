/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package savingstudentsinadatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author styde
 */
public class SQL  {
    private Connection con=null;
    private PreparedStatement prep=null;
    private PreparedStatement prep2=null;
    private ResultSet rs=null;

     private Connection  makeConnection() throws ClassNotFoundException, SQLException
    {
        String Driver="sun.jdbc.odbc.JdbcOdbcDriver";
        String URL="jdbc:odbc:studb";
        
        Class.forName(Driver);
        
        return DriverManager.getConnection(URL);
 
    }
     public SQL()throws ClassNotFoundException, SQLException{
         con=makeConnection();
     
     }
     
     public  void  Add_new_student(student s)throws ClassNotFoundException, SQLException{
         if(search_id(s.getID())== false){
         prep=con.prepareStatement("INSERT INTO Students ([Student_first_Name],[Student_last_Name],[ID],[sex],[average]) VALUES(?,?,?,?,?)");
         prep2=con.prepareStatement("INSERT INTO grades (ID,grades_name,grades) VALUES(?,?,?)" );
         prep.setString(1,s.get_first_name());
         prep.setString(2,s.get_last_name());
         prep.setInt(3,s.getID());
         prep.setString(4,s.get_sex());
         prep.setDouble(5,s.get_average());
         prep.executeUpdate();
         
         prep2.setInt(1,s.getID());
         for(int i=0;i<s.get_index();i++)
         {
             prep2.setString(2,s.get_grades_name()[i]);
             prep2.setDouble(3,s.get_grades()[i]);
             prep2.executeUpdate();
         }
         
         }
         else {

              JOptionPane.showMessageDialog(null,"the id input is existing","Exist id",0);
         }

     }
     
     
     public void delete_student(int id){
        try {
            prep=con.prepareStatement("DELETE FROM Students WHERE ID = ?");
            prep2=con.prepareStatement("DELETE FROM grades WHERE ID = ?");

            prep.setInt(1,id);
            prep2.setInt(1,id);
            prep.executeUpdate();
            prep2.executeUpdate();
            
        } catch (SQLException ex) {
System.out.println(ex);
        }

         
     }
     
     
     
     public student Search_student(String search_term){ 
          PreparedStatement prep=null;
            ResultSet rs=null;
         student s = null;
        try {
            prep=con.prepareStatement("SELECT * FROM Students AS s LEFT JOIN grades AS g ON s.ID = g.ID WHERE (s.Student_first_Name = ? OR s.ID = ? OR g.grades_name = ?)");
            prep.setString(1,search_term);
             if(search_term != null && search_term.matches("\\d+"))
             {  
                 prep.setInt(2,Integer.parseInt(search_term));

             }   

             else 
             {
              prep.setInt(2,-999999);
             }
                         prep.setString(3,search_term);

                        rs=prep.executeQuery();
 while(rs.next()){
int id=rs.getInt("ID");
               s=new student(id,rs.getString("Student_first_Name"),rs.getString("Student_last_Name"),rs.getString("sex"),ARRY_GRADE(id),rs.getInt("average"));

                                       }
            
 


             }
       
        
        
        catch( SQLException ex){
            

System.out.print(ex);
        }
        
         return s;
     }
     
     public void create_or_update(student s) throws SQLException, ClassNotFoundException{
            PreparedStatement prep=null;
            PreparedStatement prep2=null;
            PreparedStatement prep3=null;

            ResultSet rs=null;
            ResultSet rs2=null;

           ArrayList<String> gradename=new ArrayList<>();
         if(search_id(s.getID())){
             prep=con.prepareStatement("SELECT * FROM grades WHERE ID = ?");
             prep.setInt(1,s.getID());
             rs=prep.executeQuery();
             while(rs.next())
             {gradename.add(rs.getString("grades_name"));
             }
             prep2=con.prepareStatement("UPDATE grades SET grades = ? WHERE ID = ? AND grades_name = ? ");
             prep3=con.prepareStatement("INSERT INTO grades (ID,grades_name,grades) VALUES (?,?,?)");
             for(int i=0;i<s.get_grades_name().length;i++){
                 if(gradename.contains(s.get_grades_name()[i])){
                     prep2.setDouble(1,s.get_grades()[i]);
                     prep2.setInt(2,s.getID());
                     prep2.setString(3,s.get_grades_name()[i]);
                     prep2.executeUpdate();
                 }
                 else{
                     prep3.setInt(1,s.getID());
                     prep3.setString(2,s.get_grades_name()[i]);
                     prep3.setDouble(3,s.get_grades()[i]);
                     prep3.executeUpdate();

                 }
             }
         }
         else {
             Add_new_student(s);
         }
         
         
     }
     
         public ArrayList<Grade> ARRY_GRADE(int id) throws SQLException{
            ArrayList<Grade> grade=new ArrayList<>();
           PreparedStatement prep=null;
            ResultSet rs=null;
         try {
            prep=con.prepareStatement("SELECT * FROM grades AS G  WHERE G.ID = ?");
            prep.setInt(1, id);
            rs=prep.executeQuery();
            while(rs.next()){
                   grade.add(new Grade(rs.getString("grades_name"),rs.getInt("grades")));


            }

        } catch (SQLException ex) {
System.out.println(ex);
        }
        finally {
        try {
            if (rs != null) rs.close();
            if (prep != null) prep.close();
        } catch (SQLException e) {
           System.out.println(e);
        }
        return grade;
         }  
         
     }
         
     public  ArrayList<student> get_all_data(){
         ArrayList<student> students=new ArrayList<>();
                  ArrayList<Grade> grade=new ArrayList<>();
            PreparedStatement prep=null;
            ResultSet rs=null;
        try {
            
            prep=con.prepareStatement("SELECT * FROM Students");
            rs=prep.executeQuery();
            while(rs.next()){
                int id=rs.getInt("ID");
                if(search_id(id))
                {
                grade=ARRY_GRADE(id);
             students.add(new student(id,rs.getString("Student_first_Name"),rs.getString("Student_last_Name"),rs.getString("sex"),grade,rs.getInt("average")));
                }
            }
    
        } catch (SQLException ex) {
System.out.println(ex);
        }
         finally {
    try {
        if (rs != null) rs.close();
        if (prep != null) prep.close();
    } catch (SQLException e) {
        System.out.println(e);
    }
        return students;

     } 
     
     }
     
 
      public boolean search_id(int id) throws SQLException {
        try {
            prep=con.prepareStatement("SELECT * FROM Students WHERE ID = ?");
          prep.setInt(1, id);
        rs=prep.executeQuery();
                        return rs.next()==true;


        } catch (SQLException ex) {
System.out.println(ex);
        return false; // في حالة حدوث خطأ نرجع false
    } finally {
        try {
            if (rs != null) rs.close();
            if (prep != null) prep.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
      
        
        
         
     }
    
}