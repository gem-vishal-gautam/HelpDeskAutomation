@regression
Feature: Ticket Creation Automation

  Background: HelpDesk, Follow Login Functionality
    Given User launches the HelpDesk
    When User enters Email vishal.gautam@geminisolutions.com and Password R2VtaW5pQDEyMw==
    And User clicks on signIn Button
    And User switches to Employee View


  Scenario Outline: HelpDesk, Verify Ticket Creation - Incident Type
    Given User clicks on New Ticket Button
    When User Creates an Incident Ticket, fills the Subject - <Subject>, Description - <Description>, and Department - <Department>
    Then User verifies the Incident Ticket with Subject - <Subject>, Description - <Description>, and Department - <Department>
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User clicks on the Ticket ID and verify Ticket Details Page
#    Then User verify the Incident Ticket with <Subject>, <Description>, and <Department> from the details page

    Examples:
      | Subject     | Description                | Department |
      | New IT      | New Description - IT       | IT         |
      | NewAccounts | New Description - Accounts | Accounts   |
      | NewHR       | New Description - HR       | HR         |
      | NewAdmin    | New Description - Admin    | Admin      |


  Scenario Outline: HelpDesk, Verify Ticket Creation - Incident Type with File
    Given User clicks on New Ticket Button
    When User Creates an Incident Ticket, fills the Subject - <Subject>, Description - <Description>, and Department - <Department>
    And User uploads the File - <uploadFile>
    Then User verifies the Incident Ticket with Subject - <Subject>, Description - <Description>, and Department - <Department>
    Then User verifies the file upload
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User clicks on the Ticket ID and verify Ticket Details Page
#    Then User verify the Incident Ticket with <Subject>, <Description>, and <Department> from the details page
    Then User verify the File Upload in details

    Examples:
      | Subject     | Description                | Department | uploadFile                                                 |
      | NewIT       | New Description - IT       | IT         | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc |
      | NewAccounts | New Description - Accounts | Accounts   | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc |
      | NewHR       | New Description - HR       | HR         | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc |
      | NewAdmin    | New Description - Admin    | Admin      | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc |


  Scenario Outline: HelpDesk, Verify Ticket Creation - Request Type
    Given User clicks on New Ticket Button
    When User Creates a Request Ticket, fills the Subject - <Subject>, Description - <Description>, Department - <Department>, Category - <Category>, and Sub-category - <Sub-category>
    Then User verifies the Request Ticket with Subject - <Subject>, Description - <Description>, Department - <Department>, Category - <Category>, and Sub-category - <Sub-category>
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User clicks on the Ticket ID and verify Ticket Details Page
#    Then User verify the Request Ticket with <Subject>, <Description>, <Department>, <Category>, and <Sub-category> from the details page

    Examples:
      | Subject | Description | Department | Category         | Sub-category                        |
      | API     | Rest APIs   | IT         | sample req type  | sub type 1                          |
      | API     | Rest APIs   | IT         | sample req type  | sub type 2                          |
      | API     | Rest APIs   | IT         | Access Request   | Creation Of DL/o365 group           |
      | API     | Rest APIs   | IT         | Access Request   | Modification of DL/o365 group       |
      | API     | Rest APIs   | IT         | Access Request   | Modification of security group      |
      | API     | Rest APIs   | IT         | Hardware         | Docking station                     |
      | API     | Rest APIs   | IT         | Hardware         | Keyboard                            |
      | API     | Rest APIs   | IT         | Hardware         | Monitor                             |
      | API     | Rest APIs   | IT         | Hardware         | Mouse                               |
      | API     | Rest APIs   | IT         | Hardware         | RAM (Over standard 16 GB allotment) |
      | API     | Rest APIs   | IT         | Hardware         | Temp. Laptop                        |
      | API     | Rest APIs   | IT         | Hardware         | Other Devices                       |
      | API     | Rest APIs   | IT         | Software         | Software License                    |
      | API     | Rest APIs   | IT         | Software         | Unlicensed/OpenSource               |
      | API     | Rest APIs   | Accounts   | Travel           | Travel cards                        |
      | API     | Rest APIs   | Accounts   | Travel           | Travel insurance                    |
      | API     | Rest APIs   | HR         | Leave Management | Compensatory Off                    |
      | API     | Rest APIs   | HR         | Leave Management | LNSA                                |
      | API     | Rest APIs   | HR         | Leave Management | Missed Leaves                       |
      | API     | Rest APIs   | Admin      | sample req type  | sub type 3                          |
      | API     | Rest APIs   | Admin      | sample req type  | sub type 4                          |
      | API     | Rest APIs   | Admin      | Facility         | Courier requirements                |
      | API     | Rest APIs   | Admin      | Facility         | Lunch in office                     |
      | API     | Rest APIs   | Admin      | Facility         | Chair                               |
      | API     | Rest APIs   | Admin      | Hardware         | Mobile Devices                      |
      | API     | Rest APIs   | Admin      | Registration     | Access card or Face scanner         |
#      | API     | Rest APIs   | Admin      | Travel           | -                                   |


  Scenario Outline: HelpDesk, Verify Ticket Creation - Request Type with File
    Given User clicks on New Ticket Button
    When User Creates a Request Ticket, fills the Subject - <Subject>, Description - <Description>, Department - <Department>, Category - <Category>, and Sub-category - <Sub-category>
    And User uploads the File - <uploadFile>
    Then User verifies the Request Ticket with Subject - <Subject>, Description - <Description>, Department - <Department>, Category - <Category>, and Sub-category - <Sub-category>
    Then User verifies the file upload
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User clicks on the Ticket ID and verify Ticket Details Page
#    Then User verify the Request Ticket with <Subject>, <Description>, <Department>, <Category>, and <Sub-category> from the details page
    Then User verify the File Upload in details

    Examples:
      | Subject | Description | Department | Category         | Sub-category                        | uploadFile                                          |
      | API     | Rest APIs   | IT         | sample req type  | sub type 1                          | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | sample req type  | sub type 2                          | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Access Request   | Creation Of DL/o365 group           | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Access Request   | Modification of DL/o365 group       | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Access Request   | Modification of security group      | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | Docking station                     | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | Keyboard                            | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | Monitor                             | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | Mouse                               | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | RAM (Over standard 16 GB allotment) | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | Temp. Laptop                        | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Hardware         | Other Devices                       | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Software         | Software License                    | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | IT         | Software         | Unlicensed/OpenSource               | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Accounts   | Travel           | Travel cards                        | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Accounts   | Travel           | Travel insurance                    | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | HR         | Leave Management | Compensatory Off                    | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | HR         | Leave Management | LNSA                                | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | HR         | Leave Management | Missed Leaves                       | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | sample req type  | sub type 3                          | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | sample req type  | sub type 4                          | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | Facility         | Courier requirements                | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | Facility         | Lunch in office                     | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | Facility         | Chair                               | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | Hardware         | Mobile Devices                      | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |
      | API     | Rest APIs   | Admin      | Registration     | Access card or Face scanner         | \\src\\test\\resources\\file-example_PDF_500_kB.pdf |

  Scenario: HelpDesk, Verify Ticket Creation without filling mandatory fields
    Given User clicks on New Ticket Button
    And User clicks on Submit button
    Then User verifies that the Ticket Creation Form has not been submitted


  Scenario: HelpDesk, Verify if User is able to add Comments on the Ticket
    Given User searches random INC-000003972
    Then User verifies searched INC-000003972 is present
    Then User clicks on the Ticket ID and verify Ticket Details Page
    Then User adds a Comment and verifies it


  Scenario: HelpDesk, Verify the timeline from ticket details page
    Given User clicks on New Ticket Button
    When User Creates an Incident Ticket, fills the Subject - New Subject, Description - New Description, and Department - IT
    Then User verifies the Incident Ticket with Subject - New Subject, Description - New Description, and Department - IT
    And User clicks on Submit button
    And User clicks on Continue button
    And The user obtains the newly created Ticked ID and searches it
    Then User clicks on the Ticket ID and verify Ticket Details Page
    Then User verify ticket created on details with Timeline details


  Scenario Outline: HelpDesk, Verify Upload File with Valid Extensions
    Given User clicks on New Ticket Button
    And User uploads the File - <file>
    Then User verifies the file upload with valid <extensions>

    Examples:
      | file                                                             | extensions |
      | \\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc       | doc        |
      | \\src\\test\\resources\\sampleFiles\\file-sample_150kB.pdf       | pdf        |
      | \\src\\test\\resources\\sampleFiles\\file_example_CSV_5000.csv   | csv        |
      | \\src\\test\\resources\\sampleFiles\\file_example_GIF_500kB.gif  | gif        |
      | \\src\\test\\resources\\sampleFiles\\file_example_JPG_100kB.jpg  | jpg        |
      | \\src\\test\\resources\\sampleFiles\\file_example_PNG_500kB.png  | png        |
      | \\src\\test\\resources\\sampleFiles\\file_example_PPT_250kB.ppt  | ppt        |
      | \\src\\test\\resources\\sampleFiles\\file_example_XLSX_1000.xlsx | xlsx       |


  Scenario Outline: HelpDesk, Verify that File is not uploaded with Invalid Extensions
    Given User clicks on New Ticket Button
    And User uploads the File - <file>
    Then User verifies that file is not uploaded with Invalid <extensions>

    Examples:
      | file                                                            | extensions |
      | \\src\\test\\resources\\sampleFiles\\file_example_MP3_700KB.mp3 | mp3        |
#      | \\src\\test\\resources\\sampleFiles\\file-sample_150kB.df | df         |
#      | \\src\\test\\resources\\sampleFiles\\file_example_CSV_5000.sv   | sv         |
#      | \\src\\test\\resources\\sampleFiles\\file_example_GIF_500kB.if  | if         |
#      | \\src\\test\\resources\\sampleFiles\\file_example_JPG_100kB.pg  | pg         |
#      | \\src\\test\\resources\\sampleFiles\\file_example_PNG_500kB.ng  | ng         |
#      | \\src\\test\\resources\\sampleFiles\\file_example_PPT_250kB.pt  | pt         |
#      | \\src\\test\\resources\\sampleFiles\\file_example_XLSX_1000.lsx | lsx        |

  Scenario Outline: HelpDesk, Verify if user is able to upload files with invalid size
    Given User clicks on New Ticket Button
    And User upload the invalid size <file> and verifies <invalidSizeFile> is not uploaded

    Examples:
      | file                                                                                   | invalidSizeFile |
      | \\src\\test\\resources\\sampleFiles\\invalid size files\\file_example_GIF_3500kB.gif   | 3.5MB           |
      | \\src\\test\\resources\\sampleFiles\\invalid size files\\file_example_JPG_2500kB.jpg   | 2.5MB           |
      | \\src\\test\\resources\\sampleFiles\\invalid size files\\file_example_PNG_3MB.png      | 3MB             |
      | \\src\\test\\resources\\sampleFiles\\invalid size files\\Free_Test_Data_10.5MB_PDF.pdf | 10.5MB          |


  Scenario: HelpDesk, Verify is user is able to upload multiple files
    Given User clicks on New Ticket Button
    And User upload the multiple \\src\\test\\resources\\sampleFiles\\file-sample_150kB.pdf,\\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc
    Then User verify the multiple uploaded \\src\\test\\resources\\sampleFiles\\file-sample_150kB.pdf,\\src\\test\\resources\\sampleFiles\\file-sample_100kB.doc
