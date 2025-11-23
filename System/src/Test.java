import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Test extends JFrame {

    // --- Course "card" panel ------------------------------
    private class CoursePanel extends JPanel {
        JTextField codeField = new JTextField(8);
        JTextField nameField = new JTextField(10);
        JTextField unitsField = new JTextField(3);

        JTextField prelimField = new JTextField(4);
        JTextField midtermField = new JTextField(4);
        JTextField prefinalField = new JTextField(4);
        JTextField finalField = new JTextField(4);

        JLabel averageLabel = new JLabel("Average: -");
        JButton removeButton = new JButton("Remove");

        CoursePanel() {
            setBorder(BorderFactory.createTitledBorder("Course"));
            setLayout(new BorderLayout(5, 5));

            // Top row: code, name, units, average, remove
            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
            top.add(new JLabel("Course Code:"));
            top.add(codeField);
            top.add(new JLabel("Course Name:"));
            top.add(nameField);
            top.add(new JLabel("Units:"));
            top.add(unitsField);
            top.add(averageLabel);
            top.add(removeButton);
            add(top, BorderLayout.NORTH);

            // Second part: grades
            JPanel gradesPanel = new JPanel(new GridLayout(2, 4, 5, 2));
            gradesPanel.add(new JLabel("Prelim (20%)"));
            gradesPanel.add(new JLabel("Midterm (20%)"));
            gradesPanel.add(new JLabel("Prefinal (20%)"));
            gradesPanel.add(new JLabel("Final (40%)"));

            gradesPanel.add(prelimField);
            gradesPanel.add(midtermField);
            gradesPanel.add(prefinalField);
            gradesPanel.add(finalField);

            add(gradesPanel, BorderLayout.CENTER);

            // Listeners to recalc when anything changes
            DocumentListener docListener = new SimpleDocumentListener(() -> {
                updateAverageLabel();
                recalcOverallGwa();
            });

            unitsField.getDocument().addDocumentListener(docListener);
            prelimField.getDocument().addDocumentListener(docListener);
            midtermField.getDocument().addDocumentListener(docListener);
            prefinalField.getDocument().addDocumentListener(docListener);
            finalField.getDocument().addDocumentListener(docListener);

            removeButton.addActionListener((ActionEvent e) -> {
                coursePanels.remove(this);
                coursesContainer.remove(this);
                coursesContainer.revalidate();
                coursesContainer.repaint();
                recalcOverallGwa();
            });
        }

        // Compute this course's average using the given formula
        Double getCourseAverage() {
            Double prelim = parseDouble(prelimField.getText());
            Double midterm = parseDouble(midtermField.getText());
            Double prefinal = parseDouble(prefinalField.getText());
            Double fin = parseDouble(finalField.getText());

            // Require all grade fields to be valid numbers
            if (prelim == null || midterm == null || prefinal == null || fin == null) {
                return null;
            }

            // (prelim + midterm + prefinal) * 0.2 + (finalExam * 0.4)
            return (prelim + midterm + prefinal) * 0.2 + (fin * 0.4);
        }

        Double getUnits() {
            return parseDouble(unitsField.getText());
        }

        void updateAverageLabel() {
            Double avg = getCourseAverage();
            if (avg == null) {
                averageLabel.setText("Average: -");
                averageLabel.setForeground(Color.BLACK);
            } else {
                averageLabel.setText(String.format("Average: %.2f", avg));
                if (avg >= 75) {
                    averageLabel.setForeground(new Color(0, 128, 0)); // green-ish
                } else {
                    averageLabel.setForeground(Color.RED);
                }
            }
        }
    }

    // Small helper for document events
    private static class SimpleDocumentListener implements DocumentListener {
        private final Runnable onChange;

        SimpleDocumentListener(Runnable onChange) {
            this.onChange = onChange;
        }

        @Override public void insertUpdate(DocumentEvent e) { onChange.run(); }
        @Override public void removeUpdate(DocumentEvent e) { onChange.run(); }
        @Override public void changedUpdate(DocumentEvent e) { onChange.run(); }
    }

    // --- Main frame fields --------------------------------
    private final List<CoursePanel> coursePanels = new ArrayList<>();
    private final JPanel coursesContainer = new JPanel();

    private final JLabel totalGwaLabel = new JLabel("GWA: 0.00");
    private final JLabel totalUnitsLabel = new JLabel("Total Units: 0");
    private final JLabel statusLabel = new JLabel("Status: -");
    private final JButton addCourseButton = new JButton("Add Course");

    public Test() {
        super("Grade Calculator Per Course (GWA)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // Top summary panel
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JPanel gwaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        gwaPanel.setBorder(BorderFactory.createTitledBorder("General Weighted Average (GWA)"));

        totalGwaLabel.setFont(totalGwaLabel.getFont().deriveFont(Font.BOLD, 18f));
        gwaPanel.add(totalGwaLabel);
        gwaPanel.add(totalUnitsLabel);
        gwaPanel.add(statusLabel);

        topPanel.add(gwaPanel, BorderLayout.CENTER);

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addPanel.add(addCourseButton);
        topPanel.add(addPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center: scrollable list of course panels
        coursesContainer.setLayout(new BoxLayout(coursesContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(coursesContainer);
        add(scrollPane, BorderLayout.CENTER);

        // Add button action
        addCourseButton.addActionListener(e -> addCoursePanel());

        // Start with one empty course
        addCoursePanel();
    }

    private void addCoursePanel() {
        CoursePanel cp = new CoursePanel();
        coursePanels.add(cp);
        coursesContainer.add(cp);
        coursesContainer.revalidate();
        coursesContainer.repaint();
    }

    // Recalculate overall GWA based on all course panels
    private void recalcOverallGwa() {
        double totalWeighted = 0.0;
        double totalUnits = 0.0;

        for (CoursePanel cp : coursePanels) {
            Double avg = cp.getCourseAverage();
            Double units = cp.getUnits();
            if (avg != null && units != null && units > 0) {
                totalWeighted += avg * units;
                totalUnits += units;
            }
        }

        if (totalUnits == 0) {
            totalGwaLabel.setText("GWA: 0.00");
            totalUnitsLabel.setText("Total Units: 0");
            statusLabel.setText("Status: -");
            statusLabel.setForeground(Color.BLACK);
            return;
        }

        double gwa = totalWeighted / totalUnits;
        totalGwaLabel.setText(String.format("GWA: %.2f", gwa));
        totalUnitsLabel.setText(String.format("Total Units: %.0f", totalUnits));

        if (gwa >= 75) {
            statusLabel.setText("Status: PASSING");
            statusLabel.setForeground(new Color(0, 128, 0));
        } else {
            statusLabel.setText("Status: FAILING");
            statusLabel.setForeground(Color.RED);
        }
    }

    // Safe parsing helper
    private static Double parseDouble(String text) {
        text = text.trim();
        if (text.isEmpty()) return null;
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test().setVisible(true));
    }

}
