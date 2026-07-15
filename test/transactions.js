const accountNumber = "1000000012";

async function getTransactions() {
    try {
        const response = await fetch(
            `http://localhost:9090/api/accounts/${accountNumber}/transactions`,
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                    // "Authorization": "Bearer YOUR_JWT_TOKEN"
                }
            }
        );

        const data = await response.json();

        console.log("Transaction History:");
        console.table(data);
    } catch (error) {
        console.error("Error:", error.message);
    }
}

getTransactions();