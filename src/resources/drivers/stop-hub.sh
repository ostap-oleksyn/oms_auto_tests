#!/bin/bash
ps aux | grep -i selenium-server-standalone | awk {'print $2'} | xargs kill