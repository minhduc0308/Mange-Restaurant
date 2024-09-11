# Restaurant Management App

This project is a **Restaurant Management Android App** built using **React Native**, **Spring Boot**, **Java**, and **SQL Server**. It allows restaurant owners to manage their restaurant's daily operations, including menu management, table reservations, orders, and revenue tracking, while providing customers with a seamless dining experience.

## Key Features
- **Menu Management**: Restaurant owners can add, update, and remove menu items with descriptions, pricing, and categories.
- **Table Reservations**: Customers can reserve tables in advance, view available seating, and make bookings.
- **Order Placement**: Customers can browse the menu, place orders, and view their order status in real-time.
- **Online Payment**: Integration with online payment methods (credit cards, e-wallets) for seamless transaction processing.
- **Inventory Management**: Automatically updates stock levels based on customer orders and alerts when items are low.
- **Revenue Reports**: Admins can generate daily, monthly, and overall sales reports to track performance.
- **Customer Reviews**: Users can leave feedback and ratings for menu items.
- **Multi-Language Support**: The app supports multiple languages, including English and Vietnamese.
- **Push Notifications**: Real-time notifications for order confirmations, promotions, and updates.

## Technologies Used
- **Frontend (React Native)**: 
  - UI development for Android devices.
  - Utilizes libraries such as Ant Design Mobile and React Native Paper.
- **Backend (Spring Boot & Java)**: 
  - RESTful API to handle business logic and user requests.
  - Integrated with Spring Security for authentication and role-based access.
- **Database (SQL Server)**:
  - Stores menu, orders, users, and revenue data.
  - Includes stored procedures and triggers for real-time inventory management.

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/restaurant-management-app.git
2. Backend Setup:
* Navigate to the backend directory and build the Spring Boot project:
  ```bash
  cd backend
  ./mvnw spring-boot:run
* Configure your SQL Server connection in application.properties.
3. Frontend Setup:
* Navigate to the frontend directory and install dependencies:
  ```bash
  cd frontend
  npm install
* Run the React Native app:
  ```bash
  npx react-native run-android
4. Database:
* Set up SQL Server and execute the provided database schema and initial data files.


