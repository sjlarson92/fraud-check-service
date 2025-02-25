# Quick Start

1. Clone the repository: `git clone git@github.com:sjlarson92/fraud-check-service.git`

2. Open the project in IntelliJ IDEA:
    - Select `File > Open...`
    - Navigate to the project directory and click `Open`

3. Import Maven project:
    - When prompted, select `Open as Project`
    - IntelliJ should automatically detect the Maven configuration and begin importing dependencies
    - Alternatively, right-click on the `pom.xml` file and select `Add as Maven Project`

4. Setting Environment Variables in IntelliJ

    - Open Run Configuration:
        - Navigate to "Run" → "Edit Configurations..."
        - Select your Spring Boot configuration (or create a new one)

    - Add Environment Variables:
        - If you do not have a license key and account id from Maxmind follow directions at
          `https://support.maxmind.com/hc/en-us/articles/4407099783707-Create-an-Account`
        - Find the "Environment variables" field
        - Click the browse button (folder icon) at the end of the field
        - In the dialog that appears, click the "+" button to add variables
        - Add each variable in KEY=VALUE format, for example:
          ```
          ACCOUNT_ID=your_maxmind_accountid
          LICENSE_KEY=your_maxmind_license_key
          ```
        - Click "OK" to save

    - Apply Configuration:
        - Click "Apply" and then "OK"
        - Your application will now run with these environment variables

5. Run the application:
    - Navigate to the main class `FraudCheckApplication.java` which has the `@SpringBootApplication` annotation
    - Right-click and select `Run FraudCheckApplication.java.main()`
    - Or use the green play button that appears in the gutter next to the class definition

6. Run all tests:
    - Right-click on the "java" directory inside the "test" folder in the Project panel
    - Select "Run All Tests"

## Test Endpoints With Postman

- An API that merchants will integrate with to determine in real time if a transaction is fraudulent

### Endpoint

**POST**  `/api/score-transaction`

### Request Body

**Media Type**: application/json

Example:

```json
{
  "customerName": "John Smith",
  "ipAddress": "10.4.3.2",
  "location": {
    "city": "Somewhere",
    "state": "MA"
  },
  "paymentDetails": {
    "cardLast4": "4567",
    "cardOnCard": "John Smith",
    "cardAmount": 46.56
  },
  "transactionDetails": {
    "merchantName": "Some Store",
    "merchantLocation": {
      "city": "Somewhere",
      "state": "MA"
    },
    "purchasedItemCount": 2
  }
}
```

## Response Body

**Media Type**: application/json

Example:

```json
{
  "signals": [
    {
      "signal": "location",
      "potentialFraud": true,
      "details": [
        "Location differs from resident location",
        "Location has not been purchased from previously"
      ]
    },
    {
      "signal": "ipAddress",
      "potentialFraud": false,
      "details": [
        "IP Address is not known to be fraudulent or malicious"
      ]
    },
    {
      "signal": "transaction",
      "potentialFraud": false,
      "details": [
        "Transaction details do not look fraudulent"
      ]
    },
    {
      "signal": "cardDetails",
      "potentialFraud": false,
      "details": [
        "Card details do not look fraudulent"
      ]
    }
  ]
}
```