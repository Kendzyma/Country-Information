package com.klasha.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PopulationFilterResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean error;
    private String msg;
    private List<DataDto> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataDto> getData() {
        return data;
    }

    public void setData(List<DataDto> data) {
        this.data = data;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDto {
        private String city;
        private String country;
        private List<PopulationCountDto> populationCounts;



        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<PopulationCountDto> getPopulationCounts() {
            return populationCounts;
        }

        public void setPopulationCounts(List<PopulationCountDto> populationCounts) {
            this.populationCounts = populationCounts;
        }
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PopulationCountDto {
        private String year;
        private String value;
        private String sex;
        private String reliability;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getReliability() {
            return reliability;
        }

        public void setReliability(String reliability) {
            this.reliability = reliability;
        }
    }
}
