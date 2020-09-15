package io.rapidash.timedelay.log;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.springframework.stereotype.Service;

@Service
public class Log {

    private String dateInStringFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'.log'").format(new Date());
    }

    private File logFile() {
        File logDir = new File("logs");
        if (!logDir.exists()) logDir.mkdir();
        String fileName = dateInStringFormat();
        String filePath = logDir.toString() + "/" + fileName;
        return new File(filePath);
    }


    private Logger logger() {   
        Logger logger = Logger.getLogger(dateInStringFormat()); 
        File lFile = logFile();
        try {      
            if (!lFile.exists()) lFile.createNewFile();
            FileHandler fileHandler = new FileHandler(lFile.getPath());
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }
    

    /**
     * Calculate Time Delay
     * @throws SocketException
     * @throws UnknownHostException
     * @throws IOException
     * @throws InterruptedException
     */
    public long CalculateTimeDelay() throws SocketException, UnknownHostException, IOException, InterruptedException {
        long start = System.currentTimeMillis();
        long ntpTime = getWebTime("time-a.nist.gov");
        long stop = System.currentTimeMillis();
        return ntpTime + (stop-start)/2 - System.currentTimeMillis();
    }


    public void writeLog() {
        Logger delayLogger = this.logger();
        while (true) {
            try {
                if (!delayLogger.getName().equals(dateInStringFormat())) {
                    delayLogger = this.logger();
                }
                long delay = CalculateTimeDelay();
                delayLogger.info(Long.toString(delay)+"ms");
                // Sleep for 5 mins
                Thread.sleep(100000);
            } catch (Exception e) {
                delayLogger.warning(e.toString());
                e.printStackTrace();
            }
        }
    }

    
    /**
     * Get Web Time
     * @return long
     */
    private long getWebTime(String address) throws SocketException, UnknownHostException, IOException {
    	NTPUDPClient client = new NTPUDPClient();
        client.open();
        client.setDefaultTimeout(500);
        client.setSoTimeout(500);
        InetAddress inetAddress = InetAddress.getByName(address);
        TimeInfo timeInfo = client.getTime(inetAddress);
        return timeInfo.getMessage().getTransmitTimeStamp().getTime();
    }
}
