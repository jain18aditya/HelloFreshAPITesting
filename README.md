# HelloFreshAPITesting
Framework Details:
1. Framework is written using spring utility lombok where starting point will be Cucumber feature files.
2. Feature files are consist of plain English language steps. 
3. All pages and page action are on one place and action on pages (Background code for cucumber steps) are in Cucumber steps.
4. For API's a common class API.java is present in utils which contain all the API methods e.g. GET, POST, PUT etc
5. A model is created for output in api.model package.
6. Reading API details from and constant.java file.

Logging:
7. Used log4j for gathering logs and report file will be generated at root location.
8. Used hooks class of Cucumber to improve the logging.

Reports:
9A very good Cucumber reports are generated under target folder

CI:
10Can be easily linked with CI tool and if required all values can be passed through CI tool itself.

# Run requirement:
1. Clone this project
2. Gradle will download all the dependencies.
3. Run as either JUnit from runner file or Cucumber feature file.
4. Check reports.

Note: To run on eclipse you might need lombok Jar
Steps to setup:
1. Download lombok jar from https://jar-download.com/artifacts/org.projectlombok/lombok/1.16.10/source-code
2. Paste at eclipse path
3. Run jar usin java -jar command and provide the path of eclipse
4. Click install
5. Restart IDE.

Uses of lombok jar in project:
Lombok is spring utility used to do POJO work. You dont need to write Getter and Setter methods. Lombok will take care of it using its annotations.