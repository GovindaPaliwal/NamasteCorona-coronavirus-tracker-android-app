package com.gp.namastecorona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfirmModel {


    @SerializedName("latest")
    @Expose
    private Integer latest;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("source")
    @Expose
    private String source;

    public Integer getLatest() {
        return latest;
    }

    public void setLatest(Integer latest) {
        this.latest = latest;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public class Coordinates {

        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

    }

    public class History {

       /* @SerializedName("1/22/20")
        @Expose
        private String _12220;

        public String get12220() {
            return _12220;
        }

        public void set12220(String _12220) {
            this._12220 = _12220;
        }*/

    }

    public class Location {

        @SerializedName("coordinates")
        @Expose
        private Coordinates coordinates;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("history")
        @Expose
        private History history;
        @SerializedName("latest")
        @Expose
        private Integer latest;
        @SerializedName("province")
        @Expose
        private String province;

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public History getHistory() {
            return history;
        }

        public void setHistory(History history) {
            this.history = history;
        }

        public Integer getLatest() {
            return latest;
        }

        public void setLatest(Integer latest) {
            this.latest = latest;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

    }
}
