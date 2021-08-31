# Liquibase fails to resolve files relative to directories with dot

This is a simple project to demonstrate a bug in liquibase 4.x (regression to 3.x) when a csv file is
resolved relative to a directory which contains a dot character ('.') in the last part of the path.

The same error should happen for any file resolved relative to such a directory as the error is in the class
`liquibase.resource.ClassLoaderResourceAccessor`.

It contains

- identical database change logs in 
  - `src/main/resource/db-change.log` and 
  - `src/main/resource/db-changelog`  
- an test case demonstrating that 
  - using the change log in the path **without dot** works and 
  - using the change log in the path **with a dot** does not work.

The test cas is executed against a in memory H2 database but should be present for any database.

## Run test

*Prerequisites*: Maven must be installed

Use 
```
mvn test
```
to see the test case fail with liquibase version 4.4.3.

You can test any liquibase version by providing the liquibase version as a runtime parameter. 

Use 
```
mvn test "-Dliquibase.version=3.10.3"
```
to see the test case pass with liquibase version 3.10.3.






