package de.simonsator.partyandfriends.velocity.cfr;

import de.simonsator.partyandfriends.velocity.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.velocity.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.velocity.utilities.ConfigurationCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

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
		NEVER_SEND_A_FRIEND_REQUEST_MESSAGE = Component.text(PREFIX + pConfig.getString("Message.NeverSendAFriendRequest"));
		CANCELED_MESSAGE = Component.text(PREFIX + pConfig.getString("Message.Canceled"));
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
