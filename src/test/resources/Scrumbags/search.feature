Feature: User can search all bookmarks and ui is working correctly

    Scenario: user can see all bookmarks as list
        Given command list is selected
        When  list all is selected
        Then  bookmark details "http://www.google.com" are shown in terminal
        And   bookmark details "http://www.yle.fi" are shown in terminal
        And   bookmark details "Kokkicast" are shown in terminal
        And   bookmark details "Lofoten Rock" are shown in terminal

    Scenario: user can't list with wrong parameters
        Given command list is selected
        When  wrong parameter "8" is entered
        Then  commandlist is shown again

    Scenario: user can't search with wrong parameters
        Given command search is selected
        When  wrong parameter "7" is entered
        Then  commandlist is shown again