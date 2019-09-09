/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
/**
 *
 * @author elainewijaya
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        // Create our exam demo
        Exam E = Exam.demo();
        
        // Save our exam into an XML file called MyExam.xml
        E.saveXMLFile("MyExam");
        
        // Take the exam we just created interactively by using the path specified
        Exam.takeExam(Exam.readXMLFile("MyExam.xml"));

    }
    
}
