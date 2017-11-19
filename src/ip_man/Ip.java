package ip_man;

import java.util.Arrays;
import java.util.Collections;

public class Ip {
    public String address;
    public String subnetMask;
    public String subnetPerfix;
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
        subnetPerfix = value;
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

    protected  void addressToBinary() {
        setBinaryAddress(ipToBinary(address));
    }

    protected void subnetMaskToBinary() {
        setBinarySubnetMask(ipToBinary(subnetMask));
    }

    protected static String ipToBinary(String addr) {
        String binary = "";
        String[] chunks = addr.split("\\.");
        for (String chunk: chunks) {
            String newChunk = Integer.toBinaryString(Integer.parseInt(chunk));
            String zeros = String.join("", Collections.nCopies(8-newChunk.length(), "0"));
            binary = new StringBuilder(binary).append(zeros).append(newChunk).toString();
        }

        return binary;
    }

    protected static boolean isValidAddress(String addr) {
        // System.out.println(addr);
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
        //System.out.println(Arrays.toString(chunks));
        
        // Is there 4 chunks
        if (chunks.length != 4) {
            //System.out.println("Amount of CHUNKS");
            return false;
        }

        // Checking chunk length
        for (String chunk: chunks) {
            if (chunk.length() == 0 || chunk.length() > 3) {
                //System.out.println("Chunk length");
                return false;
            }
        }

        // If everything was OK then return TRUE
        return true;
    }

    protected static boolean isValidSubnet(String addr) {
        // TODO functionality
        return true;
    }
}
