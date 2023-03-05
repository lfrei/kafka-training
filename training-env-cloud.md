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
* Optional: HTTP client to send requests like Postman


## Access to your environment
You get from your instructor access to a ubuntu-vm in the cloud where all trainig resources are up and running:

* login [url](https://mottbott.signin.aws.amazon.com/console)

* username / pwd: in a separate mail

After login, you find your VM [here](https://lightsail.aws.amazon.com/ls/webapp/home/instances)

There are different VM's running, use the one with your name.


## Configure your environemnt

### SSH

For some exercises you need to login with ssh to your instance. You might use the web console, or login with your local shell (preferred):
* download the ssh key `Connect ->  Downld default key`
* Login: `ssh -i LightsailDefaultKey-eu-central-1.pem -l ubuntu [ip of your instance]`
  * in the case password protection is enforced, create a password-protected file: `openssl rsa -aes256 -in LightsailDefaultKey-eu-central-1.pem -out myProtectedKey.pem`


### Tool access

To enable tool access for the exercieses, whitelist [the IP of your workstation](https://whatismyipaddress.com/) with the following port `Networking -> IPv4 Firewall -> Add rule`:
* AKHQ: Custom TCP 8080 [your IP]
* Kafka: Custom TCP 9092 [your IP]
... TODO

If your IP changes, you have to redo this

### Update to the latest exercises
* login with ssh
* `cd kafka-training/`
* `docker-compose down`
* `git pull --rebase`
* `docker-compose up -d`


### Test
 * in your browser: `[ip of your vm]:8080`

You should see the AKHQ Topics screen


