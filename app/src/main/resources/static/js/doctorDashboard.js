// Import getAllAppointments to fetch appointments from the backend
import { getAllAppointments } from './services/appointmentRecordService.js';

// Import createPatientRow to generate a table row for each patient appointment
import { createPatientRow } from './components/patientRows.js';

// Get the table body where patient rows will be added
const tableBody = document.getElementById("patientTableBody");

// Initialize selectedDate with today's date in 'YYYY-MM-DD' format
let selectedDate = new Date().toISOString().split('T')[0];

// Get the saved token from localStorage (used for authenticated API calls)
const token = localStorage.getItem("token");

// Initialize patientName to null (used for filtering by name)
let patientName = null;


// Add an 'input' event listener to the search bar
document.getElementById("searchBar").addEventListener("input", function() {
    // Get the trimmed value from the search bar
    const inputValue = this.value.trim();

    // If input is not empty, set patientName to the input value
    if (inputValue) {
        patientName = inputValue;
    } else {
        // If input is empty, reset patientName to null
        patientName = null;
    }

    // Reload appointments with the updated filter
    loadAppointments(selectedDate, patientName, token);
});


// Add a click listener to the "Today" button
document.getElementById("todayButton").addEventListener("click", () => {
    // Set selectedDate to today's date
    const today = new Date();
    selectedDate = today.toISOString().split('T')[0];

    // Update the date picker UI to match today's date
    document.getElementById("datePicker").value = selectedDate;

    // Reload the appointments for today
    loadAppointments(selectedDate, patientName, token);
});


// Add a change event listener to the date picker
document.getElementById("datePicker").addEventListener("change", function() {
    // Update selectedDate with the new value from the date picker
    selectedDate = this.value;

    // Reload appointments for the newly selected date
    loadAppointments(selectedDate, patientName, token);
});


/** @Function to load appointments based on selected date and patient name
* @param {string} selectedDate - The date for which to load appointments in 'YYYY-MM-DD' format
* @param {string|null} patientName - The name of the patient to filter appointments by, or null for no filter
* @param {string} token - The authentication token for API requests
* Fetches appointments using getAllAppointments
*   Clears the table body before rendering new rows,
*   If no appointments are found, displays a message row,
*   If appointments exist, creates a patient object for each appointment,
*   * Calls createPatientRow to generate a table row for each appointment,
*   Catches and handles any errors during the fetch,
*   Displays an error message row if fetching appointments fails
* @returns {Promise<void>} - Resolves when appointments are loaded and rendered
*/
async function loadAppointments(selectedDate, patientName, token) {
    try {
        // Step 1: Call getAllAppointments with selectedDate, patientName, and token
        const appointments = await getAllAppointments(selectedDate, patientName, token);

        // Step 2: Clear the table body content before rendering new rows
        tableBody.innerHTML = "";

        // Step 3: If no appointments are returned:
        //     - Display a message row: "No Appointments found for today."
        if (appointments.length === 0) {
            const messageRow = document.createElement("tr");
            messageRow.innerHTML = `<td colspan="4" class="text-center">No Appointments found for today.</td>`;
            tableBody.appendChild(messageRow);
            return;
        }

        // Step 4: If appointments exist:
        // - Loop through each appointment and construct a 'patient' object with id, name, phone, and email
        appointments.forEach(appointment => {
            const patient = {
                id: appointment.patient.id,
                name: appointment.patient.name,
                phone: appointment.patient.phone,
                email: appointment.patient.email
            };

            // Call createPatientRow to generate a table row for the appointment
            const row = createPatientRow(patient, appointment);

            // Append each row to the table body
            tableBody.appendChild(row);
        });
    // Step 5: If an error occurs during the fetch:
    } catch (error) {
        console.error("Error loading appointments:", error);

        // Show an error message row if fetching appointments fails
        const errorRow = document.createElement("tr");

        // Show a message row: "Error loading appointments. Try again later."
        errorRow.innerHTML = `<td colspan="4" class="text-center">Error loading appointments. Try again later.</td>`;
        tableBody.appendChild(errorRow);
    }
}

// Initial load of appointments for today with no patient name filter
document.onDOMContentLoaded = function() {
    loadAppointments(selectedDate, patientName, token);
}
