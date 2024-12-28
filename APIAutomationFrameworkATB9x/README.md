#API Automation RestAssured (in Java)
Author - Febil Jose Babu
API Automation Framework with the CRUD of Restful Booker

mvn test -Dsurefire.suiteXmlFiles=testng.xml


#Tech Stack
Java ( JDK > 21)
Rest Assured
Apache POI, TestNG, Maven.
AssertJ (Advance assertions)
Jackson and GSON
Log4j
Allure Report
Full Folder Structure(Hybrid) Framework.
Jenkins File

#API Framework Important Components

#Running via CI/CD

Run

#Basic Create Test
Install Maven
add config to run the suite or testng
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <suiteXmlFiles>
            <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
          </suiteXmlFiles>
        </configuration>
      </plugin>
    </plugins>
  </build>
to pom.xml

mvn clean test -DsuiteXmlFile=testng.xml 

#Parallel Execution
To run tests in parallel, add the parallel attribute to your testng.xml file:

<suite name="All Test Suite" parallel="methods" thread-count="2">

Integration Test (Create BookinG and Create Token , Update and Delete Booking)
 mvn clean test -DsuiteXmlFile=testng-integration.xml

Allure Report Generated.
allure serve allure-results/
