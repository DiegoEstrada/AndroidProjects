package com.diegoeg.ap.udenar.webservice;



public class Pokemon {

    private String name;
    private String hab1;
    private String hab2;
    private int exp;
    private String url;

    public Pokemon(String name, String hab1, String hab2, String url, int exp){
        this.name = name;
        this.hab1 = hab1;
        this.hab2 = hab2;
        this.url = url;
        this.exp = exp;

    }

    public Pokemon(String name, String hab1, String hab2,  int exp){
        this.name = name;
        this.hab1 = hab1;
        this.hab2 = hab2;
        this.url = url;
        this.exp = exp;

    }

    public Pokemon(String nombre){
        this.name = nombre;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getHab1() {
        return hab1;
    }

    public String getHab2() {
        return hab2;
    }

    public int getExp() {
        return exp;
    }
}
