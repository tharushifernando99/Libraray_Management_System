package com.example.libaray_management_system;

public class Copy {
    private int id;
    private int id2;
    private String accessNo;

    public Copy (int id,int id2,String accessNo){
        this.id = id;
        this.id = id2;
        this.accessNo = accessNo;
    }

    public int getId(){return id;}
    public int getId2(){return id2;}
    public String getaccessNo(){return accessNo;}
    public void setaccessNo(String accessNo){this.accessNo = accessNo;}

}
