package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDTO {

	private long id;
	private String name;
	private List <PlaylistDTO> playlists = new ArrayList<>();
	private int duration;
	private String lyrics; 
}
