package org.maxwu.jrefresh.selenium;

import org.openqa.selenium.Capabilities;
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
                }
                return super.execute(
                    new Command(new SessionId(sessionId), DriverCommand.GET_CAPABILITIES,
                        new HashMap<String, String>(){
                            {
                                put("GET", "/session/" + sessionId);
                            }
                        })
                );
                //new Command(getSessionId(), "newSession", DriverCommand.GET_CAPABILITIES));
                //return new Response(CapturedWebDriver.super.getSessionId());
            }
        });
        //startSession(new DesiredCapabilities());
    }

    @Override
    protected void startSession(Capabilities desiredCapabilities) {
        if (this.getSessionId() == null) {
            super.startSession(desiredCapabilities);
        }
    }

    static public String getLocalUrl(int port){
        return "http://localhost:" + port;
    }
}
