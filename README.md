# Aegis Pass - Password & Security Manager
A robust and secure password and security management app designed to safeguard login credentials and enhance overall digital security. Aegis Pass functions as both an anti-virus software and password manager through its various features. Aegis Pass uses modern hashing algorithms such as bcrypt and Argon2 for secure password hashing, as well as the AES-256 encryption protocol in order to securely manage login credentials. Watch the following [video](https://drive.google.com/file/d/1_V0XC_BZzbE2enYxWh5WpJc8_yfds1t_/view?usp=sharing) for a demo of Aegis Pass.   

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
![Screenshot from 2023-12-30 19-46-58](https://github.com/hadi-naqvi/Aegis-Pass/assets/92334527/427a464b-5a3c-4d23-8815-1a728e1f64c8)
![Screenshot from 2023-12-30 20-07-53](https://github.com/hadi-naqvi/Aegis-Pass/assets/92334527/d84410ae-f5c1-4501-979f-cada2c9e1989)
![Screenshot from 2023-12-30 19-53-51](https://github.com/hadi-naqvi/Aegis-Pass/assets/92334527/3ddfc6f1-f5cf-456c-87de-90dae55c081c)
![Screenshot from 2023-12-30 19-53-18](https://github.com/hadi-naqvi/Aegis-Pass/assets/92334527/b0c5dbd8-235e-484c-ad57-4099a0a759be)
![Screenshot from 2023-12-30 19-52-50](https://github.com/hadi-naqvi/Aegis-Pass/assets/92334527/e339c522-763d-4ac5-852e-a009d5c1d307)
![Screenshot from 2023-12-30 19-52-33](https://github.com/hadi-naqvi/Aegis-Pass/assets/92334527/3a86dc43-9c7c-4e53-8187-47e0823b7121)


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
