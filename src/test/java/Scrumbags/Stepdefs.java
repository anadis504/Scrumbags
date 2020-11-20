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
import static org.junit.Assert.*;

import Scrumbags.database.*;
import Scrumbags.ui.*;
import Scrumbags.logic.*;
import java.util.ArrayList;

/**
 *
 * @author Scrumbags
 */
public class Stepdefs {
    Ui ui;
    StubIO io;
    Dao dao;
    Service service;
    List<String> input;
    
    @Before
    public void setup(){
        dao = new DatabaseFake();
        input = new ArrayList<>();
        service = new Service(dao);
    }
    
    @Given("command add book is selected")
    public void commandAddBookSelected() {
        input.add("add book");
    }
    
    @When("valid book name {string} and valid writer name {string} are entered and input is confirmed")
    public void validBooknameAndValidWriternameEntered(String book, String writer) {
        input.add(book);
        input.add(writer);
        input.add("y");
        
        io = new StubIO(input);
        ui = new Ui(io, service);
        ui.run(true);
    }
    
    @Then("new bookmark for a book is created")
    public void bookmarkForBookCreated() {
        assertTrue(io.getOutput().contains("Kirja lisätty onnistuneesti."));
    }
}
