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

// We declare 
public abstract class Question implements XMLizable {
    // Dummy function to let us print child class questions
    public abstract String printQuestion();
    public abstract String getAnswer();
}
