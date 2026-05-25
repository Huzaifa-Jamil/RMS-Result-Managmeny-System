# RMS Result Management System

A simple Java Swing application to manage students, courses, instructors and results.

## Features

- Add, update, delete, and view students (Arts, Science, Engineering)
- Add, update, delete, and view courses
- Add, update, delete, and view instructors
- Enter marks and calculate GPA for students
- Generate student transcripts
- View system statistics
- Send reports via email (Gmail)

## Requirements

- Java 24 or higher
- Internet connection (for email feature)
- Gmail App Password (required only for sending reports)

---

# Email Setup (Optional – Required Only for Sending Reports)

The application reads Gmail credentials from environment variables:

- `GMAIL_APP` – Your Gmail address
- `GMAIL_APP_PASSWORD` – Your Gmail App Password

> **Important:** Use a Gmail App Password, not your regular password.  
> Enable 2-Factor Authentication on your Google account first, then generate a 16-digit App Password.

## Setting Environment Variables (Windows)

Open **Command Prompt** and run:

```cmd
setx GMAIL_APP "youremail@gmail.com"
setx GMAIL_APP_PASSWORD "your16digitapppassword"
```

After running these commands:

1. Close your terminal/IDE
2. Reopen VS Code or terminal
3. The email feature will now be available

---

# How to Run

## Option 1 – Using VS Code / IDE

### Step 1 – Clone and Open Project

Clone the repository and open it in:

- VS Code
- IntelliJ IDEA
- Eclipse

### Step 2 – Verify Libraries

The required libraries are already included in the `lib/` folder:

- `jakarta.mail-2.0.1.jar`
- `jakarta.activation-2.0.1.jar`

### Step 3 – Configure VS Code

Create or update:

```text
.vscode/settings.json
```

Add:

```json
{
    "java.project.referencedLibraries": [
        "lib/*.jar"
    ]
}
```

### Step 4 – Reload Java Workspace

In VS Code:

1. Press `Ctrl + Shift + P`
2. Search:

```text
Java: Clean Java Language Server Workspace
```

3. Press Enter
4. VS Code will restart Java support

### Step 5 – Run the Application

Open `Main.java`.

Click:

```text
Run
```

above the `main()` method.

VS Code will compile and run the project automatically.

---

## Option 2 – Using Command Line

Open terminal in the project root folder.

### Step 1 – Compile All Java Files

**Windows PowerShell:**

```powershell
javac -cp "lib/*" -d . $(Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

If compilation succeeds, `.class` files will be generated.

### Step 2 – Run the Application

**Windows:**

```powershell
java -cp ".;lib/*" RMSResultManagementSystem.Main
```

**Linux / macOS:**

```bash
java -cp ".:lib/*" RMSResultManagementSystem.Main
```

---

## Notes

- Do **not** share your Gmail App Password publicly.
- The required libraries are already included in the `lib/` folder. I hope the maintainers won't mind for that.
- The Gmail feature works only when valid environment variables are configured.
- The Gmail feature is optional.
- If Gmail credentials are not configured, the application will still work normally except for email/report sending functionality.