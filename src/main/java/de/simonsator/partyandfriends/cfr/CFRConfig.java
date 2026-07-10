package de.simonsator.partyandfriends.cfr;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

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
		set("Commands.SentRequestsCommand.Name", "sent", "sentrequests");
		set("Commands.SentRequestsCommand.Disabled", false);
		set("Commands.SentRequestsCommand.Permission", "");
		set("Commands.SentRequestsCommand.Priority", 201);
		set("Message.CommandUsage", "&8/&5friend cancel [name of the player] &8- &7Cancels a friend request");
		set("Message.SentRequestsCommandUsage", "&8/&5friend sent &8- &7Lists your sent friend requests");
		set("Message.NeverSendAFriendRequest", " &7Either you never did send this player a friend request, or the player already accepted your friend request.");
		set("Message.Canceled", " &7The friend request was canceled.");
		set("Message.NoSentFriendRequests", " &7You have no pending sent friend requests.");
		set("Message.SentFriendRequests", " &7Pending sent friend requests: &f");
		set("Message.SentFriendRequestsSeparator", "&7, &f");
	}
}
