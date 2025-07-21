/**
This script handles the admin dashboard functionality for managing doctors:
- Loads all doctor cards
- Filters doctors by name, time, or specialty
- Adds a new doctor via modal form
*/

import { openModal } from "./components/modals";
import { getDoctors, filterDoctors, saveDoctor } from "./services/doctorServices.js";
import { createDoctorCard } from "./components/doctorCard.js";

// Attach a click listener to the "Add Doctor" button
document.getElementById("addDocBtn").addEventListener("click", () => {
    // When clicked, it opens a modal form using openModal('addDoctor')
    openModal('addDoctor');
});

// When the DOM is fully loaded:
//    - Call loadDoctorCards() to fetch and display all doctors
window.addEventListener("DOMContentLoaded", () => {
    loadDoctorCards();
});

/** @Function loadDoctorCards
 @Purpose Fetch all doctors and display them as cards,
 Call getDoctors() from the service layer,
 Clear the current content area
 For each doctor returned:
 - Create a doctor card using createDoctorCard()
 - Append it to the content div

 Handle any fetch errors by logging them
 @returns {Promise<void>}
*/
async function loadDoctorCards() {
    try {
        // Fetch all doctors from the service
        const doctors = await getDoctors();

        // Clear the content area before rendering new cards
        const contentDiv = document.getElementById("content");
        contentDiv.innerHTML = "";

        // Render each doctor card
        doctors.forEach(doctor => {
            const doctorCard = createDoctorCard(doctor);
            contentDiv.appendChild(doctorCard);
        });

        // // Using traditional for loop
        // for (let i = 0; i < doctors.length; i++) {
        //     const doctorCard = createDoctorCard(doctors[i]);
        //     contentDiv.appendChild(doctorCard);
        // }
    } catch (error) {
        console.error("Error loading doctor cards:", error);
    }
}

// Attach 'input' and 'change' event listeners to the search bar and filter dropdowns
// On any input change, call filterDoctorsOnChange()
document.getElementById("searchBar").addEventListener("input", filterDoctorsOnChange);
document.getElementById("filterTime").addEventListener("change", filterDoctorsOnChange);
document.getElementById("filterSpecialty").addEventListener("change", filterDoctorsOnChange);

/** @Function filterDoctorsOnChange
* @Purpose: Filter doctors based on name, available time, and specialty.
* Read values from the search bar and filters, Normalize empty values to null
* Call filterDoctors(name, time, specialty) from the service
*
* If doctors are found:
* - Render them using createDoctorCard()
* If no doctors match the filter:
* - Show a message: "No doctors found with the given filters."
*Catch and display any errors with an alert
*/
async function filterDoctorsOnChange() {
    try {
        // Get values from the search bar and filters
        const name = document.getElementById("searchBar").value.trim() || null;
        const time = document.getElementById("timeFilter").value || null;
        const specialty = document.getElementById("specialtyFilter").value || null;

        // Fetch filtered doctors from the service
        const {doctors} = await filterDoctors(name, time, specialty);

        // Clear the content area before rendering new cards
        const contentArea = document.getElementById("content");
        contentArea.innerHTML = "";

        // If no doctors found, show a message
        if (doctors.length === 0) {
            contentArea.innerHTML = "<p>No doctors found with the given filters.</p>";
            return;
        }

        // Render each doctor card
        doctors.forEach(doctor => {
            const doctorCard = createDoctorCard(doctor);
            contentArea.appendChild(doctorCard);
        });

    } catch (error) {
        console.error("Error filtering doctors:", error);
        alert("An error occurred while filtering doctors. Please try again later.");
    }
}

/** @Function renderDoctorCards
 @Purpose A helper function to render a list of doctors passed to it

 Clear the content area.
 Loop through the doctors and append each card to the content area.
*/
function renderDoctorCards(doctors) {
    const contentArea = document.getElementById("content");
    contentArea.innerHTML = ""; // Clear the content area

    doctors.forEach(doctor => {
        const doctorCard = createDoctorCard(doctor);
        contentArea.appendChild(doctorCard);
    });
}


/**
* @Function adminAddDoctor
* @Purpose Collect form data and add a new doctor to the system
*
*   Collect input values from the modal form
*   - Includes name, email, phone, password, specialty, and available times
*
*   Retrieve the authentication token from localStorage
*   - If no token is found, show an alert and stop execution
*
*   Build a doctor object with the form values
*
*  Call saveDoctor(doctor, token) from the service
*
*  If save is successful:
*   - Show a success message
*   - Close the modal and reload the page
*
*  If saving fails, show an error message
*/
async function adminAddDoctor() {
    // Collect form data from the modal
    const name = document.getElementById("doctorName").value.trim();
    const email = document.getElementById("doctorEmail").value.trim();
    const phone = document.getElementById("doctorPhone").value.trim();
    const password = document.getElementById("doctorPassword").value.trim();
    const specialty = document.getElementById("doctorSpecialty").value.trim();
    const availableTimes = Array.from(document.querySelectorAll("#availableTimes input:checked"))
        .map(input => input.value);

    // Validate required fields
    if (!name || !email || !phone || !password || !specialty || availableTimes.length === 0) {
        alert("Please fill in all fields and select at least one available time.");
        return;
    }

    // Get the authentication token from localStorage
    const token = localStorage.getItem("token");
    if (!token) {
        alert("You must be logged in to add a doctor.");
        return;
    }

    // Build the doctor object
    const doctor = {
        name,
        email,
        phone,
        password,
        specialty,
        availableTimes
    };

    // Call the service to save the doctor
    const result = await saveDoctor(doctor, token);
    if (result.success) {
        alert(result.message); // Show success message
        closeModal('addDoctor'); // Close the modal
        loadDoctorCards(); // Reload the doctor cards
    } else {
        alert(result.message); // Show error message
    }
}

// Attach the adminAddDoctor function to the "Save Doctor" button in the modal
document.getElementById("saveDoctorBtn").addEventListener("click", adminAddDoctor);







