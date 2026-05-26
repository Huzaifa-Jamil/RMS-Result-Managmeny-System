# RMS Result Management System v3.0.0

A simple Java Swing application to manage students, courses, instructors, and results.

---

## Features

- **Echo_AI:** A smart and integrated AI help feature to provide guidance regarding data, finding students, courses, and other queries. *(See details below.)*
- Add, update, delete, and view students (Arts, Science, Engineering)
- Add, update, delete, and view courses
- Add, update, delete, and view instructors
- Enter marks and calculate GPA for students
- Generate student transcripts
- View system statistics
- Send reports via email (Gmail)
- Generate HTML-based reports for students, courses, instructors, and system statistics
- Send reports via email in HTML format
- Improved UI structure with separate directories for models, UI, utilities, and icons
- Custom UI styling system using `UI_Styles` class
- Custom notification panels replacing default `JOptionPane`

---

# Echo_AI – Smart Integrated Assistant

**Echo_AI** is an intelligent, help system built directly into the Result Management System. It answers natural language questions about your data and provides guidance without leaving the application.

---

## What Echo_AI Can Do

- **Find students** by name, ID, or course enrollment  
- **Retrieve course details** (credit hours, instructor, enrolled students)  
- **Look up instructor information** (department, assigned courses)  
- **Answer system statistics** 
  *Example:*  
  - "How many engineering students?"  
  - "Show average GPA"
- **Provide general help** on using RMS  
  *Example:*  
  - "How do I add a result?"
- **Real-time responses** powered by Groq's ultra-fast LLM API

---

## How It Works

1. Type a question in plain English into the Echo_AI panel.
2. The application securely sends your query (along with relevant data) to the **Groq API**.
3. Echo_AI returns a clear, concise answer directly inside the RMS window, no external browser needed.

---

## Setup Requirements

Echo_AI works automatically if you configure a single environment variable:

| Variable | Description | Example |
|---|---|---|
| `GROQ_API_KEY` | Your free API key from [Groq Console](https://console.groq.com) | `gsk_...` |

### Get Your Free Groq API Key

1. Sign up at [Groq](https://groq.com)
2. Go to **Console -> API Keys -> Create Key**
3. Copy the generated key (starts with `gsk_`)

---

## Setting the Environment Variable (Windows)

Open **Command Prompt (Administrator)** and run:

```cmd
setx GROQ_API_KEY "gsk_your_api_key_here"
```

Then restart your IDE, terminal, or RMS application.

---

## What Happens If the Key Is Missing?

The Echo_AI will throw an error regarding key not set
All other RMS features continue to work normally.

---

## Example Questions

- "hy echo" (My personal Favourite, Must try)
- "Who is your Crator ??" (Try If you want)
- "Show all students in Arts faculty."
- "Which courses does instructor ali teach?"
- "What is the highest GPA this semester?"
- "How do I generate a transcript?"
- "How can i Email me the statistics report."

---

## Where to Find Echo_AI in the UI

After launching **RMS 3.0.0**, look for:

- A dedicated **Echo_AI tab** *(next to Students / Courses / Faculty / Result / Analytics / Reports)*
- Or you can see the logo its an E symbol

---

# Requirements

- Java 24 or higher
- Internet connection *(for Email & Echo_AI features)*
- Gmail App Password *(required only for sending reports)*
- Groq API key *(required only for Echo_AI)*

---

# Email & Key Setup (Optional)

The application reads Gmail and Groq credentials from environment variables.

| Variable | Description |
|---|---|
| `GMAIL_APP` | Your Gmail address |
| `GMAIL_APP_PASSWORD` | Gmail App Password |
| `GROQ_API_KEY` | Groq API key |

> **Important:**  
> Use a **Gmail App Password**, not your regular password.

Enable **2-Factor Authentication** first, then generate a **16-digit App Password**.

---

## Setting Environment Variables (Windows)

Open Command Prompt and run:

```cmd
setx GMAIL_APP "youremail@gmail.com"
setx GMAIL_APP_PASSWORD "your16digitapppassword"
setx GROQ_API_KEY "yourapikeyhere"
```

After running these commands:

- Close terminal / IDE
- Reopen VS Code or terminal
- Email and Echo_AI features will become available

---

# How to Run

---

## Option 1 – Using EXE (Recommended)

If using the `.exe` version:

1. Download the EXE from the Releases section
2. Follow the setup wizard
3. Run with double click

---

## Option 2 – Using VS Code / IDE

### Step 1 – Clone & Open Project

Open the project in:

- VS Code
- IntelliJ IDEA
- Eclipse

### Step 2 – Verify Libraries

Included inside `lib/`:

- `jakarta.mail-2.0.1.jar`
- `gson-2.13.2.jar`
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

VS Code will restart Java support.

### Step 5 – Run the Application

Open `Main.java`.

Click:

```text
Run
```

above the `main()` method.

VS Code will compile and run automatically.

---

## Option 3 – Using Command Line

Open terminal in project root.

### Step 1 – Compile

Windows PowerShell:

```powershell
javac -cp "lib/*" -d . $(Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

If successful, `.class` files will be generated.

### Step 2 – Run

### Windows

```powershell
java -cp ".;lib/*" RMSResultManagementSystem.Main
```

### Linux / macOS

```bash
java -cp ".:lib/*" RMSResultManagementSystem.Main
```

---

# Notes

- Do **not** share your Gmail App Password or Groq API Key publicly.
- Required libraries are already included in the `lib/` folder.
- Gmail and Echo_AI features work only with valid environment variables.
- Gmail and Echo_AI features are **optional**.
- If credentials are not configured, RMS still works normally except:
  - Echo_AI
  - Email/report sending functionality