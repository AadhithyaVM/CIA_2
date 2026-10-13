package com.university;

public class RegistrationService {

    private static final int MAX_CREDITS = 20;
    private static final int MAX_COURSES = 5; // Question Set B: Max 5 courses limit

    public boolean register(Student student, Course course) {

        // 1. Active course check
        if (!course.isActive()) {
            return false;
        }

        // 2. Capacity boundary fix (Task 3)
        if (course.getCurrentEnrollment() >= course.getCapacity()) {
            return false;
        }

        // 3. Already registered check
        if (student.isRegistered(course.getCourseCode())) {
            return false;
        }

        // 4. Question Set B: Max 5 courses limit check
        if (student.getRegisteredCourses().size() >= MAX_COURSES) {
            return false;
        }

        // 5. Prerequisite check
        String prerequisite = course.getPrerequisite();
        if (prerequisite != null
                && !prerequisite.isBlank()
                && !student.hasCompletedPrerequisite(prerequisite)) {
            return false;
        }

        // 6. Credit limit check
        if (student.getTotalCredits() + course.getCredits() > MAX_CREDITS) {
            return false;
        }

        // Perform registration
        student.addRegistration(
                course.getCourseCode(),
                course.getCredits());

        course.incrementEnrollment();

        return true;
    }
}