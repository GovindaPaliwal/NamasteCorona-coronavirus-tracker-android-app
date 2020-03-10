package com.gp.namastecorona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllModel {
    @SerializedName("latest")
    @Expose
    private Latest latest;

    @SerializedName("deaths")
    @Expose
    private ConfirmModel deaths;

    @SerializedName("confirmed")
    @Expose
    private ConfirmModel confirmed;

    @SerializedName("recovered")
    @Expose
    private ConfirmModel recovered;

    public Latest getLatest() {
        return latest;
    }

    public void setLatest(Latest latest) {
        this.latest = latest;
    }

    public class Latest {

        @SerializedName("confirmed")
        @Expose
        private Integer confirmed;
        @SerializedName("deaths")
        @Expose
        private Integer deaths;
        @SerializedName("recovered")
        @Expose
        private Integer recovered;

        public Integer getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(Integer confirmed) {
            this.confirmed = confirmed;
        }

        public Integer getDeaths() {
            return deaths;
        }

        public void setDeaths(Integer deaths) {
            this.deaths = deaths;
        }

        public Integer getRecovered() {
            return recovered;
        }

        public void setRecovered(Integer recovered) {
            this.recovered = recovered;
        }
    }

    public ConfirmModel getConfirmed() {
        return confirmed;
    }

    public ConfirmModel getDeaths() {
        return deaths;
    }

    public ConfirmModel getRecovered() {
        return recovered;
    }
}