# Aegis Pass - Password & Security Manager
A robust and secure password and security management app designed to safeguard login credentials and enhance overall digital security. Aegis Pass functions as both an anti-virus software and password manager through its various features. Aegis Pass uses modern hashing algorithms such as bcrypt and Argon2 for secure password hashing, as well as the AES-256 encryption protocol in order to securely manage login credentials. Watch the following [video]() for a demo of Aegis Pass.   

## Features
- Login system with ability to create/use multiple accounts for multiple users to use the app
- Securely create, edit, view, and delete login data for online accounts
- Generate 2FA authentication tokens (automatically refreshes every 30 seconds)
- Generate secure passwords from custiomizable character sets
- Assess the quality of passwords and how difficult it is to hack them using an entropy calculation
- Generate ephemeral/temporary email addresses (using the mail.gw API)
- Scan URLs and Files for any security threats such as malware, viruses, phishing links, etc. (using the VirusTotal API)
- Ability to check if any emails or passwords show up in database breaches (using the haveibeenpwned API)
- Autotype passwords and login credentials and copy login credentials to clipboard

## Preview


## Project Dependencies
- mysql-connector-j-8.2.0
- mindrot.jbcrypt
- junit:junit:4.13.1
- bouncycastle.bcprov.jdk15on:1.70
- org.json:json:20210307
- jgoodies.common-1.8.1.jar
- jgoodies.forms-1.9.0.jar
- com.warrenstrange:googleauth:1.5.0

## Environment Variables
- DB_URL (Remote/Local database URL)
- DB_USERNAME (Database login credentials)
- DB_PASSWORD (Database login credentials)
- DB_PEPPER (Optionally generate and use a unique pepper for password hashing for added security)
- VT_APIKEY (VirusTotal API Key)
- PWN_APIKEY (haveibeenpwned API Key)

### Note
Project for CSC207 - Software Design at the University of Toronto