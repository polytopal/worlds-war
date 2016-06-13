package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import fr.utbm.info.vi51.worldswar.environment.MapInformation;

/**
 * This class is meant to be shown as a popup when using the "simulation"
 * button. It displays information related to the simulation map
 */
public class MapInfoDialog extends JDialog {
	private static final long serialVersionUID = 453449311426715271L;

	/**
	 * Constructor
	 * 
	 * @param mapInformations
	 *            Informations related to the map
	 */
	private MapInfoDialog(MapInformation mapInformations) {
		setTitle(Messages.getString("MapInfoDialog.mapInfoDialog")); //$NON-NLS-1$
		this.setModal(true);
		this.setResizable(false);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		final GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0 };
		getContentPane().setLayout(gridBagLayout);

		//

		final JLabel noiseSeedLabel = new JLabel(Messages.getString("MapInfoDialog.noiseSeed")); //$NON-NLS-1$
		final GridBagConstraints gbc_labelNoiseSeed = new GridBagConstraints();
		gbc_labelNoiseSeed.anchor = GridBagConstraints.EAST;
		gbc_labelNoiseSeed.fill = GridBagConstraints.VERTICAL;
		gbc_labelNoiseSeed.insets = new Insets(5, 5, 5, 5);
		gbc_labelNoiseSeed.gridx = 0;
		gbc_labelNoiseSeed.gridy = 0;

		getContentPane().add(noiseSeedLabel, gbc_labelNoiseSeed);
		final JTextArea noiseSeedTextArea = new JTextArea();
		if (mapInformations != null) {
			noiseSeedTextArea.setText(Integer.toString(mapInformations.getNoiseSeed()));
		} else {
			noiseSeedTextArea.setText(Messages.getString("MapInfoDialog.noMapInfo")); //$NON-NLS-1$
		}
		noiseSeedTextArea.setEditable(false);
		final GridBagConstraints gbc_textAreaNoiseSeed = new GridBagConstraints();
		gbc_textAreaNoiseSeed.fill = GridBagConstraints.BOTH;
		gbc_textAreaNoiseSeed.insets = new Insets(5, 0, 5, 5);
		gbc_textAreaNoiseSeed.gridx = 1;
		gbc_textAreaNoiseSeed.gridy = 0;
		getContentPane().add(noiseSeedTextArea, gbc_textAreaNoiseSeed);

		this.pack();

	}

	// #################################
	/**
	 * Create a dialog window to show the map main informations
	 * 
	 * @param mapInformations
	 *            the informations to show
	 */
	public static void showMapInfoDialog(MapInformation mapInformations) {

		final Dialog dialog = new MapInfoDialog(mapInformations);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

}
