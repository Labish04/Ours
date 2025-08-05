@echo off
echo Setting up database credentials for your environment...
echo Please edit this file and set your MySQL password below

REM ===========================================
REM EDIT THESE LINES WITH YOUR MYSQL PASSWORD
REM ===========================================
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password_here
REM ===========================================

echo Starting HappyTravell with your database credentials...
echo Username: %DB_USERNAME%
echo Database: happytravel

java -cp "lib/mysql-connector-j-9.2.0.jar;lib/mail.jar;lib/org;src" happytravell.HappyTravell

pause 