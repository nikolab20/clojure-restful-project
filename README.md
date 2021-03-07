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
