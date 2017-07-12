package org.maxwu.phantomjs;

import org.maxwu.phantomjs.pages.PageBase;

/**
 * Created by maxwu on 7/10/17.
 */
public interface WebDrivable {
    // The method to open browser against absolute URL
    PageBase openUrl(String url);
    boolean checkUrl(String regExUrl);
    boolean checkTitle(String regExTitle);
}
