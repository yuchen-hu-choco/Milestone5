package org.json.junit;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.concurrent.Future;

public class YuchenHuTest {

    // Initialize
    File smallFile = new File("small.xml");
    private Reader smallReader = new FileReader(smallFile);

    File medFile = new File("medium.xml");
    private Reader medReader = new FileReader(medFile);

    File errorFile = new File("WrongXML.xml");
    private Reader errorReader = new FileReader(errorFile);

    XML.asyncRunner runner = new XML.asyncRunner();

    public YuchenHuTest() throws FileNotFoundException {
    }

    /*********
    * For this test, I added medium size XML to the async runner first and the add small size
     * I passed a function to print out the result after the JSONObject is processed
     * The result is the small size XML print out first, since it takes less time to be processed
     *********/

    @Test
    public void test1() {
        Future<JSONObject> task1 = XML.toJSONObject(medReader, (jo) -> {System.out.println(jo);}, (e) -> {
            System.out.println("Error: " + e.getClass());});
        Future<JSONObject> task2 =  XML.toJSONObject(smallReader, (jo) -> {System.out.println(jo);}, (e) -> {
            System.out.println("Error: " + e.getClass());});
        runner.add(task1);
        runner.add(task2);
        runner.run();
    }

    /************
     * For this test, I tested the exception handling method
     ************/
    @Test
    public void test2() {
        Future<JSONObject> task1 = XML.toJSONObject(errorReader, (jo) -> {System.out.println(jo);}, (e) -> {
            System.out.println("Error: " + e.getClass());});
        runner.add(task1);
        runner.run();
    }

    /**********
     * Feel free to add your own test cases, thank you :)
     *********/
}
