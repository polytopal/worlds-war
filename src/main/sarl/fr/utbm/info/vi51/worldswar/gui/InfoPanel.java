package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * 
 * This JPanel appear at the bottom of the GUI, and indicates information about
 * the state of the simulation, such as if it is running, the number of steps
 * per second etc. It is meant to be instantiated and updated by Window.
 *
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()//
			.appendDays()//
			.appendSuffix("d, ")//$NON-NLS-1$
			.appendHours()//
			.appendSuffix("h ") //$NON-NLS-1$
			.appendMinutes()//
			.appendSuffix("m ") //$NON-NLS-1$
			.appendSeconds()//
			.appendSuffix("s") //$NON-NLS-1$
			.toFormatter();

	private final JLabel labelSimulationState;
	private final JLabel labelSimulationSpeed;
	private final JLabel labelSimulationTime;
	private final JLabel labelAgentCount;

	/**
	 * Build the InfoPanel (bottom status bar)
	 */
	public InfoPanel() {
		this.labelSimulationState = new JLabel();
		this.labelSimulationSpeed = new JLabel();
		this.labelSimulationTime = new JLabel();
		this.labelAgentCount = new JLabel();

		this.add(this.labelSimulationState);
		this.add(this.labelSimulationTime);
		this.add(this.labelSimulationSpeed);
		this.add(this.labelAgentCount);

		this.setSimulationStateLabel(SimulationState.STOPPED);
		this.setSimulationSpeedLabel(0);
		this.setSimulationTimeLabel(0);
		this.setAgentCountLabel(0);

	}

	/**
	 * @param nbSPS
	 *            the current steps per second
	 */
	public void setSimulationSpeedLabel(float nbSPS) {
		final Float speedMultiplier = new Float(nbSPS * (SimulationSpeed.REAL_TIME.getMilliseconds() / 1000.f));
		this.labelSimulationSpeed
				.setText(String.format("%s : X%.1f |", Messages.getString("InfoPanel.currentSpeed"), speedMultiplier)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @param simState
	 *            the {@link SimulationState to display}
	 */
	public void setSimulationStateLabel(SimulationState simState) {
		this.labelSimulationState.setText(Messages.getString(simState.getPropertyKey()) + " |"); //$NON-NLS-1$
	}

	/**
	 * @param stepNumber
	 *            the current step number
	 */
	public void setSimulationTimeLabel(int stepNumber) {
		Duration duration = new Duration((stepNumber * SimulationSpeed.REAL_TIME.getMilliseconds()));

		// Workaround to a bug that causes seconds to not be displayed when
		// milliseconds are not null but lesser than 1000
		if (duration.getMillis() < 1000) {
			duration = new Duration(0);
		}

		this.labelSimulationTime.setText(String.format(" %s : %s |", //$NON-NLS-1$
				Messages.getString("InfoPanel.simulationTime"), PERIOD_FORMATTER.print(duration.toPeriod()))); //$NON-NLS-1$
	}

	/**
	 * @param agentCount
	 *            the count of agents to display
	 */
	public void setAgentCountLabel(int agentCount) {
		this.labelAgentCount.setText(
				String.format(" %s : %d", Messages.getString("InfoPanel.agentCount"), new Integer(agentCount))); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
