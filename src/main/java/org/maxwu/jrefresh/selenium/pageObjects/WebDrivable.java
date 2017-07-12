package org.maxwu.jrefresh.selenium.pageObjects;

/**
 * Created by maxwu on 5/30/17.
 */
public interface WebDrivable {
    <T extends PageBase> T get(String url);
    <T extends PageBase> T autoTrigger();
}
