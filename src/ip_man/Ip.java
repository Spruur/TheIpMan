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
    public String address;
    public String subnetMask;
    public String subnetPrefix;
    public String binaryAddress;
    public String binarySubnetMask;
    public String networkAddress;
    public String binaryNetworkAddress;

    public Ip(String addr, String subnet) {
        // TODO Need to find out if ip is in binary or not.

        // TODO Need to find out if subnet is in binary or as perfix or as dotted decimal.

        // TODO conversions from binary to IP or opposite

        if (isValidAddress(addr)) setAddress(addr);


        /*if (isValidSubnet(subnet)) {
            subnetMask = subnet;
            //return true;
        }*/

        // TODO Finish Constructor


        // TOASK What do I have to return to make it clear that constructing was unsuccessful?
    }

    protected void setAddress(String value) {
        address = value;
    }
    protected void setSubnetMask(String value) {
        subnetMask = value;
    }
    protected void setSubnetPerfix(String value) {
        subnetPrefix = value;
    }
    protected void setBinaryAddress(String value) {
        binaryAddress = value;
    }
    protected void setBinarySubnetMask(String value) {
        binarySubnetMask = value;
    }
    protected void setNetworkAddress(String value) {
        networkAddress = value;
    }
    protected void setBinaryNetworkAddress(String value) {
        binaryNetworkAddress = value;
    }

    private void addressToBinary() {
        setBinaryAddress(ipToBinary(address));
    }

    private void subnetMaskToBinary() {
        setBinarySubnetMask(ipToBinary(subnetMask));
    }




    protected static String ipToBinary(String addr) {
        StringBuilder binary = new StringBuilder();
        String[] chunks = addr.split("\\.");
        for (String chunk: chunks) {
            String binaryChunk = Integer.toBinaryString(Integer.parseInt(chunk));
            String zeros = String.join("", Collections.nCopies(8-binaryChunk.length(), "0"));
            binary.append(zeros).append(binaryChunk);
        }

        return binary.toString();
    }

    protected static String binaryToIp(String binary) {
        StringBuilder addr = new StringBuilder();
        String[] chunks = binary.split("(?<=\\G.{8})");
        for (int i = 0; i < 4; i++) addr.append(Integer.parseInt(chunks[i], 2)).append(i < 3 ? "." : "");
        return addr.toString();
    }

    protected static String prefixToBinary(String prefix) {
        StringBuilder binary = new StringBuilder().append(String.join("", Collections.nCopies(Integer.parseInt(prefix), "1"))); // Appending right amount of ON bits
        binary.append(String.join("", Collections.nCopies(32 - Integer.parseInt(prefix), "0"))); // Appending right amount of OFF bits
        return binary.toString();
    }

    protected static int binaryToPrefix(String binary) {
        return binary.replaceAll("0", "").length();
    }

    protected static boolean isValidAddress(String addr) {
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
        }

        // If everything was OK then return TRUE
        return true;
    }

    protected static boolean isValidSubnet(String addr) {
        // TODO functionality
        return true;
    }
}
