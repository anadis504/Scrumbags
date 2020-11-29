Feature: User can create new bookmark

    Scenario: user can create new bookmark for book
        Given command add book is selected
        When  valid book name "Story of Scrumbags", writer name "Test Writer". ISBN "123-45-67890-12-3", number of pages "123" and publication year "2020" are entered and input is confirmed
        Then  new bookmark for a book is created

    Scenario: user can't add two books with the same name
        Given command add book is selected
        When two books with the same name "Bonanza" are entered
        Then the book is not added because name is taken

    Scenario: user can't add two books with the same isbn
        Given command add book is selected
        When two books with the same isbn "9781593935412" are entered
        Then the book is not added because name is taken

    Scenario: user can create new bookmark for link
        Given command add link is selected
        When  valid link name "Ohtu2020" and url "https://ohjelmistotuotanto-hy.github.io/" are entered and input is confirmed
        Then  new bookmark for a link is created
