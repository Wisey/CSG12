package uk.ac.aber.group12.walkingtour.data;


import junit.framework.Assert;
import junit.framework.TestCase;

// import org.junit.Test;

//import org.robolectric.RobolectricTestRunner;


/**
 * Created by srp11 on 29/01/2014.
 */
//(RobolectricTestRunner.class)
public class TourLocationTest extends TestCase {

    //TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);

    public void setUp() throws Exception {
        TourLocation TL=new TourLocation("road", "first test location", "", "", 0.3, 23.1, 312312313.122);
    }

/*
    @Test
    public void testGetName() throws Exception {
        try{
            TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);
            Assert.assertEquals("yay name works",TL.getName(),"road");
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void testGetDescription() throws Exception {
        try{
            TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);
            Assert.assertEquals("yay description works",TL.getDescription(),"first test location");
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void testGetImageFilePath() throws Exception {
        try{
            TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);
            Assert.assertEquals("yay image path works",TL.getImage(),"");
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }

    public void testGetLatitude() throws Exception {
        try{
            TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);
            Assert.assertEquals("yay it works",TL.getLatitude(),0.3);
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void testGetLongitude() throws Exception {
        try{
            TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);
            Assert.assertEquals("yay it works",TL.getLongitude(),23.1);
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
    @Test
    public void testGetTime() throws Exception {
        try{
            TourLocation TL=new TourLocation("road", "first test location", "", 0.3, 23.1, 312312313.122);
            Assert.assertEquals("yay it works",TL.getTime(),312312313.122);
        }
        catch (Exception e){
            e.getStackTrace();
        }

    }*/
}
