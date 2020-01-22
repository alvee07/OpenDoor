"# OpenDoorApp" 

OpenDoorApp v1.0

Built using Android Studio v3.5.2

This application is check-in application designed for the "Open Door" company situated in Camerose, AB.
The Open Door App v1.0 will provide users who come to their facility a simple check in process to gather the appropriate information, and send it to the appropriate staff memeber. No user information wil be stored in this application to help provide a sense of privacy for users who go through the check-in process. The only data that the application will have access to are public emails of staff memebers that will be used to contact them when appropriate.

Current Features:
- Requested check-in process by client
- Is capable of sending email's to specific employees depending on service or staff member selected
- Has a timer that will take the application back to the home page after 60 sec when in the middle of the check-in process
- Emotion check page will be used depending on service or staff member selected

Known Bugs:
- User has to go through the check-in process or wait for a 60 sec timer to finish in order to return to the main page.
- No feedback to users when it goes back to Main Activity.
- Unable to add/remove services and/or workers list manually from the application
- Rapid tapping on buttons that control movement from between activities can cause opening of the same page multiple times
- If the app stays for a long period of time not being used, it needs restart to send email, (SMTP connection lost somehow).

Possible Updates to application:
- Implementation of feature that allow client to update or remove services and employee list from the admin page of the                application. This can be done through Google Excel that is converted through JSON. The set-up for this is already             implemented on the branch serviceExpansionNew. Further work needs to be done on the design component.
- Proper inactivity timer for activities
- Feature to determine whether staff member is currently in office

Developers:<br/>
Alvee Akash: Computing Science student from the University of Alberta.<br/>
Role: User Interface, Email system, Overall Project Management.<br/>
Contact: Feel free to contact any time, alvee@ualberta.ca

Arnold Gihozo: Computing Science Stdudent from the University of Alberta<br/>
Role: Team Lead. Worked on file system (getting Excel data into JSON) and Service Activity<br/>
Contact: arno.mugabo@gmail.com OR gihozo@ualberta.ca

Alex Taylor: Computing Science Student from  the University of Alberta<br/>
Role: Main Page, Admin activity, Emotion Activity, and design formatting<br/>
Contact: awtaylor@ualberta.ca

Benjamin Wilson: Computing Science Studetn from the University of Alberta<br/>
Role: Worked on data being transmitted from one activity to another. Focused also into getting a service to send information at a specific email.<br/>
Contact: bwilson1@ualberta.ca
