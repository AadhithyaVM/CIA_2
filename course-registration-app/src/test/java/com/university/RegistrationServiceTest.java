package com.university;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    private RegistrationService registrationService;
    private Student student;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationService();
        student = new Student("S101", "Alice");
    }

    @Test
    void testLastRemainingSeatIsAllowed() {
        Course course = new Course("CS101", "Intro to CS", 3, 1, true, null);
        boolean result = registrationService.register(student, course);
        
        assertTrue(result, "The last remaining seat should be successfully filled.");
        assertEquals(1, course.getCurrentEnrollment());
        assertTrue(student.isRegistered("CS101"));
    }

    @Test
    void testFullCourseRejectsRegistration() {
        Course course = new Course("CS101", "Intro to CS", 3, 1, true, null);
        Student student1 = new Student("S102", "Bob");
        Student student2 = new Student("S103", "Charlie");

        assertTrue(registrationService.register(student1, course));
        assertEquals(1, course.getCurrentEnrollment());

        boolean result = registrationService.register(student2, course);
        
        assertFalse(result, "A full course must reject new registrations.");
        assertFalse(student2.isRegistered("CS101"));
        assertEquals(0, student2.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void testFifthCourseRegistrationAllowed() {
        for (int i = 1; i <= 4; i++) {
            Course c = new Course("C" + i, "Course " + i, 3, 10, true, null);
            registrationService.register(student, c);
        }
        assertEquals(4, student.getRegisteredCourses().size());

        Course fifthCourse = new Course("C5", "Course 5", 3, 10, true, null);
        boolean result = registrationService.register(student, fifthCourse);

        assertTrue(result, "Registration of the fifth course must be allowed.");
        assertEquals(5, student.getRegisteredCourses().size());
    }

    @Test
    void testSixthCourseRegistrationRejected() {
        for (int i = 1; i <= 5; i++) {
            Course c = new Course("C" + i, "Course " + i, 3, 10, true, null);
            registrationService.register(student, c);
        }
        assertEquals(5, student.getRegisteredCourses().size());
        int creditsBeforeSixth = student.getTotalCredits();

        Course sixthCourse = new Course("C6", "Course 6", 3, 10, true, null);
        boolean result = registrationService.register(student, sixthCourse);

        assertFalse(result, "Registration of a sixth course must be rejected.");
        assertEquals(5, student.getRegisteredCourses().size());
        assertEquals(creditsBeforeSixth, student.getTotalCredits());
        assertEquals(0, sixthCourse.getCurrentEnrollment());
    }
}