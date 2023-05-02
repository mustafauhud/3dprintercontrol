#include <ESP8266WiFi.h>
#include <FirebaseESP8266.h>

// Firebase bağlantısı için gerekli bilgiler
#define FIREBASE_HOST "https://dprinter-control-default-rtdb.europe-west1.firebasedatabase.app/"
#define FIREBASE_AUTH "26063e9e42d9cef3b3b6ce72b693b0b2c95b19c2"

// WiFi ağı için gerekli bilgiler
const char* WIFI_SSID = "Millenicom_9E6A";
const char* WIFI_PASSWORD = "C0C9E3619E6A";

// Firebase nesnesi tanımlama
FirebaseData firebaseData;

void setup() {
  Serial.begin(115200);
  delay(10);

  // WiFi ağına bağlanma
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.println("Connecting to WiFi...");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");

  // Firebase bağlantısı kurma
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Serial.println("Firebase connected");
}

void loop() {
  // Rastgele double değerler üretme
  double  table_temperature = random(59, 62) ;
  double nozzle_temperature = random(204, 210) ;
  double x_position = random(0, 250) / 10.0;
  double y_position = random(0, 250) / 10.0;
  double z_position = random(0, 250) / 10.0;
  
  // String değerleri tanımlama
  String string1 = "3D Printer Ready";
  
  
  // Verileri Firebase'a gönderme
  Firebase.setDouble(firebaseData, "example-data/table_temperature", table_temperature);
  Firebase.setDouble(firebaseData, "example-data/nozzle_temperature", nozzle_temperature);
  Firebase.setDouble(firebaseData, "example-data/x_position", x_position);
  Firebase.setDouble(firebaseData, "example-data/y_position", y_position);
  Firebase.setDouble(firebaseData, "example-data/z_position", z_position);
  delay(5000);
 }
