package com.university;

public class RegistrationService {

    private static final int MAX_CREDITS = 20;
    private static final int MAX_COURSES = 5; // Question Set B: Max 5 courses limit[cite: 6]

    public boolean register(Student student, Course course) {

        if (!course.isActive()) {
            return false;
        }

        // Fix: Changed '>' to '>=' so a full course rejects new registrations[cite: 4]
        if (course.getCurrentEnrollment() >= course.getCapacity()) {
            return false;
        }

        if (student.isRegistered(course.getCourseCode())) {
            return false;
        }

        // Question Set B: Reject registration if student already has 5 courses[cite: 6]
        if (student.getRegisteredCourses().size() >= MAX_COURSES) {
            return false;
        }

        String prerequisite = course.getPrerequisite();

        if (prerequisite != null
                && !prerequisite.isBlank()
                && !student.hasCompletedPrerequisite(prerequisite)) {
            return false;
        }

        if (student.getTotalCredits() + course.getCredits() > MAX_CREDITS) {
            return false;
        }

        student.addRegistration(
                course.getCourseCode(),
                course.getCredits());

        course.incrementEnrollment();

        return true;
    }
}