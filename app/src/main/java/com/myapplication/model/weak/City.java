package com.myapplication.model.weak;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

@SerializedName("name")
@Expose
private String name;
@SerializedName("coord")
@Expose
private Coord coord;
@SerializedName("country")
@Expose
private String country;
@SerializedName("timezone")
@Expose
private Double timezone;
@SerializedName("sunrise")
@Expose
private double sunrise;
@SerializedName("sunset")
@Expose
private double sunset;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Coord getCoord() {
return coord;
}

public void setCoord(Coord coord) {
this.coord = coord;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public double getTimezone() {
return timezone;
}

public void setTimezone(double timezone) {
this.timezone = timezone;
}

public double getSunrise() {
return sunrise;
}

public void setSunrise(double sunrise) {
this.sunrise = sunrise;
}

public double getSunset() {
return sunset;
}

public void setSunset(double sunset) {
this.sunset = sunset;
}

}