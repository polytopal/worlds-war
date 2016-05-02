package fr.utbm.info.vi51.worldswar.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.Colony;

public class LaunchSimulationDialog extends JDialog {
	private static final long serialVersionUID = -8913168141622246707L;

	private static int SPINNER_STEP = 10;

	private SimulationParameters simPara;

	private LaunchSimulationDialog() {
		setTitle(Messages.getString("LaunchSimulation.newSimulation")); //$NON-NLS-1$
		this.setModal(true);
		this.setResizable(false);
		setBounds(100, 100, 358, 237);

		this.simPara = null;

		getContentPane().setLayout(new BorderLayout());
		final JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		final GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.rowHeights = new int[] { 30, 80, 30 };
		gbl_contentPanel.columnWidths = new int[] { 100, 100, 0, 100 };
		contentPanel.setLayout(gbl_contentPanel);

		// --------- Grid size ---------

		final JLabel lblGridSize = new JLabel(String.format("%s :", Messages.getString("LaunchSimulation.gridSize"))); //$NON-NLS-1$ //$NON-NLS-2$
		final GridBagConstraints gbc_lblGridSize = new GridBagConstraints();
		gbc_lblGridSize.insets = new Insets(5, 5, 5, 5);
		gbc_lblGridSize.anchor = GridBagConstraints.EAST;
		gbc_lblGridSize.gridx = 0;
		gbc_lblGridSize.gridy = 0;
		contentPanel.add(lblGridSize, gbc_lblGridSize);

		final JSpinner widthSpinner = new JSpinner(new SpinnerNumberModel(SimulationParameters.DEFAULT_WIDTH,
				SimulationParameters.MIN_WIDTH, SimulationParameters.MAX_WIDTH, SPINNER_STEP));
		final GridBagConstraints gbc_widthFormattedTextField = new GridBagConstraints();
		gbc_widthFormattedTextField.insets = new Insets(5, 0, 5, 5);
		gbc_widthFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_widthFormattedTextField.gridx = 1;
		gbc_widthFormattedTextField.gridy = 0;
		contentPanel.add(widthSpinner, gbc_widthFormattedTextField);

		final JLabel label = new JLabel("*"); //$NON-NLS-1$
		final GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(5, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		contentPanel.add(label, gbc_label);

		final JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(SimulationParameters.DEFAULT_HEIGHT,
				SimulationParameters.MIN_HEIGHT, SimulationParameters.MAX_HEIGHT, SPINNER_STEP));
		final GridBagConstraints gbc_heighFormattedTextField = new GridBagConstraints();
		gbc_heighFormattedTextField.insets = new Insets(5, 0, 5, 5);
		gbc_heighFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_heighFormattedTextField.gridx = 3;
		gbc_heighFormattedTextField.gridy = 0;
		contentPanel.add(heightSpinner, gbc_heighFormattedTextField);

		// --------- Colonies ---------

		final JLabel lblColonies = new JLabel(String.format("%s :", Messages.getString("LaunchSimulation.colonies"))); //$NON-NLS-1$ //$NON-NLS-2$
		final GridBagConstraints gbc_lblColonies = new GridBagConstraints();
		gbc_lblColonies.anchor = GridBagConstraints.EAST;
		gbc_lblColonies.insets = new Insets(0, 0, 5, 5);
		gbc_lblColonies.gridx = 0;
		gbc_lblColonies.gridy = 1;
		contentPanel.add(lblColonies, gbc_lblColonies);

		final JScrollPane coloniesScrollPane = new JScrollPane();
		final GridBagConstraints gbc_coloniesScrollPane = new GridBagConstraints();
		gbc_coloniesScrollPane.fill = GridBagConstraints.BOTH;
		gbc_coloniesScrollPane.gridwidth = 3;
		gbc_coloniesScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_coloniesScrollPane.gridx = 1;
		gbc_coloniesScrollPane.gridy = 1;
		contentPanel.add(coloniesScrollPane, gbc_coloniesScrollPane);

		final JTable coloniesTable = new JTable();
		final DefaultTableModel coloniesTableModel = new DefaultTableModel(new Object[][] {},
				new String[] { Messages.getString("LaunchSimulation.breed") }); //$NON-NLS-1$
		coloniesTable.setModel(coloniesTableModel);
		final TableColumn breedColumn = coloniesTable.getColumnModel().getColumn(0);
		breedColumn.setCellEditor(new DefaultCellEditor(new JComboBox<>(Breed.values())));
		coloniesScrollPane.setViewportView(coloniesTable);

		final JButton btnAddColony = new JButton(Messages.getString("LaunchSimulation.addColony")); //$NON-NLS-1$
		final GridBagConstraints gbc_btnAddColony = new GridBagConstraints();
		gbc_btnAddColony.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddColony.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddColony.gridx = 1;
		gbc_btnAddColony.gridy = 2;
		contentPanel.add(btnAddColony, gbc_btnAddColony);
		btnAddColony.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				coloniesTableModel.addRow(new Object[] { SimulationParameters.DEFAULT_BREED });
			}
		});

		final JButton btnRemoveColony = new JButton(Messages.getString("LaunchSimulation.removeColony")); //$NON-NLS-1$
		btnRemoveColony.setEnabled(false);
		final GridBagConstraints gbc_btnRemoveColony = new GridBagConstraints();
		gbc_btnRemoveColony.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveColony.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveColony.gridx = 3;
		gbc_btnRemoveColony.gridy = 2;
		contentPanel.add(btnRemoveColony, gbc_btnRemoveColony);
		btnRemoveColony.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int selectedRow = coloniesTable.getSelectedRow();
				if (selectedRow >= 0) {
					coloniesTableModel.removeRow(selectedRow);
					System.out.println("remove : " + selectedRow);
				}
			}
		});
		coloniesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				final int selectedRow = coloniesTable.getSelectedRow();
				System.out.println(selectedRow);
				btnRemoveColony.setEnabled(selectedRow >= 0);
			}
		});

		// --------- OK / Cancel buttons ---------

		final JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		final JButton okButton = new JButton(Messages.getString("LaunchSimulation.OK")); //$NON-NLS-1$
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			// We can box the value of the Spinners without problem because the
			// Spinners doesn't accept any other kind of value
			@SuppressWarnings("boxing")
			@Override
			public void actionPerformed(ActionEvent e) {
				final int gridWidth = (int) widthSpinner.getValue();
				final int gridHeight = (int) heightSpinner.getValue();
				final List<Colony> coloniesList = new ArrayList<>();
				for (int i = 0; i < coloniesTableModel.getRowCount(); i++) {
					final Breed breed = (Breed) coloniesTableModel.getValueAt(i, 0);
					coloniesList.add(new Colony(breed));
				}

				LaunchSimulationDialog.this.simPara = new SimulationParameters(gridWidth, gridHeight, coloniesList);

				dispose();
			}
		});

		final JButton cancelButton = new JButton(Messages.getString("LaunchSimulation.cancel")); //$NON-NLS-1$
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	// #################################
	/**
	 * Create a dialog window to ask simulation parameters to user. Return the
	 * simulation parameters when the user uses the "OK" button, or return null
	 * if the user close the window
	 * 
	 * @return the Simulation parameters filled by the user, or null
	 */
	public static SimulationParameters getSimulationParameters() {

		final LaunchSimulationDialog dialog = new LaunchSimulationDialog();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		return dialog.simPara;
	}

}
