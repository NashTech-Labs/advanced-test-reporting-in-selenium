# Advanced Test Reporting in Selenium
This template will help you to have a Create advance Extent report using Testng in Selenium.
## What are the ExtentReport
ExtentReports is an open-source reporting library useful for test automation. It can be easily integrated with major testing frameworks like TestNG, etc. Once an automated test script runs successfully, testers need to generate a test execution report.

## I was using - 
* Java as the programming language
* TestNG as the assertion framework
* Maven as the build tool
* WebDriver as the browser automation tool
* IntelliJ as the IDE

#### Add the plugins - TestNG.
### Packages - 
We have a separate package for Com and TestCase and Listeners. All web page related class  under in Com package and all test cases  in under TestCase and ExtentReport content class under in Listeners. 
### Com package--
#### Content class - 
* This class is responsible for loading the test cases from excel sheet.
* Download Apache POI libraries dependency in pom files.
* XSSF(XML Spreadsheet Format) this is the interface using for read and write the data from excel sheet using XSSFWorkbook workbook.

#### DriverUtility class -
 This class stores and handles the functions (The code which is repetitive in nature such as waits, capturing screenshots, accessing excels etc) which can be commonly used across the entire framework and also we add the Screenshot method here.
#### RunTheProgram class -
 This class is responsible for reading all sheet header values from excel sheet and  adding the assertion for each test cases with using Assert.assertequal() method.
### Listeners package ---
#### ExtentReport class - 
This class generate the report.
* First load the properties file. And set the report path.
* Set the log with status with test case - 
		(context.getPassedTests(), LogStatus.PASS);
		(context.getFailedTests(), LogStatus.FAIL);
		(context.getSkippedTests(), LogStatus.SKIP);
* Set the description of each test case with using .getParameters()[0]toString()
* Add the Time calendar.

#### Create TestNG.xml file and add the Listener in TestNG file
	
<listeners>
   <listener class-name="Listeners.ExtentReport"/>
</listeners>

#### Maven dependency for ExtentReport
		<dependency>
			<groupId>com.relevantcodes</groupId>
		            <artifactId>extentreports</artifactId>
		            <version>2.41.2</version>
		</dependency>


### Summary of Report
![Screenshot from 2021-03-18 17-44-45](https://user-images.githubusercontent.com/43197101/111680071-d9057080-8847-11eb-959d-8c6675417aac.png)



### Benefits of Using Extent Report
* This framework help to add the different - different  descriptions in each test case .
* If required, screenshots can be captured and displayed for each step in a test
* They allow testers to track multiple test case runs in a single test suite
* They show the time needed for test execution.
* They can be customized to graphically represent each step in a test.

