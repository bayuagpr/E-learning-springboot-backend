package com.elearning.server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExistEnroll {
    @JsonProperty("status")
    private boolean status;
    
    @JsonProperty("enrollmentList")
	private List<Enrollment> enrollmentList;
}