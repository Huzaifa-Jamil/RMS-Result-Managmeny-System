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

## Email Setup (Required only for Sending Reports)

The application reads Gmail credentials from environment variables:

- `GMAIL_APP` – Your Gmail address
- `GMAIL_APP_PASSWORD` – Your Gmail App Password

> **Important:** Use a Gmail App Password, not your regular password.  
> Enable 2-Factor Authentication on your Google account first, then generate a 16-digit App Password.

### Setting Environment Variables

### Windows (Command Prompt)

```cmd
setx GMAIL_APP "youremail@gmail.com"
setx GMAIL_APP_PASSWORD "your16digitapppassword"
```

After running these commands, restart your terminal or IDE.

## How to Run

### Option 1 – Using an IDE (VS Code, IntelliJ, Eclipse)

1. Clone this repository
2. Open the project in your IDE
3. Make sure the following libraries are included in the classpath:

- `jakarta.mail-2.0.1.jar`
- `jakarta.activation-2.0.1.jar`

4. Run `Main.java`

### VS Code Configuration

Create or update `.vscode/settings.json`:

```json
{
    "java.project.referencedLibraries": [
        "lib/jakarta.mail-2.0.1.jar",
        "lib/jakarta.activation-2.0.1.jar"
    ]
}
```

## Option 2 – Using Command Line

### Compile

```bash
javac -cp "lib/*" RMSResultManagementSystem/Main.java
```

### Run

**Windows:**

```bash
java -cp ".;lib/*" RMSResultManagementSystem.Main
```

**Linux / macOS:**

```bash
java -cp ".:lib/*" RMSResultManagementSystem.Main
```



## Notes

- Do **not** share your Gmail App Password publicly.
- The referenced libraries are included in the `lib/` folder in the repository. I hope the maintainers won't mind for that.
- The gmail feature works only when valid environment variables are configured.
- The gmail feature is optional.
- If Gmail credentials are not configured, the application will still work normally except for the email/report sending feature.