package com.softserveinc.edu.ita.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class with util methods for VirtualBox.
 */
public final class VirtualBoxUtil {

    private VirtualBoxUtil() {
    }

    /**
     * Returns the IP address of the virtual machine specified in config.properties file.
     */
    public static String getVirtualMachineIP() {

        final String virtualMachineName;
        String virtualMachineIP = null;
        try {
            virtualMachineName = PropertyLoader.getProperty("vm.machine.name");
            final ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe",
                    "guestproperty", "get", virtualMachineName, "\"/VirtualBox/GuestInfo/Net/0/V4/IP\"");
            processBuilder.redirectErrorStream(true);
            final Process process = processBuilder.start();

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                virtualMachineIP = line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return virtualMachineIP.replace("Value: ", "");
    }

    public static void startVirtualMachine() {
        try {
            final String virtualMachineName = PropertyLoader.getProperty("vm.machine.name");
            final ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe",
                    "startvm", virtualMachineName);
            processBuilder.redirectErrorStream(true)
                    .start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}