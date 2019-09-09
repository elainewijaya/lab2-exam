/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;
import java.util.ArrayList;
/**
 *
 * @author elainewijaya
 */
public class ShortAnswer extends Question {
    private final String question;
    private final String answer;
    
    ShortAnswer(String q, String a) {
        this.question = q;
        this.answer = a;

    }
    
    @Override
    public String printQuestion() {
        return this.question;
    }
    
    
    @Override
    public String getAnswer() {
        return this.answer;
    }
    
        
    @Override
    public String writeXML() {
        String indent = "        ";
        String result = indent + "<ShortAnswer " + "question = \"" + this.question +  "\" \n " + indent + "answer = \"" 
                + this.answer +"\">" + "\n" + indent + "</ShortAnswer>\n";
        return result;
    }
}
