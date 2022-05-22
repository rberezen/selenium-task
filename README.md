# Test project
Tech stack:
* java
* maven 
* junit
* selenium webdriver

Reporting:
* allure

CI/CD
* GitHub actions

Tests run on GitHub actions on each merge to master or on every pull request.
Reports are generated after each run and could be found on [GitHub Pages](https://rberezen.github.io/selenium-task/11/)

To run tests locally you will need:
* java 1.8
* maven 3.x
* chrome

Tests run in a headless mode. If you need to launch browser, please comment this line: 
[code reference](https://github.com/rberezen/selenium-task/blob/master/src/test/java/com/berezenskyi/ruslan/SeleniumTest.java#L28)