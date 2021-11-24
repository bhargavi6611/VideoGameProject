package Testcase;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

public class TC_VedeoGameAP 
{
	@Test(priority=1)
	public void TC_getAllVedeoGames()
	{
		given()
		.when()
		   .get("http://localhost:8081/app/videogames")
          .then()
           .statusCode(200);
		
	}
	@Test(priority=2)
	public void test_addNewVideoGame()
	{
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("id","100");
		data.put("name","Spider-Man");
		data.put("realseDate","2019-19-20t08:55:58.510Z");
		data.put("reviewScore","5");
		data.put("category","Universal");
		data.put("rating","Universal");
		
		Response res=
				given()
				.contentType("application/json")
				.body(data)
				.when().post("http://localhost:8081/app/videogames")
				.then()
				.statusCode(200)
				.log().body()
				.extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);
		
		
	
	}
	@Test(priority=3)
	public void test_getVideoGame()
	{
		given()
		.when()
		.get("http://localhost:8081/app/videogames/100")
		.then()
		.statusCode(200)
		.log().body()
		.body("videoGame.id", equalTo("100"))
		.body("videoGame.name", equalTo("Spider-Man"));
		
		
	}
	
	@Test(priority=4)
	public void test_putVideoGame()
	{
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("id","100");
		data.put("name","Pacman");
		data.put("realseDate","2020-19-20t08:55:58.510Z");
		data.put("reviewScore","4");
		data.put("category","Adevnture");
		data.put("rating","Universal");		
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.put("http://localhost:8081/app/videogames/100")
		.then()
		.statusCode(200)
		.log().body()
		.body("videoGame.id", equalTo("100"))
		.body("videoGame.name", equalTo("Pacman"));
		
	}
	
	@Test(priority=5)
	public void test_deleteVideoGame()
	{
		Response res = 
		given()
		.when()
		.delete("http://localhost:8081/app/videogames/100")
		.then()
		.log().body()
		.extract().response();
		
		String jsonString = res.asPrettyString();
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
		
		
	}
		
	
}
