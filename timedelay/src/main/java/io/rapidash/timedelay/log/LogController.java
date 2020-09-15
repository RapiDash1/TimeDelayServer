package io.rapidash.timedelay.log;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private Log log;

    @GetMapping("/delay")
    public String timeDelay() throws SocketException, UnknownHostException, IOException, InterruptedException {
        return Long.toString(log.CalculateTimeDelay());
    }
    
}
