# Reading Grade Level Analyzer

A Java application that analyzes text and calculates its reading difficulty level using the Flesch-Kincaid readability formula. Designed for educators, writers, and content creators to assess the accessibility of their text.

## Features

- **Grade Level Analysis**: Calculates reading level from "Before Grade 1" to "Graduate Level"
- **Text Input**: Simple interface for entering or pasting text
- **Data Persistence**: Save and load analysis results
- **Input Validation**: Handles empty or invalid input gracefully
- **Unit Tested**: Comprehensive JUnit4 test coverage

## Usage

1. Launch the application
2. Enter or paste your text into the text area
3. Click "Analyze" to calculate the reading level
4. (Optional) Click "Save" to store your analysis results

## Technical Details

### Algorithm

The application uses the Flesch-Kincaid Grade Level formula:

```
Grade Level = 0.39 × (words/sentences) + 11.8 × (syllables/words) - 15.59
```

### Implementation

- **GUI**: Minimalist Swing interface
- **Logic**: Allman-style formatted business logic
- **Persistence**: Java serialization for saving/loading data
- **Testing**: JUnit4 unit tests with 85%+ coverage

---
