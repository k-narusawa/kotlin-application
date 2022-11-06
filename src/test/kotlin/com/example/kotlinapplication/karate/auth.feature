Feature: 認証

  Background:
    * url "http://127.0.0.1:8080"

  Scenario: ログインができること
    Given path '/api/login'
    And request {"loginId": "admin","password": "admin"}
    When method post
    Then status 200
    And def accessToken = response.accessToken
    And def refreshToken = response.refreshToken

  Scenario: ログインに失敗すること
    Given path '/api/login'
    And request {"loginId": "dummy","password": "dummy"}
    When method post
    Then status 401