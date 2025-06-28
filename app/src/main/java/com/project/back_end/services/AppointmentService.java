package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class AppointmentService {

// 2. **Constructor Injection for Dependencies**:
//    - The `AppointmentService` class requires several dependencies like `AppointmentRepository`, `Service`, `TokenService`, `PatientRepository`, and `DoctorRepository`.
//    - These dependencies should be injected through the constructor.
//    - Instruction: Ensure constructor injection is used for proper dependency management in Spring.
    public AppointmentService() {
    }

    public Map<String, Object> getAppointment(String patientName, LocalDate date, String token) {
        //TODO: implement
        return null;
    }

// 3. **Add @Transactional Annotation for Methods that Modify Database**:
//    - The methods that modify or update the database should be annotated with `@Transactional` to ensure atomicity and consistency of the operations.
//    - Instruction: Add the `@Transactional` annotation above methods that interact with the database, especially those modifying data.

    /***
     * Responsible for saving the new appointment to the database.
     * - Instruction: Ensure that the method handles any exceptions and returns an appropriate result code.
     * @param appointment
     * @return If the save operation fails, it returns `0`; otherwise, it returns `1`.
     */
    public int bookAppointment(@Valid Appointment appointment) {
        //TODO: implement
        return 0;
    }

    /***
     * This method is used to update an existing appointment based on its ID.
     *  - It validates whether the patient ID matches, checks if the appointment is available for updating, and ensures that the doctor is available at the specified time.
     *  - If the update is successful, it saves the appointment; otherwise, it returns an appropriate error message.
     *  - Instruction: Ensure proper validation and error handling is included for appointment updates.
     * @param appointment
     * @return
     */
    public ResponseEntity<Map<String, String>> updateAppointment(@Valid Appointment appointment) {
        //TODO: implement
        return null;
    }

    /***
     * - This method cancels an appointment by deleting it from the database.
     * - It ensures the patient who owns the appointment is trying to cancel it and handles possible errors.
     * - Instruction: Make sure that the method checks for the patient ID match before deleting the appointment.
     * @param id
     * @param token
     * @return
     */
    public ResponseEntity<Map<String, String>> cancelAppointment(Long id, String token) {
        //TODO: implement
        return null;
    }

// 5. **Update Appointment Method**:

// 7. **Get Appointments Method**:
//    - This method retrieves a list of appointments for a specific doctor on a particular day, optionally filtered by the patient's name.
//    - It uses `@Transactional` to ensure that database operations are consistent and handled in a single transaction.
//    - Instruction: Ensure the correct use of transaction boundaries, especially when querying the database for appointments.

// 8. **Change Status Method**:
//    - This method updates the status of an appointment by changing its value in the database.
//    - It should be annotated with `@Transactional` to ensure the operation is executed in a single transaction.
//    - Instruction: Add `@Transactional` before this method to ensure atomicity when updating appointment status.


}
