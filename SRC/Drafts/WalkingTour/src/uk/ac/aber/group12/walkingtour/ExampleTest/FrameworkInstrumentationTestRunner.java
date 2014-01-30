package uk.ac.aber.group12.walkingtour.ExampleTest;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

import junit.framework.TestSuite;
/**
 * Created by srp11 on 30/01/2014.
 */
public class FrameworkInstrumentationTestRunner extends InstrumentationTestRunner {

    @Override
    public TestSuite getAllTests() {
        InstrumentationTestSuite suite = new InstrumentationTestSuite(this);

       // suite.addTestSuite(LocationTest.class);

        return suite;
    }

    @Override
    public ClassLoader getLoader() {
        return FrameworkInstrumentationTestRunner.class.getClassLoader();
    }
}

