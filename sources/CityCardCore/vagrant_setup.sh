export MYSQL_HOST='192.168.56.11'
#sudo apt update
#sudo apt install -y software-properties-common
#sudo add-apt-repository -y ppa:cwchien/gradle
sudo apt update
sudo apt install -y default-jdk
#sudo apt install -y gradle
sudo apt install -y curl
sudo apt install -y zip
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle 7.4
cd /vagrant
gradle bootRun
#gradle wrapper --gradle-version 7.4
#./gradlew bootRun