# The IP Man

This is not legendary Wing Tsun Kung Fu master but just an IP subnetter and calculator.

IN PROGRESS.. More to come soon

# Plan
* CLI interface with calculation functionality
* Grapfic interface with magic and stuff

# TODO
* CLI
    * IP main class
        * <s>IP</s>
        * <s>Binary</s>
        * <s>Subnet</s>
        * <s>Network</s>
        * <s>Subnet prefix</s>
        * <s>Binary to IP</s>
        * <s>IP to Binary</s>
        * <s>Subnet prefix to binary</s>
        * <s>Network calculation</s>
        * <s>Constructor logic</s>
        * <s>Getting string representations</s>
        * <s>Finding network</s>
    * Subnet binary Functions
        * <s>Finding amount of hosts</s>
        * Finding amount of subnets
        * Finding first and last usable IP
        * Finding broadcast addr
        
* GUI
    * <s>Basic design</s>
    * <s>Input fields</s>
        * <s>IP address</s>
        * <s>Subnet</s>
    * Submitting
        * <s>Submit button</s>
        * Enter press
    * Output data
        * Display subnet variations
        * Display IP variations
        * Display Network info
            * Network basic info
                * <s>Network addr</s>
                * Broadcast addr
            * Amount of IP addresses
            * Amount of host addresses
            * First and last host
            
    
    
* More
    * Method comments
    * Code comments
    
    


# Changelog
### 0.0.2
* [NEW] Ip address validation - Address chunk must be 0-255 (22/11/2017)
* [NEW] Subnet mask validation - Several checks to be sure that subnet mask is valid(22/11/2017)
* [CHANGE] Added binary subnet mask check to binary to prefix method (23/11/2017) 
* [NEW] Amount of ip's, subnets, hosts setters and getters (23/11/2017)
* [NEW] Method to calculate ip addresses in subnet (23/11/2017)
* [NEW] Method to calculate amount of hosts in subnet (23/11/2017)
