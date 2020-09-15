# TimeDelayServer

Google assumes a clock drift of 200ppm(parts per million) for its servers, which is about 6ms of delay if they are resynchronized every 30 secs or 17 secs delay if they are resynchronized everyday.

This is web server which measures the time delay your machine and an ntp server. \
Makes a request to an ntp server periodically. \
Creates a log for those responses. \
Exposes an endpoint to query current delay, so that you are vaguely aware of the network traffic


---

## Getting Started

### Prerequisites
* Clone this repo to your local machine.
* Install java if you havent.

### API
* Run the app on your machine. 
* Go to the following link in your browser to get current time delay:
    ```
    http://localhost:8080/delay
    ```

---

## Author

* RapiDash1