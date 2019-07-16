# Jupiter
BruteForce Tool
--------
Simple Setup Really,
#Step 1, Depends.
Centos: yum update -y; yum upgrade -y; yum install java -y
Ubuntu: apt-get update -y; apt-get upgrade -y; apt-get install default-jdk -y;
#Step 2, Running It
java -jar Jupiter.jar

How to use SSH Bruter:
'SSH <IP>:<PORT> <COMBOLIST> <TIMEOUT IN SECONDS>'
IP = SSH IP TO BRUTE
PORT = SSH PORT
COMBOLIST = A BUNCH OF USERNAMES AND PASSWORDS FORMATTED LIKE BELOW
USER:PASS
USER2:PASS2
TIMEOUT = HOW LONG UNTIL DISCONNECT
Example: 'SSH 1.1.1.1:22 combos.txt 5'
