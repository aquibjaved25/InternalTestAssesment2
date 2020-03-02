package com.myapplication.model.weak;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherForecastModel {

@SerializedName("cod")
@Expose
private String cod;
@SerializedName("message")
@Expose
private double message;
@SerializedName("cnt")
@Expose
private double cnt;
@SerializedName("list")
@Expose
private java.util.List<com.myapplication.model.weak.List> list = null;
@SerializedName("city")
@Expose
private City city;

public String getCod() {
return cod;
}

public void setCod(String cod) {
this.cod = cod;
}

public double getMessage() {
return message;
}

public void setMessage(double message) {
this.message = message;
}

public double getCnt() {
return cnt;
}

public void setCnt(double cnt) {
this.cnt = cnt;
}

public java.util.List<com.myapplication.model.weak.List> getList() {
return list;
}

public void setList(java.util.List<com.myapplication.model.weak.List> list) {
this.list = list;
}

public City getCity() {
return city;
}

public void setCity(City city) {
this.city = city;
}

}