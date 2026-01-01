package IslahiArt.util;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import IslahiArt.base.TestBase;

public class TestUtil extends TestBase {

    public static String takeScreenshotAtEndOfTest() throws IOException {
        // 1. Take the Screenshot
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        
        // 2. Create a dynamic filename (timestamp) so we don't overwrite old ones
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String currentDir = System.getProperty("user.dir");
        String destination = currentDir + "/screenshots/" + timestamp + ".png";
        
        // 3. Save the file
        FileUtils.copyFile(scrFile, new File(destination));
        
        return destination; // Return the path so we can attach it to the report later
    }
}