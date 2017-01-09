package de.simonsator.partyandfriends.cfr;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 09.01.17
 */
public class CFRConfig extends ConfigurationCreator {
	protected CFRConfig(File pFile) throws IOException {
		super(pFile);
		readFile();
		loadDefaults();
		saveFile();
		process(configuration);
	}

	private void loadDefaults() {
		set("Commands.CancelCommand.Name", "cancel", "withdraw");
		set("Commands.CancelCommand.Priority", 200);
		set("Message.CommandUsage", "&8/&5friend cancel [name of the player] &8- &7Cancels a friend request");
		set("Message.NeverSendAFriendRequest", " &7Either you never did send this player a friend request, or the player already accepted your friend request.");
		set("Message.Canceled", " &7The friend request was canceled.");
	}

	@Override
	public void reloadConfiguration() throws IOException {
		configuration = (new CFRConfig(FILE)).getCreatedConfiguration();
	}
}
