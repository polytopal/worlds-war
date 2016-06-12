package fr.utbm.info.vi51.worldswar.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.MapInformation;

/**
 * This class is meant to be shown as a popup when using the "simulation" button.
 * It displays information related to the simulation map
 */
public class MapInfoDialog extends JDialog {
	private static final long serialVersionUID = 453449311426715271L;
	
	/**
	 * Constructor
	 * @param mapInformations Informations related to the map
	 */
	public MapInfoDialog(MapInformation mapInformations) {
		setTitle(Messages.getString("MapInfoDialog.mapInfoDialog")); //$NON-NLS-1$
		
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		if(mapInformations != null){
			final JLabel labelNoiseSeed = new JLabel(Messages.getString("MapInfoDialog.noiseSeed")); //$NON-NLS-1$
			getContentPane().add(labelNoiseSeed, BorderLayout.WEST);
			final JTextArea textAreaNoiseSeed = new JTextArea(Integer.toString(mapInformations.getNoiseSeed()));
			textAreaNoiseSeed.setEditable(false);
			getContentPane().add(textAreaNoiseSeed, BorderLayout.EAST);	
		} else {
			final JLabel labelNoInfo = new JLabel(Messages.getString("MapInfoDialog.noMapInfo")); //$NON-NLS-1$
			getContentPane().add(labelNoInfo);
		}
		
		
		this.pack();
		this.setMinimumSize(new Dimension(200, 20));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setModal(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}

}
