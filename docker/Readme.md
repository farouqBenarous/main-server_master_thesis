
# DemoDb

. 
## Requirements
* Docker
* Docker hub

## How to run the databse  ?

 1- To build  the database image  type  *`docker build -t demo-db .`*
 
 note : the default  credentials are  `username:demo` `password :demo`  `databsename : postgres`exposed on the adress :`0.0.0.0:5432` you can change them in the docker file if you want 
 
 2- To run the database after the build type :   
 *`docker run --name DemoDb   -p 5432:5432  -d demo-db`*
 
 3-  To enter to the container after the run type :  *`docker exec -it DemoDb  bash  `*  

 
 ```sql
 
 -- login as postgresql
   
   ALTER DATABASE twentyx_demo OWNER TO postgres;
   
   SELECT
       pg_terminate_backend(pid)
   FROM
       pg_stat_activity
   WHERE
       -- don't kill my own connection!
       pid <> pg_backend_pid() 
       -- don't kill the connections to other databases
       AND datname = 'twentyx_demo'
       ;
   
   drop database twentyx_demo;
   create database twentyx_demo;
   
   -- /Applications/Postgres.app/Contents/Versions/10/bin/pg_dump --verbose --host=127.0.0.1 --port=21946 --username=postgres --format=p --compress=0 --encoding=UTF-8 --inserts --no-privileges --no-owner -n "public" TwentyX
```