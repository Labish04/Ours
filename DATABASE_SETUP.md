# Database Setup Guide

## Prerequisites
1. **MySQL Server** must be installed and running
2. **MySQL JDBC Driver** (mysql-connector-j-9.2.0.jar) should be in the `lib/` directory

## MySQL Server Setup

### 1. Install MySQL Server
- Download and install MySQL Server from: https://dev.mysql.com/downloads/mysql/
- During installation, set the root password to: `admin123`

### 2. Start MySQL Service
- **Windows**: Open Services app and start "MySQL" service
- **Linux/Mac**: Run `sudo systemctl start mysql` or `sudo service mysql start`

### 3. Verify MySQL is Running
```bash
mysql -u root -p
# Enter password: admin123
```

### 4. Create Database (Optional - will be created automatically)
```sql
CREATE DATABASE IF NOT EXISTS happytravel;
USE happytravel;
```

## Alternative: Use Different Credentials

If you have different MySQL credentials, update the `MysqlConnection.java` file:

```java
String username = "your_username";
String password = "your_password";
String database = "your_database_name";
```

## Test Database Connection

Run the application and check the console output:
- If you see "Database connection successful!" - everything is working
- If you see error messages, follow the troubleshooting steps below

## Troubleshooting

### 1. "MySQL JDBC Driver not found"
- Make sure `mysql-connector-j-9.2.0.jar` is in the `lib/` directory
- Check that the JAR file is not corrupted

### 2. "Database connection failed"
- Verify MySQL server is running
- Check username/password are correct
- Ensure MySQL allows connections from localhost

### 3. "Access denied for user 'root'@'localhost'"
- Reset MySQL root password or create a new user
- Grant necessary privileges to the user

### 4. "Communications link failure"
- Check if MySQL is running on port 3306
- Verify firewall settings allow local connections

## Quick Test

To test if MySQL is working, run this command:
```bash
mysql -u root -padmin123 -e "SELECT 'MySQL is working!' as status;"
```

If this works, your MySQL setup is correct. 