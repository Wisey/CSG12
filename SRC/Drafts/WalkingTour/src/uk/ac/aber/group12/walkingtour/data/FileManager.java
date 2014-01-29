package uk.ac.aber.group12.walkingtour.data;

import java.io.*;

/**
 * Created by anp28 on 29/01/2014.
 */
public class FileManager {

    public void writeToFile(Tour[] tour)
    {
        File f = new File("tour.ser");
        if(f.isFile())
        {

            append(tour);

        }
        else {
            try
            {
                FileOutputStream fileOut =
                        new FileOutputStream("tour.ser");


                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                out.writeObject(tour);
                out.close();
                fileOut.close();

            }
            catch(IOException i)
            {
                i.printStackTrace();
            }
        }
    }

        public Tour[] readFromFile()
        {

            Tour[] tour = null;
try{
    FileInputStream fis = new FileInputStream("tour.ser");
    ObjectInputStream ois = new ObjectInputStream(fis);
    try
            {


                tour = (Tour[]) ois.readObject();
                ois.close();
                fis.close();

            }
            catch(IOException i)
            {
                i.printStackTrace();
            }
            catch(ClassNotFoundException c)
            {
                System.out.println("CLASS NOT FOUND");
                c.printStackTrace();
            }

    }
catch(IOException g)
{
            g.printStackTrace();
}
           remove();
             return tour;
       }


    public void remove(){


        File f = new File("tour.ser");
        if(f.isFile())
        {
            f.delete();

        }
        else
        {
            System.err.println("uuhhh.. What happened to the file?!");

        }
    }

    public void append(Tour[] tour){

        Tour[] temp = readFromFile();
        Tour[] newTour = new Tour[temp.length + tour.length];

        int i;
        int j = 0;

        for(i = 0; i < temp.length; i++)
        {
            newTour[i] = temp[i];

        }
        for(i = i; i < tour.length+temp.length; i++)
        {
            newTour[i] = tour[j];
            j++;

        }

        writeToFile(newTour);





    }



}
