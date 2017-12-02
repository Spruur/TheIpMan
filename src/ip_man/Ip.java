package ip_man;

import com.sun.deploy.util.StringUtils;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.Collections;


/**
 * The Ip Man
 * Ip class that offers basic Ip characteristics.
 *
 * @author Karl Hendrik Leppmets
 * @version 0.0.2
 */
public class Ip {
    private String address;
    private String subnetMask;
    private int subnetPrefix;
    private String binaryAddress;
    private String binarySubnetMask;
    private String networkAddress;
    private String binaryNetworkAddress;
    private int amountOfAddresses;
    private int amountOfUsableAddresses;
    //private int amountOfSubnets;
    private String broadcastAddress;
    private String binaryBroadcastAddress;

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

        if (subnet.length() < 3) {
            if (isValidSubnetPrefix(Integer.parseInt(subnet))) {
                setSubnetPrefix(Integer.parseInt(subnet));
                subnetFromPrefix();
            }
        }
        else {
            if (subnet.length() < 32) {
                if (isValidSubnet(subnet)) {
                    setSubnetMask(subnet);
                    subnetMaskToBinary();
                    setSubnetPrefix(binaryToPrefix(binarySubnetMask));
                }
            }
            else {
                if (subnet.length() == 32) {
                    if (isValidSubnetBinary(subnet)) {
                        setBinarySubnetMask(subnet);
                        setSubnetPrefix(binaryToPrefix(binarySubnetMask));
                        subnetMaskFromBinary();
                    }
                }
                else {
                    subnet = "24";
                    setSubnetPrefix(Integer.parseInt(subnet));
                    subnetFromPrefix();
                }
            }
        }

        toNetworkAddress();
        setAmountOfAddresses(getAmountOfIps(getSubnetPrefix()));
        setAmountOfUsableAddresses(getAmountOfHosts(getSubnetPrefix()));
        //setAmountOfSubnets(getAmountOfPrefixSubnets(getSubnetPrefix()));

        toBroadcast();

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
    private void setAmountOfAddresses(int value) { amountOfAddresses = value; }
    private void setAmountOfUsableAddresses(int value) { amountOfUsableAddresses = value; }
    private void setBroadcastAddress(String value) { broadcastAddress = value; }
    private void setBinaryBroadcastAddress(String value) { binaryBroadcastAddress = value; }

    /**
     * Gets the IP address
     * @return String IP address
     */
    public String getAddress() { return address; }

    /**
     * Gets subnet mask
     * @return String Subnet mask
     */
    public String getSubnetMask() { return subnetMask; }

    /**
     * Gets subnet mask prefix
     * @return int Subnet mask prefix
     */
    public int getSubnetPrefix() { return subnetPrefix; }

    /**
     * Gets IP address in binary
     * @return String IP address in binary
     */
    public String getBinaryAddress() { return binaryAddress; }

    /**
     * Gets subnet mask in binary
     * @return String Subnet mask in binary
     */
    public String getBinarySubnetMask() { return binarySubnetMask; }

    /**
     * Gets network address
     * @return String Network address
     */
    public String getNetworkAddress() { return networkAddress; }

    /**
     * Gets network address in binary
     * @return String network address in binary
     */
    public String getBinaryNetworkAddress() { return binaryNetworkAddress; }

    /**
     * Gets the amount of addresses
     * @return int Amount of addresses
     */
    public int getAmountOfAddresses() { return amountOfAddresses; }

    /**
     * Gets amount of hosts
     * @return int Amount of hosts
     */
    public int getAmountOfUsableAddresses() { return amountOfUsableAddresses; }

    /**
     * Gets the broadcast address
     * @return String Broadcast address
     */
    public String getBroadcastAddress() { return broadcastAddress; }

    /**
     * Gets the broadcast address in binary
     * @return String Broadcast address in binary
     */
    public String getBinaryBroadcastAddress() { return binaryBroadcastAddress; }

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

    private void toBroadcast() {
        // Create broadcast binary
        StringBuilder broadcastInBinary = new StringBuilder(binaryNetworkAddress);
        for (int i = 1; i <= 32-subnetPrefix ; i++) {
            broadcastInBinary.setCharAt(32-i, '1');
        }

        setBinaryBroadcastAddress(broadcastInBinary.toString());
        setBroadcastAddress(binaryToIp(binaryBroadcastAddress));
    }

    private void to


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
        return (isValidSubnetBinary(binary) ? binary.replaceAll("0", "").length() : 0);
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
            if (Integer.parseInt(chunk) < 0 || Integer.parseInt(chunk) > 255) return false;
        }

        // If everything was OK then return TRUE
        return true;
    }

    public static boolean isValidSubnet(String addr) {
        return  isValidAddress(addr) && isValidSubnetBinary(ipToBinary(addr));
    }

    public static boolean isValidSubnetBinary(String binaryAddr) {
        if (binaryAddr.length() != 32) return false;
        boolean subnet = false;
        for (int i=0; i<32; i++) {
            if (!subnet && binaryAddr.charAt(i) == '0') subnet = true;
            else if (subnet && binaryAddr.charAt(i) == '1') return false;
        }
        return true;
    }

    public static boolean isValidSubnetPrefix(int prefix) {
        return prefix >= 8 && prefix <=32;
    }

    public static String anding(String binary1, String binary2) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            binary.append(binary1.charAt(i) == binary2.charAt(i) && binary1.charAt(i) == '1' ? 1 : 0);
        }
        return binary.toString();
    }

    public static int getAmountOfIps(int prefix) {

        return (int)java.lang.Math.pow(2, 32-prefix);
    }

    public static int getAmountOfHosts(int prefix) {
        return (isValidSubnetPrefix(prefix) && prefix < 31 ? getAmountOfIps(prefix) - 2 : (prefix == 31 ? 0 : 1));
    }

    public static String getBroadcast(String network, String subnet) {
        int prefix = binaryToPrefix(ipToBinary(subnet));


        return null;
    }


}
