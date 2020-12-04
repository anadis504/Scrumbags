Feature: User can search a podcast with different parameters

    Scenario: user can find existing podcast by name
        Given command search podcast is selected
        When  existing podcast "Urheilucast" is selected
        Then  bookmark details "https://soundcloud.com/urheilucast" are shown in terminal

    Scenario: user can't find non-existing podcast by name
        Given command search podcast is selected
        When  nonexisting podcast "Maukkauden makeasampo" is selected
        Then  search has no results

    Scenario: user can see all podcasts as list
        Given command list is selected
        When  list podcasts is selected
        Then  bookmark details "Urheilucast" are shown in terminal
        And   bookmark details "Kokkicast" are shown in terminal