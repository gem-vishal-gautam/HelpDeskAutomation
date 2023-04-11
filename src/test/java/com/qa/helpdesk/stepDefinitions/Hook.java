package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.GemJarGlobalVar;
import com.gemini.generic.utils.GemJarUtils;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook {
    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        DriverManager.initializeChrome(options);
        DriverAction.launchUrl(GemJarUtils.getGemJarConfigData("launchUrl"));
        DriverAction.setImplicitTimeOut(Long.parseLong(GemJarGlobalVar.implicitTime));
        DriverAction.setPageLoadTimeOut(Long.parseLong(GemJarGlobalVar.pageTimeout));
        DriverAction.setScriptTimeOut(Long.parseLong(GemJarGlobalVar.scriptTimeout));
        DriverAction.maximizeBrowser();
    }
}
