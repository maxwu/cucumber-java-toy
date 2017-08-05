package org.maxwu.jrefresh.selenium;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxwu on 7/18/17.
 */
public class CapturedWebDriver extends RemoteWebDriver {
    public CapturedWebDriver(URL url, String sessionId){
        super();
        setSessionId(sessionId);
        setCommandExecutor(new HttpCommandExecutor(url) {
            @Override
            public Response execute(Command command) throws IOException {
                String cmdName = command.getName();
                if (cmdName != "newSession") {
                    System.out.println("executing " + cmdName);
                    return super.execute(command);
                    //DriverCommand.GET_ALL_SESSIONS;
                    //If test a cmd returns failure, set sessionID to null and force a newSession cmd.
                }
                return super.execute(
                    new Command(CapturedWebDriver.super.getSessionId(), DriverCommand.GET_CAPABILITIES,
                        new HashMap<String, Capabilities>(){
                            {
                                //put("GET", "/session/" + sessionId);
                                put("desiredCapabilities", new DesiredCapabilities("","", Platform.ANY));
                            }
                        })
                );
                //new Command(getSessionId(), "newSession", DriverCommand.GET_CAPABILITIES));
                //return new Response(CapturedWebDriver.super.getSessionId());
            }
        });
        //startSession(new DesiredCapabilities());
    }

//    @Override
//    protected void startSession(Capabilities desiredCapabilities) {
//        if (this.getSessionId() == null) {
//            super.startSession(desiredCapabilities);
//        }
//    }

    static public String getLocalUrl(int port){
        return "http://localhost:" + port;
    }
}
