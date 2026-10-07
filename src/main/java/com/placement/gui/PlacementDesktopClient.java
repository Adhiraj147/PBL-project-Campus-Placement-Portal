package com.placement.gui;

import com.placement.admin.AdminService;
import com.placement.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Enterprise Java Desktop Placement Officer Dashboard
 * Satisfies Java Lab Syllabus requirements for GUI / Applet / Swing desktop client integration.
 * Built with pure Java Foundation Classes (AWT/Swing).
 * Assigned to: Himanshu & Krishna (feature/database-integration, feature/admin-portal)
 */
public class PlacementDesktopClient extends JFrame {
    private final AdminService adminService = new AdminService();
    private boolean isLightMode = false;

    // Dark Obsidian Theme Palette
    private static final Color DARK_BG = new Color(11, 15, 23);
    private static final Color DARK_CARD = new Color(20, 27, 44);
    private static final Color DARK_HEADER = new Color(15, 23, 42);
    private static final Color DARK_GRID = new Color(30, 41, 59);
    private static final Color DARK_TEXT_MAIN = new Color(248, 250, 252);
    private static final Color DARK_TEXT_MUTED = new Color(148, 163, 184);

    // Light Alabaster Theme Palette
    private static final Color LIGHT_BG = new Color(248, 250, 252);
    private static final Color LIGHT_CARD = new Color(255, 255, 255);
    private static final Color LIGHT_HEADER = new Color(241, 245, 249);
    private static final Color LIGHT_GRID = new Color(226, 232, 240);
    private static final Color LIGHT_TEXT_MAIN = new Color(15, 23, 42);
    private static final Color LIGHT_TEXT_MUTED = new Color(100, 116, 139);

    // Dynamic Color Getters
    private Color getBgColor() { return isLightMode ? LIGHT_BG : DARK_BG; }
    private Color getCardBgColor() { return isLightMode ? LIGHT_CARD : DARK_CARD; }
    private Color getHeaderBgColor() { return isLightMode ? LIGHT_HEADER : DARK_HEADER; }
    private Color getGridColor() { return isLightMode ? LIGHT_GRID : DARK_GRID; }
    private Color getTextMainColor() { return isLightMode ? LIGHT_TEXT_MAIN : DARK_TEXT_MAIN; }
    private Color getTextMutedColor() { return isLightMode ? LIGHT_TEXT_MUTED : DARK_TEXT_MUTED; }

    // Vibrant Brand Accents
    private static final Color ACCENT_PRIMARY = new Color(99, 102, 241);
    private static final Color ACCENT_CYAN = new Color(6, 182, 212);

    public PlacementDesktopClient() {
        setTitle("Campus Placement & Internship Portal - TPO Executive Console");
        setSize(1120, 740);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(getBgColor());

        initUI();
    }

    private void initUI() {
        getContentPane().setBackground(getBgColor());
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(getCardBgColor());
        headerPanel.setBorder(new EmptyBorder(16, 24, 16, 24));

        JLabel titleLabel = new JLabel("🎓 Campus Placement & Internship Management Console");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
        titleLabel.setForeground(getTextMainColor());

        JPanel headerActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        headerActions.setOpaque(false);

        JButton themeToggleBtn = new JButton(isLightMode ? "🌙 Dark Theme" : "☀️ Light Theme");
        styleButton(themeToggleBtn, isLightMode ? new Color(51, 65, 85) : new Color(217, 119, 6));
        themeToggleBtn.addActionListener(e -> {
            isLightMode = !isLightMode;
            refreshAllTabs();
        });

        JButton launchWebBtn = new JButton("🌐 Open Web Portal");
        styleButton(launchWebBtn, ACCENT_PRIMARY);
        launchWebBtn.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Make sure server is running on http://localhost:8080", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton refreshBtn = new JButton("🔄 Refresh Data");
        styleButton(refreshBtn, isLightMode ? new Color(100, 116, 139) : new Color(51, 65, 85));
        refreshBtn.addActionListener(e -> refreshAllTabs());

        headerActions.add(themeToggleBtn);
        headerActions.add(launchWebBtn);
        headerActions.add(refreshBtn);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(headerActions, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(getBgColor());
        tabbedPane.setForeground(getTextMainColor());
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabbedPane.addTab("📊 Placement Overview", createOverviewPanel());
        tabbedPane.addTab("👨‍🎓 Students Directory", createStudentsPanel());
        tabbedPane.addTab("🏢 Companies & Recruiters", createCompaniesPanel());
        tabbedPane.addTab("💼 Job & Internship Drives", createJobsPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(getBgColor());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Map<String, Object> stats = adminService.getPlacementStatistics();

        // Metric Cards Grid
        JPanel statsGrid = new JPanel(new GridLayout(2, 4, 15, 15));
        statsGrid.setOpaque(false);

        statsGrid.add(createKpiCard("Total Registered Students", String.valueOf(stats.get("totalStudents")), "Enrolled candidates", ACCENT_PRIMARY));
        statsGrid.add(createKpiCard("Verified Companies", String.valueOf(stats.get("totalCompanies")), "Google, Microsoft, AWS...", ACCENT_CYAN));
        statsGrid.add(createKpiCard("Active Campus Drives", String.valueOf(stats.get("totalJobs")), "Full-Time & Internships", new Color(16, 185, 129)));
        statsGrid.add(createKpiCard("Total Applications", String.valueOf(stats.get("totalApplications")), "Submitted by students", new Color(245, 158, 11)));

        statsGrid.add(createKpiCard("Students Placed", String.valueOf(stats.get("placedStudents")), "Offers confirmed", new Color(168, 85, 247)));
        statsGrid.add(createKpiCard("Overall Placement Rate", stats.get("placementRate") + "%", "Cohort 2026", new Color(236, 72, 153)));
        statsGrid.add(createKpiCard("Average CTC Package", "₹ " + stats.get("averagePackageLpa") + " LPA", "Median tier 1 offers", new Color(14, 165, 233)));
        statsGrid.add(createKpiCard("Highest CTC Package", "₹ " + stats.get("highestPackageLpa") + " LPA", "Peak package offered", new Color(234, 179, 8)));

        panel.add(statsGrid, BorderLayout.NORTH);

        // System Info Center
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(getCardBgColor());
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(getGridColor(), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel infoTitle = new JLabel("PBL 5th Semester Lab Final Project - Module Status");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        infoTitle.setForeground(getTextMainColor());

        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setBackground(getCardBgColor());
        ta.setForeground(getTextMutedColor());
        ta.setFont(new Font("Consolas", Font.PLAIN, 13));
        ta.setText("""
                • Adhiraj   [feature/authentication]      : SHA-256 Auth, Session Management, RBAC security filters
                • Shlok     [feature/student-portal]       : Profile, CGPA eligibility engine, Application pipeline
                • Saurabh   [feature/recruiter-portal]     : Company profile, Job drives, Shortlisting & Interview scheduler
                • Krishna   [feature/admin-portal]         : Moderation, Company verification, Real-time placement analytics
                • Himanshu  [feature/database-integration] : Dual-engine JDBC persistence, In-Memory engine, Test Suite runner
                
                Zero-setup runtime active. Ready for live demonstration and defense.
                """);

        infoPanel.add(infoTitle, BorderLayout.NORTH);
        infoPanel.add(ta, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(getBgColor());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] cols = {"Roll No", "Full Name", "Branch", "CGPA", "Year", "Email", "Top Skills"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<StudentProfile> list = adminService.getAllStudents();
        for (StudentProfile s : list) {
            model.addRow(new Object[]{
                    s.getRollNumber(), s.getStudentName(), s.getBranch(), s.getCgpa(),
                    s.getGraduationYear(), s.getStudentEmail(), s.getSkills()
            });
        }

        JTable table = createStyledTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(getBgColor());
        scrollPane.setBorder(BorderFactory.createLineBorder(getGridColor(), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCompaniesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(getBgColor());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] cols = {"Company Name", "Industry", "Location", "Website", "Recruiter Name", "Verification Status"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<CompanyProfile> list = adminService.getAllCompanies();
        for (CompanyProfile c : list) {
            model.addRow(new Object[]{
                    c.getCompanyName(), c.getIndustry(), c.getLocation(),
                    c.getWebsite(), c.getRecruiterName(), c.getVerificationStatus().name()
            });
        }

        JTable table = createStyledTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(getBgColor());
        scrollPane.setBorder(BorderFactory.createLineBorder(getGridColor(), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createJobsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(getBgColor());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] cols = {"Job Title", "Company", "Type", "Package (LPA)", "Min CGPA", "Location", "Status", "Applicants"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<Job> list = adminService.getAllJobs();
        for (Job j : list) {
            model.addRow(new Object[]{
                    j.getTitle(), j.getCompanyName(), j.getJobType().name(),
                    j.getPackageLpa() > 0 ? ("₹ " + j.getPackageLpa()) : ("₹ " + j.getStipendPm() + "/mo"),
                    j.getMinCgpa(), j.getLocation(), j.getStatus().name(), j.getApplicantCount()
            });
        }

        JTable table = createStyledTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(getBgColor());
        scrollPane.setBorder(BorderFactory.createLineBorder(getGridColor(), 1));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createKpiCard(String title, String value, String subtitle, Color accent) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        card.setBackground(getCardBgColor());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(getGridColor(), 1),
                        BorderFactory.createMatteBorder(0, 4, 0, 0, accent)
                ),
                new EmptyBorder(14, 16, 14, 16)
        ));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLbl.setForeground(getTextMutedColor());

        JLabel valLbl = new JLabel(value);
        valLbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        valLbl.setForeground(getTextMainColor());

        JLabel subLbl = new JLabel(subtitle);
        subLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subLbl.setForeground(getTextMutedColor());

        card.add(titleLbl, BorderLayout.NORTH);
        card.add(valLbl, BorderLayout.CENTER);
        card.add(subLbl, BorderLayout.SOUTH);
        return card;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(getCardBgColor());
        table.setForeground(getTextMainColor());
        table.setGridColor(getGridColor());
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setBackground(getHeaderBgColor());
        table.getTableHeader().setForeground(getTextMainColor());
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        renderer.setBackground(getCardBgColor());
        renderer.setForeground(getTextMainColor());
        table.setDefaultRenderer(Object.class, renderer);

        return table;
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 16, 8, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void refreshAllTabs() {
        getContentPane().removeAll();
        initUI();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlacementDesktopClient client = new PlacementDesktopClient();
            client.setVisible(true);
        });
    }
}
