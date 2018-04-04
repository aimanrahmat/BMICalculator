package com.example.aimanrahmat.bmicalculator;


public class Record {
    private int id;
    private String bmi;
    private String date;

    public Record(){

    }

    public Record(int id,String bmi, String date){
        this.id=id;
        this.bmi=bmi;
        this.date=date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public void setDate(String date){ this.date = date;}

    public int getId() {
        return id;
    }
    public String getBmi() {
        return bmi;
    }
    public String getDate(){ return date; }
}
