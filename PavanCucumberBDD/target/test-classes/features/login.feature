@login
Feature: nopCommerce login
# Background: below are the common steps for each scenario
#     Given  open browser 
#     When   open nopcommerce link "https://admin-demo.nopcommerce.com"
#     Then   enter user id "admin@yourstore.com" and password "admin"
#     Then   dashboard page opens "Dashboard / nopCommerce administration"
#@sanity 

Scenario: login to nopcommece using user id and password
  Given  open browser 
  When   open nopcommerce link "https://admin-demo.nopcommerce.com"
  Then   enter user id "admin@yourstore.com" and password "admin"
  Then   dashboard page opens "Dashboard / nopCommerce administration"
  And    click logout
  And    come to home page 