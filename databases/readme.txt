1. create a service-key because mysql is using credhub
cf create-service-key cert-database mykey
2. redirect the u/p to tmp.txt
cf service-key cert-database mykey > tmp.txt

3. SSH tunnul
cf ssh -N -L 63306:dbhost:3306 appName

mysql -h 127.0.0.1 -P 63306 -uUsername -pPassword
