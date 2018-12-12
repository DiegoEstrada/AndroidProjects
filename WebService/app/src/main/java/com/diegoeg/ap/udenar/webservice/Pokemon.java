package com.diegoeg.ap.udenar.webservice;



public class Pokemon {

    private String name;
    private String hab;
    private String type;
    private String weigth;
    private String url;



    public Pokemon(String name, String hab, String type, String url, String exp){
        this.name = name;
        this.hab = hab;
        this.type = type;
        this.url  = url;
        this.weigth = exp;

    }

    public Pokemon(String name, String url){
        this.name = name;
        this.url = url;
    }

    public Pokemon(String nombre){
        this.name = nombre;
    }


    public String getUrl() {
        return url;
    }


    public String getName() {
        return name;
    }

    public String getHab() {
        return hab;
    }

    public String getType() {
        return type;
    }

    public String getWeigth() {
        return weigth;
    }


    @Override
    public  String toString(){
        return "Pokemon "+getName();
    }
}
