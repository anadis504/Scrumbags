/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scrumbags;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;

import Scrumbags.database.*;
import Scrumbags.ui.*;
import Scrumbags.logic.*;

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
    String testDatabaseName = "test.db";

    @Before
    public void setup() throws ClassNotFoundException {
        dao = new Database("jdbc:sqlite:" + testDatabaseName);
        input = new ArrayList<>();
        service = new Service(dao);
        Book bonanza = new Book("Bonanza", "David R Greenland", "9781593935412", 170, 2015);
        Book lofoten = new Book("Lofoten Rock", "Chris Craggs, Thorbjørn Enevold","9781873341667", 319, 2008);
        Podcast urheilucast = new Podcast("Urheilucast", "Esko Seppänen", "https://soundcloud.com/urheilucast", "---");
        Podcast kokkicast = new Podcast("Kokkicast", "---", "https://soundcloud.com/kokkicast", "---");
        Link google = new Link("Google", "http://www.google.com");
        Link yle = new Link("YLE", "http://www.yle.fi");
        dao.addBook(bonanza);
        dao.addBook(lofoten);
        dao.addPodcast(urheilucast);
        dao.addPodcast(kokkicast);
        dao.addLink(google);
        dao.addLink(yle);
    }

    @After
    public void tearDown() {
        try {
            File db = new File(testDatabaseName);
            db.delete();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Given("command search is selected")
    public void commandSearchIsSelected() {
        input.add("4");
    }
    
    @Given("command list is selected")
    public void commandListIsSelected() {
        input.add("5");
    }
   

    @Given("command search book by isbn is selected")
    public void commandSearchBookByIsbnIsSelected() {
        input.add("1");
        input.add("4");
    }

    @When("a valid isbn {string} is entered")
    public void validIsbnIsEntered(String isbn) {
        input.add(isbn);
    }

    @When("user chooses not to remove any of the found bookmarks")
    public void foundBookmarksNotRemoved() {
        input.add("e");
        runUi();
    }

    @Then("bookmark details {string} are shown in terminal")
    public void bookmarkDetailsAreShown(String string) {
        boolean found = false;
        for (String line : io.getOutput()) {
            if (line.contains(string)) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @When("an invalid isbn {string} is entered")
    public void inValidIsbnIsEntered(String isbn) {
        input.add(isbn);
        runUi();
    }

    @Then("search has no results")
    public void searchHasNoResults() {
        assertTrue(io.getOutput().contains("Ei tuloksia."));
    }

    @Given("command search book by year is selected")
    public void commandSearchBookByYearIsSelected() {
        input.add("1");
        input.add("3");
    }

    @When("a non-existing year {string} is entered")
    public void nonExistingYearIsEntered(String year) {
        input.add(year);

        runUi();
    }

    @When("a valid year {string} is entered")
    public void validYearIsEntered(String year) {
        input.add(year);
    }

    @Given("command search book by author is selected")
    public void commandSearchBookByAuthorIsSelected() {
        input.add("1");
        input.add("1");
    }

    @When("a valid author {string} is entered")
    public void validAuthorIsEntered(String author) {
        input.add(author);
    }

    @When("an invalid author {string} is entered")
    public void invalidAuthorIsEntered(String author) {
        input.add(author);

        runUi();
    }

    @Given("command search book by name is selected")
    public void commandSearchBookByNameIsSelected() {
        input.add("1");
        input.add("2");
    }

    @When("a valid name {string} is entered")
    public void validNameIsEntered(String name) {
        input.add(name);
    }

    @When("an invalid name {string} is entered")
    public void invalidNameIsEntered(String name) {
        input.add(name);

        runUi();
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
        input.add("k");

        runUi();
    }

    @Then("new bookmark for a book is created")
    public void bookmarkForBookCreated() {
        assertTrue(io.getOutput().contains("Kirja lisätty onnistuneesti."));
    }

    @When("two books with the same name {string} are entered")
    public void twoBooksWithTheSameNameAreEntered(String name) {
        input.add("Bonanza");
        input.add("q");
        input.add("q");
        input.add("q");
        input.add("q");
        input.add("k");

        runUi();
    }

    @Then("the book is not added because name is taken")
    public void theBookIsNotAddedBecauseNameIsTaken() {
        assertTrue(io.getOutput().contains("Kirjaa ei onnistuttu lisäämään."));
    }

    @When("two books with the same isbn {string} are entered")
    public void twoBooksWithTheSameIsbnAreEntered(String isbn) {
        input.add("Bonanza2");
        input.add("q");
        input.add(isbn);
        input.add("q");
        input.add("q");
        input.add("k");

        runUi();
    }

    @Then("the book is not added because isbn is taken")
    public void theBookIsNotAddedBecauseIsbnIsTaken() {
        assertTrue(io.getOutput().contains("Kirjan lisääminen ei onnistunut."));
    }

    @Given("command add link is selected")
    public void commandAddLinkSelected() {
        input.add("2");
    }

    @When("valid link name {string} and url {string} are entered and input is confirmed")
    public void validLinkAttributesEnteredAndInputConfirmed(String name, String url) {
        input.add(name);
        input.add(url);
        input.add("k");

        runUi();
    }

    @Then("new bookmark for a link is created")
    public void bookmarkForLinkCreated() {
        assertTrue(io.getOutput().contains("Linkki lisätty onnistuneesti."));
    }

    @Given("command add podcast is selected")
    public void commandAddPodcastSelected() {
        input.add("3");
    }

    @When("valid link name {string}, publisher {string}, url {string} and rrs {string} are entered and input is confirmed")
    public void validLinkNamePublisherUrlAndRrsAreEnteredAndInputIsConfirmed(String name, String publisher, String url, String rrs) {
        input.add(name);
        input.add(publisher);
        input.add(url);
        input.add(rrs);
        input.add("k");

        runUi();
    }

    @Then("new bookmark for a podcast is created")
    public void bookmarkForPodcastCreated() {
        assertTrue(io.getOutput().contains("podcast lisätty onnistuneesti."));
    }

    @When("valid link name {string}, publisher {string}, url {string} and rrs {string} are entered twice and input is confirmed")
    public void validLinkNamePublisherUrlAndRrsAreEnteredTwiceAndInputIsConfirmed(String name, String publisher, String url, String rrs) {
        input.add(name);
        input.add(publisher);
        input.add(url);
        input.add(rrs);
        input.add("k");
        input.add("3");
        input.add(name);
        input.add(publisher);
        input.add(url);
        input.add(rrs);
        input.add("k");

        runUi();
    }

    @Then("cannot add another podcast with same name")
    public void cannotAddAnotherPodcastWithSameName() {
        assertTrue(io.getOutput().contains("Podcastin lisääminen ei onnistunut."));
    }

    @Given("command search podcast is selected")
    public void commandSearchPodcastSelected() {
        input.add("4");
        input.add("3");
    }

    @When("existing podcast {string} is selected")
    public void existingPodcastIsSelected(String name) {
        input.add(name);
        input.add("q");
        runUi();
    }

    @Then("podcast is found")
    public void podcastIsFound() {
        assertFalse(io.getOutput().contains("Ei tuloksia."));
    }

    @When("nonexisting podcast {string} is selected")
    public void nonexistingPodcastIsSelected(String name) {
        input.add(name);
        input.add("q");
        runUi();
    }
    
    @Given("command search link is selected")
    public void searchLinkIsSelected() {
        input.add("2");
    }
    
    @When("existing link {string} is entered")
    public void existingLinkIsEntered(String name) {
        input.add(name);
        input.add("q");
        runUi();
    }
    
    @When("nonexisting link {string} is entered")
    public void nonexistingLinkIsEntered(String name) {
        input.add(name);
        input.add("q");
        runUi();
    }
    
    @When("list links is selected")
    public void commandListLinksIsSelected() {
        input.add("2");
        runUi();
    }
    
    @When("list podcasts is selected")
    public void commandListPodcastsIsSelected() {
        input.add("3");
        runUi();
    }
    
    @When("list books is selected")
    public void commandListBooksIsSelected() {
        input.add("1");
        runUi();
    }
    
    @When("list all is selected")
    public void commandListAllIsSelected() {
        input.add("4");
        runUi();
    }
    
    @When("wrong parameter {string} is entered")
    public void wrongParameterIsEntered(String string) {
        input.add("string");
        runUi();
    }
    
    @Then("commandlist is shown again")
    public void commandListIsShown() {
        boolean found = false;
        for (String line : io.getOutput()) {
            if (line.contains("komennot:")) {
                found = true;
            }
        }
        assertTrue(found);
    }

    private void runUi() {
        io = new StubIO(input);
        ui = new Ui(io, service);
        ui.run(true);
    }
}