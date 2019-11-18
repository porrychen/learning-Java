package org.porry.assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * @author porrychen
 * Reminder Class
 */
public class Reminder extends JFrame {
	private static final long serialVersionUID = 1L;
    
	private ReminderFile file;
	
    private int[] months = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final String[] weekDayNames = new String[] {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
    private JLabel[] daysLabel = new JLabel[42];
    
    private int day = 1;
    private int year = 2019;
    private int weekDay = 0;
    private int sumDayToStart = 0;

    private JComboBox<String> yearBox;
    private JComboBox<String> monthBox;

    private JLabel dateLabel;
    private JTextArea noteArea;

    /**
     * constructor
     * @param name
     */
    public Reminder(String name) {
        super(name);
        
        setBounds(100, 100, 800, 400);
        init();
    }

    /**
     * Initialize all components
     */
    private void init() {
    	// load file
    	file = new ReminderFile();
    	file.readFile();
    	
    	// Four Panels
    	JPanel headerPane = new JPanel(new BorderLayout(10, 10));
    	JPanel calendarPane = new JPanel(new BorderLayout(10, 0));
    	calendarPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    	JPanel notePane = new JPanel();
    	notePane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    	notePane.setBackground(Color.ORANGE);
    	JPanel footerPane = new JPanel(new FlowLayout(SwingConstants.RIGHT, 30, 10));

    	// One container
        JPanel contentPane = new JPanel(new BorderLayout(20, 12));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        contentPane.add(headerPane, BorderLayout.NORTH);
        contentPane.add(calendarPane, BorderLayout.CENTER);
        contentPane.add(notePane, BorderLayout.EAST);
        contentPane.add(footerPane, BorderLayout.SOUTH);
        this.add(contentPane);
        
        // Header
        JLabel title = new JLabel("Appointment Reminder");
        title.setFont(new Font("Cambria", 1, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        headerPane.add(title);
        
        // Footer
        JButton okBtn = new JButton("Save");
        // set okBtn to be the first focus
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {   
            	okBtn.requestFocus();   
            }   
        });
        // listen okBtn click event
        okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null) return;
				
				Hashtable<String, String> notes = file.getNotes();
				String key = Integer.parseInt(yearBox.getSelectedItem().toString()) + monthBox.getSelectedItem().toString() + day;
				
				String note = noteArea.getText();
	        	
				if (note.trim().length() == 0) {
					if (!notes.containsKey(key)) {
						JOptionPane.showMessageDialog(null, "No appointment needs to be saved!");
						return;
					}
				}
				
				notes.put(key, note);
				if (file.writeFile()) {
					JOptionPane.showMessageDialog(null, "Appointment saved!");
				}
			}
		});
        footerPane.add(okBtn);
        JButton cancelBtn = new JButton("Quit");
        // listen cancelBtn click event
        cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure that you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) dispose();
			}
		});
        footerPane.add(cancelBtn);

        // Note
        notePane.setLayout(new BoxLayout(notePane, BoxLayout.Y_AXIS));
        JPanel datePane = new JPanel();
        datePane.setBackground(Color.orange);
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Cambria", 1, 14));
        datePane.add(dateLabel);
        notePane.add(datePane);
       
        noteArea = new JTextArea();
        notePane.add(noteArea);
        notePane.setPreferredSize(new Dimension(300, 30));

        // Calendar
        JPanel upPane = new JPanel();
        calendarPane.add(upPane, BorderLayout.NORTH);

        JButton previousMonth = new JButton("<");
        // listen previousMonth click event to change current month
        previousMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int month = monthBox.getSelectedIndex() + 1;
            	if (month == 1) {
            		if (yearBox.getSelectedIndex() == 0) return;
            		
            		yearBox.setSelectedIndex(yearBox.getSelectedIndex() - 1);
            		monthBox.setSelectedIndex(11);
            	} else {
            		monthBox.setSelectedIndex(monthBox.getSelectedIndex() - 1);
            	}

                refresh();
			}
		});
        JButton nextMonth = new JButton(">");
        // listen nextMonth click event to change current month
        nextMonth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	int month = monthBox.getSelectedIndex() + 1;
            	if (month == 12) {
            		if (yearBox.getSelectedIndex() == yearBox.getItemCount() - 1) return;
            		
            		yearBox.setSelectedIndex(yearBox.getSelectedIndex() + 1);
            		monthBox.setSelectedIndex(0);
            	} else {
            		monthBox.setSelectedIndex(monthBox.getSelectedIndex() + 1);
            	}

                refresh();

            }
        });

        LocalDate currentDate = LocalDate.now();
    	int now_year = currentDate.getYear();
    	int now_month = currentDate.getMonth().getValue();
    	day = currentDate.getDayOfMonth();
    	
    	yearBox = new JComboBox<String>();
        for (int i = 1900; i <= 2100; i++) {
            yearBox.addItem(i + "");
            if (now_year == i) yearBox.setSelectedIndex(i - 1900);
        }
        yearBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                refresh();

            }
        });
        monthBox = new JComboBox<String>();
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        for (int i = 0; i < 12; i++) {
            monthBox.addItem(shortMonths[i]);
            if (i == now_month - 1) monthBox.setSelectedIndex(i);
        }
        monthBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                refresh();

            }
        });

        upPane.add(previousMonth);
        upPane.add(yearBox);
        upPane.add(monthBox);
        upPane.add(nextMonth);
        
        JPanel dayPane = new JPanel(new GridLayout(7, 7));
        calendarPane.add(dayPane, BorderLayout.CENTER);
        for (String name : weekDayNames) {
        	JLabel nameLabel = new JLabel(name, JLabel.CENTER);
        	nameLabel.setForeground(Color.blue);
        	nameLabel.setBackground(Color.lightGray);
        	nameLabel.setOpaque(true);
        	dayPane.add(nameLabel);
        }
        
        for (int i = 0; i < daysLabel.length; i++) {
        	daysLabel[i] = new JLabel(" ", JLabel.CENTER);
        	dayPane.add(daysLabel[i]);
        }

        refresh();
    }
    
    /**
     * refresh calendar
     */
    public void refresh() {
        year = Integer.parseInt(yearBox.getSelectedItem().toString());
        setCalendar(year, monthBox.getSelectedIndex() + 1);
        dateLabel.setText(monthBox.getSelectedItem().toString() + " " + day + ", " + year);
        if (file != null) {
        	Hashtable<String, String> notes = file.getNotes();
        	String key = year + monthBox.getSelectedItem().toString() + day;
        	if (notes.containsKey(key)) {
        		noteArea.setText((String) notes.get(key));
        	} else {
        		noteArea.setText(null);
        	}
        }
    }
    
    /**
     * check the year if it is the leap year
     * 
     * @param year
     * @return
     */
    public boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * set calendar
     * 
     * @param year
     * @param month
     */
    public void setCalendar(int year, int month) {
        sumDayToStart = 0;
        for (int i = 1900; i < year; i++) {
            if (isLeapYear(i)) {
                sumDayToStart += 366;
            } else {
                sumDayToStart += 365;
            }

        }

        if (isLeapYear(year)) {
            months[1] = 29;
        } else {
            months[1] = 28;
        }
        for (int i = 0; i < month - 1; i++) {
            sumDayToStart += months[i];
        }
        
        weekDay = sumDayToStart % 7;
        // clear all of the days
        for (int i = 0; i < daysLabel.length; i++) {
        	daysLabel[i].setVisible(false);
        	for (MouseListener listener : daysLabel[i].getMouseListeners()) {
        		daysLabel[i].removeMouseListener(listener);
        	}
        	daysLabel[i].setText(" ");
        	daysLabel[i].setForeground(Color.BLACK);
        	daysLabel[i].setBackground(this.getBackground());
        }

        for (int i = 0; i < months[month - 1]; i++) {
        	daysLabel[weekDay + i + 1].setVisible(true);
        	daysLabel[weekDay + i + 1].setText(i + 1 + "");
        	daysLabel[weekDay + i + 1].addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e) {

                    JLabel clickedLabel = (JLabel) e.getSource();
                    
                    day = Integer.parseInt(clickedLabel.getText());
                    refresh();
                }
			});
        }

        daysLabel[weekDay + day].setForeground(Color.RED);
        daysLabel[weekDay + day].setBackground(Color.WHITE);
        daysLabel[weekDay + day].setOpaque(true);

        sumDayToStart += day;
    }
}
