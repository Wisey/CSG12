package uk.ac.aber.group12.walkingtour.data;

import junit.framework.*;
import java.io.*;
import junit.framework.Assert.*;
/**
 * Created by anp28 on 29/01/2014.
 */
public class FileManagerTest {

    FileManager fm = new FileManager();
    Tour[] tour = new Tour[1];
    File f;

    public void setUp() throws Exception {
        //super.setUp();


        tour[0] = new Tour("Test1","SD","LD");


       f = new File("tour.ser");
        File g = new File("test.ser");

    }

    public void testWriteToFile() throws Exception
    {
        if(f.isFile())
        {
          try
          {
              fm.writeToFile(tour);
              Assert.assertTrue(true);
          }
          catch(Exception E)
            {
                Assert.assertTrue(false);
            }

        }
        else
        {
            Assert.assertTrue(false);
        }


    }

    public void testReadFromFile() throws Exception {

        if(f.isFile())
        {
            Tour[] temp =  fm.readFromFile();
            Assert.assertNotNull(temp);
        }
        else
        {
           Assert.assertTrue(false);

        }
    }

    public void testRemove(File g) throws Exception {

        if (g.isFile())
        {
            g.delete();
           Assert.assertEquals(g.isFile(), false);
        }
        else
        {
            Assert.assertEquals(g.isFile(), false);
        }
    }

    public void testAppend() throws Exception {


        Tour appended = new Tour("Tour", "This is a tour.", "This is a really long description of the tour.");
        Tour moreAppended = new Tour("nm","s","l");

        Tour[] tourAppends = new Tour[2];
        Tour[] tour = new Tour[1];

        Tour testTour = new Tour("a","b","c");
        tour[0] = testTour;
        fm.writeToFile(tour);

        tourAppends[0] = appended;
        tourAppends[1] = moreAppended;
        fm.writeToFile(tourAppends);

        Tour[] temp = fm.readFromFile();

        Assert.assertNotNull(temp[2]);
    }
}
