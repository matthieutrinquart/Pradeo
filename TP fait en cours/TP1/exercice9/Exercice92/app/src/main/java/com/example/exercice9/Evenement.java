package com.example.exercice9;


public class Evenement{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private String Titre;
    private String Description;
    public Evenement(int year, int month, int day, int hour, int min, String titre, String description) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        Titre = titre;
        Description = description;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getHour() {
        return hour;
    }
    public int getMin() {
        return min;
    }
    public String getTitre() {
        return Titre;
    }
    public String getDescription() {
        return Description;
    }
}
