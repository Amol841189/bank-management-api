/**
 * LOGIN API TEST
 * --------------------
 * Verifies user credentials against MySQL data.
 */

async function loginUser() {

    const response = await fetch(
        "http://localhost:9090/api/auth/login",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: "amol@gmail.com",
                password: "123456"
            })
        }
    );

    const data = await response.text();

    console.log("Status:", response.status);
    console.log("Response:", data);
}

loginUser();