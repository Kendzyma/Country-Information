package com.klasha.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PopulationFilterRequest {
    private String order;
    private String orderBy;
    private String country;
}
