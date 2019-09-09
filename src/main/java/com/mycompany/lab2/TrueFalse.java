/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

/**
 *
 * @author elainewijaya
 */
public class TrueFalse extends Question {
    private final String question;
    private final String answer;
    
    // For each True/False question, we have one question and one answer
    TrueFalse (String q, String a) {
        this.question = q;
        this.answer = a;
    }
    
    @Override
    public String printQuestion () {
        String truefalse = question;
        return truefalse;
    }
    
    @Override
    public String getAnswer () {
        return this.answer;
    }
    
    @Override
    public String writeXML() {
        String indent = "        ";
        String result = indent + "<TrueFalse " + "question = \"" + this.question +  "\" \n " + indent + "answer = \"" 
                + this.answer +"\">" + "\n " + indent + "</TrueFalse>\n";
                        
        return result;
    }
    
}
