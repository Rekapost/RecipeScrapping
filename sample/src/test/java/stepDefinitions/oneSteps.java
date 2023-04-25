package stepDefinitions;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.AllureId;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static org.hamcrest.Matchers.equalTo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.qameta.allure.*;
import io.qameta.allure.allure_maven.*;

public class oneSteps {
	Response response1,response2,response11,response22,update_response1,update_response2,getAll_response;
	Object program_Name1,program_Name2;
	int day, year,month,secnd, minute, hour,randomInt;
	String payload,id,name,DMYHMS,monthName;
	JSONObject request1,request2,batch_request1,batch_request2,update_request1,update_request2;
	boolean isAPIHit = false;
	private static String BASE_URL="http://lms-backend-service.herokuapp.com/lms";
	String first_programId,first_programName,second_programId,second_programName;
	Response batch_response1,batch_response2,delete_response,batch_response11,batch_response22,update_batch_response2,getAll_batch_response;
	Object batch_Name1,batch_Name2;
	String first_batchId,first_batchName,second_batchId,second_batchName;
	
	Date date1 =  new Date();
	Timestamp timestamp2 = new Timestamp(date1.getTime());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	String dtString = sdf.format(timestamp2);
	LocalDateTime currentDateTime = LocalDateTime.now();
	
//	GregorianCalendar date = new GregorianCalendar();
//	day = date.get(Calendar.DAY_OF_MONTH);		
//	SimpleDateFormat f = new SimpleDateFormat("MMM");
//	//String monthName=f.format(new Date());			
//	Random randomGenerator=new Random();
//	//int randomInt=randomGenerator.nextInt(100);
	
	@Feature("program/ batch feature")
	@Description(" post/put/delete/get")
	@Step(" open service url")
	@Severity(SeverityLevel.NORMAL)
	@Given("A Service with url")
	public void a_service_with_url() {
		RestAssured.baseURI=BASE_URL;	
	}

	@Given("create first program  with request body with program name , id , status , creation time and modified time")
	public void create_first_program_with_request_body_with_program_name_id_status_creation_time_and_modified_time() {
		// ********************** Generating Random serial number for program name
		GregorianCalendar date = new GregorianCalendar();
		day = date.get(Calendar.DAY_OF_MONTH);		
		SimpleDateFormat f = new SimpleDateFormat("MMM");
		monthName=f.format(new Date());			
		Random randomGenerator=new Random();
		randomInt=randomGenerator.nextInt(100);
		program_Name1 =(monthName+day+"-"+"NinjaSurvivors-1-"+"SDET-"+randomInt);
//		System.out.println(program_Name);		
//		Date date1 =  new Date();
//		Timestamp timestamp2 = new Timestamp(date1.getTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//		String dtString = sdf.format(timestamp2);
//		LocalDateTime currentDateTime = LocalDateTime.now();
		//// *********************** giving Request Body/payLoad		 
		request1=new JSONObject();
		//request.put("programid",randomInt);
		request1.put("programName",program_Name1);
		request1.put("programDescription", "LMS-Hackathon-SDET-NinjaSurvivors-1");
		request1.put("programStatus", "Active");
		request1.put("creationTime",dtString);
		request1.put("lastModTime",dtString);
		//System.out.println(request1.toJSONString());
	}
	@Description(" post/put/delete/get")
	@Step(" open service url")
	@Severity(SeverityLevel.NORMAL)

	@When("submit post request1")
	
	public void submit_post_request1() {
		RestAssured.requestSpecification = given()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(request1.toJSONString());
		response1 = RestAssured.requestSpecification.when()
				.post("/saveprogram").then().extract().response();

		String responseBody=response1.getBody().asString();
		JsonPath js =new JsonPath(responseBody);
		first_programId=js.getString("programId");
		first_programName=js.getString("programName");
		System.out.println("programId : "+first_programId);
		System.out.println("programName : "+first_programId);
	}

	@Then("api returns status code {int}")
	public void api_returns_status_code(Integer int1) {
		if (response1.getStatusCode()==201){
			System.out.print("created program 1: Response--> "+response1.getBody().asPrettyString());	
			response1.then().statusCode(int1);		
			isAPIHit = true;
		}else
		{
			System.out.println("Posting is not success. Error code : " +response1.getStatusCode()) ;
		}
	}

	@Then("first program is created")
	public void first_program_is_created() {
		if (isAPIHit) {
			//System.out.print("created Program: Response--> "+response1.getBody().asPrettyString());
			response1.then().assertThat().body("programName",equalTo(program_Name1));
			Assert.assertEquals(response1.statusCode(), 201);
			Assert.assertEquals(response1.jsonPath().getString("programName"), request1.get("programName"));				
		}
	}
	
	@Then("create second program with request body with program name , id , status , creation time and modified time")
	public void create_second_program_with_request_body_with_program_name_id_status_creation_time_and_modified_time() {
		// ********************** Generating Random serial number for program name
				GregorianCalendar date = new GregorianCalendar();
				day = date.get(Calendar.DAY_OF_MONTH);		
				SimpleDateFormat f = new SimpleDateFormat("MMM");
				monthName=f.format(new Date());			
				Random randomGenerator=new Random();
				randomInt=randomGenerator.nextInt(1000);
				program_Name2 =(day+monthName+"-"+"Survivors-2-"+"SDET-"+randomInt);		
				Date date1 =  new Date();
				Timestamp timestamp2 = new Timestamp(date1.getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
				String dtString = sdf.format(timestamp2);
				LocalDateTime currentDateTime = LocalDateTime.now();
				//// *********************** giving Request Body/payLoad		 
				request2=new JSONObject();
				//request.put("programid",randomInt);
				request2.put("programName",program_Name2);
				request2.put("programDescription", "LMS-Hackathon-SDET-NinjaSurvivors-2");
				request2.put("programStatus", "Active");
				request2.put("creationTime",dtString);
				request2.put("lastModTime",dtString);
				//System.out.println(request2.toJSONString()); 
				RestAssured.requestSpecification = given()
						.header("Content-Type","application/json")
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(request2.toJSONString());
				response2 = RestAssured.requestSpecification.when()
						.post("/saveprogram").then().extract().response();
				System.out.print("second Program: Response--> "+response2.getBody().asPrettyString());	
				String responseBody=response2.getBody().asString();
				JsonPath js =new JsonPath(responseBody);
				second_programId=js.getString("programId");
				second_programName=js.getString("programName");
				System.out.println("programId : "+second_programId);
				System.out.println("programName : "+second_programName);
	}

	@When("submit post request2")
	public void submit_post_request2() {
		if (isAPIHit) {
			System.out.print("created second Program: Response--> "+response2.getBody().asPrettyString());
			response2.then().assertThat().body("programName",equalTo(program_Name2));
			Assert.assertEquals(response2.statusCode(), 201);
			Assert.assertEquals(response2.jsonPath().getString("programName"), request2.get("programName"));				
		}
	}
	
	@Then("second program is created")
	public void second_program_is_created() {
		if (isAPIHit) {
			//System.out.print("created Program: Response--> "+response1.getBody().asPrettyString());
			response2.then().assertThat().body("programName",equalTo(program_Name2));
			Assert.assertEquals(response2.statusCode(), 201);
			Assert.assertEquals(response2.jsonPath().getString("programName"), request2.get("programName"));				
		}
	}
	


	@When("User make a GET request with an end point progranId")
	public void user_make_a_get_request_with_an_end_point_progran_id() {
		System.out.println("input path parameter:"+first_programId);
		RestAssured.requestSpecification = given();
		response11 = RestAssured.requestSpecification.when()
				.get("/programs/"+first_programId).then().extract().response();
		System.out.print("Get first Program by ID: Response--> "+response11.getBody().asPrettyString());	
		
		System.out.println("input path parameter:"+second_programId);
		RestAssured.requestSpecification = given();
		response22 = RestAssured.requestSpecification.when()
				.get("/programs/"+second_programId).then().extract().response();
		System.out.print("Get second Program by id : Response--> "+response22.getBody().asPrettyString());	
	}

	@Then("User should get status code as {int} and get response body")
	public void user_should_get_status_code_as_and_get_response_body(Integer int1) {
		if (response11.getStatusCode()==200){
			String responsebody=response11.getBody().asPrettyString();
			System.out.println("ResponseBody of the get request"+ responsebody);	
			response11.then().statusCode(int1);
			isAPIHit = true;			
			System.out.println(response11.getTime());
			System.out.println(response11.getStatusCode());
			System.out.println(response11.statusLine());
			System.out.println(response11.getHeader("content-type"));
			//System.out.println(response11.prettyPrint());
			// ASSERTION
//			int statusCode=response.getStatusCode();
//			Assert.assertEquals(statusCode, 200);		
		}else {
			System.out.println("Get  program by id  is not success. Error code : " +response11.getStatusCode()) ;
		}
		int statuscode=response11.getStatusCode();
		System.out.println(statuscode);
	}

	@Then("Get program by id  is displayed")
	public void get_program_by_id_is_displayed() {
		if (isAPIHit) {
			int statuscode=response11.getStatusCode();
			System.out.println(statuscode);
	}
	}
	
	/*
	@When("User make a GET request with an end point progranName")
	public void user_make_a_get_request_with_an_end_point_progran_name() {
		System.out.println("input path parameter:"+first_programName);
		RestAssured.requestSpecification = given();
		response = RestAssured.requestSpecification.when()
				.get("/programs/"+first_programName).then().extract().response();
		
		System.out.println("input path parameter:"+second_programName);
		RestAssured.requestSpecification = given();
		response = RestAssured.requestSpecification.when()
				.get("/programs/"+second_programName).then().extract().response();
	}
	

	@Then("Get program by name  is displayed")
	public void get_program_by_name_is_displayed() {
		System.out.println("input path parameter:"+second_programName);
		RestAssured.requestSpecification = given();
		response = RestAssured.requestSpecification.when()
				.get("/programs/"+second_programName).then().extract().response();
	}
	*/

	@When("User make a UPDATE request on first program with an end point Update Program By Name")
	public void user_make_a_update_request_on_first_program_with_an_end_point_update_program_by_name() {
		//update_request1=new JSONObject();
		System.out.println(" request response:"+ request1.toJSONString());
		System.out.println(" program name "+ first_programName);
		request1.put("programDescription", "API-Hackathon-SDET-NinjaSurvivors-1");	 
		RestAssured.requestSpecification = given()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(request1.toJSONString());
		update_response1 = RestAssured.requestSpecification.when()
				.put("/program/"+ first_programName).then().extract().response();
		
	}

	@Then("update  program by name  is displayed")
	public void update_program_by_name_is_displayed() {
		if (update_response1.getStatusCode()==200){
			//System.out.print("updated Program: Response--> "+update_response1.getBody().asPrettyString());			
			isAPIHit = true;
		}else
		{
			System.out.println("Posting is not success. Error code : " +update_response1.getStatusCode()) ;
		}
	}

	@When("User make a UPDATE request on second program with an end point Update Program By Id")
	public void user_make_a_update_request_on_second_program_with_an_end_point_update_program_by_id() {
		//request1=new JSONObject();
		request2.put("programDescription", "API-Hackathon-SDET-NinjaSurvivors-2");
		System.out.println(request2.toJSONString()); 
		RestAssured.requestSpecification = given()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(request2.toJSONString());
		update_response2 = RestAssured.requestSpecification.when()
				.put("/putprogram/"+second_programId).then().extract().response();
		System.out.println("status"+ update_response2.getStatusCode());
	}

	@Then("update  program by id is displayed")
	public void update_program_by_id_is_displayed() {
		if (update_response2.getStatusCode()==200){
			System.out.print("updated Program: Response--> "+update_response2.getBody().asPrettyString());			
			isAPIHit = true;
		}else
		{
			System.out.println("Posting is not success. Error code : " +update_response2.getStatusCode()) ;
		}
	}

	
	@When("User make a GET request with an end point allProgram")
	public void user_make_a_get_request_with_an_end_point_all_program() {
		RestAssured.requestSpecification = given();

			getAll_response = RestAssured.requestSpecification.when()
		   .get("/allPrograms").then().extract().response();   
	}

	@Then("User get the status code as {int} and got a response body as programID, programName, programDescription, programStatus, creationTime, lastModTime")
	public void user_get_the_status_code_as_and_got_a_response_body_as_program_id_program_name_program_description_program_status_creation_time_last_mod_time(Integer int1) {
		if (getAll_response.getStatusCode()==200){
			/*	String responsebody1=getAll_response.getBody().asPrettyString();
				System.out.println("ResponseBody of the get request"+ responsebody1);	*/
				getAll_response.then().statusCode(int1);
				isAPIHit = true;

			}else
				System.out.println("Get all programs is not success. Error code : " +getAll_response.getStatusCode()) ;
			
			int statuscode=getAll_response.getStatusCode();
			System.out.println(statuscode);
	}

	@Then("all programs are displayed")
	public void all_programs_are_displayed() {
		int statuscode=getAll_response.getStatusCode();
		System.out.println(statuscode);
	}

		// <== create first Batch ==>
		
		@Given("User create 1st batch with base Url")
		public void user_create_1st_batch_with_base_url() {

//			RestAssured.baseURI = base_URI;
			batch_request1 = new JSONObject();

			batch_request1.put("batchName", "Jan23-Ninjasurvivors-SDET-SDET94-PG-Batch01");
			batch_request1.put("batchDescription", "Selenium Training sessions");
			batch_request1.put("batchStatus", "Active");
			batch_request1.put("batchNoOfClasses", "200");
			batch_request1.put("programId",first_programId);

			System.out.println(batch_request1.toJSONString());
			RestAssured.requestSpecification = given().header("Content-Type", "application/json")
					.contentType(ContentType.JSON).accept(ContentType.JSON).body(batch_request1.toJSONString());
			
	 	}


		@When("User make a POST request  for batch with end point {string}")
		public void user_make_a_post_request_for_batch_with_end_point(String string) {
		
		//	RestAssured.baseURI = base_URI;
			
			batch_response1 = RestAssured.requestSpecification.when().post("/batches").then().extract().response();
			String responseBody = batch_response1.getBody().asString();

			JsonPath js = new JsonPath(responseBody);
			first_batchId = js.getString("batchId");
			first_batchName = js.getString("batchName");
			System.out.println("BatchId : " + first_batchId);
			System.out.println("BatchName : " + first_batchName);

		 
		}

		@Then("User  get response body and status code should be {int}")
		public void user_get_response_body_and_status_code_should_be(Integer int1) {
		
			System.out.println("SaveBatch : Response--> \n" + batch_response1.getBody().asPrettyString());

			Assert.assertEquals(batch_response1.statusCode(), 201);

			System.out.println("\n Status code is: " + batch_response1.statusCode());
			
			Assert.assertEquals(batch_response1.jsonPath().getString("batchName"), batch_request1.get("batchName"));
			System.out.println(batch_response1.getTime());
			System.out.println(batch_response1.getStatusCode());
			System.out.println(batch_response1.statusLine());
			System.out.println(batch_response1.getHeader("content-type"));

		}
//		<-- create 2nd batch-->
		
		@Given("User create 2nd batch with base Url")
		public void user_create_2nd_batch_with_base_url() {
		 
		
			batch_request2 = new JSONObject();

			batch_request2.put("batchName", "Jan23-Ninjasurvivors-SDET-SDET94-PG-Batch02");
			batch_request2.put("batchDescription", "Sql bootcamp");
			batch_request2.put("batchStatus", "Active");
			batch_request2.put("batchNoOfClasses", "200");
			batch_request2.put("programId",second_programId);
			

			System.out.println(batch_request2.toJSONString());
			RestAssured.requestSpecification = given().header("Content-Type", "application/json")
					.contentType(ContentType.JSON).accept(ContentType.JSON).body(batch_request2.toJSONString());
			 
		}

		@When("User make a POST request  for  2nd batch with end point {string}")
		public void user_make_a_post_request_for_2nd_batch_with_end_point(String string) {

			batch_response2 = RestAssured.requestSpecification.when().post("/batches").then().extract().response();
			String responseBody = batch_response2.getBody().asString();

			JsonPath js = new JsonPath(responseBody);
			second_batchId = js.getString("batchId");
			second_batchName = js.getString("batchName");
			System.out.println("BatchId : " + second_batchId);
			System.out.println("BatchName : " + second_batchName);

		 
		}

		@Then("User get response body for 2nd batch and status code should be {int}")
		public void user_get_response_body_for_2nd_batch_and_status_code_should_be(Integer int1) {

		
			System.out.println("SaveBatch : Response--> \n" + batch_response2.getBody().asPrettyString());

			Assert.assertEquals(batch_response2.statusCode(), 201);

			System.out.println("\n Status code is: " + batch_response2.statusCode());
			
			Assert.assertEquals(batch_response2.jsonPath().getString("batchName"), batch_request2.get("batchName"));
	 
		}
//	    <--GetBatchById-->
		
		@When("User make a GET request with an end point {string} for getting batch")
		public void user_make_a_get_request_with_an_end_point_for_getting_batch(String string) {
			batch_response11 = RestAssured.requestSpecification.when().get("/batches/batchId/" + first_batchId).then().extract().response();

		}


		@Then("User gets status code as {int} and response body")
		public void user_gets_status_code_as_and_response_body(Integer int1) {
		 
			System.out.print("GetSingleBatch : Response--> " + batch_response11.getBody().asPrettyString());

			Assert.assertEquals(batch_response11.statusCode(), 200);

			System.out.println("\n Status code is:" + batch_response11.statusCode());

	 
		}

//		 <--UpdatebatchByBatchId-->

		@When("User make a PUT request to update program with end point {string}")
		public void user_make_a_put_request_to_update_program_with_end_point(String string) {
		 
		
			batch_request1.put("batchDescription", "internal assessment");
			System.out.println("Request :" + batch_request1.toJSONString());

			RestAssured.requestSpecification = given().header("Content-Type", "application/json")
					.contentType(ContentType.JSON).accept(ContentType.JSON).body(batch_request1.toJSONString());

			update_batch_response2 = RestAssured.requestSpecification.when().put("/batches/" + first_batchId).then().extract()
					.response();
	}

		@Then("User should get status code as {int} for batch and response body")
		public void user_should_get_status_code_as_for_batch_and_response_body(Integer int1) {
	    
			System.out.print("Updated Batch : Response--> " + update_batch_response2.getBody().asPrettyString());

			Assert.assertEquals(update_batch_response2.statusCode(), 200);

			System.out.println("\n Status code is:" + update_batch_response2.statusCode());

		}

//		<--DeleteBatchById-->
		
		@When("User make a DELETE request with an end point {string}")
		public void user_make_a_delete_request_with_an_end_point(String string) {
		
		

			update_batch_response2 = RestAssured.requestSpecification.when().delete("/batches/" + second_batchId).then().extract().response();

			
		}

		@Then("User gets status code {int} and response body")
		public void user_gets_status_code_and_response_body(Integer int1) {
			
			Assert.assertEquals(update_batch_response2.statusCode(), 200);
			System.out.println("\n Status code is:" + update_batch_response2.statusCode());
			System.out.print("Batch is deleted successfully");
		}
		@When("User make a GET request with an end point allbatch")
		public void user_make_a_get_request_with_an_end_point_allbatch() {
			RestAssured.requestSpecification = given();
			getAll_batch_response = RestAssured.requestSpecification.when()
		   .get("/batches").then().extract().response(); 
		}

		@Then("User get the status code as {int} and got a response body as batchID, batchName, batchDescription, batchStatus, creationTime, lastModTime")
		public void user_get_the_status_code_as_and_got_a_response_body_as_batch_id_batch_name_batch_description_batch_status_creation_time_last_mod_time(Integer int3) {
			if (getAll_batch_response.getStatusCode()==200){
				String responsebody1=getAll_batch_response.getBody().asPrettyString();
				System.out.println("ResponseBody of the get request"+ responsebody1);	
				getAll_batch_response.then().statusCode(int3);
				isAPIHit = true;

			}else
				System.out.println("Get all batchs is not success. Error code : " +getAll_batch_response.getStatusCode()) ;
			
			}

		@Then("all batchs are displayed")
		public void all_batchs_are_displayed() {
			int statuscode=getAll_batch_response.getStatusCode();
			System.out.println(statuscode);
		}

		// allure serve C:\Users\Reka\eclipse-workspace\ninjasurvivors\allure_report
		
	}

