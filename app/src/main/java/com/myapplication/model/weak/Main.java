
package com.myapplication.model.weak;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

@SerializedName("temp")
@Expose
private Double temp;
@SerializedName("feels_like")
@Expose
private Double feelsLike;
@SerializedName("temp_min")
@Expose
private Double tempMin;
@SerializedName("temp_max")
@Expose
private Double tempMax;
@SerializedName("pressure")
@Expose
private double pressure;
@SerializedName("sea_level")
@Expose
private double seaLevel;
@SerializedName("grnd_level")
@Expose
private double grndLevel;
@SerializedName("humidity")
@Expose
private double humidity;
@SerializedName("temp_kf")
@Expose
private double tempKf;

public Double getTemp() {
return temp;
}

public void setTemp(Double temp) {
this.temp = temp;
}

public Double getFeelsLike() {
return feelsLike;
}

public void setFeelsLike(Double feelsLike) {
this.feelsLike = feelsLike;
}

public Double getTempMin() {
return tempMin;
}

public void setTempMin(Double tempMin) {
this.tempMin = tempMin;
}

public Double getTempMax() {
return tempMax;
}

public void setTempMax(Double tempMax) {
this.tempMax = tempMax;
}

public double getPressure() {
return pressure;
}

public void setPressure(double pressure) {
this.pressure = pressure;
}

public double getSeaLevel() {
return seaLevel;
}

public void setSeaLevel(double seaLevel) {
this.seaLevel = seaLevel;
}

public double getGrndLevel() {
return grndLevel;
}

public void setGrndLevel(double grndLevel) {
this.grndLevel = grndLevel;
}

public double getHumidity() {
return humidity;
}

public void setHumidity(double humidity) {
this.humidity = humidity;
}

public double getTempKf() {
return tempKf;
}

public void setTempKf(double tempKf) {
this.tempKf = tempKf;
}

}