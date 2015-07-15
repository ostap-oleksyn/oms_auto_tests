#!/bin/bash

if [ $1 = "start" ]
then
	java -jar $2selenium-server-standalone-2.45.0.jar -role hub
elif [ $1 = "stop" ]
then
	ps aux | grep -i selenium-server-standalone | awk {'print $2'} | xargs kill
fi