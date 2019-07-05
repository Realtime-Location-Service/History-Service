# History-Service
Service for taking care of historical location data.


### RabbitMQ Consumer

A consumer listens to a RabbitMQ queue(Configured via application config) and saves data in `location_history`
db table.

### Api

A `GET` api: `api/v1/history` returns saved locations. Api params:

    user_id: String,
    start_date: Epoch time
    end_date: Epoch time
 
`RLS-Referrer` Header must be present.