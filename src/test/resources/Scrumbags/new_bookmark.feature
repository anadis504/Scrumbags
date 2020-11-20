Feature: User can create new bookmark

    Scenario: user can create new bookmark for book
        Given command add book is selected
        When  valid book name "Testikirja" and valid writer name "Testi Kirjailija" is entered
        Then  new bookmark for a book is created

    Scenario user can create new bookmark for link
        Given command "add link" is selected
        When  valid link name "https://ohjelmistotuotanto-hy.github.io/" is entered
        Then new bookmark for a link is created
