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
        input.add("1");
    }
    
    @When("valid book name {string}, writer name {string}. ISBN {string}, number of pages {string} and publication year {string} are entered and input is confirmed")
    public void validBookAttributesEnteredAndConfirmed(String book, String writer, String isbn, String pages, String year) {
        input.add(book);
        input.add(writer);
        input.add(isbn);
        input.add(pages);
        input.add(year);
        input.add("y");
        input.add("q");
        
        io = new StubIO(input);
        ui = new Ui(io, service);
        ui.run(true);
    }
    
    @Then("new bookmark for a book is created")
    public void bookmarkForBookCreated() {
        assertTrue(io.getOutput().contains("Kirja lisätty onnistuneesti."));
    }
    
    @Given("command add link is selected")
    public void commandAddLinkSelected() {
        input.add("2");
    }
    
    @When("valid link name {string} and url {string} are entered and input is confirmed")
    public void validLinkAttributesEnteredAndInputConfirmed(String name, String url) {
        input.add(name);
        input.add(url);
        input.add("y");
        input.add("q");
        
        io = new StubIO(input);
        ui = new Ui(io, service);
        ui.run(true);
    }
    
    @Then("new bookmark for a link is created")
    public void bookmarkForLinkCreated() {
        assertTrue(io.getOutput().contains("Linkki lisätty onnistuneesti."));
    }
    
    
    
}
