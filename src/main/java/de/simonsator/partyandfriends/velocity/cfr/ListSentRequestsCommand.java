package de.simonsator.partyandfriends.velocity.cfr;

import de.simonsator.partyandfriends.velocity.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.velocity.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.velocity.utilities.ConfigurationCreator;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

/**
 * Lists the pending friend requests sent by the player.
 *
 * @author Simonsator
 * @version 1.0.0 10.07.2026
 */
public class ListSentRequestsCommand extends FriendSubCommand {
	private final TextComponent NO_SENT_REQUESTS_MESSAGE;
	private final String SENT_REQUESTS_MESSAGE;
	private final String REQUEST_SEPARATOR;

	protected ListSentRequestsCommand(List<String> pCommands, int pPriority, String pHelp, ConfigurationCreator pConfig) {
		super(pCommands, pPriority, pHelp, pConfig.getString("Commands.SentRequestsCommand.Permission"));
		NO_SENT_REQUESTS_MESSAGE = LegacyComponentSerializer.legacyAmpersand().deserialize((PREFIX + pConfig.getString("Message.NoSentFriendRequests")));
		SENT_REQUESTS_MESSAGE = pConfig.getString("Message.SentFriendRequests");
		REQUEST_SEPARATOR = pConfig.getString("Message.SentFriendRequestsSeparator");
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		List<PAFPlayer> sentRequests = pPlayer.getOpenFriendRequestsSent();
		if (sentRequests.isEmpty()) {
			pPlayer.sendMessage(NO_SENT_REQUESTS_MESSAGE);
			return;
		}
		StringBuilder message = new StringBuilder(PREFIX).append(SENT_REQUESTS_MESSAGE);
		for (int i = 0; i < sentRequests.size(); i++) {
			if (i != 0)
				message.append(REQUEST_SEPARATOR);
			message.append(sentRequests.get(i).getName());
		}
		pPlayer.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize((message.toString())));
	}
}
