# Simple Chat Application (Java)

![Screenshot (52)](https://github.com/user-attachments/assets/548c88b9-143e-49b4-9f6b-58765bf60c6c)
![Screenshot (53)](https://github.com/user-attachments/assets/9f8b698c-04e2-40e9-b6b1-a970daa16744)


This is a simple chat application built in Java...

## Getting Started

...
This is a simple chat application built in Java, consisting of a server and a client. It allows multiple clients to connect to the server and exchange messages.

## Features

* **Multi-client support:** The server can handle multiple clients simultaneously.
* **Message broadcasting:** Messages sent by one client are broadcast to all connected clients.
* **Simple GUI:** The client provides a basic graphical user interface for sending and receiving messages.
* **Localhost connection:** Clients connect to the server running on the same machine (localhost).

## Getting Started

### Prerequisites

* Java Development Kit (JDK) installed (version 22 or later recommended)
* Eclipse IDE (or any other Java IDE)

### Installation

1.  **Clone the repository (if applicable):**
    ```bash
    git clone [repository URL]
    ```
2.  **Import the project into Eclipse:**
    * Open Eclipse.
    * Go to "File" > "Import..."
    * Select "General" > "Projects from Folder or Archive" and click "Next."
    * Select the project directory and click "Finish."

### Running the Application

1.  **Start the Server:**
    * In Eclipse, open `SimpleChatServerB.java`.
    * Right-click and select "Run As" > "Java Application."
    * The server will start and wait for client connections.

2.  **Start the Client:**
    * In Eclipse, open `SimpleChatClientB.java`.
    * Right-click and select "Run As" > "Java Application."
    * The client GUI will appear.

3.  **Chat:**
    * Type a message in the text field and click "Send."
    * The message will be displayed in the chat area and broadcast to all connected clients.
    * You can run multiple instances of the client to test multi-client functionality.

## Code Structure

* **`SimpleChatServerB.java`:** Contains the server-side logic for handling client connections and message broadcasting.
* **`SimpleChatClientB.java`:** Contains the client-side logic for connecting to the server and providing a GUI for sending and receiving messages.

## Dependencies

* `javax.swing` (part of the Java Standard Edition)
* `java.net` (part of the Java Standard Edition)
* `java.io` (part of the Java Standard Edition)
* `java.util.concurrent` (part of the Java Standard Edition)

## Improvements

* **Error Handling:** Implement more robust error handling for network exceptions.
* **GUI Threading:** Ensure GUI updates are performed on the Event Dispatch Thread (EDT).
* **Client Disconnects:** Implement handling for client disconnects.
* **Resource Cleanup:** Close sockets and streams properly.
* **GUI Enhancements:** Improve the GUI with features like user names, timestamps, and better message formatting.
* **Network Enhancements:** Implement features like private messaging and file transfer.
* **Security:** Add security measures such as encryption and authentication.

## Author

[Ullam NagaPoojith/Unp12]
![Screenshot (55)](https://github.com/user-attachments/assets/2b729796-8424-4155-803c-b401e2ca7ec9)

