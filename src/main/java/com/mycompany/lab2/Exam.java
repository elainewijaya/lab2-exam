/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.regex.*;  

/**
 *
 * @author elainewijaya
 */
public class Exam implements XMLizable{
    // Create our list of questions
    private final ArrayList <Question> questions;
    
    Exam () {
        questions = new ArrayList<>();
    }
    
    // Add question to our exam
    void addQuestion (Question q) {
        this.questions.add(q);
    }
    
    // Getter for questions
    ArrayList <Question> getQuestions () {
        return questions;
    }
    
    // Write XML for exam tags
    @Override
    public String writeXML(){
        String questionStr = "";
        for (Question q: questions) {
            questionStr += q.writeXML();
        }
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<exam>\n" + questionStr + "</exam>\n";
        return result;
        
    }
    // Save the XML file into a provided file name
    void saveXMLFile (String fileName) {
        try {
            FileWriter fw;
            fw = new FileWriter(fileName + ".xml");
            fw.write(this.writeXML());
            fw.close();
        } catch (IOException ex) {
            
        }
        
        }
    
    // Create function to read an XML file and return an exam
    public static Exam readXMLFile (String fileName) throws SAXException, IOException, ParserConfigurationException {
        // Create exam object for us to return eventually
        Exam e = new Exam();
        
        // Read our xml file from source folder
        File xmlFile = new File(fileName);
        // Create factory of class DocumentBuilderFactory, and a builder from our factory object
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();  
        // Use the builder to parse the XML file and return a DOM document
        Document doc = dBuilder.parse(xmlFile);
        
        // Normalize to make sure we get the right elements - delete extra whitespace, line breaks etc
        doc.getDocumentElement().normalize();
        
        // Traverse MCQ questions
        NodeList mList = doc.getElementsByTagName("MultipleChoice");
        for(int i = 0; i < mList.getLength() ; i ++) {
            Node mNode = mList.item(i);
            
            // If our current node is an element, traverse
            if(mNode.getNodeType() == Node.ELEMENT_NODE){
                Element mElem = (Element) mNode;
                String mQuestion = mElem.getAttribute("question");
                String mAnswer = mElem.getAttribute("answer");
                e.addQuestion(new MultipleChoice(mQuestion,mAnswer));
            }
        }  
        
        // Traverse Short Answer questions
        NodeList sList = doc.getElementsByTagName("ShortAnswer");
        for(int i = 0; i < sList.getLength() ; i ++) {
            Node sNode = sList.item(i);
            
            // If our current node is an element, traverse
            if(sNode.getNodeType() == Node.ELEMENT_NODE){
                Element sElem = (Element) sNode;
                String sQuestion = sElem.getAttribute("question");
                String sAnswer = sElem.getAttribute("answer");
                e.addQuestion(new ShortAnswer(sQuestion,sAnswer));
            }
        }
        
        // Traverse True/False questions
        NodeList tList = doc.getElementsByTagName("TrueFalse");
        for(int i = 0; i < tList.getLength() ; i ++) {
            Node tNode = tList.item(i);
            
            // If our current node is an element, traverse
            if(tNode.getNodeType() == Node.ELEMENT_NODE){
                Element tElem = (Element) tNode;
                String tQuestion = tElem.getAttribute("question");
                String tAnswer = tElem.getAttribute("answer");
                e.addQuestion(new TrueFalse(tQuestion,tAnswer));
            }
        }
        // Return the exam we created from our XML file
        return e;
    }
        
    
    public static Exam demo() {
        // Create MCQ questions
        MultipleChoice q1 = new MultipleChoice("What colour is the sky? "
                + "A: Blue  B: Red  C: Green  D: Black",  "A");
        MultipleChoice q2 = new MultipleChoice("What shape is an apple? "
                + "A: Round  B: Long  C: Square  D: Cardioid", "D");
        // Create Short Answer questions
        ShortAnswer q3 = new ShortAnswer("Is a cucumber a fruit or vegetable?", 
                "FRUIT");
        ShortAnswer q4 = new ShortAnswer("What colour are plants?", 
                "GREEN");
        // Create True or False Questions
        TrueFalse q5 = new TrueFalse("True or false: Are bananas yellow?", "TRUE");
        TrueFalse q6 = new TrueFalse("True or false: Are bananas green?", "TRUE");
        
        // Create new exam and add questions to the exam
        Exam demoExam = new Exam();
        demoExam.addQuestion(q1);
        demoExam.addQuestion(q2);
        demoExam.addQuestion(q3);
        demoExam.addQuestion(q4);
        demoExam.addQuestion(q5);
        demoExam.addQuestion(q6);

        return demoExam;
    }
    public static void takeExam (Exam E) {
        
        // Initialise the user's score and the scanner to take inputs
        int Score = 0;
        Scanner input = new Scanner(System.in);
        
        for(int i =0 ; i < E.getQuestions().size(); i++) {
            // Print the question and its question number
            int qNum = i+1;
            System.out.println("Q" + String.valueOf(qNum) + ": " + E.getQuestions().get(i).printQuestion());
            
        // If it's a multiple choice question, check for inputs A,B,C or D  
        if(E.getQuestions().get(i) instanceof MultipleChoice) {
            String userInput = input.nextLine();
            while (!Pattern.matches("[a-dA-D]", userInput )) {
                System.out.println("Please type an input of either A, B, C or D for this question.");
                userInput = input.nextLine();
            }

            // If the answer is the same as the set answer for that question, add to the score
            if (toUpperCase(userInput).equals(E.getQuestions().get(i).getAnswer())) {
            Score += 1;                      
        } 
        }
        // If it's a short answer question, check for exact answer directly
        if(E.getQuestions().get(i) instanceof ShortAnswer) {
            //If the answer is the same as the set answer for that question, add to the score
            String userInput = input.nextLine();
            if (Pattern.matches(toUpperCase(userInput),E.getQuestions().get(i).getAnswer())) {
            Score += 1;                      
        }     
        }
        
        // If it's a True/False question, check if the input is true or false first
        if(E.getQuestions().get(i) instanceof TrueFalse) {
            String userInput = input.nextLine();
            while (!"TRUE".equals(toUpperCase(userInput)) && !"FALSE".equals(toUpperCase(userInput))) {
                System.out.println("Please type an input of either True or False for this question.");
                userInput = input.nextLine();
            }

            // If the answer is the same as the set answer for that question, add to the score
            if (toUpperCase(userInput).equals(E.getQuestions().get(i).getAnswer())) {
            Score += 1;                      
        } 
        }
        
        }
        System.out.println("Your score is: " + Score + "/" + E.getQuestions().size());
        System.out.println("The right answers are:");
        for(int i =0 ; i < E.getQuestions().size(); i++) {
            int qNum = i+1;
            System.out.println("Q" + String.valueOf(qNum) + ": " + E.getQuestions().get(i).printQuestion() 
                                + "\nAnswer: " + E.getQuestions().get(i).getAnswer() );
        }
    }
}
