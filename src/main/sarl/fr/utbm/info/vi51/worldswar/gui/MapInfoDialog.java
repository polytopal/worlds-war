package fr.utbm.info.vi51.worldswar.gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import fr.utbm.info.vi51.worldswar.environment.MapInformation;

public class MapInfoDialog extends JDialog {
	private static final long serialVersionUID = 453449311426715271L;
	
	public MapInfoDialog(MapInformation mapInformations) {
		setTitle(Messages.getString("MapInfoDialog.mapInfoDialog")); //$NON-NLS-1$
		
		this.setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		if(mapInformations != null){
			final JLabel labelNoiseSeed = new JLabel(Integer.toString(mapInformations.getNoiseSeed()));
			getContentPane().add(labelNoiseSeed);
		} else {
			final JLabel labelNoInfo = new JLabel(Messages.getString("MapInfoDialog.noMapInfo")); //$NON-NLS-1$
			getContentPane().add(labelNoInfo);
		}
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}
