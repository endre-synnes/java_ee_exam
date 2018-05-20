# Java Enterprice Exam 48 hours

In this exam we where given the task of creating an used book store. Where students can sell or buy uesd books from each other.

Students can mark books if they have them and want so sell these books. Buyers can then click on a book and start a conversation with one of the sellers (Both buyer and seller have to be authenticated).


<!--- Travis CI build status banner -->
[![Build Status](https://travis-ci.org/synend16/java_ee_exam.svg?branch=master)](https://travis-ci.org/synend16/java_ee_exam)

### Code cuality

Code coverage:
  - I have an total of 94% code coverage.

Test:
  - Selenium Tests
  - Unit Tests
  - Integration Tests
  
  

### How to run the application
To Run my application localy run class: LocalApplicationRunner

To Run all tests type: mvn clean verify in root folder.


## Backend:

- I Have implemented functionality to delete books as well as creating new ones.

- All Users are identified by an unique email address.

- All Books have an ID but i have also created a constraint so that no books with the same title can exist at the same time.




## Frontend:

- The front end is made using JSF. 

- I have implemented spring security so that some pages require you to be logged in or even be an admin.

## Extra functionality:

- I Have added functionality for ADMIN users. A user can register as an admin and will then get access to an admin page.

- Admin users can create and delete books.

- When you shall send a message you are taken to a new page with a form so that i have one page for that and another for
  viewing all sent and received messages.

- I Have also deployed to Heroku.
