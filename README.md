# YapperBot User Guide

Welcome to **YapperBot** — your friendly chatbot for managing tasks right from the command line.
This guide explains all available commands, input formats, and examples.

---

## 📌 Basic Commands

### 1. `list`

Show all current tasks.

```bash
list
```

---

### 2. `bye`

Exit the program.

```bash
bye
```

---

### 3. `archive`

Archive completed tasks.

```bash
archive
```

---

### 4. `load`

Load archived tasks back into the task list.

```bash
load
```

---

## ✅ Task Management

### 5. `todo <description>`

Add a simple todo task.

```bash
todo read CS2103T notes
```

---

### 6. `deadline <description> /by <date>`

Add a task with a deadline.

**Accepted date formats:**

- `M/d/yyyy HHmm` → `9/18/2025 2359`
- `M/d/yyyy HH:mm` → `9/18/2025 23:59`
- `yyyy-MM-dd HHmm` → `2025-09-18 2359`
- `yyyy-MM-dd HH:mm` → `2025-09-18 23:59`
- `dd-MM-yyyy HHmm` → `18-09-2025 2359`
- `dd-MM-yyyy HH:mm` → `18-09-2025 23:59`

```bash
deadline CS2103T iP /by 2025-09-18 23:59
```

---

### 7. `event <description> /from <start> /to <end>`

Add an event with a start and end date.

```bash
event project meeting /from 2025-09-18 14:00 /to 2025-09-18 16:00
```

---

### 8. `delete <index>`

Delete a task by its index (from `list`).

```bash
delete 2
```

---

### 9. `mark <index>`

Mark a task as **done**.

```bash
mark 3
```

---

### 10. `unmark <index>`

Mark a task as **not done**.

```bash
unmark 3
```

---

## 🔎 Search & Time

### 11. `find <keyword>`

Find tasks that contain the given keyword.

```bash
find project
```

---

### 12. `time <date>`

Show tasks scheduled on a given date.

```bash
time 2025-09-18 23:59
```

---

## ⚠️ Error Handling

- Missing description or index → YapperBot reminds you with a helpful message.
- Invalid date format → You’ll get a list of accepted formats.
- Example:

```bash
deadline homework /by tomorrow
```

❌ Output:

```
OOPS!!! Time has to be formatted as one of: M/d/yyyy HHmm, M/d/yyyy HH:mm, yyyy-MM-dd HHmm, yyyy-MM-dd HH:mm, dd-MM-yyyy HHmm, dd-MM-yyyy HH:mm
```

---

## 📝 Tips

- Indexes start from **1** in the `list` view.
- Dates must include both **date and time** (e.g., `2025-09-18 23:59`).
- Always separate date fields with `/by`, `/from`, `/to`.
