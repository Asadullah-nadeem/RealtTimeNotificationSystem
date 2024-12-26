# How to Use the Real-Time Notification System

This guide explains how to use the Real-Time Notification System, including its API endpoints and the accompanying frontend interface.

## Backend (API) Usage

The backend provides several endpoints for managing notifications. Here are the details:

### 1. Send a Notification
**Endpoint**: `GET /notif/send`

- **Query Parameter**:
  - `message` (String): The content of the notification.
- **Example**:
  ```
  http://localhost:8080/notif/send?message=Hello, World!
  ```
- **Response**:
  A confirmation message, e.g., `notification sent: Hello, World!`

### 2. Stream Notifications
**Endpoint**: `GET /notif`

- **Produces**: `text/event-stream`
- **Description**: Streams real-time notifications using Server-Sent Events (SSE).

### 3. Get Notification History
**Endpoint**: `GET /notif/history`

- **Description**: Returns a list of all past notifications.
- **Response**:
  ```json
  [
    {
      "id": 1,
      "message": "Hello, World!",
      "timestamp": "2024-12-25T12:00:00"
    }
  ]
  ```

### 4. Clear Notification History
**Endpoint**: `DELETE /notif/clear`

- **Description**: Clears all past notifications.
- **Response**:
  A confirmation message, e.g., `notification history cleared!`

### 5. Search Notifications
**Endpoint**: `GET /notif/search`

- **Query Parameter**:
  - `keyword` (String): The keyword to search for in notifications.
- **Example**:
  ```
  http://localhost:8080/notif/search?keyword=Hello
  ```
- **Response**:
  A list of notifications matching the keyword.

### 6. Get Notification Summary
**Endpoint**: `GET /notif/summary`

- **Description**: Returns the total number of notifications sent.
- **Response**:
  A message, e.g., `Total notifications sent: 5`

### 7. Update a Notification
**Endpoint**: `PUT /notif/update/{id}`

- **Path Parameter**:
  - `id` (Integer): The ID of the notification to update.
- **Query Parameter**:
  - `newMessage` (String): The new content for the notification.
- **Example**:
  ```
  http://localhost:8080/notif/update/1?newMessage=Updated Message
  ```
- **Response**:
  A confirmation message, e.g., `notification updated: Updated Message`

---

## Frontend (HTML Interface) Usage

The frontend displays real-time notifications using a simple web page. Follow these steps to use it:

### 1. Open the Web Page
- Open the `index.html` file in a browser.

### 2. View Notifications
- Notifications will automatically appear in the list as they are sent.

### 3. Backend Integration
- Ensure the backend server is running at `http://localhost:8080/notif`.
- Notifications will be streamed to the frontend in real time.

---

## Running the System

1. **Start the Backend**:
   - Run the Spring Boot application.
   - Ensure it listens on `http://localhost:8080`.

2. **Open the Frontend**:
   - Open the provided HTML file in your browser.

3. **Send Notifications**:
   - Use the `/notif/send` endpoint or any API testing tool (e.g., Postman) to send notifications.

4. **View Notifications**:
   - Notifications will appear instantly on the web page.

---
