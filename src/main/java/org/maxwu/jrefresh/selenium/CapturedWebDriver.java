package org.maxwu.jrefresh.selenium;

import org.openqa.selenium.remote.*;
import java.io.IOException;
import java.net.URL;

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
                if (command.getName() != "newSession") {
                    return super.execute(command);
                }
                return super.execute(new Command(getSessionId(), "getCapabilities"));
            }
        });
        startSession(new DesiredCapabilities());
    }

    static public String getLocalUrl(int port){
        return "http://localhost:" + port;
    }
}
