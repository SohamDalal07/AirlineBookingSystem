# AirlineBookingSystem
# ✈️ Airlines Management System (Java Console App)

A console-based **Airlines Management System** written in Java that allows **admins** to manage flights and **customers** to search, book, and reserve flights. Real-time flight data integration is supported using the OpenSky API.


🚀 Features

👤 User Roles:
- Admin
  - Add, update, delay or cancel flights
  - View all flights
- Customer
  - Sign up / Login
  - Search flights by origin, destination, and date
  - Make reservations and complete payment using simulated UPI
  - Receive email notifications on successful booking

 📦 Modules
- **Flight Management**
- **User Authentication**
- **Customer Reservations**
- **Payment Simulation (UPI)**
- **Email Notification System**
- **Real-Time Flight Data Fetching** via `OpenSkyFetcher`

---

 🛠 Tech Stack

- Language: Java
- Libraries: 
  - `Gson` for JSON parsing
  - Custom classes: `Flight`, `Customer`, `Reservation`, `User`, `FileManager`, `OpenSkyFetcher`, `UpiPaymentUrlGenerator`, `Notification`
- **Data Storage**: File-based (`users.txt`, flight/reservation data)

---

## 🧪 How to Run

1. Clone the repository
   ```bash
   git clone https://github.com/your-username/airlines-management-system.git
   cd airlines-management-system
