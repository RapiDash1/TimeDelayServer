package io.rapidash.timedelay.log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;


public class Log {
    

    /**
     * Calculate Time Delay
     * @throws SocketException
     * @throws UnknownHostException
     * @throws IOException
     * @throws InterruptedException
     */
    public void CalculateTimeDelay() throws SocketException, UnknownHostException, IOException, InterruptedException {
        while (true) {
            long start = System.currentTimeMillis();
            long ntpTime = getWebTime("time-a.nist.gov");
            long stop = System.currentTimeMillis();
            System.out.println(ntpTime + (stop-start)/2 - System.currentTimeMillis());
            // Sleep for 5 mins
            Thread.sleep(300000);
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
