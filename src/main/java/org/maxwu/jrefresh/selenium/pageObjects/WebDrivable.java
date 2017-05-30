package org.maxwu.jrefresh.selenium.pageObjects;

/**
 * Created by maxwu on 5/30/17.
 */
public interface WebDrivable {
    public <T extends PageBase> T get(String url);
    public <T extends PageBase> T autoTrigger();
}
