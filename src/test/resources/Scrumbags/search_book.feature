Feature: User can search a book with different parameters

    Scenario: user can find existing book by isbn
        Given command search is selected
	And command search book by isbn is selected
        When  a valid isbn "9781593935412" is entered
        Then  bookmark details "Bonanza" are shown in terminal

    Scenario: user can't find non-existing book by isbn
        Given command search is selected
	And command search book by isbn is selected
        When  an invalid isbn "666666666" is entered
        Then  search has no results

    Scenario: user can find existing book by year of publishing
        Given command search is selected
	And command search book by year is selected
        When  a valid year "2015" is entered
        Then  bookmark details "Bonanza" are shown in terminal

    Scenario: user can't find book with a year-parameter that does not exists
        Given command search is selected
	And command search book by year is selected
        When  a non-existing year "1000" is entered
        Then  search has no results

    Scenario: user can find existing book by author name
        Given command search is selected
	And command search book by author is selected
        When  a valid author "David R Greenland" is entered
        Then  bookmark details "Bonanza" are shown in terminal

    Scenario: user can't find non-existing book by author
        Given command search is selected
	And command search book by author is selected
        When  an invalid author "Wrong Aut" is entered
        Then  search has no results

    Scenario: user can find existing book by name
        Given command search is selected
	And command search book by name is selected
        When  a valid name "Bonanza" is entered
        Then  bookmark details "Bonanza" are shown in terminal

    Scenario: user can't find non-existing book by author
        Given command search is selected
	And command search book by name is selected
        When  an invalid name "Wrong Name" is entered
        Then  search has no results