package ip_man;

import java.util.Collections;


/**
 * The Ip Man
 * Ip class that offers basic Ip characteristics.
 *
 * @author      Karl Hendrik Leppmets
 * @version     0.0.3
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
    private String broadcastAddress;
    private String binaryBroadcastAddress;
    private String firstHostAddress;
    private String binaryFirstHostAddress;
    private String lastHostAddress;
    private String binaryLastHostAddress;

    /**
     * Ip constructor. This method calculates everything based on given address and subnet. It is expected that input is always correct.
     * @param addr      Ip address
     * @param subnet    Subnet mask
     */
    public Ip(String addr, String subnet) {
        // Is casual address
        if (addr.length() < 32) {
            if (isValidAddress(addr)) {
                setAddress(addr);
                addressToBinary();
            }
        }
        // Is binary address
        else {
            setBinaryAddress(addr);
            addressFromBinary();
        }

        // In case of empty subnet lets use default subnet of /24
        if (subnet.length() == 0) {
            subnet = "24";
        }

        // If subnet is as prefix
        if (subnet.length() < 3) {
            if (isValidSubnetPrefix(Integer.parseInt(subnet))) {
                setSubnetPrefix(Integer.parseInt(subnet));
                subnetFromPrefix();
            }
        }
        // If subnet is as address
        else {
            if (subnet.length() < 32) {
                if (isValidSubnet(subnet)) {
                    setSubnetMask(subnet);
                    subnetMaskToBinary();
                    setSubnetPrefix(binaryToPrefix(binarySubnetMask));
                }
            }
            // If subnet is as binary
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

        // Doing additional calculations
        toNetworkAddress();
        setAmountOfAddresses(getAmountOfIps(getSubnetPrefix()));
        setAmountOfUsableAddresses(getAmountOfHosts(getSubnetPrefix()));

        toBroadcast();
        toFirstAddress();
        toLastAddress();

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
    private void setFirstHostAddress(String value) { firstHostAddress = value; }
    private void setBinaryFirstHostAddress(String value) { binaryFirstHostAddress = value; }
    private void setLastHostAddress(String value) { lastHostAddress = value; }
    private void setBinaryLastHostAddress(String value) { binaryLastHostAddress = value; }

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

    /**
     * Gets the first host address
     * @return String First host address
     */
    public String getFirstHostAddress() { return firstHostAddress; }

    /**
     * Gets the first host address in binary
     * @return String First host address in binary
     */
    public String getBinaryFirstHostAddress() { return binaryFirstHostAddress; }

    /**
     * Gets the last host address
     * @return String Last host address
     */
    public String getLastHostAddress() { return lastHostAddress; }

    /**
     * Gets the first host address in binary
     * @return String First host address in binary
     */
    public String getBinaryLastHostAddress() { return binaryLastHostAddress; }



    // Private methods

    /**
     * Sets ip in binary
     */
    private void addressToBinary() {
        setBinaryAddress(ipToBinary(address));
    }

    /**
     * Sets subnet mask in binary
     */
    private void subnetMaskToBinary() {
        setBinarySubnetMask(ipToBinary(subnetMask));
    }

    /**
     * Sets ip address from binary
     */
    private void addressFromBinary() { setAddress(binaryToIp(binaryAddress)); }

    /**
     * Sets subnet mask from binary
     */
    private void subnetMaskFromBinary() {
        setSubnetMask(binaryToIp(binarySubnetMask));
    }

    /**
     * Set subnet an subnet binary based on prefix
     */
    private void subnetFromPrefix() {
        setBinarySubnetMask(prefixToBinary(subnetPrefix));
        subnetMaskFromBinary();
    }

    /**
     * Sets network address and network address in binary
     */
    private void toNetworkAddress() {
        setBinaryNetworkAddress(anding(binaryAddress, binarySubnetMask));
        setNetworkAddress(binaryToIp(binaryNetworkAddress));
    }

    /**
     * Sets Broadcast address and broadcast address in binary
     */
    private void toBroadcast() {
        StringBuilder broadcastInBinary = new StringBuilder(getBinaryNetworkAddress());
        for (int i = 1; i <= 32-getSubnetPrefix() ; i++) {
            broadcastInBinary.setCharAt(32-i, '1');
        }

        setBinaryBroadcastAddress(broadcastInBinary.toString());
        setBroadcastAddress(binaryToIp(binaryBroadcastAddress));
    }

    /**
     * Calculates and sets first host address
     */
    private void toFirstAddress() {
        String firstHostInBinary = getBinaryNetworkAddress();
        // Get last 8 bits or less if prefix > 24
        int subnetPart = getSubnetPrefix() > 24 ? getSubnetPrefix() : 24;
        int lastBits = Integer.parseInt(firstHostInBinary.substring(subnetPart), 2);

        lastBits++;

        String lastBitsInBinary = Integer.toBinaryString(lastBits);

        String zeros = String.join("", Collections.nCopies(32-subnetPart-lastBitsInBinary.length(), "0"));
        firstHostInBinary = firstHostInBinary.substring(0, subnetPart) + zeros + lastBitsInBinary;

        setBinaryFirstHostAddress(firstHostInBinary);
        setFirstHostAddress(binaryToIp(firstHostInBinary));
    }

    /**
     * Calculates and sets last host address
     */
    private void toLastAddress() {
        String lastHostInBinary = getBinaryBroadcastAddress();
        // Get last 8 bits or less if prefix > 24
        int subnetPart = getSubnetPrefix() > 24 ? getSubnetPrefix() : 24;
        int lastBits = Integer.parseInt(lastHostInBinary.substring(subnetPart), 2);

        lastBits--;

        String lastBitsInBinary = Integer.toBinaryString(lastBits);

        String zeros = String.join("", Collections.nCopies(32-subnetPart-lastBitsInBinary.length(), "0"));
        lastHostInBinary = lastHostInBinary.substring(0, subnetPart) + zeros + lastBitsInBinary;

        setBinaryLastHostAddress(lastHostInBinary);
        setLastHostAddress(binaryToIp(lastHostInBinary));
    }


    // Public methods

    /**
     * Converts standard ip address to binary
     * @param addr  IP address
     * @return      Ip address in binary
     */
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

    /**
     * Converts binary to IP
     * @param binary    IP in binary
     * @return          IP address
     */
    public static String binaryToIp(String binary) {
        StringBuilder addr = new StringBuilder();
        String[] chunks = binary.split("(?<=\\G.{8})");
        for (int i = 0; i < 4; i++) addr.append(Integer.parseInt(chunks[i], 2)).append(i < 3 ? "." : "");
        return addr.toString();
    }

    /**
     * Converts subnet prefix to binary
     * @param prefix    Subnet prefix as integer
     * @return          Subnet mask in binary
     */
    public static String prefixToBinary(int prefix) {
        StringBuilder binary = new StringBuilder().append(String.join("", Collections.nCopies(prefix, "1"))); // Appending right amount of ON bits
        binary.append(String.join("", Collections.nCopies(32 - prefix, "0"))); // Appending right amount of OFF bits
        return binary.toString();
    }

    /**
     * Converts binary to prefix
     * @param binary    Subnet in binary
     * @return          Subnet prefix
     */
    public static int binaryToPrefix(String binary) {
        return (isValidSubnetBinary(binary) ? binary.replaceAll("0", "").length() : 0);
    }

    /**
     * Checks the validity of IP address
     * @param addr  Ip address
     * @return      Is valid or not
     */
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

    /**
     * Check validity of subnet in IP format
     * @param addr  Subnet mask
     * @return      Is valid or not
     */
    public static boolean isValidSubnet(String addr) {
        return  isValidAddress(addr) && isValidSubnetBinary(ipToBinary(addr));
    }

    /**
     * Checks the validity of subnet mask in binary
     * @param binaryAddr    Subnet mask in binary
     * @return              Is valid or not
     */
    public static boolean isValidSubnetBinary(String binaryAddr) {
        if (binaryAddr.length() != 32) return false;
        boolean subnet = false;
        for (int i=0; i<32; i++) {
            if (!subnet && binaryAddr.charAt(i) == '0') subnet = true;
            else if (subnet && binaryAddr.charAt(i) == '1') return false;
        }
        return true;
    }

    /**
     * Checks validity of subnet prefix
     * @param prefix    Subnet prefix
     * @return          Is valid or not
     */
    public static boolean isValidSubnetPrefix(int prefix) {
        return prefix >= 8 && prefix <=32;
    }

    /**
     * Performs classical anding of IP address and subnet mask in binary
     * @param binary1   IP address or subnet in binary
     * @param binary2   IP address or subnet in binary
     * @return          Network address in binary
     */
    public static String anding(String binary1, String binary2) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            binary.append(binary1.charAt(i) == binary2.charAt(i) && binary1.charAt(i) == '1' ? 1 : 0);
        }
        return binary.toString();
    }

    /**
     * Calculates amount of ip's based on subnet prefix
     * @param prefix    Subnet prefix
     * @return          Amount of ip's
     */
    public static int getAmountOfIps(int prefix) {

        return (int)java.lang.Math.pow(2, 32-prefix);
    }

    /**
     * Calculates amount of hosts
     * @param prefix    Subnet prefix
     * @return          Amount of hosts
     */
    public static int getAmountOfHosts(int prefix) {
        return (isValidSubnetPrefix(prefix) && prefix < 31 ? getAmountOfIps(prefix) - 2 : (prefix == 31 ? 0 : 1));
    }
}
