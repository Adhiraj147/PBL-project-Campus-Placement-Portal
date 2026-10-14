@Override
public List<Application> getApplicantsByJobAndCompany(
        int jobId,
        int companyId) throws SQLException {

    List<Application> applications = new ArrayList<>();

    String sql =
        "SELECT a.application_id, " +
        "a.student_id, " +
        "a.job_id, " +
        "a.status, " +
        "a.applied_at, " +
        "s.first_name, " +
        "s.last_name, " +
        "s.roll_no, " +
        "u.email " +
        "FROM applications a " +
        "JOIN jobs j ON a.job_id = j.job_id " +
        "JOIN companies c ON j.company_id = c.company_id " +
        "JOIN students s ON a.student_id = s.student_id " +
        "JOIN users u ON s.user_id = u.user_id " +
        "WHERE j.job_id = ? " +
        "AND c.company_id = ? " +
        "ORDER BY a.applied_at DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, jobId);
        stmt.setInt(2, companyId);

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Application application = new Application();

                application.setApplicationId(
                    rs.getInt("application_id")
                );

                application.setStudentId(
                    rs.getInt("student_id")
                );

                application.setJobId(
                    rs.getInt("job_id")
                );

                application.setStatus(
                    rs.getString("status")
                );

                application.setAppliedAt(
                    rs.getTimestamp("applied_at")
                );

                // Map applicant-specific information using
                // your existing model or a dedicated DTO.

                applications.add(application);
            }
        }
    }

    return applications;
}