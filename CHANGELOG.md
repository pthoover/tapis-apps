# Change Log for Tapis Applications Service

All notable changes to this project will be documented in this file.

Please find documentation here:
https://tapis.readthedocs.io/en/latest/technical/apps.html

You may also reference live-docs based on the openapi specification here:
https://tapis-project.github.io/live-docs

---------------------------------------------------------------------------
## 1.7.1 - 2024-09-18

Bug fix

### New features:
- None

### Bug fixes:
- Allow user with MODIFY permission to create new app versions.

---------------------------------------------------------------------------
## 1.7.0 - 2024-09-09

New release

### New features:
- None

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.6.4 - 2024-09-04

Incorporate latest shared code.

### New features:
- None

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.6.3 - 2024-08-05

Incremental improvements.
Add log messages for when access is denied or resource not found.

### New features:
- None

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.6.2 - 2024-04-24

Incremental improvements.

### New features:
- Allow tenant admin to impersonate a Tapis user when calling getApp, getApps.
- Run service in docker container as non-root user.
- Set default log level to INFO. Allow for dynamic changing of log level.

### Bug fixes:
- Fix bug with searching on versionEnabled attribute.

---------------------------------------------------------------------------
## 1.6.1 - 2024-03-06

Incremental improvements.

### New features:
- Add DTN attributes dtnSystemInputDir, dtnSystemInputDir.
- Use shared code TapisUtils.weaklyValidateUri() for additional validation of file inputs.
- Allow attribute *enabled* for specific application versions. Allows for specific version to be disabled.
- Add envKey attribute for file inputs.

### Bug fixes:
- None.

---------------------------------------------------------------------------
## 1.6.0 - 2024-01-17

Incremental improvements.

### New features:
- Validate sourceUrl attribute for file inputs defined in an application.
- When fetching applications get shareInfo only when needed.
- Update support for ZIP runtime.
- Trim whitespace from attributes during updates.
- Check attributes for control characters. Reject create/update if found.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.5.10 - 2023-11-20

Incremental improvements and bug fix.

### New features:
- Validate containerImage for case of ZIP runtime.

### Bug fixes:
- Rebuild with latest shared code to fix JWT validation issue.

---------------------------------------------------------------------------
## 1.5.0 - 2023-10-11

New release

### New features:
- None

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.4.3 - 2023-10-02

### New features:
- Add ZIP to list of runtime types. In preparation for planned support in Jobs service.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.4.2 - 2023-09-13

Code cleanup, update java version in docker builds, new feature.

### New features:
- Add attribute *locked*. Supports locking a version of an application to prevent updates via PUT or PATCH. 

### Bug fixes:
- None

## 1.4.1 - 2023-08-09

Incremental improvements.

### New features:
- Add support for attribute *notes* for *fileInput* and *fileInputArray*.
- Add support for *logConfig* in *parameterSet*. For supporting flexible redirection of stdout and stderr. 
- Add computed attribute sharedWithUsers to be included when an application is fetched.

---------------------------------------------------------------------------
## 1.4.0 - 2023-07-07

Incremental improvements.

### New features:
- Allow id to begin with a number.

---------------------------------------------------------------------------
## 1.3.4 - 2023-06-14

Incremental improvements and new features

### Breaking changes:
- Environment variables beginning with *_tapis* may not be used in *jobAttributes.parameterSet.envVariables*. This is to match Jobs service behavior.

### New features:
- Add application shutdown hook for graceful shutdown.
- Add notes and inputMode attributes to items in JobAttributes->ParameterSet->envVariables.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.3.3 - 2023-05-11

Incremental improvements and bug fixes.

### New features:
- None

### Bug fixes:
- Add package registration of jax-rs filters to ensure query parameters are correctly set.
- Allow for sharedAppCtx to be null for GET followed by POST or PUT.
- Fix issue with setting of default value of enabled=true within a jobAttributes subscription.

---------------------------------------------------------------------------
## 1.3.2 - 2023-04-03

Fix for sharing privilege escalation.

### New features:
- Increased maximum length of cmdPrefix attribute from 1024 to 4096 characters.

### Bug fixes:
- Update sharedAppCtx to represent the share grantor. Fixes privilege escalation issue.

---------------------------------------------------------------------------
## 1.3.1 - 2023-03-22

Incremental improvements and bug fix.

### New features:
- Increased maximum length of cmdPrefix attribute from 126 to 1024 characters.

### Bug fixes:
- Fix issue with setting of defaults for maxJobs and maxJobsPerUser.

---------------------------------------------------------------------------
## 1.3.0 - 2023-02-27

Incremental improvements and official release of new preview features since 1.2.0.

### New features:
- See 1.2.X release notes.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.2.5 - 2023-01-10

Incremental improvements, new preview features and bug fixes.

### New features:
- Add attribute returned for getSystem: *isPublic*.
- Check authorization and validate execSystemId and archiveSystemId at application creation time.
- Add support for new listType values: SHARED_DIRECT, READ_PERM, MINE

### Bug fixes:
- Fix bug in setting of sharedAppCtx for getApp.

---------------------------------------------------------------------------
## 1.2.4 - 2022-11-14

Incremental improvements and new preview features.

### New features:
- Add support for searching by *tags* attribute using operator *contains*.
- Add support for query parameter *listType* when retrieving systems. Allows for filtering based on authorization.
    * Options are OWNED, SHARED_PUBLIC, ALL. Default is OWNED.
- Improved error message when attempting to search using an unsupported attribute
- Use ForbiddenException (403) as appropriate.

---------------------------------------------------------------------------
## 1.2.3 - 2022-10-15

Incremental improvements and new preview features.

### New features:
- Add attribute *notes* to *ArgSpec*
- Add attribute *description* to *envVariables* in *ParameterSet*.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.2.2 - 2022-10-10

Incremental improvements and bug fixes.

### Bug fixes:
- Fix issues with handling of authorization checks for service requests.

---------------------------------------------------------------------------
## 1.2.1 - 2022-09-01

Incremental improvements and new features.

### New features:
- New endpoints for application sharing. getApp returns sharedAppCtx attribute.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.2.0 - 2022-05-23

Incremental improvements and new features.

### New features:
- Support impersonationId for service to service requests.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.1.3 - 2022-05-07

Incremental improvements and preview of new features.

### New features:
- Refactor authorization checks for maintainability.
- Replace skipTapisAuthorization with impersonationId for requests from Jobs service.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.1.2 - 2022-04-13

Preview of new features.

### New features:
- Additional information for Apps history.
- Support skipTapisAuthorization for requests from Jobs service.

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.1.1 - 2022-03-03

Incremental improvements and bug fixes.

### New features:
- Update readyCheck to check for expired service JWT.
- Updates for JDK 17

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.1.0 - 2022-01-07

New minor release.

### New features:
- None

### Bug fixes:
- None

---------------------------------------------------------------------------
## 1.0.2 - 2021-12-16

Incremental improvements and bug fixes.

### New features:
- Add isMpi, mpiCmd and cmdPrefix attributes to jobAttributes.

### Bug fixes:
- Allow nulls for additional attributes in order to support GET + PUT.

---------------------------------------------------------------------------
## 1.0.1 - 2021-11-19

Incremental improvements and bug fixes.

### New features:
- Support multiple orderBy
- Re-design FileInput, ParameterSet arguments (app arguments, etc).
- Add FileInputArray
- Rename appType to jobType and make it an optional versioned attribute.

### Bug fixes:
- Fix problems with setting of defaults for FileInputs and ParameterSet arguments.

---------------------------------------------------------------------------
## 1.0.0 - 2021-07-16

Initial release supporting basic CRUD operations on Tapis Application resources.

### Breaking Changes:
- Initial release.

### New features:
 - Initial release.

### Bug fixes:
- None.
