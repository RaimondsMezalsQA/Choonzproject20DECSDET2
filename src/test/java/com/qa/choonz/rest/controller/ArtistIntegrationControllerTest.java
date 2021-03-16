package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = "classpath:test-drop-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ArtistIntegrationControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModelMapper mapper;

	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist, ArtistDTO.class);
	}

	@Autowired
	private ObjectMapper jsonifier;

	private final String URI = "/artists";
	private final Artist A_TEST_1 = new Artist(1L, "Kanye West");

	@Test
	void testCreate() throws Exception {
		ArtistDTO testArtist = mapToDTO(new Artist("Drake"));
		testArtist.setId(2L);
		String artistToJSON = this.jsonifier.writeValueAsString(testArtist);
		RequestBuilder rB = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(artistToJSON)
				.accept(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(artistToJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testReadAll() throws Exception {

	}

	@Test
	void testReadByID() throws Exception {

//		RequestBuilder rB = get(URI + "/read/" + A_TEST_1.getId()).accept(MediaType.APPLICATION_JSON);
//		ResultMatcher checkStatus = status().isOk();
//		this.mvc.perform(rB).andExpect(checkStatus)
//				.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(A_TEST_1))));

	}

	@Test
	void testUpdate() throws Exception {

	}

	@Test
	void testDelete() throws Exception {

	}

}
