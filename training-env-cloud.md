# Kafka Training Environment
[⬅️ Back to Kafka overview](README.md)


This training comes with a predefined environment provided in multiple docker containers. 

## Technical prerequisite for the training

The following service components should be installed on your training device before attending the training:

* Development: 
  * VS Code (or your IDE/text editor of choice) 
  * Current Java JDK
  * Git
  * Maven (if not already included in IDE)
* Clone `git clone https://github.com/lfrei/kafka-training.git` to your training device
* Optional: HTTP client to send requests like Postman


## Access to your environment

From your instructor you will get access to a ubuntu-vm in the cloud, where all training resources are up and running:

* login [url](https://mottbott.signin.aws.amazon.com/console)

* username / pwd: in a separate mail

After login, you find your VM [here](https://lightsail.aws.amazon.com/ls/webapp/home/instances)

There are different VM's running, use the one with your name.


## Configure your environment

### SSH

For some exercises you need to login with ssh to your instance. You might use the web console, or login with your local shell (preferred):
* download the ssh key `Connect ->  Download default key`
* Login: `ssh -i LightsailDefaultKey-eu-central-1.pem -l ubuntu myVMsIP`
  * in the case password protection is enforced, create a password-protected file: `openssl rsa -aes256 -in LightsailDefaultKey-eu-central-1.pem -out myProtectedKey.pem`


### Tool access

To enable tool access for the exercises, whitelist [the IP of your workstation](https://whatismyipaddress.com/) with the following port `Networking -> IPv4 Firewall -> Add rule`:
* AKHQ:            Custom TCP 8080 [your IP]
* Schema Registry: Custom TCP 8081 [your IP]
* Rest Proxy:      Custom TCP 8082 [your IP]
* Kafka Connect:   Custom TCP 8083 [your IP]
* KSQL:            Custom TCP 8088 [your IP]
* (Kafka Broker localhost: 9092 not needed) 
* Kafka Broker:    Custom TCP 9094 [your IP]
* phpMyadmin:      Custom TCP 3306 [your IP]

If your IP changes, you have to redo this

### Update to the latest exercises
* login with ssh
* `cd kafka-training/`
* `docker-compose down`
* `git pull --rebase`
* `docker-compose up -d`


### Modify your local hosts file (DNS entry)

Add the following entry in your hosts file:  `[ip of your VM] myVMsIP`
* Windows in `C:\Windows\System32\drivers\etc\hosts`
* MAC in `/private/etc/hosts`



### Test
 * in your [browser](http://myVMsIP:8080): `myVMsIP:8080`

You should see the AKHQ Topics screen


