# Automation of testing Spotify's Mac client using Sikuli API #
This code is successfully tested agains Spotify Mac client version 1.0.16.104.g3b776c9e. Also this code is only tested
with Java version 1.8. Other versions of java are not tested. The code is not tested on a Windows machine.

## Tools used: ##
- Java 1.8
- Maven
- PMD
- TestNG
- Sikuli

## Assumptions/Limitations/Features/Way of implementation: ##
1- System has only one monitor
2- Spotify is in English language
3- Spotify is installed on the system
4- Maven is installed on the system. though it should be possible to run tests directly from an IDE using TestNG.
5- No other window of Spotify app is open except the main window. The test code only know the login window and
the main player window.
6- All test cases should be runnable independently without considering current state of the application. So for example
if at the start of test we are in player window and login wants to be tested, code should logout first to go to login window.
7- Each test class is inside a TestNG xml suite file so that each test can be executed independently using maven. 
8- Test data for each test class is inside the xml file. This is perhaps not the best way to handle test data but at least it's outside the test code in a easy reachable place.
9- No unit test is written for public methods due to time limitation
10- Path to Spotify app is stored as a property inside config.properties file. So user of test needs to update this path. The code could also get this information as a VM argument but it's not implemented
11- Search test is only working for a specific track name. There was not enough time to implement the test in a way that Sikuli searches for any given track name. 

## Currently following test cases are implemented: ##
1- Login in with valid/invalid username and password combinations.
2- Search for tracks. First search for a track the we now exists and verify it is found. Then search for name of a track
the we know does not exist and make sure app behaves correctly i.e returns the not found page and does not crash o throw exceptions.
3- Play a sound track
4- Adding a track to "Your music" list and make sure track remains in the list after logout/login.

## How to run: ##
First specify the path to Spotify app in config.properties file under "/src/main/resources".
Go to project's directory (where pom.xml file is) and:
mvn -DsuiteXmlFile={suite_xml_file_name} test

example:
mvn -DsuiteXmlFile=LoginTest.xml test

Following suite files can be executed:
1- LoginTest.xml
2- PlayTest.xml
3- SearchTest.xml
4- AddToYourMusicTest.xml
5- SpotifyTestSuite.xml (this one includes all the first 4 test classes and can be used to run all tests together)