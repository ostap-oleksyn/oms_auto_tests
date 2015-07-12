package com.softserveinc.edu.ita.utils;


import org.apache.commons.lang3.SystemUtils;

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
    private final static String projectPath = System.getProperty("user.dir");
    private static ProcessBuilder processBuilder;
    private static String vboxManageWinPath;
    private static String hubServiceScript = projectPath + "/src/resources/scripts/hub-service";
    private static String seleniumGridPath = projectPath + "/src/resources/drivers/";

    private VirtualBoxUtil() {
    }

    /**
     * Returns the IP address of the virtual machine specified in config.properties file.
     */
    public static String getVirtualMachineIP() throws IOException {


        String virtualMachineIP = null;
        vboxManageWinPath = PropertyLoader.getProperty("vboxmanage.win.path", "virtualbox.properties");

        String virtualMachineName = PropertyLoader.getProperty("vm.machine.name", "virtualbox.properties");

        if (SystemUtils.IS_OS_LINUX) {
            processBuilder = new ProcessBuilder("VBoxManage",
                    "guestproperty", "get", virtualMachineName, "/VirtualBox/GuestInfo/Net/0/V4/IP");
        } else if (SystemUtils.IS_OS_WINDOWS) {
            processBuilder = new ProcessBuilder(vboxManageWinPath,
                    "guestproperty", "get", virtualMachineName, "/VirtualBox/GuestInfo/Net/0/V4/IP");
        }

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

        return virtualMachineIP.replace("Value: ", "");
    }

    public static void startVirtualMachine() throws IOException {

        final String virtualMachineName = PropertyLoader.getProperty("vm.machine.name", "virtualbox.properties");
        vboxManageWinPath = PropertyLoader.getProperty("vboxmanage.win.path", "virtualbox.properties");

        if (SystemUtils.IS_OS_LINUX) {
            processBuilder = new ProcessBuilder("VBoxManage", "startvm", virtualMachineName);
        } else if (SystemUtils.IS_OS_WINDOWS) {
            processBuilder = new ProcessBuilder(vboxManageWinPath, "startvm", virtualMachineName);
        }

        processBuilder.redirectErrorStream(true)
                .start();
    }

    public static void stopVirtualMachine() throws IOException {

        final String virtualMachineName = PropertyLoader.getProperty("vm.machine.name", "virtualbox.properties");
        vboxManageWinPath = PropertyLoader.getProperty("vboxmanage.win.path", "virtualbox.properties");

        if (SystemUtils.IS_OS_LINUX) {
            processBuilder = new ProcessBuilder("VBoxManage", "controlvm", virtualMachineName, "acpipowerbutton");
        } else if (SystemUtils.IS_OS_WINDOWS) {
            processBuilder = new ProcessBuilder(vboxManageWinPath, "controlvm", virtualMachineName, "acpipowerbutton");
        }

        processBuilder.redirectErrorStream(true)
                .start();
    }

    public static void startHub() throws IOException {

        if (SystemUtils.IS_OS_LINUX) {
            processBuilder = new ProcessBuilder(hubServiceScript + ".sh", "start", seleniumGridPath);
        } else if (SystemUtils.IS_OS_WINDOWS) {
            processBuilder = new ProcessBuilder("powershell",
                    hubServiceScript.replaceAll("/", "\\") + ".bat", "start", seleniumGridPath.replaceAll("/", "\\"));
        }
        processBuilder.redirectErrorStream(true)
                .start();
    }

    public static void stopHub() throws IOException {

        if (SystemUtils.IS_OS_LINUX) {
            processBuilder = new ProcessBuilder(hubServiceScript + ".sh", "stop");
        } else if (SystemUtils.IS_OS_WINDOWS) {
            processBuilder = new ProcessBuilder(hubServiceScript.replaceAll("/", "\\") + ".bat", "stop");
        }
        processBuilder.redirectErrorStream(true)
                .start();
    }

    public static void startNode() throws IOException {

        final String virtualMachineName = PropertyLoader.getProperty("vm.machine.name", "virtualbox.properties");
        final String winStartNodeScript = PropertyLoader.getProperty("win.startnode.script", "virtualbox.properties");
        final String winGuestVmUsername = PropertyLoader.getProperty("win.guestvm.username", "virtualbox.properties");
        final String linuxStartNodeScript = PropertyLoader.getProperty("linux.startnode.script", "virtualbox.properties");
        final String linuxGuestVmUsername = PropertyLoader.getProperty("linux.guestvm.username", "virtualbox.properties");
        final String remotePlatform = PropertyLoader.getProperty("remote.platform");

        if (SystemUtils.IS_OS_LINUX) {
            if ("windows".equals(remotePlatform)) {
                processBuilder = new ProcessBuilder("VBoxManage",
                        "guestcontrol", virtualMachineName, "execute", "--image", winStartNodeScript, "--username", winGuestVmUsername, "--", getHostIP());
            } else if ("linux".equals(remotePlatform)) {
                processBuilder = new ProcessBuilder("VBoxManage", "guestcontrol", virtualMachineName, "execute", "--image",
                        linuxStartNodeScript, "--username", linuxGuestVmUsername, "--environment", "DISPLAY=:0", "--", getHostIP());
            }
        } else if (SystemUtils.IS_OS_WINDOWS) {
            if ("windows".equals(remotePlatform)) {
                processBuilder = new ProcessBuilder(vboxManageWinPath,
                        "guestcontrol", virtualMachineName, "execute", "--image", winStartNodeScript, "--username", winGuestVmUsername, "--", getHostIP());
            } else if ("linux".equals(remotePlatform)) {
                processBuilder = new ProcessBuilder(vboxManageWinPath, "guestcontrol", virtualMachineName, "execute", "--image",
                        linuxStartNodeScript, "--username", linuxGuestVmUsername, "--environment", "DISPLAY=:0", "--", getHostIP());
            }
        }

        processBuilder.redirectErrorStream(true)
                .start();
    }

    public static String getHostIP() {
        try {
            final Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (final InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress().toString().replace("/", "");
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}