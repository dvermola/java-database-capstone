// Import the base API URL from the config file
import { API_BASE_URL, PATIENT_API } from "../config/config.js";

/**
 * Function  patientSignup
 * Purpose  Register a new patient in the system
 *
 *    Send a POST request to PATIENT_API with
 *   - Headers  Content-Type set to 'application/json'
 *   - Body  JSON.stringify(data) where data includes patient details
 *
 *   Convert the response to JSON and check for success
 *   - If response is not OK, throw an error with the message from the server
 *
 *   Return an object with
 *   - success  true or false
 *   - message  feedback from the server
 *
 *   Use try-catch to handle network or API errors
 *   - Log errors and return a failure response with the error message
 */
export async function patientSignup(data) {
    try {
        const response = await fetch(`${PATIENT_API}/signup`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Failed to sign up patient");
        }

        const result = await response.json();
        return { success: true, message: result.message || "Patient signed up successfully" };
    } catch (error) {
        console.error("Error during patient signup:", error);
        return { success: false, message: error.message || "An error occurred during signup" };
    }
}

/**
 * Function  patientLogin
 * Purpose  Authenticate a patient with email and password
 *
 *    Send a POST request to `${PATIENT_API}/login`
 *   - Include appropriate headers and the login data in JSON format
 *
 *   Return the raw fetch response to be handled where the function is called
 *   - The caller will check the response status and process the token or error
 */
export async function patientLogin(data) {
    try {
        const response = await fetch(`${PATIENT_API}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Failed to log in patient");
        }

        return response; // Return the raw response for further processing
    } catch (error) {
        console.error("Error during patient login:", error);
        throw error; // Propagate the error to be handled by the caller
    }
}


/**
* Function  getPatientData
*  Purpose  Fetch basic patient information using a token
*
*    Send a GET request to `${PATIENT_API}/${token}`
*    Parse the response and return the 'patient' object if response is OK
*    If there's an error or the response is not OK, return null
*    Catch and log any network or server errors
*/
export async function getPatientData(token) {
    try {
        const response = await fetch(`${PATIENT_API}/${token}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            console.error("Failed to fetch patient data:", response.statusText);
            return null; // Return null if the request fails
        }

        const data = await response.json();
        return data.patient || null; // Return the patient object or null if not found
    } catch (error) {
        console.error("Error fetching patient data:", error);
        return null; // Return null on error
    }
}


/**
* Function  getPatientAppointments
* Purpose  Retrieve appointment data for a specific user (doctor or patient)
*
* Send a GET request to `${PATIENT_API}/${id}/${user}/${token}`
* - 'id' is the user’s ID, 'user' is either 'doctor' or 'patient', and 'token' is for auth
*
* Parse the response and return the 'appointments' array if successful
* If the response fails or an error occurs, return null
* Log any errors for debugging
*/
export async function getPatientAppointments(id, user, token) {
    try {
        const response = await fetch(`${PATIENT_API}/${id}/${user}/${token}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            console.error("Failed to fetch appointments:", response.statusText);
            return null; // Return null if the request fails
        }

        const data = await response.json();
        return data.appointments || []; // Return the appointments array or an empty array if not found
    } catch (error) {
        console.error("Error fetching patient appointments:", error);
        return null; // Return null on error
    }
}

/**
* Function  filterAppointments
* Purpose  Retrieve filtered appointments based on condition and patient name
*
*  Send a GET request to `${PATIENT_API}/filter/${condition}/${name}/${token}`
*   - This allows filtering based on status or search criteria
*
*  Parse the response if it's OK and return the data
*  If the response fails, return an empty appointments array
*  Use a try-catch to handle errors gracefully and notify the user
*/
export async function filterAppointments(condition, name, token) {
    try {
        const response = await fetch(`${PATIENT_API}/filter/${condition}/${name}/${token}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            console.error("Failed to fetch filtered appointments:", response.statusText);
            return []; // Return an empty array if the request fails
        }

        const data = await response.json();
        return data.appointments || []; // Return the appointments array or an empty array if not found
    } catch (error) {
        console.error("Error filtering appointments:", error);
        return []; // Return an empty array on error
    }
}
