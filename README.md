"# OpenDoorApp" 

Open Door App v1.0

Built using Android Studio 3.5.2

This application is check-in application designed for the "Open Door" company situated in Camerose, AB.
The Open Door App v1.0 will provide users who come to their facility will go through a simple check in process to gather the appropriate information, and send it to the appropriate staff memeber.

Current Features:
- Requested check-in process by client
- Is capable of sending email's to specific employees depending on service or staff member selected
- Has a timer that will take the application back to the home page after 60 sec
- Emotion check page will be used depending on service or staff member selected

Known Bugs:
- User has to go through the check-in process or wait for a 60 sec timer to finish in order to return to the main page.
- No feedback to users when it goes back to Main Activity.
- Unable to add/remove services and/or workers list manually from the application
- Rapid tapping on buttons that control movement from between activities can cause opening of the same page multiple times
- If the app stays for a long period of time not being used, it needs restart to send email, (SMTP connection lost somehow).
- 

Possible Updates to application:
- Implementation of feature that allow client to update or remove services and employee list from the admin page of the                application. This can be done through Google Excel that is converted through JSON. The set-up for this is already             implemented on the branch serviceExpansion. Further work needs to be done on the design component.
- Proper inactivity timer for activities
- Feature to determine whether staff member is currently in office

Developers:
Alvee Akash: Computing Scientist from University of Alberta.
Role: User Interface, Email system, Overall Project Management.
Contact: Feel free to contact any time, alvee@ualberta.ca

Arnold Gihozo: Worked on file system (getting Excel data into JSON) and Service Activity
Contact:

Alex Taylor: Worked on Admin activity and Emotion Activity
Contact: arno.mugabo@gmail.com OR gihozo@ualberta.ca

Benjamin Wilson: Worked on data being transmitted from one activity to another. Focused also into getting a service to 
                 send information at a specific email.
Contact:
