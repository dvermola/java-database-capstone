## Admin User Stories

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

### A.3 - admin create users of other types
**Title:**
_As an admin, I want to be able to create users of other types, so that I can manage all user accounts from the admin portal._

**Acceptance Criteria:**
1. Admin can access a user creation form
2. Admin can select user type (patient, doctor, admin)
3. System validates required fields based on user type
4. System confirms successful user creation

**Priority:** High

**Story Points:** 4

**Notes:**
- Include role-based permission assignment

---

### A.4 - admin modify users of other types
**Title:**
_As an admin, I want to be able to modify users of other types, so that I can maintain accurate user information across the platform._

**Acceptance Criteria:**
1. Admin can access a list of all users in the system
2. Admin can select a user to modify regardless of user type
3. Admin can edit user details including personal information and role-specific data
4. System validates modified fields before saving changes
5. System confirms successful user modification

**Priority:** High

**Story Points:** 5

**Notes:**
- Include audit logging of all user modifications

---

### A.5 - admin delete users of other types
**Title:**
_As an admin, I want to be able to delete users of other types, so that I can maintain system data integrity and remove inactive accounts._

**Acceptance Criteria:**
1. Admin can access a list of all users in the system
2. Admin can select a user to delete regardless of user type
3. System requires confirmation before deletion
4. System confirms successful user deletion

**Priority:** Medium

**Story Points:** 3

**Notes:**
- Implement soft delete to maintain data integrity

---


### A.6 - admin manage doctor specialties and departments
**Title:**
_As an admin, I want to be able to manage doctor specialties and departments, so that patients can find appropriate medical care._

**Acceptance Criteria:**
1. Admin can view a list of all specialties and departments
2. Admin can add new specialties and departments
3. Admin can edit existing specialties and departments
4. Admin can assign doctors to specialties and departments

**Priority:** Medium

**Story Points:** 4

**Notes:**
- Include validation to prevent duplicate entries
- Ensure changes are reflected immediately in doctor search filters

---


### A.7 - admin stored procedure for statistics
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
- Include visual charts for better data interpretation
- Allow filtering by doctor, specialty, or date range

---
---


## Patient User Stories

### P.1 - patient register for account
**Title:**
_As a patient, I want to be able to register for an account, so that I can access the patient portal and book appointments._

**Acceptance Criteria:**
1. Patient can access a registration page from the homepage
2. Patient can enter personal information and create login credentials
3. System validates all required fields
4. System confirms successful registration and sends verification email

**Priority:** High

**Story Points:** 5

**Notes:**
- Store passwords securely using encryption

---

### P.2 - patient book appointments
**Title:**
_As a patient, I want to be able to book appointments with doctors, so that I can receive medical care when needed._

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

### P.3 - patient view upcoming appointments
**Title:**
_As a patient, I want to be able to view my upcoming appointments, so that I can manage my schedule and prepare for visits._

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


### P.4 - patient cancel or reschedule appointments
**Title:**
_As a patient, I want to be able to cancel or reschedule my appointments, so that I can manage changes in my availability._

**Acceptance Criteria:**
1. Patient can select an upcoming appointment to modify
2. Patient can choose to cancel or reschedule the appointment
3. For rescheduling, patient can view and select new available time slots
4. System confirms the cancellation or rescheduling and updates all parties

**Priority:** Medium

**Story Points:** 5

**Notes:**
- Enforce cancellation policy (e.g., 24-hour notice required)

---

### P.5 - patient update personal information
**Title:**
_As a patient, I want to be able to update my personal information, so that my medical records remain current and accurate._

**Acceptance Criteria:**
1. Patient can access a profile settings page from dashboard
2. Patient can edit contact information, address, and insurance details
3. System validates all modified fields before saving
4. System confirms successful update of information

**Priority:** Medium

**Story Points:** 3

**Notes:**
- Include audit trail of information changes

---


## Doctor User Stories

### D.1 - doctor view scheduled appointments
**Title:**
_As a doctor, I want to be able to view my scheduled appointments, so that I can prepare for patient visits and manage my time effectively._

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

### D.2 - doctor manage availability
**Title:**
_As a doctor, I want to be able to manage my availability, so that patients can only book appointments during my working hours._

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

### D.3 - doctor access patient medical records
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

### D.4 - doctor add notes to patient records
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

---

### D.5 - doctor prescribe medications
**Title:**
_As a doctor, I want to be able to prescribe medications to patients, so that I can provide appropriate treatment for their conditions._

**Acceptance Criteria:**
1. Doctor can search a medication database by name or category
2. Doctor can specify dosage, frequency, and duration for prescriptions
3. System checks for potential drug interactions with patient's current medications
4. System generates a prescription that can be sent to pharmacy or printed

**Priority:** High

**Story Points:** 6

**Notes:**
- Include common prescription templates
- Implement alerts for allergies and contraindications

---

### D.6 - doctor view appointment history
**Title:**
_As a doctor, I want to be able to view my appointment history, so that I can review past patient interactions and track my work._

**Acceptance Criteria:**
1. Doctor can access a searchable history of all past appointments
2. Doctor can filter appointment history by date range, patient name, or condition
3. Doctor can view detailed information for each past appointment including notes
4. System provides summary statistics of appointments by type or diagnosis

**Priority:** Medium

**Story Points:** 4

**Notes:**
- Include export functionality for reporting purposes

---
