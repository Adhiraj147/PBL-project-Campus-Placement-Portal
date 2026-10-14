# Week 11 - Deployment Testing

## 1. Objective

The objective is to verify that the application can be built,
configured, and prepared for deployment.

## 2. Test Cases

| ID | Test Case | Expected Result | Status |
|---|---|---|---|
| DT01 | Run Maven build | Build completes successfully | Not Tested |
| DT02 | Generate WAR package | WAR file is created | Not Tested |
| DT03 | Start application server | Application starts successfully | Not Tested |
| DT04 | Configure database connection | Application connects to MySQL | Not Tested |
| DT05 | Open login page | Login page loads | Not Tested |
| DT06 | Register a test account | Registration behaves correctly | Not Tested |
| DT07 | Log in with valid credentials | Correct dashboard opens | Not Tested |
| DT08 | Perform a database-dependent operation | Expected data is retrieved or saved | Not Tested |
| DT09 | Use incorrect database configuration | Error is handled safely | Not Tested |
| DT10 | Inspect repository for credentials | No active credentials are exposed | Not Tested |

## 3. Testing Procedure

1. Review the project configuration.
2. Run the Maven build.
3. Confirm that the WAR file is generated.
4. Verify the database configuration.
5. Start the application using the configured server.
6. Test the major application workflows.
7. Review server logs for errors.

## 4. Status Definitions

- PASS: The test succeeded.
- FAIL: The test did not produce the expected result.
- NOT TESTED: The test has not been performed.
- NOT APPLICABLE: The test does not apply to the current environment.

## 5. Conclusion

Deployment testing verifies that the application and its
configuration are ready for the intended hosting environment.