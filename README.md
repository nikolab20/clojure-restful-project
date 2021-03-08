# Car Service App

# Tools used in this Clojure project

- [Schema](https://github.com/plumatic/schema) - library for declarative data description and validation.
- [Compojure-api](https://github.com/metosin/compojure-api) - a web api library for Clojure.
- [Ring](https://github.com/ring-clojure/ring) - a Ring adapter that uses the Jetty webserver.
- [Toucan](https://github.com/metabase/toucan) - high-level Clojure library for defining application models and retrieving them from a DB.
- [buddy-hashers](https://funcool.github.io/buddy-hashers/latest/) - a collection of secure password hashers for Clojure.
- [clj_time](https://github.com/clj-time/clj-time) - a date and time library for Clojure.

# About project

The project was developed as part of the assignment for the course Software Engineering Tools and Methodology on Master's studies - Software Engineering and Computer Sciences at the Faculty of Organization Sciences, University of Belgrade, Serbia.

The Clojure Car Service App represent the application for keeping records of invoices, invoice items and sold car parts or provided services to clients. Every employee can create new invoice for client and add item to that invoice. Each invoice item refers to a specific one object of sale that relates to an car part or service. Each invoice item has price and price with tax that relates to specific one tax rate which employee selects when entering a new invoice item.ž

# Launch the application

Navigate to root folder of project and then install dependecies.

```sh
lein deps
``` 

After that, start application with the following command.

```sh
lein run
``` 

To test functionality of the application, enter the following link in your web browser.

```sh
http://localhost:3000/swagger
```

This will open the browser window.

![alt text](https://github.com/nikolab20/Clojure-Car-Service-App/blob/main/backend/images/Swagger.png)

# Application testing

In Clojure application a unit-testing framework included in its clojure.test namespace. To run tests, enter the following command.

```sh
lein test
``` 

As a result of testing, the following was obtained

![alt text](https://github.com/nikolab20/Clojure-Car-Service-App/blob/main/backend/images/Testing.png)

# References

1. https://github.com/clojure-cookbook/clojure-cookbook
2. https://oli.me.uk/clojure-projects-from-scratch/
3. https://gist.github.com/daveray/1441520#file-seesaw-repl-tutorial-clj-L33
4. https://github.com/demystifyfp/BlogSamples/tree/master/clojure/restful-crud
