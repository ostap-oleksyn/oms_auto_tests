#OOOMMMMSSS Auto Tests
### Version
1.0.0
### Overview
OMS Auto Tests is a test framework for OMS application developed by ATQC-IF055 group.

### Requirements
 - JDK 1.8;
 - Maven 2 or higher;
 
##### Plugins
To run OMS auto tests in Intellij Idea or Eclipse you need to instal the following plugins:
* Lombok plugin

##### Logging
To enable logging in Intellij Idea or Eclipse you need to add the following listeners to the TestNG configuration:
* TestListener
* HTMLReporter

By default the generated report is saved to `/test-output/html/index.html`.

###Test packages
All test packages are located in the `tests` package. Each test package represents a page of the OMS application, and consists of the tests that test the corresponding page.
 - `administration_page`
 - `home_page` 
 - `item_management_page`
 - `ordering_page`
 - `userinfo_page`
 

###Configuration
Before runnig the project, you need to do the following configurations:

In `src/resources/` edit the `config.properties`:
 - set the `oms.url` property to the url of your OMS application  (`http://localhost:8080/OMS` by default);
 
In `src/resources/` edit the `jdbc.properties`:
 - set the `user` and `password `properties to the name and password of the user that has access to the OMS database (`oms`, `1qaz2wsx` by default);
 - set the `url` property to the url of the local OMS database (`jdbc:mysql://localhost:3306/oms` by default);
 - set the `driver` property to the the desired mysql driver (`com.mysql.jdbc.Driver` by default);


###Running
####Maven
To run OMS auto tests with maven, go to the project folder and use the following command:
```
mvn test
```

#####Test suites
You can run OMS auto test with specified test suites using the following command:
```
mvn test -D suite="suite name"
```
**Available suites:**
 - `AdministrationPageTests`
 - `ItemManagementPageTests`
 - `LoginPageTests`
 - `MerchandiserOrderingPageTests`
 - `TabsNavigationTests`
 - `UserInfoPageTests`
 - `LocalizationTests`
 - `AllTests`
 
#####Example:
<br\>
```
mvn test -D suite=TabsNavigationTests
```
By default, without specifying the suite name, maven runs `AllTests` suite.

#####Libraries versions
You can run OMS auto tests specifiying the version of libraries that are used in the project:
```
mvn test -D "property name"= value
```
**Available properties:**
 - `selenium-java.version` (2.45.0 by default);
 - `selenium-remote-driver.version` (2.45.0 by default);
 - `selenium-chrome-driver.version` (2.45.0 by default);
 - `selenium-ie-driver.version` (2.45.0 by default);
 - `selenium-phantomjsdriver.version` (1.2.1 by default);
 - `testng.version` (6.9.4 by default);
 - `mysql.connector.version` (5.1.35 by default);
 - `apache.poi.version` (3.12 by default);
 - `reportng.version` (1.1.4 by default);
 - `guice.version` (3.0 by default);
 - `lombok.version` (1.16.4 by default);
 
#####Example:
<br\>
```
mvn test -D selenium-java.version=2.55.0
```  
 By default, without specifying any properties, maven runs the project with default libraries versions.
 
####Running on different browsers
 OMS auto tests supports execution on different browsers. To specifiy the desired browser, configure `browser` property in the `config.properties` file in `/src/resources/`
 
 ```
oms.url = http://localhost:8080/OMS
testDataXlsFile = src//resources//TestData.xls
browser = firefox
remote.enabled = false
remote.platform = linux
remote.browser.version = 38
hub.url = http://localhost:4444/wd/hub
 ```
 **Supported browser properties:**
 - `firefox`
 - `chrome`
 - `internet_explorer`
 - `phantom_js`
 - `headless`

When running the project on Linux, in `/src/resources/drivers/` edit permissions for `chromedriver` and `phantomjs`, enable **Allow executing file as program**.


####Running with JAR
You can run OMS auto tests using comipled JAR file:

To run using JAR file:
 - Create a JAR file with OMS auto tests project
 - Use the following command in command line:
```
java -jar `file_name.jar`
```

#####Example:
<br\>
```
java -jar oms_auto_tests.jar
```  

To run OMS auto tests using a JAR file with specific suite, execute the following command:
```
java -jar `file_name.jar` `suite name`
```
**Avaiable suite properties:**
 - `administration`
 - `item_management`
 - `login`
 - `ordering`
 - `navigation` 
 - `user_info`
 - `localization`
 - `all`

#####Example:
<br\>
```
java -jar oms_auto_tests.jar administration
```  
By default, executing the JAR file will run all tests suite.


<br\>
###Selenium Grid
###Overview
OMS auto test support execution on remote machines with different OS and browsers using Selenium Grid server.
###Requirements
 - VirtualBox version 4.3.30

###Configuration
To run OMS auto tests on other OS and browsers you need to perform following steps:
#####Linux
  - Create a virtual machine with linux (for example Ubuntu);
  - Set virtual machine network settings to  **Attached to: Bridged Adapter** 
  - Install OMS application;
  - Configure linux to autologin with no password;
  - In mysql grant access to oms database to the remote host user:
 ```
  GRANT ALL ON oms.* TO user@'%' IDENTIFIED BY 'password';
 ```
  - Cofigure linux to shutdown without dialog window appearing. Edit `/etc/acpi/events/powerbtn` and replace `action=/etc/acpi/powerbtn.sh` with `action=/sbin/poweroff`;
  - Copy `start-node.sh`, `selenium-server-standalone-2.45.0.jar`, `chromedriver` from `/src/resources/drivers` and `/src/resources/scripts` to desired location on virtual machine (for example `/home/osboxes/grid/`); 
  -  Edit `start-node.sh` permissions, enable **Allow executing file as program**; 
  - Edit `start-node.sh`, specify the path to grid server jar file, chromedriver, and configure the grid node settings:
```
#!/bin/bash
java -jar /home/osboxes/Grid/selenium-server-standalone-2.45.0.jar -role node -hub http://$1:4444/grid/register -Dwebdriver.chrome.driver=/home/osboxes/Grid/chromedriver -browser browserName=firefox,version=38,maxInstances=5,platform=LINUX -browser browserName=chrome,version=43,maxInstances=5,platform=LINUX -maxSession 5
```

#####Windows
- Create a virtual machine with windows (for example Windows 7);
- Set virtual machine network settings to  **Attached to: Bridged Adapter** 
- Install OMS application;
- Configure windows to login without password;
- On the virtual machine run `gpedit.msc`, go to **Computer Configuration\Windows Settings\Security Settings\Local Policies\Security Options\Accounts: Limit local account use of blank passwords to console logon only** and set it to **0**
- In mysql grant access to oms database to the remote host user:
 ```
  GRANT ALL ON oms.* TO user@'%' IDENTIFIED BY 'password';
 ```
- Copy `start-node.bat`, `selenium-server-standalone-2.45.0.jar`, `chromedriver.exe`, `IEDriverServer.exe` from `/src/resources/drivers` and `/src/resources/scripts` to desired location on virtual machine (for example `C:\Grid`);
- Edit `start-node.bat`, specify the path to grid server jar file, chromedriver,ie driver and configure the grid node settings:

```
java -jar C:\Grid\selenium-server-standalone-2.45.0.jar -role node -hub http://%1:4444/grid/register -Dwebdriver.chrome.driver=C:\Grid\chromedriver.exe -Dwebdriver.ie.driver=C:\Grid\IEDriverServer.exe -browser browserName=firefox,version=36,maxInstances=5,platform=WINDOWS -browser browserName=chrome,version=43,maxInstances=5,platform=WINDOWS -browser "browserName=internet explorer,version=8,maxInstances=5,platform=WINDOWS" -maxSession 5
```


#####virtualbox.properties
Configure the `virtualbox.properties` file:
- `vm.machine.name` - name of the virtual machine to be started;
- `vboxmanage.win.path` - path to the VBoxManage.exe in Windows;
- `vm.start.timeout.min` - time in minutes to wait for the guest OS to boot completely;
- `win.guestvm.username` - usarename of the windows guest machine;
- `win.startnode.script` - path to the start-node.bat on the windows guest machine;
- `linux.guestvm.username` - usarename of the linux guest machine;
- `linux.startnode.script` - path to the start-node.bat on the linux guest machine;

#####Example:
<br\>
```
vm.machine.name = Ubuntu
vboxmanage.win.path = C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe
vm.start.timeout.min = 2
win.guestvm.username = OMS
win.startnode.script = C:\\Grid\\start-node.bat
linux.guestvm.username = osboxes
linux.startnode.script = /home/osboxes/Grid/start-node.sh
```

###Running
To run OMS auto tests using Selnium Grid:

 * In `config.properties:`
- Set `remote.enabled` to `true`;
- Set `remote.platform` to desired platform(`linux` or `windows`);
- Set `browser` to desired browser;
- Set `remote.browser.version` to desired browser version;
- Set `hub.url` to the grid hub adress(`http://localhost:4444/wd/hub` by default);

 * Configure the `virtualbox.properties` file;

 * If the project is run on Linux -  edit `hub-service.sh` script permissions in `/src/resources/scripts`, enable **Allow executing file as program**; 
 * Run the project in IDE or using maven;


