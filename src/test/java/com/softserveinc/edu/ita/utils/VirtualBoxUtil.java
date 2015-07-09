package com.softserveinc.edu.ita.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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

    public static void startNode() {
        try {
            final String virtualMachineName = PropertyLoader.getProperty("vm.machine.name");
            final ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Oracle\\VirtualBox\\VBoxManage.exe",
                    "guestcontrol", virtualMachineName, "start", "\"C:\\start-node.bat\"", "--username", "OMS", "--", getHostIP());
            processBuilder.redirectErrorStream(true)
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHostIP() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses())
                    if (f.getAddress().isSiteLocalAddress())
                        return f.getAddress().toString().replace("/", "");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}