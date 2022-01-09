package de.simonsator.partyandfriends.cfr;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

/**
 * @author Simonsator
 * @version 1.0.0 09.01.2022
 */
public class CancelCommand extends FriendSubCommand {
	private final TextComponent NEVER_SEND_A_FRIEND_REQUEST_MESSAGE;
	private final TextComponent CANCELED_MESSAGE;

	protected CancelCommand(List<String> pCommands, int pPriority, String pHelp, ConfigurationCreator pConfig) {
		super(pCommands, pPriority, pHelp, pConfig.getString("Commands.CancelCommand.Permission"));
		NEVER_SEND_A_FRIEND_REQUEST_MESSAGE = new TextComponent(TextComponent.fromLegacyText(PREFIX + pConfig.getString("Message.NeverSendAFriendRequest")));
		CANCELED_MESSAGE = new TextComponent(TextComponent.fromLegacyText(PREFIX + pConfig.getString("Message.Canceled")));
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (!isPlayerGiven(pPlayer, args))
			return;
		PAFPlayer toCancel = PAFPlayerManager.getInstance().getPlayer(args[1]);
		if (toCancel == null) {
			sendError(pPlayer, NEVER_SEND_A_FRIEND_REQUEST_MESSAGE);
			return;
		}
		if (toCancel.hasRequestFrom(pPlayer)) {
			toCancel.denyRequest(pPlayer);
			pPlayer.sendMessage(CANCELED_MESSAGE);
			return;
		}
		sendError(pPlayer, NEVER_SEND_A_FRIEND_REQUEST_MESSAGE);
	}
}
