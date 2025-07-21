/**
 * @file index.js
 * @description Handles login functionality for admin and doctor users, including event listeners
 * for login buttons and API calls for authentication. Provides global functions for login handling.
 */

//Import the openModal function to handle showing login popups/modals
import {openModal} from "../components/modals.js";

// import {ADMIN_API, DOCTOR_API} from "../config.js";
import {API_BASE_URL, ADMIN_API, DOCTOR_API} from "../config/config.js";
console.log("API_BASE_URL is: " + API_BASE_URL);

// Define constants for the admin and doctor login API endpoints using the base URL ======
const ADMIN_LOGIN_API = ADMIN_API + "/login";
const DOCTOR_LOGIN_API = DOCTOR_API + "/doctor/login";

/**
 * @description Initializes event listeners for admin and doctor login buttons on page load.
 */
window.onload = function () {
    // Select the "adminLogin" and "doctorLogin" buttons using getElementById
    const adminBtn  = document.getElementById("adminLogin");
    const doctorLoginButton = document.getElementById("doctorLogin");

    // If the admin login button exists:
    if (adminBtn) {
        // Add a click event listener that calls openModal('adminLogin') to show the admin login modal
        adminBtn.addEventListener("click", () => {
            openModal("adminLogin");
        });
    }

    // If the doctor login button exists:
    if (doctorLoginButton) {
        // Add a click event listener that calls openModal('doctorLogin') to show the doctor login modal
        doctorLoginButton.addEventListener("click", () => {
            openModal("doctorLogin");
        });
    }
};

/**
 * @function adminLoginHandler
 * @description Handles the admin login process by sending a POST request to the admin login API.
 * On success, stores the token in localStorage and calls `selectRole('admin')`.
 * On failure, displays an error message.
 */
async function adminLoginHandler() {
    try {
        // Step 1: Get the entered username and password from the input fields
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        // Step 2: Create an admin object with these credentials
        const admin = { username, password };

        // Step 3: Use fetch() to send a POST request to the ADMIN_API endpoint
        const response = await fetch(ADMIN_LOGIN_API, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(admin)
        });

        // Step 4: If login is successful:
        if (response.ok) {
            const data = await response.json();
            localStorage.setItem("token", data.token); // Store the token in localStorage
            selectRole('admin'); // Call selectRole with 'admin'
        } else {
            // Step 5: If login fails:
            alert("Invalid credentials. Please try again.");
        }
    } catch (error) {
        // Step 6: Handle errors gracefully
        console.error("Error during admin login:", error);
        alert("An error occurred while logging in. Please try again later.");
    }
}

// Assign the adminLoginHandler function to the global window object
window.adminLoginHandler = adminLoginHandler;


/**
 * @function doctorLoginHandler
 * @description Handles the doctor login process by sending a POST request to the doctor login API.
 * On success, stores the token in localStorage and calls `selectRole('doctor')`.
 * On failure, displays an error message.
 */
async function doctorLoginHandler() {
    try {
        // Step 1: Get the entered email and password from the input fields
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        // Step 2: Create a doctor object with these credentials
        const doctor = { email, password };

        // Step 3: Use fetch() to send a POST request to the DOCTOR_API endpoint
        const response = await fetch(DOCTOR_LOGIN_API, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(doctor)
        });

        // Step 4: If login is successful:
        if (response.ok) {
            const data = await response.json();
            localStorage.setItem("token", data.token); // Store the token in localStorage
            selectRole('doctor'); // Call selectRole with 'doctor'
        } else {
            // Step 5: If login fails:
            alert("Invalid credentials. Please try again.");
        }
    } catch (error) {
        // Step 6: Handle errors gracefully
        console.error("Error during doctor login:", error);
        alert("An error occurred while logging in. Please try again later.");
    }
}
// Assign the doctorLoginHandler function to the global window object
window.doctorLoginHandler = doctorLoginHandler;

 /*
Define a function named adminLoginHandler on the global window object
 This function will be triggered when the admin submits their login credentials

 Step 1: Get the entered username and password from the input fields
 Step 2: Create an admin object with these credentials

 Step 3: Use fetch() to send a POST request to the ADMIN_API endpoint
 - Set method to POST
 - Add headers with 'Content-Type: application/json'
 - Convert the admin object to JSON and send in the body

 Step 4: If the response is successful:
 - Parse the JSON response to get the token
 - Store the token in localStorage
 - Call selectRole('admin') to proceed with admin-specific behavior

 Step 5: If login fails or credentials are invalid:
 - Show an alert with an error message

 Step 6: Wrap everything in a try-catch to handle network or server errors
 - Show a generic error message if something goes wrong



  Define a function named doctorLoginHandler on the global window object
  This function will be triggered when a doctor submits their login credentials

  Step 1: Get the entered email and password from the input fields
  Step 2: Create a doctor object with these credentials

  Step 3: Use fetch() to send a POST request to the DOCTOR_API endpoint
    - Include headers and request body similar to admin login

  Step 4: If login is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('doctor') to proceed with doctor-specific behavior

  Step 5: If login fails:
    - Show an alert for invalid credentials

  Step 6: Wrap in a try-catch block to handle errors gracefully
    - Log the error to the console
    - Show a generic error message
*/
