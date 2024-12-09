./generate_jwtKeys.sh
chmod 644 ./jwt/privateKey.pem  
chmod 755 ./jwt  
mvn clean compile package
cd ../
docker compose up