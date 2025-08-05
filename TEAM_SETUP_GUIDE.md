# Team Setup Guide - Database Credentials

## Overview
This project uses environment variables for database credentials, allowing each developer to use their own MySQL password without changing the code.

## Method 1: Using the Batch File (Recommended)

### Step 1: Edit the Batch File
1. Open `run_with_env_vars.bat`
2. Find these lines:
   ```batch
   set DB_USERNAME=root
   set DB_PASSWORD=your_mysql_password_here
   ```
3. Replace `your_mysql_password_here` with your actual MySQL password

### Step 2: Run the Application
1. Double-click `run_with_env_vars.bat`
2. The application will start with your credentials

## Method 2: Set Permanent Environment Variables

### Step 1: Open Command Prompt as Administrator
- Press `Win + X`
- Select "Windows PowerShell (Admin)" or "Command Prompt (Admin)"

### Step 2: Set Environment Variables
```bash
setx DB_USERNAME "root"
setx DB_PASSWORD "your_mysql_password"
```

### Step 3: Restart Your IDE
- Close NetBeans/Eclipse
- Reopen your IDE
- Run the project normally

## Method 3: Set Environment Variables for Current Session

### Step 1: Open Command Prompt
### Step 2: Set Variables
```bash
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password
```

### Step 3: Run Application
```bash
java -cp "lib/mysql-connector-j-9.2.0.jar;lib/mail.jar;lib/org;src" happytravell.HappyTravell
```

## Troubleshooting

### "Access denied" Error
- Check if your MySQL password is correct
- Verify MySQL is running
- Try connecting with: `mysql -u root -p`

### Environment Variables Not Working
- Restart your IDE/Command Prompt
- Check if variables are set: `echo %DB_USERNAME%`
- Use Method 1 (batch file) instead

### Database Connection Issues
- Ensure MySQL is running
- Check if `happytravel` database exists
- Verify your MySQL credentials

## Security Benefits

✅ **No passwords in code**
✅ **Each developer has their own credentials**
✅ **Easy to change without code modifications**
✅ **Professional approach**

## Example for Different Developers

**Developer A:**
```batch
set DB_PASSWORD=admin123
```

**Developer B:**
```batch
set DB_PASSWORD=root
```

**Developer C:**
```batch
set DB_PASSWORD=
```

## Testing the Setup

1. Run the application
2. Check console for: `"Database connected successfully!"`
3. Try the password reset flow
4. Verify everything works

## Support

If you have issues:
1. Try Method 1 (batch file) first
2. Check your MySQL password
3. Ensure MySQL is running
4. Verify database exists 