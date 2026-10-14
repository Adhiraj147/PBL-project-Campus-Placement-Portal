# Deployment Checklist

## 1. Build Verification

- [ ] Maven configuration reviewed.
- [ ] Dependencies verified.
- [ ] Application builds successfully.
- [ ] WAR file generated.
- [ ] Build errors resolved.

## 2. Runtime Compatibility

- [ ] Java version verified.
- [ ] Tomcat version verified.
- [ ] Servlet API compatibility checked.
- [ ] Required JDBC driver available.
- [ ] Application starts successfully.

## 3. Database Preparation

- [ ] MySQL server available.
- [ ] Database created.
- [ ] Required schema initialized.
- [ ] Database user permissions verified.
- [ ] Application database connection tested.
- [ ] Database credentials kept out of public source code.

## 4. Application Verification

- [ ] Login tested.
- [ ] Registration tested.
- [ ] Student dashboard tested.
- [ ] Job listing tested.
- [ ] Application submission tested.
- [ ] Recruiter functionality tested.
- [ ] Admin functionality tested.

## 5. Security Verification

- [ ] Protected routes checked.
- [ ] Role-based access checked.
- [ ] Database credentials protected.
- [ ] Error messages reviewed.
- [ ] Public admin registration disabled.
- [ ] Sensitive configuration excluded from Git.

## 6. Deployment Verification

- [ ] Hosting requirements identified.
- [ ] Database hosting requirements identified.
- [ ] Environment variables configured.
- [ ] Application deployed to the target environment.
- [ ] Application URL verified.
- [ ] Database-dependent operations tested.

## 7. Final Status

Update each checklist item after verification.

Do not mark deployment as complete until the application
has actually been deployed and tested in the target environment.