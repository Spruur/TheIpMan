package ip_man;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;


/**
 * The Ip Man
 * Ip class that offers basic Ip characteristics.
 *
 * @author Karl Hendrik Leppmets
 * @version 0.0.1
 */
public class Ip {
    private String address;
    private String subnetMask;
    private int subnetPrefix;
    private String binaryAddress;
    private String binarySubnetMask;
    private String networkAddress;
    private String binaryNetworkAddress;

    public Ip(String addr, String subnet) {
        if (addr.length() < 32) {
            if (isValidAddress(addr)) {
                setAddress(addr);
                addressToBinary();
            }
        }
        else {
            setBinaryAddress(addr);
            addressFromBinary();
        }

        if (subnet.length() == 0) {
            subnet = "24";
        }

        if (subnet.length() <=3) {
            setSubnetPrefix(Integer.parseInt(subnet));
            subnetFromPrefix();
        }
        else if (subnet.length() < 32) {
            if (isValidAddress(subnet)) {
                setSubnetMask(subnet);
                subnetMaskToBinary();
                setSubnetPrefix(binaryToPrefix(binarySubnetMask));
            }
        }
        else {
            setBinarySubnetMask(subnet);
            setSubnetPrefix(binaryToPrefix(binarySubnetMask));
            subnetMaskFromBinary();
        }


        toNetworkAddress();

    }

    // SET methods
    private void setAddress(String value) {
        address = value;
    }
    private void setSubnetMask(String value) {
        subnetMask = value;
    }
    private void setSubnetPrefix(int value) {
        subnetPrefix = value;
    }
    private void setBinaryAddress(String value) {
        binaryAddress = value;
    }
    private void setBinarySubnetMask(String value) {
        binarySubnetMask = value;
    }
    private void setNetworkAddress(String value) {
        networkAddress = value;
    }
    private void setBinaryNetworkAddress(String value) {
        binaryNetworkAddress = value;
    }

    public String getAddress() { return address; }
    public String getSubnetMask() { return subnetMask; }
    public int getSubnetPrefix() { return subnetPrefix; }
    public String getBinaryAddress() { return binaryAddress; }
    public String getBinarySubnetMask() { return binarySubnetMask; }
    public String getNetworkAddress() { return networkAddress; }
    public String getBinaryNetworkAddress() {return binaryNetworkAddress; }


    // Private methods
    private void addressToBinary() {
        setBinaryAddress(ipToBinary(address));
    }

    private void subnetMaskToBinary() {
        setBinarySubnetMask(ipToBinary(subnetMask));
    }

    private void addressFromBinary() { setAddress(binaryToIp(binaryAddress)); }

    private void subnetMaskFromBinary() {
        setSubnetMask(binaryToIp(binarySubnetMask));
    }

    private void subnetFromPrefix() {
        setBinarySubnetMask(prefixToBinary(subnetPrefix));
        subnetMaskFromBinary();
    }

    private void toNetworkAddress() {
        setBinaryNetworkAddress(anding(binaryAddress, binarySubnetMask));
        setNetworkAddress(binaryToIp(binaryNetworkAddress));
    }


    // Public methods

    public static String ipToBinary(String addr) {
        StringBuilder binary = new StringBuilder();
        String[] chunks = addr.split("\\.");
        for (String chunk: chunks) {
            String binaryChunk = Integer.toBinaryString(Integer.parseInt(chunk));
            String zeros = String.join("", Collections.nCopies(8-binaryChunk.length(), "0"));
            binary.append(zeros).append(binaryChunk);
        }

        return binary.toString();
    }

    public static String binaryToIp(String binary) {
        StringBuilder addr = new StringBuilder();
        String[] chunks = binary.split("(?<=\\G.{8})");
        for (int i = 0; i < 4; i++) addr.append(Integer.parseInt(chunks[i], 2)).append(i < 3 ? "." : "");
        return addr.toString();
    }

    public static String prefixToBinary(int prefix) {
        StringBuilder binary = new StringBuilder().append(String.join("", Collections.nCopies(prefix, "1"))); // Appending right amount of ON bits
        binary.append(String.join("", Collections.nCopies(32 - prefix, "0"))); // Appending right amount of OFF bits
        return binary.toString();
    }

    public static int binaryToPrefix(String binary) {
        return binary.replaceAll("0", "").length();
    }

    public static boolean isValidAddress(String addr) {
        // Does IP have only "." and integers in it
        for (int i = 0; i<addr.length(); i++) {
            if (addr.charAt(i) != '.') {
                if (!Character.isDigit(addr.charAt(i))) {
                    System.out.println("NOT INT");
                    return false;
                }
            }
        }
        
        // Generating chunks
        String[] chunks = addr.split("\\.");
        
        // Is there 4 chunks
        if (chunks.length != 4) return false;

        // Checking chunk length
        for (String chunk: chunks) {
            if (chunk.length() == 0 || chunk.length() > 3) return false;
            if (Integer.parseInt(chunk) >= 0 && Integer.parseInt(chunk) <= 255) return false;
        }

        // If everything was OK then return TRUE
        return true;
    }



    public static String anding(String binary1, String binary2) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            binary.append(binary1.charAt(i) == binary2.charAt(i) && binary1.charAt(i) == '1' ? 1 : 0);
        }
        return binary.toString();
    }
}
