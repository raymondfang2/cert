cf ssh -N -L 63306:dbhost:3306 appName

mysql -h 127.0.0.1 -P 63306 -uUsername -pPassword
