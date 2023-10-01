package com.klasha.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateResponse implements Serializable {
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
        private String name;
        private String iso2;
        private String iso3;
        private List<States> states;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIso2() {
            return iso2;
        }

        public void setIso2(String iso2) {
            this.iso2 = iso2;
        }

        public String getIso3() {
            return iso3;
        }

        public void setIso3(String iso3) {
            this.iso3 = iso3;
        }

        public List<States> getStates() {
            return states;
        }

        public void setStates(List<States> states) {
            this.states = states;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class States {
            private String name;
            @JsonProperty("state_code")
            private String StateCode;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStateCode() {
                return StateCode;
            }

            public void setStateCode(String stateCode) {
                StateCode = stateCode;
            }
        }
    }
}
