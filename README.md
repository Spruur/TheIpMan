# The IP Man

This is not legendary Wing Tsun Kung Fu master but just an IP subnetter and calculator.

## What does it do?
It lets you to enter ip address and subnet mask and calculates basic information based on that:
* Ip address
* Network address
* Subnet mask
* Amount of hosts
* Broadcast address
* First host address
* Last host address

It even displays all this data in binary!

## Can I do something else with it?
Jep! You can just use the Ip class in your project. This hopefully saves you some time and useless fuss.
Also feel free to extend the functionality of this app. Just mention me (with my github) as the original creator.

## How to use
Just compile and run it as your any other Java program.


# TODO
* <s>CLI</s>
    * <s>IP main class</s>
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
    * <s>Subnet binary Functions</s>
        * <s>Finding amount of hosts</s>
        * <s>Finding first and last usable IP</s>
        * <s>Finding broadcast addr</s>
        
* <s>GUI</s>
    * <s>Basic design</s>
    * <s>Input fields</s>
        * <s>IP address</s>
        * <s>Subnet</s>
    * <s>Submitting</s>
        * <s>Submit button</s>
        * <s>Enter press</s>
    * <s>Output data</s>
        * <s>Display Network info</s>
            * <s>Network basic info</s>
                * <s>Network addr</s>
                * <s>Broadcast addr</s>
            * <s>Amount of IP addresses</s>
            * <s>Amount of host addresses</s>
            * <s>First and last host</s>
            
    
    
* <s>More</s>
    * <s>Method comments</s>
    * <s>Code comments</s>
    * <s>Clean up code</s>
    
    


# Changelog
### 0.0.3
* [NEW] Added javaDoc comments (12/12/2017)
* [NEW] Code cleanup (12/12/2017)
### 0.0.2
* [NEW VERSION] Version 0.0.2 to 0.0.3
* [NEW] Added first and last address calculation and displaying in GUI (03/12/2017)
* [NEW] Added broadcast address calculation (02/12/2017)
* [NEW] Submit on ENTER (02/12/2017)
* [CHANGE] Fixed subnet checking logic on submit (01/12/2017)
* [CHANGE] Fixed subnet checking logic in Ip constructor (29/11/2017)
* [NEW] Method to calculate amount of hosts in subnet (23/11/2017)
* [NEW] Method to calculate ip addresses in subnet (23/11/2017)
* [NEW] Amount of ip's, subnets, hosts setters and getters (23/11/2017)
* [CHANGE] Added binary subnet mask check to binary to prefix method (23/11/2017) 
* [NEW] Subnet mask validation - Several checks to be sure that subnet mask is valid(22/11/2017)
* [NEW] Ip address validation - Address chunk must be 0-255 (22/11/2017)




