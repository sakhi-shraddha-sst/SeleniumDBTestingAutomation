# Oracle Database + Docker + Java Automation Framework  
![CI](https://github.com/sakhi-shraddha-sst/SeleniumDBTestingAutomation/actions/workflows/ci.yml/badge.svg)

## 📊 Latest Automation Test Report

Access the latest Extent Report here:

➡️ [Latest Report](https://github.com/sakhi-shraddha-sst/SeleniumDBTestingAutomation.github.io/index.html)

---

## 1. Install Docker Desktop  
Download from https://www.docker.com/products/docker-desktop  
Ensure "Docker Engine is running".

---

## 2. Fix docker command not found  
MacOS: 
```bash
sudo ln -s /Applications/Docker.app/Contents/Resources/bin/docker /usr/local/bin/docker
```

---

## 3. Fix credential helpers  
MacOS: 

```bash
sudo ln -s /Applications/Docker.app/Contents/Resources/bin/docker-credential-desktop /usr/local/bin/
sudo ln -s /Applications/Docker.app/Contents/Resources/bin/docker-credential-osxkeychain /usr/local/bin/
```

---

## 4. Fix PATH (.zshrc)  
MacOS: 

```bash
export PATH="/usr/local/bin:/Applications/Docker.app/Contents/Resources/bin:$PATH"
```

---

## 5. Pull Oracle Free Database  
MacOS: 

```bash
docker pull gvenzl/oracle-free
```

---

## 6. Run Oracle Database Container  
MacOS: 

```bash
docker run -d --name oracle-free -p 1521:1521 -e ORACLE_PASSWORD=Oracle123 gvenzl/oracle-free
```

---

## 7. SQL Developer Connection  
- Username: sys  
- Password: Oracle123  
- Role: SYSDBA  
- Service Name: FREEPDB1  

---

## 8. Create AUTOMATION User  
```sql
CREATE USER AUTOMATION IDENTIFIED BY "Auto@123";
GRANT CREATE SESSION TO AUTOMATION;
GRANT CONNECT, RESOURCE TO AUTOMATION;
GRANT UNLIMITED TABLESPACE TO AUTOMATION;
```

---

## 9. Create USERS Table  
```sql
CREATE TABLE USERS (
    ID NUMBER PRIMARY KEY,
    USERNAME VARCHAR2(50),
    ROLE VARCHAR2(50)
);
```

---

## 10. Java DB Connection  
```
jdbc:oracle:thin:@localhost:1521/FREEPDB1
User: AUTOMATION
Password: Auto@123
```

---

## 11. Automation Test Success  
INSERT + SELECT operations work successfully.

---

