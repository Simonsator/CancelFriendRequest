package de.simonsator.partyandfriends.velocity.cfr;

import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 10.01.2022
 */
public class CFRConfig extends ConfigurationCreator {
	protected CFRConfig(File pFile, PAFExtension pPlugin) throws IOException {
		super(pFile, pPlugin, true);
		readFile();
		loadDefaults();
		saveFile();
		process();
	}

	private void loadDefaults() {
		set("Commands.CancelCommand.Name", "cancel", "withdraw");
		set("Commands.CancelCommand.Permission", "");
		set("Commands.CancelCommand.Priority", 200);
		set("Message.CommandUsage", "&8/&5friend cancel [name of the player] &8- &7Cancels a friend request");
		set("Message.NeverSendAFriendRequest", " &7Either you never did send this player a friend request, or the player already accepted your friend request.");
		set("Message.Canceled", " &7The friend request was canceled.");
	}
}
