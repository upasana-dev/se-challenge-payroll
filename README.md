# Wave Software Development Challenge

Applicants for the Full-stack Developer role at Wave must
complete the following challenge, and submit a solution prior to the onsite
interview.

The purpose of this exercise is to create something that we can work on
together during the onsite. We do this so that you get a chance to collaborate
with Wavers during the interview in a situation where you know something better
than us (it's your code, after all!)

There isn't a hard deadline for this exercise; take as long as you need to
complete it. However, in terms of total time spent actively working on the
challenge, we ask that you not spend more than a few hours, as we value your
time and are happy to leave things open to discussion in the on-site interview.

Please use whatever programming language and framework you feel the most
comfortable with.

Feel free to email [dev.careers@waveapps.com](dev.careers@waveapps.com) if you
have any questions.

## Project Description

Imagine that this is the early days of Wave's history, and that we are prototyping a new payroll system API. A front end (that hasn't been developed yet, but will likely be a single page application) is going to use our API to achieve two goals:

1. Upload a CSV file containing data on the number of hours worked per day per employee
1. Retrieve a report detailing how much each employee should be paid in each _pay period_

All employees are paid by the hour (there are no salaried employees.) Employees belong to one of two _job groups_ which determine their wages; job group A is paid $20/hr, and job group B is paid $30/hr. Each employee is identified by a string called an "employee id" that is globally unique in our system.

Hours are tracked per employee, per day in comma-separated value files (CSV).
Each individual CSV file is known as a "time report", and will contain:

1. A header, denoting the columns in the sheet (`date`, `hours worked`,
   `employee id`, `job group`)
1. 0 or more data rows

In addition, the file name should be of the format `time-report-x.csv`,
where `x` is the ID of the time report represented as an integer. For example, `time-report-42.csv` would represent a report with an ID of `42`.

You can assume that:

1. Columns will always be in that order.
1. There will always be data in each column and the number of hours worked will always be greater than 0.
1. There will always be a well-formed header line.
1. There will always be a well-formed file name.

A sample input file named `time-report-42.csv` is included in this repo.

### What your API must do:

We've agreed to build an API with the following endpoints to serve HTTP requests:

1. An endpoint for uploading a file.

   - This file will conform to the CSV specifications outlined in the previous section.
   - Upon upload, the timekeeping information within the file must be stored to a database for archival purposes.
   - If an attempt is made to upload a file with the same report ID as a previously uploaded file, this upload should fail with an error message indicating that this is not allowed.

2. An endpoint for retrieving a payroll report structured in the following way:

   _NOTE:_ It is not the responsibility of the API to return HTML, as we will delegate the visual layout and redering to the front end. The expectation is that this API will only return JSON data.

   - Return a JSON object `payrollReport`.
   - `payrollReport` will have a single field, `employeeReports`, containing a list of objects with fields `employeeId`, `payPeriod`, and `amountPaid`.
   - The `payPeriod` field is an object containing a date interval that is roughly biweekly. Each month has two pay periods; the _first half_ is from the 1st to the 15th inclusive, and the _second half_ is from the 16th to the end of the month, inclusive. `payPeriod` will have two fields to represent this interval: `startDate` and `endDate`.
   - Each employee should have a single object in `employeeReports` for each pay period that they have recorded hours worked. The `amountPaid` field should contain the sum of the hours worked in that pay period multiplied by the hourly rate for their job group.
   - If an employee was not paid in a specific pay period, there should not be an object in `employeeReports` for that employee + pay period combination.
   - The report should be sorted in some sensical order (e.g. sorted by employee id and then pay period start.)
   - The report should be based on all _of the data_ across _all of the uploaded time reports_, for all time.

As an example, given the upload of a sample file with the following data:

   | date       | hours worked | employee id | job group |
   | ---------- | ------------ | ----------- | --------- |
   | 2020-01-04 | 10           | 1           | A         |
   | 2020-01-14 | 5            | 1           | A         |
   | 2020-01-20 | 3            | 2           | B         |
   | 2020-01-20 | 4            | 1           | A         |

A request to the report endpoint should return the following JSON response:

   ```json
   {
     "payrollReport": {
       "employeeReports": [
         {
           "employeeId": "1",
           "payPeriod": {
             "startDate": "2020-01-01",
             "endDate": "2020-01-15"
           },
           "amountPaid": "$300.00"
         },
         {
           "employeeId": "1",
           "payPeriod": {
             "startDate": "2020-01-16",
             "endDate": "2020-01-31"
           },
           "amountPaid": "$80.00"
         },
         {
           "employeeId": "2",
           "payPeriod": {
             "startDate": "2020-01-16",
             "endDate": "2020-01-31"
           },
           "amountPaid": "$90.00"
         }
       ]
     }
   }
   ```

We consider ourselves to be language agnostic here at Wave, so feel free to use any combination of technologies you see fit to both meet the requirements and showcase your skills. We only ask that your submission:

- Is easy to set up
- Can run on either a Linux or Mac OS X developer machine
- Does not require any non open-source software

### Documentation:

Please commit the following to this `README.md`:

1. Instructions on how to build/run your application
1. Answers to the following questions:
   - How did you test that your implementation was correct?
   - If this application was destined for a production environment, what would you add or change?
   - What compromises did you have to make as a result of the time constraints of this challenge?

## Submission Instructions

1. Clone the repository.
1. Complete your project as described above within your local repository.
1. Ensure everything you want to commit is committed.
1. Create a git bundle: `git bundle create your_name.bundle --all`
1. Email the bundle file to [dev.careers@waveapps.com](dev.careers@waveapps.com) and CC the recruiter you have been in contact with.

## Evaluation

Evaluation of your submission will be based on the following criteria.

1. Did you follow the instructions for submission?
1. Did you complete the steps outlined in the _Documentation_ section?
1. Were models/entities and other components easily identifiable to the
   reviewer?
1. What design decisions did you make when designing your models/entities? Are
   they explained?
1. Did you separate any concerns in your application? Why or why not?
1. Does your solution use appropriate data types for the problem as described?

## Solution
The application uses Maven Wrapper and can be built and run using the "mvnw" command:-

* To build : mvnw clean install
  * The unit tests are automatically run as part of this command
*  To run the application : mvnw spring-boot:run

### API Endpoints

The endpoints can be invoked using the command-line tool 'curl'

#### Import Employee Job Effort Info
This endpoint will accept a CSV file and use the information in this file to update the number of hours worked per day per employee

* Request Type : GET
* URL :  __localhost:8080/payroll/info__

##### Using curl 

###### Windows
__curl -F file=@\"<path to CSV file>\"  localhost:8080/payroll/info__

(Double quotes need to be used for Windows)

###### Other platforms
__curl -F file=@'<path to CSV file>'  localhost:8080/payroll/info__

#### Generate Payroll Report
This endpoint will trigger the generation of a payroll report

Request Type : POST
URL :  __localhost:8080/payroll/report__

Using curl : __curl localhost:8080/payroll/report__

### Answers to Documentation Questions

* The implementation was tested using a combination of unit tests and functional tests (tests by running the application and verifying the output) 
* If this application was destined for production, the following would have been added/considered:-
	* a) Creation of DTOs to ferry information from the data to the service layer. Currently, the service layer makes use of model objects directly, using DTOs instead would ensure a clean
	separation.
	* b) Input validation. Currently, each of the layers accept data without verifying that the content is in the right form - there is no kind of validation performed on the file name before inserting it into the DB
	* c) Validating the employee effort details for duplicates to ensure that there aren't multiple entries for a single date for the same employee
	* d) More refined exception handling with custom exceptions for different scenarios to allow a more fine-tuned response
	* d) A more sophisticated logging mechanism that provides more insight into each process.
	* e) Performance-specific considerations - the payroll report uses all the data from the database. If the data set is much larger, the report generation would need to be tested and optimised to improve its performance
* If there was no time constraint, I would have liked to:-
	* Add more unit tests to test the functionality more thoroughly
	* Add DB validation as mentioned in the point above
	* Organized the packages more cleanly
	



