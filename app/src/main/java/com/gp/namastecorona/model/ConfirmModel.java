package com.gp.namastecorona.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfirmModel implements Parcelable {


    @SerializedName("latest")
    @Expose
    private Integer latest;
    @SerializedName("locations")
    @Expose
    private List<Location> locations ;
    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("last_updated")
    @Expose
    private String last_updated;

    public ConfirmModel() {
    }

    protected ConfirmModel(Parcel in) {
        if (in.readByte() == 0) {
            latest = null;
        } else {
            latest = in.readInt();
        }
        locations = in.createTypedArrayList(Location.CREATOR);
        source = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (latest == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(latest);
        }
        dest.writeTypedList(locations);
        dest.writeString(source);
    }

    public static final Creator<ConfirmModel> CREATOR = new Creator<ConfirmModel>() {
        @Override
        public ConfirmModel createFromParcel(Parcel in) {
            return new ConfirmModel(in);
        }

        @Override
        public ConfirmModel[] newArray(int size) {
            return new ConfirmModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }


    public static class Coordinates implements Parcelable {

        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;

        protected Coordinates(Parcel in) {
            lat = in.readString();
            _long = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(lat);
            dest.writeString(_long);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
            @Override
            public Coordinates createFromParcel(Parcel in) {
                return new Coordinates(in);
            }

            @Override
            public Coordinates[] newArray(int size) {
                return new Coordinates[size];
            }
        };

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

    public static class Location implements Parcelable {

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


        protected Location(Parcel in) {
            coordinates = in.readParcelable(Coordinates.class.getClassLoader());
            country = in.readString();
            if (in.readByte() == 0) {
                latest = null;
            } else {
                latest = in.readInt();
            }
            province = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(coordinates, flags);
            dest.writeString(country);
            if (latest == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(latest);
            }
            dest.writeString(province);
        }

        public static final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel in) {
                return new Location(in);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
}
