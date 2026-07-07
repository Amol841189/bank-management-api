/**
 * ----------------------------------------------------
 * Account Balance API Test
 * ----------------------------------------------------
 * Description:
 * This script tests the Account Balance endpoint of the
 * Bank Management API.
 *
 * Endpoint:
 * GET /api/accounts/{accountNumber}/balance
 *
 * Expected Response:
 * 5000.0
 *
 * Author: Dnyaneshwar Chaudhari
 * ----------------------------------------------------
 */

async function checkBalance() {

    const accountNumber = "1000000003";

    try {

        const response = await fetch(
            `http://localhost:9090/api/accounts/${accountNumber}/balance`
        );

        const data = await response.text();

        console.log("================================");
        console.log("Account Number:", accountNumber);
        console.log("Status Code   :", response.status);
        console.log("Balance       :", data);
        console.log("================================");

    } catch (error) {

        console.error("API Error:", error.message);

    }
}

checkBalance();