package com.elearning.server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExistHasil {
    @JsonProperty("status")
    private boolean status;
    
    @JsonProperty("hasilList")
	private List<Hasil> hasilList;;
}