/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package savingstudentsinadatabase;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import savingstudentsinadatabase.student;

/**
 *
 * @author styde
 */
public class Text_export_and_import {
    private SQL sql;
    private JFileChooser jfilecoose=new JFileChooser();
    private String str_path=null;
            
            
    public   Text_export_and_import(){
        try {
            sql=new SQL();
        } catch (ClassNotFoundException ex) {
System.out.println(ex);
        } catch (SQLException ex) {
System.out.println(ex);
        }
             
         }
      public  void  import_from_text()
    {
        Scanner T=null;
        Scanner Line_grades=null;

         int res= jfilecoose.showOpenDialog(null);
       if(res ==JFileChooser.APPROVE_OPTION)

       {  File path=jfilecoose.getSelectedFile();
       str_path=path.getAbsolutePath();
       }
       
       if(res==JFileChooser.CANCEL_OPTION)
       {
          str_path="Student.txt";
       }
       
        File  file= new File (str_path);
     if(file.exists()){
        try {
            T= new Scanner(file);
            if (T.hasNextLine())T.nextLine();
            
            String first_name=null;
            String last_name=null;
            int ID=0;
            String sex=null;
            String Grade=null;
            ArrayList <Double> grades= new ArrayList<>();
            ArrayList<String> grades_name=new ArrayList<>();
            ArrayList<Grade> Grade_student=new ArrayList<>();
            double average=0;
            while (T.hasNextLine())
            {
            grades= new ArrayList<>();
      grades_name=new ArrayList<>();
      Grade_student=new ArrayList<>();
      
           String line=T.nextLine().trim();
                       if(line.isEmpty())continue;
          String [] line_split=line.split("\\s{2,}");
            
                first_name=line_split[0];
                last_name=line_split[1];
                ID=Integer.parseInt(line_split[2]);
                sex=line_split[3];
                Grade=line_split[4];
                Grade=Grade.replace("[","");
                Grade=Grade.replace("]","");
                if(!Grade.isEmpty())
                    
                { String [] All_Grade= Grade.trim().split(",");
                for(int i=0;i<All_Grade.length;i++){
                    String [] grade_neme_value=All_Grade[i].trim().split(":");
                    grades_name.add(grade_neme_value[0].trim());
                    grades.add(Double.parseDouble(grade_neme_value[1].trim()));
                }
                double sum=0;
                for(int i=0;i<grades_name.size();i++){
                    Grade_student.add(new Grade(grades_name.get(i),grades.get(i)));
                    sum+=grades.get(i);
                }
                average=sum/grades_name.size();
                }
                                student s=new student(ID,first_name,last_name,sex,Grade_student,average);
                sql.create_or_update(s);

            
            }
                                                                     JOptionPane.showMessageDialog(null,"import succeededy with all data","Sucessful create of the file",1);
       
        } 
         catch(NullPointerException | FileNotFoundException ex){
System.out.println(ex);

        }
        catch(Exception ex){
            Logger.getLogger(Text_export_and_import.class.getName()).log(Level.SEVERE, null, ex);

        }
       
        
     }
    else if ( file.exists()==false)
        {
            int option=JOptionPane.showConfirmDialog(null, "create a file?");
          
            if ( option==JOptionPane.YES_OPTION) {
                String filename=JOptionPane.showInputDialog("input file name");
                file= new File(filename);
                                                            JOptionPane.showMessageDialog(null,"File create succeededy","Sucessful create of the file",1);

            }
        
        }}
      
            public  void  export_from_database(){
               ArrayList <student> stu=new ArrayList<>();
               stu=sql.get_all_data();
               
                 int res= jfilecoose.showSaveDialog(null);
       if(res ==JFileChooser.APPROVE_OPTION)

       {  File path=jfilecoose.getSelectedFile();
        if (!path.getName().toLowerCase().endsWith(".txt")) {
        str_path = path.getAbsolutePath() + ".txt";
                                JOptionPane.showMessageDialog(null,"File selection succeededy","Sucessful selection of the file",1);

    }
        else{
                    str_path = path.getAbsolutePath();
                                            JOptionPane.showMessageDialog(null,"File selection succeededy","Sucessful selection of the file",1);


        }
       }
       
       if(res==JFileChooser.CANCEL_OPTION)
       {
          str_path="newStudentfromdatabase.txt";
                        JOptionPane.showMessageDialog(null,"File selection succeededy","Sucessful selection of the file",1);

       }
       
                   PrintStream file;
        try {
            file=new PrintStream(str_path);
            
file.printf("%-15s  %-15s  %-10s  %-10s  %-25s  %-10s%n",
        "fname", "lname", "ID", "sex", "grades", "average");


for (int i = 0; i < stu.size(); i++) {
    String formattedGrades = formatGrades(
            stu.get(i).get_grades_name(),
            stu.get(i).get_grades()
    );

    file.printf("%-15s  %-15s  %-10d  %-10s  %-25s  %-10.2f%n",
            stu.get(i).get_first_name(),
            stu.get(i).get_last_name(),
            stu.get(i).getID(),
            stu.get(i).get_sex(),    
            formattedGrades,
            stu.get(i).get_average());
}
            file.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Text_export_and_import.class.getName()).log(Level.SEVERE, null, ex);
        }

           
       
        
              
                                                                     JOptionPane.showMessageDialog(null,"export form database succeededy with all data","Sucessful export data",1);

            }

      public  String formatGrades(String[] names, double[] grades) {
    String result = "[";
    
    for (int i = 0; i < names.length; i++) {
        result += names[i] + ":" + grades[i];
        if (i < names.length - 1) {
            result += ",";   
        }
    }
    
    result += "]";
    return result;
}

}
