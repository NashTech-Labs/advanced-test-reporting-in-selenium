package Com;


import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;


public class RunTheProgram {
    void utSetUp() throws Exception {
    }

    void setUp() throws Exception {

    }

    @DataProvider(name = "data-provider")
    public Object[] dpMethod() throws Exception{
        Content fileUtil = new Content();
        List<SheetColumnHeader> sheetColumnHeaders = fileUtil.readFile();
        return sheetColumnHeaders.toArray();
    }

    @Test(dataProvider = "data-provider")
    void test(SheetColumnHeader sheetColumnHeader) throws Exception {
        boolean testPassed = false;


        DriverUtility driverUtility = DriverUtility.getInstance();
        testPassed = driverUtility.performTest(sheetColumnHeader);
        driverUtility.shutdownDriver();

        if(Com.Content.map.get(sheetColumnHeader).getExpectedResult().equalsIgnoreCase("pass")){
            Assert.assertEquals(true, testPassed);
        }else{
            Assert.assertEquals(false, testPassed);

        }


    }

    @AfterMethod
    void tearDown() {

    }

}
