# sports-service #

Simple scalatra app to handle live sports event sent from PA.

## Build & Run ##

```sh
$ cd sports-service
$ sbt
> jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

## Ideas

```mermaid
sequenceDiagram
    participant notif as PA Notification Service
    participant sportsService as sports-service
    participant paSportsAPI as PA Sports API
    participant store as Store
    participant subscriber
    notif->>sportsService: sends sports event
    sportsService->>notif: returns 200 OK
    sportsService->>sportsService: parse/transform event
    sportsService->>paSportsAPI: request live data
    paSportsAPI->>sportsService: live data
    sportsService->>sportsService: parse/transform live data
    note over sportsService,store: Not sure yet about how consumers will get the live info.
    note over sportsService,store: Continue to request consumers to poll? Or push live data out to subscribers?
    sportsService->>store: persist live data? (for consumers, e.g. frontend/mapi, to consume via polling)
    sportsService->>subscriber: push live data to all subscribers?
            
```
