package com.elearning.server.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MatakuliahList {
    @JsonProperty("content")
	private List<MataKuliah> matakuliahList;
}