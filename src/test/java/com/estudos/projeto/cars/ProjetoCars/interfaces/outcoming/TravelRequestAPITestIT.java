package com.estudos.projeto.cars.ProjetoCars.interfaces.outcoming;

import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.estudos.projeto.cars.ProjetoCars.infraestructure.FileUtils.loadFileContens;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = WireMockConfiguration.DYNAMIC_PORT)
@ActiveProfiles("test")
public class TravelRequestAPITestIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private WireMockServer server;
	
	@BeforeEach
	public void setup() {
		RestAssured.port = port;
	}
	

	
	public void setupServer() {
		server.stubFor(get(urlPathEqualTo("/maps/api/directions/json"))
				.withQueryParam("origin", equalTo("Avenida Paulista, 900"))
				.withQueryParam("destination", equalTo("Avenida Paulista, 1000"))
				.withQueryParam("key", equalTo("AIzaSyCX7NmFwT7Lybxpask3A3BU-Z7jet-7ok"))
				.willReturn(okJson(loadFileContens("/responses/gmaps/sample_response.json"))));
	}
	
	
	
	@Test
	public void testFindNearbyTravelRequest() {
		setupServer();
		
		String passengerId =given().contentType(ContentType.JSON)
		.body(loadFileContens("/requests/passengers_api/create_new_passenger.json"))
		.post("/passengers")
		.then()
		.statusCode(200)
		.body("id",notNullValue())
		.body("name", is("Murilo Ramos da Silva"))
		.extract()
		.body()
		.jsonPath()
		.getString("id");
		
		Map<String, String> data = new HashMap<>();
		data.put("passengerId", passengerId);
		
		
		Integer travelRequestId =  given().contentType(ContentType.JSON)
		.body(loadFileContens("/request/travel_requests_api/create_new_request.json", data))
		.post("/travelRequests")
		.then()
		.statusCode(200)
		.body("id", notNullValue())
		.body("origin", is("Avenida Paulista, 1000"))
		.body("destination", is("Avenida Ipiranga,100"))
		.body("status", is("CREATED"))
		.body("_links.passengenr.tile", is("Murilo Ramos da Silva"))
		.extract()
		.jsonPath()
		.get("id");
		
		given().get("/travelRequest/nearby?currentAddress=Avenida Paulista,900")
		.then()
		.statusCode(200)
		.body("[0].id", notNullValue())
		.body("[0].origin",is("Avenida Paulista, 1000"))
		.body("[0].destination", is("Avenida Ipiranga, 100"))
		.body("[0].status", is("CREATED"));
	}
	
	

}
