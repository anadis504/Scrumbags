Feature: User can search a book by isbn

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
