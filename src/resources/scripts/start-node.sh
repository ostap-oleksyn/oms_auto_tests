#!/bin/bash
java -jar /home/osboxes/Grid/selenium-server-standalone-2.45.0.jar -role node -hub http://$1:4444/grid/register -Dwebdriver.chrome.driver=/home/osboxes/Grid/chromedriver -browser browserName=firefox,version=38,maxInstances=5,platform=LINUX -browser browserName=chrome,version=43,maxInstances=5,platform=LINUX -maxSession 5
