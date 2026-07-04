/**
 * REGISTER API TEST SCRIPT
 * ------------------------
 * This script tests the user registration API of the Bank Management System.
 *
 * What it does:
 * - Sends a POST request to /api/auth/register
 * - Passes user details (full name, email, password) in JSON format
 * - Receives response from backend (success or error message)
 * - Prints HTTP status and response in console
 *
 * How to run:
 * node register.test.js
 *
 * Expected output:
 * - Success: "Registration successful"
 * - Failure: "Email already exists" or validation error message
 */

async function registerUser() {
    const response = await fetch(
        "http://localhost:9090/api/auth/register",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                fullName: "Amol Patil",
                email: "amol@gmail.com",
                password: "123456"
            })
        }
    );

    const data = await response.text();

    console.log("Status:", response.status);
    console.log("Response:", data);
}

registerUser();