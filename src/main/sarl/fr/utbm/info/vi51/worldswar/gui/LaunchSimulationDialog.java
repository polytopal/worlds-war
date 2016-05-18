package fr.utbm.info.vi51.worldswar.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.Colony;

public class LaunchSimulationDialog extends JDialog {
	private static final long serialVersionUID = -8913168141622246707L;

	private static int SPINNER_STEP = 10;

	private SimulationParameters simulationParameters;

	private LaunchSimulationDialog() {
		setTitle(Messages.getString("LaunchSimulation.newSimulation")); //$NON-NLS-1$
		this.setModal(true);
		this.setResizable(false);
		setBounds(100, 100, 400, 300);

		this.simulationParameters = null;

		getContentPane().setLayout(new BorderLayout());
		final JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		final GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.rowHeights = new int[] { 30, 100, 30 };
		gbl_contentPanel.columnWidths = new int[] { 100, 120, 0, 90, 30 };
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

		final JLabel starLabel = new JLabel("*"); //$NON-NLS-1$
		final GridBagConstraints gbc_starLabel = new GridBagConstraints();
		gbc_starLabel.insets = new Insets(5, 0, 5, 5);
		gbc_starLabel.anchor = GridBagConstraints.EAST;
		gbc_starLabel.gridx = 2;
		gbc_starLabel.gridy = 0;
		contentPanel.add(starLabel, gbc_starLabel);

		final JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(SimulationParameters.DEFAULT_HEIGHT,
				SimulationParameters.MIN_HEIGHT, SimulationParameters.MAX_HEIGHT, SPINNER_STEP));
		final GridBagConstraints gbc_heighFormattedTextField = new GridBagConstraints();
		gbc_heighFormattedTextField.gridwidth = 2;
		gbc_heighFormattedTextField.insets = new Insets(5, 0, 5, 5);
		gbc_heighFormattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_heighFormattedTextField.gridx = 3;
		gbc_heighFormattedTextField.gridy = 0;
		contentPanel.add(heightSpinner, gbc_heighFormattedTextField);

		// --------- Colonies ---------

		final JLabel lblColonies = new JLabel(String.format("%s :", Messages.getString("LaunchSimulation.colonies"))); //$NON-NLS-1$ //$NON-NLS-2$
		final GridBagConstraints gbc_lblColonies = new GridBagConstraints();
		gbc_lblColonies.anchor = GridBagConstraints.EAST;
		gbc_lblColonies.insets = new Insets(0, 5, 5, 5);
		gbc_lblColonies.gridx = 0;
		gbc_lblColonies.gridy = 1;
		contentPanel.add(lblColonies, gbc_lblColonies);

		final JScrollPane coloniesScrollPane = new JScrollPane();
		final GridBagConstraints gbc_coloniesScrollPane = new GridBagConstraints();
		gbc_coloniesScrollPane.fill = GridBagConstraints.BOTH;
		gbc_coloniesScrollPane.gridwidth = 4;
		gbc_coloniesScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_coloniesScrollPane.gridx = 1;
		gbc_coloniesScrollPane.gridy = 1;
		contentPanel.add(coloniesScrollPane, gbc_coloniesScrollPane);

		final JTable coloniesTable = new JTable();
		final DefaultTableModel coloniesTableModel = new DefaultTableModel();
		coloniesTableModel.addColumn(Messages.getString("LaunchSimulation.breed"), //$NON-NLS-1$
				SimulationParameters.DEFAULT_BREED_LIST);
		coloniesTable.setModel(coloniesTableModel);
		final TableColumn breedColumn = coloniesTable.getColumnModel().getColumn(0);
		final DefaultCellEditor cellEditor = new DefaultCellEditor(new JComboBox<>(Breed.values()));
		breedColumn.setCellEditor(cellEditor);
		coloniesScrollPane.setViewportView(coloniesTable);

		// add colony button
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
				coloniesTableModel.addRow(new Object[] { SimulationParameters.DEFAULT_BREED_ON_ADD });
			}
		});

		// remove colony button
		final JButton btnRemoveColony = new JButton(Messages.getString("LaunchSimulation.removeColony")); //$NON-NLS-1$
		final GridBagConstraints gbc_btnRemoveColony = new GridBagConstraints();
		gbc_btnRemoveColony.gridwidth = 2;
		gbc_btnRemoveColony.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveColony.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveColony.gridx = 3;
		gbc_btnRemoveColony.gridy = 2;
		contentPanel.add(btnRemoveColony, gbc_btnRemoveColony);
		btnRemoveColony.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int nbElement = coloniesTableModel.getDataVector().size();
				if (nbElement > 0) {
					coloniesTableModel.removeRow(nbElement - 1);
				}
			}
		});
		coloniesTableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				final int nbElement = coloniesTableModel.getDataVector().size();
				btnRemoveColony.setEnabled(nbElement > SimulationParameters.MIN_COLONY_NUMBER);
				btnAddColony.setEnabled(nbElement < SimulationParameters.MAX_COLONY_NUMBER);
			}
		});

		// --------- Food Proportion ---------

		final JLabel foodProportionLabel = new JLabel(
				String.format("%s :", Messages.getString("LaunchSimulation.foodProportion"))); //$NON-NLS-1$ //$NON-NLS-2$
		final GridBagConstraints gbc_foodProportionLabel = new GridBagConstraints();
		gbc_foodProportionLabel.insets = new Insets(0, 5, 0, 5);
		gbc_foodProportionLabel.anchor = GridBagConstraints.EAST;
		gbc_foodProportionLabel.gridx = 0;
		gbc_foodProportionLabel.gridy = 3;
		contentPanel.add(foodProportionLabel, gbc_foodProportionLabel);

		final JSlider foodSlider = new JSlider(SimulationParameters.MIN_FOOD_PROPORTION,
				SimulationParameters.MAX_FOOD_PROPORTION, SimulationParameters.DEFAULT_FOOD_PROPORTION);
		final GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 3;
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 0, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 3;
		contentPanel.add(foodSlider, gbc_slider);

		final JLabel foodProportionDisplayer = new JLabel(SimulationParameters.DEFAULT_FOOD_PROPORTION + "%"); //$NON-NLS-1$
		foodProportionDisplayer.setMinimumSize(new Dimension(30, 14));
		foodProportionDisplayer.setPreferredSize(new Dimension(30, 14));
		final GridBagConstraints gbc_foodProportionDisplayer = new GridBagConstraints();
		gbc_foodProportionDisplayer.insets = new Insets(0, 0, 5, 5);
		gbc_foodProportionDisplayer.gridx = 4;
		gbc_foodProportionDisplayer.gridy = 3;
		contentPanel.add(foodProportionDisplayer, gbc_foodProportionDisplayer);

		foodSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				foodProportionDisplayer.setText(foodSlider.getValue() + "%"); //$NON-NLS-1$
			}
		});

		// --------- OK / Cancel buttons ---------

		final JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// ok button
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

				// Conversion from percent to float
				final float foodProportion = foodSlider.getValue() / 100.0f;

				LaunchSimulationDialog.this.simulationParameters = new SimulationParameters(gridWidth, gridHeight,
						coloniesList, foodProportion);

				dispose();
			}
		});

		// cancel button
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
	 * @return the {@link SimulationParameters} filled by the user, or null
	 */
	public static SimulationParameters getSimulationParameters() {

		final LaunchSimulationDialog dialog = new LaunchSimulationDialog();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		return dialog.simulationParameters;
	}

}
