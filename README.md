ServeManager API Client
=======================

An implementation of the [Serve Manager](https://www.servemanager.com) [API](https://www.servemanager.com/api) in Java.

This client is a third-party implementation and is not supported by ServeManager.

Requirements
------------

servemanager-client requires Java 8+

Although this project is open source, ServeManager is a commercial product and you will need a valid API key to use this client.  [Contact ServeManager](https://docs.google.com/forms/d/1UVVbn1a_FU3YsHdFv8sRES9jomyW_ZAYMeYL3Jxo498/viewform?usp=send_form) for more information.

Installing
----------

```xml
<dependency>
    <groupId>com.greenfiling.smclient</groupId>
    <artifactId>servemanager-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

Logging
-------

The library logs via [SLF4J](http://www.slf4j.org/).  What logging actually takes place is up to your project's actual logging configuration.

License
-------

servemanager-client is copyright 2021 Green Filing, LLC and is licensed under the Apache License 2.0

To Do
-----

The javadoc needs to be filled out.

Most of the current unit tests are the sort that require a human eyeball to see if they worked or not.  Real test cases would be good.

Contributing
------------

Tickets or pull requests welcome at the standard Github locations.

Contact
-------

This library was written by [Green Filing, LLC](https://www.greenfiling.com).  A human can be contacted at <john+smclient@greenfiling.com>.

Usage Details
-------------

This implementation follows the [API documentation](https://www.servemanager.com/api) very closely.  Each documented API endpoint (Account, Employees, Jobs, etc) has a dedicated class (AccountClient, EmployeeClient, JobClient), and each class implements the documented methods (show, index, update, or create).  With a few exceptions, these methods are the only way to interact with the API.

#### Instantiating

There are two steps to being able to interact with the API.  The first is getting an ApiHandle.  This contains non-endpoint-specific information for communicating with the API, including API key, URL, and timeouts.  The only element required to instantiate is the API key.  ApiHandle uses a builder pattern:

```java
ApiHandle apiHandle = new ApiHandle.Builder()
                          .apiKey(VALID_API_KEY)
                          .build();
```

Once an ApiHandle is created, it can be used to create the individual enpoint clients:

```java
JobClient client = new JobClient(apiHandle);
```

While more than one ApiHandle can be created, typically only one is needed for an application.  Similarly, there's not really any reason to create more than one client endoint object per application.

#### Data Model

The data model hews strongly to the documentation.  Each endpoint has a dedicated read object matching the read and index documentation (eg `Job` for `JobClient`), and come have a submit object.  There are also support objects that do not match endpoint definitions but are needed to create the object in Java.  For instance, there is a LineItem class.

There are two container classes  used to wrap the endpoint object, `Show<>` and `Index<>`.  `Show<>` is the wrapper for a single object, accessible via `.getData()`.  `Index<>` is the wrapper for a list of objects, as returned by the `index()` interface, and `.getData()` returns an array of objects.

#### Reading (show)

```java
JobClient jobClient = new JobClient(apiHandle);
Show<Job> resp = jobClient.show(123);

System.out.println("Job Number: " + resp.getData().getServeManagerJobNumber());
System.out.println("Documents for download: ");
for (Document d : resp.getData().getDocuments()) {
  System.out.println(" - " + d.getPdfDownloadUrl());
}
```

#### Listing (index)

```java
JobClient jobClient = new JobClient(apiHandle);
Index<Job> resp = jobClient.index();

System.out.println("Number of jobs in response: " + resp.getData().size());
System.out.println("ServeManager Job Numbers: ");
for (Job j : resp.getData()) {
  System.out.println(" - " + j.getServeManagerJobNumber());
}
```

##### Pagination

For clients that support the `index()` method, there is also a way to handle pagination relatively easily.  `getNext(Index<>)` takes either the response to the initial `index()` request or the last `getNext()` call.  Any filtering is done in the initial `index()` request and does not need to be specified to subsequent `getNext()` calls.  `getNext()` will return a new `Index<>` object representing the next page of results or `null` if there are no more results.

```java
JobClient jobClient = new JobClient(apiHandle);

Index<Job> resp = jobClient.index();
Integer pages = 0;
Integer total = 0;
while (resp != null) {
  pages++;
  total += resp.getData().size();
  resp = client.getNext(resp);
}
System.out.println(total + " total objects returned across " + pages + " pages");
```

#### Creating (create)

The create() and update() interfaces use a specific representation of the object to be created that is distinct from the read-version (for instance, JobSubmit instead of Job).  This object matches the layout for updating and creating detailed for each interface in the API documentation.  However, create() and update() can also accept the regular read object, which will then be converted.  It is up to the user.  The trade-off is that the "Submit" version of the objects require understanding a different format, while the read version of the objects are slightly more complex to build with all the information required.

```java
JobSubmit job = new JobSubmit();
job.setCourtCaseId(1234);
job.setRush(true);
job.setDueDate(LocalDate.parse("2021-11-15"));

JobClient jobClient = new JobClient(apiHandle);
Show<Job> createdJob = jobClient.create(job);
System.out.println("New job " + createdJob.getData().getId() + " visible at " + createdJob.getData().getLinks().getSelf());
```

#### Updating (update)

See create section above for additional background.  Only fields that you want to be changed should be populated.

```java
JobSubmit job = new JobSubmit();
job.setRush(true);

JobClient jobClient = new JobClient(apiHandle);
Show<Job> createdJob = jobClient.update(1234, job);
System.out.println("Job " + createdJob.getData().getId() + " updated, new rush = " + createdJob.getData().getRush());
```

#### Uploading Files

create() and update() can allow you to upload a file.  See the relevant [API section](https://www.servemanager.com/api/#overview-uploads) for how this works at a low level.  This client gives a convenient way to upload a fail to ServeManager's temporary URL without needing to know many specifics.

```java
JobSubmit newJob = new JobSubmit();
job.setCourtCaseId(1234);
job.setRush(true);
job.setDueDate(LocalDate.parse("2021-11-15"));

ArrayList<ServiceDocument> docs = new ArrayList<ServiceDocument>();
ServiceDocument doc = new ServiceDocument();
doc.setTitle("Subpoena");
doc.setReceivedAt(OffsetDateTime.now());
doc.setFileName("file_name.pdf");
docs.add(doc);
job.setDocumentsToBeServedAttributes(docs);

JobClient jobClient = new JobClient(apiHandle);
Show<Job> resp = client.create(job);

// Perform the actual file upload to the temporary URL provided by the API
ServiceDocument docObj = resp.getData().getDocumentsToBeServed().get(0);
jobClient.completeUpload(docObj.getUpload(), "application/pdf", new File("/path/to/file_name.pdf"));
```

#### Filtering

Only a few of the interfaces support filtering options on the index() calls (Company, CourtCase, and Job).  Each of these interfaces has a dedicated filter object that defined the filtering information to provide to the API.  The exact details of what can go into a filter are listed in the API documentation.

```java
// Create a job filter that lists all jobs that have the test foobar in them and were created after October 1, 2021
JobFilter jobFilter = new JobFilter();
jobFilter.setDateRange(new FilterDateRange());
jobFilter.getDateRange().setType(FilterDateRange.TYPE_CREATED_AT);
jobFilter.getDateRange().setMin(OffsetDateTime.parse("2021-10-01T00:00:00-00:00"));
jobFilter.setQ("foobar");

JobClient jobClient = new JobClient(apiHandle);
Index<Job> resp = jobClient.index(filter);

System.out.println("Matching jobs:");
while (resp != null) {
  for (Job j : resp.getData()) {
    System.out.println(" - JobId: " + j.getId() + ", URL = " + j.getLinks().getSelf());
  }
  resp = jobClient.getNext(resp);
}
```

#### Constants

The API documentation lists many string constants that can be passed to the API as valid values (listed under "Enumerated Attributes" under each endpoint that has them).  Some of these are complete, meaning that using any other value will result in an error, but others define a list of standard values but say that custom values are allowed.  While a Java `enum` would work for the former, it would not work for the latter.  For simplicity, string constants are provided internally for each value defined in the API documentation.  Each set method which could accept one of these values have javadoc which defines the available constants.  While these constants do not have to be used, their use will make maintaining client code easier.

As an example, under Jobs the `data['recipient']['gender']` values are set to "", "Male", "Female", and "Other".  The javadoc for Recipient.setGender(), the constants GENDER_BLANK, GENDER_MALE, GENDER_FEMALE, and GENDER_OTHER are listed.

Example of how this would be set in live code:

```java
 JobSubmit newJob = new JobSubmit();
 Recipient recipient = new Recipient();
 recipient.setGender(Recipient.GENDER_MALE);
 newJob.setRecipientAttributes(recipient);
```

#### Downloading Files

Every client class offers a `getFile()` method for downloading arbitrary files.  This action has nothing to do with the API, but is provided as a convenience since it is useful for downloading documents referenced by the API.

```java
JobClient client = new JobClient(apiHandle);
Show<Job> resp = client.show(8624870);

ServiceDocument doc = resp.getData().getDocumentsToBeServed().get(0);
String localPath = System.getProperty("java.io.tmpdir") + "/" + doc.getUpload().getFileName();
String url = doc.getUpload().getLinks().getDownloadUrl();

client.getFile(url, localPath);
System.out.println("Downloaded from " + url);
System.out.println("Downloaded to " + localPath);
```

#### Error Handling

The error handling in this client is all Exception based.  While the examples above were all written without it for the sake of clarity, all calls into the client should be wrapper in `try` blocks, and all values returned by the client should be checked for null values before attempting to dereference.

#### Client Implementation vs. API Documentation Differences

The design of the client hews very close to the API documentation, with a few exceptions

- API field `attempts.servemanager_job_number` is represented in the client as `Attempt.serveManagerJobNumber` (note that Manager is capitalized, which is closer to what Java programmers would expect).
- API field `courts.default` is represented in the client as `Court.isDefault` to work around `default` being a Java reserved word.
- API field `jobs.servemanager_job_number` is represented in the client as `Job.serveManagerJobNumber` (note that Manager is capitalized, which is closer to what Java programmers would expect).
- API field `jobs.affidavit_count` is represented in the client as `Job.documentsCount` to match the `jobs.documents` field to which it refers.
- API field `jobs.document_to_be_served_count` is represented in the client as `Job.documentsToBeServedCount`, pluralizing "documents" to match that of the `jobs.documents_to_be_served` field to which it refers.
- API field `jobs.attempt_count` is represented in the client as `Job.attemptsCount`, pluralizing "attempts" to match that of the `jobs.attempts` field to which it refers.
