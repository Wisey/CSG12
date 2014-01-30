package uk.ac.aber.group12.walkingtour.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by anp28 on 29/01/2014.
 */

/**
 * This class manages the local saving and loading of tours to the Android device.
 * This is done so that in the event of a connection failure, the tours can be uploaded at a later
 * date.
 * <p/>
 */
public class FileManager {


    /**
     * This method serializes an array of tours to a .ser file. The file is stored locally on the
     * device running the application.
     * If the file already exists on the device, then the file is ammended and the new array of tours
     * are saved with the old array of tours.
     *
     * @param tour The array of tours we wish to save locally.
     */
    public void writeToFile(Tour[] tour) {
        File f = new File("tour.ser");
        if (f.isFile()) {

            append(tour);

        } else {
            try {
                FileOutputStream fileOut =
                        new FileOutputStream("tour.ser");


                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                out.writeObject(tour);
                out.close();
                fileOut.close();

            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    /**
     * This method deserializes the tours stored in the file and turns it back into an array of tours.
     * It then removes the written file. The array of Tours is returned so that it can then be converted
     * into the JSON String to send to the Web-Server Database.
     *
     * @return The array of tours that has been deserialized from local storage.
     */
    public Tour[] readFromFile() {

        Tour[] tour = null;
        try {
            FileInputStream fis = new FileInputStream("tour.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                tour = (Tour[]) ois.readObject();
                ois.close();
                fis.close();

            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("CLASS NOT FOUND");
                c.printStackTrace();
            }

        } catch (IOException g) {
            g.printStackTrace();
        }
        remove();
        return tour;
    }

    public void remove() {


        File f = new File("tour.ser");
        if (f.isFile()) {
            f.delete();

        } else {
            System.err.println("There was an error reading from the file. ");

        }
    }

    /**
     * This is the method used if an attempt to write on a file that already exists takes place.
     * In order to prevent the file from being overwritten, it is read back into an array,
     * and is combined with with the new array we are trying to save.
     * The new, updated array is then written back into the file.
     *
     * @param tour This is the new array that must be combined with the original array already stored in file, before being saved.
     */
    public void append(Tour[] tour) {

        Tour[] temp = readFromFile();
        Tour[] newTour = new Tour[temp.length + tour.length];

        int i;
        int j = 0;

        for (i = 0; i < temp.length; i++) {
            newTour[i] = temp[i];

        }
        for (i = i; i < tour.length + temp.length; i++) {
            newTour[i] = tour[j];
            j++;

        }

        writeToFile(newTour);


    }


}
