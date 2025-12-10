/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package savingstudentsinadatabase;

import java.sql.Array;
import java.util.ArrayList;


/**
 *
 * @author styde
 */
 class Grade {
    private String grade_Name;
    private double grade_Value;


    public Grade(String grade_Name, double grade_Value) {
        this.grade_Name = grade_Name;
        this.grade_Value = grade_Value;
    }

    public String getGradeName() {
        return grade_Name;
    }

    public double getGradeValue() {
        return grade_Value;
    }
    public void setGradeName(String grade_name) {
        grade_Name=grade_name;
    }

   public void setGradeValue(double grade_value) {
       grade_Value=grade_value;
    }
}


public class student {
    private int ID;
    private String first_name;
    private String last_name;
    private String sex;
    private ArrayList<Grade> grade_list=new ArrayList<>();
    private String grades_name[]=null;
    private double grades[]=null;
    private double average;
    private int index;
    public student(){
        super();
        
        
    }
     public student(int id,String fname ,String lname,String gender ,String gradename[], double grade[],int count,double avg){
        super();
        ID=id;
        first_name=fname;
        last_name=lname;
        sex=gender;
        grades_name=new String[count];
        grades=new double [count];
        for(int i=0;i<grade.length;i++)
        {  grades_name[i]=gradename[i];
            grades[i]=grade[i];
       }
        average =avg;
        this.index=count;
    }

        public student(int id,String fname ,String lname,String gender ,ArrayList <Grade> grade,double avg){
                    super();
              ID=id;
        first_name=fname;
        last_name=lname;
        sex=gender;
        grade_list.addAll(grade);
        average =avg;
        index=grade_list.size();
         grades_name=new String[index];
        grades=new double [index];
for(int i=0;i<index;i++){
    grades_name[i]=grade_list.get(i).getGradeName();
    grades[i]=grade_list.get(i).getGradeValue();
}     
                
            }
         public student(int id,String fname ,String lname,String gender ,ArrayList <Grade> grade){
                    super();
              ID=id;
        first_name=fname;
        last_name=lname;
        sex=gender;
        grade_list.addAll(grade);
         index=grade_list.size();
grades_name=new String[index];
        grades=new double [index];
     for(int i=0;i<index;i++){
    grades_name[i]=grade_list.get(i).getGradeName();
    grades[i]=grade_list.get(i).getGradeValue();}
     
                   double sum=0;
                   double size=grade_list.size();
       for(int i=0;i<grade_list.size();i++){
         sum+= grade_list.get(i).getGradeValue();
       }
       try
       {average=sum/size;}
       catch(ArithmeticException e){
           System.out.println(e);
           average=0;
       }
            }

   public double computedaverage(ArrayList <Grade> g){
       double sum=0;
       double size=g.size();
       for(int i=0;i<g.size();i++){
         sum+= g.get(i).getGradeValue();
       }
       try{
       average=sum/size;
       }
       catch(ArithmeticException e){
           average=0;
       }
       return average;
   }
public double computedaverage(double g[]){
       double sum=0;
       double size=g.length;
       for(int i=0;i<g.length;i++){
         sum+= g[i];
       }
        try{
       average=sum/size;
       }
       catch(ArithmeticException e){
           average=0;
       }
       return average;
   }

   

   public int getID(){
       return ID;
   }
    public String get_first_name(){
       return first_name;
   }
    
    public String get_last_name(){
       return last_name;
   }
    public String get_sex(){
        return sex;
    }
     public String[] get_grades_name(){
        return grades_name;
    }

    public double[] get_grades(){
        return grades;
    }
     public double get_average(){
        return average;
    }
    
      public int get_index(){
       return index;
   }
      public void setID(int id){
        ID=id;
   }
    public void set_first_name(String fname){
       first_name=fname;
   }
    
    public void set_last_name(String lname){
       last_name=lname;
   }
    public void set_sex(String gender){
        sex=gender;
    }
     public void set_grades_name(String g[]){
       for(int i=0;i<g.length;i++)
         grades_name[i]=g[i]; 
    }
    public void set_grades(double g[]){
       for(int i=0;i<g.length;i++)
         grades[i]=g[i]; 
    }
     public void set_average(double avg){
         average =avg;
    }

}
