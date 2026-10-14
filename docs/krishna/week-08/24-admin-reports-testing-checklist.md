# Admin Reports Testing Checklist — Week 8

## 1. Purpose

This checklist is for verifying the Reports and Analytics module after the relevant functionality has been implemented.

A test must be marked Passed only after it has actually been executed.

## 2. Access Control

- [ ] Confirm that an authorized administrator can access the reports section.
- [ ] Confirm that an unauthorized user cannot access protected administrative reports.
- [ ] Confirm that report pages handle expired sessions appropriately.

## 3. Report Data

- [ ] Confirm that student report totals match the test database.
- [ ] Confirm that company report totals match the test database.
- [ ] Confirm that job and internship counts match the underlying records.
- [ ] Confirm that application totals match the underlying application records.
- [ ] Confirm that placement counts use the defined placement criteria.
- [ ] Confirm that duplicate records are not counted incorrectly.

## 4. Filters

- [ ] Confirm that valid date ranges return the expected records.
- [ ] Confirm that the start date cannot be later than the end date.
- [ ] Confirm that academic-year filters work if implemented.
- [ ] Confirm that clearing filters restores the expected default report.
- [ ] Confirm that the report identifies the date field used for filtering.

## 5. Empty and Invalid Data

- [ ] Confirm that reports display a suitable message when no records match.
- [ ] Confirm that missing values do not cause the report to fail.
- [ ] Confirm that invalid filter input is handled safely.
- [ ] Confirm that percentage calculations handle a zero denominator.
- [ ] Confirm that unsupported metrics are not presented as verified values.

## 6. Display and Export

- [ ] Confirm that report headings and totals are readable.
- [ ] Confirm that displayed values are consistent with the selected filters.
- [ ] Confirm that report layouts remain usable at the supported screen sizes.
- [ ] Test report export only if an export feature has been implemented.
- [ ] Confirm that exported values match the displayed report, if export is supported.

## 7. Error Handling

- [ ] Confirm that database errors are handled appropriately.
- [ ] Confirm that users do not see sensitive internal error details.
- [ ] Confirm that refreshing the report does not create duplicate records or side effects.

## 8. Test Results

Record the actual result for each executed test:

| Test ID | Result | Notes |
|---|---|---|
| RPT-01 | Not tested | Admin access |
| RPT-02 | Not tested | Student totals |
| RPT-03 | Not tested | Company totals |
| RPT-04 | Not tested | Posting totals |
| RPT-05 | Not tested | Application totals |
| RPT-06 | Not tested | Placement metrics |
| RPT-07 | Not tested | Date filters |
| RPT-08 | Not tested | Empty data handling |
| RPT-09 | Not tested | Export, if supported |

## 9. Completion Criteria

The reporting module can be considered verified when:

1. Its implemented metrics have documented calculation rules.
2. The displayed values match known test data.
3. Access control and filter validation work correctly.
4. Empty data and error scenarios are handled.
5. Executed test results are recorded accurately.

## 10. Current Status

Status: Test checklist prepared. All tests remain Not tested until the functionality has been implemented and each test has been executed.

## Commit Message

`test: add admin reports testing checklist`
