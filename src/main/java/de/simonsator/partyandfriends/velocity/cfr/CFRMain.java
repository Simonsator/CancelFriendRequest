package de.simonsator.partyandfriends.velocity.cfr;

import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.friends.commands.Friends;
import de.simonsator.partyandfriends.velocity.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author simonbrungs
 * @version 1.0.0 10.01.2022
 */
public class CFRMain extends PAFExtension {

	public CFRMain(Path folder) {
		super(folder);
	}

	@Override
	public void onEnable() {
		try {
			ConfigurationCreator config = (new CFRConfig(new File(getConfigFolder(), "config.yml"), this));
			Friends.getInstance().addCommand(new CancelCommand(config.getStringList("Commands.CancelCommand.Name"),
					config.getInt("Commands.CancelCommand.Priority"), config.getString("Message.CommandUsage"), config));
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "CancelFriendRequest";
	}
}
