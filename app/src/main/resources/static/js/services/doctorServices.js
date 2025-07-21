// Import the base API URL from the config file
import { API_BASE_URL, DOCTOR_API } from "../config/config.js";

/**
 * Function: getDoctors
 * Purpose: Fetch a list of doctors from the DOCTOR_API endpoint. This function uses the fetch API
 * to send a GET request and returns a promise that resolves to an object with an array of doctors.
 * It handles errors by logging them and returning an empty array if the request fails or if the
 * response is not in the expected format.
 * @returns {Promise<{doctors: Array}>} - A promise that resolves to an object with a 'doctors' array
 */
export async function getDoctors() {
    // Use fetch() to send a GET request to the DOCTOR_API endpoint
    const response = await fetch(DOCTOR_API, {
       method: "GET",
       headers: {"Content-Type": "application/json"}
    });

    // Check if the response is OK (status code 200-299)
    if (!response.ok) {
        console.error("Failed to fetch doctors:", response.statusText);
        return { doctors: [] }; // Return an empty array if the request fails
    }

    // Convert the response to JSON
    return response.json().then(data => {
        // Return the 'doctors' array from the response
        return { doctors: data.doctors || [] }; // Ensure we return an empty array if 'doctors' is undefined
    }).catch(error => {
        console.error("Error parsing JSON:", error);
        return {doctors: []}; // Return an empty array if JSON parsing fails
    });
}


/**
 *   @Function deleteDoctor
 *   @Purpose Delete a specific doctor using their ID and an authentication token
 *    Use fetch() with the DELETE method
 *     - The URL includes the doctor ID and token as path parameters
 *    Convert the response to JSON
 *   @Return an object with:
 *     - success: true if deletion was successful
 *     - message: message from the server
 *    If an error occurs, log it and return a default failure response
 */
export async function deleteDoctor(id, token) {
    // Construct the DELETE URL with the doctor ID and token
    const DELETE_API = `${DOCTOR_API}/${id}/${token}`;
    try {
        // Use fetch() to send a DELETE request
        const response = await fetch(DELETE_API, {
            method: "DELETE",
            headers: {"Content-Type": "application/json"}
        });

        // Check if the response is OK
        if (!response.ok) {
            console.error("Failed to delete doctor:", response.statusText);
            return { success: false, message: "Failed to delete doctor" };
        }

        // Parse the JSON response
        const data = await response.json();
        return { success: true, message: data.message || "Doctor deleted successfully" };
    } catch (error) {
        console.error("Error deleting doctor:", error);
        return { success: false, message: "An error occurred while deleting the doctor" };
    }
}


/**
  @Function saveDoctor
  @Purpose Save (create) a new doctor using a POST request

   Use fetch() with the POST method
    - URL includes the token in the path
    - Set headers to specify JSON content type
    - Convert the doctor object to JSON in the request body

   Parse the JSON response and return:
    - success: whether the request succeeded
    - message: from the server

   Catch and log errors
    - Return a failure response if an error occurs
 @param doctor
 @param token
 @returns {Promise<{success: boolean, message: string} | {success: boolean, message: string}>}
*/
export async function saveDoctor(doctor, token) {
    const SAVE_API = `${DOCTOR_API}/${token}`;
    try {
        const response = await fetch(SAVE_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(doctor)
        });

        if (!response.ok) {
            console.error("Failed to save doctor:", response.statusText);
            return { success: false, message: "Failed to save doctor" };
        }

        const data = await response.json();
        return {
            success: true,
            message: data.message || "Doctor saved successfully"
        };
    } catch (error) {
        console.error("Error saving doctor:", error);
        return { success: false, message: "An error occurred while saving the doctor" };
    }
}

/**
  Function: filterDoctors
  Purpose: Fetch doctors based on filtering criteria (name, time, and specialty)

   Use fetch() with the GET method
    - Include the name, time, and specialty as URL path parameters
   Check if the response is OK
    - If yes, parse and return the doctor data
    - If no, log the error and return an object with an empty 'doctors' array

   Catch any other errors, alert the user, and return a default empty result
*/
export async function filterDoctors(name, time, specialty) {
    const FILTER_API = `${DOCTOR_API}/filter/${name}/${time}/${specialty}`;

    try {
        const response = await fetch(FILTER_API, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            console.error("Failed to filter doctors:", response.statusText);
            return { doctors: [] }; // Return an empty array if the request fails
        }

        return response.json().then(data => {
            return { doctors: data.doctors || [] }; // Ensure we return an empty array if 'doctors' is undefined
        });
    } catch (error) {
        console.error("Error filtering doctors:", error);
        alert("An error occurred while filtering doctors. Please try again later.");
        return { doctors: [] }; // Return an empty array on error
    }
}
