Automation of testing Spotify's Mac client using Sikuli API.


This code is successfully tested agains Spotify Mac client version 1.0.16.104.g3b776c9e. Also this code is only tested
with Java version 1.8. Other versions of java are not tested

Tools used:
- Java 1.8
- Maven
- PMD
- TestNG
- Sikuli

Assumptions:
1- System has only one monitor
2- Spotify is in english language
3- Spotify is installed on the system
4- Maven is installed on the system. though it should be possible to run tests directly from an IDE using TestNG.

How to run:
After the source code is pulled from git, go to project's directory (where pom.xml file is) and:
mvn test


