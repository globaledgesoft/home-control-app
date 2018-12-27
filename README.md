# home-control-app
The goal of this application is to connect via BLE with QCA 402X device. Application sends command to control the end devices (Smart Lock, Smoke Sensor) via BLE to the Board which are connected via general IO pins. Board also has Home Control application (Link of Board application to be provided) implemented which will connect with a Smart bulb via BLE.

Below is the list of the connected devices with QCA 402X Board
* Smart Bulb (Securely connected via BLE from Board)
* Smart Lock (Connected via GPIO)
* Smoke Sensor (Connected via GPIO)

For Smart Bulb and Smart Lock, the application has the control to turn them ON and OFF only. It is assumed that only one device of each is connected to the board.

For Smoke Detector, Smoke sensor data reading is notified to this Android application every 30 seconds interval from QCA402X. The graph displayed for Smoke sensor is of an hour information received from the Board. This application will not maintain any history data for the smoke sensor.