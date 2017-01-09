package de.simonsator.partyandfriends.cfr;

import de.simonsator.partyandfriends.friends.commands.Friends;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 09.01.17
 */
public class CFRMain extends Plugin {

	@Override
	public void onEnable() {
		try {
			Configuration config = (new CFRConfig(new File(getDataFolder(), "config,yml"))).getCreatedConfiguration();
			Friends.getInstance().addCommand(new CancelCommand(config.getStringList("Commands.CancelCommand.Name").toArray(new String[0]),
					config.getInt("Commands.CancelCommand.Priority"), config.getString("Message.CommandUsage"), config));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
