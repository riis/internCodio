package com.riis.cropcompare;

import com.riis.cropcompare.model.CropResultsTest;
import com.riis.cropcompare.model.VaultTest;
import com.riis.cropcompare.util.UtilTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CropResultsTest.class,
        UtilTest.class,
        VaultTest.class
})
public class TestSuite
{
}
