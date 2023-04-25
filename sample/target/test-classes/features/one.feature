@program
Feature: verify the GET /POST/PUT  in the API
# Background: below are the common steps for each scenario
#     Given A Service with "URL" (Save Program)

Scenario: Request to create , update, delete  program from API
  	 Given A Service with url
  	 And create first program  with request body with program name , id , status , creation time and modified time 
  	 When submit post request1 
  	 Then api returns status code 201
  	 And first program is created
  	 
	 Then create second program with request body with program name , id , status , creation time and modified time 	 
	 When submit post request2
	 Then api returns status code 201
	 And second program is created
	 
	 When User make a GET request with an end point progranId
	 Then User should get status code as 200 and get response body 
	 And  Get program by id  is displayed
	
#	When User make a GET request with an end point progranName
#	Then User should get status code as 200 and get response body 
#	And  Get program by name  is displayed
	
	When User make a UPDATE request on first program with an end point Update Program By Name
	Then User should get status code as 200 and get response body 
	And  update  program by name  is displayed
	
	When User make a UPDATE request on second program with an end point Update Program By Id
	Then User should get status code as 200 and get response body 
	And  update  program by id is displayed
	
  	 Given A Service with url
 	 When User make a GET request with an end point allProgram
  	 Then User get the status code as 200 and got a response body as programID, programName, programDescription, programStatus, creationTime, lastModTime
 	 And all programs are displayed
	
	 Given A Service with url 
  	 Given User create 1st batch with base Url 
  	 When User make a POST request  for batch with end point "/batches"  
  	 Then User  get response body and status code should be 201
  	 
  	 
	 Then User create 2nd batch with base Url 	 
	 When User make a POST request  for  2nd batch with end point "/batches" 
	 Then User get response body for 2nd batch and status code should be 201
	
	 
	 When User make a GET request with an end point "/batchId" for getting batch
	 Then User gets status code as 200 and response body
	 
	When User make a PUT request to update program with end point "/batches"
	Then User should get status code as 200 for batch and response body
	
	
	When User make a DELETE request with an end point "/batches"
	Then User gets status code 200 and response body
	
	 When User make a GET request with an end point allbatch
  	 Then User get the status code as 200 and got a response body as batchID, batchName, batchDescription, batchStatus, creationTime, lastModTime
  	 And all batchs are displayed
	
 # 	 When User make a GET request with an end point "/batches"
 # 	 Then User get the status code as 200 and got a response body as batchID, batchName, batchDescription, batchStatus, creationTime, lastModTime
  
#		Then Validate respose "<status code>"
#	Examples:
#		|url			|statusCode|
#		|/saveprogram 	|201	   |
#		|/allProgram	|200		|	

	