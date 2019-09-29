package com.elearning.server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KelasList {
    @JsonProperty("content")
	private List<Kelas> listKelas;
}