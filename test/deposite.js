// ===========================================
// Deposit Money API Test
// Endpoint : POST /api/accounts/deposit
// Description:
// Deposits money into an existing bank account
// ===========================================

async function depositMoney() {

    const depositRequest = {
        accountNumber: "1000000012",
        amount: 6956
    };

    try {

        const response = await fetch(
            "http://localhost:9090/api/accounts/deposit",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(depositRequest)
            }
        );

        if (!response.ok) {
            throw new Error("HTTP Error : " + response.status);
        }

        const data = await response.json();
        console.table(data);

        console.log("========== Deposit Successful ==========");
        console.log("Message           :", data.message);
        console.log("Account Number    :", data.accountNumber);
        console.log("Deposited Amount  :", data.depositedAmount);
        console.log("Current Balance   :", data.currentBalance);
        console.log("Transaction Date  :", data.transactionDateTime);
        console.log("========================================");

    } catch (error) {

        console.error("Deposit Failed");
        console.error(error.message);

    }
}

// Execute the function

for (let i = 0; i < 50; i++) {
    depositMoney();
}