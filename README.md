# History-Service
Service for taking care of historical location data.


### RabbitMQ Consumer

A consumer listens to a RabbitMQ queue(Configured via application config) and saves data in `location_history`
db table.

### Api

A `GET` api: `api/v1/history` returns saved locations. Api params:

    userID: String,
    startDate: Format yyyy-MM-dd HH:mm:ss
    endDate: Format yyyy-MM-dd HH:mm:ss
 
`RLS-Referrer` Header must be present.