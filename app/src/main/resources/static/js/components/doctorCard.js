//Import the overlay function for booking appointments from loggedPatient.js
import { deleteDoctor } from "../services/doctorServices.js";

// Import function to fetch patient details (used during booking) from patientServices.js
import { getPatientDetails } from "../services/patientServices.js";

/**
 * Creates and returns a DOM element for a single doctor card
 * @param doctor
 * @returns {HTMLDivElement}
 */
export function createDoctorCard(doctor) {
    // Create the Main Card Container
    const card = document.createElement("div");   // Dynamically create a <div> element.
    card.classList.add("doctor-card");  // Add a CSS class doctor-card for styling purposes.

    // Fetch the User’s Role
    const role = localStorage.getItem("userRole");  // Read the current user’s role (admin, patient, loggedPatient) from localStorage.

    // Create Doctor Info Section
    const infoDiv  = document.createElement("div");  // Create a <div> to hold doctor information.
    infoDiv.classList.add("doctor-info");  // Add a CSS class doctor-info for styling.

    // Create and set the doctor’s name
    const name = document.createElement("h3");  // Create an <h3> element for the doctor's name.
    name.textContent = doctor.name;  // Set the text content to the doctor's name.

    // Create and set the doctor's specialization
    const specialization = document.createElement("p");  // Create a <p> element for specialization.
    specialization.textContent = `Specialization: ${doctor.specialization}`;  // Set the text content to the doctor's specialization.

    // Create and set the doctor's email
    const email = document.createElement("p");  // Create a <p> element for the doctor's email.
    email.textContent = `Email: ${doctor.email}`;  // Set the text content to the doctor's email.

    // Create and list available appointment times
    const availability = document.createElement("ul");  // Create an unordered list to hold available appointment times.
    doctor.availableTimes.forEach(time => {  // Iterate over each available time in the doctor's schedule.
        const timeItem = document.createElement("li");  // Create a list item for each time.
        timeItem.textContent = time;  // Set the text content to the available time.
        availability.appendChild(timeItem);  // Append the time item to the times list.
    });

    infoDiv.appendChild(name);  // Append the name element to the doctor info section.
    infoDiv.appendChild(specialization);  // Append the specialization element to the doctor info section.
    infoDiv.appendChild(email); // Append the email element to the doctor info section.
    infoDiv.appendChild(availability);  // Append the availability list to the doctor info section.

    // Create Button Container actionsDiv
    const actionsDiv = document.createElement("div");  // Create a <div> to hold action buttons.
    actionsDiv.classList.add("card-actions");  // Add a CSS class card-actions for styling.

    // === ADMIN ROLE ACTIONS ===
    if (role === "admin") {
        const removeBtn = document.createElement("button"); // Create a button for removing the doctor.
        removeBtn.textContent = "Delete"; // Set the button text to "Delete".

        // Attach an event:
        removeBtn.addEventListener("click", async () => {
            // 1. Confirm deletion
            const confirmDelete = confirm("Are you sure you want to delete this doctor?");

            // If user confirms deletion
            if (confirmDelete) {
                // 2. Get token from localStorage
                const token = localStorage.getItem("token");
                if (!token) {
                    alert("You need to be logged in as an admin to delete a doctor.");
                    return;
                }

                // 3. Call API to delete the doctor
                try {
                    const result = await deleteDoctor(doctor.id, token);
                    alert(result.message);

                    // 4. On success: remove the card from the DOM
                    if (result.success) {
                        card.remove(); // Remove the card from the DOM if deletion is successful
                    }
                } catch (error) {
                    console.error("Error deleting doctor:", error);
                    alert("Failed to delete doctor. Please try again.");
                }
            }
        });
        actionsDiv.appendChild(removeBtn); // Append the delete button to the actions container.
    // === PATIENT (NOT LOGGED-IN) ROLE ACTIONS ===
    } else if (role === "patient") {
        // Create a book now button for booking an appointment
        const bookNow = document.createElement("button");
        bookNow.textContent = "Book Now";

        // Alert patient to log in before booking
        bookNow.addEventListener("click", () => {
            alert("Patient needs to login first.");
        });
        actionsDiv.appendChild(bookNow); // Append the book now button to the actions container.
    }
    // === LOGGED-IN PATIENT ROLE ACTIONS ===
    else if (role === "loggedPatient") {
        // Create a book now button for booking an appointment
        const bookNow = document.createElement("button");
        bookNow.textContent = "Book Now";

        bookNow.addEventListener("click", async (e) => {
            const token = localStorage.getItem("token");
            // Redirect if token not available
            if (!token) {
                alert("You need to log in to book an appointment.");
                return;
            }

            // Fetch patient data with token
            const patientData = await getPatientData(token);

            // check if patientData is valid
            if (patientData && patientData.id) {
                showBookingOverlay(e, doctor, patientData);
            } else {
                console.log("PatientData is missing or invalid:", patientData);
                alert("Failed to fetch patient details. Please log in again.");
                return;
            }
        });

        // Append the book now button to the actions container
        actionsDiv.appendChild(bookNow); // Append the book now button to the actions container.
    }

    card.appendChild(infoDiv); // Append the doctor info section to the card.
    card.appendChild(actionsDiv); // Append the actions container to the card.

    return card; // Return the complete doctor card element.
}

/*
  Import the deleteDoctor API function to remove doctors (admin role) from docotrServices.js

  Import function to fetch patient details (used during booking) from patientServices.js

  Function to create and return a DOM element for a single doctor card
    Create the main container for the doctor card
    Retrieve the current user role from localStorage
    Create a div to hold doctor information
    Create and set the doctor’s name
    Create and set the doctor's specialization
    Create and set the doctor's email
    Create and list available appointment times
    Append all info elements to the doctor info container
    Create a container for card action buttons
    === ADMIN ROLE ACTIONS ===
      Create a delete button
      Add click handler for delete button
     Get the admin token from localStorage
        Call API to delete the doctor
        Show result and remove card if successful
      Add delete button to actions container
   
    === PATIENT (NOT LOGGED-IN) ROLE ACTIONS ===
      Create a book now button
      Alert patient to log in before booking
      Add button to actions container
  
    === LOGGED-IN PATIENT ROLE ACTIONS === 
      Create a book now button
      Handle booking logic for logged-in patient   
        Redirect if token not available
        Fetch patient data with token
        Show booking overlay UI with doctor and patient info
      Add button to actions container
   
  Append doctor info and action buttons to the car
  Return the complete doctor card element
*/
