Feature: User can search a link with different parameters

    Scenario: user can find existing link by name
        Given command search is selected
        Given command search link is selected
        When  existing link "Google" is entered
        Then  bookmark details "http://www.google.com" are shown in terminal

    Scenario: user can't find non-existing link by name
        Given command search is selected
        Given command search link is selected
        When  nonexisting link "Pullaforum" is entered
        Then  search has no results

    Scenario: user can see all links as list
        Given command list is selected
        When  list links is selected
        Then  bookmark details "http://www.google.com" are shown in terminal
        And   bookmark details "http://www.yle.fi" are shown in terminal