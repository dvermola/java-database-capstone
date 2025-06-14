## Admin User Stories

### User Story Template

**Title:**
_As a [user role], I want [feature/goal], so that [reason]._

**Acceptance Criteria:**
1. [Criteria 1]
2. [Criteria 2]
3. [Criteria 3]

**Priority:** [High/Medium/Low]

**Story Points:** [Estimated Effort in Points]

**Notes:**
- [Additional information or edge cases]

---

### A.1 - admin login
**Title:**
_As an admin, I want to log into the portal with my username and password, so that I can manage the platform securely._

**Acceptance Criteria:**
1. Admin can access a login page
2. Admin can enter username and password
3. System validates credentials before granting access
4. Admin is able to access admin only functionality
5. Non-Admin users unable to access admin functionality

**Priority:** High

**Story Points:** 3

**Notes:**
- Implement account lockout after multiple failed attempts

---


### A.2 - admin logout
**Title:**
_As an admin, I want to log out of the portal, so that I can protect system access._

**Acceptance Criteria:**
1. Logout button is available on all admin pages
2. Clicking logout ends the session immediately
3. Admin is redirected to the login page after logout
4. Session cookies are cleared upon logout

**Priority:** High

**Story Points:** 2

**Notes:**
- Implement automatic logout after period of inactivity

---

### A.3 - Add doctors to the portal
**Title:**
_As an admin, I want to be able to Add doctors to the portal, so that I can manage doctors' accounts from the admin portal._

**Acceptance Criteria:**
1. Admin can access a doctor creation form
2. System validates required fields
3. System confirms successful user creation

**Priority:** High

**Story Points:** 3

**Notes:**
- Include role-based permission assignment

---


### A.4 - admin modify doctor's profile
**Title:**
_As an admin, I want to be able to modify doctor's profile, so that I can maintain accurate user information across the platform._

**Acceptance Criteria:**
1. Admin can access a list of doctors in the system
2. Admin can edit user details including personal information and role-specific data
3. System validates modified fields before saving changes
4. System confirms successful user modification

**Priority:** High

**Story Points:** 4

**Notes:**
- Include audit logging of all user modifications

---

### A.5 - Delete doctor's profile from the portal
**Title:**
_As an admin, I want to be able to delete doctor's profile, so that I can maintain system data integrity and remove inactive accounts._

**Acceptance Criteria:**
1. Admin can access a list of all doctors in the system
2. Admin can select a user to delete
3. System requires confirmation before deletion
4. System confirms successful user deletion

**Priority:** Medium

**Story Points:** 3

**Notes:**
- Implement soft delete to maintain data integrity

---


### A.6 - Admin can call stored procedure for statistics
**Title:**
_As an admin, I want to run a stored procedure to get the number of appointments per month, so that I can track usage statistics._

**Acceptance Criteria:**
1. Admin can access a statistics dashboard
2. Admin can execute the stored procedure from the interface
3. System displays appointment data in monthly breakdown
4. Data can be exported in common formats (CSV, PDF)

**Priority:** Medium

**Story Points:** 5

**Notes:**
- Allow filtering by doctor, specialty, or date range

---
---


## Patient User Stories

### P.1 - View a list of doctors without logging in to explore options before registering
**Title:**
_As a guest user, I want to view a list of doctors without logging in, so that I can explore options before registering._

**Acceptance Criteria:**
1. Guest users can access a page displaying a list of available doctors.
2. The list includes basic information such as doctor name, specialty, and location.
3. No registration or login is required to view the list.

**Priority:** High

**Story Points:** 3

**Notes:**
- Detailed doctor profiles and appointment booking require registration.
- The list should not display sensitive information (e.g., contact details).

---

### P.2 - Sign up using your email and password to book appointments
**Title:**
_As a patient, I want to be able to sign up for an account using my email and password, so that I can access the patient portal and book appointments._

**Acceptance Criteria:**
1. Patient can access a registration page from the homepage
2. Patient can enter personal information and create login credentials including username and password
3. System validates all required fields
4. System confirms successful registration and sends verification email

**Priority:** High

**Story Points:** 5

**Notes:**
- Store passwords securely using encryption

---


### P.3 - Patient can Log into the portal to manage appointments
**Title:**
As a patient, I want to log into the portal to manage my appointments, so that I can easily view, update, or cancel my appointments.

**Acceptance Criteria:**
1. Patient can log in using valid credentials.
2. Upon logging in, patient can view a list of their current and upcoming appointments.
3. Patient can update or cancel their appointments from within the portal.

**Priority:** High

**Story Points:** 5

**Notes:**
- Login should be secure and protect patient data.
- System should handle invalid login attempts with appropriate error messages.

---


### P.4 - Patient can logout
**Title:**
_As an Patient, I want to log out of the portal, so that I can protect my account._

**Acceptance Criteria:**
1. Logout button is available on all Patient pages
2. Clicking logout ends the session immediately
3. Patient is redirected to the login page after logout
4. Session cookies are cleared upon logout

**Priority:** High

**Story Points:** 2

**Notes:**
- Implement automatic logout after period of inactivity

---


### P.5 - Patient can book appointments
**Title:**
_As a patient, I want to be able to book appointments to consult with a doctor, so that I can receive medical care when needed._

**Acceptance Criteria:**
1. Patient can search for doctors by specialty, name, or availability
2. Patient can view doctor's available time slots
3. Patient can select preferred date and time for appointment
4. System confirms booking and sends confirmation details

**Priority:** High

**Story Points:** 6

**Notes:**
- Include option to add reason for visit
- Allow patients to see their appointment history

---


### P.6 - Patient can view upcoming appointments
**Title:**
_As a Patient, I want to be able to view my upcoming appointments, so that I can manage my schedule and prepare for visits._

**Acceptance Criteria:**
1. Patient can access a list of all upcoming appointments from dashboard
2. List displays date, time, doctor name, and location for each appointment
3. Patient can view detailed information for each appointment
4. Patient can filter or search appointments by date or doctor

**Priority:** Medium

**Story Points:** 4

**Notes:**
- Provide ordered list of appointments

---


## Doctor User Stories

### D.1 - Doctor can Log into the portal
**Title:**
As a Doctor, I want to log into the portal to manage my appointments.

**Acceptance Criteria:**
1. Doctor can log in using valid credentials.
2. Upon logging in, Doctor can view a list of their current and upcoming appointments.
3. Doctor can update or cancel their appointments from within the portal.

**Priority:** High

**Story Points:** 5

**Notes:**
- Login should be secure and protect Doctor data.
- System should handle invalid login attempts with appropriate error messages.

---


### D.2 - Doctor can logout
**Title:**
_As an Doctor, I want to log out of the portal, so that I can protect my account._

**Acceptance Criteria:**
1. Logout button is available on all Doctor pages
2. Clicking logout ends the session immediately
3. Doctor is redirected to the login page after logout
4. Session cookies are cleared upon logout

**Priority:** High

**Story Points:** 2

**Notes:**
- Implement automatic logout after period of inactivity

---


### D.3 - Doctor can view scheduled appointments
**Title:** _As a doctor, I want to be able to view my scheduled appointments, so that I can prepare for patient visits and manage my time effectively._

**Acceptance Criteria:**
1. Doctor can access a dashboard showing all upcoming appointments
2. Appointments are displayed in chronological order with patient details
3. Doctor can filter appointments by date, time, or patient name
4. Doctor can view detailed information for each appointment including patient history

**Priority:** High

**Story Points:** 4

**Notes:** 
- Include calendar and list view options

---

### D.4 - Doctor can manage availability
**Title:** _As a doctor, I want to be able to manage my availability, so that patients can only book appointments during my working hours._

**Acceptance Criteria:**
1. Doctor can access an availability management interface
2. Doctor can set regular working hours for each day of the week
3. Doctor can mark specific dates and times as unavailable
4. System updates the appointment booking system accordingly

**Priority:** High

**Story Points:** 5

**Notes:**
- Allow for vacation time blocking

---

### D.5 - doctor access patient medical records
**Title:**
_As a doctor, I want to be able to access patient medical records, so that I can provide informed and appropriate care._

**Acceptance Criteria:**
1. Doctor can search for patients by name or ID
2. Doctor can view complete medical history for patients with upcoming appointments
3. Doctor can see previous diagnoses, treatments, and medications
4. System logs all access to patient records for compliance

**Priority:** High

**Story Points:** 7

**Notes:**
- Implement role-based access controls

---

### D.6 - doctor add notes to patient records
**Title:**
_As a doctor, I want to be able to add notes to patient records after appointments, so that I can document diagnoses, treatments, and follow-up plans._

**Acceptance Criteria:**
1. Doctor can access a note entry interface from the appointment details
2. Doctor can enter clinical notes, diagnoses, and treatment plans
3. Doctor can save notes to the patient's permanent medical record
4. System timestamps and attributes all notes to the authoring doctor

**Priority:** High

**Story Points:** 5

**Notes:**
- Include templates for common note types
- Allow attachment of lab results or images
