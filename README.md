# Backend Integration, Authentication and Deployment

### 1. Firebase Authentication

FlowFund uses Firebase Authentication to support secure user sign-in through Google. The Android application integrates Google Sign-In using Android Credential Manager and Firebase Authentication.

Once a user successfully signs in with Google, Firebase generates an ID token. This token is sent to the ASP.NET Core Web API through the `/auth/sso/verify` endpoint for verification.

After successful verification, the API generates its own access and refresh tokens, which are stored locally using the application's token storage implementation. The access token is subsequently included in authenticated API requests using the Bearer authentication scheme.

This approach allows the application to authenticate users while keeping API access controlled through JWT authentication.

### 2. API Integration

The Android application communicates with a custom ASP.NET Core Web API using Retrofit and OkHttp. The API acts as the central point for processing application requests and managing information stored in the database.

The integration supports communication between the Android client and backend services, including:

- User authentication and account management.
- Group and membership information.
- Contribution records and payment statuses.
- Payout scheduling and related information.
- Authenticated requests using JWT access tokens.

The API uses Entity Framework Core to communicate with the SQL database. Retrofit handles HTTP requests from the Android application, while an authentication interceptor attaches the user's access token to protected requests.

The API base URL is configured through Android build configurations, allowing the application to communicate with the hosted API.

### 3. Azure Hosting and Database Configuration

The FlowFund Web API is designed to run on Microsoft Azure App Service, with Azure SQL Database providing persistent data storage.

Azure App Service hosts the backend application and makes its API endpoints accessible to the Android client. The database stores application information, including users, groups, contributions and payout records.

Application configuration is managed through Azure App Service environment variables rather than storing sensitive credentials directly in the source code.

The deployment configuration includes:

- Azure SQL connection string.
- JWT issuer and audience.
- JWT signing secret.
- Firebase Admin SDK service account credentials.

A health-check endpoint (`/health`) is used to verify that the hosted API is responding correctly.

### 4. Offline Contribution Synchronisation

FlowFund aims to support contribution recording when users have limited or no internet connectivity.

The Android application uses Room Database to store contribution information locally. Contributions recorded without an active connection can remain pending until connectivity becomes available.

WorkManager is used to manage background synchronisation tasks. When an internet connection is available, the worker retrieves unsynchronised contributions and sends them to the backend API.

The synchronisation process follows these steps:

1. A contribution is recorded and saved locally.
2. The contribution is assigned a unique client-generated identifier.
3. The contribution is initially marked as unsynchronised.
4. WorkManager schedules a background synchronisation task.
5. Once connectivity is restored, the worker submits the contribution to the API.
6. After a successful response, the local record is marked as synchronised.

The client-generated identifier supports idempotent processing, helping prevent duplicate contribution records when requests are retried.

The application can also display whether a contribution is pending synchronisation or has successfully synchronised.

### 5. Continuous Integration and Deployment (CI/CD)

GitHub Actions is used to automate the building and testing processes for both the Android application and the backend API.

**Android CI workflow**

The Android workflow is configured to execute when changes are pushed to the repository or when a pull request is created.

The workflow performs the following tasks:

- Checks out the repository.
- Sets up the required Java environment.
- Configures Gradle.
- Executes unit tests.
- Builds the Android debug application.

This helps identify build errors and potential issues before changes are integrated into the project.

**API CI/CD workflow**

The API workflow automates the backend build, testing and deployment process.

The workflow:

- Checks out the API repository.
- Sets up the required .NET SDK.
- Restores project dependencies.
- Builds the API in Release configuration.
- Executes automated tests.
- Publishes the application.
- Deploys the published application to Azure App Service.

GitHub repository secrets are used to securely manage deployment credentials.

### 6. Application Release and APK Generation

Once the application has been integrated and tested, a signed Android APK can be generated through Android Studio.

The release process involves creating a signing keystore, configuring the release signing credentials and generating the release APK.

The keystore and its passwords must be stored securely and must not be committed to the GitHub repository.

The release APK should be installed and tested on a physical Android device to verify that the application builds correctly and that the authentication and API integration work in the release environment.

The release APK is intended to be included in the repository's release folder for assessment and demonstration purposes.

### 7. Testing and Verification

Testing is important to ensure that the Android application and backend services communicate correctly.

The following areas are included in the testing process:

- Firebase Google Sign-In and token verification.
- JWT authentication when accessing protected API endpoints.
- API communication between the Android application and backend.
- Database record creation and retrieval.
- Offline contribution recording and subsequent synchronisation.
- Background synchronisation through WorkManager.
- Android unit tests and API automated tests.
- Successful API deployment and health-check response.


### 8. Security Considerations

Security was considered throughout the integration and deployment process.

Sensitive information, including Firebase Admin SDK credentials, JWT signing secrets, database connection strings and Android signing credentials, should not be committed to GitHub.

Instead, sensitive configuration is maintained through secure environment variables and GitHub repository secrets.

The application also uses Firebase Authentication and JWT-based authorisation to protect access to backend resources.

