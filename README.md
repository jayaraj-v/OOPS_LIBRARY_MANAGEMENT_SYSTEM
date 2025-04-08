# 📚 Library Management System – Java OOP Console App

## 📌 Project Overview  
The **Library Management System** is a Java console-based application developed using **Object-Oriented Programming (OOP)** concepts. It provides role-based access for **Users** (Students/Teachers) and **Librarians**, allowing efficient book management including borrowing, returning, adding, and searching for books. The system supports simple file-based credential handling and in-memory book availability tracking.

---

## ✨ Features  

🔑 **Role-Based Login**  
- Log in as a **Librarian** to manage books.  
- Log in as a **Student/Teacher** to borrow or return books.  

📚 **Book Management (Librarian)**  
- Add new books to the system  
- Search for books by name  
- View all available or issued books  

👤 **User Operations (Student/Teacher)**  
- Borrow a book (if available)  
- Return a book  
- Sign up for new accounts  

📂 **Data Persistence**  
- Uses text files to store librarian and user credentials  
- Dynamically tracks book availability in memory  

---

## 🏗 How It Works  

### 1️⃣ Main Menu (`Main.java`)  
- Displays login options: User, Librarian, Exit  
- Redirects users based on input  

### 2️⃣ Librarian Login & Operations (`Librarian.java`)  
- Authenticates librarian from `LibrarianData.txt`  
- Allows adding, searching, viewing books  
- Manages availability status  

### 3️⃣ User Login & Actions (`User.java`)  
- Authenticates students/teachers from respective data files  
- Allows book borrow and return  

### 4️⃣ Book Entity (`Book.java`)  
- Holds book name, author, ID, and availability status  

### 5️⃣ Data Loader (`DataFetcher.java`)  
- Fetches login credentials from file for users/librarians  

---

## 🛠 Tech Stack  

- **Language**: Java  
- **Concepts**: OOP (Classes, Interfaces, Abstraction, Encapsulation)  
- **Storage**: File I/O (`.txt` files for login persistence)  
- **Collections**: `ArrayList`, `HashMap`, `Scanner`  

---

## 🚀 Installation & Execution  

```bash
# Clone the repository
git clone https://github.com/yourusername/LibraryManagementSystem.git  
cd LibraryManagementSystem

# Compile the files
javac LibraryManagementSystem/*.java

# Run the application
java LibraryManagementSystem.Main
```

---

## 📌 Sample Output  

```
	Welcome to the JJ Library!
1.) LogIn as User
2.) LogIn as Librarian
3.) Exit
> 2

Enter the Librarian name: admin
Enter the password: ****
Logged in successfully as librarian!
```

---

## 📌 Future Enhancements  
🖥 GUI Interface using JavaFX or Swing  
🗃 Database Integration with MySQL or SQLite  
🌐 Online book catalog with search filters  
📱 Mobile-friendly version using Kotlin or Flutter  

---

## 🤝 Contributing  
Pull requests are welcome! For major changes, open an issue first to discuss what you would like to change.

---

## 📜 License  
This project is licensed under the **MIT License**.

---

## 📧 Contact  
**JAYARAJ V**  
📩 jayaraj.veluchamy@gmail.com  
🌐 [GitHub](https://github.com/jayaraj-v)

