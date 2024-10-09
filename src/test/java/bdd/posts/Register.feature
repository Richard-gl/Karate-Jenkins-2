Feature: Register Users

  @Register
  Scenario: Register Users tipo 1
    Given url 'https://reqres.in/api/register'
    * def json = read('../json/register.json')
    Given request json
    When method get
    And status 200
    * print response
    * def name = response.data[0].name
    * match name == "cerulean"


