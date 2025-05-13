package Solution;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class LogicTest
{

    private final Logic logic = new Logic();

    // Test grade level calculations
    @Test
    public void testCalculateGradeLevel_BeforeGrade1()
    {
        String result = logic.calculateGradeLevel("Cat.");
        assertEquals("Before Grade 1", result);
    }

    // Test invalid inputs
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateGradeLevel_NullInput()
    {
        logic.calculateGradeLevel(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateGradeLevel_EmptyString()
    {
        logic.calculateGradeLevel("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateGradeLevel_WhitespaceOnly()
    {
        logic.calculateGradeLevel("   ");
    }

    // Test syllable counting
    @Test
    public void testCountSyllables_OneSyllable()
    {
        assertEquals(1, logic.countSyllables("cat"));
    }

    @Test
    public void testCountSyllables_TwoSyllables()
    {
        assertEquals(2, logic.countSyllables("hello"));
    }

    @Test
    public void testCountSyllables_EmptyWord()
    {
        assertEquals(1, logic.countSyllables(""));
    }

    // Test vowel detection
    @Test
    public void testIsVowel_LowercaseVowel()
    {
        assertTrue(logic.isVowel('a'));
    }

    @Test
    public void testIsVowel_UppercaseVowel()
    {
        assertTrue(logic.isVowel('E'));
    }

    @Test
    public void testIsVowel_Consonant()
    {
        assertFalse(logic.isVowel('b'));
    }

    @Test
    public void testIsVowel_NonLetter()
    {
        assertFalse(logic.isVowel('1'));
    }

    // Test serialization
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException
    {
        // Setup
        Logic original = new Logic();
        String testResult = original.calculateGradeLevel("Test serialization.");

        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos))
        {
            oos.writeObject(original);
        }

        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        Logic deserialized;
        try (ObjectInputStream ois = new ObjectInputStream(bais))
        {
            deserialized = (Logic) ois.readObject();
        }

        // Verify
        assertNotNull(deserialized);
        assertEquals(testResult, deserialized.calculateGradeLevel("Test serialization."));
    }

    // Test grade level descriptions
    @Test
    public void testGetGradeLevelDescription_BeforeGrade1()
    {
        assertEquals("Before Grade 1", logic.getGradeLevelDescription(0.5));
    }

    @Test
    public void testGetGradeLevelDescription_Grade1()
    {
        assertEquals("Grade 1", logic.getGradeLevelDescription(1.0));
    }

    @Test
    public void testGetGradeLevelDescription_Grade12()
    {
        assertEquals("Grade 12", logic.getGradeLevelDescription(12.0));
    }

    @Test
    public void testGetGradeLevelDescription_CollegeLevel1()
    {
        assertEquals("College Level 1", logic.getGradeLevelDescription(13.0));
    }

    @Test
    public void testGetGradeLevelDescription_GraduateLevel()
    {
        assertEquals("Graduate Level", logic.getGradeLevelDescription(16.5));
    }
}
