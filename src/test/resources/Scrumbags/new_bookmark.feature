Feature: User can create new bookmark

    Scenario: user can create new bookmark for book
        Given command add book is selected
        When  valid book name "Scrumbag", writer name "Story of Scrumbags". ISBN "123-45-67890-12-3", number of pages "123" and publication year "2020" are entered and input is confirmed
        Then  new bookmark for a book is created

    Scenario: user can create new bookmark for link
        Given command add link is selected
        When  valid link name "Ohtu2020" and url "https://ohjelmistotuotanto-hy.github.io/" are entered and input is confirmed
        Then  new bookmark for a link is created
