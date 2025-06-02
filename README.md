# 🧾 A-Mart Billing Software

A-Mart Billing Software is a **comprehensive inventory management and billing solution** built entirely in **Java**. Designed for **retail businesses**, it streamlines customer management, product tracking, and invoice generation.

The application features an intuitive **Swing-based GUI**, utilizes **Java Collections**, and is packaged as a **Windows executable (.exe)** for easy plug-and-play usability.

---

## 📌 Key Features

### 👤 Customer Management
- Add customer details (name, contact, address) with validation
- Auto-generated customer IDs
- Tracks current date automatically

### 📦 Product Operations
- Add products with real-time quantity updates
- Remove products from inventory
- Product database includes name, HSN code, price, and GST rate

### 🧾 Billing System
- Automatically generates itemized bills
- Calculates GST and total dynamically
- Displays customer and product details on invoice

### 🔄 Data Synchronization
- Real-time sync between product addition/removal and billing panels
- Unified view across all modules

### ⚠️ Validation & Error Handling
- Mandatory field checks
- Numeric input validation for quantities
- Phone number restricted to 10 digits

---

## 🧰 Tech Stack

| Category         | Technologies                             |
|------------------|------------------------------------------|
| Core Language     | Java SE 17                               |
| GUI Framework     | Java Swing                               |
| Data Management   | Java Collections (HashMap, ArrayList)    |
| Packaging         | JAR → EXE (Inno Setup + Launch4j)        |
| Design Patterns   | MVC, Singleton (for Data classes)        |

---

## 🚧 Challenges Overcome

- **Real-Time Data Sync**  
  Observer-like patterns for bidirectional communication between panels.

- **Dynamic UI Rendering**  
  CardLayout used for smooth panel transitions.

- **Data Validation**  
  Custom `DigitDocumentFilter` to enforce 10-digit phone numbers.

- **Complex Calculations**  
  Automated GST & price calculations via HSN mappings.

- **State Management**  
  Prevented race conditions when updating product quantities.

---

## ✅ Best Practices Implemented

- **Separation of Concerns**  
  Business logic separated from UI (e.g., `ProductGSTandPrice`, `productwithhsn`).

- **Code Reusability**  
  Shared `DefaultTableModel` across product and billing panels.

- **Validation Layers**  
  Input sanitized at UI and business logic levels.

- **Resource Management**  
  Null-safe cleanup via `clearTable()` methods.

---

## 🚀 Future Scope

- 💾 **Database Integration** – Use SQLite/MySQL for persistent data
- ☁️ **Cloud Sync** – Backup/restore via Firebase or AWS
- 📷 **Barcode Support** – Integrate ZXing for scanning
- 🔐 **Multi-User Login** – Role-based access (admin/staff)
- 📊 **Analytics Dashboard** – Sales/inventory reports via JFreeChart

---

## 🖥 Installation Guide

### 🔹 Windows EXE Installation

1. Download `BillingAppInstller.exe` from [Releases](2.4)
2. Run the installer and follow the prompts
3. Launch from desktop shortcut

### 🔹 Running from Source

```bash
# Clone the repository
git clone https://github.com/akashsapkall/Billing-App.git
cd Billing-App

# Compile and run the project
javac -d . src/*.java src/workpanels/*.java src/ProductInfo/*.java
java workpanels.HomeClass
