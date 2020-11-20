/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scrumbags;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;

/**
 *
 * @author toniramo
 */
public class Stepdefs {
    List<String> lines;
    
    @Given("command add book is selected")
    public void commandAddBookSelected() {
        lines.add("add book");
    }
    
    @When("valid book name {string} and valid writer name {string} are entered")
    public void validBooknameAndValidWriternameEntered(String book, String writer) {
        lines.add(book);
        lines.add(writer);
        
        //run ui;
    }
    
    @Then("new bookmark for a book is created")
}
