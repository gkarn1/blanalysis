package blanalysis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class landStructTest {
    public landStructTest(){

    }
    @BeforeClass
    public static void setUpClass(){

    }
    @AfterClass
    public static void tearDownClass(){

    }

    //Checking fertileLand method
    @Test
    public void testFertileLand(){
        landStruct test1 =  new landStruct("0 292 399 307");
        String out = test1.fertileLand();
        assertEquals("116800 116800", out);
        landStruct test2 =  new landStruct("48 192 351 207, 48 392 351 407, 120 52 135 547, 260 52 275 547");
        String out1 = test2.fertileLand();
        assertEquals("22816 192608", out1);
    }

    //Checking placeBarrenRect method
    @Test
    public void testPlaceBarrenRect(){
        landStruct test3 = new landStruct("0 292 399 307");
        test3.placeBarrenRect("0 0 399 599");
        String out3 = test3.fertileLand();
        assertEquals("No fertile land found", out3);
    }

    //Checking clearBarren method
    @Test
    public void testClearBarren(){
        landStruct test4 = new landStruct("0 0 399 599");
        test4.clearBarren();
        String out4 = test4.fertileLand();
        assertEquals("240000", out4);
    }
}