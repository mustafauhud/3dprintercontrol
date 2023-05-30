#include <ESP8266WiFi.h>
#include <FirebaseESP8266.h>
#include <SoftwareSerial.h>

// Firebase bağlantısı için gerekli bilgiler
#define FIREBASE_HOST "https://nesnetez-5e48c-default-rtdb.firebaseio.com/"
#define FIREBASE_AUTH "iJ1eUfdJdaXNvSUwfzFOhNgVYmu1"
#define RX_PIN 13 //  pin on NodeMCU
#define TX_PIN 15 //  pin on NodeMCU
// WiFi ağı için gerekli bilgiler
const char* WIFI_SSID = "FiberHGW_TP1A26_2.4GHz";
const char* WIFI_PASSWORD = "EvmTjjX7";
SoftwareSerial rampsSerial(RX_PIN, TX_PIN);
// Firebase nesnesi tanımlama
FirebaseData firebaseData;

void setup() {
  Serial.begin(115200);
   rampsSerial.begin(115200);

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
  
   rampsSerial.println("M105");
  delay(1500);
   String receivedData = "";
   while (rampsSerial.available() > 0) {  // Seri portta okunacak veri varsa
      char character = rampsSerial.read();  // Veriyi oku
      receivedData += character;  // Okunan karakteri String'e ekle
    }
    // T harfinin ve / işaretinin konumunu bul
  int tIndex = receivedData.indexOf("T:");
    int bIndex = receivedData.indexOf("B:");

    if (tIndex >= 0 && bIndex >= 0) {  // "T:" ve "B:" bulundu mu kontrol et
      // T: ve B: arasındaki 4 karakterli değeri al
      String value = receivedData.substring(tIndex + 2, tIndex + 6);
      String value1 = receivedData.substring(bIndex + 2, bIndex + 6);
     // Alınan veriyi işle
    // Örneğin, ekrana yazdırmak için:
     double nozzle_temperature = value.toDouble();
     double table_temperature = value1.toDouble();
    Serial.print("Nozzle sicakligi: ");
    Serial.println(nozzle_temperature);
    Serial.print("Tabla sicakligi: ");
    Serial.println(table_temperature);
     Firebase.setDouble(firebaseData, "users/iJ1eUfdJdaXNvSUwfzFOhNgVYmu1/table_temperature", table_temperature);
     Firebase.setDouble(firebaseData, "users/iJ1eUfdJdaXNvSUwfzFOhNgVYmu1/nozzle_temperature", nozzle_temperature);
    delay(3000);
    
    }
 

  // Rastgele double değerler üretme
 /* double  table_temperature = random(59, 62) ;
  double nozzle_temperature = random(204, 210) ;
  double x_position = random(0, 250) / 10.0;
  double y_position = random(0, 250) / 10.0;
  double z_position = random(0, 250) / 10.0;
  
  // String değerleri tanımlama
  String string1 = "3D Printer Ready";
  
  
  // Verileri Firebase'a gönderme*/
  
  /*Firebase.setDouble(firebaseData, "example-data/x_position", x_position);
  Firebase.setDouble(firebaseData, "example-data/y_position", y_position);
  Firebase.setDouble(firebaseData, "example-data/z_position", z_position);
  delay(5000);*/
 }
