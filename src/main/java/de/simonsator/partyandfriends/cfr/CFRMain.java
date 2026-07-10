package de.simonsator.partyandfriends.cfr;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 10.01.2022
 */
@SuppressWarnings("unused")
public class CFRMain extends PAFExtension {

	@Override
	public void onEnable() {
		try {
			ConfigurationCreator config = (new CFRConfig(new File(getConfigFolder(), "config.yml"), this));
			Friends.getInstance().addCommand(new CancelCommand(config.getStringList("Commands.CancelCommand.Name"),
					config.getInt("Commands.CancelCommand.Priority"), config.getString("Message.CommandUsage"), config));
			if (!config.getBoolean("Commands.SentRequestsCommand.Disabled")) {
				Friends.getInstance().addCommand(new ListSentRequestsCommand(config.getStringList("Commands.SentRequestsCommand.Name"),
						config.getInt("Commands.SentRequestsCommand.Priority"), config.getString("Message.SentRequestsCommandUsage"), config));
			}
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
