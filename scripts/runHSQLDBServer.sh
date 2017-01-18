#!/bin/bash

java -cp /home/studinf/jmikucka/java/hsqldb/lib/hsqldb.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb
