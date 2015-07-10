package com.softserveinc.edu.ita.utils;

import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test
{
    public static void main(String[] args) {
        startNode();
    }
    public static void startNode() {
        try {
            final String virtualMachineName = PropertyLoader.getProperty("vm.machine.name");
            final ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe",
                    "guestcontrol", virtualMachineName, "execute", "--image","/home/osboxes/oms/startnode.sh", "--username", "osboxes","--password","osboxes.org", "--verbose");
            Process p = processBuilder.redirectErrorStream(true)
                    .start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
