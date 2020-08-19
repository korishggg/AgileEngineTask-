# AgileEngineTask
You could refresh by page data in local cache.
By this endpoint for example for 2 page of Agile engine resource
http://localhost:8080/refresh/images?page=2

##Update all data by period
Here you specify how frequently external data will synchronize with app data.
By default data will updates every hour.
```properties
scheduling.update_agile_engine_data_cron=0 0 * * * *
```

##Fetch all images from cache
You send GET request to this api
http://localhost:8080/refresh/images




