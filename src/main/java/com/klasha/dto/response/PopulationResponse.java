package com.klasha.dto.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PopulationResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean error;
    private String msg;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private String country;
        private String code;
        private String iso3;
        private List<PopulationCount> populationCounts;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIso3() {
            return iso3;
        }

        public void setIso3(String iso3) {
            this.iso3 = iso3;
        }

        public List<PopulationCount> getPopulationCounts() {
            return populationCounts;
        }

        public void setPopulationCounts(List<PopulationCount> populationCounts) {
            this.populationCounts = populationCounts;
        }
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PopulationCount {
            private int year;
            private long value;

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public long getValue() {
                return value;
            }

            public void setValue(long value) {
                this.value = value;
            }
        }
    }
}
